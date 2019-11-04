package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import gate.PrimaryGate;

public class PrimeJump implements ActionListener {
	
	MapView myMap;
	PrimaryGate partner;
	
	public PrimeJump(PrimaryGate myPartner,MapView myMap) {
		partner = myPartner;
		this.myMap = myMap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myMap.viewPrimaryGate(partner);
	}

}
