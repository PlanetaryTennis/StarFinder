package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.EditMenu;
import map.MapView;

public class editmenu implements ActionListener {

	MapView myView;
	
	public editmenu(MapView mapView) {
		myView = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new EditMenu(myView);
	}

}
