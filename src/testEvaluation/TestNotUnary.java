package testEvaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import q.aTree.expr.literal.Booleanliteral;
import q.aTree.expr.unary.Not;
import q.evaluation.Value;

public class TestNotUnary extends TestAbstract{

	@Test
	public void testNotComparisons() throws Exception {
		Value value1 = evaluate(new Not(new Booleanliteral(true)));
		Value value2 = evaluate(new Not(new Booleanliteral(false)));
		
		assertEquals("!t = f", value1.getValue(), false);
		assertEquals("!f = t", value2.getValue(), true);
		
	}
}
