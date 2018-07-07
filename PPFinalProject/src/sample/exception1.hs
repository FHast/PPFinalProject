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
    , Jump (Abs 176)
    --FunDef plus4 - body
    , Nop
    , Nop
    , Pop regB
    , Load (ImmValue (1)) regC
    , Compute Add regC regArp regC
    , Store regB (IndAddr (regC))
    , Nop
    --If - condition
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (1)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Nop
    --Num - 0
    , Load (ImmValue (0)) regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Lt regD regC regE
    , Push regE
    , Pop regE
    --If
    , Load (ImmValue (1)) regF
    , Compute Xor regE regF regE
    , Branch regE (Abs 128)
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (8)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Load (ImmValue (8)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 98)
    , Load (ImmValue (86)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    --Fail
    , Load (ImmValue (1)) regE
    , Push reg0
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Jump (Ind regE)
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
    , Branch regG (Abs 111)
    , Branch regH (Abs 110)
    , Jump (Abs 110)
    , Jump (Abs 127)
    , Nop
    --Block - end
    , Branch regH (Abs 115)
    , Branch regG (Abs 121)
    , Jump (Abs 127)
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
    , Nop
    , Nop
    , Nop
    , Nop
    --Load ID - x
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
    --Return
    , Load (ImmValue (1)) regE
    , Push regE
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Jump (Ind regE)
    , Push reg0
    , Push reg0
    , Nop
    --FunDef - end plus4
    , Pop regG
    , Pop regH
    , Branch regH (Abs 160)
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
    , Pop regG
    , Store regG (IndAddr (regF))
    , Pop regA
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Load (IndAddr (regF)) regG
    , Push regG
    , Push regH
    , Push regG
    , Jump (Ind regA)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
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
    , Jump (Rel 2)
    , Jump (Abs 306)
    , Load (ImmValue (192)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regH
    , Push regH
    --VarDef - x
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Nop
    --Function - call plus4
    , Load (ImmValue (3)) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr (regC))
    , Compute Decr regC regC regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Store regD (IndAddr (regC))
    , Jump (Rel 2)
    , Jump (Abs 230)
    , Load (ImmValue (213)) regC
    , Push regC
    , Jump (Rel 2)
    , Jump (Abs 230)
    , Load (ImmValue (217)) regC
    , WriteInstr regC (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    , Load (ImmValue (3)) regC
    , Compute Add regC regArp regArp
    , Jump (Abs 46)
    --Function - return plus4
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regE
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Branch regE (Ind regD)
    , Pop regE
    , Pop regF
    , Branch regF (Rel 2)
    , Jump (Abs 1)
    --Print: int
    , Pop regB
    , WriteInstr regB numberIO
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue (-1)) regC
    , Push regC
    , Nop
    --ID Target
    , Compute Add regArp reg0 regE
    , Load (ImmValue (0)) regD
    , Compute Add regE regD regE
    , Pop regD
    , Store regD (IndAddr (regE))
    , Nop
    , Nop
    --Function - call plus4
    , Load (ImmValue (3)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regF))
    , Jump (Rel 2)
    , Jump (Abs 286)
    , Load (ImmValue (269)) regF
    , Push regF
    , Jump (Rel 2)
    , Jump (Abs 286)
    , Load (ImmValue (273)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Push regB
    , Load (ImmValue (3)) regF
    , Compute Add regF regArp regArp
    , Jump (Abs 46)
    --Function - return plus4
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regH
    , Push regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Branch regH (Ind regG)
    , Pop regH
    , Pop regA
    , Branch regA (Rel 2)
    , Jump (Abs 1)
    --Print: int
    , Pop regE
    , WriteInstr regE numberIO
    --Block - end
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
    , Branch regB (Abs 318)
    , Branch regC (Abs 370)
    , Jump (Abs 370)
    --Block - catch
    , Nop
    , Load (ImmValue (0)) regB
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Store regF (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regC (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regB (IndAddr (regA))
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 364)
    , Load (ImmValue (335)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    --Str - "failed"
    , Load (ImmValue (100)) regF
    , Push regF
    , Load (ImmValue (101)) regF
    , Push regF
    , Load (ImmValue (108)) regF
    , Push regF
    , Load (ImmValue (105)) regF
    , Push regF
    , Load (ImmValue (97)) regF
    , Push regF
    , Load (ImmValue (102)) regF
    , Push regF
    , Load (ImmValue (6)) regF
    , Push regF
    --Print: Array of 'Character'
    , Pop regG
    , Branch regG (Rel 2)
    , Jump (Abs 361)
    , Pop regH
    , WriteInstr regH charIO
    , Compute Decr regG regG regG
    , Jump (Abs 355)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    --Block - catch end
    , Pop regB
    , Pop regC
    , Jump (Abs 370)
    , Nop
    , Branch regC (Rel 2)
    , Jump (Abs 427)
    --Block - ret value backup
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Pop regE
    , Store regE (IndAddr (regA))
    , Load (ImmValue (0)) regC
    --Block - finally
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Store regF (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regC (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regB (IndAddr (regA))
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 415)
    , Load (ImmValue (393)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    --Print: int
    , Pop regD
    , WriteInstr regD numberIO
    , Compute Decr regArp regArp regG
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regC
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regB
    , Push regC
    , Push regB
    --Block - finally end
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regB
    , Pop regC
    , Branch regC (Abs 470)
    --Block - retrieve backup
    , Load (ImmValue (0)) regA
    , Compute Add regA regArp regA
    , Load (IndAddr (regA)) regE
    , Push regE
    , Load (ImmValue (1)) regC
    , Jump (Abs 470)
    , Nop
    --Block - finally
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regA
    , Compute Decr regA regA regA
    , Store regArp (IndAddr (regA))
    , Compute Decr regA regA regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regF
    , Store regF (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regC (IndAddr (regA))
    , Compute Decr regA regA regA
    , Store regB (IndAddr (regA))
    , Load (ImmValue (5)) regA
    , Compute Add regA regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 465)
    , Load (ImmValue (443)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    --Print: int
    , Pop regH
    , WriteInstr regH numberIO
    , Compute Decr regArp regArp regG
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regC
    , Compute Decr regG regG regG
    , Load (IndAddr (regG)) regB
    , Push regC
    , Push regB
    --Block - finally end
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regB
    , Pop regC
    , Nop
    , Nop
    --Block - end
    , Branch regC (Abs 475)
    , Branch regB (Abs 481)
    , Jump (Abs 487)
    , Nop
    , Push regC
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind regA)
    , Nop
    , Push regC
    , Push regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Jump (Ind regA)
    , Nop
    , EndProg
    ]
main = run [prog]
