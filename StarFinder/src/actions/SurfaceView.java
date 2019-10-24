package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.planetary.Planet;
import map.MapView;

public class SurfaceView implements ActionListener{


	private Planet myPlanet;
	private MapView myView;

	public SurfaceView(Planet planet, MapView view) {
		myPlanet = planet;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewSurface(myPlanet);
	}
}
