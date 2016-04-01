package gui.question;

//import questionair.evaluation.BooleanValue;
import gui.widget.WidComponent;

import javax.swing.JLabel;

import questionair.evaluation.Value;

public class UINormalQ implements UIQ {
	private final String id;
	private final JLabel jL;
	private final WidComponent widCom;
	private Value value;

	public UINormalQ(String id, JLabel jL, WidComponent widCom) {
		this.id = id;
		this.jL = jL;
		this.widCom = widCom;
	}

	public String getId() {
		return id;
	}

	public JLabel getJL() {
		return jL;
	}

	public WidComponent getWidCom() {
		return widCom;
	}

	@Override
	public void setValue(Value value) {
		this.value = value;
		this.widCom.setValue(value);
		this.widCom.setVisible(true);

	}

	@Override
	public void setVisibilityValue(Value value) {
		boolean visible = Boolean.parseBoolean(value.toString());// The boolean returned represents the value true if the string argument is not null and is equal
		this.widCom.setVisible(visible);
		this.jL.setVisible(visible);

	}
}
