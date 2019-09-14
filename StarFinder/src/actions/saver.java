package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Sector;
import map.MapView;

public class saver implements ActionListener {

	Sector[] mySectors;
	
	public saver(Sector[] mySectors) {
		this.mySectors = mySectors;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MapView.save(mySectors);
	}

}
