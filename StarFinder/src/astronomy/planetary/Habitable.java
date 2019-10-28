package astronomy.planetary;

import java.util.Vector;

import astronomy.LifeBearing;
import astronomy.stellar.Star;
import planetary.Condition;
import utilities.StringFundementals;

public class Habitable extends Terrestrial implements LifeBearing {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220171531249454775L;
	private Condition myCondition;

	public Habitable(String load) {
		super(load);
		this.loadString(load);
	}
	
	public Habitable(Vector<Moon> myMoons, double myAtmosphere, double myRadius,
			double myMass, double myEccentricity, double myOrbit, Star star, double myAlbido, double myGreenHouse,
			double myWater,double myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, star, myAlbido, myGreenHouse,
				myWater, myDay);
		myCondition = new Condition(this);
		++total;
	}

	public Condition getMyCondition() {
		return myCondition;
	}

	public void setMyCondition(Condition myCondition) {
		this.myCondition = myCondition;
	}
	
	@Override
	public void loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myAlbido = Double.parseDouble(in[i++]);
		myAtmosphere = Double.parseDouble(in[i++]);
		myDay = Double.parseDouble(in[i++]);
		myDensity = Double.parseDouble(in[i++]);
		myEccentricity = Double.parseDouble(in[i++]);
		myGravity = Double.parseDouble(in[i++]);
		myGreenHouse = Double.parseDouble(in[i++]);
		myOrbit = Double.parseDouble(in[i++]);
		myInnerOrbit = Double.parseDouble(in[i++]);
		myOuterOrbit = Double.parseDouble(in[i++]);
		myMass = Double.parseDouble(in[i++]);
		myRadius = Double.parseDouble(in[i++]);
		myTemps = new double[6];
		myTemps[0] = Double.parseDouble(in[i++]);
		myTemps[1] = Double.parseDouble(in[i++]);
		myTemps[2] = Double.parseDouble(in[i++]);
		myTemps[3] = Double.parseDouble(in[i++]);
		myTemps[4] = Double.parseDouble(in[i++]);
		myTemps[5] = Double.parseDouble(in[i++]);
		myVolume = Double.parseDouble(in[i++]);
		myWater = Double.parseDouble(in[i++]);
		myYear = Double.parseDouble(in[i++]);
		setColonyID(in[i++]);
		setMoonNumber(Integer.parseInt(in[i++]));
		setMoonIDs(new Vector<String>());
		for(int k = 0;k < getMoonNumber();k++) {
			getMoonIDs().add(in[i++]);
		}
		setSatilightNumber(Integer.parseInt(in[i++]));
		setSatilightIDs(new Vector<String>());
		for(int k = 0;k < getMoonNumber();k++) {
			getSatilightIDs().add(in[i++]);
		}
		setConditionID(in[i++]);
	}

	private String ConditionID;	
	
	@Override
	public String saveString() {
		String out = "";
		out += getID() + "\n";
		out += getClassIndex() + "\n";
		out += getMyName()  + "\n";
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
		out += getMyColony().getID() + "\n";
		out += getMyMoons().size() + "\n";
		for(int i = 0;i < getMyMoons().size();i++) {
			out += getMyMoons().get(i).getID() + "\n";
		}
		out += getMySatilights().size() + "\n";
		for(int i = 0;i < getMySatilights().size();i++) {
			out += getMySatilights().get(i).getID() + "\n";
		}
		out += getMyCondition().getID() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 9348476;
	
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
