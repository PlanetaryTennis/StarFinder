package astronomy.planetary;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.OrbitObject;
import astronomy.SolSystem;
import astronomy.stellar.Star;
import map.SettingList;
import map.Sprite;
import planetary.Colony;
import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Terrestrial extends Planet {

	public static int total = 0;
	private static final String LINEBREAK = "<BR/>";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7913040733716806290L;
	protected double myAlbido;
	protected double myGreenHouse;
	protected double myWater;
	protected boolean isFrozen;

	public Terrestrial(String load) {
		this.loadString(load);
	}

	public Terrestrial() {
	}

	public Terrestrial(Vector<Moon> myMoons, double myAtmosphere, double myRadius, double myMass, double myEccentricity,
			double myOrbit, Star star, double myAlbido, double myGreenHouse, double myWater, double myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myDay, star);
		this.myAlbido = myAlbido;
		this.myGreenHouse = myGreenHouse;
		this.myWater = myWater;
		myTemps = tempitures(myGreenHouse, myAlbido, myOrbit, myInnerOrbit, myOuterOrbit, star, myAtmosphere);
		if (myTemps[2] <= (freeze)) {
			isFrozen = true;
		}
		if (myTemps[5] >= (boil) || myAtmosphere <= BAR * (0.25)) {
			this.myWater = 0.0f;
		}
		myID = UUID.randomUUID().toString() + ".Planet";
	}

	public static double[] tempitures(double GreenHouse, double Albido, double Orbit, double InnerOrbit,
			double OuterOrbit, Star Star, double Atmosphere) {
		double l = Math.pow(Star.getMyLuminosity(), 1.0f / 8.0f);
		double d = Orbit / AU;
		double dividan = 16 * Math.PI * Math.pow(d, 2) * O;
		double top = l * (1 - Albido);
		double Mid = Math.pow(top / dividan, 1.0f / 4.0f);
		d = InnerOrbit / AU;
		dividan = 16 * Math.PI * Math.pow(d, 2) * O;
		double High = Math.pow(top / dividan, 1.0f / 4.0f);
		d = OuterOrbit / AU;
		dividan = 16 * Math.PI * Math.pow(d, 2) * O;
		double Low = Math.pow(top / dividan, 1.0f / 4.0f);
		double tempmods = GreenHouse * Atmosphere / AstroObject.BAR;
		double nightmod = tempmods / (tempmods + 0.1);

		double myDayNorm = Mid + Mid * Atmosphere / BAR + tempmods * Mid;
		double myNightNorm = nightmod * Mid + Mid * Atmosphere / BAR + tempmods * Mid;

		double myDayMax = High + High * Atmosphere / BAR + tempmods * High;
		double myNightMax = nightmod * High + High * Atmosphere / BAR + tempmods * High;

		double myDayMin = Low + Low * Atmosphere / BAR + tempmods * Low;
		double myNightMin = nightmod * Low + Low * Atmosphere / BAR + tempmods * Low;

		return new double[] { myDayNorm, myNightNorm, myDayMax, myNightMax, myDayMin, myNightMin };
	}

	public double getMyAlbido() {
		return myAlbido;
	}

	public double getMyGreenHouse() {
		return myGreenHouse;
	}

	public double getMyWater() {
		return myWater;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

//Habitable Stats
	public static final double low = 270.0f;
	public static final double high = 330.0f;
	public static final double freeze = 273.15;
	public static final double boil = 373.15;
	public static final Random random = new Random(System.nanoTime());

	public static Planet makeRandom(double orbit, Star star, SettingList sL) {
		int moonnum = random.nextInt(4);
		Vector<Moon> moons = new Vector<Moon>(moonnum);

		double alter = (random.nextDouble() * 15 + 0.01) / 3;

		double mass = EARTH * alter;
		double radius = EARTHRADI * (Math.cbrt(alter));
		double scale = ExtendedMathmatics.log(random.nextInt(499) + 1, 1000) / 16;

		for (int i = 0; i < moonnum; i++) {
			moons.add(Moon.makeRandom(orbit, yearcalculate(star, mass, orbit), mass, radius, scale, star));
			if (sL.isName()) {
				moons.get(i).setMyName(SolSystem.randomName());
			} else {
				moons.get(i).setMyName(nameMoon(i));
			}
		}

		double atmosphere = BAR * (random.nextDouble() * 5 / 4);

		double greenhouse = random.nextDouble() * 5;
		double albido = random.nextDouble() * 2 / 3;

		double[] temps = tempitures(greenhouse, albido, orbit, orbit * (1 + scale), orbit * (1 - scale), star,
				atmosphere);
		Planet p;

		double day = DAY * (8 - ExtendedMathmatics.log(random.nextInt(150) + 1, 2));

		double water = random.nextDouble();

		double test = atmosphere / BAR;

		if (temps[2] > (high) || temps[5] < (low) || water < 0.1 || water > 0.98 || moonnum < 1 || test < 0.5
				|| test > 5 || mass <= (LUNE) || mass >= EARTH * 5) {
			p = new Terrestrial(moons, atmosphere, radius, mass, scale, orbit, star, albido, greenhouse, water, day);
			p.setMyColony(Colony.randomTerrestrial((Terrestrial) p));
		} else {
			p = new Habitable(moons, atmosphere, radius, mass, scale, orbit, star, albido, greenhouse, water, day);
			p.setMyColony(Colony.randomHabtiable((Habitable) p));
		}

		for (int i = 0; i < moons.size(); i++) {
			moons.get(i).setMyYear(p.getMyYear());
		}

		int y = random.nextInt(10);
		Asteroid a;
		for (int i = 0; i < y; i++) {
			a = (Asteroid) Asteroid.makeRandom(p, scale, orbit, star);
			if (sL.isName()) {
				a.setMyName(SolSystem.randomName());
			} else {
				a.setMyName(nameMoon(moonnum + i));
			}
			p.getMySatilights().add(a);
		}

		p.setMyName(SolSystem.randomName());

		return p;
	}

	@Override
	public String string() {
		return "Atmosphere " + this.getMyAtmosphere() + LINEBREAK + "Day Length " + this.getMyDay() + LINEBREAK
				+ "Gravity " + this.getMyGravity() + LINEBREAK + "Density " + this.getMyDensity() + LINEBREAK + "Mass "
				+ this.getMyMass() + LINEBREAK + "Number of Moons " + this.getMyMoons().size() + LINEBREAK
				+ "Orbit Average " + this.getMyOrbit() + LINEBREAK + "Near Orbit " + this.getMyInnerOrbit() + LINEBREAK
				+ "Far Orbit " + this.getMyOuterOrbit() + LINEBREAK + "Radius " + this.getMyRadius() + LINEBREAK
				+ "Year Length" + this.getMyYear() + LINEBREAK + "Day Norm" + this.getMyTemps()[0] + LINEBREAK
				+ "Night Norm " + this.getMyTemps()[1] + LINEBREAK + "Day Max " + this.getMyTemps()[2] + LINEBREAK
				+ "Night Max " + this.getMyTemps()[3] + LINEBREAK + "Day Min " + this.getMyTemps()[4] + LINEBREAK
				+ "Night Min " + this.getMyTemps()[5] + LINEBREAK + "Water " + this.myWater + LINEBREAK
				+ "Green House Effect " + this.myGreenHouse;
	}

	@Override
	public ImageIcon getIcon() {
		if (this.getClass() == Habitable.class) {
			Habitable h = (Habitable) this;
			if (h.getMyWater() > 0.85) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Ocean Planet.png"));
			} else if (h.getMyWater() > 0.5) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Earth-Like Planet.png"));
			} else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Desert Planet.png"));
			}
		} else {
			if (this.getMyTemps()[2] / (boil) > 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Lava Planet.png"));
			} else if (this.getMyTemps()[2] / (freeze) < 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Tundra Planet.png"));
			} else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS + "Rocky Planet.png"));
			}
		}
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.get(0));
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myAlbido = Double.parseDouble(in[i++]);
		myAtmosphere = Double.parseDouble(in[i++]);
		myDay = Double.parseDouble(in[i++]);
		myDensity = Double.parseDouble(in[i++]);
		myEccentricity = Double.parseDouble(in[i++]);
		myGravity = Double.parseDouble(in[i++]);
		myGreenHouse = Double.parseDouble(in[i++]);
		myOrbit = Double.parseDouble(in[i++]);
		myInnerOrbit = Double.parseDouble(in[i++]);
		myOuterOrbit = Double.parseDouble(in[i++]);
		myMass = Double.parseDouble(in[i++]);
		myRadius = Double.parseDouble(in[i++]);
		myTemps = new double[6];
		myTemps[0] = Double.parseDouble(in[i++]);
		myTemps[1] = Double.parseDouble(in[i++]);
		myTemps[2] = Double.parseDouble(in[i++]);
		myTemps[3] = Double.parseDouble(in[i++]);
		myTemps[4] = Double.parseDouble(in[i++]);
		myTemps[5] = Double.parseDouble(in[i++]);
		myVolume = Double.parseDouble(in[i++]);
		myWater = Double.parseDouble(in[i++]);
		myYear = Double.parseDouble(in[i++]);
		setMyColony(new Colony(object.get(1)));
		setMoonNumber(Integer.parseInt(in[i++]));
		int j = 2;
		for (int k = 0; k < getMoonNumber(); k++) {
			getMyMoons().add((Moon) Planet.parseLoad(object.get(j)));
			j++;
		}
		setSatilightNumber(Integer.parseInt(in[i++]));
		for (int k = 0; k < getSatilightNumber(); k++) {
			getMySatilights().add(OrbitObject.parseLoad(object.get(j)));
			j++;
		}
		return i;
	}

	private int MoonNumber;
	private int SatilightNumber;

	@Override
	public String saveString() {
		String out = "";
		out += getID() + "\n";
		out += getClassIndex() + "\n";
		out += getMyName() + "\n";
		out += getMyAlbido() + "\n";
		out += getMyAtmosphere() + "\n";
		out += getMyDay() + "\n";
		out += getMyDensity() + "\n";
		out += getMyEccentricity() + "\n";
		out += getMyGravity() + "\n";
		out += getMyGreenHouse() + "\n";
		out += getMyOrbit() + "\n";
		out += getMyInnerOrbit() + "\n";
		out += getMyOuterOrbit() + "\n";
		out += getMyMass() + "\n";
		out += getMyRadius() + "\n";
		out += getMyTemps()[0] + "\n";
		out += getMyTemps()[1] + "\n";
		out += getMyTemps()[2] + "\n";
		out += getMyTemps()[3] + "\n";
		out += getMyTemps()[4] + "\n";
		out += getMyTemps()[5] + "\n";
		out += getMyVolume() + "\n";
		out += getMyWater() + "\n";
		out += getMyYear() + "\n";
		out += "{\n";
		out += getMyColony().saveString() + "\n";
		out += "}\n";
		out += getMyMoons().size() + "\n";
		for (int i = 0; i < getMyMoons().size(); i++) {
			out += "{\n";
			out += getMyMoons().get(i).saveString() + "\n";
			out += "}\n";
		}
		int g = 0;
		String Append = "";
		for (int i = 0; i < getMySatilights().size(); i++) {
			if (getMySatilights().get(i) == null) {
				g++;
				break;
			}
			Append += "{\n";
			Append += getMySatilights().get(i).saveString() + "\n";
			Append += "}\n";
		}
		out += getMySatilights().size() - g + "\n";
		out += Append;
		return out;
	}

	public static final int CLASSINDEX = 934826;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	protected String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getMoonNumber() {
		return MoonNumber;
	}

	public void setMoonNumber(int moonNumber) {
		MoonNumber = moonNumber;
	}

	public int getSatilightNumber() {
		return SatilightNumber;
	}

	public void setSatilightNumber(int satilightNumber) {
		SatilightNumber = satilightNumber;
	}

}
