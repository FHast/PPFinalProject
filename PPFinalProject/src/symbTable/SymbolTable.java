package symbTable;

import java.util.Stack;

import symbTable.Data.Func;

/**
 * Symbol table for typechecking phase of Sycorax
 * @author gereon
 *
 */
public class SymbolTable {
	/** Stack of scopes */
	private final Stack<Scope> scopes;
	/** maximal size of symbol table */
	private int maxSize;
	
	/**
	 * Create new symboltable
	 */
	public SymbolTable() {
		this.scopes = new Stack<>();
		openFunScope(null);
		maxSize = 0;
	}
	
	/**
	 * Open block scope
	 * @param catches has catch phrase
	 */
	public void openBlockScope(boolean catches) {
		this.scopes.push(new BlockScope(catches));
	}
	
	/**
	 * Open function scope
	 * @param func causing function
	 */
	public void openFunScope(Func func) {
		this.scopes.push(new FunScope(func));
	}

	/**
	 * Close top scope
	 */
	public void closeScope() {
		if (this.scopes.size() == 1) {
			throw new IllegalStateException("Can't close outer scope");
		}
		if (currentSize() > this.maxSize) {
			this.maxSize = currentSize();
		}
		this.scopes.pop();
	}

	/**
	 * Get max size
	 * @return maximal accumulated size
	 */
	public int getMaxSize() {
		return this.maxSize;
	}

	/**
	 * Return current size
	 * @return current accumulated size
	 */
	private int currentSize() {
		int size = 0;
		for (Scope s : scopes) {
			size += s.size();
		}
		return size;
	}

	/**
	 * Size of top scope
	 * @return top scope size
	 */
	public int size() {
		return this.scopes.peek().size();
	}

	/**
	 * Put a variable into the symbol table
	 * @param id variable name
	 * @param type variable data type
	 * @return true if successful
	 */
	public boolean put(String id, Data type) {
		return this.scopes.peek().put(id, type);
	}

	/**
	 * Returns if top fun scope is missing catchable keyword
	 * @return true top scope is missing catchable keyword
	 */
	public boolean isMissingCatchable() {
		if (scopes.peek() instanceof FunScope) {
			return ((FunScope) scopes.peek()).isMissing();
		} else {
			return false;
		}
	}

	/**
	 * Get the most recent function object
	 * @return most recent declared function
	 */
	public Func getFunction() {
		for (int i = scopes.size() - 1; i > 0; i--) {
			Scope current = scopes.get(i);
			if (current instanceof FunScope) {
				return ((FunScope) current).getFunction();
			}
		}
		return null;
	}

	/**
	 * set that current scope fails.
	 * @return if exceptions is caught
	 */
	public boolean setFails() {
		this.scopes.peek().setFails();

		boolean caught = false;
		for (int i = scopes.size() - 1; !caught && i > 0; i--) {
			Scope current = scopes.get(i);
			if (current instanceof BlockScope) {
				caught = ((BlockScope) current).catches();
			} else if (current instanceof FunScope) {
				((FunScope) current).setMissing();
				if (!((FunScope) current).isMissing() && i == 1) {
					caught = true;
				}
			}
		}
		return caught;
	}

	/**
	 * Get variable data type by name
	 * @param id variable name
	 * @return data type
	 */
	public Data get(String id) {
		Data result = null;
		for (int i = this.scopes.size() - 1; result == null && i >= 0; i--) {
			result = this.scopes.get(i).get(id);
		}
		return result;
	}

	/**
	 * Depth of variable by name
	 * @param id variable name
	 * @return how many scopes to go back
	 */
	public int depth(String id) {
		int result = -1;
		for (int i = this.scopes.size() - 1; result < 0 && i >= 0; i--) {
			if (this.scopes.get(i).contains(id)) {
				result = this.scopes.size() - 1 - i;
			}
		}
		return result;
	}

	/** 
	 * Offset of variable by name
	 * @param id variable name
	 * @return offset from arp
	 */
	public int offset(String id) {
		Integer result = null;
		for (int i = this.scopes.size() - 1; result == null && i >= 0; i--) {
			result = this.scopes.get(i).offset(id);
		}
		return result == null ? -1 : result;
	}
	
	/**
	 * Returns amount of open scopes
	 * @return amount of open scopes
	 */
	public int getScopesSize() {
		return this.scopes.size();
	}
}
