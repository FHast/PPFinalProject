package generator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import checker.ErrorListener;
import checker.ParseException;
import checker.Result;
import checker.TypeChecker;
import grammar.SycoraxLexer;
import grammar.SycoraxParser;

public class Sycorax {

	private final static String BASE_DIR = "src/sample";
	private final static String EXT = ".syc";

	public static void main(String[] args) throws IOException {
		if (args.length==0) {
			System.out.println("provide filepath.");
			return;
		}

		String filename = args[0];

		File file = new File(filename + EXT);
		if (!file.exists()) {
			file = new File(BASE_DIR, filename + EXT);
		}
		try {
			ErrorListener listener = new ErrorListener();
			Lexer lexer = new SycoraxLexer(CharStreams.fromPath(file.toPath()));
			lexer.removeErrorListeners();
			lexer.addErrorListener(listener);
			TokenStream tokens = new CommonTokenStream(lexer);
			SycoraxParser parser = new SycoraxParser(tokens);
			parser.removeErrorListeners();
			parser.addErrorListener(listener);
			
			ParseTree tree = parser.program();
			listener.throwException();
			TypeChecker checker = new TypeChecker();
			Result res = checker.check(tree);
			Generator gen = new Generator(checker.getTables(), res);
			Program prog = gen.getProgram(tree);

			PrintWriter out = new PrintWriter(BASE_DIR + "/" + filename + ".hs");
			out.println(prog.prettyPrint());
			out.close();

		} catch (ParseException e) {
			System.out.println(String.format("Error log for '%s'", filename));
			e.print();
		}

	}
}
