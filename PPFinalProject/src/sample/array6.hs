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
    , Jump (Abs (114))
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Load (ImmValue (97)) regE
    , Push regE
    , Load (ImmValue (65)) regF
    , Push regF
    , Load (ImmValue (2)) regG
    , Push regG
    , Compute Add regHeap reg0 regH
    , Load (ImmValue (1)) regA
    , Compute Add regArp regA regA
    , Store regHeap (IndAddr (regA))
    , Pop regB
    , Compute Lt regB reg0 regA
    , Branch regA (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regB regHeap
    , Store regB (IndAddr (regH))
    , Compute Incr regH regH regH
    , Branch regB (Rel (2))
    , Jump (Abs (88))
    , Pop regA
    , Store regA (IndAddr (regH))
    , Compute Incr regH regH regH
    , Compute Decr regB regB regB
    , Jump (Abs (81))
    , Nop
    , Nop
    , Nop
    , Nop
    , Load (ImmValue (2)) regC
    , Push regC
    , Compute Add regArp reg0 regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regF
    , Load (IndAddr (regF)) regF
    , Load (IndAddr (regF)) regE
    , Pop regD
    , Compute LtE regE regD regG
    , Branch regG (Abs (1))
    , Compute Lt regD reg0 regG
    , Branch regG (Abs (1))
    , Compute Add regF regD regF
    , Compute Incr regF regF regF
    , Load (IndAddr (regF)) regE
    , Push regE
    , Pop regH
    , WriteInstr regH charIO
    , Load (ImmValue (10)) regH
    , WriteInstr regH charIO
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
    , Branch regG (Abs (126))
    , Branch regH (Abs (126))
    , Jump (Abs (126))
    , Nop
    , Branch regH (Abs (130))
    , Branch regG (Abs (136))
    , Jump (Abs (142))
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
