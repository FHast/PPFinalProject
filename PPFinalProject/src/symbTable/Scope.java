package symbTable;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import symbTable.Data.Arr;
import symbTable.Data.Pointer;

/**
 * Symbol table scope supertype
 * @author gereon
 *
 */
public abstract class Scope {
	/** mapping from variable names to their data types */
	private final Map<String, Data> types;
	/** mapping from variable names to their offsets */
	private final Map<String, Integer> offsets;
	/** Set of pointers to arrays */
	private final Set<Pointer> arrays;
	/** current size of scope */
	private int size;
	/** indicates if the scope contains uncaught exceptions */
	private boolean fails;

	/**
	 * Creating a scope, fields are initialized
	 */
	public Scope() {
		this.types = new LinkedHashMap<>();
		this.offsets = new LinkedHashMap<>();
		this.arrays = new HashSet<>();
		this.size = 0;
	}

	/**
	 * Set whether scope contains uncaught exception
	 */
	public void setFails() {
		this.fails = true;
	}

	/**
	 * Read whether scope contains uncaught exception
	 * @return true if passed on exception
	 */
	public boolean fails() {
		return this.fails;
	}

	/**
	 * Request check against decleration of variable name
	 * @param id variable name
	 * @return true if already declared in this scope
	 */
	public boolean contains(String id) {
		return this.types.containsKey(id);
	}

	/**
	 * declare a variable in the current scope
	 * @param id variable name
	 * @param data data type
	 * @return true if successful
	 */
	public boolean put(String id, Data data) {
		boolean result = !this.types.containsKey(id);
		if (result) {
			if (!(data instanceof Arr)) {
				this.types.put(id, data);
				this.offsets.put(id, this.size);
				this.size += data.size();
			} else {
				Pointer p = new Pointer(data);
				this.types.put(id, p);
				this.offsets.put(id, this.size);
				this.size += p.size;
				this.arrays.add(p);
			}
		}
		return result;
	}

	/**
	 * Get data type associated with variable name
	 * @param id variable name
	 * @return data type (or null)
	 */
	public Data get(String id) {
		Data d = this.types.get(id);
		if (d instanceof Pointer && arrays.contains((Pointer) d)) {
			return ((Pointer) d).target();
		} else {
			return d;
		}
	}

	/**
	 * Offset of variable name to arp
	 * @param id variable name
	 * @return offset from arp
	 */
	public Integer offset(String id) {
		return this.offsets.get(id);
	}
	
	/**
	 * storage size of current scope
	 * @return total size of declared variables
	 */
	public int size() {
		return this.size;
	}
}
