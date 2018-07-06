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
    , Jump (Abs 152)
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
    --Load ID - x
    , Compute Add regArp reg0 regA
    , Load (ImmValue (0)) regH
    , Compute Add regH regA regH
    , Load (IndAddr (regH)) regH
    , Push regH
    --Print: int
    , Pop regC
    , WriteInstr regC numberIO
    , Nop
    , Branch regHeap (Rel 2)
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
    , Jump (Rel 2)
    , Jump (Abs 111)
    , Load (ImmValue (92)) regD
    , WriteInstr regD (IndAddr (regSprID))
    --char - 97
    , Load (ImmValue (97)) regC
    , Push regC
    --VarDef - x
    , Load (ImmValue (0)) regD
    , Compute Add regD regArp regD
    , Pop regE
    , Store regE (IndAddr (regD))
    , Nop
    --Load ID - x
    , Compute Add regArp reg0 regG
    , Load (ImmValue (0)) regF
    , Compute Add regF regG regF
    , Load (IndAddr (regF)) regF
    , Push regF
    --Print: Character
    , Pop regA
    , WriteInstr regA charIO
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
    , Branch regE (Abs 0)
    , Branch regF (Abs 123)
    , Jump (Abs 123)
    , Jump (Abs 141)
    , Pop regE
    , Pop regF
    --Block - end
    , Branch regF (Abs 129)
    , Branch regE (Abs 135)
    , Jump (Abs 141)
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
    , Branch regG (Abs 164)
    , Branch regH (Abs 212)
    , Jump (Abs 212)
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
    , Jump (Rel 2)
    , Jump (Abs 206)
    , Load (ImmValue (181)) regF
    , WriteInstr regF (IndAddr (regSprID))
    , Nop
    , Nop
    --Str - "fail"
    , Load (ImmValue (108)) regF
    , Push regF
    , Load (ImmValue (105)) regF
    , Push regF
    , Load (ImmValue (97)) regF
    , Push regF
    , Load (ImmValue (102)) regF
    , Push regF
    , Load (ImmValue (4)) regF
    , Push regF
    --Print: Array of 'Character'
    , Pop regG
    , Branch regG (Rel 2)
    , Jump (Abs 203)
    , Pop regH
    , WriteInstr regH charIO
    , Compute Decr regG regG regG
    , Jump (Abs 197)
    , Nop
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr (regArp)) regArp
    --Block - catch end
    , Pop regG
    , Pop regH
    , Jump (Abs 212)
    , Jump (Abs 230)
    , Pop regG
    , Pop regH
    --Block - end
    , Branch regH (Abs 218)
    , Branch regG (Abs 224)
    , Jump (Abs 230)
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
