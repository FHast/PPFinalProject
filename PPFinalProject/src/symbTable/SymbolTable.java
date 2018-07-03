package symbTable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import symbTable.Data.Func;

public class SymbolTable {
	private final Stack<Scope> scopes;

	private final Scope global;

	private final Map<String, Func> functions;

	private final Set<String> threads;

	private final Set<String> locks;

	public SymbolTable() {
		this.scopes = new Stack<>();
		this.functions = new HashMap<>();
		this.threads = new HashSet<>();
		this.locks = new HashSet<>();
		openFunScope(null);
		global = scopes.peek();
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
		this.scopes.pop();
	}

	public boolean put(String id, Data type, boolean global) {
		if (global) {
			if (get(id) != null) {
				return false;
			}
			return this.global.put(id, type);
		}
		if (!this.global.contains(id)) {
			return this.scopes.peek().put(id, type);
		}
		return false;
	}

	public boolean putFunction(String id, Func fun) {
		if (functions.containsKey(id)) {
			return false;
		}
		functions.put(id, fun);
		return true;
	}

	public boolean putThread(String id) {
		return threads.add(id);
	}

	public boolean removeThread(String id) {
		return threads.remove(id);
	}

	public boolean putLock(String id) {
		return locks.add(id);
	}

	public boolean removeLock(String id) {
		return locks.remove(id);
	}

	public boolean isGlobalScope() {
		return scopes.size() == 1;
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

	public Func getFunction(String id) {
		return functions.get(id);
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

	public Func function(String id) {
		if (functions.containsKey(id)) {
			return functions.get(id);
		} else {
			return null;
		}
	}

	public int offset(String id) {
		Integer result = null;
		for (int i = this.scopes.size() - 1; result == null && i >= 0; i--) {
			result = this.scopes.get(i).offset(id);
		}
		return result == null ? -1 : result;
	}
}
