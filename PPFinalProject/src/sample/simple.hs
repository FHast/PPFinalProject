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
    , Jump (Abs 242)
    , Nop
    , Nop
    , Load (ImmValue (0)) regC
    , Compute Add regC regArp regC
    , Store regHeap (IndAddr (regC))
    , Pop regD
    , Store regD (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Branch regD (Rel 2)
    , Jump (Abs 61)
    , Pop regB
    , Store regB (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Compute Decr regD regD regD
    , Jump (Abs 54)
    , Nop
    , Load (ImmValue (1)) regE
    , Compute Add regE regArp regE
    , Store reg0 (IndAddr (regE))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Nop
    , Compute Add regArp reg0 regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Load (IndAddr (regB)) regE
    , Compute Add regB regE regB
    , Compute Add regE reg0 regC
    , Branch regE (Rel 2)
    , Jump (Abs 88)
    , Load (IndAddr (regB)) regD
    , Push regD
    , Compute Decr regB regB regB
    , Compute Decr regE regE regE
    , Jump (Abs 81)
    , Push regC
    , Pop regF
    , Compute Add reg0 reg0 regG
    , Compute GtE regG regF regH
    , Branch regH (Abs 96)
    , Pop regH
    , Compute Incr regG regG regG
    , Jump (Abs 91)
    , Nop
    , Push regF
    , Pop regA
    , Pop regB
    , Compute Lt regB regA regC
    , Push regC
    , Pop regF
    , Branch regF (Rel 2)
    , Jump (Abs 209)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (7)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (7)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 177)
    , Load (ImmValue (121)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    , Compute Add regArp reg0 regD
    , Compute Decr regD regD regD
    , Load (IndAddr (regD)) regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Compute Add regArp reg0 regH
    , Compute Decr regH regH regH
    , Load (IndAddr (regH)) regH
    , Load (ImmValue (0)) regG
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
    , Pop regB
    , WriteInstr regB numberIO
    , Nop
    , Nop
    , Compute Add regArp reg0 regD
    , Compute Decr regD regD regD
    , Load (IndAddr (regD)) regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Nop
    , Load (ImmValue (1)) regF
    , Push regF
    , Pop regG
    , Pop regH
    , Compute Add regH regG regA
    , Push regA
    , Nop
    , Compute Add regArp reg0 regC
    , Compute Decr regC regC regC
    , Load (IndAddr (regC)) regC
    , Load (ImmValue (1)) regB
    , Compute Add regC regB regC
    , Pop regB
    , Store regB (IndAddr (regC))
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regE
    , Pop regF
    , Branch regE (Abs 0)
    , Branch regF (Abs 189)
    , Jump (Abs 189)
    , Jump (Abs 207)
    , Pop regE
    , Pop regF
    , Branch regF (Abs 195)
    , Branch regE (Abs 201)
    , Jump (Abs 207)
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind regD)
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind regD)
    , Nop
    , Jump (Abs 66)
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (100)) regD
    , Push regD
    , Load (ImmValue (110)) regD
    , Push regD
    , Load (ImmValue (101)) regD
    , Push regD
    , Load (ImmValue (3)) regD
    , Push regD
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 227)
    , Pop regF
    , WriteInstr regF charIO
    , Compute Decr regE regE regE
    , Jump (Abs 221)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Pop regG
    , Pop regH
    , Branch regH (Abs 238)
    , Pop regA
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regG
    , Compute Add regG regArp regG
    , Compute Decr regG regG regG
    , Store regArp (IndAddr (regG))
    , Compute Decr regG regG regG
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Store regD (IndAddr (regG))
    , Load (ImmValue (5)) regG
    , Compute Add regG regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 314)
    , Load (ImmValue (258)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (3)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regF))
    , Load (ImmValue (3)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 294)
    , Load (ImmValue (273)) regF
    , Push regF
    , Jump (Rel 2)
    , Jump (Abs 294)
    , Load (ImmValue (277)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (9)) regB
    , Push regB
    , Nop
    , Load (ImmValue (8)) regC
    , Push regC
    , Nop
    , Load (ImmValue (7)) regD
    , Push regD
    , Load (ImmValue (3)) regE
    , Push regE
    , Jump (Abs 46)
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regH
    , Push regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Branch regH (Ind regG)
    , Pop regH
    , Pop regA
    , Branch regA (Rel 2)
    , Jump (Abs 311)
    , Pop regH
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regH
    , Pop regA
    , Branch regH (Abs 326)
    , Branch regA (Abs 370)
    , Jump (Abs 370)
    , Nop
    , Load (ImmValue (0)) regH
    , Load (ImmValue (5)) regG
    , Compute Add regG regArp regG
    , Compute Decr regG regG regG
    , Store regArp (IndAddr (regG))
    , Compute Decr regG regG regG
    , Store regA (IndAddr (regG))
    , Compute Decr regG regG regG
    , Store regH (IndAddr (regG))
    , Load (ImmValue (5)) regG
    , Compute Add regG regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 364)
    , Load (ImmValue (339)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (108)) regF
    , Push regF
    , Load (ImmValue (105)) regF
    , Push regF
    , Load (ImmValue (97)) regF
    , Push regF
    , Load (ImmValue (102)) regF
    , Push regF
    , Load (ImmValue (4)) regF
    , Push regF
    , Pop regG
    , Branch regG (Rel 2)
    , Jump (Abs 361)
    , Pop regH
    , WriteInstr regH charIO
    , Compute Decr regG regG regG
    , Jump (Abs 355)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regH
    , Pop regA
    , Jump (Abs 370)
    , Jump (Abs 388)
    , Pop regH
    , Pop regA
    , Branch regA (Abs 376)
    , Branch regH (Abs 382)
    , Jump (Abs 388)
    , Nop
    , Push regA
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Jump (Ind regG)
    , Nop
    , Push regA
    , Push regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Jump (Ind regG)
    , Nop
    , EndProg
    ]
main = run [prog]
