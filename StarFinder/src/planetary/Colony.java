package planetary;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Jovian;
import astronomy.planetary.Moon;
import astronomy.planetary.Terrestrial;
import engine.Savable;
import utilities.StringFundementals;

public class Colony implements Savable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3708609165970622356L;
	private int size;
	private int scale;
	
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
	
	public Colony(int size, int scale, boolean isHab, boolean hasWater, boolean hasEzo,
			boolean hasRareGasses, boolean hasRareMetals, boolean hasRadiotropes, boolean hasMassiveMetal,
			boolean hasMassiveGasses, boolean hasBio, Ecosystem myEcosystem, SepcialDevelopments myDevelopments) {
		super();
		this.size = size;
		this.scale = scale;
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
		myID = UUID.randomUUID().toString()+".Surface";
	}
	
	public Colony(String load) {
		myID = UUID.randomUUID().toString();
		this.loadString(load);
	}

	static Random random = new Random(System.currentTimeMillis());

	public static Colony randomTerrestrial(Terrestrial t) {
		boolean Habitable = false;
		boolean Water = t.getMyWater()>0.05;
		boolean Ezo = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = false;
		
		Colony c = new Colony(0,0, Habitable, Water, Ezo, RareGas, 
				RareMetal, Radio, MassMetal, MassGas, Life, 
				null, null);
		return c;		
	}

	public static Colony randomHabtiable(Habitable p) {

		Ecosystem biosphere = Ecosystem.randomEcosystem(p);
		boolean Habitable = true;
		boolean Water = p.getMyWater()>0.05;
		boolean Ezo = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = true;
		
		Colony c = new Colony(0,0,Habitable, Water, Ezo, RareGas, 
				RareMetal, Radio, MassMetal, MassGas, Life, 
				biosphere, null);
		return c;		
	}

	public static Colony randomHabMoon(HabitableMoon p) {	

		Ecosystem biosphere = Ecosystem.randomEcosystem(p);
		boolean Habitable = true;
		boolean Water = p.getMyWater()>0.05;
		boolean Ezo = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = true;
		
		Colony c = new Colony(0,0,Habitable, Water, Ezo, RareGas, 
				RareMetal, Radio, MassMetal, MassGas, Life, 
				biosphere, null);
		return c;		
	}

	public static Colony randomMoon(Moon t) {	
		boolean Habitable = false;
		boolean Water = t.getMyWater()>0.05;
		boolean Ezo = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = random.nextBoolean();
		boolean Life = false;
		
		Colony c = new Colony(0,0,Habitable, Water, Ezo, RareGas, 
				RareMetal, Radio, MassMetal, MassGas, Life, 
				null, null);
		return c;				
	}

	public static Colony randomJovian(Jovian j) {	
		boolean Habitable = false;
		boolean Water = random.nextBoolean();
		boolean Ezo = random.nextBoolean();
		boolean RareGas = random.nextBoolean();
		boolean RareMetal = random.nextBoolean();
		boolean Radio = random.nextBoolean();
		boolean MassMetal = random.nextBoolean();
		boolean MassGas = true;
		boolean Life = false;
		
		Colony c = new Colony(0,0,Habitable, Water, Ezo, RareGas, 
				RareMetal, Radio, MassMetal, MassGas, Life, 
				null, null);
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

	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		size  = Integer.parseInt(in[i++]);
		scale  = Integer.parseInt(in[i++]);
		isHab  = Boolean.parseBoolean(in[i++]);
		hasWater  = Boolean.parseBoolean(in[i++]);
		hasEzo  = Boolean.parseBoolean(in[i++]);
		hasRareGasses  = Boolean.parseBoolean(in[i++]);
		hasRareMetals  = Boolean.parseBoolean(in[i++]);
		hasRadiotropes  = Boolean.parseBoolean(in[i++]);
		hasMassiveMetal  = Boolean.parseBoolean(in[i++]);
		hasMassiveGasses  = Boolean.parseBoolean(in[i++]);
		hasBio  = Boolean.parseBoolean(in[i++]);
//		EcosystemID  = in[i++];
//		DevelopmentsID  = in[i++];
		return i;
	}
	
	private int EcosystemID;
	int DevelopmentsID;

	@Override
	public String saveString() {
		String out = "";
		out += myID +"\n";
		out += getClassIndex() + "\n";
		out += size + "\n";
		out += scale + "\n";
		out += isHab + "\n";
		out += hasWater + "\n";
		out += hasEzo + "\n";
		out += hasRareGasses + "\n";
		out += hasRareMetals + "\n";
		out += hasRadiotropes + "\n";
		out += hasMassiveMetal + "\n";
		out += hasMassiveGasses + "\n";
		out += hasBio + "\n";
//		out += myEcosystem.getID() + "\n";
//		out += myDevelopments.getID() + "\n";
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