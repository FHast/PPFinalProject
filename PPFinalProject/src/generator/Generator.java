package generator;

import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import checker.Result;
import generator.Address.Addr;
import generator.Operator.Op;
import generator.Target.Tar;
import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArrayDefContext;
import grammar.SycoraxParser.ArrayExprContext;
import grammar.SycoraxParser.ArrayTargetContext;
import grammar.SycoraxParser.BasicDefContext;
import grammar.SycoraxParser.BoolOpExprContext;
import grammar.SycoraxParser.CharExprContext;
import grammar.SycoraxParser.CompOpExprContext;
import grammar.SycoraxParser.FalseExprContext;
import grammar.SycoraxParser.FunDefContext;
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParExprContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.WhileStatContext;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.SymbolTables;

public class Generator extends SycoraxBaseVisitor<Integer> {

	private Program prog;

	private Result checkResult;

	private int regCount;

	private ParseTreeProperty<Reg> regs;

	private Reg zero = new Reg("regZero");
	private Reg regThreadID = new Reg("regSprID");

	private Map<String, Reg> heaps;
	private Map<String, Reg> arps;

	private final int errorJump = 1;

	public Generator(int heapStart, SymbolTables tables) {
		this.prog = new Program();
		this.regs = new ParseTreeProperty<>();
		this.regCount = 0;
		Target target = new Target(Tar.Abs, 0);
		emit(OpCode.Jump, target);
		// runtime error handling
		writeString("Runtime error!");

		Map<String, Integer> map = tables.getHeapStarts();
		for (String s : map.keySet()) {
			heaps.put(s, new Reg("regH" + s));
			arps.put(s, new Reg("arp" + s));

			emit(OpCode.Load, new Address(Addr.ImmValue, map.get(s)), heaps.get(s));
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), arps.get(s));
		}

		int start = emit(OpCode.Nop);
		target.setTarget(start);
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

	private Reg getReg(ParseTree node) {
		Reg result = this.regs.get(node);
		if (result == null) {
			result = new Reg("reg" + this.regCount);
			this.regs.put(node, result);
			this.regCount++;
		}
		return result;
	}

	private Reg helpReg() {
		Reg result = new Reg("regH" + this.regCount);
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
		Address addr = new Address(Addr.DirAddr, offset);
		OpCode store;
		if (global) {
			store = OpCode.WriteInstr;
		} else {
			store = OpCode.Store;
		}
		if (assign) {
			emit(store, getReg(ctx.expr()), addr);
		} else {
			emit(store, zero, addr);
		}
		return null;
	}

	@Override
	public Integer visitArrayDef(ArrayDefContext ctx) {
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
		Reg r1 = getReg(ctx.expr());
		// help registers
		Reg r2 = getReg(ctx.arrayType());
		Reg reg = getReg(ctx);
		Reg size = helpReg();
		Reg index = helpReg();
		Reg comp = helpReg();

		// size -> store size
		emit(OpCode.Load, new Address(Addr.IndAddr, r1), size);
		emit(OpCode.Compute, new Operator(Op.Incr), size, size, size);

		/** ALLOCATION */
		if (global) {
			Reg heap = globalHeap();
			Reg arp = globalArp();
			// r2 -> heap address
			emit(OpCode.Compute, new Operator(Op.Add), heap, zero, r2);
			// store allocated address at pointer location
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), arp, reg, reg);
			emit(OpCode.WriteInstr, r2, new Address(Addr.IndAddr, reg));
			// heap increased by size+1
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);
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
			emit(OpCode.Compute, new Operator(Op.Add), heap, size, heap);
		}

		if (assign) {
			/** COPY */
			Address targetAddress = new Address(Addr.IndAddr, r2);
			Target endJump = new Target(Tar.Abs, 0);
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), index);
			int cond = emit(OpCode.Compute, new Operator(Op.Gt), index, size, comp);
			emit(OpCode.Branch, comp, endJump);
			emit(store, reg, targetAddress);
			emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
			emit(OpCode.Compute, new Operator(Op.Incr), r1, r1, r1);
			emit(OpCode.Load, new Address(Addr.IndAddr, r1), reg);
			Target condJump = new Target(Tar.Abs, cond);
			emit(OpCode.Jump, condJump);
			int end = emit(OpCode.Nop);
			endJump.setTarget(end);
		} else {
			/** FILL WITH 0 */
			Address targetAddress = new Address(Addr.IndAddr, r2);
			Target endJump = new Target(Tar.Abs, 0);
			emit(OpCode.Load, new Address(Addr.ImmValue, 0), index);
			int cond = emit(OpCode.Compute, new Operator(Op.Gt), index, size, comp);
			emit(OpCode.Branch, comp, endJump);
			emit(store, zero, targetAddress);
			emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
			Target condJump = new Target(Tar.Abs, cond);
			emit(OpCode.Jump, condJump);
			int end = emit(OpCode.Nop);
			endJump.setTarget(end);
		}
		return null;
	}

	@Override
	public Integer visitFunDef(FunDefContext ctx) { // TODO

		return null;
	}

	@Override
	public Integer visitParExpr(ParExprContext ctx) {
		int ret = visit(ctx.expr());
		setReg(ctx, getReg(ctx.expr()));
		return ret;
	}

	@Override
	public Integer visitBoolOpExpr(BoolOpExprContext ctx) {
		int ret = visit(ctx.expr(0));
		visit(ctx.expr(1));
		Reg r1 = getReg(ctx.expr(0));
		Reg r2 = getReg(ctx.expr(1));
		Reg reg = getReg(ctx);

		Operator op;
		if (ctx.boolOp().AND() != null) {
			op = new Operator(Op.And);
		} else {
			op = new Operator(Op.Or);
		}
		emit(OpCode.Compute, op, r1, r2, reg);
		return ret;
	}

	@Override
	public Integer visitIntOpExpr(IntOpExprContext ctx) {
		int ret = visit(ctx.expr(0));
		visit(ctx.expr(1));
		Reg r1 = getReg(ctx.expr(0));
		Reg r2 = getReg(ctx.expr(1));
		Reg reg = getReg(ctx);

		Operator op;
		if (ctx.intOp().PLUS() != null) {
			op = new Operator(Op.Add);
		} else if (ctx.intOp().MINUS() != null) {
			op = new Operator(Op.Sub);
		} else {
			op = new Operator(Op.Mul);
		}
		emit(OpCode.Compute, op, r1, r2, reg);
		return ret;
	}

	@Override
	public Integer visitCompOpExpr(CompOpExprContext ctx) {
		int ret = visit(ctx.expr(0));
		visit(ctx.expr(1));
		Reg r1 = getReg(ctx.expr(0));
		Reg r2 = getReg(ctx.expr(1));
		Reg reg = getReg(ctx);

		Operator op;
		if (ctx.compOp().EQUALS() != null) {
			op = new Operator(Op.Equal);
		} else if (ctx.compOp().GREATER() != null) {
			op = new Operator(Op.Gt);
		} else {
			op = new Operator(Op.Lt);
		}
		emit(OpCode.Compute, op, r1, r2, reg);
		return ret;
	}

	@Override
	public Integer visitArrayExpr(ArrayExprContext ctx) {
		int ret = visit(ctx.array());
		setReg(ctx, getReg(ctx.array()));
		return ret;
	}

	@Override
	public Integer visitSizeExpr(SizeExprContext ctx) {
		int ret = visit(ctx.expr());
		Reg reg = getReg(ctx);
		Reg r1 = getReg(ctx.expr());
		emit(OpCode.Load, new Address(Addr.IndAddr, r1), reg);
		return ret;
	}

	@Override
	public Integer visitArrayTarget(ArrayTargetContext ctx) {
		super.visitArrayTarget(ctx);
		int ret = visit(ctx.expr());
		int offset = offset(ctx);
		int depth = depth(ctx);
		boolean global = ctx.GLOBAL() != null;

		Reg rindex = getReg(ctx.expr());
		Reg reg = getReg(ctx);
		Reg h1 = helpReg();

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

		Reg reg = getReg(ctx);
		Reg h1 = helpReg();
		if (array) { //TODO
			if (global) {
				int pointer = offset(ctx.GLOBAL());
				/** CORRECT ADDRESS */
				
				/** COPY */
				
				/** STORE MEM ADDRESS */
				
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
			}
		} else {
			if (global) {
				/** CORRECT ADDRESS */
				Reg arp = globalArp();
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), h1);
				emit(OpCode.Compute, new Operator(Op.Add), h1, arp, h1);
				emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
				emit(OpCode.Receive, reg);
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
			}
		}

		return null;
	}

	@Override
	public Integer visitTrueExpr(TrueExprContext ctx) {
		Reg reg = getReg(ctx);
		Address adr = new Address(Addr.ImmValue, Program.TRUE_VAL);
		return emit(OpCode.Load, adr, reg);
	}

	@Override
	public Integer visitFalseExpr(FalseExprContext ctx) {
		Reg reg = getReg(ctx);
		Address adr = new Address(Addr.ImmValue, Program.FALSE_VAL);
		return emit(OpCode.Load, adr, reg);
	}

	@Override
	public Integer visitCharExpr(CharExprContext ctx) {
		Reg reg = getReg(ctx);
		int val = ctx.CHAR().getText().charAt(0);
		Address adr = new Address(Addr.ImmValue, val);
		return emit(OpCode.Load, adr, reg);
	}

	@Override
	public Integer visitNumExpr(NumExprContext ctx) {
		Reg reg = getReg(ctx);
		int val = Integer.parseInt(ctx.NUM().getText());
		if (ctx.NEGATIVE() != null) {
			val *= -1;
		}
		Address adr = new Address(Addr.ImmValue, val);
		return emit(OpCode.Load, adr, reg);
	}

	@Override
	public Integer visitNotExpr(NotExprContext ctx) {
		int ret = visit(ctx.expr());
		Reg r1 = getReg(ctx.expr());
		Reg reg = getReg(ctx);
		emit(OpCode.Load, new Address(Addr.ImmValue, 1), reg);
		emit(OpCode.Compute, new Operator(Op.Xor), r1, reg, reg);
		return ret;
	}

	@Override
	public Integer visitIfstat(IfstatContext ctx) {
		int ret = visit(ctx.expr());
		Reg cond = getReg(ctx.expr());
		Reg reg = getReg(ctx);
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
		int ret = visit(ctx.expr());
		Reg cond = getReg(ctx.expr());
		Reg reg = getReg(ctx);
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
