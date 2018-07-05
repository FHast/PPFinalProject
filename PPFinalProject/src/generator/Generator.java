package generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.tree.ParseTree;

import checker.Result;
import generator.Address.Addr;
import generator.Operator.Op;
import generator.Target.Tar;
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
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IndexExprContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.JoinStatContext;
import grammar.SycoraxParser.LockStatContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParamContext;
import grammar.SycoraxParser.PrintStatContext;
import grammar.SycoraxParser.ProgramContext;
import grammar.SycoraxParser.ReturnStatContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.StrExprContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.UnlockStatContext;
import grammar.SycoraxParser.WhileStatContext;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.SymbolTables;

public class Generator extends SycoraxBaseVisitor<Integer> {

	private Program prog;

	private Result checkResult;

	private int regCount = 0;

	private Reg zero = new Reg("reg0");
	private Reg arp = new Reg("regArp");
	private Reg heap = new Reg("regHeap");
	private Reg regThreadID = new Reg("regSprID");
	private Reg[] regs = { new Reg("regA"), new Reg("regB"), new Reg("regC"), new Reg("regD"), new Reg("regE"),
			new Reg("regF"), new Reg("regG"), new Reg("regH") };

	private Map<String, Reg> heaps;
	private Map<String, Reg> arps;

	private Map<String, Target> startFun;
	private Map<String, Target> endFun;

	private Map<String, Stack<Target>> breaks;

	private Map<String, Integer> threadIDs;
	private Address gArp;
	private Address gHeap;

	private final int errorJump = 1;

	public Generator(SymbolTables tables, Result res) {
		this.prog = new Program();
		this.threadIDs = new HashMap<>();
		this.checkResult = res;
		this.startFun = new HashMap<>();
		this.endFun = new HashMap<>();
		this.heaps = new HashMap<>();
		this.arps = new HashMap<>();
		this.breaks = new HashMap<>();
		for (int i = 0; i < tables.getThreads().size(); i++) {
			threadIDs.put(tables.getThreads().get(i), i);
		}
		this.gArp = new Address(Addr.DirAddr, threadIDs.size());
		this.gHeap = new Address(Addr.DirAddr, threadIDs.size() + 1);

		Target target = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, target);
		// runtime error handling
		writeString("Runtime error!");
		emit(OpCode.EndProg);

		Map<String, Integer> map = tables.getHeapStarts();
		for (String s : map.keySet()) {
			Reg r1 = helpReg();
			Reg r2 = helpReg();

			heaps.put(s, r1);
			arps.put(s, r2);
		}

		int start = emit(OpCode.Nop);
		target.setTarget(start);

