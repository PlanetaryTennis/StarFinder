package actions;

import astronomy.Sector;
import map.MapView;

/*
 * author James Armstrong
 * input A Map View, and the sectors associated
 * function Adds functionality to escape button or x button saving the sectors and exiting the program.
 */

public class exit extends java.awt.event.WindowAdapter {
	
	/*
	 * Mapview for funtionality
	 * Secotrs to be saved.
	 */
	
	MapView j;
	Sector[] mySectors;
	
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
