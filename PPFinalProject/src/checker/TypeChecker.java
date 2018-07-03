package checker;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import grammar.SycoraxBaseListener;
import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArgContext;
import grammar.SycoraxParser.ArrayContext;
import grammar.SycoraxParser.ArrayExprContext;
import grammar.SycoraxParser.ArrayTargetContext;
import grammar.SycoraxParser.AssignStatContext;
import grammar.SycoraxParser.BlockContext;
import grammar.SycoraxParser.BlockStatContext;
import grammar.SycoraxParser.BoolArrTypeContext;
import grammar.SycoraxParser.BoolOpExprContext;
import grammar.SycoraxParser.BoolTypeContext;
import grammar.SycoraxParser.CallExprContext;
import grammar.SycoraxParser.CallStatContext;
import grammar.SycoraxParser.CharArrTypeContext;
import grammar.SycoraxParser.CharExprContext;
import grammar.SycoraxParser.CharTypeContext;
import grammar.SycoraxParser.CompOpExprContext;
import grammar.SycoraxParser.FailStatContext;
import grammar.SycoraxParser.FalseExprContext;
import grammar.SycoraxParser.ForkStatContext;
import grammar.SycoraxParser.FunDefContext;
import grammar.SycoraxParser.IdExprContext;
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IndexExprContext;
import grammar.SycoraxParser.IntArrTypeContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.IntTypeContext;
import grammar.SycoraxParser.JoinStatContext;
import grammar.SycoraxParser.LockStatContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParExprContext;
import grammar.SycoraxParser.PointerStatContext;
import grammar.SycoraxParser.PrintStatContext;
import grammar.SycoraxParser.ReturnStatContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.StrExprContext;
import grammar.SycoraxParser.StringTypeContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.UnlockStatContext;
import grammar.SycoraxParser.VarDefContext;
import grammar.SycoraxParser.VardefStatContext;
import grammar.SycoraxParser.WhileStatContext;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.Data.Func;
import symbTable.Data.Pointer;
import symbTable.SymbolTable;

public class TypeChecker extends SycoraxBaseVisitor<Void> {
	/** Result of the latest call of {@link #check}. */
	private Result result;
	/** Variable scope for the latest call of {@link #check}. */
	private SymbolTable table;

	private List<String> errors;

	private static final boolean catchErrors = true;

	public Result check(ParseTree tree) throws ParseException {
		this.table = new SymbolTable();
		this.result = new Result();
		this.errors = new ArrayList<>();
		if (catchErrors) {
			try {
				this.visit(tree);
			} catch (Exception c) {
				// System.err.println(c.getMessage());
			} finally {
				if (hasErrors()) {
					throw new ParseException(getErrors());
				}
			}
		} else {
			this.visit(tree);
			if (hasErrors()) {
				throw new ParseException(getErrors());
			}
		}
		return this.result;
	}

	/**
	 * Indicates if any errors were encountered in this tree listener.
	 */
	public boolean hasErrors() {
		return !getErrors().isEmpty();
	}

	/**
	 * Returns the list of errors collected in this tree listener.
	 */
	public List<String> getErrors() {
		return this.errors;
	}

	private void checkType(ParserRuleContext node, Data expected) {
		Data actual = getData(node);
		if (actual == null) {
			throw new IllegalArgumentException("Missing inferred " + "type of " + node.getText());
		}
		if (!actual.equals(expected)) { // TODO
			addError(node, Errors.EXPECTED_TYPE_BUT_FOUND, expected, actual);
		}
	}

	private void addError(ParserRuleContext node, String message, Object... args) {
		addError(node.getStart(), message, args);
	}

	private void addError(Token token, String message, Object... args) {
		int line = token.getLine();
		int column = token.getCharPositionInLine();
		message = String.format(message, args);
		message = String.format("Line %d:%d - %s", line, column, message);
		this.errors.add(message);
	}

	/** Convenience method to add an offset to the result. */
	private void setOffset(ParseTree node, Integer offset) {
		this.result.setOffset(node, offset);
	}

	/** Convenience method to add a type to the result. */
	private void setData(ParseTree node, Data type) {
		this.result.setType(node, type);
	}

	/** Returns the type of a given expression or type node. */
	private Data getData(ParseTree node) {
		return this.result.getType(node);
	}

