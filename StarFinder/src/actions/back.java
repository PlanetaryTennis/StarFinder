package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;

/*
 * author James Armstrong
 * input A Map View
 * function Adds functionality to back buttons by calling backup from the map view.
 */

public class back implements ActionListener {

	/*
	 * Owned map view
	 */
	MapView myView;
	
	/*
	 * constructor requiring the map view for functionality.
	 */
	public back(MapView view) {
		myView = view;
	}

	/*
	 * called when action it calls the view's backup function.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.backup();
	}

}
