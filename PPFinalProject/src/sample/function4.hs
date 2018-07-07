import Sprockell

prog :: [Instruction]
prog = [
      Jump (Abs (30))
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
    , Branch regSprID (Rel (2))
    , Jump (Abs (38))
    , Nop
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regD (Ind (regD))
    , Jump (Abs (33))
    , Nop
    , Load (ImmValue (3)) regE
    , WriteInstr regE (DirAddr (1))
    , Load (ImmValue (5000)) regE
    , WriteInstr regE (DirAddr (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Jump (Abs (103))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (79))
    , Load (ImmValue (48)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Pop regE
    , Load (ImmValue (1)) regF
    , Compute Add regF regArp regF
    , Store regE (IndAddr (regF))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (1)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Nop
    , Load (ImmValue (4)) regC
    , Push regC
    , Pop regD
    , Pop regE
    , Compute Add regE regD regF
    , Push regF
    , Nop
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regH regG regH
    , Pop regG
    , Store regG (IndAddr (regH))
    , Push reg0
    , Push reg0
    , Nop
    , Pop regG
    , Pop regH
    , Branch regH (Abs (87))
    , Pop regA
    , Push regH
    , Push regG
    , Jump (Ind (regA))
    , Nop
    , Branch regH (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Pop regC
    , Store regC (IndAddr (regF))
    , Pop regA
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Load (IndAddr (regF)) regC
    , Push regC
    , Push regH
    , Push regG
    , Jump (Ind (regA))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Store regF (IndAddr (regA))
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (164))
    , Load (ImmValue (119)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (2)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regH))
    , Jump (Rel (2))
    , Jump (Abs (143))
    , Load (ImmValue (133)) regH
    , Push regH
    , Nop
    , Nop
    , Load (ImmValue (2)) regD
    , Push regD
    , Load (ImmValue (2)) regH
    , Compute Add regH regArp regArp
    , Jump (Abs (46))
    , Nop
    , Pop regB
    , Push regB
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Branch regB (Ind (regA))
    , Pop regB
    , Pop regC
    , Branch regC (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Pop regE
    , WriteInstr regE numberIO
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
    , Branch regB (Abs (176))
    , Branch regC (Abs (176))
    , Jump (Abs (176))
    , Nop
    , Branch regC (Abs (180))
    , Branch regB (Abs (186))
    , Jump (Abs (192))
    , Nop
    , Push regC
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind (regA))
    , Nop
    , Push regC
    , Push regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind (regA))
    , Nop
    , EndProg
    ]
main = run [prog]
