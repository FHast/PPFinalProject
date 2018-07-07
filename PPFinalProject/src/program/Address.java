package program;

/**
 * Part of instructions, address provide are equivalent to their sprockell counterpart
 * @author gereon
 *
 */
public class Address extends Operand {
	private Addr type;
	private int val;
	private Reg reg;

	/**
	 * Create an address of type Ind
	 * @param type
	 * @param reg
	 */
	public Address(Addr type, Reg reg) {
		super(Type.ADDRESS);
		assert type == Addr.IndAddr;
		this.type = type;
		this.reg = reg;
	}

	/**
	 * Create an address of other type than Ind
	 * @param type
	 * @param val
	 */
	public Address(Addr type, int val) {
		super(Type.ADDRESS);
		assert type != Addr.IndAddr;
		this.type = type;
		this.val = val;
	}
	
	/**
	 * Create an address to sprockell outputs
	 * @param type
	 */
	public Address(Addr type) {
		super(Type.ADDRESS);
		assert type == Addr.charIO || type == Addr.numberIO;
		this.type = type;
	}

	/**
	 * Sprockell representation
	 */
	@Override
	public String toString() {
		if (this.type == Addr.charIO || this.type == Addr.numberIO) {
			return this.type.toString();
		}
		Object arg = type == Addr.IndAddr ? reg : val;
		return String.format("(%s (%s))", type, arg);
	}

	/**
	 * Possible types of addresses
	 * @author gereon
	 *
	 */
	public enum Addr {
		ImmValue, DirAddr, IndAddr, charIO, numberIO;
	}
}
