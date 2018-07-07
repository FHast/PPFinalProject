package checker;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import grammar.SycoraxBaseListener;
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
import grammar.SycoraxParser.FunTypeContext;
import grammar.SycoraxParser.IdExprContext;
import grammar.SycoraxParser.IdPointerContext;
import grammar.SycoraxParser.IdTargetContext;
import grammar.SycoraxParser.IfstatContext;
import grammar.SycoraxParser.IndexExprContext;
import grammar.SycoraxParser.IndexPointerContext;
import grammar.SycoraxParser.IntArrTypeContext;
import grammar.SycoraxParser.IntOpExprContext;
import grammar.SycoraxParser.IntTypeContext;
import grammar.SycoraxParser.JoinStatContext;
import grammar.SycoraxParser.LockStatContext;
import grammar.SycoraxParser.NotExprContext;
import grammar.SycoraxParser.NumExprContext;
import grammar.SycoraxParser.ParExprContext;
import grammar.SycoraxParser.ParamContext;
import grammar.SycoraxParser.ParamsContext;
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

public class TypeChecker extends SycoraxBaseListener {
	/** Result of the latest call of {@link #check}. */
	private Result result;

	private List<String> errors;

	private SymbolTables tables;

	private int blockNameCounter = 0;

	private static final boolean catchErrors = false;

