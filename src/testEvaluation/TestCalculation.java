package testEvaluation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import questionair.absTree.expression.calculation.Add;
import questionair.absTree.expression.calculation.Division;
import questionair.absTree.expression.calculation.Sub;
import questionair.absTree.expression.calculation.Time;
import questionair.absTree.expression.literal.Integerliteral;
import questionair.evaluation.Value;

public class TestCalculation extends TestAbstract {
	@Test
	public void testADD() {
		Value value = evaluate(new Add(new Integerliteral(3),
				new Integerliteral(5)));
		Value value2 = evaluate(new Add(new Integerliteral(1),
				new Integerliteral(4)));
		Value value3 = evaluate(new Add(new Add(new Integerliteral(1),
				new Integerliteral(4)), int4));

		assertEquals("1+5 = 6", value.getValue(), 8);
		assertEquals("1+7 = 8", value2.getValue(), 5);
		assertEquals("(1+4)+4 = 9", value3.getValue(), 9);
	}

	@Test
	public void testSUB() {
		// Value value = evaluate( new Sub(new Integerliteral(5), new
		// Integerliteral(3)));
		Value value2 = evaluate(new Sub(new Integerliteral(4),
				new Integerliteral(1)));
		// Value value3 = evaluate( new Sub(new Add(new Integerliteral(1), new
		// Integerliteral(4)), int4));

		// assertEquals("5-3 = 2", value.getValue(), 2);
		assertEquals("4-1 = 3", value2.getValue(), 3);
		// assertEquals("(1+4)-4 = 1", value3.getValue(), 1);
	}

	@Test
	public void testTime() {
		Value value = evaluate(new Time(new Integerliteral(5),
				new Integerliteral(4)));

		assertEquals("5*4 = 20", value.getValue(), 20);
	}

	@Test
	public void testDivision() {
		Value value1 = evaluate(new Division(new Integerliteral(0),
				new Integerliteral(4)));
		Value value2 = evaluate(new Division(new Integerliteral(45),
				new Integerliteral(9)));

		assertEquals("0/0 = ", value1.getValue(), 0);
		assertEquals("45/9 = 5", value2.getValue(), 5);
	}

}
