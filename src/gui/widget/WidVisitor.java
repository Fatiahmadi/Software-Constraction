package gui.widget;

import questionair.absTree.type.AbsType;
import questionair.absTree.type.BooleanT;
import questionair.absTree.type.IntegerT;
import questionair.absTree.type.NotSpecifiedT;
import questionair.absTree.type.StringT;
import questionair.absTree.type.TypeVisitor;
import questionair.evaluation.BooleanValue;
import questionair.evaluation.IntegerValue;
import questionair.evaluation.StringValue;
import questionair.evaluation.ValueSaver;

public class WidVisitor implements TypeVisitor<WidComponent> {

	private final String id;
	private final AbsType t;
	private final ValueSaver valueSaver;

	public WidVisitor(String id, AbsType t, ValueSaver valueSaver) {
		this.id = id;
		this.t = t;
		this.valueSaver = valueSaver;
	}

	@Override
	public WidIntegerTField visit(IntegerT t) {
		this.valueSaver.putValue(id, new IntegerValue(0));
		return new WidIntegerTField(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidStringTField visit(StringT t) {
		this.valueSaver.putValue(id, new StringValue(""));
		return new WidStringTField(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidBooleanT visit(BooleanT t) {
		this.valueSaver.putValue(id, new BooleanValue(false));
		return new WidBooleanT(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidIntegerTField visit(NotSpecifiedT t) {
		assert false : "Unsupported type. This supposed to be checked in typechecker.";
		return null;

	}

}