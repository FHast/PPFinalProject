package symbTable;

/**
 * SymbolTable scope opened by block construct
 * @author gereon
 *
 */
public class BlockScope extends Scope {
	private boolean catches;

	/**
	 * Create new block scope
	 * @param catches existence of catch block
	 */
	public BlockScope(boolean catches) {
		super();
		this.catches = catches;
	}

	/**
	 * flag indicating existence of catch block
	 * @return true if block has catch part
	 */
	public boolean catches() {
		return this.catches;
	}
}
