grammar Sycorax;

@header{package grammar;}

import Sycorax_vocab;

program
	: PROGRAM ID DEFINES LBRACE defs RBRACE EOF
	;
	
defs
	: (funDef | varDef)*
	;
	
varDef
	: LET GLOBAL? ID BE type (ASSIGN expr)?
	;
	
funDef
	: FUNCTION ID 
	  (USES LPAR (arg (COMMA arg)*) RPAR)? 
	  (RETURNS type)? 
	  CATCHABLE?
	  DEFINES LBRACE stat* RBRACE
	;
	
arg
	: type ID
	;
	
stat
	: block							#blockStat
	| varDef						#vardefStat
	| FAIL							#failStat
	| FORK ID block					#forkStat
	| JOIN ID						#joinStat
	| LOCK ID						#lockStat
	| UNLOCK ID						#unlockStat
	| RETURN expr?					#returnStat
	| target ASSIGN expr			#assignStat
	| POINTER ID TO target			#pointerStat
	| IF LPAR expr RPAR 
	  THEN block
	  (ELSE block)?					#ifstat
	| WHILE LPAR expr RPAR
	  block							#whileStat
	| CALL ID (WITH args)?			#callStat
	| PRINT expr					#printStat
	;
	
expr
	: LPAR expr RPAR				#parExpr
	| expr boolOp expr				#boolOpExpr
	| expr intOp expr				#intOpExpr
	| expr compOp expr				#compOpExpr
	| array							#arrayExpr
	| SIZE expr						#sizeExpr
	| ID LBRACK expr RBRACK			#indexExpr
	| CALL ID (WITH args)?			#callExpr
	| ID							#idExpr
	| NEGATIVE? NUM					#numExpr
	| CHAR							#charExpr
	| STR							#strExpr
	| TRUE							#trueExpr
	| FALSE							#falseExpr
	| NOT expr						#notExpr
	;
	
boolOp
	: AND | OR
	;
intOp
	: PLUS | MINUS | TIMES
	;
compOp
	: EQUALS | SMALLER | GREATER
	;
	
array
	: LBRACK (expr (COMMA expr)*)? RBRACK
	;
	
args
	: LPAR (expr (COMMA expr)*) RPAR
	;
	
target
	: ID							#idTarget
	| ID LBRACK expr RBRACK			#arrayTarget
	;

block
	: DO LBRACE stat* RBRACE 
	  (CATCH LBRACE stat* RBRACE)?
	  (FINALLY LBRACE stat* RBRACE)?	
	;

type
	: INTEGER 						#intType
	| BOOLEAN						#boolType
	| CHARACTER						#charType
	| INTEGERS SIZED NUM			#intArrType
	| BOOLEANS SIZED NUM			#boolArrType
	| CHARACTERS SIZED NUM			#charArrType
	| STRING SIZED NUM				#stringType
	;	