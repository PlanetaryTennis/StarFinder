package astronomy;

import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitime;

public class HabitableMoon extends Moon implements OrbitObject, LifeBearing {

	public static int total;
	
	public HabitableMoon(sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater, sitime myDay, sitime myMonth, sidistance myMoonOrbit) {
		super(myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse, myWater, myDay, myMonth, myMoonOrbit);
		++total;
	}

}
