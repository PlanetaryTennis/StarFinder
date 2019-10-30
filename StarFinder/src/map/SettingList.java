package map;

import java.util.UUID;

import engine.Savable;

public class SettingList implements Savable{
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
	
	public SettingList(String myName,int sectors, int regionsmin, int regionsmax, int zonemin, int zonemax, int sysmin, int sysmax,
			int planetmax, boolean special, boolean multi, boolean name,int[] suns) {
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
		int i = 2;
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
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
