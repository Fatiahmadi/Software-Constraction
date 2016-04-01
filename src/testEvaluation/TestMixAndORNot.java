package testEvaluation;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import questionair.absTree.expression.literal.Booleanliteral;
import questionair.absTree.expression.logical.And;
import questionair.absTree.expression.logical.Or;
import questionair.absTree.expression.unary.Not;
import questionair.evaluation.Value;

public class TestMixAndORNot extends TestAbstract{

	@Test
	public void testLogicalComparisons() throws Exception {
		Value value1 = evaluate(new Not(new Or(new Booleanliteral(true), new Booleanliteral(true))));
		Value value2 = evaluate(new Or(new Booleanliteral(true),
				new And( new Booleanliteral(false),new Booleanliteral(false))));
		
		assertEquals("!(t || t) = f", value1.getValue(), false);
		assertEquals("t || (f && f) = t", value2.getValue(), true);
	}

}
