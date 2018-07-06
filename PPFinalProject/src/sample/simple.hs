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
    , Jump (Abs 118)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (10)) regE
    , Push regE
    , Load (ImmValue (0)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Pop regC
    , WriteInstr regC numberIO
    , Nop
    , Nop
    , Compute Add regArp reg0 regE
    , Load (ImmValue (0)) regD
    , Compute Add regE regD regE
    , Push regE
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regG regF regG
    , Pop regF
    , Store regF (IndAddr (regG))
    , Nop
    , Nop
    , Load (ImmValue (20)) regH
    , Push regH
    , Nop
    , Compute Add regArp reg0 regB
    , Load (ImmValue (1)) regA
    , Compute Add regB regA regB
    , Load (IndAddr (regB)) regB
    , Pop regA
    , Store regA (IndAddr (regB))
    , Nop
    , Compute Add regArp reg0 regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Pop regF
    , WriteInstr regF numberIO
    , Nop
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Pop regB
    , WriteInstr regB numberIO
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
    , Branch regG (Abs 130)
    , Branch regH (Abs 178)
    , Jump (Abs 178)
    , Nop
    , Load (ImmValue (0)) regG
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Compute Decr regF regF regF
    , Store regH (IndAddr (regF))
    , Compute Decr regF regF regF
    , Store regG (IndAddr (regF))
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 172)
    , Load (ImmValue (147)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (108)) regC
    , Push regC
    , Load (ImmValue (105)) regC
    , Push regC
    , Load (ImmValue (97)) regC
    , Push regC
    , Load (ImmValue (102)) regC
    , Push regC
    , Load (ImmValue (4)) regC
    , Push regC
    , Pop regD
    , Branch regD (Rel 2)
    , Jump (Abs 169)
    , Pop regE
    , WriteInstr regE charIO
    , Compute Decr regD regD regD
    , Jump (Abs 163)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regG
    , Pop regH
    , Jump (Abs 178)
    , Jump (Abs 196)
    , Pop regG
    , Pop regH
    , Branch regH (Abs 184)
    , Branch regG (Abs 190)
    , Jump (Abs 196)
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
