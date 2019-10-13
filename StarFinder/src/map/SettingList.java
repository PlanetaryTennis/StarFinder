package map;

public class SettingList {
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
	
	int[] suns;
	
	public SettingList(int sectors, int regionsmin, int regionsmax, int zonemin, int zonemax, int sysmin, int sysmax,
			int planetmax, boolean special, boolean multi, boolean name,int[] suns) {
		super();
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
	
	
}
