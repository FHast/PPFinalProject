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
    , Jump (Abs 291)
    , Load (ImmValue (68)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Jump (Rel 2)
    , Jump (Abs 77)
    , Load (ImmValue (73)) regE
    , WriteInstr regE (DirAddr (1))
    , Jump (Abs 169)
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
    , Jump (Abs 136)
    , Load (ImmValue (93)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , TestAndSet (IndAddr (regE))
    , Receive regG
    , Compute Equal regG reg0 regG
    , Branch regG (Abs 101)
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
    --Num - 200
    , Load (ImmValue (200)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Add regF regE regG
    , Push regG
    , Nop
    --ID Target
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Pop regH
    , WriteInstr regH (IndAddr (regA))
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , WriteInstr reg0 (IndAddr (regC))
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
    , Branch regH (Abs 148)
    , Jump (Abs 148)
    , Jump (Abs 166)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 154)
    , Branch regG (Abs 160)
    , Jump (Abs 166)
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
    , Jump (Abs 176)
    , Load (ImmValue (172)) regE
    , WriteInstr regE (DirAddr (2))
    , Jump (Abs 268)
    --Thread - 2
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
    , Jump (Abs 235)
    , Load (ImmValue (192)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regF
    , Load (ImmValue (1)) regE
    , Compute Add regE regF regE
    , TestAndSet (IndAddr (regE))
    , Receive regG
    , Compute Equal regG reg0 regG
    , Branch regG (Abs 200)
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
    , Nop
    , ReadInstr (DirAddr (3))
    , Receive regD
    , Load (ImmValue (1)) regC
    , Compute Add regC regD regC
    , WriteInstr reg0 (IndAddr (regC))
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
    , Branch regH (Abs 247)
    , Jump (Abs 247)
    , Jump (Abs 265)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 253)
    , Branch regG (Abs 259)
    , Jump (Abs 265)
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
    --Join - 1
    , ReadInstr (DirAddr (1))
    , Receive regE
    , Compute NEq regE reg0 regE
    , Branch regE (Abs 270)
    , Nop
    --Join - 2
    , ReadInstr (DirAddr (2))
    , Receive regF
    , Compute NEq regF reg0 regF
    , Branch regF (Abs 275)
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr (3))
    , Receive regB
    , Load (ImmValue (0)) regH
    , Compute Add regH regB regH
    , ReadInstr (IndAddr (regH))
    , Receive regG
    , Push regG
    --Print: int
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
    , Branch regH (Abs 303)
    , Jump (Abs 303)
    , Jump (Abs 321)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 309)
    , Branch regG (Abs 315)
    , Jump (Abs 321)
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
