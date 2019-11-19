package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.Subable;
import map.MapView;

public class Remover implements ActionListener {

	private int index;
	private Subable Sub;
	private MapView View;

	public Remover(Subable sub, int i, MapView mapView) {
		index = i;
		Sub = sub;
		View = mapView;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Sub.removeSub(index);
		View.backup();
	}

}
