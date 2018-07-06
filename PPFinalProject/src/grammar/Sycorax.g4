grammar Sycorax;

@header{package grammar;}

import Sycorax_vocab;

program
	: PROGRAM ID DEFINES LBRACE defs RBRACE EOF
	;
	
defs
	: (funDef | varDef | block)*
	;
	
varDef
	: LET GLOBAL? ID BE basicType (ASSIGN expr)?				#basicDef
	| LET GLOBAL? ID BE arrayType (SIZED expr | ASSIGN expr)	#arrayDef
	;
	
funDef
	: FUNCTION ID 
	  (USES params)? 
	  (RETURNS funType)? 
	  CATCHABLE?
	  DEFINES LBRACE content RBRACE
	;

funType 
	: type
	;

params 
	: LPAR (param (COMMA param)*) RPAR
	;	

param
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
	| GLOBAL? ID					#idExpr
	| GLOBAL? ID LBRACK expr RBRACK #indexExpr
	| SIZE expr						#sizeExpr
	| CALL ID (WITH args)?			#callExpr
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
	: GLOBAL? ID						#idTarget
	| GLOBAL? ID LBRACK expr RBRACK		#arrayTarget
	;

block
	: DO LBRACE content RBRACE 
	  (CATCH LBRACE content RBRACE)?
	  (FINALLY LBRACE content RBRACE)?	
	;

content : stat*;

type
	: basicType | arrayType;

basicType
	: INTEGER 						#intType
	| BOOLEAN						#boolType
	| CHARACTER						#charType
	;	
	
arrayType
	: INTEGERS						#intArrType
	| BOOLEANS						#boolArrType
	| CHARACTERS					#charArrType
	| STRING						#stringType
	;