package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Moon;
import map.MapView;

public class MoonView implements ActionListener {

	Moon myMoon;
	MapView myView;
	
	public MoonView(Moon orbitObject, MapView view) {
		myMoon = orbitObject;
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.viewWorldData(myMoon);
	}

}
