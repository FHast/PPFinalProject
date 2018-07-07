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
    , Receive regH
    , Branch regH (Ind (regH))
    , Jump (Abs (33))
    , Nop
    , Load (ImmValue (5)) regA
    , WriteInstr regA (DirAddr (3))
    , Load (ImmValue (5000)) regA
    , WriteInstr regA (DirAddr (4))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Jump (Abs (794))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (770))
    , Load (ImmValue (48)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (2)) regA
    , Push regA
    , ReadInstr (DirAddr (4))
    , Receive regE
    , ReadInstr (DirAddr (3))
    , Receive regF
    , Compute Add regE reg0 regB
    , Load (ImmValue (0)) regC
    , Compute Add regF regC regC
    , WriteInstr regB (IndAddr (regC))
    , Pop regD
    , Compute Lt regD reg0 regC
    , Branch regC (Abs (1))
    , Compute Incr regE regE regE
    , Compute Add regE regD regE
    , WriteInstr regE (DirAddr (4))
    , WriteInstr regD (IndAddr (regB))
    , Compute Incr regB regB regB
    , Branch regD (Rel (2))
    , Jump (Abs (77))
    , WriteInstr reg0 (IndAddr (regB))
    , Compute Incr regB regB regB
    , Compute Decr regD regD regD
    , Jump (Abs (71))
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , WriteInstr reg0 (IndAddr (regG))
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (2)) regA
    , Compute Add regA regB regA
    , WriteInstr reg0 (IndAddr (regA))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (94))
    , Load (ImmValue (90)) regC
    , WriteInstr regC (DirAddr (1))
    , Jump (Abs (442))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (411))
    , Load (ImmValue (110)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (1)) regC
    , Push regC
    , Nop
    , Nop
    , Load (ImmValue (0)) regD
    , Push regD
    , ReadInstr (DirAddr (3))
    , Receive regA
    , Load (ImmValue (0)) regE
    , Compute Add regE regA regE
    , ReadInstr (IndAddr (regE))
    , Receive regE
    , ReadInstr (IndAddr (regE))
    , Receive regH
    , Pop regF
    , Compute GtE regF regH regH
    , Branch regH (Abs (1))
    , Compute Lt regF reg0 regH
    , Branch regH (Abs (1))
    , Compute Add regE regF regE
    , Compute Incr regE regE regE
    , Pop regG
    , WriteInstr regG (IndAddr (regE))
    , Nop
    , Nop
    , Load (ImmValue (1)) regB
    , Push regB
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regE
    , Load (ImmValue (1)) regD
    , Compute Add regD regE regD
    , Pop regC
    , WriteInstr regC (IndAddr (regD))
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (1)) regG
    , Push regG
    , ReadInstr (DirAddr (3))
    , Receive regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , ReadInstr (IndAddr (regB))
    , Receive regB
    , ReadInstr (IndAddr (regB))
    , Receive regA
    , Pop regH
    , Compute GtE regH regA regA
    , Branch regA (Abs (1))
    , Compute Lt regH reg0 regA
    , Branch regA (Abs (1))
    , Compute Add regB regH regB
    , Compute Incr regB regB regB
    , ReadInstr (IndAddr (regB))
    , Receive regA
    , Push regA
    , Nop
    , Load (ImmValue (1)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Equal regF regE regH
    , Push regH
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (1)) regB
    , Compute Add regB regD regB
    , ReadInstr (IndAddr (regB))
    , Receive regA
    , Push regA
    , Nop
    , Load (ImmValue (1)) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Equal regG regF regA
    , Push regA
    , Pop regB
    , Pop regC
    , Compute And regC regB regD
    , Push regD
    , Pop regF
    , Branch regF (Rel (2))
    , Jump (Abs (256))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regE
    , Compute Add regE regArp regE
    , Compute Decr regE regE regE
    , Store regArp (IndAddr (regE))
    , Compute Decr regE regE regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Store regB (IndAddr (regE))
    , Load (ImmValue (5)) regE
    , Compute Add regE regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (226))
    , Load (ImmValue (221)) regE
    , WriteInstr regE (IndAddr (regSprID))
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
    , Branch regF (Abs (238))
    , Branch regG (Abs (238))
    , Jump (Abs (238))
    , Nop
    , Branch regG (Abs (242))
    , Branch regF (Abs (248))
    , Jump (Abs (254))
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
    , Jump (Abs (150))
    , Nop
    , Nop
    , Load (ImmValue (30)) regD
    , Push regD
    , Load (ImmValue (1)) regE
    , Compute Add regE regArp regE
    , Pop regF
    , Store regF (IndAddr (regE))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (1)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Nop
    , Load (ImmValue (0)) regC
    , Push regC
    , Pop regD
    , Pop regE
    , Compute Gt regE regD regG
    , Push regG
    , Pop regG
    , Branch regG (Rel (2))
    , Jump (Abs (383))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
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
    , Load (ImmValue (7)) regH
    , Compute Add regH regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (353))
    , Load (ImmValue (299)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (2)) regH
    , Compute Add regH regB regH
    , ReadInstr (IndAddr (regH))
    , Receive regG
    , Push regG
    , Nop
    , Load (ImmValue (1)) regC
    , Push regC
    , Pop regD
    , Pop regE
    , Compute Add regE regD regF
    , Push regF
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regA
    , Load (ImmValue (2)) regH
    , Compute Add regH regA regH
    , Pop regG
    , WriteInstr regG (IndAddr (regH))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regC
    , Compute Decr regC regC regC
    , Load (IndAddr (regC)) regC
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
    , Nop
    , Compute Add regArp reg0 regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regB
    , Load (ImmValue (1)) regA
    , Compute Add regB regA regB
    , Pop regA
    , Store regA (IndAddr (regB))
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
    , Branch regA (Abs (365))
    , Branch regB (Abs (365))
    , Jump (Abs (365))
    , Nop
    , Branch regB (Abs (369))
    , Branch regA (Abs (375))
    , Jump (Abs (381))
    , Nop
    , Push regB
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind (regH))
    , Nop
    , Push regB
    , Push regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind (regH))
    , Nop
    , Jump (Abs (265))
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (0)) regC
    , Push regC
    , Nop
    , Nop
    , Load (ImmValue (0)) regD
    , Push regD
    , ReadInstr (DirAddr (3))
    , Receive regA
    , Load (ImmValue (0)) regE
    , Compute Add regE regA regE
    , ReadInstr (IndAddr (regE))
    , Receive regE
    , ReadInstr (IndAddr (regE))
    , Receive regH
    , Pop regF
    , Compute GtE regF regH regH
    , Branch regH (Abs (1))
    , Compute Lt regF reg0 regH
    , Branch regH (Abs (1))
    , Compute Add regE regF regE
    , Compute Incr regE regE regE
    , Pop regG
    , WriteInstr regG (IndAddr (regE))
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
    , Branch regE (Abs (423))
    , Branch regF (Abs (423))
    , Jump (Abs (423))
    , Nop
    , Branch regF (Abs (427))
    , Branch regE (Abs (433))
    , Jump (Abs (439))
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (449))
    , Load (ImmValue (445)) regB
    , WriteInstr regB (DirAddr (2))
    , Jump (Abs (742))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
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
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (711))
    , Load (ImmValue (465)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (1)) regB
    , Push regB
    , Nop
    , Nop
    , Load (ImmValue (1)) regC
    , Push regC
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (0)) regD
    , Compute Add regD regH regD
    , ReadInstr (IndAddr (regD))
    , Receive regD
    , ReadInstr (IndAddr (regD))
    , Receive regG
    , Pop regE
    , Compute GtE regE regG regG
    , Branch regG (Abs (1))
    , Compute Lt regE reg0 regG
    , Branch regG (Abs (1))
    , Compute Add regD regE regD
    , Compute Incr regD regD regD
    , Pop regF
    , WriteInstr regF (IndAddr (regD))
    , Nop
    , Nop
    , Load (ImmValue (0)) regA
    , Push regA
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , Pop regB
    , WriteInstr regB (IndAddr (regC))
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (0)) regF
    , Push regF
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , ReadInstr (IndAddr (regA))
    , Receive regA
    , ReadInstr (IndAddr (regA))
    , Receive regH
    , Pop regG
    , Compute GtE regG regH regH
    , Branch regH (Abs (1))
    , Compute Lt regG reg0 regH
    , Branch regH (Abs (1))
    , Compute Add regA regG regA
    , Compute Incr regA regA regA
    , ReadInstr (IndAddr (regA))
    , Receive regH
    , Push regH
    , Nop
    , Load (ImmValue (1)) regC
    , Push regC
    , Pop regD
    , Pop regE
    , Compute Equal regE regD regG
    , Push regG
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regC
    , Load (ImmValue (1)) regA
    , Compute Add regA regC regA
    , ReadInstr (IndAddr (regA))
    , Receive regH
    , Push regH
    , Nop
    , Load (ImmValue (0)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Equal regF regE regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute And regB regA regC
    , Push regC
    , Pop regE
    , Branch regE (Rel (2))
    , Jump (Abs (611))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (581))
    , Load (ImmValue (576)) regD
    , WriteInstr regD (IndAddr (regSprID))
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
    , Branch regE (Abs (593))
    , Branch regF (Abs (593))
    , Jump (Abs (593))
    , Nop
    , Branch regF (Abs (597))
    , Branch regE (Abs (603))
    , Jump (Abs (609))
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Jump (Abs (505))
    , Nop
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regF
    , Load (ImmValue (2)) regD
    , Compute Add regD regF regD
    , ReadInstr (IndAddr (regD))
    , Receive regC
    , Push regC
    , Nop
    , Load (ImmValue (10)) regG
    , Push regG
    , Pop regH
    , Pop regA
    , Compute Add regA regH regB
    , Push regB
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regE
    , Load (ImmValue (2)) regD
    , Compute Add regD regE regD
    , Pop regC
    , WriteInstr regC (IndAddr (regD))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regA
    , Load (ImmValue (2)) regG
    , Compute Add regG regA regG
    , ReadInstr (IndAddr (regG))
    , Receive regF
    , Push regF
    , Nop
    , Load (ImmValue (20)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Sub regD regC regE
    , Push regE
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (2)) regG
    , Compute Add regG regH regG
    , Pop regF
    , WriteInstr regF (IndAddr (regG))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (2)) regB
    , Compute Add regB regD regB
    , ReadInstr (IndAddr (regB))
    , Receive regA
    , Push regA
    , Nop
    , Load (ImmValue (100)) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Add regG regF regH
    , Push regH
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regC
    , Load (ImmValue (2)) regB
    , Compute Add regB regC regB
    , Pop regA
    , WriteInstr regA (IndAddr (regB))
    , Nop
    , Nop
    , Load (ImmValue (0)) regD
    , Push regD
    , Nop
    , Nop
    , Load (ImmValue (1)) regE
    , Push regE
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (0)) regF
    , Compute Add regF regB regF
    , ReadInstr (IndAddr (regF))
    , Receive regF
    , ReadInstr (IndAddr (regF))
    , Receive regA
    , Pop regG
    , Compute GtE regG regA regA
    , Branch regA (Abs (1))
    , Compute Lt regG reg0 regA
    , Branch regA (Abs (1))
    , Compute Add regF regG regF
    , Compute Incr regF regF regF
    , Pop regH
    , WriteInstr regH (IndAddr (regF))
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
    , Branch regD (Abs (723))
    , Branch regE (Abs (723))
    , Jump (Abs (723))
    , Nop
    , Branch regE (Abs (727))
    , Branch regD (Abs (733))
    , Jump (Abs (739))
    , Nop
    , Push regE
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind (regC))
    , Nop
    , Push regE
    , Push regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind (regC))
    , Nop
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , ReadInstr (DirAddr (1))
    , Receive regC
    , Compute NEq regC reg0 regC
    , Branch regC (Abs (744))
    , Nop
    , ReadInstr (DirAddr (2))
    , Receive regD
    , Compute NEq regD reg0 regD
    , Branch regD (Abs (749))
    , Nop
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (2)) regF
    , Compute Add regF regH regF
    , ReadInstr (IndAddr (regF))
    , Receive regE
    , Push regE
    , Load (ImmValue (1)) regA
    , Push regA
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind (regA))
    , Push reg0
    , Push reg0
    , Nop
    , Pop regC
    , Pop regD
    , Branch regD (Abs (778))
    , Pop regE
    , Push regD
    , Push regC
    , Jump (Ind (regE))
    , Nop
    , Branch regD (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (1)) regB
    , Compute Add regB regArp regB
    , Pop regG
    , Store regG (IndAddr (regB))
    , Pop regE
    , Load (ImmValue (1)) regB
    , Compute Add regB regArp regB
    , Load (IndAddr (regB)) regG
    , Push regG
    , Push regD
    , Push regC
    , Jump (Ind (regE))
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
    , Jump (Abs (851))
    , Load (ImmValue (810)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
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
    , Jump (Abs (830))
    , Load (ImmValue (824)) regA
    , Push regA
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
    , Pop regE
    , WriteInstr regE numberIO
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
    , Branch regC (Abs (863))
    , Branch regD (Abs (863))
    , Jump (Abs (863))
    , Nop
    , Branch regD (Abs (867))
    , Branch regC (Abs (873))
    , Jump (Abs (879))
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
main = run [prog,prog,prog]
