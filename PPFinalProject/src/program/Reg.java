package program;

public class Reg extends Operand {
	private String name;
	
	public Reg(String name) {
		super(Type.REG);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
