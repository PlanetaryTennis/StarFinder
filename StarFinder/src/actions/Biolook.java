package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import planetary.Colony;
import planetary.Condition;

/**
 * Biolook views the biosphere of a planet, in the map view.
 * 
 * @author PlanetaryTennis
 */
public class Biolook implements ActionListener {

	Colony Col;
	Condition Con;
	MapView m;

	/**
	 * Class Constructor
	 * 
	 * @param colony  The colony to look at the biosphere of
	 * @param c       The associated Planetary Condition
	 * @param mapView The MapView to display the biosphere on
	 */
	public Biolook(Colony colony, Condition c, MapView mapView) {
		Col = colony;
		Con = c;
		m = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.viewBiosphere(Col, Con);
	}

}
