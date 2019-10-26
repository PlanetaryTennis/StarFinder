package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Region;
import map.MapView;

public class RegionPanel implements ActionListener {

	MapView myView;
	Region mySystem;
	
	public RegionPanel(Region s,MapView v){
		mySystem = s;
		myView = v;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewRegion(mySystem);
	}
}
