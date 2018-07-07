import Sprockell

prog :: [Instruction]
prog = [
      Jump (Abs 30)
    , Load (ImmValue (82)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (117)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (110)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (116)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (105)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (109)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (101)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (32)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (101)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (114)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (114)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (111)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (114)) regA
    , WriteInstr regA charIO
    , Load (ImmValue (33)) regA
    , WriteInstr regA charIO
    , EndProg
    , Nop
    , Branch regSprID (Rel 2)
    , Jump (Abs 38)
    , Nop
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regD (Ind regD)
    , Jump (Abs 33)
    , Nop
    , Load (ImmValue (3)) regE
    , WriteInstr regE (DirAddr (1))
    , Load (ImmValue (5000)) regE
    , WriteInstr regE (DirAddr (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 139)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 5
    , Load (ImmValue (5)) regE
    , Push regE
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regF
    , Push regF
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regG
    , Push regG
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regH
    , Push regH
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regA
    , Push regA
    , Load (ImmValue (5)) regB
    , Push regB
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regC
    , Load (ImmValue (1)) regD
    , Compute Add regArp regD regD
    , Store regC (IndAddr (regD))
    , Pop regE
    , Compute Lt regE reg0 regD
    , Branch regD (Abs 1)
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regE regHeap
    --ArrayDef - store data
    , Store regE (IndAddr (regC))
    , Compute Incr regC regC regC
    , Branch regE (Rel 2)
    , Jump (Abs 99)
    , Pop regD
    , Store regD (IndAddr (regC))
    , Compute Incr regC regC regC
    , Compute Decr regE regE regE
    , Jump (Abs 92)
    , Nop
    , Nop
    --Load ID - ints
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Load (IndAddr (regF)) regA
    , Compute Add regF regA regF
    , Compute Add regA reg0 regG
    , Branch regA (Rel 2)
    , Jump (Abs 115)
    , Load (IndAddr (regF)) regH
    , Push regH
    , Compute Decr regF regF regF
    , Compute Decr regA regA regA
    , Jump (Abs 108)
    , Push regG
    , Pop regB
    , Compute Add reg0 reg0 regC
    , Compute GtE regC regB regD
    , Branch regD (Abs 123)
    , Pop regD
    , Compute Incr regC regC regC
    , Jump (Abs 118)
    , Nop
    , Push regB
    --VarDef - s
    , Load (ImmValue (2)) regE
    , Compute Add regE regArp regE
    , Pop regF
    , Store regF (IndAddr (regE))
    , Nop
    --Load ID - s
    , Compute Add regArp reg0 regH
    , Load (ImmValue (2)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    --Print: int
    , Pop regB
    , WriteInstr regB numberIO
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regG
    , Pop regH
    , Branch regG (Abs 0)
    , Branch regH (Abs 151)
    , Jump (Abs 151)
    , Jump (Abs 169)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 157)
    , Branch regG (Abs 163)
    , Jump (Abs 169)
    , Nop
    , Push regH
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind regF)
    , Nop
    , Push regH
    , Push regG
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind regF)
    , Nop
    , EndProg
    ]
main = run [prog]
