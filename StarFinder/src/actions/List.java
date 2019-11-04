package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import astronomy.Galaxy;
import engine.EditMenu;

public class List implements ActionListener {

	Galaxy g;
	EditMenu em;

	public List(EditMenu editMenu, Galaxy myGalaxy) {
		em = editMenu;
		g = myGalaxy;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cursor save = em.getCursor();
		em.setCursor(new Cursor(Cursor.WAIT_CURSOR));

		em.setCursor(save);

		JOptionPane.showMessageDialog(em, "", "Numbers", JOptionPane.INFORMATION_MESSAGE);
	}

}
