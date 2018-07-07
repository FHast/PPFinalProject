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
    --Num - 0
    , Load (ImmValue (0)) regF
    , Push regF
    --VarDef - x
    , ReadInstr (DirAddr (1))
    , Receive regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Pop regA
    , WriteInstr regA (IndAddr (regG))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (5)) regB
    , Compute Add regB regArp regB
    , Compute Decr regB regB regB
    , Store regArp (IndAddr (regB))
    , Compute Decr regB regB regB
    , ReadInstr (IndAddr (regSprID))
    , Receive regG
    , Store regG (IndAddr (regB))
    , Load (ImmValue (5)) regB
    , Compute Add regB regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (283))
    , Load (ImmValue (70)) regB
    , WriteInstr regB (IndAddr (regSprID))
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regA
    , Push regA
    --VarDef - x
    , Load (ImmValue (0)) regB
    , Compute Add regB regArp regB
    , Pop regC
    , Store regC (IndAddr (regB))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (240))
    , Load (ImmValue (95)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regD
    , Compute Decr regD regD regD
    , Load (IndAddr (regD)) regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    --VarDef - x
    , Load (ImmValue (0)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regH
    , Compute Decr regH regH regH
    , Store regArp (IndAddr (regH))
    , Compute Decr regH regH regH
    , ReadInstr (IndAddr (regSprID))
    , Receive regE
    , Store regE (IndAddr (regH))
    , Load (ImmValue (6)) regH
    , Compute Add regH regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (208))
    , Load (ImmValue (125)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Compute Decr regH regH regH
    , Load (IndAddr (regH)) regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    --VarDef - x
    , Load (ImmValue (0)) regB
    , Compute Add regB regArp regB
    , Pop regC
    , Store regC (IndAddr (regB))
    , Nop
    , Branch regHeap (Rel (2))
    , Load (ImmValue (5000)) regHeap
    , Nop
    --Block
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regD
    , Compute Decr regD regD regD
    , Store regArp (IndAddr (regD))
    , Compute Decr regD regD regD
    , ReadInstr (IndAddr (regSprID))
    , Receive regA
    , Store regA (IndAddr (regD))
    , Load (ImmValue (6)) regD
    , Compute Add regD regArp regArp
    , Jump (Rel (2))
    , Jump (Abs (176))
    , Load (ImmValue (155)) regD
    , WriteInstr regD (IndAddr (regSprID))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regD
    , Compute Decr regD regD regD
    , Load (IndAddr (regD)) regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Nop
    --ID Target
    , ReadInstr (DirAddr (1))
    , Receive regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Pop regF
    , WriteInstr regF (IndAddr (regG))
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
    , Branch regE (Abs (189))
    , Branch regF (Abs (188))
    , Jump (Abs (188))
    , Jump (Abs (205))
    , Nop
    --Block - end
    , Branch regF (Abs (193))
    , Branch regE (Abs (199))
    , Jump (Abs (205))
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regE
    , WriteInstr regE (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regA
    , Pop regB
    , Branch regA (Abs (221))
    , Branch regB (Abs (220))
    , Jump (Abs (220))
    , Jump (Abs (237))
    , Nop
    --Block - end
    , Branch regB (Abs (225))
    , Branch regA (Abs (231))
    , Jump (Abs (237))
    , Nop
    , Push regB
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind (regH))
    , Nop
    , Push regB
    , Push regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind (regH))
    , Nop
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
    , Branch regE (Abs (253))
    , Branch regF (Abs (252))
    , Jump (Abs (252))
    , Jump (Abs (269))
    , Nop
    --Block - end
    , Branch regF (Abs (257))
    , Branch regE (Abs (263))
    , Jump (Abs (269))
    , Nop
    , Push regF
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Push regF
    , Push regE
    , ReadInstr (IndAddr (regSprID))
    , Receive regD
    , Jump (Ind (regD))
    , Nop
    , Nop
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr (1))
    , Receive regD
    , Load (ImmValue (0)) regB
    , Compute Add regB regD regB
    , ReadInstr (IndAddr (regB))
    , Receive regA
    , Push regA
    --Print: int
    , Pop regE
    , WriteInstr regE numberIO
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regG
    , WriteInstr regG (IndAddr (regSprID))
    , Compute Incr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    , Pop regC
    , Pop regD
    , Branch regC (Abs (296))
    , Branch regD (Abs (295))
    , Jump (Abs (295))
    , Jump (Abs (312))
    , Nop
    --Block - end
    , Branch regD (Abs (300))
    , Branch regC (Abs (306))
    , Jump (Abs (312))
    , Nop
    , Push regD
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , Push regD
    , Push regC
    , ReadInstr (IndAddr (regSprID))
    , Receive regB
    , Jump (Ind (regB))
    , Nop
    , EndProg
    ]
main = run [prog]
