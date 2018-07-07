package program;

/**
 * Arithmetic operators of sprockell
 * @author gereon
 *
 */
public class Operator extends Operand {
	/** the operator type */
	private Op operator;

	/**
	 * Create a new operator
	 * @param kind operator type
	 */
	public Operator(Op kind) {
		super(Type.OPERATOR);
		this.operator = kind;
	}
	
	/**
	 * Provide operator type
	 * @return operator
	 */
	public Op getOperator() {
		return this.operator;
	}
	
	/** 
	 * sprockell string representation
	 */
	@Override
	public String toString() {
		return this.operator.toString();
	}

	/** All operator types in sprockell */
	public static enum Op {
		Add, Sub, Mul, Equal, NEq, Gt, Lt, GtE, LtE, And, Or, Xor, LShift, RShift, Decr, Incr
	}
}
