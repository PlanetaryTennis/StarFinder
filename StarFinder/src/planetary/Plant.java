package planetary;

import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Plant extends Life {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7778789757735703999L;
	private boolean doesFruit;
	private boolean hasMaterial;
	private boolean isCarnivorous;
	private boolean isPosionous;
	private boolean isVenomous;
	private boolean canCamo;
	private int sizeCatagory;
	private int posionCatagory;
	private int venomCatagory;

	private int birthRate;
	private int ageRate;
	private int oldAge;

	public static Random ran = new Random(System.currentTimeMillis());

	public Plant() {
		myID = UUID.randomUUID().toString() + ".Life";
	}

	public Plant(String load) {
		this.loadString(load);
	}

	public static Plant random(Condition c) {
		Plant out = new Plant();
		out.doesFruit = ran.nextBoolean() || ran.nextBoolean();
		out.hasMaterial = ran.nextBoolean() || ran.nextBoolean();
		out.isCarnivorous = ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean();
		if (ran.nextBoolean() && ran.nextBoolean()) {
			out.setPosionous(true);
			out.setPosionCatagory((int) Math.ceil(ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)
					- ExtendedMathmatics.log(ran.nextInt(32) + 1, 2) + 5));
		} else {
			out.setPosionous(false);
			out.setPosionCatagory(0);
		}
		if (ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean()) {
			out.setVenomous(true);
			out.setVenomCatagory((int) Math.floor(ExtendedMathmatics.log(ran.nextInt(31) + 2, 2)
					- ExtendedMathmatics.log(ran.nextInt(32) + 1, 2) + 5));
		} else {
			out.setVenomous(false);
			out.setVenomCatagory(0);
		}
		out.setCanCamo(ran.nextBoolean() && ran.nextBoolean() && ran.nextBoolean());
		out.setSizeCatagory((int) Math.ceil(ran.nextDouble() * (11 - c.getGravityIndex()) * 10));

		out.birthRate = ran.nextInt((int) Math.pow(2, ran.nextInt(10) + 1)) + 1;
		out.oldAge = ran.nextInt((int) Math.pow(2, ran.nextInt(10) + 1))/1000 + 1;
		out.ageRate = (int) (out.oldAge * (double) (ran.nextInt(4) + 1) / 2);
		return out;
	}

	@Override
	public int loadString(String load) {
		Vector<String> in = StringFundementals.breakByLine(load);
		myID = in.get(0);
		int i = 2;
		doesFruit = Boolean.parseBoolean(in.get(i++));
		hasMaterial = Boolean.parseBoolean(in.get(i++));
		isCarnivorous = Boolean.parseBoolean(in.get(i++));
		isPosionous = Boolean.parseBoolean(in.get(i++));
		isVenomous = Boolean.parseBoolean(in.get(i++));
		canCamo = Boolean.parseBoolean(in.get(i++));
		sizeCatagory = Integer.parseInt(in.get(i++));
		posionCatagory = Integer.parseInt(in.get(i++));
		venomCatagory = Integer.parseInt(in.get(i++));
		birthRate = Integer.parseInt(in.get(i++));
		ageRate = Integer.parseInt(in.get(i++));
		oldAge = Integer.parseInt(in.get(i++));
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += doesFruit + "\n";
		out += hasMaterial + "\n";
		out += isCarnivorous + "\n";
		out += isPosionous + "\n";
		out += isVenomous + "\n";
		out += canCamo + "\n";
		out += sizeCatagory + "\n";
		out += posionCatagory + "\n";
		out += venomCatagory + "\n";
		out += birthRate + "\n";
		out += ageRate + "\n";
		out += oldAge + "\n";
		return out;
	}

	public static final int CLASSINDEX = 112390;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public boolean isCanCamo() {
		return canCamo;
	}

	public void setCanCamo(boolean canCamo) {
		this.canCamo = canCamo;
	}

	public boolean isDoesFruit() {
		return doesFruit;
	}

	public void setDoesFruit(boolean doesFruit) {
		this.doesFruit = doesFruit;
	}

	public boolean isHasMaterial() {
		return hasMaterial;
	}

	public void setHasMaterial(boolean hasMaterial) {
		this.hasMaterial = hasMaterial;
	}

	public boolean isCarnivorous() {
		return isCarnivorous;
	}

	public void setCarnivorous(boolean isCarnivorous) {
		this.isCarnivorous = isCarnivorous;
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

}
