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

public class Jovian extends Planet {

	public static int total = 0;
	private static final String LINEBREAK = "<BR/>";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3059962286635742097L;

	public Jovian(String load) {
		this.loadString(load);
	}

	public Jovian(Vector<Moon> myMoons, double myAtmosphere, double myRadius, double myMass, double myEccentricity,
			double myOrbit, Star star, double myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myDay, star);
		myID = UUID.randomUUID().toString() + ".Planet";
	}

	public static final Random random = new Random(System.currentTimeMillis());

	public static Planet makeRandom(double orbit, Star star, SettingList sL) {
		double m = random.nextDouble() * 15;
		double mass = AstroObject.JOVIAN * (m);

		int moonnum = random.nextInt(16) + 8;
		Vector<Moon> moons = new Vector<Moon>(moonnum);
		double radius = AstroObject.JOVIANRADI * (Math.pow(m, 0.74));

		double scale = ExtendedMathmatics.log(random.nextInt(499) + 1, 1000) / 8;

		for (int i = 0; i < moonnum; i++) {
			moons.add(Moon.makeRandomJovian(orbit, yearcalculate(star, mass, orbit), mass, radius, scale, star));
			if (sL.isName()) {
				moons.get(i).setMyName(SolSystem.randomName());
			} else {
				moons.get(i).setMyName(nameMoon(i));
			}
		}

		double day = DAY * (8 - ExtendedMathmatics.log(random.nextInt(155) + 1, 2));

		double volume = Math.pow(radius, 3) * (0.75 * Math.PI) * 1000;

		double atmosphere = mass / volume;

		Jovian j = new Jovian(moons, atmosphere, radius, mass, scale, orbit, star, day);

		for (int i = 0; i < moonnum; i++) {
			moons.get(i).setMyYear(j.getMyYear());
		}

		int y = random.nextInt(25);
		Asteroid a;
		for (int i = 0; i < y; i++) {
			a = (Asteroid) Asteroid.makeRandom(j, scale, orbit, star);
			if (sL.isName()) {
				a.setMyName(SolSystem.randomName());
			} else {
				a.setMyName(nameMoon(moonnum + i));
			}
			j.getMySatilights().add(a);
		}

		j.setMyName(SolSystem.randomName());
		j.setMyColony(Colony.randomJovian((Jovian) j));
		double[] temps = Terrestrial.tempitures(random.nextDouble() * 5, random.nextDouble(), orbit,
				orbit * (1 + scale), orbit * (1 - scale), star, atmosphere);
		j.setMyTemps(temps);

		return j;
	}

	@Override
	public String string() {
		return "Atmosphere " + this.getMyAtmosphere() + LINEBREAK + "Day Length " + this.getMyDay() + LINEBREAK
				+ "Gravity " + this.getMyGravity() + LINEBREAK + "Density " + this.getMyDensity() + LINEBREAK + "Mass "
				+ this.getMyMass() + LINEBREAK + "Number of Moons " + this.getMyMoons().size() + LINEBREAK
				+ "Orbit Average " + this.getMyOrbit() + LINEBREAK + "Near Orbit " + this.getMyInnerOrbit() + LINEBREAK
				+ "Far Orbit " + this.getMyOuterOrbit() + LINEBREAK + "Radius " + this.getMyRadius() + LINEBREAK
				+ "Year Length" + this.getMyYear();
	}

	@Override
	public ImageIcon getIcon() {
		if (getMyMass() >= (AstroObject.JOVIAN * (0.5))) {
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.GASGIANT + "Gas-Giant-Yellow.png"));
		} else {
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.GASGIANT + "Gas-Giant-Blue.png"));
		}
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.get(0));
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myAtmosphere = Double.parseDouble(in[i++]);
		myDay = Double.parseDouble(in[i++]);
		myDensity = Double.parseDouble(in[i++]);
		myEccentricity = Double.parseDouble(in[i++]);
		myGravity = Double.parseDouble(in[i++]);
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

	private void setSatilightNumber(int parseInt) {
		SatilightNumber = parseInt;
	}

	private void setMoonNumber(int parseInt) {
		MoonNumber = parseInt;
	}

	protected int MoonNumber;
	protected int SatilightNumber;

	@Override
	public String saveString() {
		String out = "";
		out += getID() + "\n";
		out += getClassIndex() + "\n";
		out += getMyName() + "\n";
		out += getMyAtmosphere() + "\n";
		out += getMyDay() + "\n";
		out += getMyDensity() + "\n";
		out += getMyEccentricity() + "\n";
		out += getMyGravity() + "\n";
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

	public static final int CLASSINDEX = 934899;

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

	public int getSatilightNumber() {
		return SatilightNumber;
	}
}