	public Result check(ParseTree tree) throws ParseException {
		this.result = new Result();
		this.errors = new ArrayList<>();
		this.tables = new SymbolTables();

		if (catchErrors) {
			try {
				new ParseTreeWalker().walk(this, tree);
			} catch (Exception c) {
				// System.err.println(c.getMessage());
			} finally {
				if (hasErrors()) {
					throw new ParseException(getErrors());
				}
			}
		} else {
			new ParseTreeWalker().walk(this, tree);
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
	public void exitProgram(ProgramContext ctx) {
		super.exitProgram(ctx);
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitIntType(IntTypeContext ctx) {
		super.exitIntType(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitBoolType(BoolTypeContext ctx) {
		super.exitBoolType(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCharType(CharTypeContext ctx) {
		super.exitCharType(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitIntArrType(IntArrTypeContext ctx) {
		super.exitIntArrType(ctx);
		setData(ctx, new Arr(Data.INT));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitBoolArrType(BoolArrTypeContext ctx) {
		super.exitBoolArrType(ctx);
		setData(ctx, new Arr(Data.BOOL));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCharArrType(CharArrTypeContext ctx) {
		super.exitCharArrType(ctx);
		setData(ctx, new Arr(Data.CHAR));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitStringType(StringTypeContext ctx) {
		super.exitStringType(ctx);
		setData(ctx, new Arr(Data.CHAR));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitType(TypeContext ctx) {
		super.exitType(ctx);
		if (ctx.basicType() != null) {
			setData(ctx, getData(ctx.basicType()));
			setEntry(ctx, entry(ctx.basicType()));
		} else {
			setData(ctx, getData(ctx.arrayType()));
			setEntry(ctx, entry(ctx.arrayType()));
		}
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitBasicDef(BasicDefContext ctx) {
		super.exitBasicDef(ctx);
		String id = ctx.ID().getText();
		Data data = getData(ctx.basicType());
		boolean global = ctx.GLOBAL() != null;
		boolean assign = ctx.ASSIGN() != null;
		boolean glscope = tables.globalScope();
		SymbolTable table = global ? global() : table();

		if (assign) {
			checkType(ctx.expr(), data);
		}
		if (!table.put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		if (!global && glscope) {
			addError(ctx, Errors.MISSING_GLOBAL, id);
		}

		setEntry(ctx, ctx);
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setDepth(ctx, table.depth(id));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitArrayDef(ArrayDefContext ctx) {
		super.exitArrayDef(ctx);
		String id = ctx.ID().getText();
		Data data = getData(ctx.arrayType());
		boolean global = ctx.GLOBAL() != null;
		boolean assign = ctx.ASSIGN() != null;
		boolean glscope = tables.globalScope();
		SymbolTable table = global ? global() : table();

		if (assign) {
			if (ctx.expr() != null) {
				checkType(ctx.expr(), data);
			}
		} else {
			if (ctx.expr() != null) {
				checkType(ctx.expr(), Data.INT);
			}
		}
		if (!table.put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		if (!global && glscope) {
			addError(ctx, Errors.MISSING_GLOBAL, id);
		}

		setEntry(ctx, ctx);
		setData(ctx, data);
		setOffset(ctx, table.offset(id));
		setDepth(ctx, table.depth(id));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void enterFunDef(FunDefContext ctx) {
		String name = ctx.ID().getText();
		boolean catchable = ctx.CATCHABLE() != null;
		boolean returns = ctx.RETURNS() != null;
		boolean success = true;
		Data rettype;
		if (returns) {
			rettype = Data.INT;
		} else {
			rettype = null;
		}

		List<Data> args = new ArrayList<>();
		List<String> vars = new ArrayList<>();
		Func func = new Func(rettype, name, args, vars, catchable);
		table().openFunScope(func);
		success = success && tables.putFunction(name, func);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.FUNCTION_DECL_FAIL, name);
		}

		if (returns) {
			setData(ctx.RETURNS(), func.ret());
			String id = "#fun" + blockNameCounter;
			boolean suc = table().put(id, func.ret());
			if (!suc) {
				addError(ctx, Errors.VARIABLE_DECL_FAIL, id);
			}
			setOffset(ctx.RETURNS(), table().offset(id));
		}
		setData(ctx, func);
		setEntry(ctx, ctx);
	}

	@Override
	public void exitFunDef(FunDefContext ctx) {
		if (table().isMissingCatchable()) {
			addError(ctx, Errors.EXPECTED_CATCHABLE);
		}
		table().closeScope();
		setOffset(ctx, table().size());
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitFunType(FunTypeContext ctx) {
		Func func = tables.getFunction();
		func.setRet(getData(ctx.type()));
		String name = func.getName();
		boolean success = table().put(name, func.ret());
		if (!success) {
			addError(ctx, Errors.VARIABLE_DECL_FAIL, name);
		}
		setOffset(ctx, table().offset(name));
	}

	@Override
	public void exitParams(ParamsContext ctx) {
		List<Data> args = new ArrayList<>();
		List<String> vars = new ArrayList<>();
		for (int i = 0; i < ctx.param().size(); i++) {
			Data data = getData(ctx.param(i));
			args.add(data);
			vars.add(ctx.param(i).ID().getText());
		}
		tables.getFunction().setArgs(args);
		tables.getFunction().setVars(vars);
	}

	@Override
	public void exitParam(ParamContext ctx) {
		super.exitParam(ctx);
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

	}

	@Override
	public void exitBlockStat(BlockStatContext ctx) {
		super.exitBlockStat(ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitVardefStat(VardefStatContext ctx) {
		super.exitVardefStat(ctx);
		setEntry(ctx, ctx.varDef());
		setData(ctx, getData(ctx.varDef()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitFailStat(FailStatContext ctx) {
		super.exitFailStat(ctx);
		boolean caught = table().setFails();
		if (!caught) {
			addError(ctx, Errors.EXCEPTION_NOT_CAUGHT);
		}
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void enterForkStat(ForkStatContext ctx) {
		super.exitForkStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.newThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.THREAD_NAME_TAKEN, id);
		}
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitForkStat(ForkStatContext ctx) {
		String id = ctx.ID().getText();
		tables.closeThread(id);
	}

	@Override
	public void exitJoinStat(JoinStatContext ctx) {
		super.exitJoinStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.hasThread(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.CANNOT_JOIN_UNDEFINED, id);
		}
		setEntry(ctx, ctx);
		setThread(ctx, id);

	}

	@Override
	public void exitLockStat(LockStatContext ctx) {
		super.exitLockStat(ctx);
		String id = ctx.ID().getText();
		boolean success = tables.lock(id);
		if (!success) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setOffset(ctx, global().offset(id));
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitUnlockStat(UnlockStatContext ctx) {
		super.exitUnlockStat(ctx);
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

	}

	@Override
	public void exitReturnStat(ReturnStatContext ctx) {
		super.exitReturnStat(ctx);
		boolean retValue = ctx.expr() != null;
		Func current = table().getFunction();
		if (current == null) {
			addError(ctx, "Cannot return outside function. ");
			;
		}
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

	}

	@Override
	public void exitAssignStat(AssignStatContext ctx) {
		super.exitAssignStat(ctx);
		Data target = getData(ctx.target());
		checkType(ctx.expr(), target);
		setData(ctx, target);
		setEntry(ctx, entry(ctx.target()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitPointerStat(PointerStatContext ctx) {
		super.exitPointerStat(ctx);
		Data var = getData(ctx.pointerTarget());
		if (var == null) {
			addError(ctx.pointerTarget(), Errors.VARIBALE_NOT_IN_SCOPE, var);
		}
		String id = ctx.ID().getText();
		Data data = new Pointer(var);
		if (!table().put(id, data)) {
			addError(ctx.ID().getSymbol(), Errors.VARIABLE_DECL_FAIL, id);
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setOffset(ctx, table().offset(id));
		setDepth(ctx, table().depth(id));
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitIdPointer(IdPointerContext ctx) {
		super.exitIdPointer(ctx);
		String id = ctx.ID().getText();
		Data data;
		data = table().get(id);
		setOffset(ctx, table().offset(id));
		setDepth(ctx, table().depth(id));
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitIndexPointer(IndexPointerContext ctx) {
		String id = ctx.ID().getText();
		Data data;
		data = table().get(id);
		setOffset(ctx, table().offset(id));
		setDepth(ctx, table().depth(id));
		if (data == null) {
			addError(ctx, Errors.VARIBALE_NOT_IN_SCOPE, id);
		}
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);
		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());
	}

	@Override
	public void exitIfstat(IfstatContext ctx) {
		super.exitIfstat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitWhileStat(WhileStatContext ctx) {
		super.exitWhileStat(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitPrintStat(PrintStatContext ctx) {
		super.exitPrintStat(ctx);
		setData(ctx, getData(ctx.expr()));
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCallStat(CallStatContext ctx) {
		super.exitCallStat(ctx);
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
		int given;
		if (ctx.args() == null) {
			given = 0;
		} else {
			given = ctx.args().expr().size();
		}
		int expected = args.size();
		if (given != expected) {
			addError(ctx.args(), Errors.EXPECTED_NUM_ARGUMENTS, expected, given);
		}
		for (int i = 0; i < args.size(); i++) {
			checkType(ctx.args().expr(i), args.get(i));
		}
		setOffset(ctx, table().size() + 2);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitParExpr(ParExprContext ctx) {
		super.exitParExpr(ctx);
		setEntry(ctx, entry(ctx.expr()));
		setData(ctx, getData(ctx.expr()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitBoolOpExpr(BoolOpExprContext ctx) {
		super.exitBoolOpExpr(ctx);
		checkType(ctx.expr(0), Data.BOOL);
		checkType(ctx.expr(1), Data.BOOL);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitIntOpExpr(IntOpExprContext ctx) {
		super.exitIntOpExpr(ctx);
		checkType(ctx.expr(0), Data.INT);
		checkType(ctx.expr(1), Data.INT);
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.INT);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCompOpExpr(CompOpExprContext ctx) {
		super.exitCompOpExpr(ctx);
		// checkType(ctx.expr(0), getData(ctx.expr(1)));
		setEntry(ctx, entry(ctx.expr(0)));
		setData(ctx, Data.BOOL);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitArrayExpr(ArrayExprContext ctx) {
		super.exitArrayExpr(ctx);
		setData(ctx, getData(ctx.array()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitSizeExpr(SizeExprContext ctx) {
		super.exitSizeExpr(ctx);
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

	}

	@Override
	public void exitIdExpr(IdExprContext ctx) {
		super.exitIdExpr(ctx);
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
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitIndexExpr(IndexExprContext ctx) {
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
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);
		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCallExpr(CallExprContext ctx) {
		super.exitCallExpr(ctx);
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
		int given;
		if (ctx.args() == null) {
			given = 0;
		} else {
			given = ctx.args().expr().size();
		}
		int expected = args.size();
		if (given != expected) {
			addError(ctx.args(), Errors.EXPECTED_NUM_ARGUMENTS, expected, given);
		}
		for (int i = 0; i < args.size(); i++) {
			checkType(ctx.args().expr(i), args.get(i));
		}
		setOffset(ctx, table().size() + 2);
		setData(ctx, func.ret());
		setData(ctx.ID(), func);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitNumExpr(NumExprContext ctx) {
		super.exitNumExpr(ctx);
		setData(ctx, Data.INT);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitCharExpr(CharExprContext ctx) {
		super.exitCharExpr(ctx);
		setData(ctx, Data.CHAR);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitStrExpr(StrExprContext ctx) {
		super.exitStrExpr(ctx);
		Arr data = new Arr(Data.CHAR);
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitTrueExpr(TrueExprContext ctx) {
		super.exitTrueExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitFalseExpr(FalseExprContext ctx) {
		super.exitFalseExpr(ctx);
		setData(ctx, Data.BOOL);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitNotExpr(NotExprContext ctx) {
		super.exitNotExpr(ctx);
		checkType(ctx.expr(), Data.BOOL);
		setData(ctx, Data.BOOL);
		setEntry(ctx, entry(ctx.expr()));
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitArray(ArrayContext ctx) {
		super.exitArray(ctx);
		Data elem = getData(ctx.expr(0));
		for (ParserRuleContext prc : ctx.expr()) {
			checkType(prc, elem);
		}
		Arr data = new Arr(elem);
		String id = data.id();
		table().put(id, data);
		setData(ctx, data);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitIdTarget(IdTargetContext ctx) { // TODO pointer
		super.exitIdTarget(ctx);
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
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		setData(ctx, data);
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void exitArrayTarget(ArrayTargetContext ctx) { // TODO pointer
		super.exitArrayTarget(ctx);
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
		if (data instanceof Pointer) {
			setData(ctx.ID(), data);
			while (data instanceof Pointer) {
				data = ((Pointer) data).target();
			}
		}
		if (!(data instanceof Arr)) {
			addError(ctx.ID().getSymbol(), Errors.EXPECTED_TYPE_BUT_FOUND, "Array", data);
		}
		checkType(ctx.expr(), Data.INT);
		setData(ctx, ((Arr) data).elem());
		setEntry(ctx, ctx);
		setThread(ctx, tables.threadID());

	}

	@Override
	public void enterBlock(BlockContext ctx) {
		boolean hasCatch = ctx.CATCH() != null;
		String id = "#blockRet" + blockNameCounter++;
		Data func = tables.getFunction();
		Data data = func == null ? Data.INT : ((Func) func).ret();
		if (data == null) {
			data = Data.INT;
		}
		boolean success = table().put(id, data);
		if (!success) {
			addError(ctx, Errors.VARIABLE_DECL_FAIL, id);
		}
		setOffset(ctx.FINALLY(), table().offset(id));
		table().openBlockScope(hasCatch);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		table().closeScope();
		setOffset(ctx, table().size() + 4);
		setThread(ctx, tables.threadID());
	}
}