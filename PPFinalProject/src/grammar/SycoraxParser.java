// Generated from Sycorax.g4 by ANTLR 4.4
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SycoraxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TRUE=1, FALSE=2, NOT=3, AND=4, OR=5, BOOLEAN=6, INTEGER=7, CHARACTER=8, 
		BOOLEANS=9, INTEGERS=10, CHARACTERS=11, IF=12, THEN=13, ELSE=14, DO=15, 
		CATCH=16, FAIL=17, FINALLY=18, LET=19, ARRAY=20, STRING=21, BE=22, ASSIGN=23, 
		SIZED=24, POINTER=25, TO=26, FUNCTION=27, USES=28, RETURNS=29, CATCHABLE=30, 
		DEFINES=31, RETURN=32, CALL=33, WITH=34, PROGRAM=35, WHILE=36, FORK=37, 
		JOIN=38, LOCK=39, UNLOCK=40, PLUS=41, MINUS=42, TIMES=43, EQUALS=44, GREATER=45, 
		SMALLER=46, GLOBAL=47, SIZE=48, PRINT=49, COMMA=50, DQUOTE=51, SQUOTE=52, 
		LBRACE=53, RBRACE=54, LPAR=55, RPAR=56, LBRACK=57, RBRACK=58, NEGATIVE=59, 
		ID=60, NUM=61, STR=62, CHAR=63, COMMENT=64, WS=65;
	public static final String[] tokenNames = {
		"<INVALID>", "TRUE", "FALSE", "NOT", "AND", "OR", "BOOLEAN", "INTEGER", 
		"CHARACTER", "BOOLEANS", "INTEGERS", "CHARACTERS", "IF", "THEN", "ELSE", 
		"DO", "CATCH", "FAIL", "FINALLY", "LET", "ARRAY", "STRING", "BE", "ASSIGN", 
		"SIZED", "POINTER", "TO", "FUNCTION", "USES", "RETURNS", "CATCHABLE", 
		"DEFINES", "RETURN", "CALL", "WITH", "PROGRAM", "WHILE", "FORK", "JOIN", 
		"LOCK", "UNLOCK", "PLUS", "MINUS", "TIMES", "EQUALS", "GREATER", "SMALLER", 
		"GLOBAL", "SIZE", "PRINT", "','", "'\"'", "'''", "'{'", "'}'", "'('", 
		"')'", "'['", "']'", "'-'", "ID", "NUM", "STR", "CHAR", "COMMENT", "WS"
	};
	public static final int
		RULE_program = 0, RULE_defs = 1, RULE_varDef = 2, RULE_funDef = 3, RULE_arg = 4, 
		RULE_stat = 5, RULE_expr = 6, RULE_boolOp = 7, RULE_intOp = 8, RULE_compOp = 9, 
		RULE_array = 10, RULE_args = 11, RULE_target = 12, RULE_block = 13, RULE_type = 14;
	public static final String[] ruleNames = {
		"program", "defs", "varDef", "funDef", "arg", "stat", "expr", "boolOp", 
		"intOp", "compOp", "array", "args", "target", "block", "type"
	};

	@Override
	public String getGrammarFileName() { return "Sycorax.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SycoraxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode RBRACE() { return getToken(SycoraxParser.RBRACE, 0); }
		public DefsContext defs() {
			return getRuleContext(DefsContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(SycoraxParser.LBRACE, 0); }
		public TerminalNode EOF() { return getToken(SycoraxParser.EOF, 0); }
		public TerminalNode DEFINES() { return getToken(SycoraxParser.DEFINES, 0); }
		public TerminalNode PROGRAM() { return getToken(SycoraxParser.PROGRAM, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30); match(PROGRAM);
			setState(31); match(ID);
			setState(32); match(DEFINES);
			setState(33); match(LBRACE);
			setState(34); defs();
			setState(35); match(RBRACE);
			setState(36); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefsContext extends ParserRuleContext {
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FunDefContext> funDef() {
			return getRuleContexts(FunDefContext.class);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public FunDefContext funDef(int i) {
			return getRuleContext(FunDefContext.class,i);
		}
		public DefsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterDefs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitDefs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitDefs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefsContext defs() throws RecognitionException {
		DefsContext _localctx = new DefsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_defs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LET || _la==FUNCTION) {
				{
				setState(40);
				switch (_input.LA(1)) {
				case FUNCTION:
					{
					setState(38); funDef();
					}
					break;
				case LET:
					{
					setState(39); varDef();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode BE() { return getToken(SycoraxParser.BE, 0); }
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
		public TerminalNode LET() { return getToken(SycoraxParser.LET, 0); }
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45); match(LET);
			setState(47);
			_la = _input.LA(1);
			if (_la==GLOBAL) {
				{
				setState(46); match(GLOBAL);
				}
			}

			setState(49); match(ID);
			setState(50); match(BE);
			setState(51); type();
			setState(54);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(52); match(ASSIGN);
				setState(53); expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunDefContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SycoraxParser.LBRACE, 0); }
		public TerminalNode CATCHABLE() { return getToken(SycoraxParser.CATCHABLE, 0); }
		public TerminalNode RETURNS() { return getToken(SycoraxParser.RETURNS, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode USES() { return getToken(SycoraxParser.USES, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SycoraxParser.COMMA, i);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode FUNCTION() { return getToken(SycoraxParser.FUNCTION, 0); }
		public TerminalNode RBRACE() { return getToken(SycoraxParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SycoraxParser.COMMA); }
		public TerminalNode DEFINES() { return getToken(SycoraxParser.DEFINES, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public FunDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterFunDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitFunDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitFunDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunDefContext funDef() throws RecognitionException {
		FunDefContext _localctx = new FunDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56); match(FUNCTION);
			setState(57); match(ID);
			setState(70);
			_la = _input.LA(1);
			if (_la==USES) {
				{
				setState(58); match(USES);
				setState(59); match(LPAR);
				{
				setState(60); arg();
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(61); match(COMMA);
					setState(62); arg();
					}
					}
					setState(67);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(68); match(RPAR);
				}
			}

			setState(74);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(72); match(RETURNS);
				setState(73); type();
				}
			}

			setState(77);
			_la = _input.LA(1);
			if (_la==CATCHABLE) {
				{
				setState(76); match(CATCHABLE);
				}
			}

			setState(79); match(DEFINES);
			setState(80); match(LBRACE);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(81); stat();
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89); type();
			setState(90); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BlockStatContext extends StatContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PointerStatContext extends StatContext {
		public TerminalNode POINTER() { return getToken(SycoraxParser.POINTER, 0); }
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public TerminalNode TO() { return getToken(SycoraxParser.TO, 0); }
		public PointerStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterPointerStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitPointerStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitPointerStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintStatContext extends StatContext {
		public TerminalNode PRINT() { return getToken(SycoraxParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterPrintStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitPrintStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitPrintStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallStatContext extends StatContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode WITH() { return getToken(SycoraxParser.WITH, 0); }
		public TerminalNode CALL() { return getToken(SycoraxParser.CALL, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public CallStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCallStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCallStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCallStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FailStatContext extends StatContext {
		public TerminalNode FAIL() { return getToken(SycoraxParser.FAIL, 0); }
		public FailStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterFailStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitFailStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitFailStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VardefStatContext extends StatContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VardefStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterVardefStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitVardefStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitVardefStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForkStatContext extends StatContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode FORK() { return getToken(SycoraxParser.FORK, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForkStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterForkStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitForkStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitForkStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JoinStatContext extends StatContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode JOIN() { return getToken(SycoraxParser.JOIN, 0); }
		public JoinStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterJoinStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitJoinStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitJoinStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfstatContext extends StatContext {
		public TerminalNode ELSE() { return getToken(SycoraxParser.ELSE, 0); }
		public TerminalNode IF() { return getToken(SycoraxParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode THEN() { return getToken(SycoraxParser.THEN, 0); }
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public IfstatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIfstat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIfstat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIfstat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockStatContext extends StatContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode LOCK() { return getToken(SycoraxParser.LOCK, 0); }
		public LockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterLockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitLockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitLockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStatContext extends StatContext {
		public TerminalNode RETURN() { return getToken(SycoraxParser.RETURN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ReturnStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterReturnStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitReturnStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitReturnStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AssignStatContext extends StatContext {
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterAssignStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitAssignStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitAssignStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnlockStatContext extends StatContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode UNLOCK() { return getToken(SycoraxParser.UNLOCK, 0); }
		public UnlockStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterUnlockStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitUnlockStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitUnlockStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public TerminalNode WHILE() { return getToken(SycoraxParser.WHILE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterWhileStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitWhileStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitWhileStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stat);
		int _la;
		try {
			setState(140);
			switch (_input.LA(1)) {
			case DO:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(92); block();
				}
				break;
			case LET:
				_localctx = new VardefStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(93); varDef();
				}
				break;
			case FAIL:
				_localctx = new FailStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(94); match(FAIL);
				}
				break;
			case FORK:
				_localctx = new ForkStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(95); match(FORK);
				setState(96); match(ID);
				setState(97); block();
				}
				break;
			case JOIN:
				_localctx = new JoinStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(98); match(JOIN);
				setState(99); match(ID);
				}
				break;
			case LOCK:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(100); match(LOCK);
				setState(101); match(ID);
				}
				break;
			case UNLOCK:
				_localctx = new UnlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(102); match(UNLOCK);
				setState(103); match(ID);
				}
				break;
			case RETURN:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(104); match(RETURN);
				setState(106);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(105); expr(0);
					}
					break;
				}
				}
				break;
			case ID:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(108); target();
				setState(109); match(ASSIGN);
				setState(110); expr(0);
				}
				break;
			case POINTER:
				_localctx = new PointerStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(112); match(POINTER);
				setState(113); match(ID);
				setState(114); match(TO);
				setState(115); target();
				}
				break;
			case IF:
				_localctx = new IfstatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(116); match(IF);
				setState(117); match(LPAR);
				setState(118); expr(0);
				setState(119); match(RPAR);
				setState(120); match(THEN);
				setState(121); block();
				setState(124);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(122); match(ELSE);
					setState(123); block();
					}
				}

				}
				break;
			case WHILE:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(126); match(WHILE);
				setState(127); match(LPAR);
				setState(128); expr(0);
				setState(129); match(RPAR);
				setState(130); block();
				}
				break;
			case CALL:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(132); match(CALL);
				setState(133); match(ID);
				setState(136);
				_la = _input.LA(1);
				if (_la==WITH) {
					{
					setState(134); match(WITH);
					setState(135); args();
					}
				}

				}
				break;
			case PRINT:
				_localctx = new PrintStatContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(138); match(PRINT);
				setState(139); expr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StrExprContext extends ExprContext {
		public TerminalNode STR() { return getToken(SycoraxParser.STR, 0); }
		public StrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterStrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitStrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitStrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharExprContext extends ExprContext {
		public TerminalNode CHAR() { return getToken(SycoraxParser.CHAR, 0); }
		public CharExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCharExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCharExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCharExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompOpExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public CompOpContext compOp() {
			return getRuleContext(CompOpContext.class,0);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public CompOpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCompOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCompOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCompOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SizeExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SIZE() { return getToken(SycoraxParser.SIZE, 0); }
		public SizeExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterSizeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitSizeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitSizeExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayExprContext extends ExprContext {
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public ArrayExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArrayExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArrayExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArrayExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TrueExprContext extends ExprContext {
		public TerminalNode TRUE() { return getToken(SycoraxParser.TRUE, 0); }
		public TrueExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterTrueExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitTrueExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitTrueExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolOpExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BoolOpContext boolOp() {
			return getRuleContext(BoolOpContext.class,0);
		}
		public BoolOpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBoolOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBoolOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBoolOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExprContext extends ExprContext {
		public TerminalNode NEGATIVE() { return getToken(SycoraxParser.NEGATIVE, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public NumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterNumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitNumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(SycoraxParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(SycoraxParser.LBRACK, 0); }
		public IndexExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIndexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIndexExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public ParExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterParExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitParExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitParExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExprContext extends ExprContext {
		public TerminalNode NOT() { return getToken(SycoraxParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NotExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntOpExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IntOpContext intOp() {
			return getRuleContext(IntOpContext.class,0);
		}
		public IntOpExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIntOpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIntOpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIntOpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CallExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode WITH() { return getToken(SycoraxParser.WITH, 0); }
		public TerminalNode CALL() { return getToken(SycoraxParser.CALL, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public CallExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCallExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCallExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCallExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FalseExprContext extends ExprContext {
		public TerminalNode FALSE() { return getToken(SycoraxParser.FALSE, 0); }
		public FalseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterFalseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitFalseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitFalseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new SizeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(143); match(SIZE);
				setState(144); expr(10);
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145); match(NOT);
				setState(146); expr(1);
				}
				break;
			case 3:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147); match(LPAR);
				setState(148); expr(0);
				setState(149); match(RPAR);
				}
				break;
			case 4:
				{
				_localctx = new ArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(151); array();
				}
				break;
			case 5:
				{
				_localctx = new IndexExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152); match(ID);
				setState(153); match(LBRACK);
				setState(154); expr(0);
				setState(155); match(RBRACK);
				}
				break;
			case 6:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157); match(CALL);
				setState(158); match(ID);
				setState(161);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(159); match(WITH);
					setState(160); args();
					}
					break;
				}
				}
				break;
			case 7:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163); match(ID);
				}
				break;
			case 8:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(165);
				_la = _input.LA(1);
				if (_la==NEGATIVE) {
					{
					setState(164); match(NEGATIVE);
					}
				}

				setState(167); match(NUM);
				}
				break;
			case 9:
				{
				_localctx = new CharExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(168); match(CHAR);
				}
				break;
			case 10:
				{
				_localctx = new StrExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169); match(STR);
				}
				break;
			case 11:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(170); match(TRUE);
				}
				break;
			case 12:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(171); match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(186);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(174);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(175); boolOp();
						setState(176); expr(15);
						}
						break;
					case 2:
						{
						_localctx = new IntOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(178);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(179); intOp();
						setState(180); expr(14);
						}
						break;
					case 3:
						{
						_localctx = new CompOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(182);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(183); compOp();
						setState(184); expr(13);
						}
						break;
					}
					} 
				}
				setState(190);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BoolOpContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(SycoraxParser.AND, 0); }
		public TerminalNode OR() { return getToken(SycoraxParser.OR, 0); }
		public BoolOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBoolOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBoolOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBoolOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolOpContext boolOp() throws RecognitionException {
		BoolOpContext _localctx = new BoolOpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntOpContext extends ParserRuleContext {
		public TerminalNode TIMES() { return getToken(SycoraxParser.TIMES, 0); }
		public TerminalNode PLUS() { return getToken(SycoraxParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(SycoraxParser.MINUS, 0); }
		public IntOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIntOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIntOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIntOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntOpContext intOp() throws RecognitionException {
		IntOpContext _localctx = new IntOpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_intOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << TIMES))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompOpContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(SycoraxParser.EQUALS, 0); }
		public TerminalNode GREATER() { return getToken(SycoraxParser.GREATER, 0); }
		public TerminalNode SMALLER() { return getToken(SycoraxParser.SMALLER, 0); }
		public CompOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCompOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCompOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCompOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompOpContext compOp() throws RecognitionException {
		CompOpContext _localctx = new CompOpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_compOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << GREATER) | (1L << SMALLER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SycoraxParser.COMMA); }
		public TerminalNode RBRACK() { return getToken(SycoraxParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(SycoraxParser.LBRACK, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SycoraxParser.COMMA, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197); match(LBRACK);
			setState(206);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << CALL) | (1L << SIZE) | (1L << LPAR) | (1L << LBRACK) | (1L << NEGATIVE) | (1L << ID) | (1L << NUM) | (1L << STR) | (1L << CHAR))) != 0)) {
				{
				setState(198); expr(0);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(199); match(COMMA);
					setState(200); expr(0);
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(208); match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SycoraxParser.COMMA); }
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SycoraxParser.COMMA, i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210); match(LPAR);
			{
			setState(211); expr(0);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212); match(COMMA);
				setState(213); expr(0);
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(219); match(RPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TargetContext extends ParserRuleContext {
		public TargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_target; }
	 
		public TargetContext() { }
		public void copyFrom(TargetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdTargetContext extends TargetContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public IdTargetContext(TargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIdTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIdTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIdTarget(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayTargetContext extends TargetContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(SycoraxParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(SycoraxParser.LBRACK, 0); }
		public ArrayTargetContext(TargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArrayTarget(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArrayTarget(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArrayTarget(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TargetContext target() throws RecognitionException {
		TargetContext _localctx = new TargetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_target);
		try {
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new IdTargetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(221); match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayTargetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222); match(ID);
				setState(223); match(LBRACK);
				setState(224); expr(0);
				setState(225); match(RBRACK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode RBRACE(int i) {
			return getToken(SycoraxParser.RBRACE, i);
		}
		public List<TerminalNode> RBRACE() { return getTokens(SycoraxParser.RBRACE); }
		public TerminalNode DO() { return getToken(SycoraxParser.DO, 0); }
		public List<TerminalNode> LBRACE() { return getTokens(SycoraxParser.LBRACE); }
		public TerminalNode CATCH() { return getToken(SycoraxParser.CATCH, 0); }
		public TerminalNode LBRACE(int i) {
			return getToken(SycoraxParser.LBRACE, i);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode FINALLY() { return getToken(SycoraxParser.FINALLY, 0); }
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229); match(DO);
			setState(230); match(LBRACE);
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(231); stat();
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(237); match(RBRACE);
			setState(247);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(238); match(CATCH);
				setState(239); match(LBRACE);
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << PRINT) | (1L << ID))) != 0)) {
					{
					{
					setState(240); stat();
					}
					}
					setState(245);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(246); match(RBRACE);
				}
			}

			setState(258);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(249); match(FINALLY);
				setState(250); match(LBRACE);
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << PRINT) | (1L << ID))) != 0)) {
					{
					{
					setState(251); stat();
					}
					}
					setState(256);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(257); match(RBRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharTypeContext extends TypeContext {
		public TerminalNode CHARACTER() { return getToken(SycoraxParser.CHARACTER, 0); }
		public CharTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCharType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCharType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCharType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CharArrTypeContext extends TypeContext {
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public TerminalNode CHARACTERS() { return getToken(SycoraxParser.CHARACTERS, 0); }
		public CharArrTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCharArrType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCharArrType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCharArrType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntTypeContext extends TypeContext {
		public TerminalNode INTEGER() { return getToken(SycoraxParser.INTEGER, 0); }
		public IntTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIntType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIntType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIntType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntArrTypeContext extends TypeContext {
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public TerminalNode INTEGERS() { return getToken(SycoraxParser.INTEGERS, 0); }
		public IntArrTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIntArrType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIntArrType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIntArrType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringTypeContext extends TypeContext {
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public TerminalNode STRING() { return getToken(SycoraxParser.STRING, 0); }
		public StringTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterStringType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitStringType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitStringType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolArrTypeContext extends TypeContext {
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public TerminalNode BOOLEANS() { return getToken(SycoraxParser.BOOLEANS, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public BoolArrTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBoolArrType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBoolArrType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBoolArrType(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolTypeContext extends TypeContext {
		public TerminalNode BOOLEAN() { return getToken(SycoraxParser.BOOLEAN, 0); }
		public BoolTypeContext(TypeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBoolType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBoolType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBoolType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_type);
		try {
			setState(275);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(260); match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(261); match(BOOLEAN);
				}
				break;
			case CHARACTER:
				_localctx = new CharTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(262); match(CHARACTER);
				}
				break;
			case INTEGERS:
				_localctx = new IntArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(263); match(INTEGERS);
				setState(264); match(SIZED);
				setState(265); match(NUM);
				}
				break;
			case BOOLEANS:
				_localctx = new BoolArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(266); match(BOOLEANS);
				setState(267); match(SIZED);
				setState(268); match(NUM);
				}
				break;
			case CHARACTERS:
				_localctx = new CharArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(269); match(CHARACTERS);
				setState(270); match(SIZED);
				setState(271); match(NUM);
				}
				break;
			case STRING:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(272); match(STRING);
				setState(273); match(SIZED);
				setState(274); match(NUM);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 14);
		case 1: return precpred(_ctx, 13);
		case 2: return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3C\u0118\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\3\3\3\7\3+\n\3\f\3\16\3.\13\3\3\4\3\4\5\4\62\n\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\49\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5B\n\5\f\5\16\5"+
		"E\13\5\3\5\3\5\5\5I\n\5\3\5\3\5\5\5M\n\5\3\5\5\5P\n\5\3\5\3\5\3\5\7\5"+
		"U\n\5\f\5\16\5X\13\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7m\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\177\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7\u008b\n\7\3\7\3\7\5\7\u008f\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a4\n\b\3"+
		"\b\3\b\5\b\u00a8\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u00af\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00bd\n\b\f\b\16\b\u00c0\13\b\3\t"+
		"\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\7\f\u00cc\n\f\f\f\16\f\u00cf\13"+
		"\f\5\f\u00d1\n\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u00d9\n\r\f\r\16\r\u00dc"+
		"\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e6\n\16\3\17\3\17"+
		"\3\17\7\17\u00eb\n\17\f\17\16\17\u00ee\13\17\3\17\3\17\3\17\3\17\7\17"+
		"\u00f4\n\17\f\17\16\17\u00f7\13\17\3\17\5\17\u00fa\n\17\3\17\3\17\3\17"+
		"\7\17\u00ff\n\17\f\17\16\17\u0102\13\17\3\17\5\17\u0105\n\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u0116\n\20\3\20\2\3\16\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\5"+
		"\3\2\6\7\3\2+-\3\2.\60\u0140\2 \3\2\2\2\4,\3\2\2\2\6/\3\2\2\2\b:\3\2\2"+
		"\2\n[\3\2\2\2\f\u008e\3\2\2\2\16\u00ae\3\2\2\2\20\u00c1\3\2\2\2\22\u00c3"+
		"\3\2\2\2\24\u00c5\3\2\2\2\26\u00c7\3\2\2\2\30\u00d4\3\2\2\2\32\u00e5\3"+
		"\2\2\2\34\u00e7\3\2\2\2\36\u0115\3\2\2\2 !\7%\2\2!\"\7>\2\2\"#\7!\2\2"+
		"#$\7\67\2\2$%\5\4\3\2%&\78\2\2&\'\7\2\2\3\'\3\3\2\2\2(+\5\b\5\2)+\5\6"+
		"\4\2*(\3\2\2\2*)\3\2\2\2+.\3\2\2\2,*\3\2\2\2,-\3\2\2\2-\5\3\2\2\2.,\3"+
		"\2\2\2/\61\7\25\2\2\60\62\7\61\2\2\61\60\3\2\2\2\61\62\3\2\2\2\62\63\3"+
		"\2\2\2\63\64\7>\2\2\64\65\7\30\2\2\658\5\36\20\2\66\67\7\31\2\2\679\5"+
		"\16\b\28\66\3\2\2\289\3\2\2\29\7\3\2\2\2:;\7\35\2\2;H\7>\2\2<=\7\36\2"+
		"\2=>\79\2\2>C\5\n\6\2?@\7\64\2\2@B\5\n\6\2A?\3\2\2\2BE\3\2\2\2CA\3\2\2"+
		"\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7:\2\2GI\3\2\2\2H<\3\2\2\2HI\3\2\2"+
		"\2IL\3\2\2\2JK\7\37\2\2KM\5\36\20\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP\7"+
		" \2\2ON\3\2\2\2OP\3\2\2\2PQ\3\2\2\2QR\7!\2\2RV\7\67\2\2SU\5\f\7\2TS\3"+
		"\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\78\2\2Z\t\3"+
		"\2\2\2[\\\5\36\20\2\\]\7>\2\2]\13\3\2\2\2^\u008f\5\34\17\2_\u008f\5\6"+
		"\4\2`\u008f\7\23\2\2ab\7\'\2\2bc\7>\2\2c\u008f\5\34\17\2de\7(\2\2e\u008f"+
		"\7>\2\2fg\7)\2\2g\u008f\7>\2\2hi\7*\2\2i\u008f\7>\2\2jl\7\"\2\2km\5\16"+
		"\b\2lk\3\2\2\2lm\3\2\2\2m\u008f\3\2\2\2no\5\32\16\2op\7\31\2\2pq\5\16"+
		"\b\2q\u008f\3\2\2\2rs\7\33\2\2st\7>\2\2tu\7\34\2\2u\u008f\5\32\16\2vw"+
		"\7\16\2\2wx\79\2\2xy\5\16\b\2yz\7:\2\2z{\7\17\2\2{~\5\34\17\2|}\7\20\2"+
		"\2}\177\5\34\17\2~|\3\2\2\2~\177\3\2\2\2\177\u008f\3\2\2\2\u0080\u0081"+
		"\7&\2\2\u0081\u0082\79\2\2\u0082\u0083\5\16\b\2\u0083\u0084\7:\2\2\u0084"+
		"\u0085\5\34\17\2\u0085\u008f\3\2\2\2\u0086\u0087\7#\2\2\u0087\u008a\7"+
		">\2\2\u0088\u0089\7$\2\2\u0089\u008b\5\30\r\2\u008a\u0088\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008f\3\2\2\2\u008c\u008d\7\63\2\2\u008d\u008f\5"+
		"\16\b\2\u008e^\3\2\2\2\u008e_\3\2\2\2\u008e`\3\2\2\2\u008ea\3\2\2\2\u008e"+
		"d\3\2\2\2\u008ef\3\2\2\2\u008eh\3\2\2\2\u008ej\3\2\2\2\u008en\3\2\2\2"+
		"\u008er\3\2\2\2\u008ev\3\2\2\2\u008e\u0080\3\2\2\2\u008e\u0086\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008f\r\3\2\2\2\u0090\u0091\b\b\1\2\u0091\u0092\7"+
		"\62\2\2\u0092\u00af\5\16\b\f\u0093\u0094\7\5\2\2\u0094\u00af\5\16\b\3"+
		"\u0095\u0096\79\2\2\u0096\u0097\5\16\b\2\u0097\u0098\7:\2\2\u0098\u00af"+
		"\3\2\2\2\u0099\u00af\5\26\f\2\u009a\u009b\7>\2\2\u009b\u009c\7;\2\2\u009c"+
		"\u009d\5\16\b\2\u009d\u009e\7<\2\2\u009e\u00af\3\2\2\2\u009f\u00a0\7#"+
		"\2\2\u00a0\u00a3\7>\2\2\u00a1\u00a2\7$\2\2\u00a2\u00a4\5\30\r\2\u00a3"+
		"\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00af\3\2\2\2\u00a5\u00af\7>"+
		"\2\2\u00a6\u00a8\7=\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00af\7?\2\2\u00aa\u00af\7A\2\2\u00ab\u00af\7@\2"+
		"\2\u00ac\u00af\7\3\2\2\u00ad\u00af\7\4\2\2\u00ae\u0090\3\2\2\2\u00ae\u0093"+
		"\3\2\2\2\u00ae\u0095\3\2\2\2\u00ae\u0099\3\2\2\2\u00ae\u009a\3\2\2\2\u00ae"+
		"\u009f\3\2\2\2\u00ae\u00a5\3\2\2\2\u00ae\u00a7\3\2\2\2\u00ae\u00aa\3\2"+
		"\2\2\u00ae\u00ab\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00ad\3\2\2\2\u00af"+
		"\u00be\3\2\2\2\u00b0\u00b1\f\20\2\2\u00b1\u00b2\5\20\t\2\u00b2\u00b3\5"+
		"\16\b\21\u00b3\u00bd\3\2\2\2\u00b4\u00b5\f\17\2\2\u00b5\u00b6\5\22\n\2"+
		"\u00b6\u00b7\5\16\b\20\u00b7\u00bd\3\2\2\2\u00b8\u00b9\f\16\2\2\u00b9"+
		"\u00ba\5\24\13\2\u00ba\u00bb\5\16\b\17\u00bb\u00bd\3\2\2\2\u00bc\u00b0"+
		"\3\2\2\2\u00bc\u00b4\3\2\2\2\u00bc\u00b8\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\17\3\2\2\2\u00c0\u00be\3\2\2"+
		"\2\u00c1\u00c2\t\2\2\2\u00c2\21\3\2\2\2\u00c3\u00c4\t\3\2\2\u00c4\23\3"+
		"\2\2\2\u00c5\u00c6\t\4\2\2\u00c6\25\3\2\2\2\u00c7\u00d0\7;\2\2\u00c8\u00cd"+
		"\5\16\b\2\u00c9\u00ca\7\64\2\2\u00ca\u00cc\5\16\b\2\u00cb\u00c9\3\2\2"+
		"\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1"+
		"\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00c8\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1"+
		"\u00d2\3\2\2\2\u00d2\u00d3\7<\2\2\u00d3\27\3\2\2\2\u00d4\u00d5\79\2\2"+
		"\u00d5\u00da\5\16\b\2\u00d6\u00d7\7\64\2\2\u00d7\u00d9\5\16\b\2\u00d8"+
		"\u00d6\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\7:\2\2\u00de"+
		"\31\3\2\2\2\u00df\u00e6\7>\2\2\u00e0\u00e1\7>\2\2\u00e1\u00e2\7;\2\2\u00e2"+
		"\u00e3\5\16\b\2\u00e3\u00e4\7<\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00df\3\2"+
		"\2\2\u00e5\u00e0\3\2\2\2\u00e6\33\3\2\2\2\u00e7\u00e8\7\21\2\2\u00e8\u00ec"+
		"\7\67\2\2\u00e9\u00eb\5\f\7\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2"+
		"\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00ec"+
		"\3\2\2\2\u00ef\u00f9\78\2\2\u00f0\u00f1\7\22\2\2\u00f1\u00f5\7\67\2\2"+
		"\u00f2\u00f4\5\f\7\2\u00f3\u00f2\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3"+
		"\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f8"+
		"\u00fa\78\2\2\u00f9\u00f0\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u0104\3\2"+
		"\2\2\u00fb\u00fc\7\24\2\2\u00fc\u0100\7\67\2\2\u00fd\u00ff\5\f\7\2\u00fe"+
		"\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2"+
		"\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0105\78\2\2\u0104"+
		"\u00fb\3\2\2\2\u0104\u0105\3\2\2\2\u0105\35\3\2\2\2\u0106\u0116\7\t\2"+
		"\2\u0107\u0116\7\b\2\2\u0108\u0116\7\n\2\2\u0109\u010a\7\f\2\2\u010a\u010b"+
		"\7\32\2\2\u010b\u0116\7?\2\2\u010c\u010d\7\13\2\2\u010d\u010e\7\32\2\2"+
		"\u010e\u0116\7?\2\2\u010f\u0110\7\r\2\2\u0110\u0111\7\32\2\2\u0111\u0116"+
		"\7?\2\2\u0112\u0113\7\27\2\2\u0113\u0114\7\32\2\2\u0114\u0116\7?\2\2\u0115"+
		"\u0106\3\2\2\2\u0115\u0107\3\2\2\2\u0115\u0108\3\2\2\2\u0115\u0109\3\2"+
		"\2\2\u0115\u010c\3\2\2\2\u0115\u010f\3\2\2\2\u0115\u0112\3\2\2\2\u0116"+
		"\37\3\2\2\2\36*,\618CHLOVl~\u008a\u008e\u00a3\u00a7\u00ae\u00bc\u00be"+
		"\u00cd\u00d0\u00da\u00e5\u00ec\u00f5\u00f9\u0100\u0104\u0115";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}