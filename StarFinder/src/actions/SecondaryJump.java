package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.MapView;
import gate.SecondaryGate;

public class SecondaryJump implements ActionListener {

	MapView myMap;
	SecondaryGate partner;

	public SecondaryJump(SecondaryGate myPartner, MapView myMap) {
		partner = myPartner;
		this.myMap = myMap;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myMap.viewSecondaryGate(partner);
	}

}
