package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;

import astronomy.old.Region;
import astronomy.old.Sector;
import map.MapView;

public class NewRegion implements ActionListener {

	MapView myView;
	boolean isRandom;
	Sector mySector;
	JMenu myMenu;
	
	public NewRegion(boolean r,Sector s,JMenu m,MapView v){
		isRandom = r;
		mySector = s;
		myMenu = m;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Region r;
		if(isRandom) {
			r = Region.makeRandom(mySector,myView.mySettings);
		}else {
			r = new Region(Region.randomName(),mySector);
		}
		JMenu j = MapView.populateRegionMenu(r,myView);
		myMenu.add(j);
		mySector.Add(r);
	}
	
}
