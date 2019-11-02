package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.OrbitObject;
import map.MapView;
import relay.ImageRelay;
import relay.PrimaryRelay;
import relay.Relay;
import relay.SecondaryRelay;
import utilities.Functions;

public class SatilightView implements ActionListener {

	OrbitObject O;
	MapView map;
	
	public SatilightView(OrbitObject orbitObject, MapView mapView) {
		map = mapView;
		O = orbitObject;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(Functions.ClassSwitch(O.getClass(),new Class[]{ImageRelay.class})) {
		case 0:
			ImageRelay IR = (ImageRelay)O;
			if(IR.getMyRelay().getClass() == PrimaryRelay.class) {
				map.viewPrimaryRelay((PrimaryRelay) IR.getMyRelay());
			}else {
				map.viewSecondaryRelay((SecondaryRelay) IR.getMyRelay());				
			}
			break;
		case -1:
		default:
			break;
		}
	}

}
