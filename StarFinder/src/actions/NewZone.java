package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import astronomy.Region;
import astronomy.Sector;
import astronomy.Zone;
import map.MapView;

public class NewZone implements ActionListener {

	MapView myView;
	boolean isRandom;
	Region myRegion;
	JMenu myMenu;
	
	public NewZone(boolean r,Region s,JMenu m, MapView v){
		isRandom = r;
		myRegion = s;
		myMenu = m;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Zone r;
		if(isRandom) {
			r = Zone.makeRandom(myRegion,myView.mySettings);
		}else {
			r = new Zone(Zone.randomName(), myRegion);
		}
		JMenu j = MapView.populateZoneMenu(r,myView);
		myMenu.add(j);
		myRegion.Add(r);
	}

}
