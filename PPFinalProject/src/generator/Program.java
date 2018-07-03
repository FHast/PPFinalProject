package generator;

import java.util.ArrayList;
import java.util.List;

public class Program {
	public static final int TRUE_VAL = 1;
	public static final int FALSE_VAL = 0;
	
	private final List<Instr> prog;
	private int lineCounter = -1;
	private int coreCount = 1;

	public Program() {
		prog = new ArrayList<>();
	}

	public int addInstr(Instr instr) {
		prog.add(instr);
		return lineCounter++;
	}

	public void incrCoreCount() {
		coreCount++;
	}

	public String prettyPrint() {
		String out = "import Sprockel\n\nprog :: [Instruction]\nprog = [";
		if (prog.size() > 0) {
			out += "\n  " + prog.get(0);
		}
		for (int i = 1; i < prog.size(); i++) {
			out += "\n, " + prog.get(i);
		}
		out += "\n]";
		out += "\n main = run [prog";
		for (int i = 1; i < coreCount; i++) {
			out += ",prog";
		}
		out += "]";
		return out;
	}
}
