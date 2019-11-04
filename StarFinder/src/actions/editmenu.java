package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.EditMenu;
import map.MapView;

/**
 * @author PlanetaryTennis
 * @see engine.EditMenu
 */

public class editmenu implements ActionListener {

	MapView myView;

	/**
	 * @author PlanetaryTennis
	 * @see engine.EditMenu
	 * @param MapView who will be passed to the EditMenu constructor.
	 */
	public editmenu(MapView mapView) {
		myView = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new EditMenu(myView);
	}

}
