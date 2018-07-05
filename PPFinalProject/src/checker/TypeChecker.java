package checker;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import grammar.SycoraxBaseVisitor;
import grammar.SycoraxParser.ArrayContext;
import grammar.SycoraxParser.ArrayDefContext;
import grammar.SycoraxParser.ArrayExprContext;
import grammar.SycoraxParser.ArrayTargetContext;
import grammar.SycoraxParser.AssignStatContext;
import grammar.SycoraxParser.BasicDefContext;
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
import grammar.SycoraxParser.ParamContext;
import grammar.SycoraxParser.PointerStatContext;
import grammar.SycoraxParser.PrintStatContext;
import grammar.SycoraxParser.ProgramContext;
import grammar.SycoraxParser.ReturnStatContext;
import grammar.SycoraxParser.SizeExprContext;
import grammar.SycoraxParser.StrExprContext;
import grammar.SycoraxParser.StringTypeContext;
import grammar.SycoraxParser.TrueExprContext;
import grammar.SycoraxParser.TypeContext;
import grammar.SycoraxParser.UnlockStatContext;
import grammar.SycoraxParser.VardefStatContext;
import grammar.SycoraxParser.WhileStatContext;
import symbTable.Data;
import symbTable.Data.Arr;
import symbTable.Data.Func;
import symbTable.Data.Lock;
import symbTable.Data.Pointer;
import symbTable.SymbolTable;
import symbTable.SymbolTables;

public class TypeChecker extends SycoraxBaseVisitor<Void> {
	/** Result of the latest call of {@link #check}. */
	private Result result;

	private List<String> errors;

	private SymbolTables tables;

	private int blockNameCounter = 0;

	private static final boolean catchErrors = true;

