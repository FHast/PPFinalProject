package symbTable;

import symbTable.Data.Func;

public class FunScope extends Scope {
	private Func func;
	private boolean missingCatchable;

	public FunScope(Func func) {
		super();
		this.func = func;
		this.missingCatchable = false;
	}

	public Func getFunction() {
		return this.func;
	}

	public void setMissing() {
		if (!func.isCatchable()) {
			this.missingCatchable = true;
		}
	}

	public boolean isMissing() {
		return this.missingCatchable;
	}
}
