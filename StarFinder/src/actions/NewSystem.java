package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Star;
import astronomy.Zone;
import map.MapView;

public class NewSystem implements ActionListener {

	MapView myView;
	boolean isRandom;
	Zone myZone;
	JMenu myMenu;
	
	public NewSystem(boolean r,Zone s,JMenu m, MapView v){
		isRandom = r;
		myZone = s;
		myMenu = m;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		SolSystem r;
		if(isRandom) {
			r = SolSystem.makeRandom(myZone,myView.mySettings);
		}else {
			r = new SolSystem(null,SolSystem.randomName(), myZone);
			r.setMyStar(Star.randomStar(r, null));
		}
		myMenu.add(MapView.populateSystemMenu(r, myView));
		myZone.Add(r);
	}
	
}
