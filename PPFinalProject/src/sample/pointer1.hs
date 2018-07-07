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
    , Jump (Abs 193)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 15
    , Load (ImmValue (15)) regE
    , Push regE
    , Nop
    --Num - 8
    , Load (ImmValue (8)) regF
    , Push regF
    , Nop
    --Num - 0
    , Load (ImmValue (0)) regG
    , Push regG
    , Load (ImmValue (3)) regH
    , Push regH
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regA
    , Load (ImmValue (1)) regB
    , Compute Add regArp regB regB
    , Store regA (IndAddr (regB))
    , Pop regC
    , Compute Lt regC reg0 regB
    , Branch regB (Abs 1)
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regC regHeap
    --ArrayDef - store data
    , Store regC (IndAddr (regA))
    , Compute Incr regA regA regA
    , Branch regC (Rel 2)
    , Jump (Abs 93)
    , Pop regB
    , Store regB (IndAddr (regA))
    , Compute Incr regA regA regA
    , Compute Decr regC regC regC
    , Jump (Abs 86)
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regD
    , Push regD
    , Compute Add regArp reg0 regE
    , Load (ImmValue (1)) regG
    , Compute Add regE regG regE
    , Load (IndAddr (regE)) regE
    , Load (IndAddr (regE)) regH
    , Pop regF
    , Compute GtE regF regH regH
    , Branch regH (Abs 1)
    , Compute Lt regF reg0 regH
    , Branch regH (Abs 1)
    , Compute Add regE regF regE
    , Compute Incr regE regE regE
    , Push regE
    --Pointer creation - y
    , Compute Add regArp reg0 regB
    , Load (ImmValue (2)) regA
    , Compute Add regB regA regB
    , Pop regA
    , Store regA (IndAddr (regB))
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Load (IndAddr (regC)) regF
    , Compute Add regC regF regC
    , Compute Add regF reg0 regD
    , Branch regF (Rel 2)
    , Jump (Abs 132)
    , Load (IndAddr (regC)) regE
    , Push regE
    , Compute Decr regC regC regC
    , Compute Decr regF regF regF
    , Jump (Abs 125)
    , Push regD
    --Print: Array of 'int'
    , Pop regG
    , Branch regG (Rel 2)
    , Jump (Abs 140)
    , Pop regH
    , WriteInstr regH numberIO
    , Compute Decr regG regG regG
    , Jump (Abs 134)
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regA
    , Push regA
    , Compute Add regArp reg0 regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regD
    , Load (IndAddr (regD)) regD
    , Load (IndAddr (regD)) regC
    , Pop regB
    , Compute LtE regC regB regE
    , Branch regE (Abs 1)
    , Compute Lt regB reg0 regE
    , Branch regE (Abs 1)
    , Compute Add regD regB regD
    , Compute Incr regD regD regD
    , Load (IndAddr (regD)) regC
    , Push regC
    , Nop
    --ID Target
    , Compute Add regArp reg0 regG
    , Load (ImmValue (2)) regF
    , Compute Add regG regF regG
    , Load (IndAddr (regG)) regG
    , Pop regF
    , Store regF (IndAddr (regG))
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regA
    , Load (ImmValue (1)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Load (IndAddr (regH)) regC
    , Compute Add regH regC regH
    , Compute Add regC reg0 regA
    , Branch regC (Rel 2)
    , Jump (Abs 182)
    , Load (IndAddr (regH)) regB
    , Push regB
    , Compute Decr regH regH regH
    , Compute Decr regC regC regC
    , Jump (Abs 175)
    , Push regA
    --Print: Array of 'int'
    , Pop regD
    , Branch regD (Rel 2)
    , Jump (Abs 190)
    , Pop regE
    , WriteInstr regE numberIO
    , Compute Decr regD regD regD
    , Jump (Abs 184)
    , Nop
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
    , Branch regH (Abs 205)
    , Jump (Abs 205)
    , Jump (Abs 223)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 211)
    , Branch regG (Abs 217)
    , Jump (Abs 223)
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
