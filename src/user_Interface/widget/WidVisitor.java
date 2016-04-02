package user_Interface.widget;

import q.aTree.type.AType;
import q.aTree.type.BooleanT;
import q.aTree.type.IntegerT;
import q.aTree.type.NotSpecifiedT;
import q.aTree.type.StringT;
import q.aTree.type.TypeVisitor;
import q.evaluation.BooleanV;
import q.evaluation.IntegerV;
import q.evaluation.StringV;
import q.evaluation.ValueSaver;

public class WidVisitor implements TypeVisitor<WidComponent> {

	private final String id;
	private final AType t;
	private final ValueSaver valueSaver;

	public WidVisitor(String id, AType t, ValueSaver valueSaver) {
		this.id = id;
		this.t = t;
		this.valueSaver = valueSaver;
	}

	@Override
	public WidIntegerTField visit(IntegerT t) {
		this.valueSaver.putValue(id, new IntegerV(0));
		return new WidIntegerTField(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidStringTField visit(StringT t) {
		this.valueSaver.putValue(id, new StringV(""));
		return new WidStringTField(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidBooleanT visit(BooleanT t) {
		this.valueSaver.putValue(id, new BooleanV(false));
		return new WidBooleanT(this.id, this.t, this.valueSaver);
	}

	@Override
	public WidIntegerTField visit(NotSpecifiedT t) {
		assert false : "Unsupported type. This supposed to be checked in typechecker.";
		return null;

	}

}