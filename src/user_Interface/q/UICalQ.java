package user_Interface.q;
import javax.swing.JLabel;

import user_Interface.eventHandler.Updater;
import user_Interface.widget.WidComponent;

public class UICalQ extends UINormalQ implements QUpdaterInterface{ 
	private Updater updater;
	
	public UICalQ(String id, JLabel jL,WidComponent widCom, Updater updater) {
		super(id, jL, widCom); 
		this.getWidCom().setEnabled(false); 
		this.updater = updater;
	}

	@Override
	public Updater getUpdater() {
		return this.updater;
	}

	@Override
	public void updateGUI() {
		 updater.updateGUI(this);
		
	}

	
}