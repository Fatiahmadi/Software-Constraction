package main.coordinator;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import q.aTree.NodeVisitor;
import q.aTree.form.Form;
import antlrProducts.QLLexer;
import antlrProducts.QLParser;

public class AbsSynTreeBuilder {

	public Form formBuilder(String filename) {
		if (!filename.isEmpty()) {
			ANTLRInputStream inputStream = new ANTLRInputStream(filename);

			QLLexer lexer = new QLLexer(inputStream);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			QLParser parser = new QLParser(tokens);

			// Walk my tree
			ParseTree tree = parser.form();
			NodeVisitor v = new NodeVisitor();
			Form form = (Form) v.visit(tree);

			// Print AST in console
			// System.out.println("AST: \n" + form.toString());

			return form;
		}
		return null;
	}
}