package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.planetary.Planet;
import map.MapView;

public class viewWorldData implements ActionListener {

	private Planet myWorld;
	private MapView myView;

	public viewWorldData(Planet planet, MapView view) {
		myWorld = planet;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.viewWorldData(myWorld);
	}

}
