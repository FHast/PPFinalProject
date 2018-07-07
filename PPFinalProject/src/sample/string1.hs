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
    --Str - "Test"
    , Load (ImmValue (116)) regF
    , Push regF
    , Load (ImmValue (115)) regF
    , Push regF
    , Load (ImmValue (101)) regF
    , Push regF
    , Load (ImmValue (84)) regF
    , Push regF
    , Load (ImmValue (4)) regF
    , Push regF
    --ArrayDef - allocate
    , ReadInstr (DirAddr (2))
    , Receive regB
    , ReadInstr (DirAddr (1))
    , Receive regC
    , Compute Add regB reg0 regG
    , Load (ImmValue (0)) regH
    , Compute Add regC regH regH
    , WriteInstr regG (IndAddr (regH))
    , Pop regA
    , Compute Lt regA reg0 regH
    , Branch regH (Abs 1)
    , Compute Incr regB regB regB
    , Compute Add regB regA regB
    , WriteInstr regB (DirAddr (2))
    --ArrayDef - store data
    , WriteInstr regA (IndAddr (regG))
    , Compute Incr regG regG regG
    , Branch regA (Rel 2)
    , Jump (Abs 79)
    , Pop regH
    , WriteInstr regH (IndAddr (regG))
    , Compute Incr regG regG regG
    , Compute Decr regA regA regA
    , Jump (Abs 72)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
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
    , Jump (Rel 2)
    , Jump (Abs 236)
    , Load (ImmValue (95)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    --Load ID - str
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
    , Jump (Abs 117)
    , ReadInstr (IndAddr (regC))
    , Receive regE
    , Push regE
    , Compute Decr regC regC regC
    , Compute Decr regG regG regG
    , Jump (Abs 109)
    , Push regD
    --Print: Array of 'Character'
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 125)
    , Pop regA
    , WriteInstr regA charIO
    , Compute Decr regH regH regH
    , Jump (Abs 119)
    , Nop
    , Nop
    --char - 105
    , Load (ImmValue (105)) regB
    , Push regB
    , Nop
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regC
    , Push regC
    , ReadInstr (DirAddr (1))
    , Receive regH
    , Load (ImmValue (0)) regD
    , Compute Add regD regH regD
    , ReadInstr (IndAddr (regD))
    , Receive regD
    , ReadInstr (IndAddr (regD))
    , Receive regG
    , Pop regE
    , Compute GtE regE regG regG
    , Branch regG (Abs 1)
    , Compute Lt regE reg0 regG
    , Branch regG (Abs 1)
    , Compute Add regD regE regD
    , Compute Incr regD regD regD
    , Pop regF
    , WriteInstr regF (IndAddr (regD))
    , Nop
    --char - 108
    , Load (ImmValue (108)) regA
    , Push regA
    , Nop
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regB
    , Push regB
    , ReadInstr (DirAddr (1))
    , Receive regG
    , Load (ImmValue (0)) regC
    , Compute Add regC regG regC
    , ReadInstr (IndAddr (regC))
    , Receive regC
    , ReadInstr (IndAddr (regC))
    , Receive regF
    , Pop regD
    , Compute GtE regD regF regF
    , Branch regF (Abs 1)
    , Compute Lt regD reg0 regF
    , Branch regF (Abs 1)
    , Compute Add regC regD regC
    , Compute Incr regC regC regC
    , Pop regE
    , WriteInstr regE (IndAddr (regC))
    , Nop
    --Load ID - str
    , ReadInstr (DirAddr (1))
    , Receive regC
    , Load (ImmValue (0)) regA
    , Compute Add regA regC regA
    , ReadInstr (IndAddr (regA))
    , Receive regH
    , ReadInstr (IndAddr (regH))
    , Receive regD
    , Compute Add regD reg0 regA
    , Compute Add regD regH regH
    , Branch regD (Rel 2)
    , Jump (Abs 193)
    , ReadInstr (IndAddr (regH))
    , Receive regB
    , Push regB
    , Compute Decr regH regH regH
    , Compute Decr regD regD regD
    , Jump (Abs 185)
    , Push regA
    --Print: Array of 'Character'
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 201)
    , Pop regF
    , WriteInstr regF charIO
    , Compute Decr regE regE regE
    , Jump (Abs 195)
    , Nop
    , Nop
    , Nop
    --Load ID - str
    , ReadInstr (DirAddr (1))
    , Receive regB
    , Load (ImmValue (0)) regH
    , Compute Add regH regB regH
    , ReadInstr (IndAddr (regH))
    , Receive regG
    , ReadInstr (IndAddr (regG))
    , Receive regC
    , Compute Add regC reg0 regH
    , Compute Add regC regG regG
    , Branch regC (Rel 2)
    , Jump (Abs 222)
    , ReadInstr (IndAddr (regG))
    , Receive regA
    , Push regA
    , Compute Decr regG regG regG
    , Compute Decr regC regC regC
    , Jump (Abs 214)
    , Push regH
    , Pop regD
    , Compute Add reg0 reg0 regE
    , Compute GtE regE regD regF
    , Branch regF (Abs 230)
    , Pop regF
    , Compute Incr regE regE regE
    , Jump (Abs 225)
    , Nop
    , Push regD
    --Print: int
    , Pop regG
    , WriteInstr regG numberIO
    --Block - end
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
    , Branch regE (Abs 0)
    , Branch regF (Abs 248)
    , Jump (Abs 248)
    , Jump (Abs 266)
    , Pop regE
    , Pop regF
    --Block - end
    , Branch regF (Abs 254)
    , Branch regE (Abs 260)
    , Jump (Abs 266)
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind regD)
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind regD)
    , Nop
    , EndProg
    ]
main = run [prog]
