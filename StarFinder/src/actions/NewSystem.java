package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.stellar.Star;
import engine.ObjectFiles;
import map.MapView;

public class NewSystem implements ActionListener {

	MapView myView;
	boolean isRandom;
	Zone myZone;
	JMenu myMenu;

	public NewSystem(boolean r, Zone s, JMenu m, MapView v) {
		isRandom = r;
		myZone = s;
		myMenu = m;
		myView = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SolSystem r;
		if (isRandom) {
			r = SolSystem.makeRandom(myZone, myView.mySettings);
		} else {
			r = new SolSystem(null, SolSystem.randomName());
			r.setMyStar(Star.randomStar(r, null));
		}
		myMenu.add(MapView.populateSystemMenu(r, myView));
		ObjectFiles.WriteSavabletoFile(r, myZone.getMyRegion().getMySector().getMyGalaxy().getMyName());
		myZone.Add(r.getID());
	}

}
