package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import astronomy.old.Galaxy;
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
		
		g.count();
		
		int star = g.Stars;
		int belts = g.Belts;
		int jov = g.Jovians;
		int ter = g.Terrestrials;
		int hab = g.Habitables;
		int moon = g.Moons;
		int habmoon = g.HabMoons;
		
		em.setCursor(save);
		
		JOptionPane.showMessageDialog(em, "Number of Stars: "+star+"\nNumber of Planets: "+(jov+ter)+"\nNumber of Jovians: "+jov+
				"\nNumber of Teresstials: "+ter+"\nNumber of Belts: "+belts+"\nNumber of Habitable: "+hab+
				"\nNumber of Moon: "+moon+"\nNumber of Habitable Moons: "+habmoon+"\nNumber of Habitable: "+(habmoon+hab),
				"Numbers", JOptionPane.INFORMATION_MESSAGE);
	}

}
