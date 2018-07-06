package generator;

public class Address extends Operand {
	private Addr type;
	private int val;
	private Reg reg;

	public Address(Addr type, Reg reg) {
		super(Type.ADDRESS);
		assert type == Addr.IndAddr;
		this.type = type;
		this.reg = reg;
	}

	public Address(Addr type, int val) {
		super(Type.ADDRESS);
		assert type != Addr.IndAddr;
		this.type = type;
		this.val = val;
	}
	
	public Address(Addr type) {
		super(Type.ADDRESS);
		assert type == Addr.charIO || type == Addr.numberIO;
		this.type = type;
	}

	@Override
	public String toString() {
		if (this.type == Addr.charIO || this.type == Addr.numberIO) {
			return this.type.toString();
		}
		Object arg = type == Addr.IndAddr ? reg : val;
		return String.format("(%s (%s))", type, arg);
	}

	public enum Addr {
		ImmValue, DirAddr, IndAddr, charIO, numberIO;
	}
}
