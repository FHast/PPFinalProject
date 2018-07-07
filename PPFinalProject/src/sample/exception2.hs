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
    , Jump (Abs 108)
    --FunDef plus4 - body
    , Nop
    , Nop
    , Pop regB
    , Load (ImmValue (1)) regC
    , Compute Add regC regArp regC
    , Store regB (IndAddr (regC))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    --Print: int
    , Pop regH
    , WriteInstr regH numberIO
    , Nop
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regB
    , Load (ImmValue (1)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Add regF regE regG
    , Push regG
    --Return
    , Load (ImmValue (1)) regH
    , Push regH
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind regH)
    , Push reg0
    , Push reg0
    , Nop
    --FunDef - end plus4
    , Pop regG
    , Pop regH
    , Branch regH (Abs 92)
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
    , Pop regB
    , Store regB (IndAddr (regF))
    , Pop regA
    , Load (ImmValue (2)) regF
    , Compute Add regF regArp regF
    , Load (IndAddr (regF)) regB
    , Push regB
    , Push regH
    , Push regG
    , Jump (Ind regA)
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
    , Jump (Abs 220)
    , Load (ImmValue (124)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    --Num - 3
    , Load (ImmValue (3)) regC
    , Push regC
    --VarDef - x
    , Load (ImmValue (0)) regD
    , Compute Add regD regArp regD
    , Pop regE
    , Store regE (IndAddr (regD))
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
    , Jump (Abs 162)
    , Load (ImmValue (145)) regF
    , Push regF
    , Jump (Rel 2)
    , Jump (Abs 162)
    , Load (ImmValue (149)) regF
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
    , Nop
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regG
    , Load (ImmValue (0)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regA
    , Push regA
    , Pop regB
    , Pop regC
    , Compute Sub regC regB regD
    , Push regD
    , Nop
    --ID Target
    , Compute Add regArp reg0 regF
    , Load (ImmValue (0)) regE
    , Compute Add regF regE regF
    , Pop regE
    , Store regE (IndAddr (regF))
    , Nop
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (0)) regG
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
    --Print: Boolean
    , Pop regF
    , WriteInstr regF numberIO
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
    , Branch regE (Abs 232)
    , Branch regF (Abs 284)
    , Jump (Abs 284)
    --Block - catch
    , Nop
    , Load (ImmValue (0)) regE
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regF (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regE (IndAddr (regD))
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 278)
    , Load (ImmValue (249)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    --Str - "failed"
    , Load (ImmValue (100)) regG
    , Push regG
    , Load (ImmValue (101)) regG
    , Push regG
    , Load (ImmValue (108)) regG
    , Push regG
    , Load (ImmValue (105)) regG
    , Push regG
    , Load (ImmValue (97)) regG
    , Push regG
    , Load (ImmValue (102)) regG
    , Push regG
    , Load (ImmValue (6)) regG
    , Push regG
    --Print: Array of 'Character'
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 275)
    , Pop regA
    , WriteInstr regA charIO
    , Compute Decr regH regH regH
    , Jump (Abs 269)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    --Block - catch end
    , Pop regE
    , Pop regF
    , Jump (Abs 284)
    , Nop
    , Branch regF (Rel 2)
    , Jump (Abs 341)
    --Block - ret value backup
    , Load (ImmValue (0)) regD
    , Compute Add regD regArp regD
    , Pop regH
    , Store regH (IndAddr (regD))
    , Load (ImmValue (0)) regF
    --Block - finally
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regF (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regE (IndAddr (regD))
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 329)
    , Load (ImmValue (307)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regC
    , Load (ImmValue (0)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Push regB
    --Print: int
    , Pop regE
    , WriteInstr regE numberIO
    , Compute Decr regArp regArp regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regF
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regE
    , Push regF
    , Push regE
    --Block - finally end
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regE
    , Pop regF
    , Branch regF (Abs 384)
    --Block - retrieve backup
    , Load (ImmValue (0)) regD
    , Compute Add regD regArp regD
    , Load (IndAddr (regD)) regH
    , Push regH
    , Load (ImmValue (1)) regF
    , Jump (Abs 384)
    , Nop
    --Block - finally
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regF (IndAddr (regD))
    , Compute Decr regD regD regD
    , Store regE (IndAddr (regD))
    , Load (ImmValue (5)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel 2)
    , Jump (Abs 379)
    , Load (ImmValue (357)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regG
    , Load (ImmValue (0)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    --Print: int
    , Pop regA
    , WriteInstr regA numberIO
    , Compute Decr regArp regArp regB
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regF
    , Compute Decr regB regB regB
    , Load (IndAddr (regB)) regE
    , Push regF
    , Push regE
    --Block - finally end
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regE
    , Pop regF
    , Nop
    --Block - end
    , Branch regF (Abs 388)
    , Branch regE (Abs 394)
    , Jump (Abs 400)
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
