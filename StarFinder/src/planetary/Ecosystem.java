package planetary;

import astronomy.Habitable;
import astronomy.HabitableMoon;

public class Ecosystem {
	
	private Animal ApexPreditor;
	private Animal Standard;
	private Animal Pest;
	
	private Plant Primary;
	private Plant Secondary;
	
	private Condition myConditions;
	
	public static Ecosystem randomEcosystem(Habitable p) {
		Condition C = p.getMyCondition();
		Animal AP = Animal.randomApexPreditor(C);
		Animal S = Animal.randomStandard(C);
		Animal P = Animal.randomPest(C);
		
		Plant Pr = Plant.random(C);
		Plant Sc = Plant.random(C);
		return null;
	}

	public static Ecosystem randomEcosystem(HabitableMoon p) {
//		myConditions = p.getMyCondition();		
		return null;
	}
}
