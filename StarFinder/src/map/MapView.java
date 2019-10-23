package map;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import actions.MoonView;
import actions.NewRegion;
import actions.NewSystem;
import actions.NewZone;
import actions.PlanetView;
import actions.PrimeJump;
import actions.RegionPanel;
import actions.SatilightView;
import actions.SecondaryJump;
import actions.SectorPanel;
import actions.StarView;
import actions.SurfaceView;
import actions.SystemPanel;
import actions.ZonePanel;
import actions.back;
import actions.editmenu;
import actions.exit;
import actions.saver;
import actions.viewWorldData;
import astronomy.Asteroid;
import astronomy.AstroObject;
import astronomy.Belt;
import astronomy.Galaxy;
import astronomy.Habitable;
import astronomy.HabitableMoon;
import astronomy.Jovian;
import astronomy.Moon;
import astronomy.OrbitObject;
import astronomy.Planet;
import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Star;
import astronomy.Terrestrial;
import astronomy.Zone;
import engine.ObjectFiles;
import planetary.Colony;
import planetary.Condition;
import planetary.Ecosystem;
import planetary.SepcialDevelopments;
import relay.PrimaryRelay;
import relay.RelayNetwork;
import relay.SecondaryRelay;
import units.SI;
import units.sci;
import units.sitime;

public class MapView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3083279752317354841L;

	private static final int TYPEVALUE = 6;
	private static final int SPVALUE = 5;
	private static final int POSITIONVALUE = 4;
	private static final int SYSTEMVALUE = 3;
	private static final int ZONEVALUE = 2;
	private static final int REGIONVALUE = 1;
	private static final int SECTORVALUE = 0;
	
	private JPanel myView;
	private JMenu[] myMenus;
	private JMenuItem Name;

	private Sector[] mySectors;

	private Sector lasts = null;
	private Region lastr = null;
	private Zone lastz = null;
	private SolSystem lastss = null;
	private Planet lastp = null;
	private int level = -3;
	
	private Galaxy galaxy;

	public SettingList mySettings;

	public MapView(String name, int s, int rS, int rE, int zS, int zE, int sS, int sE, int p, boolean ss, boolean ms, boolean n, int[] suns, boolean r){
		super(name);
		mySettings = new SettingList(s,rS,rE,zS,zE,sS,sE,p,ss,ms,n,suns);
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);

		JMenuBar bar = new JMenuBar();
		myMenus = new JMenu[s];
		mySectors = new Sector[s];
		for(int i = 0;i < myMenus.length;i++) {
			myMenus[i] = new JMenu();
			mySectors[i] = Sector.makeRandom(mySettings);
			if(!n)mySectors[i].setName(""+i);
			myMenus[i] = (populateSectorMenu(mySectors[i], this));
			bar.add(myMenus[i]);
		}

		galaxy = new Galaxy(mySectors);
		for(int i = 0;i < mySectors.length;i++) {
			mySectors[i].setMyGalaxy(galaxy);
		}

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new saver(this, galaxy));
		bar.add(save);
		
		Name = new JMenuItem(galaxy.getMyName());
		bar.add(Name);
		
		JMenuItem editor = new JMenuItem("Editor");
		editor.addActionListener(new editmenu(this));
		bar.add(editor);

		JMenuItem back = new JMenuItem("Back");
		back.addActionListener(new back(this));
		bar.add(back);

		this.setJMenuBar(bar);
		myView = new JPanel();
		myView.setBackground(Color.BLACK);
		JScrollPane sp = new JScrollPane(myView);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		if(r) {
			galaxy.setMyNetwork(new RelayNetwork(galaxy, 0));
		}
		
		this.viewGalaxy();
		
		this.setSize(1000, 750);
		this.add(sp);
		this.addWindowListener(new exit(this));
		this.setVisible(true);
	}

	public MapView(String name,Galaxy g) {
		super(name);
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);

		JMenuBar bar = new JMenuBar();
		
		galaxy = g;
		int s = galaxy.getMySectors().length;
		
		myMenus = new JMenu[s];
		mySectors = new Sector[s];
		for(int i = 0;i < myMenus.length;i++) {
			myMenus[i] = new JMenu();
			mySectors[i] = galaxy.getMySectors()[i];
			myMenus[i] = (populateSectorMenu(mySectors[i], this));
			bar.add(myMenus[i]);
		}

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new saver(this, galaxy));
		bar.add(save);
		
		Name = new JMenuItem(galaxy.getMyName());
		bar.add(Name);
		
		JMenuItem editor = new JMenuItem("Editor");
		editor.addActionListener(new editmenu(this));
		bar.add(editor);

		JMenuItem back = new JMenuItem("Back");
		back.addActionListener(new back(this));
		bar.add(back);

		this.setJMenuBar(bar);
		myView = new JPanel();
		myView.setBackground(Color.BLACK);
		JScrollPane sp = new JScrollPane(myView);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		this.viewGalaxy();
		
		this.setSize(1000, 750);
		this.add(sp);
		this.addWindowListener(new exit(this));
		this.setVisible(true);
	}

	public void review() {
		for(int i = 0;i < mySectors.length;i++) {
			Region[] r = mySectors[i].getRegions();
			for(int j = 0;j < r.length;j++) {
				populateRegionMenu(r[j],this);
			}
		}
	}
	
