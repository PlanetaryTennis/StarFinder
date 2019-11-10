package astronomy;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import astronomy.planetary.Belt;
import astronomy.planetary.Jovian;
import astronomy.planetary.Planet;
import astronomy.planetary.Terrestrial;
import astronomy.stellar.BlackHole;
import astronomy.stellar.BrownDwarf;
import astronomy.stellar.MultiStar;
import astronomy.stellar.Nebula;
import astronomy.stellar.Neutron;
import astronomy.stellar.Star;
import astronomy.stellar.WhiteDwarf;
import engine.Namable;
import engine.Savable;
import map.SettingList;
import utilities.ARRAY;
import utilities.RandomList;
import utilities.StringFundementals;

public class SolSystem implements Serializable, Savable, Namable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6219707992537343564L;
	private Star myStar;
	private Vector<Planet> myObjects = new Vector<Planet>();
	private String myName;
	private String myID;

	public SolSystem(String load) {
		this.loadString(load);
	}

	public SolSystem(Star star, String name) {
		myStar = star;
		myName = name;
		myObjects = new Vector<Planet>();
		myID = UUID.randomUUID().toString() + ".solarsystem";
	}

	public void Add(Planet planet) {
		myObjects.add(planet);
	}

	public Star getMyStar() {
		return myStar;
	}

	public void setMyStar(Star myStar) {
		this.myStar = myStar;
	}

	public Vector<Planet> getMyObjects() {
		return myObjects;
	}

	public void setMyObjects(Vector<Planet> myObjects) {
		this.myObjects = myObjects;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	// static values for generation
	public static RandomList orbits;

	public static final int NORMALSTAR = 0;
	public static final int BROWNSTAR = 1;
	public static final int WHITESTAR = 2;
	public static final int NUETRONSTAR = 3;
	public static final int BLACKHOLES = 4;
	public static final int SIZEONESTAR = 5;
	public static final int SIZETWOSTAR = 6;
	public static final int SIZETHREESTAR = 7;

	public static SolSystem makeRandom(Zone s, SettingList SL) {

		SolSystem r = new SolSystem(null, SolSystem.randomName());
		Random ran = new Random();
		int mult = ran.nextInt(1000);
		if (SL.isMulti() && mult < SL.getSuns()[9]) {
			r.setMyStar(MultiStar.randomMultiStar(r, SL));
			r.getMyStar().setMyName(r.getMyName() + " Cluster");
			int k = ran.nextInt(3) + 1;
			int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
			double[] o = new double[total + k];
			Planet put;
			double[] hab = r.myStar.getHabitablezone();
			double frost = r.myStar.getFrostLine();
			double[] sup;
			int measure = (int) (frost * (2.0) / (AstroObject.AU) * 1000);
			if (measure == 0) {
				measure = (int) (frost * (2.0) / (AstroObject.AU) * 1000);
			}
			if (measure <= 0) {
				measure += 1;
			}
			for (int i = 0; i < total; i++) {
				o[i] = AstroObject.AU * ((double) (ran.nextInt(measure) + 1) / 1000);
			}

			if (k == 3) {
				sup = new double[] { hab[0], hab[1], frost };
			} else if (k == 2) {
				sup = new double[] { hab[ran.nextInt(2)], frost };
			} else {
				sup = new double[] { frost };
			}

			for (int i = 0; i < k; i++) {
				o[total + i] = sup[i];
			}
			o = ARRAY.SORT(o);

			k = 0;
			int z = 0;
			for (int i = 0; i < o.length; i++) {
				if (o[i] <= (r.myStar.getMyRadius() * (2.0))) {
				} else if (o[i] == (hab[0])) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
					r.Add(put);
				} else if (o[i] < (hab[0])) {
					z++;
					put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
					if (!SL.isName())
						put.setMyName("" + (char) ('a' + k++));
					r.Add(put);
				} else if (o[i] == (hab[1])) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
					r.Add(put);
				} else if (o[i] < (hab[1])) {
					z++;
					int u = ran.nextInt(100);
					if (u < 75) {
						put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
					} else {
						put = Jovian.makeRandom(o[i], r.getMyStar(), SL);
					}
					if (!SL.isName())
						put.setMyName("" + (char) ('a' + k++));
					r.Add(put);
				} else if (o[i] == (frost)) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
					r.Add(put);
				} else if (o[i] < (frost)) {
					z++;
					int u = ran.nextInt(100);
					if (u < 50) {
						put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
					} else {
						put = Jovian.makeRandom(o[i], r.getMyStar(), SL);
					}
					if (!SL.isName())
						put.setMyName("" + (char) ('a' + k++));
					r.Add(put);
				} else {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
					r.Add(put);
				}
			}
		} else {
			int special = ran.nextInt(SL.getSuns()[5]);
			if (SL.getSuns() == null || special <= SL.getSuns()[0] || !SL.isSpecial()) {
				r.setMyStar(Star.randomStar(r, SL.getSuns()));
				r.getMyStar().setMyName(r.getMyName());
				int k = ran.nextInt(3) + 1;
				int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
				double[] o = new double[total + k];
				Planet put;
				double[] hab = r.myStar.getHabitablezone();
				double frost = r.myStar.getFrostLine();
				double[] sup;
				int measure = (int) (frost * (2.0) / (AstroObject.AU) * 1000);
				if (measure == 0) {
					measure = (int) (frost * (2.0) / (AstroObject.AU) * 1000);
				}
				for (int i = 0; i < total; i++) {
					o[i] = AstroObject.AU * ((double) (ran.nextInt(measure) + 1) / 1000);
				}

				if (k == 3) {
					sup = new double[] { hab[0], hab[1], frost };
				} else if (k == 2) {
					sup = new double[] { hab[ran.nextInt(2)], frost };
				} else {
					sup = new double[] { frost };
				}

				for (int i = 0; i < k; i++) {
					o[total + i] = sup[i];
				}
				o = ARRAY.SORT(o);

				k = 0;
				int z = 0;
				for (int i = 0; i < o.length; i++) {
					if (o[i] <= (r.myStar.getMyRadius() * (2.0))) {
					} else if (o[i] == (hab[0])) {
						put = Belt.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
						r.Add(put);
					} else if (o[i] < (hab[0])) {
						z++;
						put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName("" + (char) ('a' + k++));
						r.Add(put);
					} else if (o[i] == (hab[1])) {
						put = Belt.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
						r.Add(put);
					} else if (o[i] < (hab[1])) {
						z++;
						int u = ran.nextInt(100);
						if (u < 75) {
							put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
						} else {
							put = Jovian.makeRandom(o[i], r.getMyStar(), SL);
						}
						if (!SL.isName())
							put.setMyName("" + (char) ('a' + k++));
						r.Add(put);
					} else if (o[i] == (frost)) {
						put = Belt.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
						r.Add(put);
					} else if (o[i] < (frost)) {
						z++;
						int u = ran.nextInt(100);
						if (u < 50) {
							put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
						} else {
							put = Jovian.makeRandom(o[i], r.getMyStar(), SL);
						}
						if (!SL.isName())
							put.setMyName("" + (char) ('a' + k++));
						r.Add(put);
					} else {
						put = Belt.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
						r.Add(put);
					}
				}
			} else if (special <= SL.getSuns()[1]) {
				r.setMyStar(BrownDwarf.randomStar(r));
				r.getMyStar().setMyName(r.getMyName());
				int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
				double[] o = new double[total];

				for (int i = 0; i < total; i++) {
					o[i] = AstroObject.AU * ((double) (ran.nextInt(20) + 1) / 1000);
				}

				int z = 0;
				int k = 0;
				Planet put;
				for (int i = 0; i < o.length; i++) {
					if (i == 0) {
						put = Belt.makeRandom(o[i], r.getMyStar(), SL);
						if (!SL.isName())
							put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
						r.Add(put);
					} else {
						int h = ran.nextInt(3);
						switch (h) {
						case 0:
							put = Belt.makeRandom(o[i], r.getMyStar(), SL);
							if (!SL.isName())
								put.setMyName((char) (Planet.ALPHA + i - z) + " Belt");
							r.Add(put);
							break;
						case 1:
							z++;
							put = Terrestrial.makeRandom(o[i], r.getMyStar(), SL);
							if (!SL.isName())
								put.setMyName("" + (char) ('a' + k++));
							r.Add(put);
							break;
						case 2:
							z++;
							put = Jovian.makeRandom(o[i], r.getMyStar(), SL);
							if (!SL.isName())
								put.setMyName("" + (char) ('a' + k++));
							r.Add(put);
							break;
						}
					}
				}
			} else if (special <= SL.getSuns()[2]) {
				r.setMyStar(WhiteDwarf.randomStar(r));
				r.getMyStar().setMyName(r.getMyName());
				int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
				double[] o = new double[total];
				for (int i = 0; i < total; i++) {
					o[i] = AstroObject.AU * ((double) (ran.nextInt(100) + 1) / 1000);
				}
				Planet put;
				for (int i = 0; i < o.length; i++) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					r.Add(put);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i) + " Belt");
				}
			} else if (special <= SL.getSuns()[3]) {
				r.setMyStar(Neutron.randomStar(r));
				r.getMyStar().setMyName(r.getMyName());
				int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
				double[] o = new double[total];
				for (int i = 0; i < total; i++) {
					o[i] = AstroObject.AU * ((double) (ran.nextInt(500) + 1) / 1000);
				}
				Planet put;
				for (int i = 0; i < o.length; i++) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					r.Add(put);
					if (!SL.isName())
						put.setMyName((char) (Planet.ALPHA + i) + " Belt");
				}
			} else if (special <= SL.getSuns()[4]) {
				r.setMyName(r.getMyName() + " Singularity");
				r.setMyStar(new BlackHole(ran.nextInt(100) + 13, r));
				int total = ran.nextInt(SL.getPlanetmax() + 1) + 1;
				double[] o = new double[total];
				for (int i = 0; i < total; i++) {
					o[i] = AstroObject.AU * ((double) (ran.nextInt(500) + 1) / 1000);
				}
				Planet put;
				for (int i = 0; i < o.length; i++) {
					put = Belt.makeRandom(o[i], r.getMyStar(), SL);
					r.Add(put);
					if (!SL.isName())
						put.setMyName((Planet.ALPHA + i) + " Belt");
				}
			} else {
				r.setMyName(r.getMyName() + " Nebula");
				r.setMyStar(new Nebula(r));
				r.Add(Belt.makeRandom(0, r.getMyStar(), SL));
			}
		}
		r.setMyName(r.getMyName() + " System");
		return r;
	}

	public static RandomList consonants;
	public static RandomList vowels;

	public static void gen() {
		consonants = new RandomList();
		vowels = new RandomList();
		vowels.add(10, "a");
		consonants.add(12, "b");
		consonants.add(20, "c");
		consonants.add(15, "d");
		vowels.add(7, "e");
		consonants.add(10, "f");
		consonants.add(13, "g");
		consonants.add(20, "h");
		vowels.add(10, "i");
		consonants.add(10, "j");
		consonants.add(12, "k");
		consonants.add(16, "l");
		consonants.add(20, "m");
		consonants.add(20, "n");
		vowels.add(6, "o");
		consonants.add(9, "p");
		consonants.add(2, "q");
		consonants.add(13, "r");
		consonants.add(20, "s");
		consonants.add(20, "t");
		vowels.add(5, "u");
		consonants.add(11, "v");
		consonants.add(5, "w");
		consonants.add(1, "x");
		consonants.add(3, "y");
		consonants.add(1, "z");
		vowels.add(1, "-");
		consonants.add(3, "ll");
		consonants.add(4, "sh");
		consonants.add(6, "th");
		consonants.add(1, "wh");
		vowels.add(3, "oo");
		vowels.add(4, "ie");
		vowels.add(2, "ae");
		vowels.add(3, "ee");
		consonants.add(4, "tr");
		consonants.add(3, "cr");
		consonants.add(2, "ch");
		consonants.add(2, "ck");
		vowels.add(1, "y");
		vowels.add(1, "'");
	}

	public static String randomName() {
		if (consonants == null)
			gen();
		String s = "";
		Random ran = new Random();
		int i = ran.nextInt(4) + 1;
		for (; i > 0; i--) {
			s += consonants.get();
			s += vowels.get();
		}
		return s;
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.elementAt(0));
		myID = in[0];
		int i = 2;
		myName = in[i++];
		setMyStar(StarParse(object.get(1)));
		myStar.setMySystem(this);
		setPlanetNumber(Integer.parseInt(in[i++]));
		for (int k = 0; k < getPlanetNumber(); k++) {
			Add(Planet.parseLoad(object.get(k + 2)));
		}
		return i;
	}

	public static Star StarParse(String string) {
		String[] in = StringFundementals.breakByLine(string);
		switch (Integer.parseInt(in[2])) {
		case Star.CLASSINDEX:
			return new Star(string);
		case BrownDwarf.CLASSINDEX:
			return new BrownDwarf(string);
		case BlackHole.CLASSINDEX:
			return new BlackHole(string);
		case Nebula.CLASSINDEX:
			return new Nebula(string);
		case Neutron.CLASSINDEX:
			return new Neutron(string);
		case WhiteDwarf.CLASSINDEX:
			return new WhiteDwarf(string);
		case MultiStar.CLASSINDEX:
			return new MultiStar(string);
		}
		return null;
	}

	private String StarID;
	String ZoneID;
	private int PlanetNumber;
	private Vector<String> PlanetIDs = new Vector<String>();

	@Override
	public String saveString() {
		String out = "";
		out += this.myID + "\n";
		out += this.getClassIndex() + "\n";
		out += this.getMyName() + "\n";
		out += "{\n";
		out += this.myStar.saveString() + "\n";
		out += "}\n";
		out += this.getMyObjects().size() + "\n";
		for (int i = 0; i < myObjects.size(); i++) {
			out += "{\n";
			out += myObjects.get(i).saveString() + "\n";
			out += "}\n";
		}
		return out;
	}

	public static final int CLASSINDEX = 937113;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	@Override
	public String getID() {
		return myID;
	}

	public String getStarID() {
		return StarID;
	}

	public void setStarID(String starID) {
		StarID = starID;
	}

	public int getPlanetNumber() {
		return PlanetNumber;
	}

	public void setPlanetNumber(int planetNumber) {
		PlanetNumber = planetNumber;
	}

	public Vector<String> getPlanetIDs() {
		return PlanetIDs;
	}

	public void setPlanetIDs(Vector<String> planetIDs) {
		PlanetIDs = planetIDs;
	}
}
