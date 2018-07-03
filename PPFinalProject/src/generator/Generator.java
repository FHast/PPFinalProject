package generator;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import checker.Result;
import generator.Address.Addr;
import generator.Operator.Op;
import generator.Target.Tar;
import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArgContext;
import grammar.SycoraxParser.ArrayExprContext;
import grammar.SycoraxParser.BoolOpExprContext;
import grammar.SycoraxParser.CharExprContext;
import grammar.SycoraxParser.CompOpExprContext;
import grammar.SycoraxParser.FalseExprContext;
import grammar.SycoraxParser.FunDefContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IndexExprContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParExprContext;
import grammar.SycoraxParser.ProgramContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.VarDefContext;
import grammar.SycoraxParser.WhileStatContext;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.SymbolTable;

public class Generator extends SycoraxBaseVisitor<Integer> {
	private Program prog;

	private Result checkResult;

	private int regCount;

	private ParseTreeProperty<Reg> regs;

	private Reg arp = new Reg("regArp");

	private Reg zero = new Reg("regZero");

	private Reg threadID = new Reg("regSprID");

	private SymbolTable table;

	private int emit(OpCode opcode, Operand... operands) {
		Instr res = new Instr(opcode, operands);
		return prog.addInstr(res);
	}

	private Data getData(ParseTree node) {
		return this.checkResult.getType(node);
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
		this.prog = new Program();
		this.regs = new ParseTreeProperty<>();
		this.regCount = 0;
		// runtime error handling
		writeString("Runtime error!");
		
		super.visitProgram(ctx);
		return null;
	}

	@Override
	public Integer visitVarDef(VarDefContext ctx) {
		Reg reg = getReg(ctx);
		boolean assign = ctx.ASSIGN() != null;
		boolean global = ctx.GLOBAL() != null;
		Data data = getData(ctx);
		int offset = offset(ctx);
		Address addr = new Address(Addr.DirAddr, offset);

		OpCode store;
		if (global) {
			store = OpCode.WriteInstr;
		} else {
			store = OpCode.Store;
		}
		if (!(data instanceof Arr)) { // basic type
			if (assign) {
				emit(OpCode.Store, getReg(ctx.expr()), addr);
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, 0), reg);
				emit(store, reg, addr);
			}
		} else {
			Arr arr = ((Arr) data);
			int size = arr.size();
			Reg r2 = getReg(ctx.type());
			if (assign) {
				Reg r1 = getReg(ctx.expr());
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), r2);
				Address index = new Address(Addr.IndAddr, r2);
				emit(OpCode.Load, new Address(Addr.ImmValue, size), reg);
				emit(store, reg, index);
				for (int i = 0; i < size; i++) {
					emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
					emit(OpCode.Compute, new Operator(Op.Incr), r1, r1, r1);
					emit(OpCode.Load, new Address(Addr.IndAddr, r1), reg);
					emit(store, reg, index);
				}
			} else {
				emit(OpCode.Load, new Address(Addr.ImmValue, offset), r2);
				Address index = new Address(Addr.IndAddr, r2);
				emit(OpCode.Load, new Address(Addr.ImmValue, size), reg);
				emit(store, reg, index);
				for (int i = 0; i < size; i++) {
					emit(OpCode.Compute, new Operator(Op.Incr), r2, r2, r2);
					emit(OpCode.Load, new Address(Addr.ImmValue, 0), reg);
					emit(store, reg, index);
				}
			}
		}
		return null;
	}

	@Override
	public Integer visitFunDef(FunDefContext ctx) { // TODO

		return null;
	}
	
	@Override
	public Integer visitArg(ArgContext ctx) {
		
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
	public Integer visitIndexExpr(IndexExprContext ctx) { // TODO global
		int ret = visit(ctx.expr());
		Reg rindex = getReg(ctx.expr());
		Reg reg = getReg(ctx);
		int offset = offset(ctx);
		boolean global = false;
		String id = ctx.ID().getText();
		int depth = table.depth(id);

		Reg h1 = helpReg();

		if (global) {
			// add offset to arp(0)
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, zero, h1);
			// check index bounds
			Reg comp = helpReg();
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg); 
			emit(OpCode.Compute, new Operator(Op.LtE), reg, rindex, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, 0));
			emit(OpCode.Compute, new Operator(Op.Lt), rindex, zero, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, 0));
			// get arr[rindex]
			emit(OpCode.Compute, new Operator(Op.Add), h1, rindex, h1);
			emit(OpCode.ReadInstr, new Address(Addr.IndAddr, h1));
			emit(OpCode.Receive, reg);
		} else {
			// goto scope (h1 has memory address)
			emit(OpCode.Compute, new Operator(Op.Add), this.arp, zero, h1);
			for (int i = 0; i < depth; i++) {
				emit(OpCode.Compute, new Operator(Op.Decr), h1, h1, h1);
				emit(OpCode.Load, new Address(Addr.IndAddr, h1), h1);
			}
			// add offset
			emit(OpCode.Load, new Address(Addr.ImmValue, offset), reg);
			emit(OpCode.Compute, new Operator(Op.Add), reg, h1, h1);
			// check index bounds
			Reg comp = helpReg();
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
			emit(OpCode.Compute, new Operator(Op.LtE), reg, rindex, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, 0));
			emit(OpCode.Compute, new Operator(Op.Lt), rindex, zero, comp);
			emit(OpCode.Branch, comp, new Target(Tar.Abs, 0));
			// get arr[rindex]
			emit(OpCode.Compute, new Operator(Op.Add), h1, rindex, h1);
			emit(OpCode.Load, new Address(Addr.IndAddr, h1), reg);
		}
		return ret;
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
