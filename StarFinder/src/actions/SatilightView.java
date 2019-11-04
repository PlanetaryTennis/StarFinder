package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.OrbitObject;
import map.MapView;
import gate.ImageGate;
import gate.PrimaryGate;
import gate.SecondaryGate;
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
		switch(Functions.ClassSwitch(O.getClass(),new Class[]{ImageGate.class})) {
		case 0:
			ImageGate IR = (ImageGate)O;
			if(IR.getMyGate().getClass() == PrimaryGate.class) {
				map.viewPrimaryGate((PrimaryGate) IR.getMyGate());
			}else {
				map.viewSecondaryGate((SecondaryGate) IR.getMyGate());				
			}
			break;
		case -1:
		default:
			break;
		}
	}

}
