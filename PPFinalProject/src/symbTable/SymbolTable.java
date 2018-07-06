package symbTable;

import java.util.Stack;

import symbTable.Data.Func;

public class SymbolTable {
	private final Stack<Scope> scopes;

	private int maxSize;

	public SymbolTable() {
		this.scopes = new Stack<>();
		openFunScope(null);
		maxSize = 0;
	}

	public void openBlockScope(boolean catches) {
		this.scopes.push(new BlockScope(catches));
	}

	public void openFunScope(Func func) {
		this.scopes.push(new FunScope(func));
	}

	public void closeScope() {
		if (this.scopes.size() == 1) {
			throw new IllegalStateException("Can't close outer scope");
		}
		if (currentSize() > this.maxSize) {
			this.maxSize = currentSize();
		}
		this.scopes.pop();
	}

	public int getMaxSize() {
		return this.maxSize;
	}

	private int currentSize() {
		int size = 0;
		for (Scope s : scopes) {
			size += s.size();
		}
		return size;
	}

	public int size() {
		return this.scopes.peek().size();
	}

	public boolean put(String id, Data type) {
		return this.scopes.peek().put(id, type);
	}

	public boolean isMissingCatchable() {
		if (scopes.peek() instanceof FunScope) {
			return ((FunScope) scopes.peek()).isMissing();
		} else {
			return false;
		}
	}

	public Func getFunction() {
		for (int i = scopes.size() - 1; i > 0; i--) {
			Scope current = scopes.get(i);
			if (current instanceof FunScope) {
				return ((FunScope) current).getFunction();
			}
		}
		return null;
	}

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

	public Data get(String id) {
		Data result = null;
		for (int i = this.scopes.size() - 1; result == null && i >= 0; i--) {
			result = this.scopes.get(i).get(id);
		}
		return result;
	}

	public int depth(String id) {
		int result = -1;
		for (int i = this.scopes.size() - 1; result < 0 && i >= 0; i--) {
			if (this.scopes.get(i).contains(id)) {
				result = this.scopes.size() - 1 - i;
			}
		}
		return result;
	}

	public int offset(String id) {
		Integer result = null;
		for (int i = this.scopes.size() - 1; result == null && i >= 0; i--) {
			result = this.scopes.get(i).offset(id);
		}
		return result == null ? -1 : result;
	}
	
	public int getScopesSize() {
		return this.scopes.size();
	}
}
