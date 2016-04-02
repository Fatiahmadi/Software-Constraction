package testEvaluation;


import org.junit.After;
import org.junit.Before;

import q.aTree.expr.AExpression;
import q.aTree.expr.literal.Integerliteral;
import q.evaluation.EvalVisitor;
import q.evaluation.Value;
import q.evaluation.ValueSaver;

public class TestAbstract {

	protected final Integerliteral int4 = new Integerliteral(4);
	
	private ValueSaver valrep = new ValueSaver();
	private EvalVisitor eval;

	@Before 
	public void setUp() { 
		eval = new EvalVisitor(valrep); 
	}
	
	public Value evaluate(AExpression expression) {
		return expression.accept(this.eval);
	}
	
	@After
	public void tearDown() { eval = null; }

	}


