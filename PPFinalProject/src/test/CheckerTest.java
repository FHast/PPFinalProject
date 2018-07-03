package test;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import checker.ErrorListener;
import checker.ParseException;
import checker.TypeChecker;
import grammar.SycoraxLexer;
import grammar.SycoraxParser;


public class CheckerTest {
	private final static String BASE_DIR = "src/sample";
	private final static String EXT = ".syc";

	@Test
	public void testCalcs() throws IOException, ParseException {
		check("fibonacci");
	}

	private void check(String filename) throws IOException, ParseException {
		File file = new File(filename + EXT);
		if (!file.exists()) {
			file = new File(BASE_DIR, filename + EXT);
		}
		try {
		TypeChecker checker = new TypeChecker();
		checker.check(parse(file));
		} catch (ParseException e) {
			System.out.println(String.format("Error log for '%s'",filename));
			e.print();
			throw e;
		}
	}

	/** Compiles a given Simple Pascal string into a parse tree. */
	public ParseTree parse(File file) throws ParseException, IOException {
		return parse(CharStreams.fromPath(file.toPath()));
	}

	private ParseTree parse(CharStream chars) throws ParseException {
		ErrorListener listener = new ErrorListener();
		Lexer lexer = new SycoraxLexer(chars);
		lexer.removeErrorListeners();
		lexer.addErrorListener(listener);
		TokenStream tokens = new CommonTokenStream(lexer);
		SycoraxParser parser = new SycoraxParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		ParseTree result = parser.program();
		listener.throwException();
		return result;
	}
}
