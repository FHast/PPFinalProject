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
    , Nop
    , Nop
    , Nop
    --Num - 2
    , Load (ImmValue 2) regA
    , Push regA
    , Nop
    --Num - 2
    , Load (ImmValue 2) regB
    , Push regB
    , Nop
    --Num - 1
    , Load (ImmValue 1) regC
    , Push regC
    , Load (ImmValue 3) regD
    , Push regD
    --ArrayDef - allocate
    , ReadInstr (DirAddr 2)
    , Receive regH
    , ReadInstr (DirAddr 1)
    , Receive regA
    , Compute Add regH reg0 regE
    , Load (ImmValue 0) regF
    , Compute Add regA regF regF
    , WriteInstr regE (IndAddr regF)
    , Pop regG
    , Compute Incr regH regH regH
    , Compute Add regH regG regH
    , WriteInstr regH (DirAddr 2)
    --ArrayDef - store data
    , WriteInstr regG (IndAddr regE)
    , Compute Incr regE regE regE
    , Branch regG (Rel 2)
    , Jump (Abs 87)
    , Pop regF
    , WriteInstr regF (IndAddr regE)
    , Compute Incr regE regE regE
    , Compute Decr regG regG regG
    , Jump (Abs 80)
    , Nop
    , Nop
    , Nop
    , Nop
    --Num - 1
    , Load (ImmValue 1) regB
    , Push regB
    , Nop
    --Num - 2
    , Load (ImmValue 2) regC
    , Push regC
    , Nop
    --Num - 4
    , Load (ImmValue 4) regD
    , Push regD
    , Nop
    --Num - 4
    , Load (ImmValue 4) regE
    , Push regE
    , Load (ImmValue 4) regF
    , Push regF
    , Nop
    --ID Target
    , ReadInstr (DirAddr 1)
    , Receive regA
    , Load (ImmValue 0) regH
    , Compute Add regH regA regH
    , Load (IndAddr regH) regH
    , WriteInstr regH numberIO
    , Pop regB
    , Load (IndAddr regH) regG
    , Compute Equal regG regB regG
    , Branch regG (Abs 1)
    , WriteInstr regB (IndAddr regH)
    , Compute Incr regH regH regH
    , Branch regB (Rel 2)
    , Jump (Abs 124)
    , Pop regG
    , WriteInstr regG (IndAddr regH)
    , Compute Incr regH regH regH
    , Compute Decr regB regB regB
    , Jump (Abs 117)
    , Nop
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr 1)
    , Receive regF
    , Load (ImmValue 0) regD
    , Compute Add regD regF regD
    , ReadInstr (IndAddr regD)
    , Receive regC
    , ReadInstr (IndAddr regC)
    , Receive regG
    , Compute Add regG reg0 regD
    , Compute Add regG regC regC
    , Branch regG (Rel 2)
    , Jump (Abs 144)
    , ReadInstr (IndAddr regC)
    , Receive regE
    , Push regE
    , Compute Decr regC regC regC
    , Compute Decr regG regG regG
    , Jump (Abs 136)
    , Push regD
    --Print: Array of 'int'
    , Pop regH
    , Branch regH (Rel 2)
    , Jump (Abs 152)
    , Pop regA
    , WriteInstr regA numberIO
    , Compute Decr regH regH regH
    , Jump (Abs 146)
    , Nop
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr regArp) regArp
    , Pop regG
    , Pop regH
    , Branch regG (Abs 0)
    , Branch regH (Abs 163)
    , Jump (Abs 163)
    , Nop
    , Nop
    , Push regH
    , Push regG
    , Nop
    , EndProg
    ]
main = run [prog]
