package map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import actions.AnimalView;
import actions.Biolook;
import actions.Colonize;
import actions.ColonyViewer;
import actions.MoonView;
import actions.PlanetView;
import actions.PlantView;
import actions.Rename;
import actions.SatilightView;
import actions.StarView;
import actions.SurfaceView;
import actions.Systemexit;
import actions.loadSystem;
import actions.systemback;
import actions.viewWorldData;
import astronomy.AstroObject;
import astronomy.SolSystem;
import astronomy.planetary.Asteroid;
import astronomy.planetary.Belt;
import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Jovian;
import astronomy.planetary.Moon;
import astronomy.planetary.Planet;
import astronomy.planetary.Terrestrial;
import astronomy.stellar.MultiStar;
import astronomy.stellar.Nebula;
import astronomy.stellar.Star;
import engine.ObjectFiles;
import gate.ImageGate;
import gate.PrimaryGate;
import gate.SecondaryGate;
import planetary.Colony;
import planetary.Condition;

public class SystemView extends JFrame implements StarViewable, PlanetViewer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416318384934836618L;
	private String Path;
	private JPanel myView;
	private JMenuItem Name;
	
	private SolSystem system = null;
	private Planet lastp = null;
	private int level = 0;

	public SettingList mySettings;

	public SystemView(String name, SolSystem s, String path) {
		super(name);

		system = s;
		Path = path;
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.BLACK);

		JMenuBar bar = new JMenuBar();

		Name = new JMenuItem(system.getMyName());
		bar.add(Name);
		
		JMenuItem load = new JMenuItem("Load System");
		load.addActionListener(new loadSystem(this));
		bar.add(load);

		JMenuItem back = new JMenuItem("Back");
		back.addActionListener(new systemback(this));
		bar.add(back);

		this.setJMenuBar(bar);
		myView = new JPanel();
		myView.setBackground(Color.BLACK);
		JScrollPane sp = new JScrollPane(myView);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		this.viewSystem();

		this.setIconImage(system.getMyStar().getIcon().getImage());

		this.setSize(1000, 750);
		this.add(sp);
		this.addWindowListener(new Systemexit(this));
		this.setVisible(true);
	}

	public static void save(SystemView view) {
		Cursor c = view.getCursor();
		view.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		ObjectFiles.WriteSavabletoFileByCannon(view.getSystem(),view.Path);
		view.setCursor(c);
	}

	private SolSystem getSystem() {
		return system;
	}
	

	public void viewSystem() {
		level = 0;
		Name.setText(system.getMyName());
		lastp = null;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new BorderLayout());
		JPanel look = new JPanel();
		look.setLayout(new GridLayout((int) Math.ceil(Math.sqrt(system.getMyObjects().size())),
				(int) Math.ceil(Math.sqrt(system.getMyObjects().size()))));

		look.setBackground(Color.BLACK);

		if (system.getMyStar().getClass() != Nebula.class) {
			JButton star = new JButton();
			ImageIcon img = system.getMyStar().getIcon();
			star.setIcon(img);
			star.setPreferredSize(new Dimension(img.getIconWidth() + img.getIconWidth() / 4, img.getIconHeight()));
			star.setBackground(Color.BLACK);
			star.setBorderPainted(false);
			star.setOpaque(false);
			star.addActionListener(new StarView(system.getMyStar(), this));
			myView.add(star, BorderLayout.WEST);
		}

		JButton planet;
		for (int i = 0; i < system.getMyObjects().size(); i++) {
			if (system.getMyObjects().get(i).getClass() != Belt.class) {
				planet = new JButton(system.getMyObjects().get(i).getMyName());
			} else {
				planet = new JButton("" + system.getMyObjects().get(i).getMyName().charAt(0));
			}
			planet.addActionListener(new PlanetView(system.getMyObjects().get(i), this));
			ImageIcon plimg = system.getMyObjects().get(i).getIcon();
			planet.setIcon(plimg);
			planet.setPreferredSize(new Dimension(plimg.getIconWidth() + 50, plimg.getIconHeight()));
			planet.setForeground(Color.WHITE);
			planet.setBackground(Color.BLACK);
			planet.setBorderPainted(false);
			planet.setOpaque(false);
			look.add(planet);
		}

		myView.add(look, BorderLayout.CENTER);

		JButton rename = new JButton("Rename");
		rename.addActionListener(new Rename(system));
		myView.add(rename, BorderLayout.SOUTH);

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public void viewStar(Star star) {
		level = 1;
		Name.setText(star.getMyName());
		myView.removeAll();
		myView.setLayout(new BorderLayout());
		JPanel look = new JPanel();
		look.setLayout(new GridLayout(2, 2));

		look.setBackground(Color.BLACK);

		if (star.getClass() == MultiStar.class) {
			Vector<Star> Stars = ((MultiStar) star).getMyStars();
			JButton st = null;
			ImageIcon img;
			for (int i = 0; i < Stars.size(); i++) {
				st = new JButton(Stars.get(i).getMyName());
				img = Stars.get(i).getIcon();
				st.setIcon(img);
				st.setPreferredSize(new Dimension(img.getIconWidth() + img.getIconWidth() / 4, img.getIconHeight()));
				st.setBackground(Color.BLACK);
				st.setBorderPainted(false);
				st.setOpaque(false);
				st.addActionListener(new StarView(Stars.get(i), this));
				look.add(st);
			}
		} else {
			JTextArea Print = new JTextArea();
			Print.setEditable(false);
			String display = star.getMyName() + "\n";
			display += "Inner Habitable Zone " + star.getHabitablezone()[0] / AstroObject.AU + " AU\n";
			display += "Outer Habitable Zone " + star.getHabitablezone()[1] / AstroObject.AU + " AU\n";
			display += "Frost Line " + star.getFrostLine() / AstroObject.AU + " AU\n";
			display += "Color " + star.getMyColor().toString() + "\n";
			display += "Radius " + star.getMyRadius() / AstroObject.SOLRADI + " Sols\n";
			display += "Brightness " + star.getMyLuminosity() / AstroObject.SUNLIGHT + " Sols\n";
			display += "Mass " + star.getMyMass() / AstroObject.SOL + " Sols\n";
			display += "Temperature " + star.getMyTemp() / AstroObject.SOLTEMP + " Sols";
			Print.setText(display);

			look.add(Print);
		}

		myView.add(look, BorderLayout.CENTER);

		JButton rename = new JButton("Rename");
		rename.addActionListener(new Rename(star));
		myView.add(rename, BorderLayout.SOUTH);

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public void viewPlanet(Planet planet) {
		level = 1;
		Name.setText(planet.getMyName());
		lastp = planet;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new BorderLayout());
		JPanel look = new JPanel();
		look.setLayout(
				new GridLayout((int) Math.ceil(Math.sqrt(planet.getMyMoons().size() + planet.getMySatilights().size())),
						(int) Math.ceil(Math.sqrt(planet.getMyMoons().size() + planet.getMySatilights().size()))));

		look.setBackground(Color.BLACK);

		JButton main = new JButton(planet.getMyName());
		ImageIcon plimg = planet.getIcon();
		main.setIcon(plimg);
		main.setPreferredSize(new Dimension(plimg.getIconWidth() + 50, plimg.getIconHeight()));
		main.setForeground(Color.WHITE);
		main.setBackground(Color.BLACK);
		main.setBorderPainted(false);
		main.setOpaque(false);
		main.addActionListener(new viewWorldData(planet, this));
		if (planet.getClass() != Belt.class)
			myView.add(main, BorderLayout.WEST);
		JButton moon;

		for (int i = 0; i < planet.getMyMoons().size(); i++) {
			moon = new JButton(planet.getMyMoons().get(i).getMyName());
			ImageIcon moimg = planet.getMyMoons().get(i).getIcon();
			moon.setIcon(moimg);
			moon.setPreferredSize(new Dimension(moimg.getIconWidth() + 50, moimg.getIconHeight()));
			moon.setForeground(Color.WHITE);
			moon.setBackground(Color.BLACK);
			moon.setBorderPainted(false);
			moon.setOpaque(false);
			moon.addActionListener(new MoonView((Moon) planet.getMyMoons().get(i), this));

			look.add(moon);
		}

		for (int i = 0; i < planet.getMySatilights().size(); i++) {
			if (planet.getMySatilights().get(i).getClass() == ImageGate.class) {
				moon = new JButton("Relay");
				moon.setToolTipText("Can not transverse relays in System Viewer.");
				moon.setForeground(Color.WHITE);
				moon.setBackground(Color.BLACK);
				moon.setBorderPainted(false);
				moon.setOpaque(false);
			} else {
				moon = new JButton(planet.getMySatilights().get(i).getMyName());
				ImageIcon moimg = planet.getMySatilights().get(i).getIcon();
				moimg = new ImageIcon(moimg.getImage().getScaledInstance(25, 25, 100));
				moon.setIcon(moimg);
				moon.setPreferredSize(new Dimension(moimg.getIconWidth() + 50, moimg.getIconHeight()));
				moon.setForeground(Color.WHITE);
				moon.setBackground(Color.BLACK);
				moon.setBorderPainted(false);
				moon.setOpaque(false);
				if (planet.getMySatilights().get(i).getClass() == Asteroid.class) {
					moon.addActionListener(new MoonView((Moon) planet.getMySatilights().get(i), this));
				} else {
					moon.addActionListener(new SatilightView(planet.getMySatilights().get(i), this));
				}
			}

			look.add(moon);
		}

		myView.add(look, BorderLayout.CENTER);

		JButton rename = new JButton("Rename");
		rename.addActionListener(new Rename(planet));
		myView.add(rename, BorderLayout.SOUTH);

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);

	}

	public void viewWorldData(Planet planet) {
		level = 2;
		Name.setText(planet.getMyName());
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new BorderLayout());
		JPanel look = new JPanel();
		look.setLayout(new FlowLayout());

		look.setBackground(Color.BLACK);

		JTextArea Print = new JTextArea();
		Print.setEditable(false);

		String display = planet.getMyName() + "\n";

		if (planet.getClass() == Jovian.class) {
			display += "Radius " + planet.getMyRadius() / AstroObject.JOVIANRADI + " Jupiters\n";
			display += "Mass " + planet.getMyMass() / (AstroObject.JOVIAN) + " Jupiters\n";
		} else if (planet.getClass() == Habitable.class) {
			Habitable world = (Habitable) planet;
			display += "Radius " + world.getMyRadius() / (AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass() / (AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater() * 100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere() / (AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			display += "This world is habitable\n";
		} else if (planet.getClass() == HabitableMoon.class) {
			HabitableMoon world = (HabitableMoon) planet;
			display += "Radius " + world.getMyRadius() / (AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass() / (AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater() * 100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere() / (AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			display += "This world is habitable\n";
			Moon moon = (Moon) planet;
			display += "Month " + moon.getMyMonth() / (AstroObject.MONTH) + " months\n";
			display += "Distant to world " + moon.getMyMoonOrbit() / (AstroObject.LIGHTSECOND) + " light seconds\n";
		} else {
			Terrestrial world = (Terrestrial) planet;
			display += "Radius " + world.getMyRadius() / (AstroObject.EARTHRADI) + " Earths\n";
			display += "Mass " + world.getMyMass() / (AstroObject.EARTH) + " Earths\n";
			display += "Water Coverage " + world.getMyWater() * 100 + "%\n";
			display += "Atmospheric Density " + world.getMyAtmosphere() / (AstroObject.BAR) + " Earths\n";
			display += "Maxium Day Tempiture " + world.getMyTemps()[2] + "\n";
			display += "Minimum Day Tempiture " + world.getMyTemps()[4] + "\n";
			display += "Maxium Night Tempiture " + world.getMyTemps()[3] + "\n";
			display += "Minimum Night Tempiture " + world.getMyTemps()[5] + "\n";
			display += "Day Tempiture " + world.getMyTemps()[0] + "\n";
			display += "Night Tempiture " + world.getMyTemps()[1] + "\n";
			display += "Greenhouse " + world.getMyGreenHouse() + "\n";
			if (planet.getClass() == Moon.class) {
				Moon moon = (Moon) planet;
				display += "Month " + moon.getMyMonth() / (AstroObject.MONTH) + " months\n";
				display += "Distant to world " + moon.getMyMoonOrbit() / (AstroObject.LIGHTSECOND) + " light seconds\n";
			}
		}

		// Add year data
		if (planet.getMyYear() / (AstroObject.YEAR * (3.0)) > 1.0) {
			display += "Year " + planet.getMyYear() / (AstroObject.YEAR) + " years\n";
		} else if (planet.getMyYear() / (AstroObject.MONTH * (3.0)) > 1.0) {
			display += "Year " + planet.getMyYear() / (AstroObject.MONTH) + " months\n";
		} else {
			display += "Year " + planet.getMyYear() / (AstroObject.DAY) + " days\n";
		}

		// Add day data
		if (planet.getMyDay() / (AstroObject.YEAR * (3.0)) > 1.0) {
			display += "Day " + planet.getMyDay() / (AstroObject.YEAR) + " years\n";
		} else if (planet.getMyDay() / (AstroObject.MONTH * (3.0)) > 1.0) {
			display += "Day " + planet.getMyDay() / (AstroObject.MONTH) + " months\n";
		} else if (planet.getMyDay() / (AstroObject.DAY * (3.0)) > 1.0) {
			display += "Day " + planet.getMyDay() / (AstroObject.DAY) + " days\n";
		} else if (planet.getMyDay() / (AstroObject.HOUR * (3.0)) > 1.0) {
			display += "Day " + planet.getMyDay() / (AstroObject.HOUR) + " hours\n";
		} else {
			display += "Day " + planet.getMyDay() / 60 + " minutes\n";
		}

		display += "Gravity " + planet.getMyGravity() / (AstroObject.GRAVITYEARTH) + " G's\n";

		// Add orbit data
		display += "Average Orbit " + planet.getMyOrbit() / (AstroObject.AU) + " AU\n";
		display += "Inner Orbit " + planet.getMyInnerOrbit() / (AstroObject.AU) + " AU\n";
		display += "Outer Orbit " + planet.getMyOuterOrbit() / (AstroObject.AU) + " AU";

		JTextArea View = new JTextArea(display);
		View.setEditable(false);
		look.add(View);

		JButton Look = new JButton("Surface");
		Look.addActionListener(new SurfaceView(planet, this));
		look.add(Look);
		myView.add(look, BorderLayout.CENTER);

		JButton rename = new JButton("Rename");
		rename.addActionListener(new Rename(planet));
		myView.add(rename, BorderLayout.SOUTH);

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public void backup() {
		if (level == 1) {
			viewSystem();
		} else if (level == 2) {
			viewPlanet(lastp);
		} else if (level == 3) {
			viewWorldData(lastp);
		} else if (level == 4) {
			viewSurface(lastp);
		}
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
		display += "Gravite Present: " + colony.isHasGravite() + "\n";
		display += "Massive Metal Presence: " + colony.isHasMassiveMetal() + "\n";
		display += "Massive Gasses Presence: " + colony.isHasMassiveGasses() + "\n";
		display += "Radiotropics Present: " + colony.isHasRadiotropes() + "\n";
		display += "Rare Metals Present: " + colony.isHasRareMetals() + "\n";
		display += "Rare Gasses Present: " + colony.isHasRareGasses() + "\n";
		display += "\n";
		display += "Max Population " + (colony.getMaxSize() * 3 + 1) + "\n";
		display += "Has a colony " + (colony.getSize() > 0) + "\n";
		if (colony.getSize() > 0) {
			display += "Colony Size: " + colony.getSize() + "\n";
			display += "Colony Growth: " + colony.getScale() + "\n";
		}

		Condition c = null;
		if (planet.getClass() == Habitable.class) {
			c = ((Habitable) planet).getMyCondition();
		} else if (planet.getClass() == HabitableMoon.class) {
			c = ((HabitableMoon) planet).getMyCondition();
		}

		if (c != null) {
			display += "------------------------------\n";
			display += "Gravity Index " + c.getGravityIndex() + "\n";
			display += "Tempiture Index " + c.getTempitureIndex() + "\n";
			display += "Tempiture Variations " + c.getVarianceIndex() + "\n";
			display += "Pressure Index " + c.getAtmosphericIndex() + "\n";
			display += "Water Index " + c.getWaterIndex() + "\n";
			display += "Atmosphere Type ";
			switch (c.getAirIndex()) {
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
			display += c.isDextros() ? "Dextro" : "Levo";
			display += "-amino acid Biology";
		}

		Print.setText(display);
		myView.add(Print);

		if (colony.isHasBio()) {
			JButton ViewBio = new JButton("View Biosphere");
			ViewBio.addActionListener(new Biolook(colony, c, this));
			myView.add(ViewBio);
		}

		if (colony.getMyColony() != null) {
			JButton ViewColony = new JButton("View Colony");
			ViewColony.addActionListener(new ColonyViewer(colony.getMyColony()));
			myView.add(ViewColony);
		} else {
			JButton ViewColony = new JButton("Colonize");
			ViewColony.addActionListener(new Colonize(colony, this, planet));
			myView.add(ViewColony);
		}

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public void viewBiosphere(Colony col, Condition con) {
		level = 4;
		myView.removeAll();
		myView.repaint();
		myView.setLayout(new FlowLayout());
		JTextArea Print = new JTextArea();
		String display = "";
		display += "Gravity Index " + con.getGravityIndex() + "\n";
		display += "Tempiture Index " + con.getTempitureIndex() + "\n";
		display += "Tempiture Variations " + con.getVarianceIndex() + "\n";
		display += "Pressure Index " + con.getAtmosphericIndex() + "\n";
		display += "Water Index " + con.getWaterIndex() + "\n";
		display += "Atmosphere Type ";
		switch (con.getAirIndex()) {
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
		display += con.isDextros() ? "Dextro" : "Levo";
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

		JButton Prime = new JButton("Major Plant");
		Prime.addActionListener(new PlantView(col.getMyEcosystem().getPrimary()));
		myView.add(Prime);

		JButton Secondary = new JButton("Submajor Plant");
		Secondary.addActionListener(new PlantView(col.getMyEcosystem().getSecondary()));
		myView.add(Secondary);

		this.setSize(this.getWidth() + 1, this.getHeight() + 1);
		this.setSize(this.getWidth() - 1, this.getHeight() - 1);
	}

	public JPanel getMyView() {
		return myView;
	}

	public void setMyView(JPanel myView) {
		this.myView = myView;
	}

	public SolSystem getLastss() {
		return system;
	}

	public void setLastss(SolSystem lastss) {
		this.system = lastss;
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

	@Override
	public void viewPrimaryGate(PrimaryGate myGate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewSecondaryGate(SecondaryGate myGate) {
		// TODO Auto-generated method stub
		
	}
}
