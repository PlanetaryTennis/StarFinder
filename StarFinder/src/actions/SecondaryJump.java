package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import relay.PrimaryRelay;
import relay.SecondaryRelay;

public class SecondaryJump implements ActionListener {

	MapView myMap;
	SecondaryRelay partner;
	
	public SecondaryJump(SecondaryRelay myPartner,MapView myMap) {
		partner = myPartner;
		this.myMap = myMap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myMap.viewSecondaryRelay(partner);
	}

}
