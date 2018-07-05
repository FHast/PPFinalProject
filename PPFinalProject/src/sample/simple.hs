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
    , Load (ImmValue 2) regE
    , WriteInstr regE (DirAddr 1)
    , Load (ImmValue 5000) regE
    , WriteInstr regE (DirAddr 2)
    , Load (ImmValue 5000) regHeap
    , Nop
    , Nop
    , Nop
    --Num - 3
    , Load (ImmValue 3) regF
    , Push regF
    , Nop
    --Num - 2
    , Load (ImmValue 2) regG
    , Push regG
    , Nop
    --Num - 1
    , Load (ImmValue 1) regH
    , Push regH
    , Load (ImmValue 3) regA
    , Push regA
    --ArrayDef - allocate
    , ReadInstr (DirAddr 2)
    , Receive regE
    , ReadInstr (DirAddr 1)
    , Receive regF
    , Compute Add regE reg0 regB
    , Load (ImmValue 0) regC
    , Compute Add regF regC regC
    , WriteInstr regE (IndAddr regC)
    , Pop regD
    , Compute Incr regE regE regE
    , Compute Add regE regD regE
    , WriteInstr regE (DirAddr 2)
    --ArrayDef - store data
    , WriteInstr regD (IndAddr regB)
    , Compute Incr regB regB regB
    , Branch regD (Rel 2)
    , Jump (Abs 78)
    , Pop regC
    , WriteInstr regC (IndAddr regB)
    , Compute Incr regB regB regB
    , Compute Decr regD regD regD
    , Jump (Abs 71)
    , Nop
    , Nop
    , Branch regHeap (Abs 82)
    , Load (ImmValue 5000) regHeap
    , Nop
    --Block
    , Load (ImmValue 3) regG
    , Compute Add regG regArp regG
    , Compute Decr regG regG regG
    , Store regArp (IndAddr regG)
    , Compute Incr regG regG regArp
    , Nop
    --Load ID - x
    , ReadInstr (DirAddr 1)
    , Receive regE
    , Load (ImmValue 0) regC
    , Compute Add regC regE regC
    , ReadInstr (IndAddr regC)
    , Receive regB
    , ReadInstr (IndAddr regB)
    , Receive regF
    , Branch regF (Rel 2)
    , Jump (Abs 105)
    , ReadInstr (IndAddr regB)
    , Receive regD
    , Push regD
    , Compute Incr regB regB regB
    , Compute Decr regF regF regF
    , Jump (Abs 97)
    , Push regF
    --Print: Array of 'int'
    , Pop regG
    , Branch regG (Rel 2)
    , Jump (Abs 113)
    , Pop regH
    , WriteInstr regH numberIO
    , Compute Decr regG regG regG
    , Jump (Abs 107)
    , Nop
    --Block - end
    , Push reg0
    , Push reg0
    , Nop
    , Compute Decr regArp regArp regArp
    , Load (IndAddr regArp) regArp
    , Pop regH
    , Pop regA
    , Branch regH (Abs 0)
    , Branch regA (Abs 124)
    , Jump (Abs 124)
    , Nop
    , Nop
    , Push regA
    , Push regH
    , Nop
    , EndProg
    ]
main = run [prog]
