package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Planet;
import map.MapView;

public class PlanetView implements ActionListener {

	private Planet myPlanet;
	private MapView myView;

	public PlanetView(Planet planet, MapView view) {
		myPlanet = planet;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewPlanet(myPlanet);
	}

}