//	public void search(AstroObject o) {
//		switch(o.getClass()) {
//		case
//		}
//	}

	public static void save(MapView view,Galaxy galaxy) {
		Cursor c = view.getCursor();
		view.setCursor(new Cursor(Cursor.WAIT_CURSOR));;
		ObjectFiles.WriteObjecttoFile(galaxy, galaxy.getMyName());
		view.setCursor(c);

		//		int tracker = 1;
		//		Row row;
		//		Cell cell;
		//		for(int i = 0;i < mySectors.length;i++) {
		//			Region[] reg = mySectors[i].getRegions();
		//			for(int k = 0;k < reg.length;k++) {
		//				Zone[] zon = reg[k].getMyZones();
		//				for(int j = 0;j < zon.length;j++) {
		//					SolSystem[] sol = zon[j].getMySystems();
		//					for(int h = 0;h < sol.length;h++) {
		//						row = sheet.createRow(tracker++);
		//						cell = row.createCell(SECTORVALUE);
		//						cell.setCellValue(mySectors[i].getName());
		//						cell = row.createCell(REGIONVALUE);
		//						cell.setCellValue(reg[k].getName());
		//						cell = row.createCell(ZONEVALUE);
		//						cell.setCellValue(zon[j].getMyName());
		//						cell = row.createCell(SYSTEMVALUE);
		//						cell.setCellValue(sol[h].getMyName());
		//						cell = row.createCell(TYPEVALUE);
		//						cell.setCellValue("Star");
		//						AstroObject[] pla = sol[h].getMyObjects();
		//						for(int g = 0;g < pla.length;g++) {
		//							row = sheet.createRow(tracker++);
		//							cell = row.createCell(SECTORVALUE);
		//							cell.setCellValue(mySectors[i].getName());
		//							cell = row.createCell(REGIONVALUE);
		//							cell.setCellValue(reg[k].getName());
		//							cell = row.createCell(ZONEVALUE);
		//							cell.setCellValue(zon[j].getMyName());
		//							cell = row.createCell(SYSTEMVALUE);
		//							cell.setCellValue(sol[h].getMyName());
		//							cell = row.createCell(POSITIONVALUE);
		//							cell.setCellValue((char)(97+g));
		//							cell = row.createCell(TYPEVALUE);
		//							cell.setCellValue(pla[g].getClass().getName());
		//						}
		//					}
		//				}
		//			}
		//		}
		//		try {
		//			ExcelSystem.pushOut(mySave, "save");
		//		} catch (InvalidFormatException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		}
	}

	public static JMenu populateSectorMenu(Sector r, MapView myView) {
		JMenu region = new JMenu(r.getName());
		JMenuItem view = new JMenuItem("View");
		view.addActionListener(new SectorPanel(r, myView));
		region.add(view);
		for(int i = 0;r.getRegions() != null&&i < r.getRegions().length;i++) {
			JMenu z = MapView.populateRegionMenu(r.getRegions()[i], myView);
			region.add(z);
		}
		JMenu makeNew = new JMenu("Make New Region");
		JMenuItem randNew = new JMenuItem("Random Region");
		JMenuItem blankNew = new JMenuItem("Blank Region");
		randNew.addActionListener(new NewRegion(true,r,region,myView));
		blankNew.addActionListener(new NewRegion(false,r,region,myView));
		makeNew.add(blankNew);
		makeNew.add(randNew);
		region.add(makeNew);
		return region;
	}

	public static JMenu populateRegionMenu(Region r, MapView myView) {
		JMenu region = new JMenu(r.getName());
		JMenuItem view = new JMenuItem("View");
		view.addActionListener(new RegionPanel(r, myView));
		region.add(view);
		for(int i = 0;r.getMyZones() != null&&i < r.getMyZones().length;i++) {
			JMenu z = MapView.populateZoneMenu(r.getMyZones()[i], myView);
			region.add(z);
		}
		JMenu makeNew = new JMenu("Make New Zone");
		JMenuItem randNew = new JMenuItem("Random Zone");
		JMenuItem blankNew = new JMenuItem("Blank Zone");
		randNew.addActionListener(new NewZone(true,r,region,myView));
		blankNew.addActionListener(new NewZone(false,r,region,myView));
		makeNew.add(blankNew);
		makeNew.add(randNew);
		region.add(makeNew);
		return region;
	}

	public static JMenu populateZoneMenu(Zone z, MapView myView) {
		JMenu zone = new JMenu(z.getMyName());
		JMenuItem view = new JMenuItem("View");
		view.addActionListener(new ZonePanel(z, myView));
		zone.add(view);
		for(int i = 0;z.getMySystems() != null&&i < z.getMySystems().length;i++) {
			JMenuItem s = populateSystemMenu(z.getMySystems()[i],myView);
			zone.add(s);
		}
		JMenu makeNew = new JMenu("Make New System");
		JMenuItem randNew = new JMenuItem("Random System");
		JMenuItem blankNew = new JMenuItem("Blank System");
		randNew.addActionListener(new NewSystem(true,z,zone,myView));
		blankNew.addActionListener(new NewSystem(false,z,zone,myView));
		makeNew.add(randNew);
		zone.add(makeNew);
		return zone;
	}

	public static JMenuItem populateSystemMenu(SolSystem s, MapView myView) {
		JMenuItem out = new JMenuItem(s.getMyName());
		out.addActionListener(new SystemPanel(s,myView));
		return out;
	}

	public void viewSector(Sector sector) {
		level = -3;
		Name.setText(sector.getName());
		lastp = null;
		lastss = null;
		lastz = null;
		lastr = null;
		lasts = sector;
		myView.removeAll();
		myView.repaint();
		int square = (int) Math.ceil(Math.pow(sector.getRegions().length,0.5));
		myView.setLayout(new GridLayout(square,square));

		JButton zone;
		for(int i = 0;i < sector.getRegions().length;i++) {
			zone = new JButton(sector.getRegions()[i].getName());
			zone.setPreferredSize(new Dimension(100, 100));
			zone.addActionListener(new RegionPanel(sector.getRegions()[i],this));
			myView.add(zone);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewRegion(Region region) {
		level = -2;
		Name.setText(region.getName());
		lastp = null;
		lastss = null;
		lastz = null;
		lastr = region;
		lasts = lastr.getMySector();
		myView.removeAll();
		myView.repaint();
		int square = (int) Math.ceil(Math.pow(region.getMyZones().length,0.5));
		myView.setLayout(new GridLayout(square,square));

		JButton zone;
		for(int i = 0;i < region.getMyZones().length;i++) {
			zone = new JButton(region.getMyZones()[i].getMyName());
			zone.setPreferredSize(new Dimension(100, 100));
			zone.addActionListener(new ZonePanel(region.getMyZones()[i],this));
			myView.add(zone);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewZone(Zone zone) {
		level = -1;
		Name.setText(zone.getMyName());
		lastp = null;
		lastss = null;
		lastz = zone;
		lastr = zone.getMyRegion();
		lasts = lastr.getMySector();
		myView.removeAll();
		myView.repaint();
		int square = (int) Math.ceil(Math.pow(zone.getMySystems().length,0.5));
		myView.setLayout(new GridLayout(square,square));
		
		JButton solarsystem;
		for(int i = 0;i < zone.getMySystems().length;i++) {
			solarsystem = new JButton(zone.getMySystems()[i].getMyName());
			ImageIcon img = zone.getMySystems()[i].getMyStar().getIcon();
			solarsystem.setIcon(img);
			solarsystem.setPreferredSize(new Dimension(img.getIconWidth()+50,img.getIconHeight()));
			solarsystem.setBackground(Color.BLACK);
			solarsystem.setBorderPainted(false);
			solarsystem.setOpaque(false);
			solarsystem.setForeground(Color.WHITE);
			solarsystem.addActionListener(new SystemPanel(zone.getMySystems()[i],this));
			myView.add(solarsystem);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewSystem(SolSystem solsystem) {
		level = 0;
		Name.setText(solsystem.getMyName());
		lastp = null;
		lastss = solsystem;
		lastz = solsystem.getMyZone();
		lastr = lastz.getMyRegion();
		lasts = lastr.getMySector();
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());

		JButton star = new JButton();
		ImageIcon img = solsystem.getMyStar().getIcon();
		star.setIcon(img);
		star.setPreferredSize(new Dimension(img.getIconWidth(),img.getIconHeight()));
		star.setBackground(Color.BLACK);
		star.setBorderPainted(false);
		star.setOpaque(false);
		star.addActionListener(new StarView(solsystem.getMyStar(), this));
		myView.add(star);

		JButton planet;
		for(int i = 0;i < solsystem.getMyObjects().length;i++) {
			if(solsystem.getMyObjects()[i].getClass() != Belt.class) {
				planet = new JButton(solsystem.getMyObjects()[i].getMyName());
			}else {
				planet = new JButton(""+solsystem.getMyObjects()[i].getMyName().charAt(0));				
			}
			planet.addActionListener(new PlanetView(solsystem.getMyObjects()[i],this));
			ImageIcon plimg = solsystem.getMyObjects()[i].getIcon();
			planet.setIcon(plimg);
			planet.setPreferredSize(new Dimension(plimg.getIconWidth()+50,plimg.getIconHeight()));
			planet.setForeground(Color.WHITE);
			planet.setBackground(Color.BLACK);
			planet.setBorderPainted(false);
			planet.setOpaque(false);
			
//			planet.setToolTipText("<html><p width=\"500\">" + solsystem.getMyObjects()[i].string() + "</p></html>");
//			Color c;
//			if(solsystem.getMyObjects()[i].getClass()==Jovian.class) {
//				if(solsystem.getMyObjects()[i].getMyMass().greaterOrEqual(AstroObject.JOVIAN.scale(0.5))) {
//					c = Color.yellow;
//				}else {
//					c = Color.cyan;
//				}
//			}else if(solsystem.getMyObjects()[i].getClass()==Habitable.class) {
//				if(((Habitable) solsystem.getMyObjects()[i]).getMyWater()<=0.4) {
//					c = Color.green;
//				}else {
//					c = Color.blue;
//				}
//			}else if(solsystem.getMyObjects()[i].getClass()==Terrestrial.class){
//				if(((Terrestrial)solsystem.getMyObjects()[i]).isFrozen()) {
//					c = Color.white;
//				}else {
//					c = Color.LIGHT_GRAY;
//				}
//			}else {
//				c = Color.DARK_GRAY;
//				planet.setForeground(Color.white);
//			}
//			planet.setBackground(c);
			myView.add(planet);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewStar(Star star) {
		level = 1;
		Name.setText(star.getMySystem().getMyName());
		myView.removeAll();
		myView.setLayout(new FlowLayout());

		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = star.getMySystem().getMyName() + "\n";
		display += "Inner Habitable Zone " + sci.round(sci.convertToDouble(star.getHabitablezone()[0].getValue())/sci.convertToDouble(AstroObject.AU.getValue()),4) + " AU\n";
		display += "Outer Habitable Zone " + sci.round(sci.convertToDouble(star.getHabitablezone()[1].getValue())/sci.convertToDouble(AstroObject.AU.getValue()),4) + " AU\n";
		display += "Frost Line " + sci.round(sci.convertToDouble(star.getFrostLine().getValue())/sci.convertToDouble(AstroObject.AU.getValue()),4) + " AU\n";
		display += "Color " + star.getMyColor().toString() + "\n";
		display += "Radius " + sci.round(sci.convertToDouble(star.getMyRadius().getValue())/sci.convertToDouble(AstroObject.SOLRADI.getValue()),4) + " Sols\n";
		display += "Brightness "+ sci.round(sci.convertToDouble(star.getMyLuminosity().getValue())/sci.convertToDouble(AstroObject.SUNLIGHT.getValue()),4) + " Sols\n";
		display += "Mass " + sci.round(sci.convertToDouble(star.getMyMass().getValue())/sci.convertToDouble(AstroObject.SOL.getValue()),4) + " Sols\n";
		display += "Temperature " + sci.round(sci.convertToDouble(star.getMyTemp().getValue())/sci.convertToDouble(AstroObject.SOLTEMP.getValue()),4) + " Sols";
		Print.setText(display);

		myView.add(Print);

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewPlanet(Planet planet) {
		level = 1;
		Name.setText(planet.getMyName());
		lastp = planet;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());

		JButton main = new JButton(planet.getMyName());
		ImageIcon plimg = planet.getIcon();
		main.setIcon(plimg);
		main.setPreferredSize(new Dimension(plimg.getIconWidth()+50,plimg.getIconHeight()));
		main.setForeground(Color.WHITE);
		main.setBackground(Color.BLACK);
		main.setBorderPainted(false);
		main.setOpaque(false);
		main.addActionListener(new viewWorldData(planet,this));
		if(planet.getClass() != Belt.class)
			myView.add(main);
		JButton moon;
		
		for(int i = 0;i < planet.getMyMoons().length;i++) {
			moon = new JButton(planet.getMyMoons()[i].getMyName());
			ImageIcon moimg = planet.getMyMoons()[i].getIcon();
			moon.setIcon(moimg);
			moon.setPreferredSize(new Dimension(moimg.getIconWidth()+50,moimg.getIconHeight()));
			moon.setForeground(Color.WHITE);
			moon.setBackground(Color.BLACK);
			moon.setBorderPainted(false);
			moon.setOpaque(false);
			moon.addActionListener(new MoonView((Moon) planet.getMyMoons()[i],this));

			myView.add(moon);
		}

		for(int i = 0;i < planet.getMySatilights().size();i++) {
			moon = new JButton(planet.getMySatilights().get(i).getMyName());
			ImageIcon moimg = planet.getMySatilights().get(i).getIcon();
			moimg = new ImageIcon(moimg.getImage().getScaledInstance(25, 25, 100));
			moon.setIcon(moimg);
			moon.setPreferredSize(new Dimension(moimg.getIconWidth()+50,moimg.getIconHeight()));
			moon.setForeground(Color.WHITE);
			moon.setBackground(Color.BLACK);
			moon.setBorderPainted(false);
			moon.setOpaque(false);
			if(planet.getMySatilights().get(i).getClass()==Asteroid.class) {
				moon.addActionListener(new MoonView((Moon) planet.getMySatilights().get(i),this));
			}else {
				moon.addActionListener(new SatilightView(planet.getMySatilights().get(i),this));
			}
			
			myView.add(moon);
		}
		
		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);


	}

	public void viewWorldData(Planet planet) {
		level = 2;
		Name.setText(planet.getMyName());
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = planet.getMyName() + "\n";

		if(planet.getClass() == Jovian.class) {
			display += "Radius " + planet.getMyRadius().compair(AstroObject.JOVIANRADI) + " Jupiters\n";
			display += "Mass " + planet.getMyMass().compair(AstroObject.JOVIAN) + " Jupiters\n";
		}else if(planet.getClass() == Habitable.class){
			Habitable world = (Habitable) planet;
			display += "Radius " + world.getMyRadius().compair(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass().compair(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere().compair(AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			display += "This world is habitable\n";
		}else if(planet.getClass() == HabitableMoon.class){
			HabitableMoon world = (HabitableMoon) planet;
			display += "Radius " + world.getMyRadius().compair(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass().compair(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere().compair(AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			display += "This world is habitable\n";
			Moon moon = (Moon) planet;
			display += "Month " + moon.getMyMonth().compair(AstroObject.MONTH) + " months\n";
			display += "Distant to world " + moon
					.getMyMoonOrbit()
			.compair(AstroObject.LIGHTSECOND) + " light seconds\n";
		}else {
			Terrestrial world = (Terrestrial) planet;
			display += "Radius " + world.getMyRadius().compair(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass().compair(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere().compair(AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";	
			if(planet.getClass() == Moon.class) {
				Moon moon = (Moon) planet;
				display += "Month " + moon.getMyMonth().compair(AstroObject.MONTH) + " months\n";
				display += "Distant to world " + moon
						.getMyMoonOrbit()
				.compair(AstroObject.LIGHTSECOND) + " light seconds\n";
			}
		}

		//Add year data
		if(planet.getMyYear().compair(AstroObject.YEAR.scale(3.0))>1.0) {
			display += "Year " + planet.getMyYear().compair(AstroObject.YEAR) + " years\n";
		}else if(planet.getMyYear().compair(AstroObject.MONTH.scale(3.0))>1.0) {
			display += "Year " + planet.getMyYear().compair(AstroObject.MONTH) + " months\n";
		}else {
			display += "Year " + planet.getMyYear().compair(AstroObject.DAY) + " days\n";
		}

		//Add day data
		if(planet.getMyDay().compair(AstroObject.YEAR.scale(3.0))>1.0) {
			display += "Day " + planet.getMyDay().compair(AstroObject.YEAR) + " years\n";
		}else if(planet.getMyDay().compair(AstroObject.MONTH.scale(3.0))>1.0) {
			display += "Day " + planet.getMyDay().compair(AstroObject.MONTH) + " months\n";
		}else if(planet.getMyDay().compair(AstroObject.DAY.scale(3.0))>1.0) {
			display += "Day " + planet.getMyDay().compair(AstroObject.DAY) + " days\n";
		}else if(planet.getMyDay().compair(AstroObject.HOUR.scale(3.0))>1.0) {
			display += "Day " + planet.getMyDay().compair(AstroObject.HOUR) + " hours\n";			
		}else {
			display += "Day " + planet.getMyDay().compair(new sitime(60,SI.BASE)) + " minutes\n";			
		}

		display += "Gravity " + planet.getMyGravity().compair(AstroObject.GRAVITYEARTH) + " G's\n";

		//Add orbit data
		display += "Average Orbit " + planet.getMyOrbit().compair(AstroObject.AU) + " AU\n";
		display += "Inner Orbit " + planet.getMyInnerOrbit().compair(AstroObject.AU) + " AU\n";
		display += "Outer Orbit " + planet.getMyOuterOrbit().compair(AstroObject.AU) + " AU";

		JButton Look = new JButton("Surface");
		Look.addActionListener(new SurfaceView(planet, this));
		myView.add(Look);
		
		Print.setText(display);
		myView.add(Print);

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void backup() {
		if(level == -3) {
			viewGalaxy();
		}else if(level == -2) {
			viewSector(lasts);
		}else if(level == -1) {
			viewRegion(lastr);
		}else if(level == 0) {
			viewZone(lastz);
		}else if(level == 1) {
			viewSystem(lastss);
		}else if(level == 2) {
			viewPlanet(lastp);
		}else if(level == 3) {
			viewWorldData(lastp);
		}
	}

	private void viewGalaxy() {
		level = -3;
		Name.setText(galaxy.getMyName());
		JButton Sector;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new GridLayout(2,2));

		for(int i = 0;i < myMenus.length;i++) {
			Sector = new JButton(mySectors[i].getName());
			Sector.setPreferredSize(new Dimension(100, 100));
			Sector.addActionListener(new SectorPanel(mySectors[i],this));
			myView.add(Sector);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewPrimaryRelay(PrimaryRelay o) {
		level = 2;
		Name.setText(o.getMyName());
		this.lastp = o.getMyWorld();
		this.lastss = o.getMySystem();
		this.lastz = lastss.getMyZone();
		this.lastr = lastz.getMyRegion();
		this.lasts = lastr.getMySector();
		
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = o.getMyName();

		JButton Look = new JButton("Jump To Partner");
		Look.addActionListener(new PrimeJump(o.getMyPartner(), this));
		myView.add(Look);
		
		Print.setText(display);
		myView.add(Print);

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewSecondaryRelay(SecondaryRelay o) {
		level = 2;
		Name.setText(o.getMyName());
		this.lastp = o.getMyWorld();
		this.lastss = o.getMySystem();
		this.lastz = lastss.getMyZone();
		this.lastr = lastz.getMyRegion();
		this.lasts = lastr.getMySector();
		
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = o.getMyName();

		SecondaryRelay[] pod = o.getPod();
		for(int i = 0;i < pod.length;i++) {
			if(pod[i] != o) {
				JButton Look = new JButton("Jump to the "+pod[i].getMyName());
				Look.addActionListener(new SecondaryJump(pod[i],this));
				myView.add(Look);
			}
		}
		
		Print.setText(display);
		myView.add(Print);

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public void viewSurface(Planet planet) {
		Colony colony = planet.getMyColony();
		level = 3;
		Name.setText(planet.getMyName());
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = planet.getMyName() + "\n";
		display += "Habitable: " + colony.isHab() + "\n";
		display += "Water Present: " + colony.isHasWater() + "\n";
		display += "Biosphere Present: " + colony.isHasBio() + "\n";
		display += "Ezero Present: " + colony.isHasEzo() + "\n";
		display += "Massive Metal Presence: " + colony.isHasMassiveMetal() + "\n";
		display += "Massive Gasses Presence: " + colony.isHasMassiveGasses() + "\n";
		display += "Radiotropics Present: " + colony.isHasRadiotropes() + "\n";
		display += "Rare Metals Present: " + colony.isHasRareMetals() + "\n";
		display += "Rare Gasses Present: " + colony.isHasRareGasses() + "\n";
		display += "\n";
		display += "Has a colony " + (colony.getSize()>0) + "\n";
		if(colony.getSize()>0) {
			display += "Colony Size: " + colony.getSize() + "\n";
			display += "Colony Growth: " + colony.getScale()  + "\n";
		}
		
		Condition c = null;
		if(planet.getClass()==Habitable.class) {
			c = ((Habitable)planet).getMyCondition();
		}else if(planet.getClass()==HabitableMoon.class) {
			c = ((HabitableMoon)planet).getMyCondition();
		}
		
		if(c!=null) {
			display += "------------------------------\n";
			display += "Gravity Index "+c.getGravityIndex()+"\n";
			display += "Tempiture Index "+c.getTempitureIndex()+"\n";
			display += "Tempiture Variations "+c.getVarianceIndex()+"\n";
			display += "Pressure Index "+c.getAtmosphericIndex()+"\n";
			display += "Water Index "+c.getWaterIndex()+"\n";
			display += "Atmosphere Type ";
			switch(c.getAirIndex()) {
			case AMMONIA:
				display += "Ammonia";
				break;
			case METHANE:
				display += "Methane";
				break;
			default:
				display += "Nitrogen";
				break;
			}
			display += "\n";
			display += c.isDextros() ? "Dextro":"Levo";
			display += "-amino acid Biology";
		}
		
		
		Print.setText(display);
		myView.add(Print);

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}
	
	public Sector[] getMySectors() {
		return mySectors;
	}
	
	public JPanel getMyView() {
		return myView;
	}

	public void setMyView(JPanel myView) {
		this.myView = myView;
	}

	public JMenu[] getMyMenus() {
		return myMenus;
	}

	public void setMyMenus(JMenu[] myMenus) {
		this.myMenus = myMenus;
	}

//	public Workbook getMySave() {
//		return mySave;
//	}
//
//	public void setMySave(Workbook mySave) {
//		this.mySave = mySave;
//	}
//
//	public Sheet getSheet() {
//		return sheet;
//	}
//
//	public void setSheet(Sheet sheet) {
//		this.sheet = sheet;
//	}

	public Sector getLasts() {
		return lasts;
	}

	public void setLasts(Sector lasts) {
		this.lasts = lasts;
	}

	public Region getLastr() {
		return lastr;
	}

	public void setLastr(Region lastr) {
		this.lastr = lastr;
	}

	public Zone getLastz() {
		return lastz;
	}

	public void setLastz(Zone lastz) {
		this.lastz = lastz;
	}

	public SolSystem getLastss() {
		return lastss;
	}

	public void setLastss(SolSystem lastss) {
		this.lastss = lastss;
	}

	public Planet getLastp() {
		return lastp;
	}

	public void setLastp(Planet lastp) {
		this.lastp = lastp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Galaxy getGalaxy() {
		return galaxy;
	}

	public void setGalaxy(Galaxy galaxy) {
		this.galaxy = galaxy;
	}

	public void setMySectors(Sector[] mySectors) {
		this.mySectors = mySectors;
	}
}