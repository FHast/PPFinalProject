package program;

/** 
 * sprockel register type
 * @author gereon
 *
 */
public class Reg extends Operand {
	private String name;
	
	/**
	 * Create new register
	 * @param name register name
	 */
	public Reg(String name) {
		super(Type.REG);
		this.name = name;
	}
	
	/**
	 * provide name
	 * @return name of register
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sprockell representation
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
