package planetary;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Jovian;
import astronomy.planetary.Moon;
import astronomy.planetary.Terrestrial;
import engine.Savable;
import utilities.StringFundementals;

public class Colony implements Savable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708609165970622356L;
	private int size;
	private int scale;

	private boolean isHab;
	private boolean hasWater;
	private boolean hasGravite;
	private boolean hasRareGasses;
	private boolean hasRareMetals;
	private boolean hasRadiotropes;
	private boolean hasMassiveMetal;
	private boolean hasMassiveGasses;
	private boolean hasBio;
	private boolean isWaste;
	private int maxSize;

	private Ecosystem myEcosystem;
	private Habitation myColony;

	public Colony(int size, int scale, boolean isHab, boolean hasWater, boolean hasGravite, boolean hasRareGasses,
			boolean hasRareMetals, boolean hasRadiotropes, boolean hasMassiveMetal, boolean hasMassiveGasses,
			boolean hasBio, boolean isWaste, Ecosystem myEcosystem) {
		super();
		this.size = size;
		this.scale = scale;
		this.isHab = isHab;
		this.hasWater = hasWater;
		this.hasGravite = hasGravite;
		this.hasRareGasses = hasRareGasses;
		this.hasRareMetals = hasRareMetals;
		this.hasRadiotropes = hasRadiotropes;
		this.hasMassiveMetal = hasMassiveMetal;
		this.hasMassiveGasses = hasMassiveGasses;
		this.hasBio = hasBio;
		this.isWaste = isWaste;
		this.myEcosystem = myEcosystem;
		myID = UUID.randomUUID().toString() + ".Surface";
	}

	public Colony(String load) {
		myID = UUID.randomUUID().toString();
		this.loadString(load);
	}

	static Random random = new Random(System.currentTimeMillis());

	public static Colony randomTerrestrial(Terrestrial t) {
		boolean Habitable = false;
		boolean Water = t.getMyWater() > 0.05;
		boolean Gravite = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = false;
		boolean isWaste = false;

		Colony c = new Colony(0, 0, Habitable, Water, Gravite, RareGas, RareMetal, Radio, MassMetal, MassGas, Life,
				isWaste, null);
		c.setMaxSize(Colony.calculateMaxSize(t.getMyRadius()));
		return c;
	}

	public static Colony randomHabtiable(Habitable p) {

		Ecosystem biosphere = Ecosystem.randomEcosystem(p);
		boolean Habitable = true;
		boolean Water = p.getMyWater() > 0.05;
		boolean Gravite = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = true;
		boolean isWaste = false;

		Colony c = new Colony(0, 0, Habitable, Water, Gravite, RareGas, RareMetal, Radio, MassMetal, MassGas, Life,
				isWaste, biosphere);
		c.setMaxSize(Colony.calculateMaxSize(p.getMyRadius()));
		return c;
	}

	public static Colony randomHabMoon(HabitableMoon p) {

		Ecosystem biosphere = Ecosystem.randomEcosystem(p);
		boolean Habitable = true;
		boolean Water = p.getMyWater() > 0.05;
		boolean Gravite = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = true;
		boolean isWaste = false;

		Colony c = new Colony(0, 0, Habitable, Water, Gravite, RareGas, RareMetal, Radio, MassMetal, MassGas, Life,
				isWaste, biosphere);
		c.setMaxSize(Colony.calculateMaxSize(p.getMyRadius()));
		return c;
	}

	public static Colony randomMoon(Moon t) {
		boolean Habitable = false;
		boolean Water = t.getMyWater() > 0.05;
		boolean Gravite = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = false;
		boolean isWaste = false;

		Colony c = new Colony(0, 0, Habitable, Water, Gravite, RareGas, RareMetal, Radio, MassMetal, MassGas, Life,
				isWaste, null);
		c.setMaxSize(Colony.calculateMaxSize(t.getMyRadius()));
		return c;
	}

	public static Colony randomJovian(Jovian j) {
		boolean Habitable = false;
		boolean Water = random.nextBoolean();
		boolean Gravite = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = true;
		boolean Life = false;
		boolean isWaste = false;

		Colony c = new Colony(0, 0, Habitable, Water, Gravite, RareGas, RareMetal, Radio, MassMetal, MassGas, Life,
				isWaste, null);
		c.setMaxSize(Colony.calculateMaxSize(j.getMyRadius() / 10));
		return c;
	}

	public static int calculateMaxSize(double r) {
		double radius = r / 1000;
		return (int) Math.ceil((5 + Math.sqrt(251 * radius + 58006)) / 251 - 0.5);
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
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

	public boolean isHab() {
		return isHab;
	}

	public boolean isWaste() {
		return isWaste;
	}

	public void setWaste(boolean isWaste) {
		this.isWaste = isWaste;
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

	public boolean isHasGravite() {
		return hasGravite;
	}

	public void setMyColony(Habitation myColony) {
		this.myColony = myColony;
	}

	public Habitation getMyColony() {
		return myColony;
	}

	public void setHasGravite(boolean hasGravite) {
		this.hasGravite = hasGravite;
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

	@Override
	public int loadString(String load) {
		Vector<String> parse = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(parse.get(0));
		myID = in[0];
		int i = 2;
		size = Integer.parseInt(in[i++]);
		scale = Integer.parseInt(in[i++]);
		isHab = Boolean.parseBoolean(in[i++]);
		hasWater = Boolean.parseBoolean(in[i++]);
		hasGravite = Boolean.parseBoolean(in[i++]);
		hasRareGasses = Boolean.parseBoolean(in[i++]);
		hasRareMetals = Boolean.parseBoolean(in[i++]);
		hasRadiotropes = Boolean.parseBoolean(in[i++]);
		hasMassiveMetal = Boolean.parseBoolean(in[i++]);
		hasMassiveGasses = Boolean.parseBoolean(in[i++]);
		hasBio = Boolean.parseBoolean(in[i++]);
		maxSize = Integer.parseInt(in[i++]);
		boolean dev = Boolean.parseBoolean(in[i++]);
		if (dev)
			myColony = new Habitation(parse.get(1));
		boolean eco = Boolean.parseBoolean(in[i++]);
		if (eco)
			myEcosystem = new Ecosystem(parse.get(1));
		return i;
	}

	private int EcosystemID;
	int DevelopmentsID;

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += size + "\n";
		out += scale + "\n";
		out += isHab + "\n";
		out += hasWater + "\n";
		out += hasGravite + "\n";
		out += hasRareGasses + "\n";
		out += hasRareMetals + "\n";
		out += hasRadiotropes + "\n";
		out += hasMassiveMetal + "\n";
		out += hasMassiveGasses + "\n";
		out += hasBio + "\n";
		out += isWaste + "\n";
		out += maxSize + "\n";
		if (myColony != null) {
			out += true + "\n";
			out += "{\n";
			out += myColony.saveString() + "\n";
			out += "}\n";
		} else {
			out += false + "\n";
		}
		if (myEcosystem != null) {
			out += true + "\n";
			out += "{\n";
			out += myEcosystem.saveString() + "\n";
			out += "}\n";
		} else {
			out += false + "\n";
		}
		return out;
	}

	public static final int CLASSINDEX = 119017;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getEcosystemID() {
		return EcosystemID;
	}

	public void setEcosystemID(int ecosystemID) {
		EcosystemID = ecosystemID;
	}
}