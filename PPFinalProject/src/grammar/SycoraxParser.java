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
		JOIN=38, LOCK=39, UNLOCK=40, PLUS=41, MINUS=42, TIMES=43, EQUALS=44, UNEQUALS=45, 
		GREATER=46, SMALLER=47, GLOBAL=48, SIZE=49, PRINT=50, READ=51, COMMA=52, 
		DQUOTE=53, SQUOTE=54, LBRACE=55, RBRACE=56, LPAR=57, RPAR=58, LBRACK=59, 
		RBRACK=60, NEGATIVE=61, ID=62, NUM=63, STR=64, CHAR=65, COMMENT=66, WS=67;
	public static final String[] tokenNames = {
		"<INVALID>", "TRUE", "FALSE", "NOT", "AND", "OR", "BOOLEAN", "INTEGER", 
		"CHARACTER", "BOOLEANS", "INTEGERS", "CHARACTERS", "IF", "THEN", "ELSE", 
		"DO", "CATCH", "FAIL", "FINALLY", "LET", "ARRAY", "STRING", "BE", "ASSIGN", 
		"SIZED", "POINTER", "TO", "FUNCTION", "USES", "RETURNS", "CATCHABLE", 
		"DEFINES", "RETURN", "CALL", "WITH", "PROGRAM", "WHILE", "FORK", "JOIN", 
		"LOCK", "UNLOCK", "PLUS", "MINUS", "TIMES", "EQUALS", "UNEQUALS", "GREATER", 
		"SMALLER", "GLOBAL", "SIZE", "PRINT", "READ", "','", "'\"'", "'''", "'{'", 
		"'}'", "'('", "')'", "'['", "']'", "'-'", "ID", "NUM", "STR", "CHAR", 
		"COMMENT", "WS"
	};
	public static final int
		RULE_program = 0, RULE_defs = 1, RULE_varDef = 2, RULE_funDef = 3, RULE_funType = 4, 
		RULE_params = 5, RULE_param = 6, RULE_stat = 7, RULE_pointerTarget = 8, 
		RULE_expr = 9, RULE_boolOp = 10, RULE_intOp = 11, RULE_compOp = 12, RULE_array = 13, 
		RULE_args = 14, RULE_target = 15, RULE_block = 16, RULE_content = 17, 
		RULE_type = 18, RULE_basicType = 19, RULE_arrayType = 20;
	public static final String[] ruleNames = {
		"program", "defs", "varDef", "funDef", "funType", "params", "param", "stat", 
		"pointerTarget", "expr", "boolOp", "intOp", "compOp", "array", "args", 
		"target", "block", "content", "type", "basicType", "arrayType"
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
			setState(42); match(PROGRAM);
			setState(43); match(ID);
			setState(44); match(DEFINES);
			setState(45); match(LBRACE);
			setState(46); defs();
			setState(47); match(RBRACE);
			setState(48); match(EOF);
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
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DO) | (1L << LET) | (1L << FUNCTION))) != 0)) {
				{
				setState(53);
				switch (_input.LA(1)) {
				case FUNCTION:
					{
					setState(50); funDef();
					}
					break;
				case LET:
					{
					setState(51); varDef();
					}
					break;
				case DO:
					{
					setState(52); block();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(57);
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
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
	 
		public VarDefContext() { }
		public void copyFrom(VarDefContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayDefContext extends VarDefContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode BE() { return getToken(SycoraxParser.BE, 0); }
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
		public TerminalNode LET() { return getToken(SycoraxParser.LET, 0); }
		public ArrayDefContext(VarDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterArrayDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitArrayDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitArrayDef(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BasicDefContext extends VarDefContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode BE() { return getToken(SycoraxParser.BE, 0); }
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public TerminalNode LET() { return getToken(SycoraxParser.LET, 0); }
		public BasicDefContext(VarDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterBasicDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitBasicDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitBasicDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varDef);
		int _la;
		try {
			setState(82);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new BasicDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(58); match(LET);
				setState(60);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(59); match(GLOBAL);
					}
				}

				setState(62); match(ID);
				setState(63); match(BE);
				setState(64); basicType();
				setState(67);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(65); match(ASSIGN);
					setState(66); expr(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new ArrayDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69); match(LET);
				setState(71);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(70); match(GLOBAL);
					}
				}

				setState(73); match(ID);
				setState(74); match(BE);
				setState(75); arrayType();
				setState(80);
				switch (_input.LA(1)) {
				case SIZED:
					{
					setState(76); match(SIZED);
					setState(77); expr(0);
					}
					break;
				case ASSIGN:
					{
					setState(78); match(ASSIGN);
					setState(79); expr(0);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	public static class FunDefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public TerminalNode FUNCTION() { return getToken(SycoraxParser.FUNCTION, 0); }
		public TerminalNode RBRACE() { return getToken(SycoraxParser.RBRACE, 0); }
		public TerminalNode LBRACE() { return getToken(SycoraxParser.LBRACE, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode DEFINES() { return getToken(SycoraxParser.DEFINES, 0); }
		public TerminalNode CATCHABLE() { return getToken(SycoraxParser.CATCHABLE, 0); }
		public TerminalNode RETURNS() { return getToken(SycoraxParser.RETURNS, 0); }
		public TerminalNode USES() { return getToken(SycoraxParser.USES, 0); }
		public FunTypeContext funType() {
			return getRuleContext(FunTypeContext.class,0);
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
			setState(84); match(FUNCTION);
			setState(85); match(ID);
			setState(88);
			_la = _input.LA(1);
			if (_la==USES) {
				{
				setState(86); match(USES);
				setState(87); params();
				}
			}

			setState(92);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(90); match(RETURNS);
				setState(91); funType();
				}
			}

			setState(95);
			_la = _input.LA(1);
			if (_la==CATCHABLE) {
				{
				setState(94); match(CATCHABLE);
				}
			}

			setState(97); match(DEFINES);
			setState(98); match(LBRACE);
			setState(99); content();
			setState(100); match(RBRACE);
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

	public static class FunTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterFunType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitFunType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitFunType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunTypeContext funType() throws RecognitionException {
		FunTypeContext _localctx = new FunTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_funType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102); type();
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

	public static class ParamsContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(SycoraxParser.COMMA); }
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(SycoraxParser.RPAR, 0); }
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public TerminalNode COMMA(int i) {
			return getToken(SycoraxParser.COMMA, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104); match(LPAR);
			{
			setState(105); param();
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(106); match(COMMA);
				setState(107); param();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(113); match(RPAR);
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

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); type();
			setState(116); match(ID);
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
		public PointerTargetContext pointerTarget() {
			return getRuleContext(PointerTargetContext.class,0);
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
		enterRule(_localctx, 14, RULE_stat);
		int _la;
		try {
			setState(166);
			switch (_input.LA(1)) {
			case DO:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(118); block();
				}
				break;
			case LET:
				_localctx = new VardefStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(119); varDef();
				}
				break;
			case FAIL:
				_localctx = new FailStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(120); match(FAIL);
				}
				break;
			case FORK:
				_localctx = new ForkStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(121); match(FORK);
				setState(122); match(ID);
				setState(123); block();
				}
				break;
			case JOIN:
				_localctx = new JoinStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(124); match(JOIN);
				setState(125); match(ID);
				}
				break;
			case LOCK:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(126); match(LOCK);
				setState(127); match(ID);
				}
				break;
			case UNLOCK:
				_localctx = new UnlockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(128); match(UNLOCK);
				setState(129); match(ID);
				}
				break;
			case RETURN:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(130); match(RETURN);
				setState(132);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(131); expr(0);
					}
					break;
				}
				}
				break;
			case GLOBAL:
			case ID:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(134); target();
				setState(135); match(ASSIGN);
				setState(136); expr(0);
				}
				break;
			case POINTER:
				_localctx = new PointerStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(138); match(POINTER);
				setState(139); match(ID);
				setState(140); match(TO);
				setState(141); pointerTarget();
				}
				break;
			case IF:
				_localctx = new IfstatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(142); match(IF);
				setState(143); match(LPAR);
				setState(144); expr(0);
				setState(145); match(RPAR);
				setState(146); match(THEN);
				setState(147); block();
				setState(150);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(148); match(ELSE);
					setState(149); block();
					}
				}

				}
				break;
			case WHILE:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(152); match(WHILE);
				setState(153); match(LPAR);
				setState(154); expr(0);
				setState(155); match(RPAR);
				setState(156); block();
				}
				break;
			case CALL:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(158); match(CALL);
				setState(159); match(ID);
				setState(162);
				_la = _input.LA(1);
				if (_la==WITH) {
					{
					setState(160); match(WITH);
					setState(161); args();
					}
				}

				}
				break;
			case PRINT:
				_localctx = new PrintStatContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(164); match(PRINT);
				setState(165); expr(0);
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

	public static class PointerTargetContext extends ParserRuleContext {
		public PointerTargetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointerTarget; }
	 
		public PointerTargetContext() { }
		public void copyFrom(PointerTargetContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IdPointerContext extends PointerTargetContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public IdPointerContext(PointerTargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIdPointer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIdPointer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIdPointer(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexPointerContext extends PointerTargetContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(SycoraxParser.RBRACK, 0); }
		public TerminalNode LBRACK() { return getToken(SycoraxParser.LBRACK, 0); }
		public IndexPointerContext(PointerTargetContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterIndexPointer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitIndexPointer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitIndexPointer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PointerTargetContext pointerTarget() throws RecognitionException {
		PointerTargetContext _localctx = new PointerTargetContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_pointerTarget);
		try {
			setState(174);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new IdPointerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(168); match(ID);
				}
				break;
			case 2:
				_localctx = new IndexPointerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(169); match(ID);
				setState(170); match(LBRACK);
				setState(171); expr(0);
				setState(172); match(RBRACK);
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
	public static class ReadExprContext extends ExprContext {
		public TerminalNode READ() { return getToken(SycoraxParser.READ, 0); }
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public ReadExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterReadExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitReadExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitReadExpr(this);
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
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
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
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
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
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				_localctx = new SizeExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(177); match(SIZE);
				setState(178); expr(9);
				}
				break;
			case 2:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179); match(NOT);
				setState(180); expr(1);
				}
				break;
			case 3:
				{
				_localctx = new ParExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181); match(LPAR);
				setState(182); expr(0);
				setState(183); match(RPAR);
				}
				break;
			case 4:
				{
				_localctx = new ArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(185); array();
				}
				break;
			case 5:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(186); match(GLOBAL);
					}
				}

				setState(189); match(ID);
				}
				break;
			case 6:
				{
				_localctx = new IndexExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(190); match(GLOBAL);
					}
				}

				setState(193); match(ID);
				setState(194); match(LBRACK);
				setState(195); expr(0);
				setState(196); match(RBRACK);
				}
				break;
			case 7:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198); match(CALL);
				setState(199); match(ID);
				setState(202);
				switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
				case 1:
					{
					setState(200); match(WITH);
					setState(201); args();
					}
					break;
				}
				}
				break;
			case 8:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205);
				_la = _input.LA(1);
				if (_la==NEGATIVE) {
					{
					setState(204); match(NEGATIVE);
					}
				}

				setState(207); match(NUM);
				}
				break;
			case 9:
				{
				_localctx = new ReadExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(208); match(READ);
				setState(209); basicType();
				}
				break;
			case 10:
				{
				_localctx = new CharExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(210); match(CHAR);
				}
				break;
			case 11:
				{
				_localctx = new StrExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(211); match(STR);
				}
				break;
			case 12:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(212); match(TRUE);
				}
				break;
			case 13:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(213); match(FALSE);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(228);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new BoolOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(216);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(217); boolOp();
						setState(218); expr(16);
						}
						break;
					case 2:
						{
						_localctx = new IntOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(220);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(221); intOp();
						setState(222); expr(15);
						}
						break;
					case 3:
						{
						_localctx = new CompOpExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(224);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(225); compOp();
						setState(226); expr(14);
						}
						break;
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		enterRule(_localctx, 20, RULE_boolOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
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
		enterRule(_localctx, 22, RULE_intOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
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
		public TerminalNode UNEQUALS() { return getToken(SycoraxParser.UNEQUALS, 0); }
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
		enterRule(_localctx, 24, RULE_compOp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUALS) | (1L << UNEQUALS) | (1L << GREATER) | (1L << SMALLER))) != 0)) ) {
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
		enterRule(_localctx, 26, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239); match(LBRACK);
			setState(248);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << CALL) | (1L << GLOBAL) | (1L << SIZE) | (1L << READ) | (1L << LPAR) | (1L << LBRACK) | (1L << NEGATIVE) | (1L << ID) | (1L << NUM))) != 0) || _la==STR || _la==CHAR) {
				{
				setState(240); expr(0);
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(241); match(COMMA);
					setState(242); expr(0);
					}
					}
					setState(247);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(250); match(RBRACK);
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
		enterRule(_localctx, 28, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252); match(LPAR);
			{
			setState(253); expr(0);
			setState(258);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(254); match(COMMA);
				setState(255); expr(0);
				}
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(261); match(RPAR);
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
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
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
		public TerminalNode GLOBAL() { return getToken(SycoraxParser.GLOBAL, 0); }
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
		enterRule(_localctx, 30, RULE_target);
		int _la;
		try {
			setState(275);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				_localctx = new IdTargetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(263); match(GLOBAL);
					}
				}

				setState(266); match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayTargetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(268);
				_la = _input.LA(1);
				if (_la==GLOBAL) {
					{
					setState(267); match(GLOBAL);
					}
				}

				setState(270); match(ID);
				setState(271); match(LBRACK);
				setState(272); expr(0);
				setState(273); match(RBRACK);
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
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public TerminalNode CATCH() { return getToken(SycoraxParser.CATCH, 0); }
		public TerminalNode LBRACE(int i) {
			return getToken(SycoraxParser.LBRACE, i);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
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
		enterRule(_localctx, 32, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277); match(DO);
			setState(278); match(LBRACE);
			setState(279); content();
			setState(280); match(RBRACE);
			setState(286);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(281); match(CATCH);
				setState(282); match(LBRACE);
				setState(283); content();
				setState(284); match(RBRACE);
				}
			}

			setState(293);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(288); match(FINALLY);
				setState(289); match(LBRACE);
				setState(290); content();
				setState(291); match(RBRACE);
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

	public static class ContentContext extends ParserRuleContext {
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << GLOBAL) | (1L << PRINT) | (1L << ID))) != 0)) {
				{
				{
				setState(295); stat();
				}
				}
				setState(300);
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

	public static class TypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public BasicTypeContext basicType() {
			return getRuleContext(BasicTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type);
		try {
			setState(303);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case INTEGER:
			case CHARACTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(301); basicType();
				}
				break;
			case BOOLEANS:
			case INTEGERS:
			case CHARACTERS:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(302); arrayType();
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

	public static class BasicTypeContext extends ParserRuleContext {
		public BasicTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicType; }
	 
		public BasicTypeContext() { }
		public void copyFrom(BasicTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharTypeContext extends BasicTypeContext {
		public TerminalNode CHARACTER() { return getToken(SycoraxParser.CHARACTER, 0); }
		public CharTypeContext(BasicTypeContext ctx) { copyFrom(ctx); }
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
	public static class IntTypeContext extends BasicTypeContext {
		public TerminalNode INTEGER() { return getToken(SycoraxParser.INTEGER, 0); }
		public IntTypeContext(BasicTypeContext ctx) { copyFrom(ctx); }
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
	public static class BoolTypeContext extends BasicTypeContext {
		public TerminalNode BOOLEAN() { return getToken(SycoraxParser.BOOLEAN, 0); }
		public BoolTypeContext(BasicTypeContext ctx) { copyFrom(ctx); }
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

	public final BasicTypeContext basicType() throws RecognitionException {
		BasicTypeContext _localctx = new BasicTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_basicType);
		try {
			setState(308);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(305); match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(306); match(BOOLEAN);
				}
				break;
			case CHARACTER:
				_localctx = new CharTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(307); match(CHARACTER);
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

	public static class ArrayTypeContext extends ParserRuleContext {
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
	 
		public ArrayTypeContext() { }
		public void copyFrom(ArrayTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CharArrTypeContext extends ArrayTypeContext {
		public TerminalNode CHARACTERS() { return getToken(SycoraxParser.CHARACTERS, 0); }
		public CharArrTypeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
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
	public static class IntArrTypeContext extends ArrayTypeContext {
		public TerminalNode INTEGERS() { return getToken(SycoraxParser.INTEGERS, 0); }
		public IntArrTypeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
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
	public static class StringTypeContext extends ArrayTypeContext {
		public TerminalNode STRING() { return getToken(SycoraxParser.STRING, 0); }
		public StringTypeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
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
	public static class BoolArrTypeContext extends ArrayTypeContext {
		public TerminalNode BOOLEANS() { return getToken(SycoraxParser.BOOLEANS, 0); }
		public BoolArrTypeContext(ArrayTypeContext ctx) { copyFrom(ctx); }
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

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_arrayType);
		try {
			setState(314);
			switch (_input.LA(1)) {
			case INTEGERS:
				_localctx = new IntArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(310); match(INTEGERS);
				}
				break;
			case BOOLEANS:
				_localctx = new BoolArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(311); match(BOOLEANS);
				}
				break;
			case CHARACTERS:
				_localctx = new CharArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(312); match(CHARACTERS);
				}
				break;
			case STRING:
				_localctx = new StringTypeContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(313); match(STRING);
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
		case 9: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 15);
		case 1: return precpred(_ctx, 14);
		case 2: return precpred(_ctx, 13);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3E\u013f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\7\38\n\3\f\3\16\3;\13\3\3\4\3\4\5\4?\n\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4F\n\4\3\4\3\4\5\4J\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4S\n\4"+
		"\5\4U\n\4\3\5\3\5\3\5\3\5\5\5[\n\5\3\5\3\5\5\5_\n\5\3\5\5\5b\n\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\7\7o\n\7\f\7\16\7r\13\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u0087\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\t\u0099\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a5"+
		"\n\t\3\t\3\t\5\t\u00a9\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00b1\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00be\n\13\3\13"+
		"\3\13\5\13\u00c2\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u00cd\n\13\3\13\5\13\u00d0\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5"+
		"\13\u00d9\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\7\13\u00e7\n\13\f\13\16\13\u00ea\13\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\7\17\u00f6\n\17\f\17\16\17\u00f9\13\17\5\17\u00fb"+
		"\n\17\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u0103\n\20\f\20\16\20\u0106\13"+
		"\20\3\20\3\20\3\21\5\21\u010b\n\21\3\21\3\21\5\21\u010f\n\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u0116\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u0121\n\22\3\22\3\22\3\22\3\22\3\22\5\22\u0128\n\22\3\23\7"+
		"\23\u012b\n\23\f\23\16\23\u012e\13\23\3\24\3\24\5\24\u0132\n\24\3\25\3"+
		"\25\3\25\5\25\u0137\n\25\3\26\3\26\3\26\3\26\5\26\u013d\n\26\3\26\2\3"+
		"\24\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2\5\3\2\6\7\3\2+"+
		"-\3\2.\61\u0168\2,\3\2\2\2\49\3\2\2\2\6T\3\2\2\2\bV\3\2\2\2\nh\3\2\2\2"+
		"\fj\3\2\2\2\16u\3\2\2\2\20\u00a8\3\2\2\2\22\u00b0\3\2\2\2\24\u00d8\3\2"+
		"\2\2\26\u00eb\3\2\2\2\30\u00ed\3\2\2\2\32\u00ef\3\2\2\2\34\u00f1\3\2\2"+
		"\2\36\u00fe\3\2\2\2 \u0115\3\2\2\2\"\u0117\3\2\2\2$\u012c\3\2\2\2&\u0131"+
		"\3\2\2\2(\u0136\3\2\2\2*\u013c\3\2\2\2,-\7%\2\2-.\7@\2\2./\7!\2\2/\60"+
		"\79\2\2\60\61\5\4\3\2\61\62\7:\2\2\62\63\7\2\2\3\63\3\3\2\2\2\648\5\b"+
		"\5\2\658\5\6\4\2\668\5\"\22\2\67\64\3\2\2\2\67\65\3\2\2\2\67\66\3\2\2"+
		"\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\5\3\2\2\2;9\3\2\2\2<>\7\25\2\2=?\7"+
		"\62\2\2>=\3\2\2\2>?\3\2\2\2?@\3\2\2\2@A\7@\2\2AB\7\30\2\2BE\5(\25\2CD"+
		"\7\31\2\2DF\5\24\13\2EC\3\2\2\2EF\3\2\2\2FU\3\2\2\2GI\7\25\2\2HJ\7\62"+
		"\2\2IH\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7@\2\2LM\7\30\2\2MR\5*\26\2NO\7\32"+
		"\2\2OS\5\24\13\2PQ\7\31\2\2QS\5\24\13\2RN\3\2\2\2RP\3\2\2\2SU\3\2\2\2"+
		"T<\3\2\2\2TG\3\2\2\2U\7\3\2\2\2VW\7\35\2\2WZ\7@\2\2XY\7\36\2\2Y[\5\f\7"+
		"\2ZX\3\2\2\2Z[\3\2\2\2[^\3\2\2\2\\]\7\37\2\2]_\5\n\6\2^\\\3\2\2\2^_\3"+
		"\2\2\2_a\3\2\2\2`b\7 \2\2a`\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\7!\2\2de\79"+
		"\2\2ef\5$\23\2fg\7:\2\2g\t\3\2\2\2hi\5&\24\2i\13\3\2\2\2jk\7;\2\2kp\5"+
		"\16\b\2lm\7\66\2\2mo\5\16\b\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2"+
		"qs\3\2\2\2rp\3\2\2\2st\7<\2\2t\r\3\2\2\2uv\5&\24\2vw\7@\2\2w\17\3\2\2"+
		"\2x\u00a9\5\"\22\2y\u00a9\5\6\4\2z\u00a9\7\23\2\2{|\7\'\2\2|}\7@\2\2}"+
		"\u00a9\5\"\22\2~\177\7(\2\2\177\u00a9\7@\2\2\u0080\u0081\7)\2\2\u0081"+
		"\u00a9\7@\2\2\u0082\u0083\7*\2\2\u0083\u00a9\7@\2\2\u0084\u0086\7\"\2"+
		"\2\u0085\u0087\5\24\13\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u00a9\3\2\2\2\u0088\u0089\5 \21\2\u0089\u008a\7\31\2\2\u008a\u008b\5"+
		"\24\13\2\u008b\u00a9\3\2\2\2\u008c\u008d\7\33\2\2\u008d\u008e\7@\2\2\u008e"+
		"\u008f\7\34\2\2\u008f\u00a9\5\22\n\2\u0090\u0091\7\16\2\2\u0091\u0092"+
		"\7;\2\2\u0092\u0093\5\24\13\2\u0093\u0094\7<\2\2\u0094\u0095\7\17\2\2"+
		"\u0095\u0098\5\"\22\2\u0096\u0097\7\20\2\2\u0097\u0099\5\"\22\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u00a9\3\2\2\2\u009a\u009b\7&"+
		"\2\2\u009b\u009c\7;\2\2\u009c\u009d\5\24\13\2\u009d\u009e\7<\2\2\u009e"+
		"\u009f\5\"\22\2\u009f\u00a9\3\2\2\2\u00a0\u00a1\7#\2\2\u00a1\u00a4\7@"+
		"\2\2\u00a2\u00a3\7$\2\2\u00a3\u00a5\5\36\20\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a9\3\2\2\2\u00a6\u00a7\7\64\2\2\u00a7\u00a9\5"+
		"\24\13\2\u00a8x\3\2\2\2\u00a8y\3\2\2\2\u00a8z\3\2\2\2\u00a8{\3\2\2\2\u00a8"+
		"~\3\2\2\2\u00a8\u0080\3\2\2\2\u00a8\u0082\3\2\2\2\u00a8\u0084\3\2\2\2"+
		"\u00a8\u0088\3\2\2\2\u00a8\u008c\3\2\2\2\u00a8\u0090\3\2\2\2\u00a8\u009a"+
		"\3\2\2\2\u00a8\u00a0\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\21\3\2\2\2\u00aa"+
		"\u00b1\7@\2\2\u00ab\u00ac\7@\2\2\u00ac\u00ad\7=\2\2\u00ad\u00ae\5\24\13"+
		"\2\u00ae\u00af\7>\2\2\u00af\u00b1\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0\u00ab"+
		"\3\2\2\2\u00b1\23\3\2\2\2\u00b2\u00b3\b\13\1\2\u00b3\u00b4\7\63\2\2\u00b4"+
		"\u00d9\5\24\13\13\u00b5\u00b6\7\5\2\2\u00b6\u00d9\5\24\13\3\u00b7\u00b8"+
		"\7;\2\2\u00b8\u00b9\5\24\13\2\u00b9\u00ba\7<\2\2\u00ba\u00d9\3\2\2\2\u00bb"+
		"\u00d9\5\34\17\2\u00bc\u00be\7\62\2\2\u00bd\u00bc\3\2\2\2\u00bd\u00be"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00d9\7@\2\2\u00c0\u00c2\7\62\2\2\u00c1"+
		"\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\7@"+
		"\2\2\u00c4\u00c5\7=\2\2\u00c5\u00c6\5\24\13\2\u00c6\u00c7\7>\2\2\u00c7"+
		"\u00d9\3\2\2\2\u00c8\u00c9\7#\2\2\u00c9\u00cc\7@\2\2\u00ca\u00cb\7$\2"+
		"\2\u00cb\u00cd\5\36\20\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00d9\3\2\2\2\u00ce\u00d0\7?\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d9\7A\2\2\u00d2\u00d3\7\65\2\2\u00d3"+
		"\u00d9\5(\25\2\u00d4\u00d9\7C\2\2\u00d5\u00d9\7B\2\2\u00d6\u00d9\7\3\2"+
		"\2\u00d7\u00d9\7\4\2\2\u00d8\u00b2\3\2\2\2\u00d8\u00b5\3\2\2\2\u00d8\u00b7"+
		"\3\2\2\2\u00d8\u00bb\3\2\2\2\u00d8\u00bd\3\2\2\2\u00d8\u00c1\3\2\2\2\u00d8"+
		"\u00c8\3\2\2\2\u00d8\u00cf\3\2\2\2\u00d8\u00d2\3\2\2\2\u00d8\u00d4\3\2"+
		"\2\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9"+
		"\u00e8\3\2\2\2\u00da\u00db\f\21\2\2\u00db\u00dc\5\26\f\2\u00dc\u00dd\5"+
		"\24\13\22\u00dd\u00e7\3\2\2\2\u00de\u00df\f\20\2\2\u00df\u00e0\5\30\r"+
		"\2\u00e0\u00e1\5\24\13\21\u00e1\u00e7\3\2\2\2\u00e2\u00e3\f\17\2\2\u00e3"+
		"\u00e4\5\32\16\2\u00e4\u00e5\5\24\13\20\u00e5\u00e7\3\2\2\2\u00e6\u00da"+
		"\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6\u00e2\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\25\3\2\2\2\u00ea\u00e8\3\2\2"+
		"\2\u00eb\u00ec\t\2\2\2\u00ec\27\3\2\2\2\u00ed\u00ee\t\3\2\2\u00ee\31\3"+
		"\2\2\2\u00ef\u00f0\t\4\2\2\u00f0\33\3\2\2\2\u00f1\u00fa\7=\2\2\u00f2\u00f7"+
		"\5\24\13\2\u00f3\u00f4\7\66\2\2\u00f4\u00f6\5\24\13\2\u00f5\u00f3\3\2"+
		"\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00f2\3\2\2\2\u00fa\u00fb\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\7>\2\2\u00fd\35\3\2\2\2\u00fe\u00ff"+
		"\7;\2\2\u00ff\u0104\5\24\13\2\u0100\u0101\7\66\2\2\u0101\u0103\5\24\13"+
		"\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0105"+
		"\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7<\2\2\u0108"+
		"\37\3\2\2\2\u0109\u010b\7\62\2\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2"+
		"\2\u010b\u010c\3\2\2\2\u010c\u0116\7@\2\2\u010d\u010f\7\62\2\2\u010e\u010d"+
		"\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\7@\2\2\u0111"+
		"\u0112\7=\2\2\u0112\u0113\5\24\13\2\u0113\u0114\7>\2\2\u0114\u0116\3\2"+
		"\2\2\u0115\u010a\3\2\2\2\u0115\u010e\3\2\2\2\u0116!\3\2\2\2\u0117\u0118"+
		"\7\21\2\2\u0118\u0119\79\2\2\u0119\u011a\5$\23\2\u011a\u0120\7:\2\2\u011b"+
		"\u011c\7\22\2\2\u011c\u011d\79\2\2\u011d\u011e\5$\23\2\u011e\u011f\7:"+
		"\2\2\u011f\u0121\3\2\2\2\u0120\u011b\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0127\3\2\2\2\u0122\u0123\7\24\2\2\u0123\u0124\79\2\2\u0124\u0125\5$"+
		"\23\2\u0125\u0126\7:\2\2\u0126\u0128\3\2\2\2\u0127\u0122\3\2\2\2\u0127"+
		"\u0128\3\2\2\2\u0128#\3\2\2\2\u0129\u012b\5\20\t\2\u012a\u0129\3\2\2\2"+
		"\u012b\u012e\3\2\2\2\u012c\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d%\3"+
		"\2\2\2\u012e\u012c\3\2\2\2\u012f\u0132\5(\25\2\u0130\u0132\5*\26\2\u0131"+
		"\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132\'\3\2\2\2\u0133\u0137\7\t\2\2"+
		"\u0134\u0137\7\b\2\2\u0135\u0137\7\n\2\2\u0136\u0133\3\2\2\2\u0136\u0134"+
		"\3\2\2\2\u0136\u0135\3\2\2\2\u0137)\3\2\2\2\u0138\u013d\7\f\2\2\u0139"+
		"\u013d\7\13\2\2\u013a\u013d\7\r\2\2\u013b\u013d\7\27\2\2\u013c\u0138\3"+
		"\2\2\2\u013c\u0139\3\2\2\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d"+
		"+\3\2\2\2%\679>EIRTZ^ap\u0086\u0098\u00a4\u00a8\u00b0\u00bd\u00c1\u00cc"+
		"\u00cf\u00d8\u00e6\u00e8\u00f7\u00fa\u0104\u010a\u010e\u0115\u0120\u0127"+
		"\u012c\u0131\u0136\u013c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}