		Target txT = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, regThreadID, txT);
		Target t0T = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, t0T);

		int tx = emit(OpCode.Nop);
		txT.setTarget(tx);
		Reg h1 = helpReg();

		emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
		emit(OpCode.Receive, h1);
		emit(OpCode.Branch, h1, new Target(Tar.Ind, h1));
		emit(OpCode.Jump, txT);

		int t0 = emit(OpCode.Nop);
		t0T.setTarget(t0);

		Reg reg = helpReg();
		emit(OpCode.Load, new Address(Addr.ImmValue, threadIDs.size() + 2), reg);
		emit(OpCode.WriteInstr, reg, gArp);
		emit(OpCode.Load, new Address(Addr.ImmValue, 5000), reg);
		emit(OpCode.WriteInstr, reg, gHeap);
	}

	public Program getProgram(ParseTree node) {
		super.visit(node);
		return this.prog;
	}

	private int emit(OpCode opcode, Operand... operands) {
		Instr res = new Instr(opcode, operands);
		return prog.addInstr(res);
	}

	private void addTar(ParseTree node, Target t) {
		String id = this.checkResult.getThread(node);
		if (!this.breaks.containsKey(id)) {
			this.breaks.put(id, new Stack<>());
		}
		this.breaks.get(id).push(t);
	}

	private boolean hasTar(ParseTree node) {
		String id = this.checkResult.getThread(node);
		return !this.breaks.get(id).isEmpty();
	}

	private Target getTar(ParseTree node) {
		String id = this.checkResult.getThread(node);
		return this.breaks.get(id).peek();
	}

	private Target removeTar(ParseTree node) {
		String id = this.checkResult.getThread(node);
		return this.breaks.get(id).pop();
	}

	private Data getData(ParseTree node) {
		return this.checkResult.getType(node);
	}

	private Reg helpReg() {
		Reg result = regs[regCount];
		this.regCount = (regCount + 1) % regs.length;
		return result;
	}

	private int offset(ParseTree node) {
		return this.checkResult.getOffset(node);
	}

	private int depth(ParseTree node) {
		return this.checkResult.getDepth(node);
	}

	/** code generation part */

	@Override
	public Integer visitProgram(ProgramContext ctx) {
		emit(OpCode.Load, new Address(Addr.ImmValue, 5000), this.heap);
		super.visitProgram(ctx);
		emit(OpCode.EndProg);
		return null;
	}

	private void writeString(String str) {
		Reg reg = helpReg();
		for (char c : str.toCharArray()) {
			int ord = c;
			emit(OpCode.Load, new Address(Addr.ImmValue, ord), reg);
			emit(OpCode.WriteInstr, reg, new Address(Addr.charIO));
		}
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
			emit(OpCode.Store, r2, new Address(Addr.IndAddr, reg));
			// heap increased by size+1
			emit(OpCode.Pop, size);
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
		Target pastT = new Target(Tar.Abs, 0);
		String id = ctx.ID().getText();
		boolean returns = ctx.RETURNS() != null;
		emit(OpCode.Jump, pastT);

		emit(OpCode.Comment, new Str("FunDef " + id + " - body"));
		int body = emit(OpCode.Nop);

		super.visitFunDef(ctx);

		Reg reg = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();
		Reg target = helpReg();
		emit(OpCode.Comment, new Str("FunDef " + id));
		int c = emit(OpCode.Nop);

		emit(OpCode.Pop, failR);
		emit(OpCode.Pop, retR);

		/** JUMP AWAY */
		Target extract = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, retR, extract);
		emit(OpCode.Pop, target);
		emit(OpCode.Jump, new Target(Tar.Ind, target));

		/** EXTRACT */
		int extr = emit(OpCode.Nop);
		extract.setTarget(extr);
		if (returns) {
			Data data = getData(ctx.RETURNS());
			boolean array = data instanceof Arr;
			int retOffset = offset(ctx.RETURNS());

			emit(OpCode.Comment, new Str("FunDef - ret value backup"));
			if (array) {
				Reg heap = this.heap;
				/** STORE ALLOCATED POSITION */
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Store, heap, new Address(Addr.IndAddr, reg));

				/** ALLOCATE / COPY */
				Reg size = helpReg();
				emit(OpCode.Pop, size);
				emit(OpCode.Store, size, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);

				int loop = emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Branch, size, new Target(Tar.Abs, loop));
			} else {
				Reg val = helpReg();
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Pop, val);
				emit(OpCode.Store, val, new Address(Addr.IndAddr, reg));
			}

			emit(OpCode.Pop, target);

			emit(OpCode.Comment, new Str("FunDef - retrieve backup"));
			if (array) {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				Reg size = helpReg();
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), size);
				emit(OpCode.Compute, new Operator(Op.Add), size, reg, reg);

				Reg val = helpReg();

				int loop = emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Branch, size, new Target(Tar.Abs, loop));

			} else {
				Reg val = helpReg();
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
			}
		}
		emit(OpCode.Jump, new Target(Tar.Ind, target));

		/** STORE TARGETS */
		Target end = new Target(Tar.Abs, c);
		Target start = new Target(Tar.Abs, body);
		this.startFun.put(id, start);
		this.endFun.put(id, end);

		int pastV = emit(OpCode.Nop);
		pastT.setTarget(pastV);
		return ret;
	}

	@Override
	public Integer visitParam(ParamContext ctx) {
		int ret = emit(OpCode.Nop);

		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		int offset = offset(ctx);

		Reg arp = this.arp;
		Reg heap = this.heap;
		Reg reg = helpReg();
		Reg h1 = helpReg();

		if (array) {
			Reg index = helpReg();
			/** CORRECT ADDRESS */
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
			// store allocated location
			emit(OpCode.Store, heap, new Address(Addr.IndAddr, h1));

			/** COPY ARRAY */
			emit(OpCode.Compute, new Operator(Op.Add), zero, zero, index);

			int loop = emit(OpCode.Pop, reg);
			emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Incr), index, index, index);

			emit(OpCode.Compute, new Operator(Op.LtE), h1);
			emit(OpCode.Branch, h1, new Target(Tar.Abs, loop));

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

		/** SIGNAL THREAD START */
		Target past = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, past);
		Target thread = new Target(Tar.Abs, 0);
		int tj = emit(OpCode.Jump, thread);
		int p = emit(OpCode.Load, new Address(Addr.ImmValue, tj), reg);
		past.setTarget(p);
		emit(OpCode.WriteInstr, reg, new Address(Addr.IndAddr, regThreadID));

		/** JUMP PAST FORK */
		Target tar = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, tar);

		/** START THREAD LOCATION */
		int t = emit(OpCode.Nop);
		thread.setTarget(t);

		/** RUN THREAD */
		super.visitForkStat(ctx);

		/** SIGNAL THREAD STOP */
		emit(OpCode.WriteInstr, zero, new Address(Addr.IndAddr, regThreadID));

		/** STOP THREAD */
		emit(OpCode.EndProg);

		/** JUMP PAST POINT */
		int main = emit(OpCode.Nop);
		tar.setTarget(main);

		return ret;
	}

	@Override
	public Integer visitJoinStat(JoinStatContext ctx) {
		int ret = emit(OpCode.Nop);

		Reg reg = helpReg();
		/** LOOP UNTIL THREAD STOPPED */
		int loop = emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID));
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

		int incr = offset(ctx); // incr of arp pointer
		Reg reg = helpReg();
		Reg arp = this.arp;

		emit(OpCode.Comment, new Str("Function - call " + id));
		/** PUSH & SET ARP */
		emit(OpCode.Push, arp);
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

		/** PUSH RET ADDRESS */
		emit(OpCode.Jump, new Target(Tar.Rel, +2));
		Target con = new Target(Tar.Abs, 0);
		int val = emit(OpCode.Jump, con);
		emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);

		/** SET ARGS */
		if (ctx.args() != null) {
			visit(ctx.args());
		}

		/** JUMP */
		emit(OpCode.Jump, startFun);

		/** FUNCTION RETURN */
		emit(OpCode.Comment, new Str("Function - return " + id));
		int c = emit(OpCode.Nop);
		con.setTarget(c);

		/** RESET ARP */
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Sub), arp, reg, arp);

		Reg failR = helpReg();

		emit(OpCode.Pop, failR);
		emit(OpCode.Push, failR);
		emit(OpCode.Branch, failR, getTar(ctx));

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
		emit(OpCode.Jump, getTar(ctx));
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
		emit(OpCode.Jump, getTar(ctx));
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
		Reg arp = this.arp;
		Reg reg = helpReg();
		Reg failR = helpReg();
		Reg retR = helpReg();

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

		/** SET NEW ARP */
		emit(OpCode.Compute, new Operator(Op.Incr), reg, reg, arp);

		Target retT = new Target(Tar.Abs, 0);
		addTar(ctx, retT);
		visit(ctx.content(0));
		removeTar(ctx);
		emit(OpCode.Comment, new Str("Block - end"));
		emit(OpCode.Push, zero);
		emit(OpCode.Push, zero);

		int retV = emit(OpCode.Nop);
		retT.setTarget(retV);

		/** RESET ARP */
		emit(OpCode.Compute, new Operator(Op.Decr), arp, arp, arp);
		emit(OpCode.Load, new Address(Addr.IndAddr, arp), arp);

		Target endT = new Target(Tar.Abs, 0);
		Target catT = new Target(Tar.Abs, 0);
		Target finT = new Target(Tar.Abs, 0);
		Target jumpT = new Target(Tar.Abs, 0);

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
			visit(ctx.content(1));
			emit(OpCode.Comment, new Str("Block - catch end"));
			emit(OpCode.Jump, finT);
		}

		if (fin) {
			emit(OpCode.Comment, new Str("Block - ret value backup"));
			int finV = emit(OpCode.Nop);
			finT.setTarget(finV);
			if (array) {
				Reg heap = this.heap;
				/** STORE ALLOCATED POSITION */
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Store, heap, new Address(Addr.IndAddr, reg));

				/** ALLOCATE / COPY */
				Reg size = helpReg();
				emit(OpCode.Pop, size);
				emit(OpCode.Store, size, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);

				int loop = emit(OpCode.Pop, reg);
				emit(OpCode.Store, reg, new Address(Addr.IndAddr, heap));
				emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Branch, size, new Target(Tar.Abs, loop));
			} else {
				Reg val = helpReg();
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Pop, val);
				emit(OpCode.Store, val, new Address(Addr.IndAddr, reg));
			}

			emit(OpCode.Comment, new Str("Block - finally"));

			Target retTFin = new Target(Tar.Abs, 0);
			addTar(ctx, retTFin);
			if (cat) {
				visit(ctx.content(2));
			} else {
				visit(ctx.content(1));
			}
			removeTar(ctx);
			emit(OpCode.Comment, new Str("Block - finally end"));
			emit(OpCode.Push, retR);
			emit(OpCode.Push, failR);

			int retVFin = emit(OpCode.Nop);
			retTFin.setTarget(retVFin);

			emit(OpCode.Pop, failR);
			emit(OpCode.Pop, retR);

			Target pastT = new Target(Tar.Abs, 0);

			emit(OpCode.Branch, retR, pastT);

			/** COPY BACKUP BACK TO STACK */
			emit(OpCode.Comment, new Str("Block - retrieve backup"));
			if (array) {
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				Reg size = helpReg();
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), size);
				emit(OpCode.Compute, new Operator(Op.Add), size, reg, reg);

				Reg val = helpReg();

				int loop = emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
				emit(OpCode.Compute, new Operator(Op.Decr), size, size, size);
				emit(OpCode.Compute, new Operator(Op.Decr), reg, reg, reg);
				emit(OpCode.Branch, size, new Target(Tar.Abs, loop));

			} else {
				Reg val = helpReg();
				emit(OpCode.Load, new Address(Addr.ImmValue, retOffset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, arp, reg);

				emit(OpCode.Load, new Address(Addr.IndAddr, reg), val);
				emit(OpCode.Push, val);
			}

			int pastV = emit(OpCode.Nop);
			pastT.setTarget(pastV);

			emit(OpCode.Comment, new Str("Block - end"));

			emit(OpCode.Branch, failR, jumpT);
			emit(OpCode.Branch, retR, jumpT);
			emit(OpCode.Jump, endT);
		} else {
			int finV = emit(OpCode.Nop);
			finT.setTarget(finV);

		}
		int jumpV = emit(OpCode.Nop);
		jumpT.setTarget(jumpV);
		emit(OpCode.Push, retR);
		emit(OpCode.Push, failR);
		if (hasTar(ctx)) {
			emit(OpCode.Jump, getTar(ctx));
		}

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

		Reg r1 = helpReg();
		Reg r2 = helpReg();
		Reg reg = helpReg();

		Operator op;
		if (ctx.compOp().EQUALS() != null) {
			op = new Operator(Op.Equal);
		} else if (ctx.compOp().GREATER() != null) {
			op = new Operator(Op.Gt);
		} else {
			op = new Operator(Op.Lt);
		}
		emit(OpCode.Pop, r1);
		emit(OpCode.Pop, r2);
		emit(OpCode.Compute, op, r2, r1, reg);
		emit(OpCode.Push, reg);
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
	public Integer visitCallExpr(CallExprContext ctx) {
		int ret = emit(OpCode.Nop);

		String id = ctx.ID().getText();
		Target startFun = this.startFun.get(id);

		int incr = offset(ctx); // incr of arp pointer
		Reg reg = helpReg();
		Reg arp = this.arp;

		emit(OpCode.Comment, new Str("Function - call " + id));
		/** PUSH & SET ARP */
		emit(OpCode.Push, arp);
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, arp, arp);

		/** PUSH RET ADDRESS */
		emit(OpCode.Jump, new Target(Tar.Rel, +2));
		Target con = new Target(Tar.Abs, 0);
		int val = emit(OpCode.Jump, con);
		emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);
		/** SET ARGS */
		if (ctx.args() != null) {
			visit(ctx.args());
		}

		/** JUMP */
		emit(OpCode.Jump, startFun);

		/** FUNCTION RETURN */
		emit(OpCode.Comment, new Str("Function - return " + id));
		int c = emit(OpCode.Nop);
		con.setTarget(c);

		/** RESET ARP */
		emit(OpCode.Load, new Address(Addr.ImmValue, incr), reg);
		emit(OpCode.Compute, new Operator(Op.Sub), arp, reg, arp);

		Reg failR = helpReg();

		emit(OpCode.Pop, failR);
		emit(OpCode.Push, failR);

		emit(OpCode.Branch, failR, getTar(ctx));

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
		super.visitIdExpr(ctx);
		Data data = getData(ctx);
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
		} else {
			if (global) {
				/** CORRECT ADDRESS */
				Reg arp = helpReg();
				emit(OpCode.ReadInstr, gArp);
				emit(OpCode.Receive, arp);

				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
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
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), reg);

				/** ON STACK */
				emit(OpCode.Push, reg);
			}
		}
		return null;
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

		Address adr = new Address(Addr.ImmValue, Program.TRUE_VAL);
		emit(OpCode.Load, adr, reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitFalseExpr(FalseExprContext ctx) {
		int ret = emit(OpCode.Nop);
		Reg reg = helpReg();

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

			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, h1);

			if (array) {
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
			
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);

			if (array) {
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

			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, h1);
			
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, size);
			
			// add index +1 (size storage)
			emit(OpCode.Pop, index);
			
			//Check index
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
			
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), size);
			// add index +1 (size storage)
			emit(OpCode.Pop, index);
			
			//Check index
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
		emit(OpCode.Comment, new Str("If - condition"));
		visit(ctx.expr());
		Reg cond = helpReg();
		Reg reg = helpReg();
		emit(OpCode.Comment, new Str("If"));
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Pop, cond);
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
		emit(OpCode.Comment, new Str("While - condition"));
		visit(ctx.expr());
		Reg cond = helpReg();
		Reg reg = helpReg();
		emit(OpCode.Comment, new Str("While"));
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Pop, cond);
		emit(OpCode.Compute, new Operator(Op.Xor), cond, reg, cond);
		Target endtarget = new Target(Tar.Abs, 0);
		emit(OpCode.Branch, cond, endtarget);
		visit(ctx.block());
		Target whileTarget = new Target(Tar.Abs, ret);
		emit(OpCode.Jump, whileTarget);
		int end = emit(OpCode.Nop);
		endtarget.setTarget(end);
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
