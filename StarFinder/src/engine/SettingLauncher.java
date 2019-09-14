package engine;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextPane;

import actions.LaunchSettings;
import map.MapView;

public class SettingLauncher {

	public SettingLauncher(boolean b) {
		JFrame LaunchSettings = new JFrame("Launch Settings");
		LaunchSettings.setLayout(new GridLayout(11,3));
		
		JTextPane sector = new JTextPane();
		sector.setEditable(false);
		sector.setText("Number of Sectors");
		sector.setToolTipText("This is the number of sectors the galaxy will have, larger values will lead to longer generation times.");
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
		regionmin.setToolTipText("This is the minimum number of regions per sector, larger values will lead to longer generation times.");
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
		regionmax.setToolTipText("This is the max value to be randomly added to each sector lower values less random size, larger values will lead to longer generation times.");
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
		zonemin.setToolTipText("This is the minimum number of zones per region, larger values will lead to longer generation times.");
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
		zonemax.setToolTipText("This is the max value to be zones added to each region lower values less random size, larger values will lead to longer generation times.");
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
		systemmin.setToolTipText("This is the minimum number of systems per zone, larger values will lead to longer generation times.");
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
		systemmax.setToolTipText("This is the max value to be randomly added to each zone lower values less random size, larger values will lead to longer generation times.");
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
		Planets.setToolTipText("This is the maximum number of planets each solar system can have, this does not include belts, larger values will lead to longer generation times.");
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
		
		JCheckBox MultiStars = new JCheckBox("Multi Stars");
		MultiStars.setSelected(false);
		MultiStars.setToolTipText("This allows Binary, and Trinary stars");
		MultiStars.setEnabled(false);
		LaunchSettings.add(MultiStars);
		
		JCheckBox Names = new JCheckBox("Random Names");
		Names.setSelected(false);
		Names.setToolTipText("This will generate random names rather than leaving it in a serial form.");
		Names.setEnabled(true);
		LaunchSettings.add(Names);
		
		JCheckBox Relays = new JCheckBox("Generate Relay Network");
		Names.setSelected(false);
		Names.setToolTipText("This will generate a Relay network at the launch.");
		Names.setEnabled(true);
		LaunchSettings.add(Relays);
		
		JButton Make = new JButton("Make");
		Make.addActionListener(new LaunchSettings(LaunchSettings,b,Sectors,RegionsMin,RegionsMax,
				ZonesMin,ZonesMax,SystemsMin,SystemsMax,PlanetsNum,
				SpecialStars,MultiStars,Names,Relays));
		LaunchSettings.add(Make);
		
		LaunchSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		LaunchSettings.setSize(600,600);
		LaunchSettings.setVisible(true);
	}
}
