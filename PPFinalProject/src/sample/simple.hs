import Sprockell

prog :: [Instruction]
prog = [
      Jump (Abs 30)
    , Load (ImmValue 82) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 117) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 110) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 116) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 105) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 109) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 101) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 32) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 101) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 114) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 114) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 111) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 114) reg2
    , WriteInstr reg2 charIO
    , Load (ImmValue 33) reg2
    , WriteInstr reg2 charIO
    , EndProg
    , Nop
    , Branch regSprID (Abs 33)
    , Jump (Abs 38)
    , Nop
    , ReadInstr (IndAddr regSprID)
    , Receive reg7
    , Branch reg7 (Ind reg7)
    , Jump (Abs 33)
    , Nop
    , Load (ImmValue 3) reg8
    , WriteInstr reg8 (DirAddr 2)
    , Load (ImmValue 5000) reg8
    , WriteInstr reg8 (DirAddr 3)
    , Load (ImmValue 5000) reg5
    , Nop
    , Load (ImmValue 4) reg9
    , Push reg9
    --ArrayDef - allocate
    , ReadInstr (DirAddr 3)
    , Receive reg15
    , ReadInstr (DirAddr 2)
    , Receive reg16
    , Compute Add reg15 reg0 reg10
    , Load (ImmValue 0) reg11
    , Compute Add reg16 reg11 reg11
    , WriteInstr reg15 (IndAddr reg11)
    , Pop reg12
    , Compute Incr reg15 reg15 reg15
    , Compute Add reg15 reg12 reg15
    , WriteInstr reg15 (DirAddr 3)
    --ArrayDef - store data
    , Load (ImmValue 0) reg13
    , Compute Gt reg13 reg12 reg14
    , Branch reg14 (Abs 66)
    , Compute Incr reg13 reg13 reg13
    , Compute Incr reg10 reg10 reg10
    , WriteInstr reg0 (IndAddr reg10)
    , Jump (Abs 60)
    , Nop
    , EndProg
    ]
main = run [prog]
