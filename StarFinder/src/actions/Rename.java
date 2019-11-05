package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.Galaxy;
import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import astronomy.stellar.Star;
import gate.PrimaryGate;
import gate.SecondaryGate;

public class Rename implements ActionListener {

	private int index;
	private Galaxy g;
	private Sector s;
	private Region r;
	private Zone z;
	private SolSystem ss;
	private Star sr;
	private Planet pl;
	private PrimaryGate pg;
	private SecondaryGate sg;
	
	public Rename(Galaxy galaxy) {
		index = 0;
		g = galaxy;
	}

	public Rename(Sector sector) {
		index = 1;
		s = sector;
	}

	public Rename(Region region) {
		index = 2;
		r = region;
	}

	public Rename(Zone zone) {
		index = 3;
		z = zone;
	}

	public Rename(SolSystem solsystem) {
		index = 4;
		ss = solsystem;
	}

	public Rename(Star star) {
		index = 5;
		sr = star;
	}

	public Rename(Planet planet) {
		index = 6;
		pl = planet;
	}

	public Rename(PrimaryGate o) {
		index = 7;
		pg = o;
	}

	public Rename(SecondaryGate o) {
		index = 8;
		sg = o;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
