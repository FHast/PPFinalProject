package generator;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import checker.Result;
import generator.Address.Addr;
import generator.Operator.Op;
import generator.Target.Tar;
import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArgsContext;
import grammar.SycoraxParser.ArrayContext;
import grammar.SycoraxParser.ArrayDefContext;
import grammar.SycoraxParser.ArrayTargetContext;
import grammar.SycoraxParser.BasicDefContext;
import grammar.SycoraxParser.BlockContext;
import grammar.SycoraxParser.BlockStatContext;
import grammar.SycoraxParser.BoolOpExprContext;
import grammar.SycoraxParser.CallExprContext;
import grammar.SycoraxParser.CharExprContext;
import grammar.SycoraxParser.CompOpExprContext;
import grammar.SycoraxParser.FalseExprContext;
import grammar.SycoraxParser.ForkStatContext;
import grammar.SycoraxParser.FunDefContext;
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.JoinStatContext;
import grammar.SycoraxParser.LockStatContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParamContext;
import grammar.SycoraxParser.ProgramContext;
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

	private int regCount;

	private ParseTreeProperty<Reg> regs;

	private Reg zero = new Reg("reg0");
	private Reg regThreadID = new Reg("regSprID");

	private Map<String, Reg> heaps;
	private Map<String, Reg> arps;

	private Map<String, Target> startFun;
	private Map<String, Target> endFun;

	private Map<String, Integer> threadIDs;
	private Address gArp;
	private Address gHeap = new Address(Addr.DirAddr, 500);

	private final int errorJump = 1;

	public Generator(SymbolTables tables, Result res) {
		this.prog = new Program();
		this.regs = new ParseTreeProperty<>();
		this.regCount = 1;
		this.threadIDs = new HashMap<>();
		this.checkResult = res;
		this.heaps = new HashMap<>();
		this.arps = new HashMap<>();
		for (int i = 0; i < tables.getThreads().size(); i++) {
			threadIDs.put(tables.getThreads().get(i), i);
		}
		this.gArp = new Address(Addr.DirAddr, threadIDs.size()); //TODO

		Target target = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, target);
		// runtime error handling
		writeString("Runtime error!");
		emit(OpCode.EndProg);

		Map<String, Integer> map = tables.getHeapStarts();
		for (String s : map.keySet()) {
			Reg r1 = helpReg();
			Reg r2 = helpReg();

			heaps.put(s, r1); //TODO
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
	}

	public Program getProgram() {
		this.prog.setRegCount(this.regCount);
		return this.prog;
	}

	private int emit(OpCode opcode, Operand... operands) {
		Instr res = new Instr(opcode, operands);
		return prog.addInstr(res);
	}

	private Data getData(ParseTree node) {
		return this.checkResult.getType(node);
	}

	private Reg arp(ParseTree node) {
		return this.arps.get(this.checkResult.getThread(node));
	}

	private Reg heap(ParseTree node) {
		return this.heaps.get(this.checkResult.getThread(node));
	}

	private Reg globalHeap() {
		return this.heaps.get(SymbolTables.GLOBAL);
	}

	private Reg globalArp() {
		return this.arps.get(SymbolTables.GLOBAL);
	}

	private Reg helpReg() {
		Reg result = new Reg("reg" + this.regCount);
		this.regCount++;
		return result;
	}

	private void setReg(ParseTree node, Reg reg) {
		this.regs.put(node, reg);
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
		int offset = offset(ctx);
		Address addr = new Address(Addr.DirAddr, offset); // TODO
		OpCode store;
		if (global) {
			store = OpCode.WriteInstr;
		} else {
			store = OpCode.Store;
		}

		if (assign) {
			Reg reg = helpReg();
			emit(OpCode.Pop, reg);
			emit(store, reg, addr);
		} else {
			emit(store, zero, addr);
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

		OpCode store;
		if (global) {
			store = OpCode.WriteInstr;
		} else {
			store = OpCode.Store;
		}

		// r1 -> mem address assignment
		Reg r1 = helpReg();
		// help registers
		Reg r2 = helpReg();
		Reg reg = helpReg();
		Reg size = helpReg();
		Reg index = helpReg();
		Reg comp = helpReg();

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
			emit(OpCode.WriteInstr, heap, new Address(Addr.IndAddr, reg));

			// heap increased by size+1
			emit(OpCode.Pop, size);
			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);
			emit(OpCode.WriteInstr, heap, gHeap);
		} else {
			Reg heap = heap(ctx);
			Reg arp = arp(ctx);
			// r2 -> heap address
			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, r2);
			// store allocated address at pointer location
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), arp, reg, reg);
			emit(OpCode.Store, r2, new Address(Addr.IndAddr, reg));
			// heap increased by size+1
			emit(OpCode.Pop, size);
			emit(OpCode.Compute, new Operator(Op.Incr), heap, heap, heap);
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);
		}
		emit(OpCode.Comment, new Str("ArrayDef - store data"));
		if (assign) {
			/** COPY */
			Address targetAddress = new Address(Addr.IndAddr, r2);
			Target endJump = new Target(Tar.Abs, 0);
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), index);
			int cond = emit(OpCode.Compute, new Operator(Op.Gt), index, size, comp);
			emit(OpCode.Branch, comp, endJump);
			emit(OpCode.Compute, new Operator(Op.Incr), index, index, index);
			emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
			emit(OpCode.Pop, reg);
			emit(store, reg, targetAddress);
			emit(OpCode.Jump, new Target(Tar.Abs, cond));
			int end = emit(OpCode.Nop);
			endJump.setTarget(end);
		} else {
			/** FILL WITH 0 */
			Address targetAddress = new Address(Addr.IndAddr, r2);
			Target endJump = new Target(Tar.Abs, 0);
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), index);
			int cond = emit(OpCode.Compute, new Operator(Op.Gt), index, size, comp);
			emit(OpCode.Branch, comp, endJump);
			emit(OpCode.Compute, new Operator(Op.Incr), index, index, index);
			emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
			emit(store, zero, targetAddress);
			Target condJump = new Target(Tar.Abs, cond);
			emit(OpCode.Jump, condJump);
			int end = emit(OpCode.Nop);
			endJump.setTarget(end);
		}
		return null;
	}

	@Override
	public Integer visitFunDef(FunDefContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitFunDef(ctx);

		String id = ctx.ID().getText();

		Reg reg = helpReg();

		int c = emit(OpCode.Pop, reg);
		emit(OpCode.Jump, new Address(Addr.IndAddr, reg));

		/** STORE TARGETS */
		Target end = new Target(Tar.Abs, c);
		Target start = new Target(Tar.Abs, ret);
		this.startFun.put(id, start);
		this.endFun.put(id, end);
		return ret;
	}

	@Override
	public Integer visitParam(ParamContext ctx) {
		int ret = emit(OpCode.Nop);

		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		int offset = offset(ctx);

		Reg arp = arp(ctx);
		Reg heap = heap(ctx);
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
		Reg arp = globalArp();
		Reg answer = helpReg();

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
		Reg arp = globalArp();

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
		int loop = emit(OpCode.ReadInstr, new Address(Addr.IndAddr, regThreadID)); // TODO
		emit(OpCode.Receive, reg);
		emit(OpCode.Compute, new Operator(Op.NEq), reg, zero, reg);
		emit(OpCode.Branch, reg, new Target(Tar.Abs, loop));

		return ret;
	}

	@Override
	public Integer visitBlock(BlockContext ctx) {
		int ret = emit(OpCode.Nop);

		boolean fin = ctx.FINALLY() != null;
		boolean cat = ctx.CATCH() != null;
		int offset = offset(ctx);
		Reg arp = arp(ctx);
		Reg reg = helpReg();

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
		emit(OpCode.Compute, op, r1, r2, reg);
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
		emit(OpCode.Compute, op, r1, r2, reg);
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
		emit(OpCode.Compute, op, r1, r2, reg);
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
		Reg arp = arp(ctx);
		Reg result = helpReg();

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
		visit(ctx.args());

		/** JUMP */
		emit(OpCode.Jump, startFun);

		/** FUNCTION RETURN */
		int c = emit(OpCode.Nop);
		con.setTarget(c);

		/** GET RESULT */
		emit(OpCode.Pop, result);

		/** RESET ARP */
		emit(OpCode.Pop, reg);
		emit(OpCode.Compute, new Operator(Op.Add), reg, zero, arp);

		/** PUSH RESULT */
		emit(OpCode.Push, result);
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
	public Integer visitArrayTarget(ArrayTargetContext ctx) {
		int ret = emit(OpCode.Nop);
		super.visitArrayTarget(ctx);

		int offset = offset(ctx);
		int depth = depth(ctx);
		boolean global = ctx.GLOBAL() != null;

		Reg rindex = helpReg();
		Reg reg = helpReg();
		Reg h1 = helpReg();

		emit(OpCode.Pop, rindex);
		if (global) {
			/** COMPUTING ARRAY ADDRESS */
			Reg arp = globalArp();
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
			emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, h1);

			/** BOUNDS CHECKING */
			Reg comp = helpReg();
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg);
			emit(OpCode.Compute, new Operator(Op.LtE), reg, rindex, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, errorJump));
			emit(OpCode.Compute, new Operator(Op.Lt), rindex, zero, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, errorJump));

			/** LOADING VALUE */
			emit(OpCode.Compute, new Operator(Op.Add), h1, rindex, h1);
			emit(OpCode.Compute, new Operator(Op.Incr), h1, h1, h1);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg);
			emit(OpCode.Push, reg);
		} else {
			/** COMPUTING ARRAY ADDRESS */
			Reg arp = arp(ctx);
			// get correct scope
			emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
			for (int i = 0; i < depth; i++) {
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
			// add offset
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, h1, h1);

			/** CHECKING INDEX BOUNDS */
			Reg comp = helpReg();
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
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
	public Integer visitIdTarget(IdTargetContext ctx) {
		super.visitIdTarget(ctx);
		Data data = getData(ctx);
		boolean array = data instanceof Arr;
		boolean global = ctx.GLOBAL() != null;
		int offset = offset(ctx);

		int depth = depth(ctx);

		Reg reg = helpReg();
		Reg h1 = helpReg();
		Reg comp = arp(ctx);
		Reg addr = helpReg();
		if (array) {
			if (global) {
				Reg arp = globalArp();
				/** CORRECT ADDRESS */
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);

				/** ON STACK */
				// load size
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, reg));
				emit(OpCode.Receive, h1);

				int start = emit(OpCode.Compute, new Operator(Op.Lt), h1, zero, comp);
				Target tar = new Target(Tar.Abs, 0);
				emit(OpCode.Branch, comp, tar);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, addr);
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, addr));
				emit(OpCode.Receive, addr);
				emit(OpCode.Push, addr);
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Jump, new Target(Tar.Abs, start));
				int end = emit(OpCode.Nop);
				tar.setTarget(end);

			} else {
				/** CORRECT ADDRESS */
				Reg arp = arp(ctx);
				emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
				for (int i = 0; i < depth; i++) {
					emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, reg);

				/** BACKWARDS ON STACK */
				emit(OpCode.Load, new Address(Addr.IndAddr, reg), h1);

				int start = emit(OpCode.Compute, new Operator(Op.Lt), h1, zero, comp);
				Target tar = new Target(Tar.Abs, 0);
				emit(OpCode.Branch, comp, tar);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, addr);
				emit(OpCode.Load, new Address(Addr.IndAddr, addr), addr);
				emit(OpCode.Push, addr);
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Jump, new Target(Tar.Abs, start));
				int end = emit(OpCode.Nop);
				tar.setTarget(end);
			}
		} else {
			if (global) {
				/** CORRECT ADDRESS */
				Reg arp = globalArp();
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);

				/** ON STACK */
				emit(OpCode.Push, reg);
			} else {
				/** CORRECT ADDRESS */
				Reg arp = arp(ctx);
				emit(OpCode.Compute, new Operator(Op.Add), arp, zero, h1);
				for (int i = 0; i < depth; i++) {
					emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
					emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
				}
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
				emit(OpCode.Compute, new Operator(Op.Add), reg, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);

				/** ON STACK */
				emit(OpCode.Push, reg);
			}
		}
		return null;
	}

	@Override
	public Integer visitNumExpr(NumExprContext ctx) {
		int val = Integer.parseInt(ctx.NUM().getText());
		if (ctx.NEGATIVE() != null) {
			val *= -1;
		}
		Reg reg = helpReg();
		int ret = emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitCharExpr(CharExprContext ctx) {
		int val = ctx.CHAR().getText().charAt(0);
		Reg reg = helpReg();
		int ret = emit(OpCode.Load, new Address(Addr.ImmValue, val), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitStrExpr(StrExprContext ctx) {
		int ret = emit(OpCode.Nop);
		String str = ctx.STR().getText();
		int size = str.length();
		Reg reg = helpReg();

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

		/** PUSH ELEMENTS IN INVERTED ORDER */
		for (int i = ctx.expr().size() - 1; i >= 0; i--) {
			visit(ctx.expr(i));
		}

		Reg reg = helpReg();
		/** PUSH SIZE */
		int size = ctx.expr().size();
		emit(OpCode.Load, new Address(Addr.ImmValue, size), reg);
		emit(OpCode.Push, reg);
		return ret;
	}

	@Override
	public Integer visitIfstat(IfstatContext ctx) { // TODO
		int ret = visit(ctx.expr());
		Reg cond = helpReg();
		Reg reg = helpReg();
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
	public Integer visitWhileStat(WhileStatContext ctx) { // TODO
		int ret = visit(ctx.expr());
		Reg cond = helpReg();
		Reg reg = helpReg();
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
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
}
