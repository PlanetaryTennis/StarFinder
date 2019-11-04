package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Galaxy;
import map.MapView;

public class saver implements ActionListener {

	MapView v;
	Galaxy galaxy;
	
	public saver(MapView v, Galaxy galaxy) {
		this.v = v;
		this.galaxy = galaxy;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		MapView.save(v, galaxy);
	}

}
