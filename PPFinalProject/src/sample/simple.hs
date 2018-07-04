import Sprockell

reg1 :: Value
reg1 = 0
reg2 :: Value
reg2 = 0
reg3 :: Value
reg3 = 0
reg4 :: Value
reg4 = 0
reg5 :: Value
reg5 = 0
reg6 :: Value
reg6 = 0
reg7 :: Value
reg7 = 0
reg8 :: Value
reg8 = 0
reg9 :: Value
reg9 = 0
reg10 :: Value
reg10 = 0
reg11 :: Value
reg11 = 0
reg12 :: Value
reg12 = 0
reg13 :: Value
reg13 = 0
reg14 :: Value
reg14 = 0
reg15 :: Value
reg15 = 0
prog :: [Instruction]
prog = [
      Jump (Abs 30)
    , Load (ImmValue 82) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 117) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 110) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 116) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 105) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 109) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 101) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 32) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 101) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 114) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 114) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 111) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 114) reg1
    , WriteInstr reg1 charIO
    , Load (ImmValue 33) reg1
    , WriteInstr reg1 charIO
    , EndProg
    , Nop
    , Branch regSprID (Abs 33)
    , Jump (Abs 38)
    , Nop
    , ReadInstr (IndAddr regSprID)
    , Receive reg6
    , Branch reg6 (Ind reg6)
    , Jump (Abs 33)
    , Nop
    , Nop
    , Load (ImmValue 4) reg7
    , Push reg7
    --ArrayDef - allocate
    , ReadInstr (DirAddr 500)
    , Receive reg14
    , ReadInstr (DirAddr 2)
    , Receive reg15
    , Compute Add reg14 reg0 reg9
    , Load (ImmValue 0) reg10
    , Compute Add reg15 reg10 reg10
    , WriteInstr reg14 (IndAddr reg10)
    , Pop reg11
    , Compute Incr reg14 reg14 reg14
    , Compute Add reg14 reg11 reg14
    , WriteInstr reg14 (DirAddr 500)
    --ArrayDef - store data
    , Load (ImmValue 0) reg12
    , Compute Gt reg12 reg11 reg13
    , Branch reg13 (Abs 61)
    , Compute Incr reg12 reg12 reg12
    , Compute Incr reg9 reg9 reg9
    , WriteInstr reg0 (IndAddr reg9)
    , Jump (Abs 55)
    , Nop
    , EndProg
    ]
main = runWithDebugger (debuggerSimplePrintAndWait myShow) [prog]
