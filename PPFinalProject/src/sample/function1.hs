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
    , Jump (Abs 98)
    --FunDef plus4 - body
    , Nop
    , Nop
    , Pop regB
    , Load (ImmValue (1)) regC
    , Compute Add regC regArp regC
    , Store regB (IndAddr (regC))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute Add regB regA regC
    , Push regC
    --Return
    , Load (ImmValue (1)) regD
    , Push regD
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind regD)
    , Push reg0
    , Push reg0
    , Nop
    --FunDef - end plus4
    , Pop regG
    , Pop regH
    , Branch regH (Abs 82)
    , Pop regA
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Branch regH (Rel 2)
    , Jump (Abs 1)
    , Nop
    --FunDef - ret value backup
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Pop regF
    , Store regF (IndAddr (regF))
    , Pop regA
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Nop
    , Jump (Abs 153)
    --FunDef times2 - body
    , Nop
    , Nop
    , Pop regD
    , Load (ImmValue (1)) regE
    , Compute Add regE regArp regE
    , Store regD (IndAddr (regE))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Mul regD regC regE
    , Push regE
    --Return
    , Load (ImmValue (1)) regF
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind regF)
    , Push reg0
    , Push reg0
    , Nop
    --FunDef - end times2
    , Pop regA
    , Pop regB
    , Branch regB (Abs 137)
    , Pop regC
    , Push regB
    , Push regA
    , Jump (Ind regC)
    , Nop
    , Branch regB (Rel 2)
    , Jump (Abs 1)
    , Nop
    --FunDef - ret value backup
    , Load (ImmValue (2)) regH
    , Compute Add regH regArp regH
    , Pop regH
    , Store regH (IndAddr (regH))
    , Pop regC
    , Load (ImmValue (2)) regH
    , Compute Add regH regArp regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Push regB
    , Push regA
    , Jump (Ind regC)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (5)) regB
    , Compute Add regB regArp regB
    , Compute Decr regB regB regB
    , Store regArp (IndAddr (regB))
    , Compute Decr regB regB regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regB))
    , Load (ImmValue (5)) regB
    , Compute Add regB regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 297)
    , Load (ImmValue (169)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Nop
    --Function - call plus4
    , Load (ImmValue (2)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Store regB (IndAddr (regA))
    , Load (ImmValue (2)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 197)
    , Load (ImmValue (185)) regA
    , Push regA
    , Jump (Rel 2)
    , Jump (Abs 197)
    , Load (ImmValue (189)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regE
    , Push regE
    , Jump (Abs 46)
    --Function - return plus4
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regC
    , Push regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Branch regC (Ind regB)
    , Pop regC
    , Pop regD
    , Branch regD (Rel 2)
    , Jump (Abs 1)
    --Print: int
    , Pop regF
    , WriteInstr regF numberIO
    , Nop
    , Nop
    --Function - call times2
    , Load (ImmValue (2)) regG
    , Compute Add regG regArp regG
    , Compute Decr regG regG regG
    , Store regArp (IndAddr (regG))
    , Compute Decr regG regG regG
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Store regH (IndAddr (regG))
    , Load (ImmValue (2)) regG
    , Compute Add regG regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 277)
    , Load (ImmValue (228)) regG
    , Push regG
    , Jump (Rel 2)
    , Jump (Abs 277)
    , Load (ImmValue (232)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Nop
    , Nop
    --Function - call plus4
    , Load (ImmValue (2)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Store regD (IndAddr (regC))
    , Load (ImmValue (2)) regC
    , Compute Add regC regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 260)
    , Load (ImmValue (248)) regC
    , Push regC
    , Jump (Rel 2)
    , Jump (Abs 260)
    , Load (ImmValue (252)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regG
    , Push regG
    , Jump (Abs 46)
    --Function - return plus4
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regE
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regE (Ind regD)
    , Pop regE
    , Pop regF
    , Branch regF (Rel 2)
    , Jump (Abs 1)
    , Jump (Abs 101)
    --Function - return times2
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regA
    , Push regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Branch regA (Ind regH)
    , Pop regA
    , Pop regB
    , Branch regB (Rel 2)
    , Jump (Abs 1)
    --Print: int
    , Pop regH
    , WriteInstr regH numberIO
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regC
    , Pop regD
    , Branch regC (Abs 0)
    , Branch regD (Abs 309)
    , Jump (Abs 309)
    , Jump (Abs 327)
    , Pop regC
    , Pop regD
    --Block - end
    , Branch regD (Abs 315)
    , Branch regC (Abs 321)
    , Jump (Abs 327)
    , Nop
    , Push regD
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind regB)
    , Nop
    , Push regD
    , Push regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind regB)
    , Nop
    , EndProg
    ]
main = run [prog]
