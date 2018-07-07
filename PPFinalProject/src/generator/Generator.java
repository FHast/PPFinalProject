package generator;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import checker.Result;
import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArgsContext;
import grammar.SycoraxParser.ArrayContext;
import grammar.SycoraxParser.ArrayDefContext;
import grammar.SycoraxParser.ArrayTargetContext;
import grammar.SycoraxParser.AssignStatContext;
import grammar.SycoraxParser.BasicDefContext;
import grammar.SycoraxParser.BlockContext;
import grammar.SycoraxParser.BoolOpExprContext;
import grammar.SycoraxParser.CallExprContext;
import grammar.SycoraxParser.CallStatContext;
import grammar.SycoraxParser.CharExprContext;
import grammar.SycoraxParser.CompOpExprContext;
import grammar.SycoraxParser.FailStatContext;
import grammar.SycoraxParser.FalseExprContext;
import grammar.SycoraxParser.ForkStatContext;
import grammar.SycoraxParser.FunDefContext;
import grammar.SycoraxParser.IdExprContext;
import grammar.SycoraxParser.IdPointerContext;
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IndexExprContext;
import grammar.SycoraxParser.IndexPointerContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.JoinStatContext;
import grammar.SycoraxParser.LockStatContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParamContext;
import grammar.SycoraxParser.PointerStatContext;
import grammar.SycoraxParser.PrintStatContext;
import grammar.SycoraxParser.ProgramContext;
import grammar.SycoraxParser.ReadExprContext;
import grammar.SycoraxParser.ReturnStatContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.StrExprContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.UnlockStatContext;
import grammar.SycoraxParser.WhileStatContext;
import program.Address;
import program.Instr;
import program.OpCode;
import program.Operand;
import program.Operator;
import program.Program;
import program.Reg;
import program.Str;
import program.Target;
import program.Address.Addr;
import program.Operator.Op;
import program.Target.Tar;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.Data.Pointer;
import symbTable.SymbolTables;

/**
 * Code generator for sycorax to sprockell
 * @author gereon
 *
 */
public class Generator extends SycoraxBaseVisitor<Integer> {

	/** resulting sprockell program */
	private Program prog;
	/** typechecking result */
	private Result checkResult;
	/** rotation of available registers */
	private int regCount = 0;

	/** registers */
	private Reg zero = new Reg("reg0");
	/** arp register */
	private Reg arp = new Reg("regArp");
	/** heap management register */
	private Reg heap = new Reg("regHeap");
	/** register containing threadID */
	private Reg regThreadID = new Reg("regSprID");
	/** available registers */
	private Reg[] regs = { new Reg("regA"), new Reg("regB"), new Reg("regC"), new Reg("regD"), new Reg("regE"),
			new Reg("regF"), new Reg("regG"), new Reg("regH") };


	/** targets to jump to start a function */
	private Map<String, Target> startFun;
	/** targets to jump to end of function */
	private Map<String, Target> endFun;
	/** mapping thread names to threadIDs */
	private Map<String, Integer> threadIDs;
	/** global arp storage location */
	private Address gArp;
	/** global heap storage location */
	private Address gHeap;

	/** error jump location */
	private final int errorJump = 1;

	/**
	 * Create a generator
	 * @param tables symboltables
	 * @param res typecheking result
	 */
	public Generator(SymbolTables tables, Result res) {
		// initialization
		this.prog = new Program();
		this.threadIDs = new HashMap<>();
		this.checkResult = res;
		this.startFun = new HashMap<>();
		this.endFun = new HashMap<>();
		for (int i = 0; i < tables.getThreads().size(); i++) {
			threadIDs.put(tables.getThreads().get(i), i);
		}
		// set global heap/arp storage locations after all threadIDs
		this.gArp = new Address(Addr.DirAddr, threadIDs.size());
		this.gHeap = new Address(Addr.DirAddr, threadIDs.size() + 1);

		// target to main code start
		Target target = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, target);
		// runtime error handling
		writeString("Runtime error!\n");
		emit(OpCode.EndProg);
		
		Reg h1 = helpReg();

		int start = emit(OpCode.Nop);
		target.setTarget(start);

