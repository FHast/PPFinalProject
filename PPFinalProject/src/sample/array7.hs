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
    , Branch regHeap (Rel (2))
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
    , Jump (Rel (2))
    , Jump (Abs (113))
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (-1)) regE
    , Push regE
    , Compute Add regHeap reg0 regF
    , Load (ImmValue (0)) regG
    , Compute Add regArp regG regG
    , Store regHeap (IndAddr (regG))
    , Pop regH
    , Compute Lt regH reg0 regG
    , Branch regG (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regH regHeap
    , Store regH (IndAddr (regF))
    , Compute Incr regF regF regF
    , Branch regH (Rel (2))
    , Jump (Abs (83))
    , Store reg0 (IndAddr (regF))
    , Compute Incr regF regF regF
    , Compute Decr regH regH regH
    , Jump (Abs (77))
    , Nop
    , Nop
    , Nop
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Load (IndAddr (regA)) regD
    , Compute Add regA regD regA
    , Compute Add regD reg0 regB
    , Branch regD (Rel (2))
    , Jump (Abs (100))
    , Load (IndAddr (regA)) regC
    , Push regC
    , Compute Decr regA regA regA
    , Compute Decr regD regD regD
    , Jump (Abs (93))
    , Push regB
    , Pop regE
    , Branch regE (Rel (2))
    , Jump (Abs (108))
    , Pop regF
    , WriteInstr regF charIO
    , Compute Decr regE regE regE
    , Jump (Abs (102))
    , Nop
    , Load (ImmValue (10)) regF
    , WriteInstr regF charIO
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
    , Branch regG (Abs (125))
    , Branch regH (Abs (125))
    , Jump (Abs (125))
    , Nop
    , Branch regH (Abs (129))
    , Branch regG (Abs (135))
    , Jump (Abs (141))
    , Nop
    , Push regH
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind (regF))
    , Nop
    , Push regH
    , Push regG
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Jump (Ind (regF))
    , Nop
    , EndProg
    ]
main = run [prog]
