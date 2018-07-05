import Sprockell

prog :: [Instruction]
prog = [
      Jump (Abs 30)
    , Load (ImmValue 82) regA
    , WriteInstr regA charIO
    , Load (ImmValue 117) regA
    , WriteInstr regA charIO
    , Load (ImmValue 110) regA
    , WriteInstr regA charIO
    , Load (ImmValue 116) regA
    , WriteInstr regA charIO
    , Load (ImmValue 105) regA
    , WriteInstr regA charIO
    , Load (ImmValue 109) regA
    , WriteInstr regA charIO
    , Load (ImmValue 101) regA
    , WriteInstr regA charIO
    , Load (ImmValue 32) regA
    , WriteInstr regA charIO
    , Load (ImmValue 101) regA
    , WriteInstr regA charIO
    , Load (ImmValue 114) regA
    , WriteInstr regA charIO
    , Load (ImmValue 114) regA
    , WriteInstr regA charIO
    , Load (ImmValue 111) regA
    , WriteInstr regA charIO
    , Load (ImmValue 114) regA
    , WriteInstr regA charIO
    , Load (ImmValue 33) regA
    , WriteInstr regA charIO
    , EndProg
    , Nop
    , Branch regSprID (Abs 33)
    , Jump (Abs 38)
    , Nop
    , ReadInstr (IndAddr regSprID)
    , Receive regD
    , Branch regD (Ind regD)
    , Jump (Abs 33)
    , Nop
    , Load (ImmValue 3) regE
    , WriteInstr regE (DirAddr 1)
    , Load (ImmValue 5000) regE
    , WriteInstr regE (DirAddr 2)
    , Load (ImmValue 5000) regHeap
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue 5000) regHeap
    , Nop
    --Block
    , Load (ImmValue 2) regF
    , Compute Add regF regArp regF
    , Compute Decr regF regF regF
    , Store regArp (IndAddr regF)
    , Compute Incr regF regF regArp
    --VarDef - i
    , Load (ImmValue 0) regA
    , Compute Add regA regArp regA
    , Store reg0 (IndAddr regA)
    , Nop
    --While - condition
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regC
    , Load (ImmValue 0) regB
    , Compute Add regB regC regB
    , Load (IndAddr regB) regB
    , Push regB
    , Nop
    --Num - 10
    , Load (ImmValue 10) regE
    , Push regE
    , Pop regF
    , Pop regG
    , Compute Lt regG regF regH
    , Push regH
    --While
    , Load (ImmValue 1) regB
    , Pop regA
    , Compute Xor regA regB regA
    , Branch regA (Abs 125)
    , Nop
    , Branch regHeap (Rel 2)
    , Load (ImmValue 5000) regHeap
    , Nop
    --Block
    , Load (ImmValue 3) regC
    , Compute Add regC regArp regC
    , Compute Decr regC regC regC
    , Store regArp (IndAddr regC)
    , Compute Incr regC regC regArp
    , Nop
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regG
    , Compute Decr regG regG regG
    , Load (IndAddr regG) regG
    , Load (ImmValue 0) regF
    , Compute Add regF regG regF
    , Load (IndAddr regF) regF
    , Push regF
    , Nop
    --Num - 1
    , Load (ImmValue 1) regA
    , Push regA
    , Pop regB
    , Pop regC
    , Compute Add regC regB regD
    , Push regD
    , Nop
    --ID Target
    , Compute Add regArp reg0 regF
    , Compute Decr regF regF regF
    , Load (IndAddr regF) regF
    , Load (ImmValue 0) regE
    , Compute Add regF regE regF
    , Load (IndAddr regF) regF
    , Pop regE
    , Store regE (IndAddr regF)
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr regArp) regArp
    , Pop regD
    , Pop regE
    , Branch regD (Abs 0)
    , Branch regE (Abs 118)
    , Jump (Abs 118)
    , Nop
    , Nop
    , Push regE
    , Push regD
    , Jump (Abs 136)
    , Nop
    , Jump (Abs 56)
    , Nop
    , Nop
    --Load ID - i
    , Compute Add regArp reg0 regH
    , Load (ImmValue 0) regG
    , Compute Add regG regH regG
    , Load (IndAddr regG) regG
    , Push regG
    --Print: int
    , Pop regB
    , WriteInstr regB numberIO
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr regArp) regArp
    , Pop regG
    , Pop regH
    , Branch regG (Abs 0)
    , Branch regH (Abs 144)
    , Jump (Abs 144)
    , Nop
    , Nop
    , Push regH
    , Push regG
    , Nop
    , EndProg
    ]
main = run [prog]
