package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.SolSystem;
import map.MapView;

public class SystemPanel implements ActionListener {

	MapView myView;
	SolSystem mySystem;

	public SystemPanel(SolSystem s, MapView v) {
		mySystem = s;
		myView = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myView.viewSystem(mySystem);
		System.out.println("Down");
	}

}
