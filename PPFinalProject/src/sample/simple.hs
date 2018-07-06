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
    , Jump (Abs 136)
    --FunDef test - body
    , Nop
    , Nop
    , Load (ImmValue (1)) regC
    , Compute Add regC regArp regC
    , Store regHeap (IndAddr (regC))
    , Pop regD
    , Store regD (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Branch regD (Rel 2)
    , Jump (Abs 61)
    , Pop regB
    , Store regB (IndAddr (regHeap))
    , Compute Incr regHeap regHeap regHeap
    , Compute Decr regD regD regD
    , Jump (Abs 54)
    , Nop
    , Nop
    , Nop
    --Load ID - val
    , Compute Add regArp reg0 regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Load (IndAddr (regE)) regH
    , Compute Add regE regH regE
    , Compute Add regH reg0 regF
    , Branch regH (Rel 2)
    , Jump (Abs 78)
    , Load (IndAddr (regE)) regG
    , Push regG
    , Compute Decr regE regE regE
    , Compute Decr regH regH regH
    , Jump (Abs 71)
    , Push regF
    --Print: Array of 'int'
    , Pop regA
    , Branch regA (Rel 2)
    , Jump (Abs 86)
    , Pop regB
    , WriteInstr regB numberIO
    , Compute Decr regA regA regA
    , Jump (Abs 80)
    , Nop
    , Nop
    , Nop
    --Load ID - val
    , Compute Add regArp reg0 regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Load (IndAddr (regC)) regF
    , Compute Add regC regF regC
    , Compute Add regF reg0 regD
    , Branch regF (Rel 2)
    , Jump (Abs 103)
    , Load (IndAddr (regC)) regE
    , Push regE
    , Compute Decr regC regC regC
    , Compute Decr regF regF regF
    , Jump (Abs 96)
    , Push regD
    --Return
    , Load (ImmValue (1)) regG
    , Push regG
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Jump (Ind regG)
    , Push reg0
    , Push reg0
    , Nop
    --FunDef - end test
    , Pop regG
    , Pop regH
    , Branch regH (Abs 120)
    , Pop regA
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Branch regH (Rel 2)
    , Jump (Abs 1)
    , Nop
    --FunDef - ret value backup
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Pop regA
    , Store regA (IndAddr (regF))
    , Pop regA
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Load (IndAddr (regF)) regA
    , Push regA
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
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
    , Jump (Rel 2)
    , Jump (Abs 254)
    , Load (ImmValue (152)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 6
    , Load (ImmValue (6)) regB
    , Push regB
    , Nop
    --Num - 5
    , Load (ImmValue (5)) regC
    , Push regC
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regD
    , Push regD
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regE
    , Push regE
    , Load (ImmValue (4)) regF
    , Push regF
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regG
    , Load (ImmValue (1)) regH
    , Compute Add regArp regH regH
    , Store regHeap (IndAddr (regH))
    , Pop regA
    , Compute Lt regA reg0 regH
    , Branch regH (Abs 1)
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regA regHeap
    --ArrayDef - store data
    , Store regA (IndAddr (regG))
    , Compute Incr regG regG regG
    , Branch regA (Rel 2)
    , Jump (Abs 189)
    , Pop regH
    , Store regH (IndAddr (regG))
    , Compute Incr regG regG regG
    , Compute Decr regA regA regA
    , Jump (Abs 182)
    , Nop
    , Nop
    , Nop
    --Function - call test
    , Load (ImmValue (4)) regB
    , Compute Add regB regArp regB
    , Compute Decr regB regB regB
    , Store regArp (IndAddr (regB))
    , Compute Decr regB regB regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regB))
    , Jump (Rel 2)
    , Jump (Abs 228)
    , Load (ImmValue (201)) regB
    , Push regB
    , Jump (Rel 2)
    , Jump (Abs 228)
    , Load (ImmValue (205)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Load (IndAddr (regF)) regA
    , Compute Add regF regA regF
    , Compute Add regA reg0 regG
    , Branch regA (Rel 2)
    , Jump (Abs 224)
    , Load (IndAddr (regF)) regH
    , Push regH
    , Compute Decr regF regF regF
    , Compute Decr regA regA regA
    , Jump (Abs 217)
    , Push regG
    , Load (ImmValue (4)) regB
    , Compute Add regB regArp regArp
    , Jump (Abs 46)
    --Function - return test
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regD
    , Push regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Branch regD (Ind regC)
    , Pop regD
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 1)
    --Print: Array of 'int'
    , Pop regB
    , Branch regB (Rel 2)
    , Jump (Abs 251)
    , Pop regC
    , WriteInstr regC numberIO
    , Compute Decr regB regB regB
    , Jump (Abs 245)
    , Nop
    --Block - end
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
    , Branch regD (Abs 266)
    , Branch regE (Abs 314)
    , Jump (Abs 314)
    --Block - catch
    , Nop
    , Load (ImmValue (0)) regD
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Store regH (IndAddr (regC))
    , Compute Decr regC regC regC
    , Store regE (IndAddr (regC))
    , Compute Decr regC regC regC
    , Store regD (IndAddr (regC))
    , Load (ImmValue (5)) regC
    , Compute Add regC regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 308)
    , Load (ImmValue (283)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    --Str - "fail"
    , Load (ImmValue (108)) regD
    , Push regD
    , Load (ImmValue (105)) regD
    , Push regD
    , Load (ImmValue (97)) regD
    , Push regD
    , Load (ImmValue (102)) regD
    , Push regD
    , Load (ImmValue (4)) regD
    , Push regD
    --Print: Array of 'Character'
    , Pop regE
    , Branch regE (Rel 2)
    , Jump (Abs 305)
    , Pop regF
    , WriteInstr regF charIO
    , Compute Decr regE regE regE
    , Jump (Abs 299)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    --Block - catch end
    , Pop regD
    , Pop regE
    , Jump (Abs 314)
    , Jump (Abs 332)
    , Pop regD
    , Pop regE
    --Block - end
    , Branch regE (Abs 320)
    , Branch regD (Abs 326)
    , Jump (Abs 332)
    , Nop
    , Push regE
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind regC)
    , Nop
    , Push regE
    , Push regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Jump (Ind regC)
    , Nop
    , EndProg
    ]
main = run [prog]
