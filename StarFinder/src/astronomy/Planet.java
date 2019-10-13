package astronomy;

import units.sci;
import units.siacceleration;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitemperature;
import units.sitime;
import units.sivolume;

import java.util.Vector;

import javax.swing.ImageIcon;

import planetary.Colony;

public abstract class Planet implements AstroObject {
	
	public static final char ALPHA = (char)945;
	public static final char OMEGA = (char)969;
	public static final char BE = (char)1073;
	public static final char YA = (char)1103;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5195473902898182146L;

	protected String myName;
	
	protected Colony myColony;
	
	protected Moon[] myMoons;
	
	protected Vector<OrbitObject> mySatilights = new Vector<OrbitObject>();
	
	protected sidensity myAtmosphere;
	protected sitemperature[] myTemps;
	
	protected sidistance myRadius;
	protected simass myMass;
	protected siacceleration myGravity;
	protected sivolume myVolume;
	protected sidensity myDensity;
	
	protected double myEccentricity;
	protected sidistance myOrbit;
	protected sidistance myInnerOrbit;
	protected sidistance myOuterOrbit;
	
	protected sitime myDay;
	protected sitime myYear;
	
	protected Star myStar;
	
	public int Moons;
	public int Asteroids;
	public int HabMoons;
	
	public void count() {
		Moons = 0;
		Asteroids = 0;
		HabMoons = 0;
		
		for(int i = 0;i < myMoons.length;i++) {
			if(myMoons[i].getClass()==HabitableMoon.class) {
				HabMoons++;
			}else if(myMoons[i].getClass()==Asteroid.class) {
				Asteroids++;
			}else if(myMoons[i].getClass()==Moon.class) {
				Moons++;
			}
		}
	}
	
	public Planet(Moon[] myMoons, sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit,Star myStar,sitime myDay) {
		super();
		this.myMoons = myMoons;
		this.myAtmosphere = myAtmosphere;
		this.myRadius = myRadius;
		this.myMass = myMass;
		this.myEccentricity = myEccentricity;
		this.myOrbit = myOrbit;
		this.myStar = myStar;
		this.myDay = myDay;
		calculate();
	}

	public Planet(Moon[] myMoons, double myEccentricity, sidistance myOrbit, Star myStar) {
		this.myMoons = myMoons;
		this.myEccentricity = myEccentricity;
		this.myOrbit = myOrbit;
		this.myStar = myStar;
		myInnerOrbit = myOrbit.scale(1-myEccentricity);
		myOuterOrbit = myOrbit.scale(1+myEccentricity);
	}

	private void calculate() {
		myGravity = new siacceleration(
				sci.convertToDouble(AstroObject.G)*
				sci.convertToDouble(myMass.getValue())/
				Math.pow(sci.convertToDouble(myRadius.getValue()),2));
		myVolume = new sivolume (
				Math.pow(sci.convertToDouble(myRadius.getValue()),3)*
				(0.75*Math.PI)*1000);
		myDensity = new sidensity(
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myVolume.getValue()));
		if(myOrbit == null) {
			int y = 1;
			if(y==0)this.setMyName("CAT");
		}
		myInnerOrbit = myOrbit.scale(1-myEccentricity);
		myOuterOrbit = myOrbit.scale(1+myEccentricity);
		myYear = yearcalculate(myStar,myMass,myOrbit);
		
	}

	public static sitime yearcalculate(Star myStar, simass myMass, sidistance myOrbit) {
		double massfactor = sci.convertToDouble(myStar.getMyMass().getValue().plus(myMass.getValue()));
		double distancefactor = Math.pow(sci.convertToDouble(myOrbit.getValue()),3);
		double pifactor = Math.pow(Math.PI, 2)*4;
		double completefactor = Math.sqrt((pifactor*distancefactor)/
				(sci.convertToDouble(AstroObject.G)*massfactor));
		return new sitime(completefactor);
	}
	

	public Colony getMyColony() {
		return myColony;
	}

	public void setMyColony(Colony myColony) {
		this.myColony = myColony;
	}

	public Moon[] getMyMoons() {
		return myMoons;
	}

	public Vector<OrbitObject> getMySatilights() {
		return mySatilights;
	}

	public void setMySatilights(Vector<OrbitObject> mySatilights) {
		this.mySatilights = mySatilights;
	}

	public int getMoons() {
		return Moons;
	}

	public void setMoons(int moons) {
		Moons = moons;
	}

	public int getAsteroids() {
		return Asteroids;
	}

	public void setAsteroids(int asteroids) {
		Asteroids = asteroids;
	}

	public int getHabMoons() {
		return HabMoons;
	}

	public void setHabMoons(int habMoons) {
		HabMoons = habMoons;
	}

	public void setMyMoons(Moon[] moons) {
		this.myMoons = moons;
	}

	public sidensity getMyAtmosphere() {
		return myAtmosphere;
	}

	public void setMyAtmosphere(sidensity myAtmosphere) {
		this.myAtmosphere = myAtmosphere;
	}

	public sitemperature[] getMyTemps() {
		return myTemps;
	}

	public void setMyTemps(sitemperature[] myTemps) {
		this.myTemps = myTemps;
	}

	public sidistance getMyRadius() {
		return myRadius;
	}

	public void setMyRadius(sidistance myRadius) {
		this.myRadius = myRadius;
	}

	public simass getMyMass() {
		return myMass;
	}

	public void setMyMass(simass myMass) {
		this.myMass = myMass;
	}

	public siacceleration getMyGravity() {
		return myGravity;
	}

	public void setMyGravity(siacceleration myGravity) {
		this.myGravity = myGravity;
	}

	public sivolume getMyVolume() {
		return myVolume;
	}

	public void setMyVolume(sivolume myVolume) {
		this.myVolume = myVolume;
	}

	public sidensity getMyDensity() {
		return myDensity;
	}

	public void setMyDensity(sidensity myDensity) {
		this.myDensity = myDensity;
	}

	public double getMyEccentricity() {
		return myEccentricity;
	}

	public void setMyEccentricity(double myEccentricity) {
		this.myEccentricity = myEccentricity;
	}

	public sidistance getMyOrbit() {
		return myOrbit;
	}

	public void setMyOrbit(sidistance myOrbit) {
		this.myOrbit = myOrbit;
	}

	public sidistance getMyInnerOrbit() {
		return myInnerOrbit;
	}

	public void setMyInnerOrbit(sidistance myInnerOrbit) {
		this.myInnerOrbit = myInnerOrbit;
	}

	public sidistance getMyOuterOrbit() {
		return myOuterOrbit;
	}

	public void setMyOuterOrbit(sidistance myOuterOrbit) {
		this.myOuterOrbit = myOuterOrbit;
	}

	public sitime getMyDay() {
		return myDay;
	}

	public void setMyDay(sitime myDay) {
		this.myDay = myDay;
	}

	public sitime getMyYear() {
		return myYear;
	}

	public void setMyYear(sitime myYear) {
		this.myYear = myYear;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Star getMyStar() {
		return myStar;
	}

	public void setMyStar(Star myStar) {
		this.myStar = myStar;
	}

	public static String nameMoon(int i) {
		String out = "";
		
		if(ALPHA+i<=OMEGA) {
			out += (char) (ALPHA+i);
		}else {
			i = (i-OMEGA+ALPHA);
			if(BE+i<=YA) {
				out += (char) (BE+i);
			}
		}
		
		return out;
	}
	
	public String toString() {
		return this.myName;
	}
	
	public abstract ImageIcon getIcon();
	
	public abstract String string();
	
}
