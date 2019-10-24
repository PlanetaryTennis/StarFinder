package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.old.Zone;
import map.MapView;

public class ZonePanel implements ActionListener {

	MapView myView;
	Zone mySystem;
	
	public ZonePanel(Zone s,MapView v){
		mySystem = s;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewZone(mySystem);
	}

}
