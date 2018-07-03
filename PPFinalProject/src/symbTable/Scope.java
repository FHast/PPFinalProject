package symbTable;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Scope {
	
	private final Map<String, Data> types;
	
	private final Map<String, Integer> offsets;
	
	private int size;
	
	private boolean fails;
	
	public Scope() {
		this.types = new LinkedHashMap<>();
		this.offsets = new LinkedHashMap<>();
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
			this.types.put(id, data);
			this.offsets.put(id, this.size);
			this.size += data.size();
		}
		return result;
	}
	
	public Data get(String id) {
		return this.types.get(id);
	}
	
	public Integer offset(String id) {
		return this.offsets.get(id);
	}
}
