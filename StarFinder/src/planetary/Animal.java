package planetary;

import java.util.Random;
import java.util.UUID;

import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Animal extends Life {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2775509875562709159L;
	// Motion
	private boolean canWalk;
	private boolean canSwim;
	private boolean canFly;
	private boolean canBurrow;

	// Senses
	private boolean canSee;
	private boolean canHear;
	private boolean canSmell;

	// functions
	private boolean canWork;
	private boolean laysEggs;
	private boolean hasMilk;
	private boolean thickHide;
	private boolean isPosionous;
	private boolean isVenomous;
	private boolean canCamo;
	private int sizeCatagory;
	private int posionCatagory;
	private int venomCatagory;

	// Diet
	private boolean eatsMeat;
	private boolean eatsPlants;
	private boolean eatsMetal;

	// design
	private int legPairs;
	private int wingPairs;
	private int heavyManipulatorPairs;
	private int manipulatorPairs;
	private int fineManipulatorPairs;
	private int eyes;
	private int birthRate;
	private int ageRate;
	private int oldAge;

	public static Random ran = new Random(System.currentTimeMillis());

	public Animal() {
		myID = UUID.randomUUID().toString() + ".Life";
	}

	public Animal(String load) {
		this.loadString(load);
	}

	public static Animal randomStandard(Condition c) {
		Animal out = new Animal();
		randomMotions(c, out);
		randomSenses(c, out);
		randomFunctions(c, out);
		randomDiet(c, out);
		randomDesign(c, out);
		out.setAgeRate((int) (out.getOldAge() * (((double) (ran.nextInt(10) + 1) / 10) + 0.5)));
		out.setBirthRate(ran.nextInt(12) + 1);
		return out;
	}

	public static Animal randomPest(Condition c) {
		Animal out = new Animal();
		randomMotions(c, out);
		randomSenses(c, out);
		randomFunctions(c, out);
		randomDiet(c, out);
		randomDesign(c, out);
		out.setAgeRate((int) (out.getOldAge() * (((double) (ran.nextInt(10) + 1) / 10) + 0.5)) * 12);
		out.setBirthRate(ran.nextInt(24) + 1);
		return out;
	}

	public static Animal randomApexPreditor(Condition c) {
		Animal out = new Animal();
		randomMotions(c, out);
		randomSenses(c, out);
		randomFunctions(c, out);
		randomDiet(c, out);
		if (!out.isVenomous && ran.nextBoolean() && ran.nextBoolean()) {
			out.setVenomous(true);
			out.setVenomCatagory((int) Math.floor(ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)
					- ExtendedMathmatics.log(ran.nextInt(32) + 1, 2) + 5));
		}
		randomDesign(c, out);
		out.setEatsMeat(true);
		out.setAgeRate((int) (out.getOldAge() * (((double) (ran.nextInt(10) + 1) / 10.0) + 0.5)));
		out.setBirthRate(ran.nextInt(6) + 1);
		return out;
	}

	private static void randomDesign(Condition c, Animal animal) {
		if (animal.canWalk) {
			animal.setLegPairs(ran.nextInt(4) + 1);
		} else {
			animal.setLegPairs((int) Math.floor(5 - ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)));
		}
		if (animal.canFly) {
			animal.setWingPairs(ran.nextInt(3) + 1);
		} else {
			animal.setWingPairs((int) Math.floor((5 - ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)) / 2));
		}
		if (animal.canBurrow || animal.canBurrow) {
			animal.setHeavyManipulatorPairs(ran.nextInt(2) * ran.nextInt(3));
		} else {
			animal.setHeavyManipulatorPairs(ran.nextInt(2) * ran.nextInt(2));
		}
		animal.setFineManipulatorPairs(ran.nextInt(2) * ran.nextInt(3) * ran.nextInt(2));
		animal.setManipulatorPairs(ran.nextInt(2) * ran.nextInt(3));
		if (animal.canSee) {
			animal.setEyes((ran.nextInt(4) + 1) * 2 + ran.nextInt(2) * ran.nextInt(2) * ran.nextInt(2));
		} else {
			animal.setEyes((ran.nextInt(4) * ran.nextInt(2)) * 2 + ran.nextInt(2) * ran.nextInt(2) * ran.nextInt(2));
		}
		animal.setOldAge((int) Math.ceil(ran.nextInt(400) / (ran.nextInt(200) + 1)) + 1);
	}

	private static void randomDiet(Condition c, Animal animal) {
		animal.setEatsMeat(ran.nextBoolean());
		animal.setEatsPlants(ran.nextBoolean());
		if (!animal.isEatsMeat() && !animal.isEatsPlants()) {
			boolean coin = ran.nextBoolean();
			if (coin) {
				animal.setEatsMeat(true);
			} else {
				animal.setEatsPlants(true);
			}
		}
		animal.setEatsMetal(
				ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean());
	}

	public static void randomFunctions(Condition c, Animal animal) {
		animal.setCanWork(ran.nextBoolean());
		animal.setLaysEggs(ran.nextBoolean());
		animal.setHasMilk((ran.nextBoolean() && ran.nextBoolean()) || (!animal.laysEggs && ran.nextBoolean()));
		animal.setThickHide(ran.nextBoolean());
		if (ran.nextBoolean() && ran.nextBoolean()) {
			animal.setPosionous(true);
			animal.setPosionCatagory((int) Math.ceil(ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)
					- ExtendedMathmatics.log(ran.nextInt(32) + 1, 2) + 5));
		} else {
			animal.setPosionous(false);
			animal.setPosionCatagory(0);
		}
		if (ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean()) {
			animal.setVenomous(true);
			animal.setVenomCatagory((int) Math.floor(ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)
					- ExtendedMathmatics.log(ran.nextInt(32) + 1, 2) + 5));
		} else {
			animal.setVenomous(false);
			animal.setVenomCatagory(0);
		}
		animal.setCanCamo(ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean());
		animal.setSizeCatagory((int) Math.ceil(ran.nextDouble() * (11 - c.getGravityIndex()) * 10));
	}

	public static void randomSenses(Condition c, Animal animal) {
		animal.setCanSee(shouldSee(c));
		animal.setCanHear(shouldHear(c));
		animal.setCanSmell(ran.nextBoolean() || ran.nextBoolean());
	}

	private static boolean shouldSee(Condition c) {
		int chance = 0;
		chance += c.getAtmosphericIndex() * 3;
		return (ran.nextInt(100) > chance);
	}

	public static boolean shouldHear(Condition c) {
		int chance = 0;
		chance += (10 - c.getAtmosphericIndex()) * 5;
		return (ran.nextInt(100) > chance);
	}

	public static void randomMotions(Condition c, Animal animal) {
		animal.setCanWalk(shouldWalk(c));
		animal.setCanSwim(shouldSwim(c));
		if (!animal.isCanWalk() && !animal.isCanSwim()) {
			boolean coin = ran.nextBoolean();
			if (coin) {
				animal.setCanSwim(true);
			} else {
				animal.setCanWalk(true);
			}
		}
		animal.setCanFly(shouldFly(c));
		animal.setCanBurrow(ran.nextBoolean() && ran.nextBoolean());
	}

	private static boolean shouldFly(Condition c) {
		int chance = 0;
		chance += (10 - c.getAtmosphericIndex()) * 3;
		chance += c.getGravityIndex() * 7;
		return (ran.nextInt(100) > chance);
	}

	private static boolean shouldWalk(Condition c) {
		int chance = 0;
		chance += c.getWaterIndex() * 8;
		return (ran.nextInt(100) > chance);
	}

	private static boolean shouldSwim(Condition c) {
		int chance = 0;
		chance += (10 - c.getWaterIndex()) * 8;
		return (ran.nextInt(100) > chance);
	}

	public boolean isCanWalk() {
		return canWalk;
	}

	public boolean isCanHear() {
		return canHear;
	}

	public void setCanHear(boolean canHear) {
		this.canHear = canHear;
	}

	public boolean isCanSmell() {
		return canSmell;
	}

	public void setCanSmell(boolean canSmell) {
		this.canSmell = canSmell;
	}

	public int getHeavyManipulatorPairs() {
		return heavyManipulatorPairs;
	}

	public void setHeavyManipulatorPairs(int heavyManipulatorPairs) {
		this.heavyManipulatorPairs = heavyManipulatorPairs;
	}

	public int getManipulatorPairs() {
		return manipulatorPairs;
	}

	public void setManipulatorPairs(int manipulatorPairs) {
		this.manipulatorPairs = manipulatorPairs;
	}

	public int getFineManipulatorPairs() {
		return fineManipulatorPairs;
	}

	public void setFineManipulatorPairs(int fineManipulatorPairs) {
		this.fineManipulatorPairs = fineManipulatorPairs;
	}

	public void setCanWalk(boolean canWalk) {
		this.canWalk = canWalk;
	}

	public boolean isCanSwim() {
		return canSwim;
	}

	public void setCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}

	public boolean isCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public boolean isCanBurrow() {
		return canBurrow;
	}

	public void setCanBurrow(boolean canBurrow) {
		this.canBurrow = canBurrow;
	}

	public boolean isCanSee() {
		return canSee;
	}

	public void setCanSee(boolean canSee) {
		this.canSee = canSee;
	}

	public boolean isCanWork() {
		return canWork;
	}

	public void setCanWork(boolean canWork) {
		this.canWork = canWork;
	}

	public boolean isLaysEggs() {
		return laysEggs;
	}

	public void setLaysEggs(boolean laysEggs) {
		this.laysEggs = laysEggs;
	}

	public boolean isHasMilk() {
		return hasMilk;
	}

	public void setHasMilk(boolean hasMilk) {
		this.hasMilk = hasMilk;
	}

	public boolean isThickHide() {
		return thickHide;
	}

	public void setThickHide(boolean thickHide) {
		this.thickHide = thickHide;
	}

	public boolean isPosionous() {
		return isPosionous;
	}

	public void setPosionous(boolean isPosionous) {
		this.isPosionous = isPosionous;
	}

	public boolean isVenomous() {
		return isVenomous;
	}

	public void setVenomous(boolean isVenomous) {
		this.isVenomous = isVenomous;
	}

	public boolean isCanCamo() {
		return canCamo;
	}

	public void setCanCamo(boolean canCamo) {
		this.canCamo = canCamo;
	}

	public boolean isEatsMeat() {
		return eatsMeat;
	}

	public void setEatsMeat(boolean eatsMeat) {
		this.eatsMeat = eatsMeat;
	}

	public boolean isEatsPlants() {
		return eatsPlants;
	}

	public void setEatsPlants(boolean eatsPlants) {
		this.eatsPlants = eatsPlants;
	}

	public boolean isEatsMetal() {
		return eatsMetal;
	}

	public void setEatsMetal(boolean eatsMetal) {
		this.eatsMetal = eatsMetal;
	}

	public int getLegPairs() {
		return legPairs;
	}

	public void setLegPairs(int legPairs) {
		this.legPairs = legPairs;
	}

	public int getWingPairs() {
		return wingPairs;
	}

	public void setWingPairs(int wingPairs) {
		this.wingPairs = wingPairs;
	}

	public int getEyes() {
		return eyes;
	}

	public void setEyes(int eyes) {
		this.eyes = eyes;
	}

	public int getSizeCatagory() {
		return sizeCatagory;
	}

	public void setSizeCatagory(int sizeCatagory) {
		this.sizeCatagory = sizeCatagory;
	}

	public int getPosionCatagory() {
		return posionCatagory;
	}

	public void setPosionCatagory(int posionCatagory) {
		this.posionCatagory = posionCatagory;
	}

	public int getVenomCatagory() {
		return venomCatagory;
	}

	public void setVenomCatagory(int venomCatagory) {
		this.venomCatagory = venomCatagory;
	}

	public int getBirthRate() {
		return birthRate;
	}

	public void setBirthRate(int birthRate) {
		this.birthRate = birthRate;
	}

	public int getAgeRate() {
		return ageRate;
	}

	public void setAgeRate(int ageRate) {
		this.ageRate = ageRate;
	}

	public int getOldAge() {
		return oldAge;
	}

	public void setOldAge(int oldAge) {
		this.oldAge = oldAge;
	}

	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		canWalk = Boolean.parseBoolean(in[i++]);
		canSwim = Boolean.parseBoolean(in[i++]);
		canFly = Boolean.parseBoolean(in[i++]);
		canBurrow = Boolean.parseBoolean(in[i++]);
		canSee = Boolean.parseBoolean(in[i++]);
		canHear = Boolean.parseBoolean(in[i++]);
		canSmell = Boolean.parseBoolean(in[i++]);
		canWork = Boolean.parseBoolean(in[i++]);
		laysEggs = Boolean.parseBoolean(in[i++]);
		hasMilk = Boolean.parseBoolean(in[i++]);
		thickHide = Boolean.parseBoolean(in[i++]);
		isPosionous = Boolean.parseBoolean(in[i++]);
		isVenomous = Boolean.parseBoolean(in[i++]);
		canCamo = Boolean.parseBoolean(in[i++]);
		sizeCatagory = Integer.parseInt(in[i++]);
		posionCatagory = Integer.parseInt(in[i++]);
		venomCatagory = Integer.parseInt(in[i++]);
		eatsMeat = Boolean.parseBoolean(in[i++]);
		eatsPlants = Boolean.parseBoolean(in[i++]);
		eatsMetal = Boolean.parseBoolean(in[i++]);
		legPairs = Integer.parseInt(in[i++]);
		wingPairs = Integer.parseInt(in[i++]);
		heavyManipulatorPairs = Integer.parseInt(in[i++]);
		manipulatorPairs = Integer.parseInt(in[i++]);
		fineManipulatorPairs = Integer.parseInt(in[i++]);
		eyes = Integer.parseInt(in[i++]);
		birthRate = Integer.parseInt(in[i++]);
		ageRate = Integer.parseInt(in[i++]);
		oldAge = Integer.parseInt(in[i++]);
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += canWalk + "\n";
		out += canSwim + "\n";
		out += canFly + "\n";
		out += canBurrow + "\n";
		out += canSee + "\n";
		out += canHear + "\n";
		out += canSmell + "\n";
		out += canWork + "\n";
		out += laysEggs + "\n";
		out += hasMilk + "\n";
		out += thickHide + "\n";
		out += isPosionous + "\n";
		out += isVenomous + "\n";
		out += canCamo + "\n";
		out += sizeCatagory + "\n";
		out += posionCatagory + "\n";
		out += venomCatagory + "\n";
		out += eatsMeat + "\n";
		out += eatsPlants + "\n";
		out += eatsMetal + "\n";
		out += legPairs + "\n";
		out += wingPairs + "\n";
		out += heavyManipulatorPairs + "\n";
		out += manipulatorPairs + "\n";
		out += fineManipulatorPairs + "\n";
		out += eyes + "\n";
		out += birthRate + "\n";
		out += ageRate + "\n";
		out += oldAge + "\n";
		return out;
	}

	public static final int CLASSINDEX = 112345;

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
