// Generated from Sycorax.g4 by ANTLR 4.4
package grammar;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SycoraxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SycoraxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code strExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStrExpr(@NotNull SycoraxParser.StrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStat(@NotNull SycoraxParser.BlockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayDef}
	 * labeled alternative in {@link SycoraxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDef(@NotNull SycoraxParser.ArrayDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolArrType}
	 * labeled alternative in {@link SycoraxParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolArrType(@NotNull SycoraxParser.BoolArrTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(@NotNull SycoraxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull SycoraxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(@NotNull SycoraxParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTarget(@NotNull SycoraxParser.ArrayTargetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStat(@NotNull SycoraxParser.ReturnStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull SycoraxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseExpr(@NotNull SycoraxParser.FalseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStat(@NotNull SycoraxParser.WhileStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#defs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefs(@NotNull SycoraxParser.DefsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charType}
	 * labeled alternative in {@link SycoraxParser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharType(@NotNull SycoraxParser.CharTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idTarget}
	 * labeled alternative in {@link SycoraxParser#target}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdTarget(@NotNull SycoraxParser.IdTargetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOpExpr(@NotNull SycoraxParser.CompOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOpExpr(@NotNull SycoraxParser.BoolOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code failStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFailStat(@NotNull SycoraxParser.FailStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forkStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForkStat(@NotNull SycoraxParser.ForkStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumExpr(@NotNull SycoraxParser.NumExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(@NotNull SycoraxParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#funDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDef(@NotNull SycoraxParser.FunDefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intArrType}
	 * labeled alternative in {@link SycoraxParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntArrType(@NotNull SycoraxParser.IntArrTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(@NotNull SycoraxParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueExpr(@NotNull SycoraxParser.TrueExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#intOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntOp(@NotNull SycoraxParser.IntOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link SycoraxParser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(@NotNull SycoraxParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLockStat(@NotNull SycoraxParser.LockStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicDef}
	 * labeled alternative in {@link SycoraxParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicDef(@NotNull SycoraxParser.BasicDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(@NotNull SycoraxParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArg(@NotNull SycoraxParser.ArgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link SycoraxParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(@NotNull SycoraxParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallExpr(@NotNull SycoraxParser.CallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code targetExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTargetExpr(@NotNull SycoraxParser.TargetExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#boolOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(@NotNull SycoraxParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharExpr(@NotNull SycoraxParser.CharExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pointerStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPointerStat(@NotNull SycoraxParser.PointerStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charArrType}
	 * labeled alternative in {@link SycoraxParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharArrType(@NotNull SycoraxParser.CharArrTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sizeExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSizeExpr(@NotNull SycoraxParser.SizeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(@NotNull SycoraxParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code callStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallStat(@NotNull SycoraxParser.CallStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link SycoraxParser#basicType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(@NotNull SycoraxParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vardefStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardefStat(@NotNull SycoraxParser.VardefStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code joinStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinStat(@NotNull SycoraxParser.JoinStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#compOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompOp(@NotNull SycoraxParser.CompOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link SycoraxParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(@NotNull SycoraxParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifstat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstat(@NotNull SycoraxParser.IfstatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intOpExpr}
	 * labeled alternative in {@link SycoraxParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntOpExpr(@NotNull SycoraxParser.IntOpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStat(@NotNull SycoraxParser.AssignStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unlockStat}
	 * labeled alternative in {@link SycoraxParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlockStat(@NotNull SycoraxParser.UnlockStatContext ctx);
}