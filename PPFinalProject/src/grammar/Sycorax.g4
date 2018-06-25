grammar Sycorax;

@header{package grammar;}

import Sycorax_vocab;

program
	: PROGRAM ID DEFINES LBRACE defs RBRACE EOF
	;
	
defs
	: (funDef | varDef | comment)*
	;
	
varDef
	: LET ID BE basicType (ASSIGN expr)?		#basicDef
	| LET ID BE arrayType 
	  (ASSIGN array | SIZED NUM)				#arrayDef
	| LET ID BE STRING (ASSIGN STR)?			#stringDef
	;
	
funDef
	: FUNCTION ID 
	  (USES LPAR (type ID (COMMA type ID)*))? 
	  (RETURNS type)? 
	  CATCHABLE?
	  DEFINES LBRACE stat* RBRACE
	;
	
stat
	: block							#blockStat
	| varDef						#vardefStat
	| comment						#commentStat
	| FAIL							#failStat
	| FORK ID block					#forkStat
	| JOIN ID						#joinStat
	| LOCK NUM						#lockStat
	| UNLOCK NUM					#unlockStat
	| RETURN expr					#returnStat
	| target ASSIGN expr			#assignStat
	| POINTER ID TO ID				#pointerStat
	| IF LPAR expr RPAR 
	  THEN block
	  (ELSE block)?					#ifstat
	| WHILE LPAR expr RPAR
	  block							#whileStat
	| CALL ID (WITH args)?			#callStat
	;
	
expr
	: ID							#idExpr
	| NUM							#numExpr
	| STR							#strExpr
	| TRUE							#trueExpr
	| FALSE							#falseExpr
	| NOT expr						#notExpr
	| NEGATIVE NUM					#negExpr
	| expr AND expr					#andExpr
	| expr OR expr					#orExpr
	| expr PLUS expr				#plusExpr
	| expr MINUS expr				#minusExpr
	| expr TIMES expr				#timesExpr
	| expr EQUALS expr				#equalsExpr
	| expr SMALLER expr				#smallerExpr
	| expr GREATER expr				#greaterExpr
	| array							#arrayExpr
	| ID LBRACK expr RBRACK			#indexExpr
	| CALL ID (WITH args)?			#callExpr
	;
	
array
	: LBRACK (expr (COMMA expr)*)? RBRACK
	;
	
args
	: LPAR (expr (COMMA expr)*)
	;
	
target
	: ID							#idTarget
	| ID LBRACK expr RBRACK			#arrayTarget
	;

comment
	: COMMENT .*? '\n'
	;

block
	: DO LBRACE stat* RBRACE 
	  (CATCH LBRACE stat* RBRACE)?
	  (FINALLY LBRACE stat* RBRACE)?	
	;
	
basicType
	: INTEGER 						#intType
	| BOOLEAN						#boolType
	;

arrayType
	: INTEGERS 						#intArrType
	| BOOLEANS						#boolArrType
	;

type
	: basicType | arrayType | STRING
	;	