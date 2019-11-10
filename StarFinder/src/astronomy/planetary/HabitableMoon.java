package astronomy.planetary;

import java.util.Vector;

import astronomy.LifeBearing;
import astronomy.OrbitObject;
import astronomy.stellar.Star;
import planetary.Colony;
import planetary.Condition;
import utilities.StringFundementals;

public class HabitableMoon extends Moon implements LifeBearing {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1053665170927162712L;
	public static int total;
	private Condition myCondition;

	public HabitableMoon(String load) {
		this.loadString(load);
	}

	public HabitableMoon(double myAtmosphere, double myRadius, double myMass, double myEccentricity, double myOrbit,
			Star myStar, double myAlbido, double myGreenHouse, double myWater, double myDay, double myMonth,
			double myMoonOrbit) {
		super(myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse, myWater, myDay,
				myMonth, myMoonOrbit);
		myCondition = new Condition(this);
	}

	public Condition getMyCondition() {
		return myCondition;
	}

	public void setMyCondition(Condition myCondition) {
		this.myCondition = myCondition;
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		Vector<String> in = StringFundementals.breakByLine(object.get(0));
		myID = in.get(0);
		int i = 2;
		myName = in.get(i++);
		myAlbido = Double.parseDouble(in.get(i++));
		myAtmosphere = Double.parseDouble(in.get(i++));
		myDay = Double.parseDouble(in.get(i++));
		myDensity = Double.parseDouble(in.get(i++));
		myEccentricity = Double.parseDouble(in.get(i++));
		myGravity = Double.parseDouble(in.get(i++));
		myGreenHouse = Double.parseDouble(in.get(i++));
		myOrbit = Double.parseDouble(in.get(i++));
		myInnerOrbit = Double.parseDouble(in.get(i++));
		myOuterOrbit = Double.parseDouble(in.get(i++));
		myMass = Double.parseDouble(in.get(i++));
		myRadius = Double.parseDouble(in.get(i++));
		myTemps = new double[6];
		myTemps[0] = Double.parseDouble(in.get(i++));
		myTemps[1] = Double.parseDouble(in.get(i++));
		myTemps[2] = Double.parseDouble(in.get(i++));
		myTemps[3] = Double.parseDouble(in.get(i++));
		myTemps[4] = Double.parseDouble(in.get(i++));
		myTemps[5] = Double.parseDouble(in.get(i++));
		myVolume = Double.parseDouble(in.get(i++));
		myWater = Double.parseDouble(in.get(i++));
		myYear = Double.parseDouble(in.get(i++));
		setMyColony(new Colony(object.get(1)));
		setMoonNumber(Integer.parseInt(in.get(i++)));
		int j = 2;
		for (int k = 0; k < getMoonNumber(); k++) {
			getMyMoons().add((Moon) Planet.parseLoad(object.get(j)));
			j++;
		}
		setSatilightNumber(Integer.parseInt(in.get(i++)));
		for (int k = 0; k < getSatilightNumber(); k++) {
			getMySatilights().add(OrbitObject.parseLoad(object.get(j)));
			j++;
		}
		myMonth = Double.parseDouble(in.get(i++));
		myMoonOrbit = Double.parseDouble(in.get(i++));
		setMyCondition(new Condition(object.get(j)));
		return i;
	}

	private String ConditionID;

	@Override
	public String saveString() {
		String out = "";
		out += getID() + "\n";
		out += getClassIndex() + "\n";
		out += getMyName() + "\n";
		out += getMyAlbido() + "\n";
		out += getMyAtmosphere() + "\n";
		out += getMyDay() + "\n";
		out += getMyDensity() + "\n";
		out += getMyEccentricity() + "\n";
		out += getMyGravity() + "\n";
		out += getMyGreenHouse() + "\n";
		out += getMyOrbit() + "\n";
		out += getMyInnerOrbit() + "\n";
		out += getMyOuterOrbit() + "\n";
		out += getMyMass() + "\n";
		out += getMyRadius() + "\n";
		out += getMyTemps()[0] + "\n";
		out += getMyTemps()[1] + "\n";
		out += getMyTemps()[2] + "\n";
		out += getMyTemps()[3] + "\n";
		out += getMyTemps()[4] + "\n";
		out += getMyTemps()[5] + "\n";
		out += getMyVolume() + "\n";
		out += getMyWater() + "\n";
		out += getMyYear() + "\n";
		out += "{\n";
		out += getMyColony().saveString() + "\n";
		out += "}\n";
		out += getMyMoons().size() + "\n";
		for (int i = 0; i < getMyMoons().size(); i++) {
			out += "{\n";
			out += getMyMoons().get(i).saveString() + "\n";
			out += "}\n";
		}
		out += getMySatilights().size() + "\n";
		for (int i = 0; i < getMySatilights().size(); i++) {
			out += "{\n";
			out += getMySatilights().get(i).saveString() + "\n";
			out += "}\n";
		}
		out += getMyMonth() + "\n";
		out += getMyMoonOrbit() + "\n";
		out += "{\n";
		out += getMyCondition().saveString() + "\n";
		out += "}\n";
		return out;
	}

	public static final int CLASSINDEX = 934832;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	public String getConditionID() {
		return ConditionID;
	}

	public void setConditionID(String conditionID) {
		ConditionID = conditionID;
	}
}
