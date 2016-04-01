package testEvaluation;


import org.junit.After;
import org.junit.Before;

import questionair.absTree.expression.AbsExpression;
import questionair.absTree.expression.literal.Integerliteral;
import questionair.evaluation.EvalVisitor;
import questionair.evaluation.ValueSaver;
import questionair.evaluation.Value;

public class TestAbstract {

	protected final Integerliteral int4 = new Integerliteral(4);
	
	private ValueSaver valrep = new ValueSaver();
	private EvalVisitor eval;

	@Before 
	public void setUp() { 
		eval = new EvalVisitor(valrep); 
	}
	
	public Value evaluate(AbsExpression expression) {
		return expression.accept(this.eval);
	}
	
	@After
	public void tearDown() { eval = null; }

	}


