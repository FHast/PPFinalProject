import Sprockell

prog :: [Instruction]
prog = [
      Jump (Abs (32))
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
    , Load (ImmValue (10)) regA
    , WriteInstr regA charIO
    , EndProg
    , Nop
    , Branch regSprID (Rel (2))
    , Jump (Abs (40))
    , Nop
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regD (Ind (regD))
    , Jump (Abs (35))
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
    --Block
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
    , Jump (Abs (141))
    , Load (ImmValue (61)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regE
    , Push regE
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regF
    , Push regF
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regG
    , Push regG
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regH
    , Push regH
    , Load (ImmValue (4)) regA
    , Push regA
    , Nop
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regB
    , Push regB
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regC
    , Push regC
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regD
    , Push regD
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regE
    , Push regE
    , Load (ImmValue (4)) regF
    , Push regF
    , Compute Add regHeap reg0 regC
    , Pop regB
    , Store regB (IndAddr (regC))
    , Compute Incr regC regC regC
    , Branch regB (Rel (2))
    , Jump (Abs (107))
    , Pop regG
    , Store regG (IndAddr (regC))
    , Compute Incr regC regC regC
    , Compute Decr regB regB regB
    , Jump (Abs (100))
    , Nop
    , Compute Add regHeap reg0 regC
    , Load (IndAddr (regC)) regB
    , Compute Incr regC regC regC
    , Pop regG
    , Compute NEq regB regG regB
    , Branch regB (Abs (124))
    , Load (ImmValue (1)) regH
    , Branch regG (Rel (2))
    , Jump (Abs (131))
    , Pop regB
    , Load (IndAddr (regC)) regA
    , Compute Equal regB regA regA
    , Compute And regH regA regH
    , Compute Incr regC regC regC
    , Compute Decr regG regG regG
    , Jump (Abs (115))
    , Nop
    , Load (ImmValue (0)) regH
    , Branch regG (Rel (2))
    , Jump (Abs (131))
    , Pop regB
    , Compute Decr regG regG regG
    , Jump (Abs (126))
    , Nop
    , Push regH
    , Pop regG
    , Load (ImmValue (1)) regH
    , Compute Xor regH regG regB
    , Push regB
    --Print: Boolean
    , Pop regD
    , WriteInstr regD numberIO
    --Block - end
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
    , Branch regG (Abs (153))
    , Branch regH (Abs (153))
    , Jump (Abs (153))
    , Nop
    --Block - end
    , Branch regH (Abs (157))
    , Branch regG (Abs (163))
    , Jump (Abs (169))
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
