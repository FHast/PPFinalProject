package symbTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Sycorax Data type representations
 * @author gereon
 *
 */
public abstract class Data {
	/**
	 * Sprockel storage size of basic data types
	 */
	private static final int BASIC_SIZE = 1;
	/** singleton instances of integer*/
	public static final Int INT = new Int();
	/** singleton instances of boolean*/
	public static final Bool BOOL = new Bool();
	/** singleton instances of character*/
	public static final Char CHAR = new Char();

	/** data type*/
	protected Type type;
	/** Size in Bytes */
	protected int size;

	/**
	 * Creating data with basic size
	 * @param type datatype
	 */
	public Data(Type type) {
		this(type, BASIC_SIZE);
	}
	
	/**
	 * Creating data with custom size
	 * @param type datatype
	 * @param size storage size needed
	 */
	public Data(Type type, int size) {
		this.type = type;
		this.size = size;
	}

	/**
	 * Returns type of data
	 * @return type
	 */
	public Type type() {
		return this.type;
	}

	/**
	 * Returns needed storage size in sprockell
	 * @return storage size
	 */
	public int size() {
		return this.size;
	}

	/** 
	 * Set storage size of data if not yet set
	 * @param size new storage size
	 */
	public void setSize(int size) {
		assert this.size == 0;
		assert size > 0;
		this.size = size;
	}

	/**
	 * Sycorax data types
	 * @author gereon
	 *
	 */
	public enum Type {
		INTEGER, BOOLEAN, CHARACTER, ARRAY, FUNCTION, POINTER, LOCK;
	}

	/**
	 * Sycorax Integer data type
	 * @author gereon
	 *
	 */
	static public class Int extends Data {
		public Int() {
			super(Type.INTEGER);
		}

		@Override
		public String toString() {
			return "integer";
		}
	}

	/**
	 * Sycorax boolean data type
	 * @author gereon
	 *
	 */
	static public class Bool extends Data {
		public Bool() {
			super(Type.BOOLEAN);
		}

		@Override
		public String toString() {
			return "Boolean";
		}
	}

	/**
	 * Sycorax character data type
	 * @author gereon
	 *
	 */
	static public class Char extends Data {
		public Char() {
			super(Type.CHARACTER);
		}

		@Override
		public String toString() {
			return "Character";
		}
	}

	/**
	 * Sycorax array data type
	 * @author gereon
	 *
	 */
	static public class Arr extends Data {
		/** type of elements */
		private Data elemData;
		/** array name */
		private String name;
		/** id for name creation */
		private static int idcount = 0;

		/**
		 * Create an array with a specific elementtype
		 * @param elem content type
		 */
		public Arr(Data elem) {
			this(elem, "Array" + idcount++);
		}

		/**
		 * Create a named array
		 * @param elem content type
		 * @param id array name
		 */
		public Arr(Data elem, String id) {
			super(Type.ARRAY, 0);
			this.elemData = elem;
			this.name = id;
		}

		/**
		 * Returns array name
		 * @return array name
		 */
		public String id() {
			return this.name;
		}

		/**
		 * returns element type
		 * @return element data type
		 */
		public Data elem() {
			return this.elemData;
		}

		/**
		 * String representation for error logs
		 */
		@Override
		public String toString() {
			return String.format("Array of '%s'", this.elemData);
		}

		/**
		 * Checking if equal to other object
		 * equal if same type and same elem type, or at least one array is empty
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (!(obj instanceof Arr)) {
				return false;
			} else {
				Arr other = (Arr) obj;
				if (this.elem() == null || other.elem() == null) {
					return true;
				}
				return this.elem().equals(other.elem());
			}
		}
	}

	/**
	 * Sycorax function information storage
	 * @author gereon
	 *
	 */
	static public class Func extends Data {
		/** argument types */
		private List<Data> args;
		/** argument names */
		private List<String> vars;
		/** function return type */
		private Data returnData;
		/** catchable - true if content may fail */
		private boolean catchable;
		/** function name */
		private String name;

		/**
		 * Constructing a function representation
		 * @param data return type
		 * @param name function name
		 * @param args argument types
		 * @param vars argument names
		 * @param catchable true if content may fail
		 */
		public Func(Data data, String name, List<Data> args, List<String> vars, boolean catchable) {
			super(Type.FUNCTION);
			this.returnData = data;
			this.args = new ArrayList<>(args);
			this.vars = new ArrayList<>(vars);
			this.catchable = catchable;
			this.name = name;
		}

		/**
		 * function argument types
		 * @return argument types
		 */
		public List<Data> args() {
			return this.args;
		}
		
		/**
		 * function name
		 * @return function name
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * Set argument types
		 * @param args list of data types
		 */
		public void setArgs(List<Data> args) {
			this.args = args;
		}
		
		/**
		 * Set return type
		 * @param data function return type
		 */
		public void setRet(Data data) {
			this.returnData = data;
		}

		/**
		 * Get data type of argument at index i
		 * @param i argument index
		 * @return data type
		 */
		public Data arg(int i) {
			return this.args.get(i);
		}
		
		/**
		 * Get name of argmument at index i
		 * @param i argument index
		 * @return variable name
		 */
		public String var(int i) {
			return this.vars.get(i);
		}
		
		/**
		 * Set names of arguments
		 * @param vars list of names
		 */
		public void setVars(List<String> vars) {
			this.vars = vars;
		}

		/**
		 * Get function return type
		 * @return return data type
		 */
		public Data ret() {
			return this.returnData;
		}

		/**
		 * get catchable flag
		 * @return contains uncaught fail in contents
		 */
		public boolean isCatchable() {
			return this.catchable;
		}

		/**
		 * checks whether there is a return type
		 * @return has no return type
		 */
		public boolean isVoid() {
			return this.type == null;
		}

		/**
		 * String representation for error log
		 */
		@Override
		public String toString() {
			return String.format("Function %s -> '%s'", this.args, this.returnData);
		}

		/** comparing functions */
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

	/**
	 * Sycorax pointer data type
	 * @author gereon
	 *
	 */
	static public class Pointer extends Data {
		private Data target;

		/**
		 * Create pointer to target type
		 * @param target target data type
		 */
		public Pointer(Data target) {
			super(Type.POINTER);
			this.target = target;
		}

		/**
		 * returns target
		 * @return target data type
		 */
		public Data target() {
			return this.target;
		}

		/**
		 * String representation for error log
		 */
		@Override
		public String toString() {
			return String.format("Pointer to '%s'", this.target);
		}
		
		/**
		 * Comparing data types
		 * true if pointing to same data type
		 */
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
	
	/**
	 * Sycorax lock data type 
	 * @author gereon
	 *
	 */
	static public class Lock extends Data {
		public Lock() {
			super(Type.LOCK);
		}
	}
}
