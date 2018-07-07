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
    , Load (ImmValue (9)) regA
    , WriteInstr regA (DirAddr (7))
    , Load (ImmValue (5000)) regA
    , WriteInstr regA (DirAddr (8))
    , Load (ImmValue (5000)) regHeap
    , ReadInstr (DirAddr (7))
    , Receive regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , WriteInstr reg0 (IndAddr (regB))
    , Nop
    , Jump (Abs (1352))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (1328))
    , Load (ImmValue (53)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Pop regC
    , Load (ImmValue (1)) regD
    , Compute Add regD regArp regD
    , Store regC (IndAddr (regD))
    , Nop
    , Nop
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , Pop regA
    , WriteInstr regA (IndAddr (regB))
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (81))
    , Load (ImmValue (77)) regD
    , WriteInstr regD (DirAddr (1))
    , Jump (Abs (275))
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
    , Jump (Abs (244))
    , Load (ImmValue (97)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (10)) regD
    , Push regD
    , Load (ImmValue (0)) regE
    , Compute Add regE regArp regE
    , Pop regF
    , Store regF (IndAddr (regE))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
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
    , Jump (Abs (241))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Store regE (IndAddr (regH))
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (211))
    , Load (ImmValue (142)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , TestAndSet (IndAddr (regG))
    , Receive regA
    , Compute Equal regA reg0 regA
    , Branch regA (Abs (150))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regE
    , Load (ImmValue (0)) regC
    , Compute Add regC regE regC
    , ReadInstr (IndAddr (regC))
    , Receive regB
    , Push regB
    , Nop
    , Load (ImmValue (1)) regF
    , Push regF
    , Pop regG
    , Pop regH
    , Compute Sub regH regG regA
    , Push regA
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Pop regB
    , WriteInstr regB (IndAddr (regC))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regF
    , Compute Decr regF regF regF
    , Load (IndAddr (regF)) regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    , Nop
    , Load (ImmValue (1)) regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute Sub regB regA regC
    , Push regC
    , Nop
    , Compute Add regArp reg0 regE
    , Compute Decr regE regE regE
    , Load (IndAddr (regE)) regE
    , Load (ImmValue (0)) regD
    , Compute Add regE regD regE
    , Pop regD
    , Store regD (IndAddr (regE))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , WriteInstr reg0 (IndAddr (regF))
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
    , Branch regA (Abs (223))
    , Branch regB (Abs (223))
    , Jump (Abs (223))
    , Nop
    , Branch regB (Abs (227))
    , Branch regA (Abs (233))
    , Jump (Abs (239))
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
    , Jump (Abs (108))
    , Nop
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
    , Branch regF (Abs (256))
    , Branch regG (Abs (256))
    , Jump (Abs (256))
    , Nop
    , Branch regG (Abs (260))
    , Branch regF (Abs (266))
    , Jump (Abs (272))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (282))
    , Load (ImmValue (278)) regH
    , WriteInstr regH (DirAddr (2))
    , Jump (Abs (476))
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
    , Jump (Abs (445))
    , Load (ImmValue (298)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (15)) regH
    , Push regH
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regE
    , Load (ImmValue (0)) regD
    , Compute Add regD regE regD
    , Load (IndAddr (regD)) regD
    , Push regD
    , Nop
    , Load (ImmValue (0)) regG
    , Push regG
    , Pop regH
    , Pop regA
    , Compute Gt regA regH regC
    , Push regC
    , Pop regC
    , Branch regC (Rel (2))
    , Jump (Abs (442))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (412))
    , Load (ImmValue (343)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , TestAndSet (IndAddr (regC))
    , Receive regE
    , Compute Equal regE reg0 regE
    , Branch regE (Abs (351))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regA
    , Load (ImmValue (0)) regG
    , Compute Add regG regA regG
    , ReadInstr (IndAddr (regG))
    , Receive regF
    , Push regF
    , Nop
    , Load (ImmValue (1)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Sub regD regC regE
    , Push regE
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Pop regF
    , WriteInstr regF (IndAddr (regG))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    , Load (ImmValue (1)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Sub regF regE regG
    , Push regG
    , Nop
    , Compute Add regArp reg0 regA
    , Compute Decr regA regA regA
    , Load (IndAddr (regA)) regA
    , Load (ImmValue (0)) regH
    , Compute Add regA regH regA
    , Pop regH
    , Store regH (IndAddr (regA))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , WriteInstr reg0 (IndAddr (regB))
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
    , Branch regE (Abs (424))
    , Branch regF (Abs (424))
    , Jump (Abs (424))
    , Nop
    , Branch regF (Abs (428))
    , Branch regE (Abs (434))
    , Jump (Abs (440))
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
    , Jump (Abs (309))
    , Nop
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
    , Branch regB (Abs (457))
    , Branch regC (Abs (457))
    , Jump (Abs (457))
    , Nop
    , Branch regC (Abs (461))
    , Branch regB (Abs (467))
    , Jump (Abs (473))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (483))
    , Load (ImmValue (479)) regD
    , WriteInstr regD (DirAddr (3))
    , Jump (Abs (677))
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
    , Jump (Abs (646))
    , Load (ImmValue (499)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (123)) regD
    , Push regD
    , Load (ImmValue (0)) regE
    , Compute Add regE regArp regE
    , Pop regF
    , Store regF (IndAddr (regE))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
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
    , Jump (Abs (643))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Store regE (IndAddr (regH))
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (613))
    , Load (ImmValue (544)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , TestAndSet (IndAddr (regG))
    , Receive regA
    , Compute Equal regA reg0 regA
    , Branch regA (Abs (552))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regE
    , Load (ImmValue (0)) regC
    , Compute Add regC regE regC
    , ReadInstr (IndAddr (regC))
    , Receive regB
    , Push regB
    , Nop
    , Load (ImmValue (1)) regF
    , Push regF
    , Pop regG
    , Pop regH
    , Compute Add regH regG regA
    , Push regA
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Pop regB
    , WriteInstr regB (IndAddr (regC))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regF
    , Compute Decr regF regF regF
    , Load (IndAddr (regF)) regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    , Nop
    , Load (ImmValue (1)) regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute Sub regB regA regC
    , Push regC
    , Nop
    , Compute Add regArp reg0 regE
    , Compute Decr regE regE regE
    , Load (IndAddr (regE)) regE
    , Load (ImmValue (0)) regD
    , Compute Add regE regD regE
    , Pop regD
    , Store regD (IndAddr (regE))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , WriteInstr reg0 (IndAddr (regF))
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
    , Branch regA (Abs (625))
    , Branch regB (Abs (625))
    , Jump (Abs (625))
    , Nop
    , Branch regB (Abs (629))
    , Branch regA (Abs (635))
    , Jump (Abs (641))
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
    , Jump (Abs (510))
    , Nop
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
    , Branch regF (Abs (658))
    , Branch regG (Abs (658))
    , Jump (Abs (658))
    , Nop
    , Branch regG (Abs (662))
    , Branch regF (Abs (668))
    , Jump (Abs (674))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (684))
    , Load (ImmValue (680)) regH
    , WriteInstr regH (DirAddr (4))
    , Jump (Abs (878))
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
    , Jump (Abs (847))
    , Load (ImmValue (700)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (30)) regH
    , Push regH
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regE
    , Load (ImmValue (0)) regD
    , Compute Add regD regE regD
    , Load (IndAddr (regD)) regD
    , Push regD
    , Nop
    , Load (ImmValue (0)) regG
    , Push regG
    , Pop regH
    , Pop regA
    , Compute Gt regA regH regC
    , Push regC
    , Pop regC
    , Branch regC (Rel (2))
    , Jump (Abs (844))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (814))
    , Load (ImmValue (745)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , TestAndSet (IndAddr (regC))
    , Receive regE
    , Compute Equal regE reg0 regE
    , Branch regE (Abs (753))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regA
    , Load (ImmValue (0)) regG
    , Compute Add regG regA regG
    , ReadInstr (IndAddr (regG))
    , Receive regF
    , Push regF
    , Nop
    , Load (ImmValue (1)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Add regD regC regE
    , Push regE
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Pop regF
    , WriteInstr regF (IndAddr (regG))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    , Load (ImmValue (1)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Sub regF regE regG
    , Push regG
    , Nop
    , Compute Add regArp reg0 regA
    , Compute Decr regA regA regA
    , Load (IndAddr (regA)) regA
    , Load (ImmValue (0)) regH
    , Compute Add regA regH regA
    , Pop regH
    , Store regH (IndAddr (regA))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , WriteInstr reg0 (IndAddr (regB))
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
    , Branch regE (Abs (826))
    , Branch regF (Abs (826))
    , Jump (Abs (826))
    , Nop
    , Branch regF (Abs (830))
    , Branch regE (Abs (836))
    , Jump (Abs (842))
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
    , Jump (Abs (711))
    , Nop
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
    , Branch regB (Abs (859))
    , Branch regC (Abs (859))
    , Jump (Abs (859))
    , Nop
    , Branch regC (Abs (863))
    , Branch regB (Abs (869))
    , Jump (Abs (875))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (885))
    , Load (ImmValue (881)) regD
    , WriteInstr regD (DirAddr (5))
    , Jump (Abs (1079))
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
    , Jump (Abs (1048))
    , Load (ImmValue (901)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (23)) regD
    , Push regD
    , Load (ImmValue (0)) regE
    , Compute Add regE regArp regE
    , Pop regF
    , Store regF (IndAddr (regE))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
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
    , Jump (Abs (1045))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Store regE (IndAddr (regH))
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (1015))
    , Load (ImmValue (946)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , TestAndSet (IndAddr (regG))
    , Receive regA
    , Compute Equal regA reg0 regA
    , Branch regA (Abs (954))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regE
    , Load (ImmValue (0)) regC
    , Compute Add regC regE regC
    , ReadInstr (IndAddr (regC))
    , Receive regB
    , Push regB
    , Nop
    , Load (ImmValue (1)) regF
    , Push regF
    , Pop regG
    , Pop regH
    , Compute Sub regH regG regA
    , Push regA
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Pop regB
    , WriteInstr regB (IndAddr (regC))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regF
    , Compute Decr regF regF regF
    , Load (IndAddr (regF)) regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    , Nop
    , Load (ImmValue (1)) regH
    , Push regH
    , Pop regA
    , Pop regB
    , Compute Sub regB regA regC
    , Push regC
    , Nop
    , Compute Add regArp reg0 regE
    , Compute Decr regE regE regE
    , Load (IndAddr (regE)) regE
    , Load (ImmValue (0)) regD
    , Compute Add regE regD regE
    , Pop regD
    , Store regD (IndAddr (regE))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , WriteInstr reg0 (IndAddr (regF))
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
    , Branch regA (Abs (1027))
    , Branch regB (Abs (1027))
    , Jump (Abs (1027))
    , Nop
    , Branch regB (Abs (1031))
    , Branch regA (Abs (1037))
    , Jump (Abs (1043))
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
    , Jump (Abs (912))
    , Nop
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
    , Branch regF (Abs (1060))
    , Branch regG (Abs (1060))
    , Jump (Abs (1060))
    , Nop
    , Branch regG (Abs (1064))
    , Branch regF (Abs (1070))
    , Jump (Abs (1076))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , Jump (Rel (2))
    , Jump (Abs (1086))
    , Load (ImmValue (1082)) regH
    , WriteInstr regH (DirAddr (6))
    , Jump (Abs (1280))
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
    , Jump (Abs (1249))
    , Load (ImmValue (1102)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Load (ImmValue (50)) regH
    , Push regH
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regE
    , Load (ImmValue (0)) regD
    , Compute Add regD regE regD
    , Load (IndAddr (regD)) regD
    , Push regD
    , Nop
    , Load (ImmValue (0)) regG
    , Push regG
    , Pop regH
    , Pop regA
    , Compute Gt regA regH regC
    , Push regC
    , Pop regC
    , Branch regC (Rel (2))
    , Jump (Abs (1246))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (1216))
    , Load (ImmValue (1147)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , TestAndSet (IndAddr (regC))
    , Receive regE
    , Compute Equal regE reg0 regE
    , Branch regE (Abs (1155))
    , Nop
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regA
    , Load (ImmValue (0)) regG
    , Compute Add regG regA regG
    , ReadInstr (IndAddr (regG))
    , Receive regF
    , Push regF
    , Nop
    , Load (ImmValue (1)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Add regD regC regE
    , Push regE
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Pop regF
    , WriteInstr regF (IndAddr (regG))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    , Load (ImmValue (1)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Sub regF regE regG
    , Push regG
    , Nop
    , Compute Add regArp reg0 regA
    , Compute Decr regA regA regA
    , Load (IndAddr (regA)) regA
    , Load (ImmValue (0)) regH
    , Compute Add regA regH regA
    , Pop regH
    , Store regH (IndAddr (regA))
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , WriteInstr reg0 (IndAddr (regB))
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
    , Branch regE (Abs (1228))
    , Branch regF (Abs (1228))
    , Jump (Abs (1228))
    , Nop
    , Branch regF (Abs (1232))
    , Branch regE (Abs (1238))
    , Jump (Abs (1244))
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
    , Jump (Abs (1113))
    , Nop
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
    , Branch regB (Abs (1261))
    , Branch regC (Abs (1261))
    , Jump (Abs (1261))
    , Nop
    , Branch regC (Abs (1265))
    , Branch regB (Abs (1271))
    , Jump (Abs (1277))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    , Nop
    , Nop
    , ReadInstr (DirAddr (1))
    , Receive regD
    , Compute NEq regD reg0 regD
    , Branch regD (Abs (1282))
    , Nop
    , ReadInstr (DirAddr (2))
    , Receive regE
    , Compute NEq regE reg0 regE
    , Branch regE (Abs (1287))
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regF
    , Compute NEq regF reg0 regF
    , Branch regF (Abs (1292))
    , Nop
    , ReadInstr (DirAddr (4))
    , Receive regG
    , Compute NEq regG reg0 regG
    , Branch regG (Abs (1297))
    , Nop
    , ReadInstr (DirAddr (5))
    , Receive regH
    , Compute NEq regH reg0 regH
    , Branch regH (Abs (1302))
    , Nop
    , ReadInstr (DirAddr (6))
    , Receive regA
    , Compute NEq regA reg0 regA
    , Branch regA (Abs (1307))
    , Nop
    , Nop
    , ReadInstr (DirAddr (7))
    , Receive regE
    , Load (ImmValue (0)) regC
    , Compute Add regC regE regC
    , ReadInstr (IndAddr (regC))
    , Receive regB
    , Push regB
    , Load (ImmValue (1)) regF
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind (regF))
    , Push reg0
    , Push reg0
    , Nop
    , Pop regE
    , Pop regF
    , Branch regF (Abs (1336))
    , Pop regG
    , Push regF
    , Push regE
    , Jump (Ind (regG))
    , Nop
    , Branch regF (Rel (2))
    , Jump (Abs (1))
    , Nop
    , Load (ImmValue (2)) regD
    , Compute Add regD regArp regD
    , Pop regA
    , Store regA (IndAddr (regD))
    , Pop regG
    , Load (ImmValue (2)) regD
    , Compute Add regD regArp regD
    , Load (IndAddr (regD)) regA
    , Push regA
    , Push regF
    , Push regE
    , Jump (Ind (regG))
    , Nop
    , Nop
    , Branch regHeap (Rel (2))
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
    , Jump (Rel (2))
    , Jump (Abs (1414))
    , Load (ImmValue (1368)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regF))
    , Jump (Rel (2))
    , Jump (Abs (1393))
    , Load (ImmValue (1382)) regF
    , Push regF
    , Nop
    , Nop
    , ReadInstr numberIO
    , Receive regB
    , Push regB
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regArp
    , Jump (Abs (51))
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
    , Pop regD
    , WriteInstr regD numberIO
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
    , Branch regH (Abs (1426))
    , Branch regA (Abs (1426))
    , Jump (Abs (1426))
    , Nop
    , Branch regA (Abs (1430))
    , Branch regH (Abs (1436))
    , Jump (Abs (1442))
    , Nop
    , Push regA
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Jump (Ind (regG))
    , Nop
    , Push regA
    , Push regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Jump (Ind (regG))
    , Nop
    , EndProg
    ]
main = run [prog,prog,prog,prog,prog,prog,prog]
