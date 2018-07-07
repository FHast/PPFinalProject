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
    , Jump (Abs 177)
    , Load (ImmValue (59)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    --Num - 5
    , Load (ImmValue (5)) regE
    , Push regE
    --VarDef - i
    , Load (ImmValue (0)) regF
    , Compute Add regF regArp regF
    , Pop regG
    , Store regG (IndAddr (regF))
    , Nop
    --While
    --While - condition
    , Nop
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regB
    , Load (ImmValue (0)) regA
    , Compute Add regA regB regA
    , Load (IndAddr (regA)) regA
    , Push regA
    , Nop
    --Num - 0
    , Load (ImmValue (0)) regD
    , Push regD
    , Pop regE
    , Pop regF
    , Compute Gt regF regE regG
    , Push regG
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 174)
    , Nop
    , Nop
    , Branch regHeap (Rel 2)
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
    , Jump (Rel 2)
    , Jump (Abs 142)
    , Load (ImmValue (103)) regH
    , WriteInstr regH (IndAddr (regSprID))
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regH
    , Compute Decr regH regH regH
    , Load (IndAddr (regH)) regH
    , Load (ImmValue (0)) regG
    , Compute Add regG regH regG
    , Load (IndAddr (regG)) regG
    , Push regG
    --Print: int
    , Pop regB
    , WriteInstr regB numberIO
    , Nop
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regD
    , Compute Decr regD regD regD
    , Load (IndAddr (regD)) regD
    , Load (ImmValue (0)) regC
    , Compute Add regC regD regC
    , Load (IndAddr (regC)) regC
    , Push regC
    , Nop
    --Num - 1
    , Load (ImmValue (1)) regF
    , Push regF
    , Pop regG
    , Pop regH
    , Compute Sub regH regG regA
    , Push regA
    , Nop
    --ID Target
    , Compute Add regArp reg0 regC
    , Compute Decr regC regC regC
    , Load (IndAddr (regC)) regC
    , Load (ImmValue (0)) regB
    , Compute Add regC regB regC
    , Pop regB
    , Store regB (IndAddr (regC))
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
    , Branch regA (Abs 0)
    , Branch regB (Abs 154)
    , Jump (Abs 154)
    , Jump (Abs 172)
    , Pop regA
    , Pop regB
    --Block - end
    , Branch regB (Abs 160)
    , Branch regA (Abs 166)
    , Jump (Abs 172)
    , Nop
    , Push regB
    , Push reg0
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind regH)
    , Nop
    , Push regB
    , Push regA
    , ReadInstr (IndAddr (regSprID))
    , Receive regH
    , Jump (Ind regH)
    , Nop
    , Jump (Abs 70)
    , Nop
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
    , Branch regH (Abs 189)
    , Jump (Abs 189)
    , Jump (Abs 207)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 195)
    , Branch regG (Abs 201)
    , Jump (Abs 207)
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