	public Result check(ParseTree tree) throws ParseException {
		this.result = new Result();
		this.errors = new ArrayList<>();
		this.tables = new SymbolTables();
		
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

	public SymbolTables getTables() {
		return this.tables;
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
		if (!actual.equals(expected)) {
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

	private void setDepth(ParseTree node, Integer depth) {
		this.result.setDepth(node, depth);
	}

	private void setThread(ParseTree node, String id) {
		this.result.setThread(node, id);
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

	/** Returns the flow graph entry of a given expression or statement. */
	private ParserRuleContext entry(ParseTree node) {
		return this.result.getEntry(node);
	}

	private SymbolTable table() {
		return tables.table();
	}

	private SymbolTable global() {
		return tables.global();
	}

	@Override
	public Void visitProgram(ProgramContext ctx) {
		super.visitProgram(ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIntType(IntTypeContext ctx) {
		super.visitIntType(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBoolType(BoolTypeContext ctx) {
		super.visitBoolType(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCharType(CharTypeContext ctx) {
		super.visitCharType(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIntArrType(IntArrTypeContext ctx) {
		super.visitIntArrType(ctx);
		setData(ctx, new Arr(Data.INT));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBoolArrType(BoolArrTypeContext ctx) {
		super.visitBoolArrType(ctx);
		setData(ctx, new Arr(Data.BOOL));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCharArrType(CharArrTypeContext ctx) {
		super.visitCharArrType(ctx);
		setData(ctx, new Arr(Data.CHAR));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitStringType(StringTypeContext ctx) {
		super.visitStringType(ctx);
		setData(ctx, new Arr(Data.CHAR));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitType(TypeContext ctx) {
		super.visitType(ctx);
		if (ctx.basicType() != null) {
			setData(ctx, getData(ctx.basicType()));
			setEntry(ctx, entry(ctx.basicType()));
		} else {
			setData(ctx, getData(ctx.arrayType()));
			setEntry(ctx, entry(ctx.arrayType()));
		}
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBasicDef(BasicDefContext ctx) {
		super.visitBasicDef(ctx);
		String id = ctx.ID().getText();
		Data data = getData(ctx.basicType());
		boolean global = ctx.GLOBAL() != null;
		boolean assign = ctx.ASSIGN() != null;
		SymbolTable table = global ? global() : table();

		if (assign) {
			checkType(ctx.expr(), data);
		}
		if (!table.put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setEntry(ctx, ctx);
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setDepth(ctx, table.depth(id));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitArrayDef(ArrayDefContext ctx) {
		super.visitArrayDef(ctx);
		String id = ctx.ID().getText();
		Data data = getData(ctx.arrayType());
		boolean global = ctx.GLOBAL() != null;
		boolean assign = ctx.ASSIGN() != null;
		SymbolTable table = global ? global() : table();

		if (assign) {
			checkType(ctx.expr(), data);
		} else {
			checkType(ctx.expr(), Data.INT);
		}
		if (!table.put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setEntry(ctx, ctx);
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setDepth(ctx, table.depth(id));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitFunDef(FunDefContext ctx) {
		visit(ctx.type());

		String name = ctx.ID().getText();
		boolean catchable = ctx.CATCHABLE() != null;
		boolean returns = ctx.RETURNS() != null;
		boolean success = true;
		Data rettype;
		if (returns) {
			rettype = getData(ctx.type());
			success = success && table().put(name, rettype);
		} else {
			rettype = null;
		}

		List<Data> args = new ArrayList<>();
		List<String> vars = new ArrayList<>();
		for (int i = 0; i < ctx.param().size(); i++) {
			visit(ctx.param(i));
			Data data = getData(ctx.param(i));
			args.add(data);
			vars.add(ctx.param(i).ID().getText());
		}
		Func func = new Func(rettype, args, vars, catchable);
		table().openFunScope(func);
		success = success && tables.putFunction(name, func);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_DECL_FAIL, name);
		}
		setData(ctx, func);
		setEntry(ctx, ctx);

		visit(ctx.content());

		if (table().isMissingCatchable()) {
			addError(ctx, Errors.EXPECTED_CATCHABLE);
		}
		table().closeScope();
		setOffset(ctx, table().size());
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitParam(ParamContext ctx) {
		super.visitParam(ctx);
		Data data = getData(ctx.type());
		String id = ctx.ID().getText();
		if (!table().put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setData(ctx, data);
		setOffset(ctx, table().offset(id));
		setDepth(ctx, table().depth(id));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBlockStat(BlockStatContext ctx) {
		super.visitBlockStat(ctx);
		setEntry(ctx, entry(ctx.block()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitVardefStat(VardefStatContext ctx) {
		super.visitVardefStat(ctx);
		setEntry(ctx, ctx.varDef());
		setData(ctx, getData(ctx.varDef()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitFailStat(FailStatContext ctx) {
		super.visitFailStat(ctx);
		boolean caught = table().setFails();
		if (!caught) {
			addError(ctx, Errors.EXCEPTION_NOT_CAUGHT);
		}
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitForkStat(ForkStatContext ctx) {
		super.visitForkStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.newThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.THREAD_NAME_TAKEN, id);
		}
		setEntry(ctx, entry(ctx.block()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitJoinStat(JoinStatContext ctx) {
		super.visitJoinStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.closeThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.CANNOT_JOIN_UNDEFINED, id);
		}
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitLockStat(LockStatContext ctx) {
		super.visitLockStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.lock(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setOffset(ctx, global().offset(id));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitUnlockStat(UnlockStatContext ctx) {
		super.visitUnlockStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.unlock(id);
		Data d = global().get(id);
		if (!(d instanceof Lock)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_BUT_FOUND, "Lock", d);
		}
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.CANNOT_UNLOCK_UNLOCKED, id);
		}
		setOffset(ctx, global().offset(id));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitReturnStat(ReturnStatContext ctx) {
		super.visitReturnStat(ctx);
		boolean retValue = ctx.expr() != null;
		Func current = table().getFunction();
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
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitAssignStat(AssignStatContext ctx) {
		super.visitAssignStat(ctx);
		Data target = getData(ctx.target());
		checkType(ctx.expr(), target);
		setData(ctx, target);
		setEntry(ctx, entry(ctx.target()));
		setThread(ctx, tables.threadID());
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
		if (table().put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setOffset(ctx, table().offset(id));
		setDepth(ctx, table().depth(id));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIfstat(IfstatContext ctx) {
		super.visitIfstat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitWhileStat(WhileStatContext ctx) {
		super.visitWhileStat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitPrintStat(PrintStatContext ctx) {
		super.visitPrintStat(ctx);
		setData(ctx, getData(ctx.expr()));
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCallStat(CallStatContext ctx) {
		super.visitCallStat(ctx);
		String name = ctx.ID().getText();
		Func func = tables.getFunction(name);
		if (func == null) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_NOT_DEFINED, name);
		} else if (func.isCatchable()) {
			boolean caught = table().setFails();
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
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitParExpr(ParExprContext ctx) {
		super.visitParExpr(ctx);
		setEntry(ctx, entry(ctx.expr()));
		setData(ctx, getData(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBoolOpExpr(BoolOpExprContext ctx) {
		super.visitBoolOpExpr(ctx);
		checkType(ctx.expr(0), Data.BOOL);
		checkType(ctx.expr(1), Data.BOOL);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIntOpExpr(IntOpExprContext ctx) {
		super.visitIntOpExpr(ctx);
		checkType(ctx.expr(0), Data.INT);
		checkType(ctx.expr(1), Data.INT);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.INT);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCompOpExpr(CompOpExprContext ctx) {
		super.visitCompOpExpr(ctx);
		checkType(ctx.expr(0), getData(ctx.expr(1)));
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitArrayExpr(ArrayExprContext ctx) {
		super.visitArrayExpr(ctx);
		setData(ctx, getData(ctx.array()));
		setEntry(ctx, entry(ctx.array()));
		setThread(ctx, tables.threadID());
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
			setOffset(ctx, table().offset(arr.id()));
			setDepth(ctx, table().depth(arr.id()));
		}
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIdExpr(IdExprContext ctx) {
		super.visitIdExpr(ctx);
		String id = ctx.ID().getText();
		Data data;
		boolean global = ctx.GLOBAL() != null;
		if (global) {
			data = global().get(id);
			setOffset(ctx, global().offset(id));
			setDepth(ctx, global().depth(id));
		} else {
			data = table().get(id);
			setOffset(ctx, table().offset(id));
			setDepth(ctx, table().depth(id));
		}
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIndexExpr(IndexExprContext ctx) {
		String id = ctx.ID().getText();
		Data data;
		boolean global = ctx.GLOBAL() != null;

		if (global) {
			data = global().get(id);
			setOffset(ctx, global().offset(id));
			setDepth(ctx, global().depth(id));
		} else {
			data = table().get(id);
			setOffset(ctx, table().offset(id));
			setDepth(ctx, table().depth(id));
		}
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		} else if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);

		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCallExpr(CallExprContext ctx) {
		super.visitCallExpr(ctx);
		String name = ctx.ID().getText();
		Func func = tables.getFunction(name);
		if (func == null) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_NOT_DEFINED, name);
		} else if (func.isCatchable()) {
			boolean caught = table().setFails();
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
		setOffset(ctx, table().size());
		setData(ctx, func.ret());
		setData(ctx.ID(), func);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitNumExpr(NumExprContext ctx) {
		super.visitNumExpr(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitCharExpr(CharExprContext ctx) {
		super.visitCharExpr(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitStrExpr(StrExprContext ctx) {
		super.visitStrExpr(ctx);
		Arr data = new Arr(Data.CHAR);
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitTrueExpr(TrueExprContext ctx) {
		super.visitTrueExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitFalseExpr(FalseExprContext ctx) {
		super.visitFalseExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitNotExpr(NotExprContext ctx) {
		super.visitNotExpr(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setData(ctx, Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitArray(ArrayContext ctx) {
		super.visitArray(ctx);
		Data elem = getData(ctx.expr(0));
		for (ParserRuleContext prc : ctx.expr()) {
			checkType(prc, elem);
		}
		Arr data = new Arr(elem);
		String id = data.id();
		table().put(id, data);
		setData(ctx, data);
		setEntry(ctx, entry(ctx.expr(0)));
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitIdTarget(IdTargetContext ctx) { // TODO pointer
		super.visitIdTarget(ctx);
		String id = ctx.ID().getText();
		Data data;
		boolean global = ctx.GLOBAL() != null;
		if (global) {
			data = global().get(id);
			setOffset(ctx, global().offset(id));
			setDepth(ctx, global().depth(id));
		} else {
			data = table().get(id);
			setOffset(ctx, table().offset(id));
			setDepth(ctx, table().depth(id));
		}
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitArrayTarget(ArrayTargetContext ctx) { // TODO pointer
		super.visitArrayTarget(ctx);
		String id = ctx.ID().getText();
		Data data;
		boolean global = ctx.GLOBAL() != null;

		if (global) {
			data = global().get(id);
			setOffset(ctx, global().offset(id));
			setDepth(ctx, global().depth(id));
		} else {
			data = table().get(id);
			setOffset(ctx, table().offset(id));
			setDepth(ctx, table().depth(id));
		}
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		} else if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);

		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
		return null;
	}

	@Override
	public Void visitBlock(BlockContext ctx) {
		boolean hasCatch = ctx.CATCH() != null;
		String id = "#blockRet" + blockNameCounter++;
		Data data = tables.getFunction().ret();
		if (data == null) {
			data = Data.INT;
		}
		boolean success = table().put(id, data);
		if (success) {
			addError(ctx, Errors.VARIABLE_DECL_FAIL, id);
		}
		setOffset(ctx.FINALLY(), table().offset(id));
		table().openBlockScope(hasCatch);
		super.visitBlock(ctx);
		table().closeScope();
		setOffset(ctx, table().size() + 1);
		setThread(ctx, tables.threadID());
		return null;
	}
}