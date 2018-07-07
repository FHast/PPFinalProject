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
    , Jump (Abs (368))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (344))
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
    , Compute Add regArp reg0 regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Push regB
    , Nop
    , Load (ImmValue (0)) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Equal regG regF regA
    , Push regA
    , Pop regH
    , Load (ImmValue (1)) regA
    , Compute Xor regH regA regH
    , Branch regH (Abs (134))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (8)) regB
    , Compute Add regB regArp regB
    , Compute Decr regB regB regB
    , Store regArp (IndAddr (regB))
    , Compute Decr regB regB regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regB))
    , Load (ImmValue (8)) regB
    , Compute Add regB regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (105))
    , Load (ImmValue (90)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (0)) regA
    , Push regA
    , Load (ImmValue (1)) regB
    , Push regB
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
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
    , Branch regC (Abs (117))
    , Branch regD (Abs (117))
    , Jump (Abs (117))
    , Nop
    , Branch regD (Abs (121))
    , Branch regC (Abs (127))
    , Jump (Abs (133))
    , Nop
    , Push regD
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , Push regD
    , Push regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    , Nop
    , Load (ImmValue (1)) regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute Equal regB regA regD
    , Push regD
    , Pop regC
    , Load (ImmValue (1)) regD
    , Compute Xor regC regD regC
    , Branch regC (Abs (213))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (9)) regE
    , Compute Add regE regArp regE
    , Compute Decr regE regE regE
    , Store regArp (IndAddr (regE))
    , Compute Decr regE regE regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Store regB (IndAddr (regE))
    , Load (ImmValue (9)) regE
    , Compute Add regE regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (184))
    , Load (ImmValue (169)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (1)) regD
    , Push regD
    , Load (ImmValue (1)) regE
    , Push regE
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Jump (Ind (regE))
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regF
    , Pop regG
    , Branch regF (Abs (196))
    , Branch regG (Abs (196))
    , Jump (Abs (196))
    , Nop
    , Branch regG (Abs (200))
    , Branch regF (Abs (206))
    , Jump (Abs (212))
    , Nop
    , Push regG
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Jump (Ind (regE))
    , Nop
    , Push regG
    , Push regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Jump (Ind (regE))
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (7)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regF))
    , Jump (Rel (2))
    , Jump (Abs (245))
    , Load (ImmValue (224)) regF
    , Push regF
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Push regB
    , Nop
    , Load (ImmValue (1)) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Sub regG regF regH
    , Push regH
    , Load (ImmValue (7)) regF
    , Compute Add regF regArp regArp
    , Jump (Abs (46))
    , Nop
    , Pop regH
    , Push regH
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Branch regH (Ind (regG))
    , Pop regH
    , Pop regA
    , Branch regA (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Load (ImmValue (8)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Store regD (IndAddr (regC))
    , Jump (Rel (2))
    , Jump (Abs (297))
    , Load (ImmValue (276)) regC
    , Push regC
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Nop
    , Load (ImmValue (2)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Sub regD regC regE
    , Push regE
    , Load (ImmValue (8)) regC
    , Compute Add regC regArp regArp
    , Jump (Abs (46))
    , Nop
    , Pop regE
    , Push regE
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regE (Ind (regD))
    , Pop regE
    , Pop regF
    , Branch regF (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (6)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (5)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Nop
    , Compute Add regArp reg0 regD
    , Load (ImmValue (6)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Pop regF
    , Pop regG
    , Compute Add regG regF regH
    , Push regH
    , Load (ImmValue (1)) regA
    , Push regA
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind (regA))
    , Push reg0
    , Push reg0
    , Nop
    , Pop regG
    , Pop regH
    , Branch regH (Abs (352))
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
    , Jump (Rel (2))
    , Jump (Abs (439))
    , Load (ImmValue (384)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (2)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Store regB (IndAddr (regA))
    , Jump (Rel (2))
    , Jump (Abs (407))
    , Load (ImmValue (397)) regA
    , Push regA
    , Nop
    , Nop
    , Load (ImmValue (10)) regE
    , Push regE
    , Load (ImmValue (2)) regA
    , Compute Add regA regArp regArp
    , Jump (Abs (46))
    , Nop
    , Pop regC
    , Push regC
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Branch regC (Ind (regB))
    , Pop regC
    , Pop regD
    , Branch regD (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (0)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Pop regC
    , WriteInstr regC numberIO
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
    , Branch regC (Abs (451))
    , Branch regD (Abs (451))
    , Jump (Abs (451))
    , Nop
    , Branch regD (Abs (455))
    , Branch regC (Abs (461))
    , Jump (Abs (467))
    , Nop
    , Push regD
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , Push regD
    , Push regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , EndProg
    ]
main = run [prog]
