package generator;

public class Str extends Operand {
	private String str;

	public Str(String s) {
		super(Type.STR);
		this.str = s;
	}

	@Override
	public String toString() {
		return this.str;
	}

}
