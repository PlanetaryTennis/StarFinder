package planetary;

import astronomy.Terrestrial;

public class Colony {

	private int size;
	private int scale;
	private int hostility;
	
	private boolean isHab;
	private boolean hasWater;
	private boolean hasEzo;
	private boolean hasRareGasses;
	private boolean hasRareMetals;
	private boolean hasRadiotropes;
	private boolean hasMassiveMetal;
	private boolean hasMassiveGasses;
	private boolean hasBio;
	
	private Ecosystem myEcosystem;
	private SepcialDevelopments myDevelopments;
	
	public Colony(int size, int scale, int hostility, boolean isHab, boolean hasWater, boolean hasEzo,
			boolean hasRareGasses, boolean hasRareMetals, boolean hasRadiotropes, boolean hasMassiveMetal,
			boolean hasMassiveGasses, boolean hasBio, Ecosystem myEcosystem, SepcialDevelopments myDevelopments) {
		super();
		this.size = size;
		this.scale = scale;
		this.hostility = hostility;
		this.isHab = isHab;
		this.hasWater = hasWater;
		this.hasEzo = hasEzo;
		this.hasRareGasses = hasRareGasses;
		this.hasRareMetals = hasRareMetals;
		this.hasRadiotropes = hasRadiotropes;
		this.hasMassiveMetal = hasMassiveMetal;
		this.hasMassiveGasses = hasMassiveGasses;
		this.hasBio = hasBio;
		this.myEcosystem = myEcosystem;
		this.myDevelopments = myDevelopments;
	}

	public static Colony randomTerrestrial(Terrestrial t) {
		
		Colony c = new Colony(0,0,0, true, true, true, true, true, true, true, true, true, null, null);
		return c;		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getHostility() {
		return hostility;
	}

	public void setHostility(int hostility) {
		this.hostility = hostility;
	}

	public boolean isHab() {
		return isHab;
	}

	public void setHab(boolean isHab) {
		this.isHab = isHab;
	}

	public boolean isHasWater() {
		return hasWater;
	}

	public void setHasWater(boolean hasWater) {
		this.hasWater = hasWater;
	}

	public boolean isHasEzo() {
		return hasEzo;
	}

	public void setHasEzo(boolean hasEzo) {
		this.hasEzo = hasEzo;
	}

	public boolean isHasRareGasses() {
		return hasRareGasses;
	}

	public void setHasRareGasses(boolean hasRareGasses) {
		this.hasRareGasses = hasRareGasses;
	}

	public boolean isHasRareMetals() {
		return hasRareMetals;
	}

	public void setHasRareMetals(boolean hasRareMetals) {
		this.hasRareMetals = hasRareMetals;
	}

	public boolean isHasRadiotropes() {
		return hasRadiotropes;
	}

	public void setHasRadiotropes(boolean hasRadiotropes) {
		this.hasRadiotropes = hasRadiotropes;
	}

	public boolean isHasMassiveMetal() {
		return hasMassiveMetal;
	}

	public void setHasMassiveMetal(boolean hasMassiveMetal) {
		this.hasMassiveMetal = hasMassiveMetal;
	}

	public boolean isHasMassiveGasses() {
		return hasMassiveGasses;
	}

	public void setHasMassiveGasses(boolean hasMassiveGasses) {
		this.hasMassiveGasses = hasMassiveGasses;
	}

	public boolean isHasBio() {
		return hasBio;
	}

	public void setHasBio(boolean hasBio) {
		this.hasBio = hasBio;
	}

	public Ecosystem getMyEcosystem() {
		return myEcosystem;
	}

	public void setMyEcosystem(Ecosystem myEcosystem) {
		this.myEcosystem = myEcosystem;
	}

	public SepcialDevelopments getMyDevelopments() {
		return myDevelopments;
	}

	public void setMyDevelopments(SepcialDevelopments myDevelopments) {
		this.myDevelopments = myDevelopments;
	}
