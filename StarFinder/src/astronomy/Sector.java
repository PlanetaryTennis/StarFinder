package astronomy;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import engine.Namable;
import engine.Savable;
import map.SettingList;
import utilities.RandomList;
import utilities.StringFundementals;

public class Sector implements Serializable, Savable, Namable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7823345276729076886L;
	private Vector<Region> myRegions = new Vector<Region>();
	private Galaxy myGalaxy;
	private String myName;

	public Sector(String load, double d) {
		this.loadString(load);
	}

	public Galaxy getMyGalaxy() {
		return myGalaxy;
	}

	public void setMyGalaxy(Galaxy myGalaxy) {
		this.myGalaxy = myGalaxy;
	}

	public Sector(String name) {
		myName = name;
		myRegions = new Vector<Region>();
		myID = UUID.randomUUID().toString() + ".sector";
	}

	public void Add(Region region) {
		myRegions.add(region);
	}

	public Vector<Region> getRegions() {
		return myRegions;
	}

	public String getName() {
		return myName;
	}

	public static RandomList consonants = RandomList
			.zif(new String[] { "t", "n", "s", "r", "h", "th", "sh", "tt", "l", "d", "c", "ch", "m", "nd", "f", "p",
					"ph", "g", "ng", "ch", "w", "y", "wh", "b", "v", "k", "x", "j", "q", "z" });
	public static RandomList vowels = RandomList.zif(new String[] { "e", "a", "o", "i", "u", "ee", "oo", "ie", "ae",
			"y", "e", "a", "o", "i", "u", "ee", "oo", "ie", "ae", "y", "-", "'" });

	public static Sector makeRandom(SettingList SL) {
		Sector r = new Sector(Sector.randomName());
		Random ran = new Random();
		int total = ran.nextInt(SL.getRegionsmax() + 1) + SL.getRegionsmin();
		for (int i = 0; i < total; i++) {
			Region z = Region.makeRandom(r, SL);
			if (!SL.isName())
				z.setName("" + i);
			r.Add(z);
		}
		return r;
	}

	public static String randomName() {
		String s = "";
		Random ran = new Random();
		int i = ran.nextInt(4) + 1;
		for (; i > 0; i--) {
			s += consonants.get();
			s += vowels.get();
		}
		return s;
	}

	public void setName(String text) {
		myName = text;
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.get(0));
		myID = in[0];
		int k = 2;
		myName = in[k++];
		GalaxyID = in[k++];
		setRegionNumber(Integer.parseInt(in[k++]));
		for (int i = 0; i < getRegionNumber(); i++) {
			myRegions.add(new Region(object.get(i + 1)));
			myRegions.get(i).setMySector(this);
		}
		return k;
	}

	String GalaxyID;
	private int RegionNumber;
	private Vector<String> RegionIDs = new Vector<String>();;

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += this.getName() + "\n";
		out += this.getMyGalaxy().getID() + "\n";
		out += this.getRegions().size() + "\n";
		for (int i = 0; i < this.getRegions().size(); i++) {
			out += "{\n";
			out += myRegions.get(i).saveString() + "\n";
			out += "}\n";
		}
		return out;
	}

	public static final int CLASSINDEX = 937184;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getRegionNumber() {
		return RegionNumber;
	}

	public void setRegionNumber(int regionNumber) {
		RegionNumber = regionNumber;
	}

	public Vector<String> getRegionIDs() {
		return RegionIDs;
	}

	public void setRegionIDs(Vector<String> regionIDs) {
		RegionIDs = regionIDs;
	}

	@Override
	public String getMyName() {
		return getName();
	}

	@Override
	public void setMyName(String name) {
		setName(name);
	}
}
