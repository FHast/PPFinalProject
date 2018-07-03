package symbTable;

public class BlockScope extends Scope {
	private boolean catches;

	public BlockScope(boolean catches) {
		super();
		this.catches = catches;
	}

	public boolean catches() {
		return this.catches;
	}
}
