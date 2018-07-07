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
    , Receive regH
    , Branch regH (Ind regH)
    , Jump (Abs 33)
    , Nop
    , Load (ImmValue (5)) regA
    , WriteInstr regA (DirAddr (3))
    , Load (ImmValue (5000)) regA
    , WriteInstr regA (DirAddr (4))
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Num - 10
    , Load (ImmValue (10)) regB
    , Push regB
    --VarDef - x
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Pop regE
    , WriteInstr regE (IndAddr (regC))
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
    , Jump (Abs 261)
    , Load (ImmValue (68)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Jump (Rel 2)
    , Jump (Abs 77)
    , Load (ImmValue (73)) regE
    , WriteInstr regE (DirAddr (1))
    , Jump (Abs 154)
    --Thread - 1
    , Nop
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
    , Load (ImmValue (93)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (0)) regF
    , Compute Add regF regH regF
    , ReadInstr (IndAddr (regF))
    , Receive regE
    , Push regE
    , Nop
    --Num - 200
    , Load (ImmValue (200)) regA
    , Push regA
    , Pop regB
    , Pop regC
    , Compute Add regC regB regD
    , Push regD
    , Nop
    --ID Target
    , ReadInstr (DirAddr (3))
    , Receive regG
    , Load (ImmValue (0)) regF
    , Compute Add regF regG regF
    , Pop regE
    , WriteInstr regE (IndAddr (regF))
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    --Main continue
    , Nop
    , Nop
    , Jump (Rel 2)
    , Jump (Abs 161)
    , Load (ImmValue (157)) regH
    , WriteInstr regH (DirAddr (2))
    , Jump (Abs 238)
    --Thread - 2
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
    , Jump (Abs 205)
    , Load (ImmValue (177)) regA
    , WriteInstr regA (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr (3))
    , Receive regC
    , Load (ImmValue (0)) regA
    , Compute Add regA regC regA
    , ReadInstr (IndAddr (regA))
    , Receive regH
    , Push regH
    , Nop
    --Num - 100
    , Load (ImmValue (100)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Sub regF regE regG
    , Push regG
    , Nop
    --ID Target
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Pop regH
    , WriteInstr regH (IndAddr (regA))
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
    , Branch regB (Abs 0)
    , Branch regC (Abs 217)
    , Jump (Abs 217)
    , Jump (Abs 235)
    , Pop regB
    , Pop regC
    --Block - end
    , Branch regC (Abs 223)
    , Branch regB (Abs 229)
    , Jump (Abs 235)
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
    , WriteInstr reg0 (IndAddr (regSprID))
    , EndProg
    --Main continue
    , Nop
    , Nop
    --Join - 1
    , ReadInstr (DirAddr (1))
    , Receive regC
    , Compute NEq regC reg0 regC
    , Branch regC (Abs 240)
    , Nop
    --Join - 2
    , ReadInstr (DirAddr (2))
    , Receive regD
    , Compute NEq regD reg0 regD
    , Branch regD (Abs 245)
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr (3))
    , Receive regH
    , Load (ImmValue (0)) regF
    , Compute Add regF regH regF
    , ReadInstr (IndAddr (regF))
    , Receive regE
    , Push regE
    --Print: int
    , Pop regA
    , WriteInstr regA numberIO
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
    , Branch regH (Abs 273)
    , Jump (Abs 273)
    , Jump (Abs 291)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 279)
    , Branch regG (Abs 285)
    , Jump (Abs 291)
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
main = run [prog,prog,prog]
