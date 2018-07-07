package program;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the representation of a sprockel program
 * print statement is the complete haskell file
 * @author gereon
 *
 */
public class Program {
	public static final int TRUE_VAL = 1;
	public static final int FALSE_VAL = 0;

	/** List of instructions */
	private final List<Instr> prog;
	private int lineCounter = 0;
	private int coreCount = 1;

	public Program() {
		prog = new ArrayList<>();
	}

	/**
	 * Add instruction to the program and return the absolute line number of the addes instruction
	 * @param instr
	 * @return line number
	 */
	public int addInstr(Instr instr) {
		prog.add(instr);
		if (instr.getOpCode() != OpCode.Comment) {
			int ret = lineCounter;
			lineCounter++;
			return ret;
		}
		return 0;
	}

	/**
	 * Sets the number of cores the program requires.
	 * @param count
	 */
	public void setCores(int count) {
		this.coreCount = count;
	}

	/**
	 * provides a string containing the haskell representation of the program
	 * @return haskell representation
	 */
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
