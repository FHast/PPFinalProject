package symbTable;

import java.util.ArrayList;
import java.util.List;

public abstract class Data {
	private static final int BASIC_SIZE = 1;
	public static final Int INT = new Int();
	public static final Bool BOOL = new Bool();
	public static final Char CHAR = new Char();

	protected Type type;
	/** Size in Bytes */
	protected int size;

	protected boolean global;

	public Data(Type type) {
		this(type, BASIC_SIZE);
	}

	public Data(Type type, int size) {
		this.type = type;
		this.size = size;
	}

	public boolean global() {
		return this.global;
	}

	public void setGlobal() {
		this.global = true;
	}

	public Type type() {
		return this.type;
	}

	public int size() {
		return this.size;
	}

	public void setSize(int size) {
		assert this.size == 0;
		assert size > 0;
		this.size = size;
	}

	public enum Type {
		INTEGER, BOOLEAN, CHARACTER, ARRAY, FUNCTION, POINTER;
	}

	static public class Int extends Data {
		public Int() {
			super(Type.INTEGER);
		}

		@Override
		public String toString() {
			return "Integer";
		}
	}

	static public class Bool extends Data {
		public Bool() {
			super(Type.BOOLEAN);
		}

		@Override
		public String toString() {
			return "Boolean";
		}
	}

	static public class Char extends Data {
		public Char() {
			super(Type.CHARACTER);
		}

		@Override
		public String toString() {
			return "Character";
		}
	}

	static public class Arr extends Data {
		private Data elemData;
		private String name;
		private static int idcount = 0;

		public Arr(Data elem, int size) {
			this(elem, size, "Array" + idcount++);
		}

		public Arr(Data elem, int size, String id) {
			super(Type.ARRAY, (size + 1) * BASIC_SIZE);
			this.elemData = elem;
			this.name = id;
		}

		public String id() {
			return this.name;
		}

		public Data elem() {
			return this.elemData;
		}

		@Override
		public String toString() {
			return String.format("Array of '%s' sized '%s'", this.elemData, this.size);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (!(obj instanceof Arr)) {
				return false;
			} else {
				Arr other = (Arr) obj;
				return this.elem().equals(other.elem()) && this.size == other.size;
			}
		}
	}

	static public class Func extends Data {
		private List<Data> args;
		private Data returnData;
		private boolean catchable;

		public Func(Data data, List<Data> args, boolean catchable) {
			super(Type.FUNCTION);
			this.returnData = data;
			this.args = new ArrayList<>(args);
			this.catchable = catchable;
		}

		public List<Data> args() {
			return this.args;
		}

		public Data arg(int i) {
			return this.args.get(i);
		}

		public Data ret() {
			return this.returnData;
		}

		public boolean isCatchable() {
			return this.catchable;
		}

		public boolean isVoid() {
			return this.type == null;
		}

		@Override
		public String toString() {
			return String.format("Function %s -> '%s'", this.args, this.returnData);
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			} else if (!(obj instanceof Func)) {
				return false;
			} else {
				Func other = (Func) obj;
				return this.ret().equals(other.ret()) && this.args.equals(other.args);
			}
		}
	}

	static public class Pointer extends Data {
		private Data target;

		public Pointer(Data target) {
			super(Type.POINTER);
			this.target = target;
		}

		public Data target() {
			return this.target;
		}

		@Override
		public String toString() {
			return String.format("Pointer to '%s'", this.target);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (!(obj instanceof Data)) {
				return false;
			} else if (obj instanceof Pointer) {
				return this.target.equals(((Pointer) obj).target);
			} else {
				return this.target.equals(((Data) obj));
			}
		}
	}
}
