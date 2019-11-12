package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.PlanetViewer;
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
	PlanetViewer m;

	/**
	 * Class Constructor
	 * 
	 * @param colony  The colony to look at the biosphere of
	 * @param c       The associated Planetary Condition
	 * @param mapView The MapView to display the biosphere on
	 */
	public Biolook(Colony colony, Condition c, PlanetViewer mapView) {
		Col = colony;
		Con = c;
		m = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.viewBiosphere(Col, Con);
	}

}
