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
    , ReadInstr (DirAddr (1))
    , Receive regG
    , Load (ImmValue (0)) regF
    , Compute Add regF regG regF
    , WriteInstr reg0 (IndAddr (regF))
    , Nop
    , Jump (Abs 196)
    , Nop
    , Nop
    , Load (ImmValue (0)) regE
    , Compute Add regE regArp regE
    , Store regHeap (IndAddr (regE))
    , Pop regF
    , Store regF (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Branch regF (Rel 2)
    , Jump (Abs 66)
    , Pop regD
    , Store regD (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Compute Decr regF regF regF
    , Jump (Abs 59)
    , Nop
    , Load (ImmValue (1)) regG
    , Compute Add regG regArp regG
    , Store reg0 (IndAddr (regG))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regB
    , Load (ImmValue (1)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    , Load (ImmValue (10)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Lt regF regE regG
    , Push regG
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 163)
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (7)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Store regE (IndAddr (regH))
    , Compute Incr regH regH regArp
    , Jump (Rel 2)
    , Jump (Abs 131)
    , Load (ImmValue (102)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    , Nop
    , Compute Add regArp reg0 regH
    , Compute Decr regH regH regH
    , Load (IndAddr (regH)) regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Nop
    , Load (ImmValue (2)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Add regD regC regE
    , Push regE
    , Nop
    , Compute Add regArp reg0 regG
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regG
    , Load (ImmValue (1)) regF
    , Compute Add regG regF regG
    , Pop regF
    , Store regF (IndAddr (regG))
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regA
    , Pop regB
    , Branch regA (Abs 0)
    , Branch regB (Abs 143)
    , Jump (Abs 143)
    , Jump (Abs 161)
    , Pop regA
    , Pop regB
    , Branch regB (Abs 149)
    , Branch regA (Abs 155)
    , Jump (Abs 161)
    , Nop
    , Push regB
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind regH)
    , Nop
    , Push regB
    , Push regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind regH)
    , Nop
    , Jump (Abs 71)
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (100)) regH
    , Push regH
    , Load (ImmValue (110)) regH
    , Push regH
    , Load (ImmValue (101)) regH
    , Push regH
    , Load (ImmValue (3)) regH
    , Push regH
    , Pop regA
    , Branch regA (Rel 2)
    , Jump (Abs 181)
    , Pop regB
    , WriteInstr regB charIO
    , Compute Decr regA regA regA
    , Jump (Abs 175)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Pop regA
    , Pop regB
    , Branch regB (Abs 192)
    , Pop regC
    , Push regB
    , Push regA
    , Jump (Ind regC)
    , Nop
    , Push regB
    , Push regA
    , Jump (Ind regC)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Store regH (IndAddr (regC))
    , Compute Incr regC regC regArp
    , Jump (Rel 2)
    , Jump (Abs 267)
    , Load (ImmValue (211)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (3)) regB
    , Compute Add regB regArp regB
    , Compute Decr regB regB regB
    , Store regArp (IndAddr (regB))
    , Compute Decr regB regB regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regB))
    , Load (ImmValue (3)) regB
    , Compute Add regB regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 247)
    , Load (ImmValue (226)) regB
    , Push regB
    , Jump (Rel 2)
    , Jump (Abs 247)
    , Load (ImmValue (230)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (3)) regF
    , Push regF
    , Nop
    , Load (ImmValue (2)) regG
    , Push regG
    , Nop
    , Load (ImmValue (1)) regH
    , Push regH
    , Load (ImmValue (3)) regA
    , Push regA
    , Jump (Abs 51)
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regD
    , Push regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Branch regD (Ind regC)
    , Pop regD
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 264)
    , Pop regD
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regD
    , Pop regE
    , Branch regD (Abs 279)
    , Branch regE (Abs 323)
    , Jump (Abs 323)
    , Nop
    , Load (ImmValue (0)) regD
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , Store regE (IndAddr (regC))
    , Compute Decr regC regC regC
    , Store regD (IndAddr (regC))
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 317)
    , Load (ImmValue (292)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (108)) regB
    , Push regB
    , Load (ImmValue (105)) regB
    , Push regB
    , Load (ImmValue (97)) regB
    , Push regB
    , Load (ImmValue (102)) regB
    , Push regB
    , Load (ImmValue (4)) regB
    , Push regB
    , Pop regC
    , Branch regC (Rel 2)
    , Jump (Abs 314)
    , Pop regD
    , WriteInstr regD charIO
    , Compute Decr regC regC regC
    , Jump (Abs 308)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regD
    , Pop regE
    , Jump (Abs 323)
    , Jump (Abs 341)
    , Pop regD
    , Pop regE
    , Branch regE (Abs 329)
    , Branch regD (Abs 335)
    , Jump (Abs 341)
    , Nop
    , Push regE
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind regC)
    , Nop
    , Push regE
    , Push regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind regC)
    , Nop
    , EndProg
    ]
main = run [prog]
