package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.planetary.Planet;
import map.PlanetViewer;

public class PlanetView implements ActionListener {

	private Planet myPlanet;
	private PlanetViewer myView;

	public PlanetView(Planet planet, PlanetViewer view) {
		myPlanet = planet;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewPlanet(myPlanet);
	}

}
