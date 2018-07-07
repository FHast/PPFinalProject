package program;

/** 
 * String type, for sycorax comments
 * @author gereon
 *
 */
public class Str extends Operand {
	/** text */
	private String str;

	/**
	 * Create a new Str
	 * @param s text
	 */
	public Str(String s) {
		super(Type.STR);
		this.str = s;
	}

	/** 
	 * Sprockel representation
	 */
	@Override
	public String toString() {
		return this.str;
	}

}
