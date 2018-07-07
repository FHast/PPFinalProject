package program;

import java.util.ArrayList;
import java.util.List;

public class Program {
	public static final int TRUE_VAL = 1;
	public static final int FALSE_VAL = 0;

	private final List<Instr> prog;
	private int lineCounter = 0;
	private int coreCount = 1;

	public Program() {
		prog = new ArrayList<>();
	}

	public int addInstr(Instr instr) {
		prog.add(instr);
		if (instr.getOpCode() != OpCode.Comment) {
			int ret = lineCounter;
			lineCounter++;
			return ret;
		}
		return 0;
	}

	public void setCores(int count) {
		this.coreCount = count;
	}

	public String prettyPrint() {
		String out = "import Sprockell\n";

		out += "\nprog :: [Instruction]\nprog = [";
		if (prog.size() > 0) {
			out += "\n      " + prog.get(0);
		}
		for (int i = 1; i < prog.size(); i++) {
			if (prog.get(i).getOpCode() == OpCode.Comment) {
				out += "\n    --" + prog.get(i).getOperands().get(0).toString();
			} else {
				out += "\n    , " + prog.get(i);
			}
		}
		out += "\n    ]";
		out += "\nmain = run [prog";
		for (int i = 1; i < coreCount; i++) {
			out += ",prog";
		}
		out += "]";
		return out;
	}
}
