lexer grammar Sycorax_vocab;

// Keywords
TRUE:		T R U E ;
FALSE:		F A L S E ;
NOT:		N O T ;
AND:		A N D;
OR:			O R ;

BOOLEAN:	B O O L E A N ;
INTEGER: 	I N T E G E R ;
BOOLEANS:	B O O L E A N S ;
INTEGERS: 	I N T E G E R S ;

IF:			I F ;
THEN:		T H E N ;
ELSE:		E L S E ;

DO:			D O;
CATCH:		C A T C H;
FAIL:		F A I L;
FINALLY:	F I N A L L Y;

LET:		L E T;
ARRAY:		A R R A Y;
STRING:		S T R I N G;
BE:			B E;
ASSIGN:		A S S I G N;
SIZED:		S I Z E D;

POINTER:	P O I N T E R;
TO:			T O;

FUNCTION:   F U N C T I O N ;
USES:		U S E S;
RETURNS:	R E T U R N S;
CATCHABLE:	C A T C H A B L E;
DEFINES:	D E F I N E S;
RETURN:		R E T U R N;
CALL:		C A L L;
WITH:		W I T H;

PROGRAM:	P R O G R A M;

WHILE:		W H I L E ;

FORK:		F O R K;
JOIN:		J O I N;
LOCK:		L O C K;
UNLOCK:		U N L O C K;

PLUS:		P L U S;
MINUS:		M I N U S;
TIMES:		T I M E S;

EQUALS:		E Q U A L S;
GREATER:	G R E A T E R;
SMALLER:	S M A L L E R;

COMMENT:	C O M M E N T;

COMMA:  	',';
DQUOTE: 	'"';
LBRACE: 	'{';
RBRACE: 	'}';
LPAR:   	'(';
RPAR:   	')';
LBRACK:		'[';
RBRACK:		']';
NEGATIVE:	'-';

// Content-bearing token types
ID: LETTER (LETTER | DIGIT)*;
NUM: DIGIT (DIGIT)*;
STR: DQUOTE .*? DQUOTE;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

// Skipped token types
WS: [ \t\r\n]+ -> skip;

fragment A: [aA];
fragment B: [bB];
fragment C: [cC];
fragment D: [dD];
fragment E: [eE];
fragment F: [fF];
fragment G: [gG];
fragment H: [hH];
fragment I: [iI];
fragment J: [jJ];
fragment K: [kK];
fragment L: [lL];
fragment M: [mM];
fragment N: [nN];
fragment O: [oO];
fragment P: [pP];
fragment Q: [qQ];
fragment R: [rR];
fragment S: [sS];
fragment T: [tT];
fragment U: [uU];
fragment V: [vV];
fragment W: [wW];
fragment X: [xX];
fragment Y: [yY];
fragment Z: [zZ];