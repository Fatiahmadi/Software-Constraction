package testEvaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import q.aTree.expr.literal.Booleanliteral;
import q.aTree.expr.logical.And;
import q.aTree.expr.logical.Or;
import q.evaluation.Value;

public class TestLogical extends TestAbstract {

	@Test
	public void testOR() throws Exception {
		Value value1 = evaluate(new Or(new Booleanliteral(true), new Booleanliteral(true)));
		Value value2 = evaluate(new Or(new Booleanliteral(true), new Booleanliteral(false)));
		Value value3 = evaluate(new Or(new Booleanliteral(false), new Booleanliteral(false)));
		
		assertEquals("t || t = t", value1.getValue(), true);
		assertEquals("t || f = t", value2.getValue(), true);
		assertEquals("f || f = f", value3.getValue(), false);
	}
	@Test
	public void testAnd() throws Exception {
		Value value1 = evaluate(new And(new Booleanliteral(true), new Booleanliteral(true)));
		Value value2 = evaluate(new And(new Booleanliteral(true), new Booleanliteral(false)));
		Value value3 = evaluate(new And(new Booleanliteral(false), new Booleanliteral(false)));
		
		assertEquals("t && t = t", value1.getValue(), true);
		assertEquals("t && f = f", value2.getValue(), false);
		assertEquals("f && f = f", value3.getValue(), false);
		
	}
}