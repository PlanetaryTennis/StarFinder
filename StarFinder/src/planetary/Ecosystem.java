package planetary;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import engine.Savable;
import utilities.StringFundementals;

public class Ecosystem  implements Serializable, Savable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -995386838754462726L;
	private Animal ApexPreditor;
	private Animal Standard;
	private Animal Pest;
	
	private Plant Primary;
	private Plant Secondary;
	
	private Condition myConditions;
	
	public Ecosystem(Animal Apex,Animal Stan, Animal Pes, Plant Pri, Plant Sec,Condition con) {
		ApexPreditor = Apex;
		Standard = Stan;
		Pest = Pes;
		Primary = Pri;
		Secondary = Sec;
		myConditions = con;
		myID = UUID.randomUUID().toString()+".Surface";
	}
	
	public Ecosystem(String load) {
		this.loadString(load);
	}
	
	public Animal getApexPreditor() {
		return ApexPreditor;
	}

	public void setApexPreditor(Animal apexPreditor) {
		ApexPreditor = apexPreditor;
	}

	public Animal getStandard() {
		return Standard;
	}

	public void setStandard(Animal standard) {
		Standard = standard;
	}

	public Animal getPest() {
		return Pest;
	}

	public void setPest(Animal pest) {
		Pest = pest;
	}

	public Plant getPrimary() {
		return Primary;
	}

	public void setPrimary(Plant primary) {
		Primary = primary;
	}

	public Plant getSecondary() {
		return Secondary;
	}

	public void setSecondary(Plant secondary) {
		Secondary = secondary;
	}

	public Condition getMyConditions() {
		return myConditions;
	}

	public void setMyConditions(Condition myConditions) {
		this.myConditions = myConditions;
	}

	public static Ecosystem randomEcosystem(Habitable p) {
		Condition C = p.getMyCondition();
		Animal AP = Animal.randomApexPreditor(C);
		Animal S = Animal.randomStandard(C);
		Animal P = Animal.randomPest(C);
		
		Plant Pr = Plant.random(C);
		Plant Sc = Plant.random(C);
		return new Ecosystem(AP, S, P, Pr, Sc, C);
	}

	public static Ecosystem randomEcosystem(HabitableMoon p) {
		Condition C = p.getMyCondition();
		Animal AP = Animal.randomApexPreditor(C);
		Animal S = Animal.randomStandard(C);
		Animal P = Animal.randomPest(C);
		
		Plant Pr = Plant.random(C);
		Plant Sc = Plant.random(C);
		return new Ecosystem(AP, S, P, Pr, Sc, C);
	}

	@Override
	public int loadString(String load) {
		Vector<String> proccess = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(proccess.get(0));
		myID = in[0];
		setApexPreditor(new Animal(proccess.get(1)));
		setStandard(new Animal(proccess.get(2)));
		setPest(new Animal(proccess.get(3)));
//		setPrimary(new Plant(proccess.get(4)));
//		setSecondary(new Plant(proccess.get(5)));
		return 0;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX +"\n";
		out += "{\n";
		out += ApexPreditor.saveString() + "\n";
		out += "}\n";
		out += "{\n";
		out += Standard.saveString() + "\n";
		out += "}\n";
		out += "{\n";
		out += Pest.saveString() + "\n";
		out += "}\n";
//		out += "{\n";
//		out += Primary.saveString() + "\n";
//		out += "}\n";
//		out += "{\n";
//		out += Secondary.saveString() + "\n";
//		out += "}\n";
		return out;
	}

	public static final int CLASSINDEX = 112377;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;
	
	@Override
	public String getID() {
		return myID;
	}
}
