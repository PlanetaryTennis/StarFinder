package map;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

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

import actions.AnimalView;
import actions.Biolook;
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
import astronomy.AstroObject;
import astronomy.Galaxy;
import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Asteroid;
import astronomy.planetary.Belt;
import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Jovian;
import astronomy.planetary.Moon;
import astronomy.planetary.Planet;
import astronomy.planetary.Terrestrial;
import astronomy.stellar.Star;
import engine.ObjectFiles;
import planetary.Colony;
import planetary.Condition;
import relay.PrimaryRelay;
import relay.RelayNetwork;
import relay.SecondaryRelay;

public class MapView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3083279752317354841L;

	private JPanel myView;
	private Vector<JMenu> myMenus;
	private JMenuItem Name;

	private Vector<Sector> mySectors;

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
		String nam = Sector.randomName();
		mySettings = new SettingList(nam,s,rS,rE,zS,zE,sS,sE,p,ss,ms,n,suns);
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);

		JMenuBar bar = new JMenuBar();
		myMenus = new Vector<JMenu>();
		mySectors = new Vector<Sector>();
		for(int i = 0;i < s;i++) {
			mySectors.add(Sector.makeRandom(mySettings));
			if(!n)mySectors.get(i).setName(""+i);
			myMenus.add(populateSectorMenu(mySectors.get(i), this));
			bar.add(myMenus.get(i));
		}

		galaxy = new Galaxy(mySectors);
		galaxy.setMyName(nam);
		for(int i = 0;i < mySectors.size();i++) {
			mySectors.get(i).setMyGalaxy(galaxy);
		}
		
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

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new saver(this, galaxy));
		bar.add(save);

		ObjectFiles.WriteSavabletoFile(mySettings, galaxy.getMyName());
		ObjectFiles.WriteSavabletoFile(galaxy, galaxy.getMyName());
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
		int s = galaxy.getMySectors().size();
		
		myMenus = new Vector<JMenu>();
		mySectors = galaxy.getMySectors();
		for(int i = 0;i < s;i++) {
			myMenus.add(populateSectorMenu(mySectors.get(i), this));
			bar.add(myMenus.get(i));
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
		

//		ObjectFiles.ReadSaveableFromFile(galaxy.getMyName()+"/Map.settings");
		this.viewGalaxy();
		
		this.setSize(1000, 750);
		this.add(sp);
		this.addWindowListener(new exit(this));
		this.setVisible(true);
	}

	public void review() {
		for(int i = 0;i < mySectors.size();i++) {
			Vector<Region> r = mySectors.get(i).getRegions();
			for(int j = 0;j < r.size();j++) {
				populateRegionMenu(r.get(j),this);
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
		view.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		ObjectFiles.WriteSavabletoFile(galaxy, galaxy.getMyName());
		if(galaxy.getMyNetwork()!=null)
			ObjectFiles.WriteSavabletoFile(galaxy.getMyNetwork(), galaxy.getMyName());
		if(view.lastss != null)
			ObjectFiles.WriteSavabletoFile(view.lastss, galaxy.getMyName());
		ObjectFiles.WriteSavabletoFile(view.mySettings, galaxy.getMyName());
		view.setCursor(c);
	}

	public static JMenu populateSectorMenu(Sector r, MapView myView) {
		JMenu region = new JMenu(r.getName());
		JMenuItem view = new JMenuItem("View");
		view.addActionListener(new SectorPanel(r, myView));
		region.add(view);
		for(int i = 0;r.getRegions() != null&&i < r.getRegions().size();i++) {
			JMenu z = MapView.populateRegionMenu(r.getRegions().get(i), myView);
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
		for(int i = 0;r.getMyZones() != null&&i < r.getMyZones().size();i++) {
			JMenu z = new JMenu(r.getMyZones().get(i).getMyName());
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
		int square = (int) Math.ceil(Math.pow(sector.getRegions().size(),0.5));
		myView.setLayout(new GridLayout(square,square));

		JButton zone;
		for(int i = 0;i < sector.getRegions().size();i++) {
			zone = new JButton(sector.getRegions().get(i).getName());
			zone.setPreferredSize(new Dimension(100, 100));
			zone.addActionListener(new RegionPanel(sector.getRegions().get(i),this));
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
		int square = (int) Math.ceil(Math.pow(region.getMyZones().size(),0.5));
		myView.setLayout(new GridLayout(square,square));

		JButton zone;
		for(int i = 0;i < region.getMyZones().size();i++) {
			zone = new JButton(region.getMyZones().get(i).getMyName());
			zone.setPreferredSize(new Dimension(100, 100));
			zone.addActionListener(new ZonePanel(region.getMyZones().get(i),this));
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
		int square = (int) Math.ceil(Math.pow(zone.getSystemNumber(),0.5));
		myView.setLayout(new GridLayout(square,square));
		boolean same;
		if(solStore.size()>0&&solStore.get(0).getID().contentEquals(zone.getSystemIDs().get(0))) {
			same = true;
		}else {
			same = false;
			solStore = new Vector<SolSystem>();
		}
		JButton solarsystem;
		SolSystem sol;
		for(int i = 0;i < zone.getSystemNumber();i++) {
			if(same) {
				sol = solStore.get(i);
			}else {
				sol = (SolSystem)ObjectFiles.ReadSaveableFromFile(lasts.getMyGalaxy().getMyName()+"/"+zone.getSystemIDs().get(i));
				solStore.add(sol);
			}
			solarsystem = new JButton(sol.getMyName());
			ImageIcon img = sol.getMyStar().getIcon();
			solarsystem.setIcon(img);
			solarsystem.setPreferredSize(new Dimension(img.getIconWidth()+50,img.getIconHeight()));
			solarsystem.setBackground(Color.BLACK);
			solarsystem.setBorderPainted(false);
			solarsystem.setOpaque(false);
			solarsystem.setForeground(Color.WHITE);
			solarsystem.addActionListener(new SystemPanel(sol,this));
			myView.add(solarsystem);
		}

		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}

	public Vector<SolSystem> solStore = new Vector<SolSystem>();
	
	public void viewSystem(SolSystem solsystem) {
		level = 0;
		Name.setText(solsystem.getMyName());
		lastp = null;
		lastss = solsystem;
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
		for(int i = 0;i < solsystem.getMyObjects().size();i++) {
			if(solsystem.getMyObjects().get(i).getClass() != Belt.class) {
				planet = new JButton(solsystem.getMyObjects().get(i).getMyName());
			}else {
				planet = new JButton(""+solsystem.getMyObjects().get(i).getMyName().charAt(0));				
			}
			planet.addActionListener(new PlanetView(solsystem.getMyObjects().get(i),this));
			ImageIcon plimg = solsystem.getMyObjects().get(i).getIcon();
			planet.setIcon(plimg);
			planet.setPreferredSize(new Dimension(plimg.getIconWidth()+50,plimg.getIconHeight()));
			planet.setForeground(Color.WHITE);
			planet.setBackground(Color.BLACK);
			planet.setBorderPainted(false);
			planet.setOpaque(false);
			
//			planet.setToolTipText("<html><p width=\"500\">" + solsystem.getMyObjects().get(i).string() + "</p></html>");
//			Color c;
//			if(solsystem.getMyObjects().get(i).getClass()==Jovian.class) {
//				if(solsystem.getMyObjects().get(i).getMyMass().greaterOrEqual(AstroObject.JOVIAN*(0.5))) {
//					c = Color.yellow;
//				}else {
//					c = Color.cyan;
//				}
//			}else if(solsystem.getMyObjects().get(i).getClass()==Habitable.class) {
//				if(((Habitable) solsystem.getMyObjects().get(i)).getMyWater()<=0.4) {
//					c = Color.green;
//				}else {
//					c = Color.blue;
//				}
//			}else if(solsystem.getMyObjects().get(i).getClass()==Terrestrial.class){
//				if(((Terrestrial)solsystem.getMyObjects().get(i)).isFrozen()) {
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
		display += "Inner Habitable Zone " + star.getHabitablezone()[0]/AstroObject.AU + " AU\n";
		display += "Outer Habitable Zone " + star.getHabitablezone()[1]/AstroObject.AU + " AU\n";
		display += "Frost Line " + star.getFrostLine()/AstroObject.AU + " AU\n";
		display += "Color " + star.getMyColor().toString() + "\n";
		display += "Radius " + star.getMyRadius()/AstroObject.SOLRADI + " Sols\n";
		display += "Brightness "+ star.getMyLuminosity()/AstroObject.SUNLIGHT + " Sols\n";
		display += "Mass " + star.getMyMass()/AstroObject.SOL + " Sols\n";
		display += "Temperature " + star.getMyTemp()/AstroObject.SOLTEMP + " Sols";
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
		
		for(int i = 0;i < planet.getMyMoons().size();i++) {
			moon = new JButton(planet.getMyMoons().get(i).getMyName());
			ImageIcon moimg = planet.getMyMoons().get(i).getIcon();
			moon.setIcon(moimg);
			moon.setPreferredSize(new Dimension(moimg.getIconWidth()+50,moimg.getIconHeight()));
			moon.setForeground(Color.WHITE);
			moon.setBackground(Color.BLACK);
			moon.setBorderPainted(false);
			moon.setOpaque(false);
			moon.addActionListener(new MoonView((Moon) planet.getMyMoons().get(i),this));

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
			display += "Radius " + planet.getMyRadius()/AstroObject.JOVIANRADI + " Jupiters\n";
			display += "Mass " + planet.getMyMass()/(AstroObject.JOVIAN) + " Jupiters\n";
		}else if(planet.getClass() == Habitable.class){
			Habitable world = (Habitable) planet;
			display += "Radius " + world.getMyRadius()/(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass()/(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere()/(AstroObject.BAR) + " Earths\n";
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
			display += "Radius " + world.getMyRadius()/(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass()/(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere()/(AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			display += "This world is habitable\n";
			Moon moon = (Moon) planet;
			display += "Month " + moon.getMyMonth()/(AstroObject.MONTH) + " months\n";
			display += "Distant to world " + moon
					.getMyMoonOrbit()
			/(AstroObject.LIGHTSECOND) + " light seconds\n";
		}else {
			Terrestrial world = (Terrestrial) planet;
			display += "Radius " + world.getMyRadius()/(AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass()/(AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater()*100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere()/(AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";	
			if(planet.getClass() == Moon.class) {
				Moon moon = (Moon) planet;
				display += "Month " + moon.getMyMonth()/(AstroObject.MONTH) + " months\n";
				display += "Distant to world " + moon
						.getMyMoonOrbit()
				/(AstroObject.LIGHTSECOND) + " light seconds\n";
			}
		}

		//Add year data
		if(planet.getMyYear()/(AstroObject.YEAR*(3.0))>1.0) {
			display += "Year " + planet.getMyYear()/(AstroObject.YEAR) + " years\n";
		}else if(planet.getMyYear()/(AstroObject.MONTH*(3.0))>1.0) {
			display += "Year " + planet.getMyYear()/(AstroObject.MONTH) + " months\n";
		}else {
			display += "Year " + planet.getMyYear()/(AstroObject.DAY) + " days\n";
		}

		//Add day data
		if(planet.getMyDay()/(AstroObject.YEAR*(3.0))>1.0) {
			display += "Day " + planet.getMyDay()/(AstroObject.YEAR) + " years\n";
		}else if(planet.getMyDay()/(AstroObject.MONTH*(3.0))>1.0) {
			display += "Day " + planet.getMyDay()/(AstroObject.MONTH) + " months\n";
		}else if(planet.getMyDay()/(AstroObject.DAY*(3.0))>1.0) {
			display += "Day " + planet.getMyDay()/(AstroObject.DAY) + " days\n";
		}else if(planet.getMyDay()/(AstroObject.HOUR*(3.0))>1.0) {
			display += "Day " + planet.getMyDay()/(AstroObject.HOUR) + " hours\n";			
		}else {
			display += "Day " + planet.getMyDay()/60 + " minutes\n";			
		}

		display += "Gravity " + planet.getMyGravity()/(AstroObject.GRAVITYEARTH) + " G's\n";

		//Add orbit data
		display += "Average Orbit " + planet.getMyOrbit()/(AstroObject.AU) + " AU\n";
		display += "Inner Orbit " + planet.getMyInnerOrbit()/(AstroObject.AU) + " AU\n";
		display += "Outer Orbit " + planet.getMyOuterOrbit()/(AstroObject.AU) + " AU";

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
		}else if(level == 4) {
			viewSurface(lastp);
		}
	}

	private void viewGalaxy() {
		level = -3;
		Name.setText(galaxy.getMyName());
		JButton Sector;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new GridLayout(2,2));

		for(int i = 0;i < myMenus.size();i++) {
			Sector = new JButton(mySectors.get(i).getName());
			Sector.setPreferredSize(new Dimension(100, 100));
			Sector.addActionListener(new SectorPanel(mySectors.get(i),this));
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
		this.lastz = o.getMyZone();
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
		this.lastz = o.getMyZone();
		this.lastr = lastz.getMyRegion();
		this.lasts = lastr.getMySector();
		
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		Print.setEditable(false);
		String display = o.getMyName();

		Vector<SecondaryRelay> pod = o.getMyPod();
		for(int i = 0;i < pod.size();i++) {
			if(pod.get(i) != o) {
				JButton Look = new JButton("Jump to the "+pod.get(i).getMyName());
				Look.addActionListener(new SecondaryJump(pod.get(i),this));
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

		if(colony.isHasBio()) {
			JButton ViewBio = new JButton("View Biosphere");
			ViewBio.addActionListener(new Biolook(colony,c,this));
			myView.add(ViewBio);
		}
		
		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}
	
	public void viewBiosphere(Colony col, Condition con) {
		level = 4;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		String display = "";
		display += "Gravity Index "+con.getGravityIndex()+"\n";
		display += "Tempiture Index "+con.getTempitureIndex()+"\n";
		display += "Tempiture Variations "+con.getVarianceIndex()+"\n";
		display += "Pressure Index "+con.getAtmosphericIndex()+"\n";
		display += "Water Index "+con.getWaterIndex()+"\n";
		display += "Atmosphere Type ";
		switch(con.getAirIndex()) {
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
		display += con.isDextros() ? "Dextro":"Levo";
		display += "-amino acid Biology";

		Print.setText(display);
		myView.add(Print);

		JButton Apex = new JButton("Apex Preditor");
		Apex.addActionListener(new AnimalView(col.getMyEcosystem().getApexPreditor()));
		myView.add(Apex);

		JButton Stand = new JButton("Main Animal");
		Stand.addActionListener(new AnimalView(col.getMyEcosystem().getStandard()));
		myView.add(Stand);

		JButton Pest = new JButton("Local Pest");
		Pest.addActionListener(new AnimalView(col.getMyEcosystem().getPest()));
		myView.add(Pest);
		
		this.setSize(this.getWidth()+1, this.getHeight()+1);
		this.setSize(this.getWidth()-1, this.getHeight()-1);
	}
	
	public Vector<Sector> getMySectors() {
		return mySectors;
	}
	
	public JPanel getMyView() {
		return myView;
	}

	public void setMyView(JPanel myView) {
		this.myView = myView;
	}

	public Vector<JMenu> getMyMenus() {
		return myMenus;
	}

	public void setMyMenus(Vector<JMenu> myMenus) {
		this.myMenus = myMenus;
	}

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

	public void setMySectors(Vector<Sector> mySectors) {
		this.mySectors = mySectors;
	}
}