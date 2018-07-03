package symbTable;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import symbTable.Data.Arr;
import symbTable.Data.Pointer;

public abstract class Scope {

	private final Map<String, Data> types;

	private final Map<String, Integer> offsets;

	private final Set<Pointer> arrays;

	private int size;

	private boolean fails;

	public Scope() {
		this.types = new LinkedHashMap<>();
		this.offsets = new LinkedHashMap<>();
		this.arrays = new HashSet<>();
	}

	public void setFails() {
		this.fails = true;
	}

	public boolean fails() {
		return this.fails;
	}

	public boolean contains(String id) {
		return this.types.containsKey(id);
	}

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

	public Data get(String id) {
		Data d = this.types.get(id);
		if (d instanceof Pointer && arrays.contains((Pointer) d)) {
			return ((Pointer) d).target();
		} else {
			return d;
		}
	}

	public Integer offset(String id) {
		return this.offsets.get(id);
	}

	public int size() {
		return this.size;
	}
}
