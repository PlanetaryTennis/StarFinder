package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;

/**
 * @author PlanetaryTennis
 * @see map.MapView.backup()
 */

public class back implements ActionListener {
	
	MapView myView;
	
	/**
	 * @author PlanetaryTennis
	 * @see map.MapView.backup()
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
