package generator;

public class Target extends Operand {
	private Tar type;
	private int val;
	private Reg reg;

	public Target(Tar type, int val) {
		super(Type.TARGET);
		assert type != Tar.Ind;
		this.type = type;
		this.val = val;
	}

	public Target(Tar type, Reg reg) {
		super(Type.TARGET);
		assert type == Tar.Ind;
		this.reg = reg;
	}

	@Override
	public String toString() {
		Object arg = type == Tar.Ind ? reg : val;
		return String.format("(%s %s)", type, arg);
	}

	public void setTarget(int val) {
		assert type != Tar.Ind;
		this.val = val;
	}

	public enum Tar {
		Abs, Rel, Ind;
	}
}
