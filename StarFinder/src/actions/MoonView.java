package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.planetary.Moon;
import map.PlanetViewer;

public class MoonView implements ActionListener {

	Moon myMoon;
	PlanetViewer myView;

	public MoonView(Moon orbitObject, PlanetViewer view) {
		myMoon = orbitObject;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.viewWorldData(myMoon);
	}

}
