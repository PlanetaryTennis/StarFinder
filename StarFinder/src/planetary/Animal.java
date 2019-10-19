package planetary;

import java.util.Random;

public class Animal extends Life {
	
	private boolean canWalk;
	private boolean canSwim;
	private boolean canFly;
	private boolean canBurrow;
	private boolean canSee;
	private boolean canWork;
	private boolean laysEggs;
	private boolean hasMilk;
	private boolean thickHide;
	private boolean isPosionous;
	private boolean isVenomous;
	private boolean canCamo;
	private boolean eatsMeat;
	private boolean eatsPlants;
	private boolean eatsMetal;
	
	private int legPairs;
	private int wingPairs;
	private int eyes;
	private int sizeCatagory;
	private int posionCatagory;
	private int venomCatagory;
	private int birthRate;
	private int ageRate;
	private int oldAge;
	
	public static Random ran = new Random(System.currentTimeMillis());
	
	public static Animal randomStandard(Condition c) {
		return null;
	}

	public static Animal randomPest(Condition c) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Animal randomApexPreditor(Condition c) {
		// TODO Auto-generated method stub
		return null;
	}

}
