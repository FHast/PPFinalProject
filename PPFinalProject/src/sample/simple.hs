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
    , Jump (Abs (246))
    , Load (ImmValue (61)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    , Nop
    --Num - 8
    , Load (ImmValue (8)) regE
    , Push regE
    , Nop
    --Num - 7
    , Load (ImmValue (7)) regF
    , Push regF
    , Nop
    --Num - 6
    , Load (ImmValue (6)) regG
    , Push regG
    , Nop
    --Num - 5
    , Load (ImmValue (5)) regH
    , Push regH
    , Load (ImmValue (4)) regA
    , Push regA
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regB
    , Load (ImmValue (1)) regC
    , Compute Add regArp regC regC
    , Store regHeap (IndAddr (regC))
    , Pop regD
    , Compute Lt regD reg0 regC
    , Branch regC (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regD regHeap
    --ArrayDef - store data
    , Store regD (IndAddr (regB))
    , Compute Incr regB regB regB
    , Branch regD (Rel (2))
    , Jump (Abs (98))
    , Pop regC
    , Store regC (IndAddr (regB))
    , Compute Incr regB regB regB
    , Compute Decr regD regD regD
    , Jump (Abs (91))
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 10
    , Load (ImmValue (10)) regE
    , Push regE
    , Nop
    --Num - 9
    , Load (ImmValue (9)) regF
    , Push regF
    , Nop
    --Num - 8
    , Load (ImmValue (8)) regG
    , Push regG
    , Nop
    --Num - 7
    , Load (ImmValue (7)) regH
    , Push regH
    , Nop
    --Num - 6
    , Load (ImmValue (6)) regA
    , Push regA
    , Load (ImmValue (5)) regB
    , Push regB
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regC
    , Load (ImmValue (3)) regD
    , Compute Add regArp regD regD
    , Store regHeap (IndAddr (regD))
    , Pop regE
    , Compute Lt regE reg0 regD
    , Branch regD (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regE regHeap
    --ArrayDef - store data
    , Store regE (IndAddr (regC))
    , Compute Incr regC regC regC
    , Branch regE (Rel (2))
    , Jump (Abs (136))
    , Pop regD
    , Store regD (IndAddr (regC))
    , Compute Incr regC regC regC
    , Compute Decr regE regE regE
    , Jump (Abs (129))
    , Nop
    , Nop
    , Nop
    --Num - 4
    , Load (ImmValue (4)) regF
    , Push regF
    --ArrayDef - allocate
    , Compute Add regHeap reg0 regG
    , Load (ImmValue (4)) regH
    , Compute Add regArp regH regH
    , Store regHeap (IndAddr (regH))
    , Pop regA
    , Compute Lt regA reg0 regH
    , Branch regH (Abs (1))
    , Compute Incr regHeap regHeap regHeap
    , Compute Add regHeap regA regHeap
    --ArrayDef - store data
    , Store regA (IndAddr (regG))
    , Compute Incr regG regG regG
    , Branch regA (Rel (2))
    , Jump (Abs (158))
    , Store reg0 (IndAddr (regG))
    , Compute Incr regG regG regG
    , Compute Decr regA regA regA
    , Jump (Abs (152))
    , Nop
    , Nop
    , Nop
    --char - 97
    , Load (ImmValue (97)) regB
    , Push regB
    --char - 98
    , Load (ImmValue (98)) regC
    , Push regC
    , Pop regD
    , Pop regE
    , Compute Lt regE regD regG
    , Push regG
    --Print: Boolean
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
    , Load (IndAddr (regA)) regD
    , Compute Add regA regD regA
    , Compute Add regD reg0 regB
    , Branch regD (Rel (2))
    , Jump (Abs (188))
    , Load (IndAddr (regA)) regC
    , Push regC
    , Compute Decr regA regA regA
    , Compute Decr regD regD regD
    , Jump (Abs (181))
    , Push regB
    , Nop
    --Load ID - z
    , Compute Add regArp reg0 regF
    , Load (ImmValue (4)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Load (IndAddr (regE)) regH
    , Compute Add regE regH regE
    , Compute Add regH reg0 regF
    , Branch regH (Rel (2))
    , Jump (Abs (204))
    , Load (IndAddr (regE)) regG
    , Push regG
    , Compute Decr regE regE regE
    , Compute Decr regH regH regH
    , Jump (Abs (197))
    , Push regF
    , Compute Add regHeap reg0 regE
    , Pop regD
    , Store regD (IndAddr (regE))
    , Compute Incr regE regE regE
    , Branch regD (Rel (2))
    , Jump (Abs (216))
    , Pop regA
    , Store regA (IndAddr (regE))
    , Compute Incr regE regE regE
    , Compute Decr regD regD regD
    , Jump (Abs (209))
    , Nop
    , Compute Add regHeap reg0 regE
    , Load (IndAddr (regE)) regD
    , Compute Incr regE regE regE
    , Pop regA
    , Compute NEq regD regA regD
    , Branch regD (Abs (233))
    , Load (ImmValue (1)) regB
    , Branch regA (Rel (2))
    , Jump (Abs (240))
    , Pop regD
    , Load (IndAddr (regE)) regC
    , Compute Equal regD regC regC
    , Compute And regB regC regB
    , Compute Incr regE regE regE
    , Compute Decr regA regA regA
    , Jump (Abs (224))
    , Nop
    , Load (ImmValue (0)) regB
    , Branch regA (Rel (2))
    , Jump (Abs (240))
    , Pop regD
    , Compute Decr regA regA regA
    , Jump (Abs (235))
    , Nop
    , Push regB
    --Print: Boolean
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
    , Branch regG (Abs (258))
    , Branch regH (Abs (312))
    , Jump (Abs (312))
    --Block - catch
    , Nop
    , Load (ImmValue (0)) regG
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr (regF))
    , Compute Decr regF regF regF
    , ReadInstr (IndAddr (regSprID))
    , Receive regC
    , Store regC (IndAddr (regF))
    , Compute Decr regF regF regF
    , Store regH (IndAddr (regF))
    , Compute Decr regF regF regF
    , Store regG (IndAddr (regF))
    , Load (ImmValue (5)) regF
    , Compute Add regF regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (306))
    , Load (ImmValue (275)) regF
    , WriteInstr regF (IndAddr (regSprID))
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
    , Branch regH (Rel (2))
    , Jump (Abs (301))
    , Pop regA
    , WriteInstr regA charIO
    , Compute Decr regH regH regH
    , Jump (Abs (295))
    , Nop
    , Load (ImmValue (10)) regA
    , WriteInstr regA charIO
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    --Block - catch end
    , Pop regG
    , Pop regH
    , Jump (Abs (312))
    , Jump (Abs (329))
    , Nop
    --Block - end
    , Branch regH (Abs (317))
    , Branch regG (Abs (323))
    , Jump (Abs (329))
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
