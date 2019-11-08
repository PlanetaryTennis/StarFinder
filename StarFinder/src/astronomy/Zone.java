package astronomy;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import engine.ObjectFiles;
import engine.Savable;
import map.SettingList;
import utilities.RandomList;
import utilities.StringFundementals;

public class Zone implements Serializable, Savable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7375343829370278946L;
	private String myName;
	private Region myRegion;

	public Zone(String load) {
		this.loadString(load);
	}

	public Zone(String name, Region r) {
		myName = name;
		myRegion = r;
		myID = UUID.randomUUID().toString() + ".zone";
	}

	public void Add(String system) {
		SystemIDs.add(system);
		SystemNumber++;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Region getMyRegion() {
		return myRegion;
	}

	public void setMyRegion(Region myRegion) {
		this.myRegion = myRegion;
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

	public static Zone makeRandom(Region s, SettingList SL) {
		Zone r = new Zone(Zone.randomName(), s);
		Random ran = new Random();
		int total = ran.nextInt(SL.getSysmax() + 1) + SL.getSysmin();
		for (int i = 0; i < total; i++) {
			SolSystem z = SolSystem.makeRandom(r, SL);
			if (!SL.isName())
				z.setMyName("" + i);
			ObjectFiles.WriteSavabletoFile(z, SL.getName());
			r.Add(z.getID());
		}
		return r;
	}

	@Override
	public int loadString(String load) {
		Vector<String> in = StringFundementals.breakByLine(load);
		myID = in.get(0);
		int k = 2;
		myName = in.get(k++);
		RegionID = in.get(k++);
		setSystemNumber(Integer.parseInt(in.get(k++)));
		for (int i = 0; i < getSystemNumber(); i++) {
			getSystemIDs().add(in.get(k++));
		}
		return k;
	}

	String RegionID;
	private int SystemNumber;
	private Vector<String> SystemIDs = new Vector<String>();

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += this.getMyName() + "\n";
		out += this.getMyRegion().getID() + "\n";
		out += this.SystemIDs.size() + "\n";
		for (int i = 0; i < this.SystemIDs.size(); i++) {
			out += SystemIDs.get(i) + "\n";
		}
		return out;
	}

	public static final int CLASSINDEX = 937173;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getSystemNumber() {
		return SystemNumber;
	}

	public void setSystemNumber(int systemNumber) {
		SystemNumber = systemNumber;
	}

	public Vector<String> getSystemIDs() {
		return SystemIDs;
	}

	public void setSystemIDs(Vector<String> systemIDs) {
		SystemIDs = systemIDs;
	}
}
