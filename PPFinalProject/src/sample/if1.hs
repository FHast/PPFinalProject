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
    , Jump (Abs 284)
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
    --If - condition
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Load (IndAddr (regF)) regA
    , Compute Add regF regA regF
    , Compute Add regA reg0 regG
    , Branch regA (Rel 2)
    , Jump (Abs 111)
    , Load (IndAddr (regF)) regH
    , Push regH
    , Compute Decr regF regF regF
    , Compute Decr regA regA regA
    , Jump (Abs 104)
    , Push regG
    , Pop regB
    , Compute Add reg0 reg0 regC
    , Compute GtE regC regB regD
    , Branch regD (Abs 119)
    , Pop regD
    , Compute Incr regC regC regC
    , Jump (Abs 114)
    , Nop
    , Push regB
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Gt regG regF regH
    , Push regH
    , Pop regD
    --If
    , Load (ImmValue (1)) regE
    , Compute Xor regD regE regD
    , Branch regD (Abs 207)
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (7)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Store regF (IndAddr (regA))
    , Load (ImmValue (7)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 175)
    , Load (ImmValue (147)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 0
    , Load (ImmValue (0)) regH
    , Push regH
    , Compute Add regArp reg0 regC
    , Compute Decr regC regC regC
    , Load (IndAddr (regC)) regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regC
    , Load (IndAddr (regC)) regC
    , Load (IndAddr (regC)) regB
    , Pop regA
    , Compute LtE regB regA regD
    , Branch regD (Abs 1)
    , Compute Lt regA reg0 regD
    , Branch regD (Abs 1)
    , Compute Add regC regA regC
    , Compute Incr regC regC regC
    , Load (IndAddr (regC)) regB
    , Push regB
    --Print: int
    , Pop regE
    , WriteInstr regE numberIO
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regB
    , Pop regC
    , Branch regB (Abs 0)
    , Branch regC (Abs 187)
    , Jump (Abs 187)
    , Jump (Abs 205)
    , Pop regB
    , Pop regC
    --Block - end
    , Branch regC (Abs 193)
    , Branch regB (Abs 199)
    , Jump (Abs 205)
    , Nop
    , Push regC
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind regA)
    , Nop
    , Push regC
    , Push regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind regA)
    , Nop
    , Jump (Abs 281)
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (8)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Load (ImmValue (8)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 250)
    , Load (ImmValue (222)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regE
    , Push regE
    , Compute Add regArp reg0 regH
    , Compute Decr regH regH regH
    , Load (IndAddr (regH)) regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regH
    , Load (IndAddr (regH)) regH
    , Load (IndAddr (regH)) regG
    , Pop regF
    , Compute LtE regG regF regA
    , Branch regA (Abs 1)
    , Compute Lt regF reg0 regA
    , Branch regA (Abs 1)
    , Compute Add regH regF regH
    , Compute Incr regH regH regH
    , Load (IndAddr (regH)) regG
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
    , Branch regH (Abs 262)
    , Jump (Abs 262)
    , Jump (Abs 280)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 268)
    , Branch regG (Abs 274)
    , Jump (Abs 280)
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
    , Branch regH (Abs 296)
    , Jump (Abs 296)
    , Jump (Abs 314)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 302)
    , Branch regG (Abs 308)
    , Jump (Abs 314)
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
