package astronomy.old;

import astronomy.LifeBearing;
import astronomy.Star;
import planetary.Condition;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitime;

public class Habitable extends Terrestrial implements LifeBearing {

	public static int total = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -220171531249454775L;
	private Condition myCondition;

	public Habitable(Moon[] myMoons, sidensity myAtmosphere, sidistance myRadius,
			simass myMass, double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater,sitime myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse,
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
}