		// branch main thread from others
		emit(OpCode.Branch, regThreadID, new Target(Tar.Rel, 2));
		Target t0T = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, t0T);

		// loop until thread can go
		int loop = emit(OpCode.Nop);
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Branch, h1, new Target(Tar.Ind, h1));
		emit(OpCode.Jump, new Target(Tar.Abs, loop));

		int t0 = emit(OpCode.Nop);
		t0T.setTarget(t0);
		
		// main thread start, set global arp and heap
		Reg reg = helpReg();
		emit(OpCode.Load, new Address(Addr.ImmValue, threadIDs.size() + 2), reg);
		emit(OpCode.WriteInstr, reg, gArp);
		emit(OpCode.Load, new Address(Addr.ImmValue, 5000), reg);
		emit(OpCode.WriteInstr, reg, gHeap);
	}

	/**
	 * Get generated program resulting from parseTree
	 * @param node parsetree 
	 * @return generated program
	 */
	public Program getProgram(ParseTree node) {
		super.visit(node);
		this.prog.setCores(this.threadIDs.size());
		return this.prog;
	}

	/**
	 * get current threadID
	 * @param node
	 * @return threadID
	 */
	private int threadID(ParseTree node) {
		return threadIDs.get(this.checkResult.getThread(node));
	}

	/**
	 * Add instruction to program
	 * @return line number
	 */
	private int emit(OpCode opcode, Operand... operands) {
		Instr res = new Instr(opcode, operands);
		return prog.addInstr(res);
	}

	/**
	 * Get type of node
	 * @param node
	 * @return data type
	 */
	private Data getData(ParseTree node) {
		return this.checkResult.getType(node);
	}

	/**
	 * get next available register
	 * rotates through 8 registers
	 * @return register 
	 */
	private Reg helpReg() {
		Reg result = regs[regCount];
		this.regCount = (regCount + 1) % regs.length;
		return result;
	}

	/** convenience method to get offset */
	private int offset(ParseTree node) {
		return this.checkResult.getOffset(node);
	}

	/** convenience method to get depth */
	private int depth(ParseTree node) {
		return this.checkResult.getDepth(node);
	}

	/** code generation part */
	
	/**
	 * Use sprockell char output to print a custom string, followed by new line
	 * @param str string to be printed
	 */
	private void writeString(String str) {
		Reg reg = helpReg();
		for (char c : str.toCharArray()) {
			int ord = c;
			emit(OpCode.Load, new Address(Addr.ImmValue, ord), reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.charIO));
		}
	}

	@Override
	public Integer visitProgram(ProgramContext ctx) {
		emit(OpCode.Load, new Address(Addr.ImmValue, 5000), this.heap);
		super.visitProgram(ctx);
		emit(OpCode.EndProg);
		return null;
	}

	@Override
	public Integer visitBasicDef(BasicDefContext ctx) {
		super.visitBasicDef(ctx);
		boolean assign = ctx.ASSIGN() != null;
		boolean global = ctx.GLOBAL() != null;
		String id = ctx.ID().getText();
		int offset = offset(ctx);

		emit(OpCode.Comment, new Str("VarDef - " + id));
		Reg h1 = helpReg();
		if (global) {
			Reg arp = helpReg();
			emit(OpCode.ReadInstr, gArp);
			emit(OpCode.Receive, arp);

			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
			if (assign) {
				Reg reg = helpReg();
				emit(OpCode.Pop, reg);
				emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, h1));
			} else {
				emit(OpCode.WriteInstr, zero, new Address(Addr.IndAddr, h1));
			}
		} else {
			Reg arp = this.arp;
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
			if (assign) {
				Reg reg = helpReg();
				emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
			} else {
				emit(OpCode.Store, zero, new Address(Addr.IndAddr, h1));
			}
		}
		return null;
	}

	@Override
	public Integer visitArrayDef(ArrayDefContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitArrayDef(ctx);

		boolean assign = ctx.ASSIGN() != null;
		boolean global = ctx.GLOBAL() != null;
		int offset = offset(ctx);

		// help registers
		Reg r2 = helpReg();
		Reg reg = helpReg();
		Reg size = helpReg();

		emit(OpCode.Comment, new Str("ArrayDef - allocate"));
		/** ALLOCATION */
		if (global) {
			Reg heap = helpReg();
			emit(OpCode.ReadInstr, gHeap);
			emit(OpCode.Receive, heap);

			Reg arp = helpReg();
			emit(OpCode.ReadInstr, gArp);
			emit(OpCode.Receive, arp);

			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, r2);
			// store allocated address at pointer location
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), arp, reg, reg);

			emit(OpCode.WriteInstr, r2, new Address(Addr.IndAddr, reg));

			// heap increased by size+1
			emit(OpCode.Pop, size);
			// index checking
			emit(OpCode.Compute, new Operator(Op.Lt), size, zero, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Abs, errorJump));

			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);
			emit(OpCode.WriteInstr, heap, gHeap);

			emit(OpCode.Comment, new Str("ArrayDef - store data"));
			if (assign) {
				/** COPY */
				emit(OpCode.WriteInstr, size, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, reg);
				emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			} else {
				/** FILL WITH 0 */
				emit(OpCode.WriteInstr, size, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.WriteInstr, zero, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			}
		} else {
			Reg heap = this.heap;
			Reg arp = this.arp;

			// r2 -> heap address
			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, r2);
			// store allocated address at pointer location
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), arp, reg, reg);
			// store pointer to heap
			emit(OpCode.Store, heap, new Address(Addr.IndAddr, reg));
			// heap increased by size+1
			emit(OpCode.Pop, size);
			// index checking
			emit(OpCode.Compute, new Operator(Op.Lt), size, zero, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Abs, errorJump));

			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);

			emit(OpCode.Comment, new Str("ArrayDef - store data"));
			if (assign) {
				/** COPY */
				emit(OpCode.Store, size, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			} else {
				/** FILL WITH 0 */
				emit(OpCode.Store, size, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Store, zero, new Address(Addr.IndAddr, r2));
				emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			}
		}

		return ret;
	}

	@Override
	public Integer visitFunDef(FunDefContext ctx) {
		int ret = emit(OpCode.Nop);

		String id = ctx.ID().getText();
		boolean returns = ctx.RETURNS() != null;

		Reg reg = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();
		Reg target = helpReg();
		
		Reg size = helpReg();
		Reg val = helpReg();
		Reg h1 = helpReg();

		Target end = new Target(Tar.Abs, 0);
		Target start = new Target(Tar.Abs, 0);
		this.startFun.put(id, start);
		this.endFun.put(id, end);

		// jump past content
		Target pastT = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, pastT);

		emit(OpCode.Comment, new Str("FunDef " + id + " - body"));
		int body = emit(OpCode.Nop);

		/** STORE BREAK ADDRESS */
		emit(OpCode.Jump, new Target(Tar.Rel, 2));
		int breakV = emit(OpCode.Jump, end);
		emit(OpCode.Load, new Address(Addr.ImmValue, breakV), reg);
		emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

		// params and content
		super.visitFunDef(ctx);

		emit(OpCode.Push, zero);
		emit(OpCode.Push, zero);

		int c = emit(OpCode.Nop); 
		emit(OpCode.Comment, new Str("FunDef - end " + id));

		emit(OpCode.Pop, failR);
		emit(OpCode.Pop, retR);

		/** JUMP AWAY */
		Target extract = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, retR, extract);
		emit(OpCode.Pop, target);

		emit(OpCode.Push, retR);
		emit(OpCode.Push, failR);

		emit(OpCode.Jump, new Target(Tar.Ind, target));

		/** EXTRACT */
		int extr = emit(OpCode.Nop);
		extract.setTarget(extr);
		if (returns) {
			Data data = getData(ctx.RETURNS());
			boolean array = data instanceof Arr;
			int retOffset = offset(ctx.funType());

			emit(OpCode.Branch, retR, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, new Target(Tar.Abs, errorJump));
			emit(OpCode.Nop);

			emit(OpCode.Comment, new Str("FunDef - ret value backup"));
			if (array) {
				Reg heap = this.heap;
				/** STORE ALLOCATED POSITION */
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Store, heap, new Address(Addr.IndAddr, reg));

				/** ALLOCATE / COPY */
				emit(OpCode.Pop, size);
				emit(OpCode.Store, size, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);

				Target loopT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, loopT);

				emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));

				int loopV = emit(OpCode.Nop);
				loopT.setTarget(loopV);
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Pop, val);
				emit(OpCode.Store, val, new Address(Addr.IndAddr, reg));
			}

			emit(OpCode.Pop, target);

			if (array) {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), size);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), h1);
				emit(OpCode.Compute, new Operator(Op.Add), size, reg, reg);
				emit(OpCode.Compute, new Operator(Op.Incr), reg, reg, reg);

				Target loopT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, loopT);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));

				int loopV = emit(OpCode.Push, h1);
				loopT.setTarget(loopV);
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
			}
		}
		
		emit(OpCode.Push, retR);
		emit(OpCode.Push, failR);
		emit(OpCode.Jump, new Target(Tar.Ind, target));

		/** STORE TARGETS */
		end.setTarget(c);
		start.setTarget(body);

		int pastV = emit(OpCode.Nop);
		pastT.setTarget(pastV);
		return ret;
	}

	@Override
	public Integer visitParam(ParamContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitParam(ctx);

		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		int offset = offset(ctx);

		Reg arp = this.arp;
		Reg heap = this.heap;
		Reg reg = helpReg();
		Reg h1 = helpReg();
		Reg size = helpReg();

		if (array) {
			/** CORRECT ADDRESS */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
			// store allocated location
			emit(OpCode.Store, heap, new Address(Addr.IndAddr, h1));

			/** COPY ARRAY */
			emit(OpCode.Pop, size);

			emit(OpCode.Store, size, new Address(Addr.IndAddr, heap));
			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);

			Target endT = new Target(Tar.Abs, 0);
			int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, endT);

			emit(OpCode.Pop, reg);
			emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
			emit(OpCode.Jump, new Target(Tar.Abs, loop));

			int end = emit(OpCode.Nop);
			endT.setTarget(end);
		} else {
			/** VAL FROM STACK */
			emit(OpCode.Pop, reg);

			/** CORRECT ADDRESS */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

			/* STORE */
			emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
		}
		return ret;
	}

	@Override
	public Integer visitLockStat(LockStatContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitLockStat(ctx);

		int offset = offset(ctx);
		Reg reg = helpReg();
		Reg arp = helpReg();
		Reg answer = helpReg();

		emit(OpCode.ReadInstr, gArp);
		emit(OpCode.Receive, arp);

		/** GET LOCK ADDRESS */
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

		/** TEST AND SET */
		int loop = emit(OpCode.TestAndSet, new Address(Addr.IndAddr, reg));
		emit(OpCode.Receive, answer);
		emit(OpCode.Compute, new Operator(Op.Equal), answer, zero, answer);
		emit(OpCode.Branch, answer, new Target(Tar.Abs, loop));

		return ret;
	}

	@Override
	public Integer visitUnlockStat(UnlockStatContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitUnlockStat(ctx);

		int offset = offset(ctx);
		Reg reg = helpReg();
		Reg arp = helpReg();

		emit(OpCode.ReadInstr, gArp);
		emit(OpCode.Receive, arp);

		/** GET LOCK ADDRESS */
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

		/** UNLOCK */
		emit(OpCode.WriteInstr, zero, new Address(Addr.IndAddr, reg));

		return ret;
	}

	@Override
	public Integer visitForkStat(ForkStatContext ctx) {
		int ret = emit(OpCode.Nop);

		Reg reg = helpReg();

		/** SIGNAL THRED START */
		Target pastT = new Target(Tar.Abs, 0);
		Target thread = new Target(Tar.Abs, 0);

		emit(OpCode.Jump, new Target(Tar.Rel, 2));
		int tjump = emit(OpCode.Jump, thread);
		emit(OpCode.Load, new Address(Addr.ImmValue, tjump), reg);
		emit(OpCode.WriteInstr, reg, new Address(Addr.DirAddr, threadID(ctx)));
		emit(OpCode.Jump, pastT);

		emit(OpCode.Comment, new Str("Thread - " + threadID(ctx)));
		/** START THREAD LOCATION */
		int t = emit(OpCode.Nop);
		thread.setTarget(t);

		/** RUN THREAD */
		super.visitForkStat(ctx);

		/** SIGNAL THREAD STOP */
		emit(OpCode.WriteInstr, zero, new Address(Addr.IndAddr, regThreadID));

		/** STOP THREAD */
		emit(OpCode.EndProg);

		emit(OpCode.Comment, new Str("Main continue"));
		/** JUMP PAST POINT */
		int main = emit(OpCode.Nop);
		pastT.setTarget(main);

		return ret;
	}

	@Override
	public Integer visitJoinStat(JoinStatContext ctx) {
		int ret = emit(OpCode.Nop);

		Reg reg = helpReg();
		/** LOOP UNTIL THREAD STOPPED */
		emit(OpCode.Comment, new Str("Join - " + threadID(ctx)));
		int loop = emit(OpCode.ReadInstr, new Address(Addr.DirAddr, threadID(ctx)));
		emit(OpCode.Receive, reg);
		emit(OpCode.Compute, new Operator(Op.NEq), reg, zero, reg);
		emit(OpCode.Branch, reg, new Target(Tar.Abs, loop));

		return ret;
	}

	@Override
	public Integer visitCallStat(CallStatContext ctx) {
		int ret = emit(OpCode.Nop);

		String id = ctx.ID().getText();
		Target startFun = this.startFun.get(id);
		Data data = getData(ctx);
		boolean array = data instanceof Arr;

		int incr = offset(ctx); // incr of arp pointer
		Reg reg = helpReg();
		Reg arp = this.arp;
		Reg h1 = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();

		emit(OpCode.Comment, new Str("Function - call " + id));
		/** PUSH & SET ARP */
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));

		/** PUSH RET ADDRESS */
		emit(OpCode.Jump, new Target(Tar.Rel, 2));
		Target con = new Target(Tar.Abs, 0);
		int val = emit(OpCode.Jump, con);

		emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);

		/** SET ARGS */
		if (ctx.args() != null) {
			visit(ctx.args());
		}

		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

		/** JUMP */
		emit(OpCode.Jump, startFun);

		/** FUNCTION RETURN */
		emit(OpCode.Comment, new Str("Function - return " + id));
		int c = emit(OpCode.Nop);
		con.setTarget(c);

		/** RESET ARP */
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), h1);
		emit(OpCode.WriteInstr, h1, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Compute, new Operator(Op.Incr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

		emit(OpCode.Pop, failR);
		emit(OpCode.Push, failR);

		// break jump
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Branch, failR, new Target(Tar.Ind, h1));

		// remove unneeded stack vals
		emit(OpCode.Pop, failR);
		emit(OpCode.Pop, retR);

		Target endT = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, retR, new Target(Tar.Rel, 2));
		emit(OpCode.Jump, endT);

		// remove stack/val
		if (array) {
			emit(OpCode.Pop, failR);

			Target loopT = new Target(Tar.Abs, 0);
			int loop = emit(OpCode.Branch, failR, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, loopT);

			emit(OpCode.Pop, retR);
			emit(OpCode.Compute, new Operator(Op.Decr), failR, failR, failR);
			emit(OpCode.Jump, new Target(Tar.Abs, loop));

			int loopV = emit(OpCode.Nop);
			loopT.setTarget(loopV);
		} else {
			emit(OpCode.Pop, failR);
		}

		int end = emit(OpCode.Nop);
		endT.setTarget(end);

		return ret;
	}

	@Override
	public Integer visitPointerStat(PointerStatContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitPointerStat(ctx);

		int offset = offset(ctx);
		String id = ctx.ID().getText();
		Reg reg = helpReg();
		Reg arp = this.arp;
		Reg h1 = helpReg();

		emit(OpCode.Comment, new Str("Pointer creation - " + id));

		/** GET SCOPE AND OFFSET */
		emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), h1, reg, h1);

		emit(OpCode.Pop, reg);
		emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
		return ret;
	}

	@Override
	public Integer visitIdPointer(IdPointerContext ctx) {
		int ret = emit(OpCode.Nop);

		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		int offset = offset(ctx);
		int depth = depth(ctx);

		Reg reg = helpReg();
		Reg h1 = helpReg();

		emit(OpCode.Comment, new Str("ID pointer target"));
		Reg arp = this.arp;

		/** GET SCOPE AND OFFSET */
		emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
		for (int i = 0; i < depth; i++) {
			emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
		}
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), h1, reg, h1);

		if (pointer) {
			while (point instanceof Pointer) {
				point = ((Pointer) point).target();
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
		}

		emit(OpCode.Push, h1);

		return ret;
	}

	@Override
	public Integer visitIndexPointer(IndexPointerContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitIndexPointer(ctx);

		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		int offset = offset(ctx);
		int depth = depth(ctx);

		Reg h1 = helpReg();
		Reg index = helpReg();
		Reg reg = helpReg();
		Reg size = helpReg();

		Reg arp = this.arp;

		emit(OpCode.Comment, new Str("Index pointer target"));
		/** GET SCOPE AND OFFSET */
		emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
		for (int i = 0; i < depth; i++) {
			emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
		}
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), h1, reg, h1);

		if (pointer) {
			while (point instanceof Pointer) {
				point = ((Pointer) point).target();
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
		}

		// load index pointer
		emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);

		// load size
		emit(OpCode.Load, new Address(Addr.IndAddr, h1), size);
		// add index +1 (size storage)
		emit(OpCode.Pop, index);

		// Check index
		emit(OpCode.Compute, new Operator(Op.GtE), index, size, size);
		emit(OpCode.Branch, size, new Target(Tar.Abs, this.errorJump));
		emit(OpCode.Compute, new Operator(Op.Lt), index, zero, size);
		emit(OpCode.Branch, size, new Target(Tar.Abs, errorJump));

		emit(OpCode.Compute, new Operator(Op.Add), h1, index, h1);
		emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

		/** LOAD VALUE */
		emit(OpCode.Push, h1);
		return ret;
	}

	@Override
	public Integer visitPrintStat(PrintStatContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitPrintStat(ctx);

		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		boolean ch;
		if (array) {
			ch = ((Arr) data).elem() == Data.CHAR;
		} else {
			ch = data.equals(Data.CHAR);
		}

		emit(OpCode.Comment, new Str("Print: " + data.toString()));
		if (array) {
			Reg size = helpReg();
			Reg val = helpReg();
			if (ch) {
				emit(OpCode.Pop, size);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, val);
				emit(OpCode.WriteInstr, val, new Address(Addr.charIO));
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);

				emit(OpCode.Load, new Address(Addr.ImmValue, 10), val);
				emit(OpCode.WriteInstr, val, new Address(Addr.charIO));
			} else {
				emit(OpCode.Pop, size);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, val);
				emit(OpCode.WriteInstr, val, new Address(Addr.numberIO));
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			}
		} else {
			Reg val = helpReg();
			if (ch) {
				emit(OpCode.Pop, val);
				emit(OpCode.WriteInstr, val, new Address(Addr.charIO));

				emit(OpCode.Load, new Address(Addr.ImmValue, 10), val);
				emit(OpCode.WriteInstr, val, new Address(Addr.charIO));
			} else {
				emit(OpCode.Pop, val);
				emit(OpCode.WriteInstr, val, new Address(Addr.numberIO));
			}
		}

		return ret;
	}

	@Override
	public Integer visitFailStat(FailStatContext ctx) {
		int ret = emit(OpCode.Nop);

		Reg reg = helpReg();
		emit(OpCode.Comment, new Str("Fail"));
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Push, zero);
		emit(OpCode.Push, reg);

		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, reg);
		emit(OpCode.Jump, new Target(Tar.Ind, reg));
		return ret;
	}

	@Override
	public Integer visitReturnStat(ReturnStatContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitReturnStat(ctx);

		Reg reg = helpReg();
		emit(OpCode.Comment, new Str("Return"));
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Push, reg);
		emit(OpCode.Push, zero);

		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, reg);
		emit(OpCode.Jump, new Target(Tar.Ind, reg));
		return ret;
	}

	@Override
	public Integer visitBlock(BlockContext ctx) {
		int ret = emit(OpCode.Nop);

		boolean fin = ctx.FINALLY() != null;
		boolean cat = ctx.CATCH() != null;
		boolean array = getData(ctx) instanceof Arr;
		int offset = offset(ctx);
		int retOffset = offset(ctx.FINALLY());

		Target endT = new Target(Tar.Abs, 0);
		Target catT = new Target(Tar.Abs, 0);
		Target finT = new Target(Tar.Abs, 0);

		Target endTFail = new Target(Tar.Abs, 0); // target if still failed
		Target endTRet = new Target(Tar.Abs, 0); // target if still returns

		Reg arp = this.arp;

		Reg reg = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();
		Reg size = helpReg();
		Reg val = helpReg();
		Reg h1 = helpReg();
		Reg num = helpReg();

		emit(OpCode.Branch, this.heap, new Target(Tar.Rel, 2));
		emit(OpCode.Load, new Address(Addr.ImmValue, 5000), this.heap);
		emit(OpCode.Nop);

		emit(OpCode.Comment, new Str("Block"));

		/** GET NEW ARP VAL */
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

		/** STORE SCOPE ADDRESS */
		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));

		/** SET NEW ARP */
		emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

		emit(OpCode.Jump, new Target(Tar.Rel, 2));
		Target retT = new Target(Tar.Abs, 0);
		int breakV = emit(OpCode.Jump, retT);
		emit(OpCode.Load, new Address(Addr.ImmValue, breakV), reg);
		emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

		visit(ctx.content(0));

		emit(OpCode.Comment, new Str("Block - end"));
		emit(OpCode.Push, zero);
		emit(OpCode.Push, zero);

		int retV = emit(OpCode.Nop);
		retT.setTarget(retV);

		/** RESET ARP */
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), h1);
		emit(OpCode.WriteInstr, h1, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Compute, new Operator(Op.Incr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

		/** DEAL WITH CATCH / FINALLY */
		emit(OpCode.Pop, failR);
		emit(OpCode.Pop, retR);

		emit(OpCode.Branch, failR, catT); // jump if fail
		emit(OpCode.Branch, retR, finT); // jump if return
		emit(OpCode.Jump, finT);

		if (cat) {
			emit(OpCode.Comment, new Str("Block - catch"));

			int catV = emit(OpCode.Nop);
			catT.setTarget(catV);
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), failR);

			/** GET NEW ARP VAL */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

			/** STORE SCOPE ADDRESS */
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
			emit(OpCode.Receive, h1);
			emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, retR, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, failR, new Address(Addr.IndAddr, reg));

			/** SET NEW ARP */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

			Target retTCat = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, new Target(Tar.Rel, 2));
			int breakVRet = emit(OpCode.Jump, retTCat);
			emit(OpCode.Load, new Address(Addr.ImmValue, breakVRet), reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

			visit(ctx.content(1));

			emit(OpCode.Push, zero);
			emit(OpCode.Push, zero);

			int retVCat = emit(OpCode.Nop);
			retTCat.setTarget(retVCat);

			/** RESET ARP */
			emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
			emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

			emit(OpCode.Comment, new Str("Block - catch end"));

			/** RESULT EVAL */
			emit(OpCode.Pop, failR);
			emit(OpCode.Pop, retR);

			emit(OpCode.Jump, finT);
		} // end catch

		if (fin) {
			int finV = emit(OpCode.Nop);
			finT.setTarget(finV);
			if (!cat) {
				catT.setTarget(finV);
			}

			Target pastBackupT = new Target(Tar.Abs, 0);
			emit(OpCode.Branch, retR, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, pastBackupT);

			/** IF BACKUP NEEDED */
			emit(OpCode.Comment, new Str("Block - ret value backup"));
			if (array) {
				Reg heap = this.heap;
				/** STORE ALLOCATED POSITION */
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Store, heap, new Address(Addr.IndAddr, reg));

				/** ALLOCATE / COPY */
				emit(OpCode.Pop, size);
				emit(OpCode.Store, size, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);

				Target loopT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, loopT);

				emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));

				int loopV = emit(OpCode.Nop);
				loopT.setTarget(loopV);
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Pop, val);
				emit(OpCode.Store, val, new Address(Addr.IndAddr, reg));
			}
			/** SET RETURN FLAG TO 0 */
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), retR);
			emit(OpCode.Comment, new Str("Block - finally"));

			/** GET NEW ARP VAL */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

			/** STORE SCOPE ADDRESS */
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
			emit(OpCode.Receive, h1);
			emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, retR, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, failR, new Address(Addr.IndAddr, reg));

			/** SET NEW ARP */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

			Target retTFin = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, new Target(Tar.Rel, 2));
			int breakVFin = emit(OpCode.Jump, retTFin);
			emit(OpCode.Load, new Address(Addr.ImmValue, breakVFin), reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

			if (cat) {
				visit(ctx.content(2));
			} else {
				visit(ctx.content(1));
			}

			emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, num);

			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), h1);
			emit(OpCode.WriteInstr, h1, new Address(Addr.IndAddr, regThreadID));

			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), retR);
			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), failR);

			emit(OpCode.Push, retR);
			emit(OpCode.Push, failR);

			emit(OpCode.Comment, new Str("Block - finally end"));
			int retVFin = emit(OpCode.Nop);
			retTFin.setTarget(retVFin);

			/** RESET ARP */
			emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
			emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

			emit(OpCode.Pop, failR);
			emit(OpCode.Pop, retR);

			Target pastRetrieveT = new Target(Tar.Abs, 0);
			emit(OpCode.Branch, retR, pastRetrieveT);

			/** COPY BACKUP BACK TO STACK */
			emit(OpCode.Comment, new Str("Block - retrieve backup"));
			if (array) {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), size);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), h1);
				emit(OpCode.Compute, new Operator(Op.Add), size, reg, reg);
				emit(OpCode.Compute, new Operator(Op.Incr), reg, reg, reg);

				Target loopT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, loopT);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));

				int loopV = emit(OpCode.Push, h1);
				loopT.setTarget(loopV);
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
			}
			emit(OpCode.Load, new Address(Addr.ImmValue, 1), retR);

			Target pastFin2T = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, pastFin2T);

			int pastBackupV = emit(OpCode.Nop);
			pastBackupT.setTarget(pastBackupV);

			/** BLOCK IF NO BACKUP */
			emit(OpCode.Comment, new Str("Block - finally"));

			/** GET NEW ARP VAL */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

			/** STORE SCOPE ADDRESS */
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
			emit(OpCode.Receive, h1);
			emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, retR, new Address(Addr.IndAddr, reg));
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Store, failR, new Address(Addr.IndAddr, reg));

			/** SET NEW ARP */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

			Target retTFin2 = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, new Target(Tar.Rel, 2));
			int breakVFin2 = emit(OpCode.Jump, retTFin2);
			emit(OpCode.Load, new Address(Addr.ImmValue, breakVFin2), reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

			if (cat) {
				visit(ctx.content(2));
			} else {
				visit(ctx.content(1));
			}

			emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, num);

			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), h1);
			emit(OpCode.WriteInstr, h1, new Address(Addr.IndAddr, regThreadID));

			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), retR);
			emit(OpCode.Compute, new Operator(Op.Decr), num, num, num);
			emit(OpCode.Load, new Address(Addr.IndAddr, num), failR);

			emit(OpCode.Push, retR);
			emit(OpCode.Push, failR);

			emit(OpCode.Comment, new Str("Block - finally end"));
			int retVFin2 = emit(OpCode.Nop);
			retTFin2.setTarget(retVFin2);

			/** RESET ARP */
			emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
			emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

			emit(OpCode.Pop, failR);
			emit(OpCode.Pop, retR);

			int pastFin2V = emit(OpCode.Nop);
			pastFin2T.setTarget(pastFin2V);
			pastRetrieveT.setTarget(pastFin2V);
		}

		int catV = emit(OpCode.Nop);
		if (!cat) {
			catT.setTarget(catV);
		}
		if (!fin) {
			finT.setTarget(catV);
		}

		emit(OpCode.Comment, new Str("Block - end"));

		emit(OpCode.Branch, retR, endTRet);
		emit(OpCode.Branch, failR, endTFail);
		emit(OpCode.Jump, endT);

		int jumpVRet = emit(OpCode.Nop);
		endTRet.setTarget(jumpVRet);
		emit(OpCode.Push, retR);
		emit(OpCode.Push, zero);

		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, reg);
		emit(OpCode.Jump, new Target(Tar.Ind, reg));

		int jumpVFail = emit(OpCode.Nop);
		endTFail.setTarget(jumpVFail);
		emit(OpCode.Push, retR);
		emit(OpCode.Push, failR);
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, reg);
		emit(OpCode.Jump, new Target(Tar.Ind, reg));

		int endV = emit(OpCode.Nop);
		endT.setTarget(endV);
		return ret;
	}

	@Override
	public Integer visitBoolOpExpr(BoolOpExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitBoolOpExpr(ctx);

		Reg r1 = helpReg();
		Reg r2 = helpReg();
		Reg reg = helpReg();

		Operator op;
		if (ctx.boolOp().AND() != null) {
			op = new Operator(Op.And);
		} else {
			op = new Operator(Op.Or);
		}
		emit(OpCode.Pop, r1);
		emit(OpCode.Pop, r2);
		emit(OpCode.Compute, op, r2, r1, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitIntOpExpr(IntOpExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitIntOpExpr(ctx);

		Reg r1 = helpReg();
		Reg r2 = helpReg();
		Reg reg = helpReg();

		Operator op;
		if (ctx.intOp().PLUS() != null) {
			op = new Operator(Op.Add);
		} else if (ctx.intOp().MINUS() != null) {
			op = new Operator(Op.Sub);
		} else {
			op = new Operator(Op.Mul);
		}
		emit(OpCode.Pop, r1);
		emit(OpCode.Pop, r2);
		emit(OpCode.Compute, op, r2, r1, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitCompOpExpr(CompOpExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitCompOpExpr(ctx);

		Data data = getData(ctx.expr(0));
		boolean array = data instanceof Arr;

		Reg r1 = helpReg();
		Reg r2 = helpReg();
		Reg r3 = helpReg();
		Reg reg = helpReg();

		Operator op;
		if (ctx.compOp().EQUALS() != null) {
			op = new Operator(Op.Equal);
		} else if (ctx.compOp().GREATER() != null) {
			op = new Operator(Op.Gt);
		} else if (ctx.compOp().SMALLER() != null) {
			op = new Operator(Op.Lt);
		} else {
			op = new Operator(Op.Equal);
		}

		if (array) {
			Reg heap = this.heap;
			Reg h1 = helpReg();
			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, h1);
			// size
			emit(OpCode.Pop, reg);
			emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

			Target pastT = new Target(Tar.Abs, 0);
			int loop1 = emit(OpCode.Branch, reg, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, pastT);

			emit(OpCode.Pop, r1);
			emit(OpCode.Store, r1, new Address(Addr.IndAddr, h1));
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
			emit(OpCode.Jump, new Target(Tar.Abs, loop1));
			int pastV = emit(OpCode.Nop);
			pastT.setTarget(pastV);

			// reset heap pointer
			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, h1);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.Pop, r1);

			Target falseT = new Target(Tar.Abs, 0);
			emit(OpCode.Compute, new Operator(Op.NEq), reg, r1, reg);
			emit(OpCode.Branch, reg, falseT);

			// flag
			emit(OpCode.Load, new Address(Addr.ImmValue, 1), r2);

			Target endT = new Target(Tar.Abs, 0);
			int loop2 = emit(OpCode.Branch, r1, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, endT);

			emit(OpCode.Pop, reg);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), r3);
			emit(OpCode.Compute, op, reg, r3, r3);
			emit(OpCode.Compute, new Operator(Op.And), r2, r3, r2);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.Compute, new Operator(Op.Decr), r1, r1, r1);
			emit(OpCode.Jump, new Target(Tar.Abs, loop2));

			int falseV = emit(OpCode.Nop);
			falseT.setTarget(falseV);

			emit(OpCode.Load, new Address(Addr.ImmValue, 0), r2);
			// clean array
			int loop3 = emit(OpCode.Branch, r1, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, endT);

			emit(OpCode.Pop, reg);
			emit(OpCode.Compute, new Operator(Op.Decr), r1, r1, r1);
			emit(OpCode.Jump, new Target(Tar.Abs, loop3));

			int endV = emit(OpCode.Nop);
			endT.setTarget(endV);

			emit(OpCode.Push, r2);

		} else {
			emit(OpCode.Pop, r1);
			emit(OpCode.Pop, r2);
			emit(OpCode.Compute, op, r2, r1, reg);
			emit(OpCode.Push, reg);
		}
		
		if (ctx.compOp().UNEQUALS() != null) {
			emit(OpCode.Pop, r1);
			emit(OpCode.Load, new Address(Addr.ImmValue,1), r2);
			emit(OpCode.Compute, new Operator(Op.Xor), r2, r1, reg);
			emit(OpCode.Push, reg);
		}
		return ret;
	}

	@Override
	public Integer visitSizeExpr(SizeExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitSizeExpr(ctx);

		Reg reg = helpReg();
		Reg index = helpReg();
		Reg comp = helpReg();

		/** GET SIZE */
		emit(OpCode.Pop, reg);

		/** REMOVE ELEMENTS FROM STACK */
		emit(OpCode.Compute, new Operator(Op.Add), zero, zero, index);
		int start = emit(OpCode.Compute, new Operator(Op.GtE), index, reg, comp);
		Target tar = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, comp, tar);
		emit(OpCode.Pop, comp);
		emit(OpCode.Compute, new Operator(Op.Incr), index, index, index);
		emit(OpCode.Jump, new Target(Tar.Abs, start));
		int end = emit(OpCode.Nop);
		tar.setTarget(end);

		/** STORE SIZE ON STACK */
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitReadExpr(ReadExprContext ctx) {
		int ret = emit(OpCode.Nop);

		Data data = getData(ctx);

		Reg reg = helpReg();
		Reg newLine = helpReg();
		if (data instanceof Data.Char) {
			emit(OpCode.Load, new Address(Addr.ImmValue, 10), newLine);

			writeString("input character: ");

			emit(OpCode.ReadInstr, new Address(Addr.charIO));
			emit(OpCode.Receive, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, new Target(Tar.Rel, (-3)));
			emit(OpCode.Push, reg);

			int loop = emit(OpCode.ReadInstr, new Address(Addr.charIO));
			emit(OpCode.Receive, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, new Target(Tar.Rel, (-3)));
			emit(OpCode.Compute, new Operator(Op.Equal), reg, newLine, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Rel, 2));
			emit(OpCode.Jump, new Target(Tar.Abs, loop));

		} else if (data instanceof Data.Bool) {
			emit(OpCode.ReadInstr, new Address(Addr.numberIO));
			emit(OpCode.Receive, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Rel, 3));
			emit(OpCode.Push, reg);
			Target pastT = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, pastT);
			emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
			emit(OpCode.Push, reg);
			int past = emit(OpCode.Nop);
			pastT.setTarget(past);
		} else {
			emit(OpCode.ReadInstr, new Address(Addr.numberIO));
			emit(OpCode.Receive, reg);
			emit(OpCode.Push, reg);
		}

		return ret;
	}

	@Override
	public Integer visitCallExpr(CallExprContext ctx) {
		int ret = emit(OpCode.Nop);

		String id = ctx.ID().getText();
		Target startFun = this.startFun.get(id);

		int incr = offset(ctx); // incr of arp pointer
		Reg reg = helpReg();
		Reg arp = this.arp;
		Reg h1 = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();

		emit(OpCode.Comment, new Str("Function - call " + id));

		/** PUSH & SET ARP */
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

		// backup old arp
		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.Store, arp, new Address(Addr.IndAddr, reg));
		// backup old break target
		emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Store, h1, new Address(Addr.IndAddr, reg));

		/** PUSH RET ADDRESS */
		emit(OpCode.Jump, new Target(Tar.Rel, 2));
		Target con = new Target(Tar.Abs, 0);
		int val = emit(OpCode.Jump, con);

		emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);

		/** SET ARGS */
		if (ctx.args() != null) {
			visit(ctx.args());
		}

		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

		/** JUMP */
		emit(OpCode.Jump, startFun);

		/** FUNCTION RETURN */
		emit(OpCode.Comment, new Str("Function - return " + id));
		int c = emit(OpCode.Nop);
		con.setTarget(c);
		
		emit(OpCode.Pop, failR);
		emit(OpCode.Push, failR);

		/** RESET ARP */
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), h1);
		emit(OpCode.WriteInstr, h1, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Compute, new Operator(Op.Incr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

		// break jump
		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Branch, failR, new Target(Tar.Ind, h1));

		// remove unneeded stack vals
		emit(OpCode.Pop, failR);
		emit(OpCode.Pop, retR);

		emit(OpCode.Branch, retR, new Target(Tar.Rel, 2));
		emit(OpCode.Jump, new Target(Tar.Abs, errorJump));
		emit(OpCode.Nop);
		return ret;
	}

	@Override
	public Integer visitArgs(ArgsContext ctx) {
		int ret = emit(OpCode.Nop);

		for (int i = ctx.expr().size() - 1; i >= 0; i--) {
			visit(ctx.expr(i));
		}

		return ret;
	}

	@Override
	public Integer visitIndexExpr(IndexExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitIndexExpr(ctx);

		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		int offset = offset(ctx);
		int depth = depth(ctx);
		boolean global = ctx.GLOBAL() != null;

		Reg rindex = helpReg();
		Reg reg = helpReg();
		Reg h1 = helpReg();

		if (global) {
			/** COMPUTING ARRAY ADDRESS */
			Reg arp = helpReg();
			emit(OpCode.ReadInstr, gArp);
			emit(OpCode.Receive, arp);

			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
					emit(OpCode.Receive, h1);
				}
			}

			// get heap location
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, h1);

			/** BOUNDS CHECKING */
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg);
			emit(OpCode.Pop, rindex);
			emit(OpCode.Compute, new Operator(Op.GtE), rindex, reg, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Abs, errorJump));
			emit(OpCode.Compute, new Operator(Op.Lt), rindex, zero, reg);
			emit(OpCode.Branch, reg, new Target(Tar.Abs, errorJump));

			/** LOADING VALUE */
			emit(OpCode.Compute, new Operator(Op.Add), h1, rindex, h1);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg);
			emit(OpCode.Push, reg);
		} else {
			/** COMPUTING ARRAY ADDRESS */
			Reg arp = this.arp;
			// get correct scope
			emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
			for (int i = 0; i < depth; i++) {
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
			// add offset
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, h1, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
			}

			// load heap location
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);

			/** CHECKING INDEX BOUNDS */
			Reg comp = helpReg();
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
			emit(OpCode.Pop, rindex);
			emit(OpCode.Compute, new Operator(Op.LtE), reg, rindex, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, errorJump));
			emit(OpCode.Compute, new Operator(Op.Lt), rindex, zero, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, errorJump));

			/** LOADING VALUE */
			emit(OpCode.Compute, new Operator(Op.Add), h1, rindex, h1);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
			emit(OpCode.Push, reg);
		}
		return ret;
	}

	@Override
	public Integer visitIdExpr(IdExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitIdExpr(ctx);
		Data data = getData(ctx);
		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		boolean array = data instanceof Arr;
		boolean global = ctx.GLOBAL() != null;
		int offset = offset(ctx);
		int depth = depth(ctx);

		Reg reg = helpReg();
		Reg h1 = helpReg();
		Reg addr = helpReg();

		emit(OpCode.Comment, new Str("Load ID - " + ctx.ID().getText()));
		if (array) {
			if (global) {
				Reg arp = helpReg();
				emit(OpCode.ReadInstr, gArp);
				emit(OpCode.Receive, arp);
				/** CORRECT ADDRESS */
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

				if (pointer) {
					while (point instanceof Pointer) {
						point = ((Pointer) point).target();
						emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
						emit(OpCode.Receive, h1);
					}
				}
				// get heap location
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);

				/** ON STACK */
				Reg size = helpReg();
				// load size
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, reg));
				emit(OpCode.Receive, size);

				emit(OpCode.Compute, new Operator(Op.Add), size, zero, h1);
				emit(OpCode.Compute, new Operator(Op.Add), size, reg, reg);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, reg));
				emit(OpCode.Receive, addr);
				emit(OpCode.Push, addr);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Push, h1);
				endT.setTarget(endV);

			} else {
				/** CORRECT ADDRESS */
				Reg arp = this.arp;
				emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
				for (int i = 0; i < depth; i++) {
					emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, reg);

				if (pointer) {
					while (point instanceof Pointer) {
						point = ((Pointer) point).target();
						emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);
					}
				}
				// load heap location
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				/** BACKWARDS ON STACK */
				Reg size = helpReg();

				// load size
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), size);
				emit(OpCode.Compute, new Operator(Op.Add), reg, size, reg);
				emit(OpCode.Compute, new Operator(Op.Add), size, zero, h1);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), addr);
				emit(OpCode.Push, addr);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Push, h1);
				endT.setTarget(endV);
			}
		} else { // no array
			if (global) {
				/** CORRECT ADDRESS */
				Reg arp = helpReg();
				emit(OpCode.ReadInstr, gArp);
				emit(OpCode.Receive, arp);

				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

				if (pointer) {
					while (point instanceof Pointer) {
						point = ((Pointer) point).target();
						emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
						emit(OpCode.Receive, h1);
					}
				}

				// load value
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);

				/** ON STACK */
				emit(OpCode.Push, reg);
			} else {
				/** CORRECT ADDRESS */
				Reg arp = this.arp;
				emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
				for (int i = 0; i < depth; i++) {
					emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, reg);

				if (pointer) {
					while (point instanceof Pointer) {
						point = ((Pointer) point).target();
						emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);
					}
				}

				// load value
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				/** ON STACK */
				emit(OpCode.Push, reg);
			}
		}
		return ret;
	}

	@Override
	public Integer visitNumExpr(NumExprContext ctx) {
		int ret = emit(OpCode.Nop);
		int val = Integer.parseInt(ctx.NUM().getText());
		emit(OpCode.Comment, new Str("Num - " + val));

		if (ctx.NEGATIVE() != null) {
			val *= -1;
		}
		Reg reg = helpReg();
		emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitCharExpr(CharExprContext ctx) {
		int val = ctx.CHAR().getText().charAt(1);
		Reg reg = helpReg();

		emit(OpCode.Comment, new Str("char - " + val));
		int ret = emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitStrExpr(StrExprContext ctx) {
		int ret = emit(OpCode.Nop);
		String str = ctx.STR().getText();
		str = str.substring(1, str.length() - 1);
		int size = str.length();
		Reg reg = helpReg();

		emit(OpCode.Comment, new Str("Str - " + ctx.STR().getText()));
		/** PUT ON STACK */
		for (int i = str.length(); i > 0; i--) {
			int c = str.charAt(i - 1);
			emit(OpCode.Load, new Address(Addr.ImmValue, (int) c), reg);
			emit(OpCode.Push, reg);
		}
		emit(OpCode.Load, new Address(Addr.ImmValue, size), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitTrueExpr(TrueExprContext ctx) {
		int ret = emit(OpCode.Nop);
		Reg reg = helpReg();

		emit(OpCode.Comment, new Str("boolean - " + true));
		Address adr = new Address(Addr.ImmValue, Program.TRUE_VAL);
		emit(OpCode.Load, adr, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitFalseExpr(FalseExprContext ctx) {
		int ret = emit(OpCode.Nop);
		Reg reg = helpReg();

		emit(OpCode.Comment, new Str("char - " + false));
		Address adr = new Address(Addr.ImmValue, Program.FALSE_VAL);
		emit(OpCode.Load, adr, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitNotExpr(NotExprContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitNotExpr(ctx);
		Reg r1 = helpReg();
		Reg reg = helpReg();

		emit(OpCode.Pop, reg);
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), r1);
		emit(OpCode.Compute, new Operator(Op.Xor), r1, reg, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitArray(ArrayContext ctx) {
		int ret = emit(OpCode.Nop);

		if (ctx.expr() != null) {
			/** PUSH ELEMENTS IN INVERTED ORDER */
			for (int i = ctx.expr().size() - 1; i >= 0; i--) {
				visit(ctx.expr(i));
			}

			Reg reg = helpReg();
			/** PUSH SIZE */
			int size = ctx.expr().size();
			emit(OpCode.Load, new Address(Addr.ImmValue, size), reg);
			emit(OpCode.Push, reg);
		} else {
			emit(OpCode.Push, zero);
		}
		return ret;
	}

	@Override
	public Integer visitIdTarget(IdTargetContext ctx) {
		int ret = emit(OpCode.Nop);

		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		boolean global = ctx.GLOBAL() != null;
		int offset = offset(ctx);
		int depth = depth(ctx);

		Reg reg = helpReg();
		Reg h1 = helpReg();

		emit(OpCode.Comment, new Str("ID Target"));
		if (global) {
			Reg arp = helpReg();
			emit(OpCode.ReadInstr, gArp);
			emit(OpCode.Receive, arp);

			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
					emit(OpCode.Receive, h1);
				}
			}

			if (array) {
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, h1);

				Reg size = helpReg();
				emit(OpCode.Pop, size);

				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);
				emit(OpCode.Compute, new Operator(Op.NEq), reg, size, reg);
				emit(OpCode.Branch, reg, new Target(Tar.Abs, this.errorJump));

				emit(OpCode.WriteInstr, size, new Address(Addr.IndAddr, h1));
				emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, reg);
				emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, h1));
				emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			} else {
				emit(OpCode.Pop, reg);
				emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, h1));
			}
		} else { // local
			Reg arp = this.arp;

			/** GET SCOPE AND OFFSET */
			emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
			for (int i = 0; i < depth; i++) {
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), h1, reg, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
			}

			if (array) {
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);

				Reg size = helpReg();
				// CHECK SIZE
				emit(OpCode.Pop, size);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
				emit(OpCode.Compute, new Operator(Op.NEq), reg, size, reg);
				emit(OpCode.Branch, reg, new Target(Tar.Abs, this.errorJump));

				emit(OpCode.Store, size, new Address(Addr.IndAddr, h1));
				emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

				Target endT = new Target(Tar.Abs, 0);
				int loop = emit(OpCode.Branch, size, new Target(Tar.Rel, 2));
				emit(OpCode.Jump, endT);

				emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
				emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Jump, new Target(Tar.Abs, loop));
				int endV = emit(OpCode.Nop);
				endT.setTarget(endV);
			} else {
				/** LOAD VALUE */
				emit(OpCode.Pop, reg);

				emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
			}
		}

		return ret;
	}

	@Override
	public Integer visitArrayTarget(ArrayTargetContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitArrayTarget(ctx);

		Data point = getData(ctx.ID());
		boolean pointer = point != null;
		boolean global = ctx.GLOBAL() != null;
		int offset = offset(ctx);
		int depth = depth(ctx);

		Reg h1 = helpReg();
		Reg index = helpReg();
		Reg reg = helpReg();
		Reg size = helpReg();
		if (global) {
			Reg arp = helpReg();
			emit(OpCode.ReadInstr, gArp);
			emit(OpCode.Receive, arp);

			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
					emit(OpCode.Receive, h1);
				}
			}

			// get heap location
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, h1);

			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, size);

			// add index +1 (size storage)
			emit(OpCode.Pop, index);

			// Check index
			emit(OpCode.Compute, new Operator(Op.GtE), index, size, size);
			emit(OpCode.Branch, size, new Target(Tar.Abs, this.errorJump));
			emit(OpCode.Compute, new Operator(Op.Lt), index, zero, size);
			emit(OpCode.Branch, size, new Target(Tar.Abs, errorJump));

			emit(OpCode.Compute, new Operator(Op.Add), h1, index, h1);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

			/** LOAD VALUE */
			emit(OpCode.Pop, reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, h1));
		} else {
			Reg arp = this.arp;

			/** GET SCOPE AND OFFSET */
			emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
			for (int i = 0; i < depth; i++) {
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), h1, reg, h1);

			if (pointer) {
				while (point instanceof Pointer) {
					point = ((Pointer) point).target();
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
			}
			// load heap location
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);

			emit(OpCode.Load, new Address(Addr.IndAddr, h1), size);
			// add index +1 (size storage)
			emit(OpCode.Pop, index);

			// Check index
			emit(OpCode.Compute, new Operator(Op.GtE), index, size, size);
			emit(OpCode.Branch, size, new Target(Tar.Abs, this.errorJump));
			emit(OpCode.Compute, new Operator(Op.Lt), index, zero, size);
			emit(OpCode.Branch, size, new Target(Tar.Abs, errorJump));

			emit(OpCode.Compute, new Operator(Op.Add), h1, index, h1);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);

			/** LOAD VALUE */
			emit(OpCode.Pop, reg);
			emit(OpCode.Store, reg, new Address(Addr.IndAddr, h1));
		}
		return ret;
	}

	@Override
	public Integer visitIfstat(IfstatContext ctx) {
		int ret = emit(OpCode.Nop);
		Reg cond = helpReg();
		Reg reg = helpReg();

		emit(OpCode.Comment, new Str("If - condition"));
		visit(ctx.expr());
		emit(OpCode.Pop, cond);

		emit(OpCode.Comment, new Str("If"));
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Compute, new Operator(Op.Xor), cond, reg, cond);
		Target iftarget = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, cond, iftarget);
		visit(ctx.block(0));
		if (ctx.ELSE() != null) {
			Target endtarget = new Target(Tar.Abs, 0);
			emit(OpCode.Jump, endtarget);
			int line = visit(ctx.block(1));
			iftarget.setTarget(line);
			int end = emit(OpCode.Nop);
			endtarget.setTarget(end);
		} else {
			int end = emit(OpCode.Nop);
			iftarget.setTarget(end);
		}
		return ret;
	}

	@Override
	public Integer visitWhileStat(WhileStatContext ctx) {
		int ret = emit(OpCode.Nop);
		Reg cond = helpReg();

		emit(OpCode.Comment, new Str("While"));
		Target endT = new Target(Tar.Abs, 0);

		emit(OpCode.Comment, new Str("While - condition"));
		int loop = emit(OpCode.Nop);
		visit(ctx.expr());
		emit(OpCode.Pop, cond);
		emit(OpCode.Branch, cond, new Target(Tar.Rel, 2));
		emit(OpCode.Jump, endT);
		emit(OpCode.Nop);
		visit(ctx.block());
		emit(OpCode.Jump, new Target(Tar.Abs, loop));
		int end = emit(OpCode.Nop);
		endT.setTarget(end);
		return ret;
	}

	@Override
	public Integer visitAssignStat(AssignStatContext ctx) {
		int ret = emit(OpCode.Nop);
		visit(ctx.expr());
		visit(ctx.target());
		return ret;
	}
}
