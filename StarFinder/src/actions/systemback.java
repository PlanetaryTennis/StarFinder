package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import map.SystemView;

public class systemback implements ActionListener {

	SystemView view;
	
	public systemback(SystemView systemView) {
		view = systemView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		view.backup();
	}

}
