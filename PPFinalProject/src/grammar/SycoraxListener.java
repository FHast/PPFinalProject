// Generated from Sycorax.g4 by ANTLR 4.4
package grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SycoraxParser}.
 */
public interface SycoraxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code strExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStrExpr(@NotNull SycoraxParser.StrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code strExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStrExpr(@NotNull SycoraxParser.StrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(@NotNull SycoraxParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(@NotNull SycoraxParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolArrType(@NotNull SycoraxParser.BoolArrTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolArrType(@NotNull SycoraxParser.BoolArrTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(@NotNull SycoraxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(@NotNull SycoraxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(@NotNull SycoraxParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(@NotNull SycoraxParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 */
	void enterArrayTarget(@NotNull SycoraxParser.ArrayTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 */
	void exitArrayTarget(@NotNull SycoraxParser.ArrayTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(@NotNull SycoraxParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(@NotNull SycoraxParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(@NotNull SycoraxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(@NotNull SycoraxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFalseExpr(@NotNull SycoraxParser.FalseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFalseExpr(@NotNull SycoraxParser.FalseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(@NotNull SycoraxParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(@NotNull SycoraxParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#defs}.
	 * @param ctx the parse tree
	 */
	void enterDefs(@NotNull SycoraxParser.DefsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#defs}.
	 * @param ctx the parse tree
	 */
	void exitDefs(@NotNull SycoraxParser.DefsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterCharType(@NotNull SycoraxParser.CharTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitCharType(@NotNull SycoraxParser.CharTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 */
	void enterIdTarget(@NotNull SycoraxParser.IdTargetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 */
	void exitIdTarget(@NotNull SycoraxParser.IdTargetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompOpExpr(@NotNull SycoraxParser.CompOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompOpExpr(@NotNull SycoraxParser.CompOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolOpExpr(@NotNull SycoraxParser.BoolOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolOpExpr(@NotNull SycoraxParser.BoolOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code failStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFailStat(@NotNull SycoraxParser.FailStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code failStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFailStat(@NotNull SycoraxParser.FailStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forkStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterForkStat(@NotNull SycoraxParser.ForkStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forkStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitForkStat(@NotNull SycoraxParser.ForkStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumExpr(@NotNull SycoraxParser.NumExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumExpr(@NotNull SycoraxParser.NumExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(@NotNull SycoraxParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(@NotNull SycoraxParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(@NotNull SycoraxParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(@NotNull SycoraxParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#funDef}.
	 * @param ctx the parse tree
	 */
	void enterFunDef(@NotNull SycoraxParser.FunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#funDef}.
	 * @param ctx the parse tree
	 */
	void exitFunDef(@NotNull SycoraxParser.FunDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntArrType(@NotNull SycoraxParser.IntArrTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntArrType(@NotNull SycoraxParser.IntArrTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(@NotNull SycoraxParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(@NotNull SycoraxParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintStat(@NotNull SycoraxParser.PrintStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintStat(@NotNull SycoraxParser.PrintStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTrueExpr(@NotNull SycoraxParser.TrueExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTrueExpr(@NotNull SycoraxParser.TrueExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#intOp}.
	 * @param ctx the parse tree
	 */
	void enterIntOp(@NotNull SycoraxParser.IntOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#intOp}.
	 * @param ctx the parse tree
	 */
	void exitIntOp(@NotNull SycoraxParser.IntOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(@NotNull SycoraxParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(@NotNull SycoraxParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterLockStat(@NotNull SycoraxParser.LockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitLockStat(@NotNull SycoraxParser.LockStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(@NotNull SycoraxParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(@NotNull SycoraxParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#arg}.
	 * @param ctx the parse tree
	 */
	void enterArg(@NotNull SycoraxParser.ArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#arg}.
	 * @param ctx the parse tree
	 */
	void exitArg(@NotNull SycoraxParser.ArgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterStringType(@NotNull SycoraxParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitStringType(@NotNull SycoraxParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCallExpr(@NotNull SycoraxParser.CallExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCallExpr(@NotNull SycoraxParser.CallExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(@NotNull SycoraxParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(@NotNull SycoraxParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCharExpr(@NotNull SycoraxParser.CharExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCharExpr(@NotNull SycoraxParser.CharExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pointerStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPointerStat(@NotNull SycoraxParser.PointerStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pointerStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPointerStat(@NotNull SycoraxParser.PointerStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterCharArrType(@NotNull SycoraxParser.CharArrTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charArrType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitCharArrType(@NotNull SycoraxParser.CharArrTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sizeExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSizeExpr(@NotNull SycoraxParser.SizeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sizeExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSizeExpr(@NotNull SycoraxParser.SizeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(@NotNull SycoraxParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(@NotNull SycoraxParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCallStat(@NotNull SycoraxParser.CallStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCallStat(@NotNull SycoraxParser.CallStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterIntType(@NotNull SycoraxParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitIntType(@NotNull SycoraxParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterVardefStat(@NotNull SycoraxParser.VardefStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitVardefStat(@NotNull SycoraxParser.VardefStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code joinStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterJoinStat(@NotNull SycoraxParser.JoinStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code joinStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitJoinStat(@NotNull SycoraxParser.JoinStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#compOp}.
	 * @param ctx the parse tree
	 */
	void enterCompOp(@NotNull SycoraxParser.CompOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#compOp}.
	 * @param ctx the parse tree
	 */
	void exitCompOp(@NotNull SycoraxParser.CompOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link SycoraxParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull SycoraxParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SycoraxParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull SycoraxParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifstat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterIfstat(@NotNull SycoraxParser.IfstatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifstat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitIfstat(@NotNull SycoraxParser.IfstatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntOpExpr(@NotNull SycoraxParser.IntOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntOpExpr(@NotNull SycoraxParser.IntOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterAssignStat(@NotNull SycoraxParser.AssignStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitAssignStat(@NotNull SycoraxParser.AssignStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unlockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterUnlockStat(@NotNull SycoraxParser.UnlockStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unlockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitUnlockStat(@NotNull SycoraxParser.UnlockStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(@NotNull SycoraxParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(@NotNull SycoraxParser.IdExprContext ctx);
}