package program;

/**
 * Target sprockell type
 * @author gereon
 *
 */
public class Target extends Operand {
	private Tar type;
	private int val;
	private Reg reg;

	/**
	 * Create a target which is not Indirect
	 * @param type target type
	 * @param val target address
	 */
	public Target(Tar type, int val) {
		super(Type.TARGET);
		assert type != Tar.Ind;
		this.type = type;
		this.val = val;
	}

	/**
	 * Create a indirect target
	 * @param type target type
	 * @param reg register containing address
	 */
	public Target(Tar type, Reg reg) {
		super(Type.TARGET);
		assert type == Tar.Ind;
		this.type = type;
		this.reg = reg;
	}

	/**
	 * Sprockel representation
	 */
	@Override
	public String toString() {
		Object arg = type == Tar.Ind ? reg : val;
		return String.format("(%s (%s))", type, arg);
	}

	/**
	 * Set target of not absolute target
	 * @param val new address value
	 */
	public void setTarget(int val) {
		assert type != Tar.Ind;
		this.val = val;
	}

	/** 
	 * All sprockel target types
	 * @author gereon
	 *
	 */
	public enum Tar {
		Abs, Rel, Ind;
	}
}
