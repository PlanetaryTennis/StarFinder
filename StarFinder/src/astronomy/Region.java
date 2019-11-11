package astronomy;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import engine.Namable;
import engine.Savable;
import engine.Subable;
import map.SettingList;
import utilities.RandomList;
import utilities.StringFundementals;

public class Region implements Serializable, Savable, Namable, Subable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Zone> myZones = new Vector<Zone>();
	private Sector mySector;
	private String Name;

	public Region(String load) {
		this.loadString(load);
	}

	public Region(String Name, Sector sector) {
		this.Name = Name;
		this.mySector = sector;
		myZones = new Vector<Zone>();
		myID = UUID.randomUUID().toString() + ".region";
	}

	public void Add(Zone zone) {
		myZones.add(zone);
	}

	public Sector getMySector() {
		return mySector;
	}

	public void setMySector(Sector mySector) {
		this.mySector = mySector;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Vector<Zone> getMyZones() {
		return myZones;
	}

	public static RandomList consonants;
	public static RandomList vowels;

	public static void gen() {
		consonants = new RandomList();
		vowels = new RandomList();
		vowels.add(10, "a");
		consonants.add(8, "b");
		consonants.add(9, "c");
		consonants.add(8, "d");
		vowels.add(7, "e");
		consonants.add(5, "f");
		consonants.add(6, "g");
		consonants.add(4, "h");
		vowels.add(10, "i");
		consonants.add(3, "j");
		consonants.add(5, "k");
		consonants.add(7, "l");
		consonants.add(9, "m");
		consonants.add(10, "n");
		vowels.add(6, "o");
		consonants.add(2, "p");
		consonants.add(1, "q");
		consonants.add(8, "r");
		consonants.add(10, "s");
		consonants.add(10, "t");
		vowels.add(5, "u");
		consonants.add(2, "v");
		consonants.add(4, "w");
		consonants.add(1, "x");
		consonants.add(1, "y");
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
		vowels.add(1, "y");
		vowels.add(1, "'");
	}

	public static Region makeRandom(Sector s, SettingList SL) {
		Region r = new Region(Region.randomName(), s);
		Random ran = new Random();
		int total = ran.nextInt(SL.getZonemax() + 1) + SL.getSysmin();
		for (int i = 0; i < total; i++) {
			Zone z = Zone.makeRandom(r, SL);
			if (!SL.isName())
				z.setMyName("" + i);
			r.Add(z);
		}
		return r;
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
		Vector<String> in = StringFundementals.breakByLine(object.get(0));
		myID = in.get(0);
		int k = 2;
		Name = in.get(k++);
		SectorID = in.get(k++);
		setZoneNumber(Integer.parseInt(in.get(k++)));
		for (int i = 0; i < getZoneNumber(); i++) {
			myZones.add(new Zone(object.get(i + 1)));
			myZones.get(i).setMyRegion(this);
		}
		return k;
	}

	String SectorID;
	private int ZoneNumber;
	private Vector<String> ZoneIDs = new Vector<String>();

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += this.getName() + "\n";
		out += this.getMySector().getID() + "\n";
		out += this.getMyZones().size() + "\n";
		for (int i = 0; i < this.getMyZones().size(); i++) {
			out += "{\n";
			out += myZones.get(i).saveString() + "\n";
			out += "}\n";
		}
		return out;
	}

	public static final int CLASSINDEX = 937120;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getZoneNumber() {
		return ZoneNumber;
	}

	public void setZoneNumber(int zoneNumber) {
		ZoneNumber = zoneNumber;
	}

	public Vector<String> getZoneIDs() {
		return ZoneIDs;
	}

	public void setZoneIDs(Vector<String> zoneIDs) {
		ZoneIDs = zoneIDs;
	}

	@Override
	public String getMyName() {
		return getName();
	}

	@Override
	public void setMyName(String name) {
		setName(name);		
	}

	@Override
	public void newEmptySub() {
		Add(new Zone(Zone.randomName()));
	}

	@Override
	public void newRandomSub() {
		Add(Zone.makeRandom(this, mySector.getMyGalaxy().getMySettings()));
	}

	@Override
	public void removeSub(int index) {
		Zone s = myZones.elementAt(index);
		if(myZones.remove(index) != null)
			for(int k = 0;k < s.getSystemIDs().size();)
				s.removeSub(k);
	}
}