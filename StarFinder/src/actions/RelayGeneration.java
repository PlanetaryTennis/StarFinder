package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.old.Galaxy;
import engine.EditMenu;
import relay.RelayNetwork;

public class RelayGeneration implements ActionListener {

	Galaxy g;
	EditMenu em;
	
	public RelayGeneration(Galaxy gal,EditMenu e){
		g = gal;
		em = e;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cursor save = em.getCursor();
		em.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		g.setMyNetwork(new RelayNetwork(g, 0));
		em.setCursor(save);
	}

}
