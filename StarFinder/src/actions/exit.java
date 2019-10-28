package actions;

import astronomy.Galaxy;
import astronomy.Sector;
import map.MapView;

/**
 * @author PlanetaryTennis
 */

public class exit extends java.awt.event.WindowAdapter {
	
	MapView j;
	Galaxy galaxy;
	
	/**
	 * @author PlanetaryTennis
	 * @param MapView who will be saved.
	 * @param Sector who will be passed to MapView to be saved.
	 */
	public exit(MapView f){
		j = f;
		this.galaxy = f.getGalaxy();
	}
	
    public void windowClosing(java.awt.event.WindowEvent e) {
//        MapView.save(j,galaxy);
    	System.exit(0);
    }
}
