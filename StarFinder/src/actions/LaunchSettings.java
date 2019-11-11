package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

import map.MapView;

/**
 * @author PlanetaryTennis
 */
public class LaunchSettings implements ActionListener {

	boolean b;
	JSlider sectors;
	JSlider regionsmin;
	JSlider regionsmax;
	JSlider zonemin;
	JSlider zonemax;
	JSlider sysmin;
	JSlider sysmax;
	JSlider planetmax;
	JCheckBox special;
	JCheckBox multi;
	JCheckBox name;
	JCheckBox Gates;
	JFrame LS;
	private int[] suns;

	/**
	 * @author PlanetaryTennis
	 * @see map.MapView
	 * @param JFrame     The settings JFrame to be closed with the launch.
	 * @param b          [boolean] determines if the view is opened in painter mode
	 *                   or not.
	 * @param sectors    [JSlider] determines the number of sectors the Galaxy is
	 *                   generated with.
	 * @param regionsmin [JSlider] determines the minimum number of regions each
	 *                   sector is generated with.
	 * @param regionsmax [JSlider] determines the number of aditional regions each
	 *                   sector is generated with.
	 * @param suns
	 * 
	 */
	public LaunchSettings(JFrame launchSettings, boolean b, JSlider sectors, JSlider regionsmin, JSlider regionsmax,
			JSlider zonemin, JSlider zonemax, JSlider sysmin, JSlider sysmax, JSlider planetmax, JCheckBox special,
			JCheckBox multi, JCheckBox name, JCheckBox Gates, int[] suns) {
		super();
		LS = launchSettings;
		this.b = b;
		this.sectors = sectors;
		this.regionsmin = regionsmin;
		this.regionsmax = regionsmax;
		this.zonemin = zonemin;
		this.zonemax = zonemax;
		this.sysmin = sysmin;
		this.sysmax = sysmax;
		this.planetmax = planetmax;
		this.special = special;
		this.multi = multi;
		this.name = name;
		this.Gates = Gates;
		this.suns = suns;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int s = sectors.getValue();
		int rS = regionsmin.getValue();
		int rE = regionsmax.getValue();
		int zS = zonemin.getValue();
		int zE = zonemax.getValue();
		int sS = sysmin.getValue();
		int sE = sysmax.getValue();
		int p = planetmax.getValue();
		boolean ss = special.isSelected();
		boolean ms = multi.isSelected();
		boolean n = name.isSelected();
		boolean r = Gates.isSelected();

		if (s > 6 || rS > 8 || rE > 7 || zS > 8 || zE > 7 || sS > 8 || sE > 7 || p > 20 || r)
			JOptionPane.showMessageDialog(LS, "You options may cause long generation times for your galaxy.", "Warning",
					JOptionPane.WARNING_MESSAGE);

		String[] dia = new String[] { "Not Have", "Have" };

		if (1 == JOptionPane.showConfirmDialog(LS,
				"Your Galaxy will have...\n" + s + " Sectors \n" + "Between " + rS + " And " + (rS + rE)
						+ " Regions per Sector \n" + "Between " + zS + " And " + (zS + zE) + " Zones per Region \n"
						+ "Between " + sS + " And " + (sS + sE) + " Systems per Zone \n" + "At Most " + p
						+ " Planets per System \n" + "It Will " + dia[ss ? 1 : 0] + " Special Star Types\n" + "It Will "
						+ dia[ms ? 1 : 0] + " Multipule Stars in a Single System\n" + "It Will " + dia[n ? 1 : 0]
						+ " Radom Names\n" + "It Will " + dia[r ? 1 : 0] + " a Gate Network\n" + "Is This Acceptable?",
				"Continue?", JOptionPane.YES_NO_OPTION)) {
			return;
		}

		LS.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if (b) {
			MapView v = new MapView("Galaxy Painter", s, rS, rE, zS, zE, sS, sE, p, ss, ms, n, suns, r);
			v.getSize();
		} else {

		}
		LS.dispose();
	}

}
