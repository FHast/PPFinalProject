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
    , Jump (Abs (109))
    , Load (ImmValue (61)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regE
    , Push regE
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regF
    , Load (ImmValue (0)) regG
    , Compute Add regArp regG regG
    , Store regHeap (IndAddr (regG))
    , Pop regH
    , Compute Lt regH reg0 regG
    , Branch regG (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regH regHeap
    --ArrayDef - store data
    , Store regH (IndAddr (regF))
    , Compute Incr regF regF regF
    , Branch regH (Rel (2))
    , Jump (Abs (85))
    , Store reg0 (IndAddr (regF))
    , Compute Incr regF regF regF
    , Compute Decr regH regH regH
    , Jump (Abs (79))
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regA
    , Push regA
    , Compute Add regArp reg0 regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regD
    , Load (IndAddr (regD)) regD
    , Load (IndAddr (regD)) regC
    , Pop regB
    , Compute LtE regC regB regE
    , Branch regE (Abs (1))
    , Compute Lt regB reg0 regE
    , Branch regE (Abs (1))
    , Compute Add regD regB regD
    , Compute Incr regD regD regD
    , Load (IndAddr (regD)) regC
    , Push regC
    --Print: int
    , Pop regF
    , WriteInstr regF numberIO
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
    , Branch regG (Abs (121))
    , Branch regH (Abs (121))
    , Jump (Abs (121))
    , Nop
    --Block - end
    , Branch regH (Abs (125))
    , Branch regG (Abs (131))
    , Jump (Abs (137))
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