	/** Convenience method to add a flow graph entry to the result. */
	private void setEntry(ParseTree node, ParserRuleContext entry) {
		if (entry == null) {
			throw new IllegalArgumentException("Null flow graph entry");
		}
		this.result.setEntry(node, entry);
	}

	/**
	 * Returns the flow graph entry of a given expression or statement.
	 */
	private ParserRuleContext entry(ParseTree node) {
		return this.result.getEntry(node);
	}

	@Override
	public Void visitIntType(IntTypeContext ctx) {
		super.visitIntType(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitBoolType(BoolTypeContext ctx) {
		super.visitBoolType(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitCharType(CharTypeContext ctx) {
		super.visitCharType(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitIntArrType(IntArrTypeContext ctx) {
		super.visitIntArrType(ctx);
		int size = Integer.parseInt(ctx.NUM().getText());
		setData(ctx, new Arr(Data.INT, size));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitBoolArrType(BoolArrTypeContext ctx) {
		super.visitBoolArrType(ctx);
		int size = Integer.parseInt(ctx.NUM().getText());
		setData(ctx, new Arr(Data.BOOL, size));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitCharArrType(CharArrTypeContext ctx) {
		super.visitCharArrType(ctx);
		int size = Integer.parseInt(ctx.NUM().getText());
		setData(ctx, new Arr(Data.CHAR, size));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitStringType(StringTypeContext ctx) {
		super.visitStringType(ctx);
		int size = Integer.parseInt(ctx.NUM().getText());
		setData(ctx, new Arr(Data.CHAR, size));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitVarDef(VarDefContext ctx) {
		super.visitVarDef(ctx);
		String id = ctx.ID().getText();
		Data data = getData(ctx.type());
		boolean global = ctx.GLOBAL() != null;
		if (global) {
			data.setGlobal();
		}
		if (!global && table.isGlobalScope()) {
			addError(ctx, Errors.MISSING_GLOBAL);
		}
		if (ctx.ASSIGN() != null) {
			checkType(ctx.expr(), data);
		}
		if (!table.put(id, data, global)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setEntry(ctx, ctx);
		setData(ctx, data);
		setOffset(ctx, this.table.offset(id));
		return null;
	}

	@Override
	public Void visitFunDef(FunDefContext ctx) {
		visit(ctx.type());

		String name = ctx.ID().getText();
		boolean catchable = ctx.CATCHABLE() != null;
		Data rettype;
		if (ctx.RETURNS() != null) {
			rettype = getData(ctx.type());
		} else {
			rettype = null;
		}

		List<Data> args = new ArrayList<>();
		for (int i = 0; i < ctx.arg().size(); i++) {
			visit(ctx.arg(i));
			Data data = getData(ctx.arg(i));
			args.add(data);
		}
		Func func = new Func(rettype, args, catchable);
		table.openFunScope(func);
		boolean success = table.putFunction(name, func);
		if (!success) {
			addError(ctx.arg(0), Errors.FUNCTION_DECL_FAIL, name);
		}
		setData(ctx, func);
		setEntry(ctx, ctx);

		for (ParserRuleContext prc : ctx.stat()) {
			visit(prc);
		}

		if (table.isMissingCatchable()) {
			addError(ctx, Errors.EXPECTED_CATCHABLE);
		}
		table.closeScope();
		return null;
	}

	@Override
	public Void visitArg(ArgContext ctx) {
		super.visitArg(ctx);
		Data data = getData(ctx.type());
		String id = ctx.ID().getText();
		if (!table.put(id, data, false)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitBlockStat(BlockStatContext ctx) {
		super.visitBlockStat(ctx);
		setEntry(ctx, entry(ctx.block()));
		return null;
	}

	@Override
	public Void visitVardefStat(VardefStatContext ctx) {
		super.visitVardefStat(ctx);
		setEntry(ctx, ctx.varDef());
		setData(ctx, getData(ctx.varDef()));
		return null;
	}

	@Override
	public Void visitFailStat(FailStatContext ctx) {
		super.visitFailStat(ctx);
		boolean caught = table.setFails();
		if (!caught) {
			addError(ctx, Errors.EXCEPTION_NOT_CAUGHT);
		}
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitForkStat(ForkStatContext ctx) {
		super.visitForkStat(ctx);
		String id = ctx.ID().getText();
		boolean success = table.putThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.THREAD_NAME_TAKEN, id);
		}
		setEntry(ctx, entry(ctx.block()));
		return null;
	}

	@Override
	public Void visitJoinStat(JoinStatContext ctx) {
		super.visitJoinStat(ctx);
		String id = ctx.ID().getText();
		boolean success = table.removeThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.CANNOT_JOIN_UNDEFINED, id);
		}
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitLockStat(LockStatContext ctx) {
		super.visitLockStat(ctx);
		String id = ctx.ID().getText();
		table.putLock(id);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitUnlockStat(UnlockStatContext ctx) {
		super.visitUnlockStat(ctx);
		String id = ctx.ID().getText();
		boolean success = table.removeLock(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.CANNOT_UNLOCK_UNLOCKED, id);
		}
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitReturnStat(ReturnStatContext ctx) {
		super.visitReturnStat(ctx);
		boolean retValue = ctx.expr() != null;
		Func current = table.getFunction();
		Data ret = current.ret();

		if (retValue && current.isVoid()) {
			addError(ctx.expr(), Errors.CANNOT_RETURN_IN_VOID);
		} else if (!retValue && !current.isVoid()) {
			addError(ctx.expr(), Errors.EXPECTED_BUT_FOUND, ret, "nothing");
		} else if (retValue && !current.isVoid()) {
			checkType(ctx.expr(), ret);
		}
		setData(ctx, ret);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitAssignStat(AssignStatContext ctx) {
		super.visitAssignStat(ctx);
		Data target = getData(ctx.target());
		checkType(ctx.expr(), target);
		setData(ctx, target);
		setEntry(ctx, entry(ctx.target()));
		return null;
	}

	@Override
	public Void visitPointerStat(PointerStatContext ctx) {
		super.visitPointerStat(ctx);
		Data var = getData(ctx.target());
		if (var == null) {
			addError(ctx.target(), Errors.VARIBALE_NOT_IN_SCOPE, var);
		}
		String id = ctx.ID().getText();
		Data data = new Pointer(var);
		if (table.put(id, data, false)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setOffset(ctx, table.offset(id));
		return null;
	}

	@Override
	public Void visitIfstat(IfstatContext ctx) {
		super.visitIfstat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitWhileStat(WhileStatContext ctx) {
		super.visitWhileStat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitPrintStat(PrintStatContext ctx) { // TODO
		super.visitPrintStat(ctx);
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitCallStat(CallStatContext ctx) {
		super.visitCallStat(ctx);
		String name = ctx.ID().getText();
		Func func = table.getFunction(name);
		if (func == null) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_NOT_DEFINED, name);
		} else if (func.isCatchable()) {
			boolean caught = table.setFails();
			if (!caught) {
				addError(ctx, "Exception is never caught.");
			}
		}
		List<Data> args = func.args();
		int given = ctx.args().expr().size();
		int expected = args.size();
		if (given != expected) {
			addError(ctx.args(), Errors.EXPECTED_NUM_ARGUMENTS, expected, given);
		}
		for (int i = 0; i < args.size(); i++) {
			checkType(ctx.args().expr(i), args.get(i));
		}
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitParExpr(ParExprContext ctx) {
		super.visitParExpr(ctx);
		setEntry(ctx, entry(ctx.expr()));
		setData(ctx, getData(ctx.expr()));
		return null;
	}

	@Override
	public Void visitBoolOpExpr(BoolOpExprContext ctx) {
		super.visitBoolOpExpr(ctx);
		checkType(ctx.expr(0), Data.BOOL);
		checkType(ctx.expr(1), Data.BOOL);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		return null;
	}

	@Override
	public Void visitIntOpExpr(IntOpExprContext ctx) {
		super.visitIntOpExpr(ctx);
		checkType(ctx.expr(0), Data.INT);
		checkType(ctx.expr(1), Data.INT);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.INT);
		return null;
	}

	@Override
	public Void visitCompOpExpr(CompOpExprContext ctx) {
		super.visitCompOpExpr(ctx);
		checkType(ctx.expr(0), getData(ctx.expr(1)));
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		return null;
	}

	@Override
	public Void visitArrayExpr(ArrayExprContext ctx) {
		super.visitArrayExpr(ctx);
		setData(ctx, getData(ctx.array()));
		setEntry(ctx, entry(ctx.array()));
		return null;
	}

	@Override
	public Void visitSizeExpr(SizeExprContext ctx) {
		super.visitSizeExpr(ctx);
		setData(ctx, Data.INT);
		Data data = getData(ctx.expr());
		if (!(data instanceof Arr)) {
			addError(ctx.expr(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		} else {
			Arr arr = (Arr) data;
			setOffset(ctx, table.offset(arr.id()));
		}
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitIndexExpr(IndexExprContext ctx) {
		super.visitIndexExpr(ctx);
		checkType(ctx.expr(), Data.INT);
		String id = ctx.ID().getText();
		Data data = table.get(id);
		if (data == null) {
			addError(ctx.ID().getSymbol(), Errors.VARIBALE_NOT_IN_SCOPE, id);
		} else if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		setOffset(ctx, table.offset(id));
		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitCallExpr(CallExprContext ctx) {
		super.visitCallExpr(ctx);
		String name = ctx.ID().getText();
		Func func = table.getFunction(name);
		if (func == null) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_NOT_DEFINED, name);
		} else if (func.isCatchable()) {
			boolean caught = table.setFails();
			if (!caught) {
				addError(ctx, "Exception is never caught.");
			}
		}
		List<Data> args = func.args();
		int given = ctx.args().expr().size();
		int expected = args.size();
		if (given != expected) {
			addError(ctx.args(), Errors.EXPECTED_NUM_ARGUMENTS, expected, given);
		}
		for (int i = 0; i < args.size(); i++) {
			checkType(ctx.args().expr(i), args.get(i));
		}
		setData(ctx, func.ret());
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitIdExpr(IdExprContext ctx) {
		super.visitIdExpr(ctx);
		String id = ctx.ID().getText();
		Data data = table.get(id);
		if (data == null) {
			addError(ctx.ID().getSymbol(), Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitNumExpr(NumExprContext ctx) {
		super.visitNumExpr(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitCharExpr(CharExprContext ctx) {
		super.visitCharExpr(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitStrExpr(StrExprContext ctx) { // TODO array storage
		super.visitStrExpr(ctx);
		String str = ctx.STR().getText();
		Arr data = new Arr(Data.CHAR, str.length());
		String id = data.id();
		table.put(id, data, false);
		setData(ctx, data);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitTrueExpr(TrueExprContext ctx) {
		super.visitTrueExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitFalseExpr(FalseExprContext ctx) {
		super.visitFalseExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitNotExpr(NotExprContext ctx) {
		super.visitNotExpr(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setData(ctx, Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		return null;
	}

	@Override
	public Void visitArray(ArrayContext ctx) { // TODO array storage
		super.visitArray(ctx);
		int size = ctx.expr().size();
		Data elem = getData(ctx.expr(0));
		for (ParserRuleContext prc : ctx.expr()) {
			checkType(prc, elem);
		}
		Arr data = new Arr(elem, size);
		String id = data.id();
		table.put(id, data, false);
		setData(ctx, data);
		setEntry(ctx, entry(ctx.expr(0)));
		return null;
	}

	@Override
	public Void visitIdTarget(IdTargetContext ctx) {
		super.visitIdTarget(ctx);
		String id = ctx.ID().getText();
		Data data = table.get(id);
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitArrayTarget(ArrayTargetContext ctx) {
		super.visitArrayTarget(ctx);
		String id = ctx.ID().getText();
		Data data = table.get(id);
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		} else if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);

		setData(ctx, ((Arr) data).elem());
		setOffset(ctx, table.offset(id));
		setEntry(ctx, ctx);
		return null;
	}

	@Override
	public Void visitBlock(BlockContext ctx) {
		boolean hasCatch = ctx.CATCH() != null;
		table.openBlockScope(hasCatch);
		super.visitBlock(ctx);
		table.closeScope();
		if (ctx.stat().isEmpty()) {
			setEntry(ctx, ctx);
		} else {
			setEntry(ctx, entry(ctx.stat(0)));
		}
		return null;
	}
}