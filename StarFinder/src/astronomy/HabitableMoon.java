package astronomy;

import planetary.Condition;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitime;

public class HabitableMoon extends Moon implements OrbitObject, LifeBearing {

	public static int total;
	private Condition myCondition;
	
	public HabitableMoon(sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater, sitime myDay, sitime myMonth, sidistance myMoonOrbit) {
		super(myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse, myWater, myDay, myMonth, myMoonOrbit);
		myCondition = new Condition(this);
		++total;
	}

	public Condition getMyCondition() {
		return myCondition;
	}

	public void setMyCondition(Condition myCondition) {
		this.myCondition = myCondition;
	}
}
