package symbTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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

	private List<String> threadIDs;

	public static final String GLOBAL = "global";
	public static final String MAIN = "main";

	public SymbolTables() {
		this.functions = new HashMap<>();
		this.locks = new HashMap<>();
		this.locked = new HashSet<>();
		this.tables = new HashMap<>();
		this.threads = new Stack<>();
		this.threadIDs = new ArrayList<>();
		tables.put(GLOBAL, new SymbolTable());
		newThread(MAIN);
	}

	public boolean newThread(String id) {
		if (tables.containsKey(id))
			return false;
		threads.push(id);
		threadIDs.add(id);
		tables.put(id, new SymbolTable());
		return true;
	}

	public boolean hasThread(String id) {
		return tables.containsKey(id);
	}

	public boolean closeThread(String id) {
		assert id != MAIN && id != GLOBAL;
		if (!id.equals(threadID())) {
			return false;
		} else {
			// tables.remove(threadID());
			threads.pop();
			return true;
		}
	}

	public boolean lock(String id) {
		Data d = global().get(id);
		if (d != null && !(d instanceof Lock)) {
			return false;
		}
		Lock l;
		if (locks.containsKey(id)) {
			l = locks.get(id);
		} else {
			l = new Lock();
			locks.put(id, l);
			global().put(id, l);
		}
		locked.add(l);
		return true;
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
		Map<String, Integer> ret = new HashMap<>();
		for (String s : threadIDs) {
			SymbolTable t = tables.get(s);
			ret.put(s, t.getMaxSize());
		}
		return ret;
	}

	public List<String> getThreads() {
		return this.threadIDs;
	}

	public boolean globalScope() {
		return this.tables.get(MAIN).getScopesSize() == 1;
	}

}
