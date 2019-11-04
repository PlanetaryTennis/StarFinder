package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.planetary.Planet;
import map.MapView;
import planetary.Colony;
import planetary.Habitation;

public class Colonize implements ActionListener {

	Colony colony;
	MapView myMap;
	Planet planet;
	
	public Colonize(Colony colony,MapView map,Planet p) {
		this.colony = colony;
		this.myMap = map;
		this.planet = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		colony.setMyColony(new Habitation(colony.getMaxSize()));
		myMap.viewSurface(planet);
	}

}
