package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import relay.PrimaryRelay;

public class PrimeJump implements ActionListener {
	
	MapView myMap;
	PrimaryRelay partner;
	
	public PrimeJump(PrimaryRelay myPartner,MapView myMap) {
		partner = myPartner;
		this.myMap = myMap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myMap.viewPrimaryRelay(partner);
	}

}
