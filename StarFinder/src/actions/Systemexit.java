package actions;

import map.SystemView;

public class Systemexit extends java.awt.event.WindowAdapter {

	SystemView j;

	public Systemexit(SystemView f) {
		j = f;
	}

	public void windowClosing(java.awt.event.WindowEvent e) {
        SystemView.save(j);
		System.exit(0);
	}

}
