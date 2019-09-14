package actions;

import astronomy.Sector;
import map.MapView;

/**
 * @author PlanetaryTennis
 */

public class exit extends java.awt.event.WindowAdapter {
	
	MapView j;
	Sector[] mySectors;
	
	/**
	 * @author PlanetaryTennis
	 * @param MapView who will be saved.
	 * @param Sector who will be passed to MapView to be saved.
	 */
	public exit(MapView f, Sector[] mySectors){
		j = f;
		this.mySectors = mySectors;
	}
	
    public void windowClosing(java.awt.event.WindowEvent e) {
		System.out.println(mySectors[0].getName());
//        MapView.save(mySectors);
    	System.exit(0);
    }
}
