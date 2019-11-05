package engine;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextPane;

import actions.LaunchSettings;
import actions.StarSetter;
import actions.UpdateStarNumbers;
import map.Sprite;

public class SettingLauncher {

	int[] suns = new int[] { 700, 800, 900, 920, 940, 1000, 70, 90, 100, 100 };

	public SettingLauncher(boolean b) {
		JFrame LaunchSettings = new JFrame("Launch Settings");
		LaunchSettings.setLayout(new GridLayout(16, 3));

		JTextPane sector = new JTextPane();
		sector.setEditable(false);
		sector.setText("Number of Sectors");
		sector.setToolTipText(
				"This is the number of sectors the galaxy will have, larger values will lead to longer generation times.");
		JSlider Sectors = new JSlider();
		Sectors.setPaintLabels(true);
		Sectors.setValue(4);
		Sectors.setMinimum(2);
		Sectors.setMaximum(14);
		Sectors.setMajorTickSpacing(3);
		Sectors.setMajorTickSpacing(1);
		Sectors.setPaintTicks(true);
		LaunchSettings.add(sector);
		LaunchSettings.add(Sectors);

		JTextPane regionmin = new JTextPane();
		regionmin.setEditable(false);
		regionmin.setText("Minimum Number of Regions");
		regionmin.setToolTipText(
				"This is the minimum number of regions per sector, larger values will lead to longer generation times.");
		JSlider RegionsMin = new JSlider();
		RegionsMin.setPaintLabels(true);
		RegionsMin.setValue(5);
		RegionsMin.setMinimum(2);
		RegionsMin.setMaximum(11);
		RegionsMin.setMajorTickSpacing(3);
		RegionsMin.setMajorTickSpacing(1);
		RegionsMin.setPaintTicks(true);
		JTextPane regionmax = new JTextPane();
		regionmax.setEditable(false);
		regionmax.setText("Additonal Regions");
		regionmax.setToolTipText(
				"This is the max value to be randomly added to each sector lower values less random size, larger values will lead to longer generation times.");
		JSlider RegionsMax = new JSlider();
		RegionsMax.setPaintLabels(true);
		RegionsMax.setValue(5);
		RegionsMax.setMinimum(0);
		RegionsMax.setMaximum(9);
		RegionsMax.setMajorTickSpacing(3);
		RegionsMax.setMajorTickSpacing(1);
		RegionsMax.setPaintTicks(true);
		LaunchSettings.add(regionmin);
		LaunchSettings.add(RegionsMin);
		LaunchSettings.add(regionmax);
		LaunchSettings.add(RegionsMax);

		JTextPane zonemin = new JTextPane();
		zonemin.setEditable(false);
		zonemin.setText("Minimum Number of Zones");
		zonemin.setToolTipText(
				"This is the minimum number of zones per region, larger values will lead to longer generation times.");
		JSlider ZonesMin = new JSlider();
		ZonesMin.setPaintLabels(true);
		ZonesMin.setValue(5);
		ZonesMin.setMinimum(2);
		ZonesMin.setMaximum(17);
		ZonesMin.setMajorTickSpacing(3);
		ZonesMin.setMajorTickSpacing(1);
		ZonesMin.setPaintTicks(true);
		JTextPane zonemax = new JTextPane();
		zonemax.setEditable(false);
		zonemax.setText("Additonal Zones");
		zonemax.setToolTipText(
				"This is the max value to be zones added to each region lower values less random size, larger values will lead to longer generation times.");
		JSlider ZonesMax = new JSlider();
		ZonesMax.setPaintLabels(true);
		ZonesMax.setValue(5);
		ZonesMax.setMinimum(0);
		ZonesMax.setMaximum(9);
		ZonesMax.setMajorTickSpacing(3);
		ZonesMax.setMajorTickSpacing(1);
		ZonesMax.setPaintTicks(true);
		LaunchSettings.add(zonemin);
		LaunchSettings.add(ZonesMin);
		LaunchSettings.add(zonemax);
		LaunchSettings.add(ZonesMax);

		JTextPane systemmin = new JTextPane();
		systemmin.setEditable(false);
		systemmin.setText("Minimum Number of Systems");
		systemmin.setToolTipText(
				"This is the minimum number of systems per zone, larger values will lead to longer generation times.");
		JSlider SystemsMin = new JSlider();
		SystemsMin.setPaintLabels(true);
		SystemsMin.setValue(5);
		SystemsMin.setMinimum(1);
		SystemsMin.setMaximum(37);
		SystemsMin.setMajorTickSpacing(3);
		SystemsMin.setMajorTickSpacing(1);
		SystemsMin.setPaintTicks(true);
		JTextPane systemmax = new JTextPane();
		systemmax.setEditable(false);
		systemmax.setText("Additonal Systems");
		systemmax.setToolTipText(
				"This is the max value to be randomly added to each zone lower values less random size, larger values will lead to longer generation times.");
		JSlider SystemsMax = new JSlider();
		SystemsMax.setPaintLabels(true);
		SystemsMax.setValue(5);
		SystemsMax.setMinimum(0);
		SystemsMax.setMaximum(18);
		SystemsMax.setMajorTickSpacing(3);
		SystemsMax.setMajorTickSpacing(1);
		SystemsMax.setPaintTicks(true);
		LaunchSettings.add(systemmin);
		LaunchSettings.add(SystemsMin);
		LaunchSettings.add(systemmax);
		LaunchSettings.add(SystemsMax);

		JTextPane Planets = new JTextPane();
		Planets.setEditable(false);
		Planets.setText("Maximum Number of Planets");
		Planets.setToolTipText(
				"This is the maximum number of planets each solar system can have, this does not include belts, larger values will lead to longer generation times.");
		JSlider PlanetsNum = new JSlider();
		PlanetsNum.setPaintLabels(true);
		PlanetsNum.setValue(13);
		PlanetsNum.setMinimum(1);
		PlanetsNum.setMaximum(22);
		PlanetsNum.setMajorTickSpacing(3);
		PlanetsNum.setMajorTickSpacing(1);
		PlanetsNum.setPaintTicks(true);
		LaunchSettings.add(Planets);
		LaunchSettings.add(PlanetsNum);

		JCheckBox SpecialStars = new JCheckBox("Special Stars");
		SpecialStars.setSelected(false);
		SpecialStars.setToolTipText("This allows abnormal stars such as white dwarfs, nutron stars, and black holes.");
		SpecialStars.setEnabled(true);
		LaunchSettings.add(SpecialStars);

		JButton StarSpread = new JButton("Star Chances");
		StarSpread.addActionListener(new StarSetter(this));
		SpecialStars.setEnabled(true);
		LaunchSettings.add(StarSpread);

		JCheckBox MultiStars = new JCheckBox("Multi Stars");
		MultiStars.setSelected(false);
		MultiStars.setToolTipText("This allows Binary, and Trinary stars");
		MultiStars.setEnabled(true);
		LaunchSettings.add(MultiStars);

		JCheckBox Names = new JCheckBox("Random Names");
		Names.setSelected(false);
		Names.setToolTipText("This will generate random names rather than leaving it in a serial form.");
		Names.setEnabled(true);
		LaunchSettings.add(Names);

		JCheckBox Gates = new JCheckBox("Generate Gate Network");
		Names.setSelected(false);
		Names.setToolTipText("This will generate a Gate network at the launch.");
		Names.setEnabled(true);
		LaunchSettings.add(Gates);

		JButton Make = new JButton("Make");
		Make.addActionListener(new LaunchSettings(LaunchSettings, b, Sectors, RegionsMin, RegionsMax, ZonesMin,
				ZonesMax, SystemsMin, SystemsMax, PlanetsNum, SpecialStars, MultiStars, Names, Gates, suns));
		LaunchSettings.add(Make);

		LaunchSettings.setIconImage(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Black Hole.png"));
		LaunchSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		LaunchSettings.setSize(600, 600);
		LaunchSettings.setVisible(true);
	}

	InnerSlider NormalStars;
	InnerSlider BrownDwarf;
	InnerSlider WhiteDwarf;
	InnerSlider NeutronStar;
	InnerSlider BlackHole;
	InnerSlider Nebula;
	InnerSlider Small;
	InnerSlider Medium;
	InnerSlider Large;
	InnerSlider Multi;

	public void SunCalculat() {
		JFrame StarTypes = new JFrame("Star Types");
		StarTypes.setLayout(new GridLayout(11, 2));
		int NormalStars = suns[0];
		int BrownDwarf = suns[1] - suns[0];
		int WhiteDwarf = suns[2] - suns[1];
		int NeutronStar = suns[3] - suns[2];
		int BlackHole = suns[4] - suns[3];
		int Nebula = suns[5] - suns[4];
		int Small = suns[6];
		int Medium = suns[7] - suns[6];
		int Large = suns[8] - suns[7];
		int Multi = suns[9];

		JTextPane NS = new JTextPane();
		NS.setEditable(false);
		NS.setText("Normal Stars");
		NS.setToolTipText("This is the chance out of 1000 that a random star will be a normal star.");
		this.NormalStars = new InnerSlider(0, 1000, 0, 1000);
		this.NormalStars.setPaintLabels(true);
		this.NormalStars.setValue(NormalStars);
		this.NormalStars.setMajorTickSpacing(100);
		this.NormalStars.setMajorTickSpacing(10);
		this.NormalStars.setPaintTicks(true);
		StarTypes.add(NS);
		StarTypes.add(this.NormalStars);

		JTextPane BD = new JTextPane();
		BD.setEditable(false);
		BD.setText("Brown Dwarf");
		BD.setToolTipText("This is the chance out of 1000 that a random star will be a brown dwarf.");
		this.BrownDwarf = new InnerSlider(0, 1000, 0, 1000);
		this.BrownDwarf.setPaintLabels(true);
		this.BrownDwarf.setValue(BrownDwarf);
		this.BrownDwarf.setMajorTickSpacing(100);
		this.BrownDwarf.setMajorTickSpacing(10);
		this.BrownDwarf.setPaintTicks(true);
		StarTypes.add(BD);
		StarTypes.add(this.BrownDwarf);

		JTextPane WD = new JTextPane();
		WD.setEditable(false);
		WD.setText("White Dwaf");
		WD.setToolTipText("This is the chance out of 1000 that a random star will be a white dwarf.");
		this.WhiteDwarf = new InnerSlider(0, 1000, 0, 1000);
		this.WhiteDwarf.setPaintLabels(true);
		this.WhiteDwarf.setValue(WhiteDwarf);
		this.WhiteDwarf.setMajorTickSpacing(100);
		this.WhiteDwarf.setMajorTickSpacing(10);
		this.WhiteDwarf.setPaintTicks(true);
		StarTypes.add(WD);
		StarTypes.add(this.WhiteDwarf);

		JTextPane Nu = new JTextPane();
		Nu.setEditable(false);
		Nu.setText("Neutron Stars");
		Nu.setToolTipText("This is the chance out of 1000 that a random star will be a neutron star.");
		this.NeutronStar = new InnerSlider(0, 1000, 0, 1000);
		this.NeutronStar.setPaintLabels(true);
		this.NeutronStar.setValue(NeutronStar);
		this.NeutronStar.setMajorTickSpacing(100);
		this.NeutronStar.setMajorTickSpacing(10);
		this.NeutronStar.setPaintTicks(true);
		StarTypes.add(Nu);
		StarTypes.add(this.NeutronStar);

		JTextPane BH = new JTextPane();
		BH.setEditable(false);
		BH.setText("Black Hole");
		BH.setToolTipText("This is the chance out of 1000 that a random star will be a Black Hole.");
		this.BlackHole = new InnerSlider(0, 1000, 0, 1000);
		this.BlackHole.setPaintLabels(true);
		this.BlackHole.setValue(BlackHole);
		this.BlackHole.setMajorTickSpacing(100);
		this.BlackHole.setMajorTickSpacing(10);
		this.BlackHole.setPaintTicks(true);
		StarTypes.add(BH);
		StarTypes.add(this.BlackHole);

		JTextPane Neb = new JTextPane();
		Neb.setEditable(false);
		Neb.setText("Nebula");
		Neb.setToolTipText("This is the chance out of 1000 that a random star will be a Nebula.");
		this.Nebula = new InnerSlider(0, 1000, 0, 1000);
		this.Nebula.setPaintLabels(true);
		this.Nebula.setValue(Nebula);
		this.Nebula.setMajorTickSpacing(100);
		this.Nebula.setMajorTickSpacing(10);
		this.Nebula.setPaintTicks(true);
		StarTypes.add(Neb);
		StarTypes.add(this.Nebula);

		JTextPane Sma = new JTextPane();
		Sma.setEditable(false);
		Sma.setText("Small Stars");
		Sma.setToolTipText("This is the chance out of 100 that a normal star will be a small star.");
		this.Small = new InnerSlider(0, 100, 0, 100);
		this.Small.setPaintLabels(true);
		this.Small.setValue(Small);
		this.Small.setMajorTickSpacing(10);
		this.Small.setMajorTickSpacing(1);
		this.Small.setPaintTicks(true);
		StarTypes.add(Sma);
		StarTypes.add(this.Small);

		JTextPane Med = new JTextPane();
		Med.setEditable(false);
		Med.setText("Medium Stars");
		Med.setToolTipText("This is the chance out of 100 that a normal star will be a medium star.");
		this.Medium = new InnerSlider(0, 100, 0, 100);
		this.Medium.setPaintLabels(true);
		this.Medium.setValue(Medium);
		this.Medium.setMajorTickSpacing(10);
		this.Medium.setMajorTickSpacing(1);
		this.Medium.setPaintTicks(true);
		StarTypes.add(Med);
		StarTypes.add(this.Medium);

		JTextPane Lar = new JTextPane();
		Lar.setEditable(false);
		Lar.setText("Large Stars");
		Lar.setToolTipText("This is the chance out of 100 that a normal star will be a large star.");
		this.Large = new InnerSlider(0, 100, 0, 100);
		this.Large.setPaintLabels(true);
		this.Large.setValue(Large);
		this.Large.setMajorTickSpacing(10);
		this.Large.setMajorTickSpacing(1);
		this.Large.setPaintTicks(true);
		StarTypes.add(Lar);
		StarTypes.add(this.Large);

		JTextPane mul = new JTextPane();
		mul.setEditable(false);
		mul.setText("Multi Stars System");
		mul.setToolTipText("This is the chance out of 1000 that Multistar System.");
		this.Multi = new InnerSlider(0, 1000, 0, 1000);
		this.Multi.setPaintLabels(true);
		this.Multi.setValue(Multi);
		this.Multi.setMajorTickSpacing(100);
		this.Multi.setMajorTickSpacing(10);
		this.Multi.setPaintTicks(true);
		StarTypes.add(mul);
		StarTypes.add(this.Multi);

		JButton Proccess = new JButton("Accept");
		Proccess.addActionListener(new UpdateStarNumbers(this, StarTypes));
		StarTypes.add(Proccess);

		StarTypes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		StarTypes.setSize(800, 500);
		StarTypes.setVisible(true);
	}

	public int reCalculateStar(int i) {
		if (NormalStars == null || BrownDwarf == null || WhiteDwarf == null || NeutronStar == null || BlackHole == null
				|| Nebula == null || Small == null || Medium == null || Large == null) {
			switch (i) {
			case 0:
				return suns[0];
			case 1:
				return suns[1] - suns[0];
			case 2:
				return suns[2] - suns[1];
			case 3:
				return suns[3] - suns[2];
			case 4:
				return suns[4] - suns[3];
			case 5:
				return suns[5] - suns[4];
			case 6:
				return suns[6];
			case 7:
				return suns[7] - suns[6];
			case 8:
				return suns[8] - suns[7];
			}
		}
		switch (i) {
		case 0:
			return 1000 - BrownDwarf.getValue() - WhiteDwarf.getValue() - NeutronStar.getValue() - BlackHole.getValue()
					- Nebula.getValue();
		case 1:
			return 1000 - NormalStars.getValue() - WhiteDwarf.getValue() - NeutronStar.getValue() - BlackHole.getValue()
					- Nebula.getValue();
		case 2:
			return 1000 - NormalStars.getValue() - BrownDwarf.getValue() - NeutronStar.getValue() - BlackHole.getValue()
					- Nebula.getValue();
		case 3:
			return 1000 - NormalStars.getValue() - BrownDwarf.getValue() - WhiteDwarf.getValue() - BlackHole.getValue()
					- Nebula.getValue();
		case 4:
			return 1000 - NormalStars.getValue() - BrownDwarf.getValue() - WhiteDwarf.getValue()
					- NeutronStar.getValue() - Nebula.getValue();
		case 5:
			return 1000 - NormalStars.getValue() - BrownDwarf.getValue() - WhiteDwarf.getValue()
					- NeutronStar.getValue() - BlackHole.getValue();
		case 6:
			return 100 - Medium.getValue() - Large.getValue();
		case 7:
			return 100 - Small.getValue() - Large.getValue();
		case 8:
			return 100 - Small.getValue() - Medium.getValue();
		}
		return -1;
	}

	public void setSuns() {
		suns[0] = NormalStars.getValue();
		suns[1] = BrownDwarf.getValue() + suns[0];
		suns[2] = WhiteDwarf.getValue() + suns[1];
		suns[3] = NeutronStar.getValue() + suns[2];
		suns[4] = BlackHole.getValue() + suns[3];
		suns[5] = Nebula.getValue() + suns[4];
		suns[6] = Small.getValue();
		suns[7] = Medium.getValue() + suns[6];
		suns[8] = Large.getValue() + suns[7];
		suns[9] = Multi.getValue();
	}
}
