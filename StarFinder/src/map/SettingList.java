package map;

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
		myID = "Map.settings";
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
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		sectors = Integer.parseInt(in[i++]);
		regionsmin = Integer.parseInt(in[i++]);
		regionsmax = Integer.parseInt(in[i++]);
		zonemin = Integer.parseInt(in[i++]);
		zonemax = Integer.parseInt(in[i++]);
		sysmin = Integer.parseInt(in[i++]);
		sysmax = Integer.parseInt(in[i++]);
		planetmax = Integer.parseInt(in[i++]);
		special = Boolean.parseBoolean(in[i++]);
		multi = Boolean.parseBoolean(in[i++]);
		name = Boolean.parseBoolean(in[i++]);
		myName = in[i++];
		suns = new int[Integer.parseInt(in[i++])];
		for (int k = 0; i < suns.length; k++)
			suns[k] = Integer.parseInt(in[i++]);
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

}
