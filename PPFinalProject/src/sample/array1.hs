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
    , Nop
    , Nop
    --char - false
    , Load (ImmValue (0)) regF
    , Push regF
    , Nop
    --boolean - true
    , Load (ImmValue (1)) regG
    , Push regG
    , Load (ImmValue (2)) regH
    , Push regH
    --ArrayDef - allocate
    , ReadInstr (DirAddr (2))
    , Receive regD
    , ReadInstr (DirAddr (1))
    , Receive regE
    , Compute Add regD reg0 regA
    , Load (ImmValue (0)) regB
    , Compute Add regE regB regB
    , WriteInstr regA (IndAddr (regB))
    , Pop regC
    , Compute Lt regC reg0 regB
    , Branch regB (Abs 1)
    , Compute Incr regD regD regD
    , Compute Add regD regC regD
    , WriteInstr regD (DirAddr (2))
    --ArrayDef - store data
    , WriteInstr regC (IndAddr (regA))
    , Compute Incr regA regA regA
    , Branch regC (Rel 2)
    , Jump (Abs 77)
    , Pop regB
    , WriteInstr regB (IndAddr (regA))
    , Compute Incr regA regA regA
    , Compute Decr regC regC regC
    , Jump (Abs 70)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (6)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Load (ImmValue (6)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 299)
    , Load (ImmValue (93)) regF
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
    , Jump (Abs 117)
    , Store reg0 (IndAddr (regF))
    , Compute Incr regF regF regF
    , Compute Decr regH regH regH
    , Jump (Abs 111)
    , Nop
    , Nop
    --Load ID - ints
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Load (IndAddr (regA)) regD
    , Compute Add regA regD regA
    , Compute Add regD reg0 regB
    , Branch regD (Rel 2)
    , Jump (Abs 133)
    , Load (IndAddr (regA)) regC
    , Push regC
    , Compute Decr regA regA regA
    , Compute Decr regD regD regD
    , Jump (Abs 126)
    , Push regB
    --Print: Array of 'int'
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 141)
    , Pop regF
    , WriteInstr regF numberIO
    , Compute Decr regE regE regE
    , Jump (Abs 135)
    , Nop
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regG
    , Push regG
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regB
    , Load (IndAddr (regB)) regB
    , Load (IndAddr (regB)) regA
    , Pop regH
    , Compute LtE regA regH regC
    , Branch regC (Abs 1)
    , Compute Lt regH reg0 regC
    , Branch regC (Abs 1)
    , Compute Add regB regH regB
    , Compute Incr regB regB regB
    , Load (IndAddr (regB)) regA
    , Push regA
    --VarDef - int
    , Load (ImmValue (1)) regD
    , Compute Add regD regArp regD
    , Pop regE
    , Store regE (IndAddr (regD))
    , Nop
    , Nop
    --Load ID - int
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regA
    , Push regA
    , Pop regB
    , Pop regC
    , Compute Add regC regB regD
    , Push regD
    --Print: int
    , Pop regE
    , WriteInstr regE numberIO
    , Nop
    , Nop
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
    , Load (ImmValue (3)) regA
    , Push regA
    , Nop
    --ID Target
    , Compute Add regArp reg0 regC
    , Load (ImmValue (0)) regB
    , Compute Add regC regB regC
    , Load (IndAddr (regC)) regC
    , Pop regD
    , Load (IndAddr (regC)) regB
    , Compute NEq regB regD regB
    , Branch regB (Abs 1)
    , Store regD (IndAddr (regC))
    , Compute Incr regC regC regC
    , Branch regD (Rel 2)
    , Jump (Abs 211)
    , Pop regB
    , Store regB (IndAddr (regC))
    , Compute Incr regC regC regC
    , Compute Decr regD regD regD
    , Jump (Abs 204)
    , Nop
    , Nop
    --Load ID - ints
    , Compute Add regArp reg0 regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Load (IndAddr (regE)) regH
    , Compute Add regE regH regE
    , Compute Add regH reg0 regF
    , Branch regH (Rel 2)
    , Jump (Abs 227)
    , Load (IndAddr (regE)) regG
    , Push regG
    , Compute Decr regE regE regE
    , Compute Decr regH regH regH
    , Jump (Abs 220)
    , Push regF
    --Print: Array of 'int'
    , Pop regA
    , Branch regA (Rel 2)
    , Jump (Abs 235)
    , Pop regB
    , WriteInstr regB numberIO
    , Compute Decr regA regA regA
    , Jump (Abs 229)
    , Nop
    , Nop
    --Load ID - bools
    , ReadInstr (DirAddr (1))
    , Receive regF
    , Load (ImmValue (0)) regD
    , Compute Add regD regF regD
    , ReadInstr (IndAddr (regD))
    , Receive regC
    , ReadInstr (IndAddr (regC))
    , Receive regG
    , Compute Add regG reg0 regD
    , Compute Add regG regC regC
    , Branch regG (Rel 2)
    , Jump (Abs 255)
    , ReadInstr (IndAddr (regC))
    , Receive regE
    , Push regE
    , Compute Decr regC regC regC
    , Compute Decr regG regG regG
    , Jump (Abs 247)
    , Push regD
    --Print: Array of 'Boolean'
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 263)
    , Pop regA
    , WriteInstr regA numberIO
    , Compute Decr regH regH regH
    , Jump (Abs 257)
    , Nop
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regB
    , Push regB
    , ReadInstr (DirAddr (1))
    , Receive regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , ReadInstr (IndAddr (regE))
    , Receive regE
    , ReadInstr (IndAddr (regE))
    , Receive regD
    , Pop regC
    , Compute GtE regC regD regD
    , Branch regD (Abs 1)
    , Compute Lt regC reg0 regD
    , Branch regD (Abs 1)
    , Compute Add regE regC regE
    , Compute Incr regE regE regE
    , ReadInstr (IndAddr (regE))
    , Receive regD
    , Push regD
    , Nop
    --boolean - true
    , Load (ImmValue (1)) regG
    , Push regG
    , Pop regH
    , Pop regA
    , Compute And regA regH regB
    , Push regB
    --Print: Boolean
    , Pop regC
    , WriteInstr regC numberIO
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
    , Branch regH (Abs 311)
    , Jump (Abs 311)
    , Jump (Abs 329)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 317)
    , Branch regG (Abs 323)
    , Jump (Abs 329)
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
