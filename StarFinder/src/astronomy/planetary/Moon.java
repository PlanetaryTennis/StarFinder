package astronomy.planetary;

import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.OrbitObject;
import astronomy.stellar.Star;
import map.Sprite;
import planetary.Colony;
import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Moon extends Terrestrial implements OrbitObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624598682414279466L;

	protected double myMonth;
	protected double myMoonOrbit;

	public Moon(String load) {
		this.loadString(load);
	}

	public Moon() {

	}

	public Moon(double myAtmosphere, double myRadius, double myMass, double myEccentricity, double myOrbit, Star star,
			double myAlbido, double myGreenHouse, double myWater, double myDay, double myMonth, double myMoonOrbit) {
		super(new Vector<Moon>(0), myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, star, myAlbido,
				myGreenHouse, myWater, myDay);
		this.myMoonOrbit = myMoonOrbit;
		this.myMonth = myMonth;
	}

	public double getMyMonth() {
		return myMonth;
	}

	public void setMyMonth(double myMonth) {
		this.myMonth = myMonth;
	}

	public double getMyMoonOrbit() {
		return myMoonOrbit;
	}

	public void setMyMoonOrbit(double myMoonOrbit) {
		this.myMoonOrbit = myMoonOrbit;
	}

	public static Moon makeRandom(double orbit, double sitime, double planetmass, double radius, double scale,
			Star star) {
		double alter = random.nextDouble() + 0.01;
		double mass = AstroObject.LUNE * (alter * 10);
		double moonradius = LUNERADI * (Math.cbrt(alter));
		double lesserorbit = radius * (4) + (radius * (random.nextDouble() * 4));

		double month = monthcalculate(planetmass, mass, lesserorbit);

		double atmosphere = BAR * (random.nextDouble() * 15);

		double greenhouse = random.nextDouble() * 15 + 0.01;
		double albido = random.nextDouble() * 2 / 3;

		double day = DAY * (8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));

		double water = random.nextDouble();

		double[] temps = tempitures(greenhouse, albido, orbit, orbit * (1 + scale), orbit * (1 - scale), star,
				atmosphere);

		Moon p;

		if (temps[2] >= (high) || temps[5] <= (low) || water < 0.1 || water > 0.98 || atmosphere <= (BAR * (0.5))
				|| atmosphere >= (BAR * (5)) || mass <= (LUNE) || mass >= (EARTH * (5))) {
			p = new Moon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month,
					lesserorbit);
			p.setMyColony(Colony.randomMoon((Moon) p));
		} else {
			p = new HabitableMoon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day,
					month, lesserorbit);
			p.setMyColony(Colony.randomHabMoon((HabitableMoon) p));
		}

		return p;
	}

	public static Moon makeRandomJovian(double orbit, double sitime, double planetmass, double radius, double scale,
			Star star) {

		double alter = (random.nextDouble() + 0.01) / 64;

		double mass = AstroObject.LUNE * (alter * 10);
		double moonradius = LUNERADI * (Math.cbrt(alter));
		double lesserorbit = radius * (4) + (radius * (random.nextDouble() * 4 + 0.1));

		double month = monthcalculate(planetmass, mass, lesserorbit);

		double atmosphere = BAR * (random.nextDouble() * 15 + 0.1);

		double greenhouse = random.nextDouble() * 15 + 0.01;
		double albido = random.nextDouble() * 2 / 3;

		double day = DAY * (8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));

		double water = random.nextDouble();

		double[] temps = tempitures(greenhouse, albido, orbit, orbit * (1 + scale), orbit * (1 - scale), star,
				atmosphere);

		Moon p;

		double test = atmosphere / (BAR);

		if (temps[2] > (high) || temps[5] < (low) || water < 0.1 || water > 0.98 || test < 0.5 || test > 5
				|| mass <= (LUNE) || mass >= (EARTH * 5)) {
			p = new Moon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month,
					lesserorbit);
			p.setMyColony(Colony.randomMoon((Moon) p));
		} else {
			p = new HabitableMoon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day,
					month, lesserorbit);
			p.setMyColony(Colony.randomHabMoon((HabitableMoon) p));
		}

		return p;
	}

	protected static double monthcalculate(double planetmass, double moonmass, double moonorbit) {
		double massfactor = planetmass + moonmass;
		double distancefactor = Math.pow(moonorbit, 3);
		double pifactor = Math.pow(Math.PI, 2) * 4;
		double completefactor = Math.sqrt((pifactor * distancefactor) / (AstroObject.G * massfactor));
		return completefactor;
	}

	@Override
	public ImageIcon getIcon() {
		if (this.getClass() == HabitableMoon.class) {
			HabitableMoon h = (HabitableMoon) this;
			if (h.getMyColony().isWaste()) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Wasteland Moon.png"));
			} else if (h.getMyWater() > 0.85) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Ocean Moon.png"));
			} else if (h.getMyWater() > 0.5) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Earth-Like Moon.png"));
			} else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Desert Moon.png"));
			}
		} else {
			if (this.getMyTemps()[2] / (boil) > 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Lava Moon.png"));
			} else if (this.getMyTemps()[2] / (freeze) < 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Icy Moon.png"));
			} else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS + "Rocky Moon.png"));
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
		myMonth = Double.parseDouble(in[i++]);
		myMoonOrbit = Double.parseDouble(in[i++]);
		return i;
	}

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
		out += getMySatilights().size() + "\n";
		for (int i = 0; i < getMySatilights().size(); i++) {
			out += "{\n";
			out += getMySatilights().get(i).saveString() + "\n";
			out += "}\n";
		}
		out += getMyMonth() + "\n";
		out += getMyMoonOrbit() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 934824;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
