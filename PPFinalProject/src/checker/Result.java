package checker;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import symbTable.Data;

/** Class holding the results of the Simple Pascal checker. */
public class Result {
	/**
	 * Mapping from statements and expressions to the atomic subtree that is their
	 * entry in the control flow graph.
	 */
	private final ParseTreeProperty<ParserRuleContext> entries = new ParseTreeProperty<>();
	/** Mapping from expressions to types. */
	private final ParseTreeProperty<Data> types = new ParseTreeProperty<>();
	/** Mapping from variables to offset. */
	private final ParseTreeProperty<Integer> offsets = new ParseTreeProperty<>();
	/** Mapping from variables to decleration depth. */
	private final ParseTreeProperty<Integer> depths = new ParseTreeProperty<>();
	/** Mapping from variables to current thread. */
	private final ParseTreeProperty<String> threads = new ParseTreeProperty<>();

	/** Adds an association from parse tree node to the flow graph entry. */
	public void setEntry(ParseTree node, ParserRuleContext entry) {
		this.entries.put(node, entry);
	}

	/**
	 * Returns the flow graph entry associated with a given parse tree node.
	 */
	public ParserRuleContext getEntry(ParseTree node) {
		return this.entries.get(node);
	}

	/**
	 * Sets the decleration depth on a node
	 * @param node
	 * @param depth
	 */
	public void setDepth(ParseTree node, int depth) {
		this.depths.put(node, depth);
	}

	/**
	 * Returns the decleartion depth on a node
	 * @param node
	 * @return
	 */
	public int getDepth(ParseTree node) {
		return this.depths.get(node);
	}

	/**
	 * Set the current thread on a node
	 * @param node
	 * @param id
	 */
	public void setThread(ParseTree node, String id) {
		this.threads.put(node, id);
	}

	/**
	 * Returns the current thread on a node
	 * @param node
	 * @return
	 */
	public String getThread(ParseTree node) {
		return this.threads.get(node);
	}

	/**
	 * Adds an association from a parse tree node containing a variable reference to
	 * the offset of that variable.
	 */
	public void setOffset(ParseTree node, int offset) {
		this.offsets.put(node, offset);
	}

	/**
	 * Returns the declaration offset of the variable accessed in a given parse tree
	 * node.
	 */
	public int getOffset(ParseTree node) {
		return this.offsets.get(node);
	}

	/**
	 * Adds an association from a parse tree expression, type, or assignment target
	 * node to the corresponding (inferred) type.
	 */
	public void setType(ParseTree node, Data type) {
		this.types.put(node, type);
	}

	/** Returns the type associated with a given parse tree node. */
	public Data getType(ParseTree node) {
		return this.types.get(node);
	}
}
