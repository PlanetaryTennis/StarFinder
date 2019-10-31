package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import planetary.Colony;
import planetary.Condition;

public class Biolook implements ActionListener {
	
	Colony Col;
	Condition Con;
	MapView m;

	public Biolook(Colony colony, Condition c, MapView mapView) {
		Col = colony;
		Con = c;
		m = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.viewBiosphere(Col,Con);
	}

}
