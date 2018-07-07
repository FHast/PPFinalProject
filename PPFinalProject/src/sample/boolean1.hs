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
    , Jump (Abs 123)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    --VarDef - x
    , Load (ImmValue (0)) regE
    , Compute Add regE regArp regE
    , Store reg0 (IndAddr (regE))
    , Nop
    --boolean - true
    , Load (ImmValue (1)) regF
    , Push regF
    --VarDef - y
    , Load (ImmValue (1)) regG
    , Compute Add regG regArp regG
    , Pop regH
    , Store regH (IndAddr (regG))
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    --Load ID - y
    , Compute Add regArp reg0 regE
    , Load (ImmValue (1)) regD
    , Compute Add regD regE regD
    , Load (IndAddr (regD)) regD
    , Push regD
    , Pop regG
    , Pop regH
    , Compute Equal regH regG regA
    , Push regA
    --Print: Boolean
    , Pop regB
    , WriteInstr regB numberIO
    , Nop
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Nop
    --Load ID - y
    , Compute Add regArp reg0 regG
    , Load (ImmValue (1)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    , Pop regB
    , Load (ImmValue (1)) regA
    , Compute Xor regA regB regB
    , Push regB
    , Pop regC
    , Pop regD
    , Compute Equal regD regC regE
    , Push regE
    --Print: Boolean
    , Pop regF
    , WriteInstr regF numberIO
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    --Print: Boolean
    , Pop regB
    , WriteInstr regB numberIO
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
    , Branch regH (Abs 135)
    , Jump (Abs 135)
    , Jump (Abs 153)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 141)
    , Branch regG (Abs 147)
    , Jump (Abs 153)
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
