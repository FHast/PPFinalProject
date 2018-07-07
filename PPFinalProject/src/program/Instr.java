package program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import program.Operand.Type;

public class Instr {
	private OpCode opcode;
	private List<Operand> operands;

	public Instr(OpCode oc, Operand... operands) {
		this(oc, new ArrayList<Operand>(Arrays.asList(operands)));
	}

	public Instr(OpCode oc, List<Operand> operands) {
		this.opcode = oc;
		int opcount = this.opcode.getTypesSize();
		if (opcount != operands.size()) {
			throw new IllegalArgumentException(
					String.format("Operation '%s' expects %d arguments but has %d", opcode, opcount, operands.size()));
		}
		for (int i = 0; i < opcount; i++) {
			Operand op = operands.get(i);
			Type expected = opcode.getTypes().get(i);
			if (op.getType() != expected) {
				throw new IllegalArgumentException(
						String.format("Operation '%s' argument %d should be '%s' but is '%s'", this.opcode, i, expected,
								op.getType()));
			}
		}
		this.operands = new ArrayList<>(operands);
	}

	@Override
	public String toString() {
		String out = this.opcode.toString();
		for (Operand op : this.operands) {
			out += " " + op.toString();
		}
		return out;
	}

	public OpCode getOpCode() {
		return this.opcode;
	}

	public List<Operand> getOperands() {
		return this.operands;
	}
}
