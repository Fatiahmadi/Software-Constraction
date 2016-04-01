package testEvaluation;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import questionair.absTree.expression.calculation.Division;
import questionair.absTree.expression.calculation.Sub;
import questionair.absTree.expression.calculation.Time;
import questionair.absTree.expression.literal.Integerliteral;
import questionair.evaluation.Value;

public class TestMixCalculation extends TestAbstract{

	@Test
	public void testCalculations() {
		Value value1 = evaluate( new Time(
				new Sub(new Integerliteral(7), new Integerliteral(2)),
				new Integerliteral(5)) );
		Value value2 = evaluate(new Time(
				new Division(new Integerliteral(48), new Integerliteral(8)),
				new Integerliteral(2)) );
				
		assertEquals("(7-2)*5=25", value1.getValue(), 25);
		assertEquals("(48/8)*2=12", value2.getValue(), 12);
	}

}
