package program;

public class Operator extends Operand {
	private Op operator;

	public Operator(Op kind) {
		super(Type.OPERATOR);
		this.operator = kind;
	}
	
	public Op getOperator() {
		return this.operator;
	}
	
	@Override
	public String toString() {
		return this.operator.toString();
	}

	public static enum Op {
		Add, Sub, Mul, Equal, NEq, Gt, Lt, GtE, LtE, And, Or, Xor, LShift, RShift, Decr, Incr
	}
}
