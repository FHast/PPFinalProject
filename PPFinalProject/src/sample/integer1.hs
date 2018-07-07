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
    , Jump (Abs 132)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    --Num - 10
    , Load (ImmValue (10)) regE
    , Push regE
    --VarDef - x
    , Load (ImmValue (0)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    --Num - 2
    , Load (ImmValue (2)) regH
    , Push regH
    --VarDef - y
    , Load (ImmValue (1)) regA
    , Compute Add regA regArp regA
    , Pop regB
    , Store regB (IndAddr (regA))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    --Load ID - y
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Pop regA
    , Pop regB
    , Compute Add regB regA regC
    , Push regC
    --Print: int
    , Pop regD
    , WriteInstr regD numberIO
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regF
    , Load (ImmValue (0)) regE
    , Compute Add regE regF regE
    , Load (IndAddr (regE)) regE
    , Push regE
    --Load ID - y
    , Compute Add regArp reg0 regA
    , Load (ImmValue (1)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    , Pop regC
    , Pop regD
    , Compute Sub regD regC regE
    , Push regE
    --Print: int
    , Pop regF
    , WriteInstr regF numberIO
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    --Load ID - y
    , Compute Add regArp reg0 regC
    , Load (ImmValue (1)) regB
    , Compute Add regB regC regB
    , Load (IndAddr (regB)) regB
    , Push regB
    , Pop regE
    , Pop regF
    , Compute Mul regF regE regG
    , Push regG
    --Print: int
    , Pop regH
    , WriteInstr regH numberIO
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
    , Branch regH (Abs 144)
    , Jump (Abs 144)
    , Jump (Abs 162)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 150)
    , Branch regG (Abs 156)
    , Jump (Abs 162)
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
