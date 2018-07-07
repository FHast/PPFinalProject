package symbTable;

import symbTable.Data.Func;

/**
 * Scope opened by function definition construct
 * @author gereon
 *
 */
public class FunScope extends Scope {
	/**
	 * Function causing this scope
	 */
	private Func func;
	/**
	 * Indicating whether the function is missing catchable keyword
	 */
	private boolean missingCatchable;

	/**
	 * Create a new function scope
	 * @param func function causing the scope
	 */
	public FunScope(Func func) {
		super();
		this.func = func;
		this.missingCatchable = false;
	}

	/**
	 * Get the causing function
	 * @return function object
	 */
	public Func getFunction() {
		return this.func;
	}

	/**
	 * Mark that a exception is not caught
	 */
	public void setMissing() {
		if (!func.isCatchable()) {
			this.missingCatchable = true;
		}
	}

	/**
	 * uncaught exception in scope
	 * @return true if causing function is missing catchable keyword
	 */
	public boolean isMissing() {
		return this.missingCatchable;
	}
}
