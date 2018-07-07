package symbTable;

/**
 * Collection of symbol tables for multithreading
 */
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
	/** map from thread name to symboltable */
	private Map<String, SymbolTable> tables;
	/** global list of declared functions */
	private Map<String, Func> functions;
	/** stack of declared threads */
	private Stack<String> threads;
	/** set of locked locks */
	private Set<Lock> locked;
	/** map from lock names to lock objects */
	private Map<String, Lock> locks;
	/** list of all ever created thread names */
	private List<String> threadIDs;
	
	/** global symboltable name */
	public static final String GLOBAL = "global";
	/** main thread identifier */
	public static final String MAIN = "main";

	/**
	 * initialize fields
	 * create global symboltable
	 * create main thread
	 */
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

	/**
	 * Create new thread
	 * initialize symbol table for thread
	 * @param id thread name
	 * @return true if successful
	 */
	public boolean newThread(String id) {
		if (tables.containsKey(id))
			return false;
		threads.push(id);
		threadIDs.add(id);
		tables.put(id, new SymbolTable());
		return true;
	}

	/**
	 * Requests if thread exists
	 * @param id thread name
	 * @return true if exists
	 */
	public boolean hasThread(String id) {
		return tables.containsKey(id);
	}

	/**
	 * Close thread (join)
	 * @param id thread name
	 * @return true if successful
	 */
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

	/**
	 * Lock a lock
	 * checks if lock is already created
	 * fails if variable with that name exists
	 * @param id lock name
	 * @return success
	 */
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

	/**
	 * Unlock lock, remove from locked locks
	 * @param id lock name
	 * @return true if it was locked
	 */
	public boolean unlock(String id) {
		Lock l = locks.get(id);
		if (l == null) {
			return false;
		}
		locked.remove(l);
		return true;
	}

	/**
	 * Current running thread
	 * @return
	 */
	public String threadID() {
		return threads.peek();
	}

	/**
	 * symboltable of current thread
	 * @return
	 */
	public SymbolTable table() {
		return tables.get(threadID());
	}

	/**
	 * Global symbol table
	 * @return
	 */
	public SymbolTable global() {
		return tables.get(GLOBAL);
	}

	/**
	 * store a function
	 * @param id function name
	 * @param fun function object
	 * @return true if name is available
	 */
	public boolean putFunction(String id, Func fun) {
		if (functions.containsKey(id)) {
			return false;
		}
		functions.put(id, fun);
		return true;
	}

	/**
	 * Get function object by name
	 * @param id function name
	 * @return get function object
	 */
	public Func getFunction(String id) {
		return functions.get(id);
	}

	/**
	 * get most recent function decleration
	 * @return
	 */
	public Func getFunction() {
		return table().getFunction();
	}

	/**
	 * generate start of heap managements
	 * @return mapping of thread name to heap start
	 */
	public Map<String, Integer> getHeapStarts() {
		Map<String, Integer> ret = new HashMap<>();
		for (String s : threadIDs) {
			SymbolTable t = tables.get(s);
			ret.put(s, t.getMaxSize());
		}
		return ret;
	}

	/**
	 * get all threadID
	 * @return list of all thread names
	 */
	public List<String> getThreads() {
		return this.threadIDs;
	}

	/**
	 * check if current symboltable is in global scope
	 * @return true if current scope is global scope
	 */
	public boolean globalScope() {
		return this.tables.get(MAIN).getScopesSize() == 1;
	}

}
