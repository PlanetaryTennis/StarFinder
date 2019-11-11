package map;

import java.util.Vector;

import engine.Savable;
import utilities.StringFundementals;

public class SettingList implements Savable {
	int sectors;
	int regionsmin;
	int regionsmax;
	int zonemin;
	int zonemax;
	int sysmin;
	int sysmax;
	int planetmax;
	boolean special;
	boolean multi;
	boolean name;
	String myName;

	int[] suns;

	public SettingList(String myName, int sectors, int regionsmin, int regionsmax, int zonemin, int zonemax, int sysmin,
			int sysmax, int planetmax, boolean special, boolean multi, boolean name, int[] suns) {
		super();
		this.myName = myName;
		this.sectors = sectors;
		this.regionsmin = regionsmin;
		this.regionsmax = regionsmax;
		this.zonemin = zonemin;
		this.zonemax = zonemax;
		this.sysmin = sysmin;
		this.sysmax = sysmax;
		this.planetmax = planetmax;
		this.special = special;
		this.multi = multi;
		this.name = name;
		this.suns = suns;
		myID = "Map.SFS";
	}

	public int getSectors() {
		return sectors;
	}

	public int getRegionsmin() {
		return regionsmin;
	}

	public int getRegionsmax() {
		return regionsmax;
	}

	public int getZonemin() {
		return zonemin;
	}

	public int getZonemax() {
		return zonemax;
	}

	public int getSysmin() {
		return sysmin;
	}

	public int getSysmax() {
		return sysmax;
	}

	public int getPlanetmax() {
		return planetmax;
	}

	public boolean isSpecial() {
		return special;
	}

	public boolean isMulti() {
		return multi;
	}

	public boolean isName() {
		return name;
	}

	public int[] getSuns() {
		return suns;
	}

	public String getName() {
		return myName;
	}

	@Override
	public int loadString(String load) {
		Vector<String> in = StringFundementals.breakByLine(load);
		myID = in.get(0);
		int i = 2;
		sectors = Integer.parseInt(in.get(i++));
		regionsmin = Integer.parseInt(in.get(i++));
		regionsmax = Integer.parseInt(in.get(i++));
		zonemin = Integer.parseInt(in.get(i++));
		zonemax = Integer.parseInt(in.get(i++));
		sysmin = Integer.parseInt(in.get(i++));
		sysmax = Integer.parseInt(in.get(i++));
		planetmax = Integer.parseInt(in.get(i++));
		special = Boolean.parseBoolean(in.get(i++));
		multi = Boolean.parseBoolean(in.get(i++));
		name = Boolean.parseBoolean(in.get(i++));
		myName = in.get(i++);
		suns = new int[Integer.parseInt(in.get(i++))];
		for (int k = 0; i < suns.length; k++)
			suns[k] = Integer.parseInt(in.get(i++));
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX + "\n";
		out += sectors + "\n";
		out += regionsmin + "\n";
		out += regionsmax + "\n";
		out += zonemin + "\n";
		out += zonemax + "\n";
		out += sysmin + "\n";
		out += sysmax + "\n";
		out += planetmax + "\n";
		out += special + "\n";
		out += multi + "\n";
		out += name + "\n";
		out += myName + "\n";
		out += suns.length + "\n";
		for (int i = 0; i < suns.length; i++)
			out += suns[i] + "\n";
		return out;
	}

	public static final int CLASSINDEX = 000001;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	private String myID;

	@Override
	public String getID() {
		return myID;
	}

	public static SettingList getDefault() {
		return new SettingList(null, 4, 5, 5, 5, 5, 5, 5, 13, false, false, true, new int[] { 700, 800, 900, 920, 940, 1000, 70, 90, 100, 100 });
	}

}
