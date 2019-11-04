package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;

/**
 * Adds functionality to the return button of the MapView
 * 
 * @author PlanetaryTennis
 * @see MapView
 */

public class back implements ActionListener {

	MapView myView;

	/**
	 * Class Constructor
	 * 
	 * @param MapView whose backup function will be called.
	 */
	public back(MapView view) {
		myView = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.backup();
	}

}
