package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Sector;
import map.MapView;

public class SectorPanel implements ActionListener {

	MapView myView;
	Sector mySystem;
	
	public SectorPanel(Sector s,MapView v){
		mySystem = s;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewSector(mySystem);
	}
}
