package generator;

import static generator.Operand.Type.ADDRESS;
import static generator.Operand.Type.OPERATOR;
import static generator.Operand.Type.REG;
import static generator.Operand.Type.TARGET;
import static generator.Operand.Type.STR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OpCode {
	/** Arithmetic operations */
	Compute(OPERATOR, REG, REG, REG),

	/** Control operation: Jump */
	Jump(TARGET),
	/** Control operation: branch */
	Branch(REG, TARGET),

	/** Memory operation: load */
	Load(ADDRESS, REG),
	/** Memory operation: Store */
	Store(REG, ADDRESS),

	/** Stack operation: Push */
	Push(REG),
	/** Stack operation: Pull */
	Pop(REG),

	/** Global memory: request read */
	ReadInstr(ADDRESS),
	/** Global memory: receive value */
	Receive(REG),
	/** Global memory: request write */
	WriteInstr(REG, ADDRESS),
	/** Global memory: testAndSet */
	TestAndSet(ADDRESS),

	/** End current Sprockel */
	EndProg,

	/** No operation */
	Nop,

	/** auxiliary comments */
	Comment(STR);
	
	private final List<Operand.Type> types;

	private OpCode(Operand.Type... args) {
		this.types = new ArrayList<>(Arrays.asList(args));
	}

	public List<Operand.Type> getTypes() {
		return types;
	}

	public int getTypesSize() {
		return types.size();
	}

	public static OpCode parse(String id) {
		return codeMap.get(id);
	}

	private static final Map<String, OpCode> codeMap = new HashMap<>();
	static {
		for (OpCode op : values()) {
			codeMap.put(op.name(), op);
		}
	}
}
