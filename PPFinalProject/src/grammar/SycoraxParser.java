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
		T__0=1, TRUE=2, FALSE=3, NOT=4, AND=5, OR=6, BOOLEAN=7, INTEGER=8, BOOLEANS=9, 
		INTEGERS=10, IF=11, THEN=12, ELSE=13, DO=14, CATCH=15, FAIL=16, FINALLY=17, 
		LET=18, ARRAY=19, STRING=20, BE=21, ASSIGN=22, SIZED=23, POINTER=24, TO=25, 
		FUNCTION=26, USES=27, RETURNS=28, CATCHABLE=29, DEFINES=30, RETURN=31, 
		CALL=32, WITH=33, PROGRAM=34, WHILE=35, FORK=36, JOIN=37, LOCK=38, UNLOCK=39, 
		PLUS=40, MINUS=41, TIMES=42, EQUALS=43, GREATER=44, SMALLER=45, COMMENT=46, 
		COMMA=47, DQUOTE=48, LBRACE=49, RBRACE=50, LPAR=51, RPAR=52, LBRACK=53, 
		RBRACK=54, NEGATIVE=55, ID=56, NUM=57, STR=58, WS=59;
	public static final String[] tokenNames = {
		"<INVALID>", "'\n'", "TRUE", "FALSE", "NOT", "AND", "OR", "BOOLEAN", "INTEGER", 
		"BOOLEANS", "INTEGERS", "IF", "THEN", "ELSE", "DO", "CATCH", "FAIL", "FINALLY", 
		"LET", "ARRAY", "STRING", "BE", "ASSIGN", "SIZED", "POINTER", "TO", "FUNCTION", 
		"USES", "RETURNS", "CATCHABLE", "DEFINES", "RETURN", "CALL", "WITH", "PROGRAM", 
		"WHILE", "FORK", "JOIN", "LOCK", "UNLOCK", "PLUS", "MINUS", "TIMES", "EQUALS", 
		"GREATER", "SMALLER", "COMMENT", "','", "'\"'", "'{'", "'}'", "'('", "')'", 
		"'['", "']'", "'-'", "ID", "NUM", "STR", "WS"
	};
	public static final int
		RULE_program = 0, RULE_defs = 1, RULE_varDef = 2, RULE_funDef = 3, RULE_stat = 4, 
		RULE_expr = 5, RULE_array = 6, RULE_args = 7, RULE_target = 8, RULE_comment = 9, 
		RULE_block = 10, RULE_basicType = 11, RULE_arrayType = 12, RULE_type = 13;
	public static final String[] ruleNames = {
		"program", "defs", "varDef", "funDef", "stat", "expr", "array", "args", 
		"target", "comment", "block", "basicType", "arrayType", "type"
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
			setState(28); match(PROGRAM);
			setState(29); match(ID);
			setState(30); match(DEFINES);
			setState(31); match(LBRACE);
			setState(32); defs();
			setState(33); match(RBRACE);
			setState(34); match(EOF);
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
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
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
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LET) | (1L << FUNCTION) | (1L << COMMENT))) != 0)) {
				{
				setState(39);
				switch (_input.LA(1)) {
				case FUNCTION:
					{
					setState(36); funDef();
					}
					break;
				case LET:
					{
					setState(37); varDef();
					}
					break;
				case COMMENT:
					{
					setState(38); comment();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(43);
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
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public TerminalNode SIZED() { return getToken(SycoraxParser.SIZED, 0); }
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public TerminalNode LET() { return getToken(SycoraxParser.LET, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
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
	public static class StringDefContext extends VarDefContext {
		public TerminalNode ID() { return getToken(SycoraxParser.ID, 0); }
		public TerminalNode BE() { return getToken(SycoraxParser.BE, 0); }
		public TerminalNode ASSIGN() { return getToken(SycoraxParser.ASSIGN, 0); }
		public TerminalNode STR() { return getToken(SycoraxParser.STR, 0); }
		public TerminalNode STRING() { return getToken(SycoraxParser.STRING, 0); }
		public TerminalNode LET() { return getToken(SycoraxParser.LET, 0); }
		public StringDefContext(VarDefContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterStringDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitStringDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitStringDef(this);
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
			setState(70);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new BasicDefContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(44); match(LET);
				setState(45); match(ID);
				setState(46); match(BE);
				setState(47); basicType();
				setState(50);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(48); match(ASSIGN);
					setState(49); expr(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new ArrayDefContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(52); match(LET);
				setState(53); match(ID);
				setState(54); match(BE);
				setState(55); arrayType();
				setState(60);
				switch (_input.LA(1)) {
				case ASSIGN:
					{
					setState(56); match(ASSIGN);
					setState(57); array();
					}
					break;
				case SIZED:
					{
					setState(58); match(SIZED);
					setState(59); match(NUM);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 3:
				_localctx = new StringDefContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(62); match(LET);
				setState(63); match(ID);
				setState(64); match(BE);
				setState(65); match(STRING);
				setState(68);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(66); match(ASSIGN);
					setState(67); match(STR);
					}
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
		public TerminalNode LBRACE() { return getToken(SycoraxParser.LBRACE, 0); }
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode CATCHABLE() { return getToken(SycoraxParser.CATCHABLE, 0); }
		public TerminalNode ID(int i) {
			return getToken(SycoraxParser.ID, i);
		}
		public TerminalNode RETURNS() { return getToken(SycoraxParser.RETURNS, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public TerminalNode LPAR() { return getToken(SycoraxParser.LPAR, 0); }
		public TerminalNode USES() { return getToken(SycoraxParser.USES, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(SycoraxParser.COMMA, i);
		}
		public List<TerminalNode> ID() { return getTokens(SycoraxParser.ID); }
		public TerminalNode FUNCTION() { return getToken(SycoraxParser.FUNCTION, 0); }
		public TerminalNode RBRACE() { return getToken(SycoraxParser.RBRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(SycoraxParser.COMMA); }
		public TerminalNode DEFINES() { return getToken(SycoraxParser.DEFINES, 0); }
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
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
			setState(72); match(FUNCTION);
			setState(73); match(ID);
			setState(87);
			_la = _input.LA(1);
			if (_la==USES) {
				{
				setState(74); match(USES);
				setState(75); match(LPAR);
				{
				setState(76); type();
				setState(77); match(ID);
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(78); match(COMMA);
					setState(79); type();
					setState(80); match(ID);
					}
					}
					setState(86);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
			}

			setState(91);
			_la = _input.LA(1);
			if (_la==RETURNS) {
				{
				setState(89); match(RETURNS);
				setState(90); type();
				}
			}

			setState(94);
			_la = _input.LA(1);
			if (_la==CATCHABLE) {
				{
				setState(93); match(CATCHABLE);
				}
			}

			setState(96); match(DEFINES);
			setState(97); match(LBRACE);
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << COMMENT) | (1L << ID))) != 0)) {
				{
				{
				setState(98); stat();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104); match(RBRACE);
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
		public List<TerminalNode> ID() { return getTokens(SycoraxParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SycoraxParser.ID, i);
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
	public static class CommentStatContext extends StatContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public CommentStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterCommentStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitCommentStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitCommentStat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LockStatContext extends StatContext {
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
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
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
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
		enterRule(_localctx, 8, RULE_stat);
		int _la;
		try {
			setState(151);
			switch (_input.LA(1)) {
			case DO:
				_localctx = new BlockStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(106); block();
				}
				break;
			case LET:
				_localctx = new VardefStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(107); varDef();
				}
				break;
			case COMMENT:
				_localctx = new CommentStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(108); comment();
				}
				break;
			case FAIL:
				_localctx = new FailStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109); match(FAIL);
				}
				break;
			case FORK:
				_localctx = new ForkStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(110); match(FORK);
				setState(111); match(ID);
				setState(112); block();
				}
				break;
			case JOIN:
				_localctx = new JoinStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(113); match(JOIN);
				setState(114); match(ID);
				}
				break;
			case LOCK:
				_localctx = new LockStatContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(115); match(LOCK);
				setState(116); match(NUM);
				}
				break;
			case UNLOCK:
				_localctx = new UnlockStatContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(117); match(UNLOCK);
				setState(118); match(NUM);
				}
				break;
			case RETURN:
				_localctx = new ReturnStatContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(119); match(RETURN);
				setState(120); expr(0);
				}
				break;
			case ID:
				_localctx = new AssignStatContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(121); target();
				setState(122); match(ASSIGN);
				setState(123); expr(0);
				}
				break;
			case POINTER:
				_localctx = new PointerStatContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(125); match(POINTER);
				setState(126); match(ID);
				setState(127); match(TO);
				setState(128); match(ID);
				}
				break;
			case IF:
				_localctx = new IfstatContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(129); match(IF);
				setState(130); match(LPAR);
				setState(131); expr(0);
				setState(132); match(RPAR);
				setState(133); match(THEN);
				setState(134); block();
				setState(137);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(135); match(ELSE);
					setState(136); block();
					}
				}

				}
				break;
			case WHILE:
				_localctx = new WhileStatContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(139); match(WHILE);
				setState(140); match(LPAR);
				setState(141); expr(0);
				setState(142); match(RPAR);
				setState(143); block();
				}
				break;
			case CALL:
				_localctx = new CallStatContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(145); match(CALL);
				setState(146); match(ID);
				setState(149);
				_la = _input.LA(1);
				if (_la==WITH) {
					{
					setState(147); match(WITH);
					setState(148); args();
					}
				}

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
	public static class EqualsExprContext extends ExprContext {
		public TerminalNode EQUALS() { return getToken(SycoraxParser.EQUALS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualsExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterEqualsExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitEqualsExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitEqualsExpr(this);
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
	public static class OrExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OR() { return getToken(SycoraxParser.OR, 0); }
		public OrExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MINUS() { return getToken(SycoraxParser.MINUS, 0); }
		public MinusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterMinusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitMinusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitMinusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExprContext extends ExprContext {
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
	public static class PlusExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(SycoraxParser.PLUS, 0); }
		public PlusExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterPlusExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitPlusExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitPlusExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TimesExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public TerminalNode TIMES() { return getToken(SycoraxParser.TIMES, 0); }
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TimesExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterTimesExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitTimesExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitTimesExpr(this);
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
	public static class GreaterExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode GREATER() { return getToken(SycoraxParser.GREATER, 0); }
		public GreaterExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterGreaterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitGreaterExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitGreaterExpr(this);
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
	public static class NegExprContext extends ExprContext {
		public TerminalNode NEGATIVE() { return getToken(SycoraxParser.NEGATIVE, 0); }
		public TerminalNode NUM() { return getToken(SycoraxParser.NUM, 0); }
		public NegExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterNegExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitNegExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitNegExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SmallerExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SMALLER() { return getToken(SycoraxParser.SMALLER, 0); }
		public SmallerExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterSmallerExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitSmallerExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitSmallerExpr(this);
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
	public static class AndExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode AND() { return getToken(SycoraxParser.AND, 0); }
		public AndExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitAndExpr(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(154); match(NOT);
				setState(155); expr(13);
				}
				break;
			case 2:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156); match(ID);
				}
				break;
			case 3:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(157); match(NUM);
				}
				break;
			case 4:
				{
				_localctx = new StrExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(158); match(STR);
				}
				break;
			case 5:
				{
				_localctx = new TrueExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(159); match(TRUE);
				}
				break;
			case 6:
				{
				_localctx = new FalseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(160); match(FALSE);
				}
				break;
			case 7:
				{
				_localctx = new NegExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(161); match(NEGATIVE);
				setState(162); match(NUM);
				}
				break;
			case 8:
				{
				_localctx = new ArrayExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(163); array();
				}
				break;
			case 9:
				{
				_localctx = new IndexExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(164); match(ID);
				setState(165); match(LBRACK);
				setState(166); expr(0);
				setState(167); match(RBRACK);
				}
				break;
			case 10:
				{
				_localctx = new CallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(169); match(CALL);
				setState(170); match(ID);
				setState(173);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(171); match(WITH);
					setState(172); args();
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(201);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new AndExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(177);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(178); match(AND);
						setState(179); expr(12);
						}
						break;
					case 2:
						{
						_localctx = new OrExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(180);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(181); match(OR);
						setState(182); expr(11);
						}
						break;
					case 3:
						{
						_localctx = new PlusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(183);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(184); match(PLUS);
						setState(185); expr(10);
						}
						break;
					case 4:
						{
						_localctx = new MinusExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(186);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(187); match(MINUS);
						setState(188); expr(9);
						}
						break;
					case 5:
						{
						_localctx = new TimesExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(189);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(190); match(TIMES);
						setState(191); expr(8);
						}
						break;
					case 6:
						{
						_localctx = new EqualsExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(192);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(193); match(EQUALS);
						setState(194); expr(7);
						}
						break;
					case 7:
						{
						_localctx = new SmallerExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(195);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(196); match(SMALLER);
						setState(197); expr(6);
						}
						break;
					case 8:
						{
						_localctx = new GreaterExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(198);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(199); match(GREATER);
						setState(200); expr(5);
						}
						break;
					}
					} 
				}
				setState(205);
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
		enterRule(_localctx, 12, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206); match(LBRACK);
			setState(215);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TRUE) | (1L << FALSE) | (1L << NOT) | (1L << CALL) | (1L << LBRACK) | (1L << NEGATIVE) | (1L << ID) | (1L << NUM) | (1L << STR))) != 0)) {
				{
				setState(207); expr(0);
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(208); match(COMMA);
					setState(209); expr(0);
					}
					}
					setState(214);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(217); match(RBRACK);
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
		enterRule(_localctx, 14, RULE_args);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219); match(LPAR);
			{
			setState(220); expr(0);
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(221); match(COMMA);
					setState(222); expr(0);
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 16, RULE_target);
		try {
			setState(234);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new IdTargetContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(228); match(ID);
				}
				break;
			case 2:
				_localctx = new ArrayTargetContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(229); match(ID);
				setState(230); match(LBRACK);
				setState(231); expr(0);
				setState(232); match(RBRACK);
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

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(SycoraxParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SycoraxListener ) ((SycoraxListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SycoraxVisitor ) return ((SycoraxVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comment);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(236); match(COMMENT);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(237);
					matchWildcard();
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(243); match(T__0);
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
		enterRule(_localctx, 20, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245); match(DO);
			setState(246); match(LBRACE);
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << COMMENT) | (1L << ID))) != 0)) {
				{
				{
				setState(247); stat();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(253); match(RBRACE);
			setState(263);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(254); match(CATCH);
				setState(255); match(LBRACE);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << COMMENT) | (1L << ID))) != 0)) {
					{
					{
					setState(256); stat();
					}
					}
					setState(261);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(262); match(RBRACE);
				}
			}

			setState(274);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(265); match(FINALLY);
				setState(266); match(LBRACE);
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << DO) | (1L << FAIL) | (1L << LET) | (1L << POINTER) | (1L << RETURN) | (1L << CALL) | (1L << WHILE) | (1L << FORK) | (1L << JOIN) | (1L << LOCK) | (1L << UNLOCK) | (1L << COMMENT) | (1L << ID))) != 0)) {
					{
					{
					setState(267); stat();
					}
					}
					setState(272);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(273); match(RBRACE);
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
		enterRule(_localctx, 22, RULE_basicType);
		try {
			setState(278);
			switch (_input.LA(1)) {
			case INTEGER:
				_localctx = new IntTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(276); match(INTEGER);
				}
				break;
			case BOOLEAN:
				_localctx = new BoolTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(277); match(BOOLEAN);
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
		enterRule(_localctx, 24, RULE_arrayType);
		try {
			setState(282);
			switch (_input.LA(1)) {
			case INTEGERS:
				_localctx = new IntArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(280); match(INTEGERS);
				}
				break;
			case BOOLEANS:
				_localctx = new BoolArrTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(281); match(BOOLEANS);
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

	public static class TypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public TerminalNode STRING() { return getToken(SycoraxParser.STRING, 0); }
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
		enterRule(_localctx, 26, RULE_type);
		try {
			setState(287);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(284); basicType();
				}
				break;
			case BOOLEANS:
			case INTEGERS:
				enterOuterAlt(_localctx, 2);
				{
				setState(285); arrayType();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(286); match(STRING);
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
		case 5: return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 11);
		case 1: return precpred(_ctx, 10);
		case 2: return precpred(_ctx, 9);
		case 3: return precpred(_ctx, 8);
		case 4: return precpred(_ctx, 7);
		case 5: return precpred(_ctx, 6);
		case 6: return precpred(_ctx, 5);
		case 7: return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3=\u0124\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\65"+
		"\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4?\n\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4G\n\4\5\4I\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5U\n\5\f"+
		"\5\16\5X\13\5\5\5Z\n\5\3\5\3\5\5\5^\n\5\3\5\5\5a\n\5\3\5\3\5\3\5\7\5f"+
		"\n\5\f\5\16\5i\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6\u008c\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6"+
		"\u0098\n\6\5\6\u009a\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00b0\n\7\5\7\u00b2\n\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\7\7\u00cc\n\7\f\7\16\7\u00cf\13\7\3\b\3\b\3\b\3\b\7"+
		"\b\u00d5\n\b\f\b\16\b\u00d8\13\b\5\b\u00da\n\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\7\t\u00e2\n\t\f\t\16\t\u00e5\13\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00ed\n"+
		"\n\3\13\3\13\7\13\u00f1\n\13\f\13\16\13\u00f4\13\13\3\13\3\13\3\f\3\f"+
		"\3\f\7\f\u00fb\n\f\f\f\16\f\u00fe\13\f\3\f\3\f\3\f\3\f\7\f\u0104\n\f\f"+
		"\f\16\f\u0107\13\f\3\f\5\f\u010a\n\f\3\f\3\f\3\f\7\f\u010f\n\f\f\f\16"+
		"\f\u0112\13\f\3\f\5\f\u0115\n\f\3\r\3\r\5\r\u0119\n\r\3\16\3\16\5\16\u011d"+
		"\n\16\3\17\3\17\3\17\5\17\u0122\n\17\3\17\3\u00f2\3\f\20\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\2\2\u0151\2\36\3\2\2\2\4+\3\2\2\2\6H\3\2\2\2\bJ"+
		"\3\2\2\2\n\u0099\3\2\2\2\f\u00b1\3\2\2\2\16\u00d0\3\2\2\2\20\u00dd\3\2"+
		"\2\2\22\u00ec\3\2\2\2\24\u00ee\3\2\2\2\26\u00f7\3\2\2\2\30\u0118\3\2\2"+
		"\2\32\u011c\3\2\2\2\34\u0121\3\2\2\2\36\37\7$\2\2\37 \7:\2\2 !\7 \2\2"+
		"!\"\7\63\2\2\"#\5\4\3\2#$\7\64\2\2$%\7\2\2\3%\3\3\2\2\2&*\5\b\5\2\'*\5"+
		"\6\4\2(*\5\24\13\2)&\3\2\2\2)\'\3\2\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2"+
		"+,\3\2\2\2,\5\3\2\2\2-+\3\2\2\2./\7\24\2\2/\60\7:\2\2\60\61\7\27\2\2\61"+
		"\64\5\30\r\2\62\63\7\30\2\2\63\65\5\f\7\2\64\62\3\2\2\2\64\65\3\2\2\2"+
		"\65I\3\2\2\2\66\67\7\24\2\2\678\7:\2\289\7\27\2\29>\5\32\16\2:;\7\30\2"+
		"\2;?\5\16\b\2<=\7\31\2\2=?\7;\2\2>:\3\2\2\2><\3\2\2\2?I\3\2\2\2@A\7\24"+
		"\2\2AB\7:\2\2BC\7\27\2\2CF\7\26\2\2DE\7\30\2\2EG\7<\2\2FD\3\2\2\2FG\3"+
		"\2\2\2GI\3\2\2\2H.\3\2\2\2H\66\3\2\2\2H@\3\2\2\2I\7\3\2\2\2JK\7\34\2\2"+
		"KY\7:\2\2LM\7\35\2\2MN\7\65\2\2NO\5\34\17\2OV\7:\2\2PQ\7\61\2\2QR\5\34"+
		"\17\2RS\7:\2\2SU\3\2\2\2TP\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WZ\3\2"+
		"\2\2XV\3\2\2\2YL\3\2\2\2YZ\3\2\2\2Z]\3\2\2\2[\\\7\36\2\2\\^\5\34\17\2"+
		"][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_a\7\37\2\2`_\3\2\2\2`a\3\2\2\2ab\3\2\2"+
		"\2bc\7 \2\2cg\7\63\2\2df\5\n\6\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2"+
		"\2hj\3\2\2\2ig\3\2\2\2jk\7\64\2\2k\t\3\2\2\2l\u009a\5\26\f\2m\u009a\5"+
		"\6\4\2n\u009a\5\24\13\2o\u009a\7\22\2\2pq\7&\2\2qr\7:\2\2r\u009a\5\26"+
		"\f\2st\7\'\2\2t\u009a\7:\2\2uv\7(\2\2v\u009a\7;\2\2wx\7)\2\2x\u009a\7"+
		";\2\2yz\7!\2\2z\u009a\5\f\7\2{|\5\22\n\2|}\7\30\2\2}~\5\f\7\2~\u009a\3"+
		"\2\2\2\177\u0080\7\32\2\2\u0080\u0081\7:\2\2\u0081\u0082\7\33\2\2\u0082"+
		"\u009a\7:\2\2\u0083\u0084\7\r\2\2\u0084\u0085\7\65\2\2\u0085\u0086\5\f"+
		"\7\2\u0086\u0087\7\66\2\2\u0087\u0088\7\16\2\2\u0088\u008b\5\26\f\2\u0089"+
		"\u008a\7\17\2\2\u008a\u008c\5\26\f\2\u008b\u0089\3\2\2\2\u008b\u008c\3"+
		"\2\2\2\u008c\u009a\3\2\2\2\u008d\u008e\7%\2\2\u008e\u008f\7\65\2\2\u008f"+
		"\u0090\5\f\7\2\u0090\u0091\7\66\2\2\u0091\u0092\5\26\f\2\u0092\u009a\3"+
		"\2\2\2\u0093\u0094\7\"\2\2\u0094\u0097\7:\2\2\u0095\u0096\7#\2\2\u0096"+
		"\u0098\5\20\t\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3"+
		"\2\2\2\u0099l\3\2\2\2\u0099m\3\2\2\2\u0099n\3\2\2\2\u0099o\3\2\2\2\u0099"+
		"p\3\2\2\2\u0099s\3\2\2\2\u0099u\3\2\2\2\u0099w\3\2\2\2\u0099y\3\2\2\2"+
		"\u0099{\3\2\2\2\u0099\177\3\2\2\2\u0099\u0083\3\2\2\2\u0099\u008d\3\2"+
		"\2\2\u0099\u0093\3\2\2\2\u009a\13\3\2\2\2\u009b\u009c\b\7\1\2\u009c\u009d"+
		"\7\6\2\2\u009d\u00b2\5\f\7\17\u009e\u00b2\7:\2\2\u009f\u00b2\7;\2\2\u00a0"+
		"\u00b2\7<\2\2\u00a1\u00b2\7\4\2\2\u00a2\u00b2\7\5\2\2\u00a3\u00a4\79\2"+
		"\2\u00a4\u00b2\7;\2\2\u00a5\u00b2\5\16\b\2\u00a6\u00a7\7:\2\2\u00a7\u00a8"+
		"\7\67\2\2\u00a8\u00a9\5\f\7\2\u00a9\u00aa\78\2\2\u00aa\u00b2\3\2\2\2\u00ab"+
		"\u00ac\7\"\2\2\u00ac\u00af\7:\2\2\u00ad\u00ae\7#\2\2\u00ae\u00b0\5\20"+
		"\t\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1"+
		"\u009b\3\2\2\2\u00b1\u009e\3\2\2\2\u00b1\u009f\3\2\2\2\u00b1\u00a0\3\2"+
		"\2\2\u00b1\u00a1\3\2\2\2\u00b1\u00a2\3\2\2\2\u00b1\u00a3\3\2\2\2\u00b1"+
		"\u00a5\3\2\2\2\u00b1\u00a6\3\2\2\2\u00b1\u00ab\3\2\2\2\u00b2\u00cd\3\2"+
		"\2\2\u00b3\u00b4\f\r\2\2\u00b4\u00b5\7\7\2\2\u00b5\u00cc\5\f\7\16\u00b6"+
		"\u00b7\f\f\2\2\u00b7\u00b8\7\b\2\2\u00b8\u00cc\5\f\7\r\u00b9\u00ba\f\13"+
		"\2\2\u00ba\u00bb\7*\2\2\u00bb\u00cc\5\f\7\f\u00bc\u00bd\f\n\2\2\u00bd"+
		"\u00be\7+\2\2\u00be\u00cc\5\f\7\13\u00bf\u00c0\f\t\2\2\u00c0\u00c1\7,"+
		"\2\2\u00c1\u00cc\5\f\7\n\u00c2\u00c3\f\b\2\2\u00c3\u00c4\7-\2\2\u00c4"+
		"\u00cc\5\f\7\t\u00c5\u00c6\f\7\2\2\u00c6\u00c7\7/\2\2\u00c7\u00cc\5\f"+
		"\7\b\u00c8\u00c9\f\6\2\2\u00c9\u00ca\7.\2\2\u00ca\u00cc\5\f\7\7\u00cb"+
		"\u00b3\3\2\2\2\u00cb\u00b6\3\2\2\2\u00cb\u00b9\3\2\2\2\u00cb\u00bc\3\2"+
		"\2\2\u00cb\u00bf\3\2\2\2\u00cb\u00c2\3\2\2\2\u00cb\u00c5\3\2\2\2\u00cb"+
		"\u00c8\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\r\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d9\7\67\2\2\u00d1\u00d6"+
		"\5\f\7\2\u00d2\u00d3\7\61\2\2\u00d3\u00d5\5\f\7\2\u00d4\u00d2\3\2\2\2"+
		"\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00da"+
		"\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00d1\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\78\2\2\u00dc\17\3\2\2\2\u00dd\u00de\7\65\2"+
		"\2\u00de\u00e3\5\f\7\2\u00df\u00e0\7\61\2\2\u00e0\u00e2\5\f\7\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\21\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00ed\7:\2\2\u00e7\u00e8"+
		"\7:\2\2\u00e8\u00e9\7\67\2\2\u00e9\u00ea\5\f\7\2\u00ea\u00eb\78\2\2\u00eb"+
		"\u00ed\3\2\2\2\u00ec\u00e6\3\2\2\2\u00ec\u00e7\3\2\2\2\u00ed\23\3\2\2"+
		"\2\u00ee\u00f2\7\60\2\2\u00ef\u00f1\13\2\2\2\u00f0\u00ef\3\2\2\2\u00f1"+
		"\u00f4\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f5\3\2"+
		"\2\2\u00f4\u00f2\3\2\2\2\u00f5\u00f6\7\3\2\2\u00f6\25\3\2\2\2\u00f7\u00f8"+
		"\7\20\2\2\u00f8\u00fc\7\63\2\2\u00f9\u00fb\5\n\6\2\u00fa\u00f9\3\2\2\2"+
		"\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff"+
		"\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0109\7\64\2\2\u0100\u0101\7\21\2\2"+
		"\u0101\u0105\7\63\2\2\u0102\u0104\5\n\6\2\u0103\u0102\3\2\2\2\u0104\u0107"+
		"\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0108\3\2\2\2\u0107"+
		"\u0105\3\2\2\2\u0108\u010a\7\64\2\2\u0109\u0100\3\2\2\2\u0109\u010a\3"+
		"\2\2\2\u010a\u0114\3\2\2\2\u010b\u010c\7\23\2\2\u010c\u0110\7\63\2\2\u010d"+
		"\u010f\5\n\6\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0115\7\64\2\2\u0114\u010b\3\2\2\2\u0114\u0115\3\2\2\2\u0115\27\3\2\2"+
		"\2\u0116\u0119\7\n\2\2\u0117\u0119\7\t\2\2\u0118\u0116\3\2\2\2\u0118\u0117"+
		"\3\2\2\2\u0119\31\3\2\2\2\u011a\u011d\7\f\2\2\u011b\u011d\7\13\2\2\u011c"+
		"\u011a\3\2\2\2\u011c\u011b\3\2\2\2\u011d\33\3\2\2\2\u011e\u0122\5\30\r"+
		"\2\u011f\u0122\5\32\16\2\u0120\u0122\7\26\2\2\u0121\u011e\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122\35\3\2\2\2!)+\64>FHVY]`g\u008b"+
		"\u0097\u0099\u00af\u00b1\u00cb\u00cd\u00d6\u00d9\u00e3\u00ec\u00f2\u00fc"+
		"\u0105\u0109\u0110\u0114\u0118\u011c\u0121";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}