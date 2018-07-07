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
    , Jump (Rel 2)
    , Jump (Abs 121)
    , Load (ImmValue (59)) regF
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
    , Store regF (IndAddr (regG))
    , Pop regH
    , Compute Lt regH reg0 regG
    , Branch regG (Abs 1)
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regH regHeap
    --ArrayDef - store data
    , Store regH (IndAddr (regF))
    , Compute Incr regF regF regF
    , Branch regH (Rel 2)
    , Jump (Abs 83)
    , Store reg0 (IndAddr (regF))
    , Compute Incr regF regF regF
    , Compute Decr regH regH regH
    , Jump (Abs 77)
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regA
    , Push regA
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regB
    , Push regB
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regC
    , Push regC
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regD
    , Push regD
    , Load (ImmValue (4)) regE
    , Push regE
    , Nop
    --ID Target
    , Compute Add regArp reg0 regG
    , Load (ImmValue (0)) regF
    , Compute Add regG regF regG
    , Load (IndAddr (regG)) regG
    , Pop regH
    , Load (IndAddr (regG)) regF
    , Compute NEq regF regH regF
    , Branch regF (Abs 1)
    , Store regH (IndAddr (regG))
    , Compute Incr regG regG regG
    , Branch regH (Rel 2)
    , Jump (Abs 118)
    , Pop regF
    , Store regF (IndAddr (regG))
    , Compute Incr regG regG regG
    , Compute Decr regH regH regH
    , Jump (Abs 111)
    , Nop
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
    , Branch regG (Abs 0)
    , Branch regH (Abs 133)
    , Jump (Abs 133)
    , Jump (Abs 151)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 139)
    , Branch regG (Abs 145)
    , Jump (Abs 151)
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
