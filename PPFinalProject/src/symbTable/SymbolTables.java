package symbTable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import symbTable.Data.Func;
import symbTable.Data.Lock;

public class SymbolTables {
	private Map<String, SymbolTable> tables;

	private Map<String, Func> functions;

	private Stack<String> threads;

	private Set<Lock> locked;

	private Map<String, Lock> locks;

	public static final String GLOBAL = "global";
	public static final String MAIN = "main";

	public SymbolTables() {
		this.functions = new HashMap<>();
		this.tables = new HashMap<>();
		this.threads = new Stack<>();
		newThread(GLOBAL);
		newThread(MAIN);
	}

	public boolean newThread(String id) {
		if (tables.containsKey(id))
			return false;
		threads.push(id);
		tables.put(id, new SymbolTable());
		return true;
	}

	public boolean closeThread(String id) {
		assert id != MAIN && id != GLOBAL;
		if (!id.equals(threadID())) {
			return false;
		} else {
			//tables.remove(threadID());
			threads.pop();
			return true;
		}
	}

	public void lock(String id) { // TODO lock storage and offsets
		Lock l;
		if (locks.containsKey(id)) {
			l = locks.get(id);
		} else {
			l = new Lock();
			locks.put(id, l);
		}
		locked.add(l);
	}

	public boolean unlock(String id) {
		Lock l = locks.get(id);
		if (l == null) {
			return false;
		}
		locked.remove(l);
		return true;
	}

	public String threadID() {
		return threads.peek();
	}

	public SymbolTable table() {
		return tables.get(threadID());
	}

	public SymbolTable global() {
		return tables.get(GLOBAL);
	}

	public boolean putFunction(String id, Func fun) {
		if (functions.containsKey(id)) {
			return false;
		}
		functions.put(id, fun);
		return true;
	}

	public Func getFunction(String id) {
		return functions.get(id);
	}

	public Func getFunction() {
		return table().getFunction();
	}
	
	public Map<String, Integer> getHeapStarts() {
		Map<String,Integer> ret = new HashMap<>();
		for (String s : tables.keySet()) {
			SymbolTable t = tables.get(s);
			ret.put(s, t.getMaxSize());
		}
		return ret;
	}

}
