package astronomy.planetary;

import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.OrbitObject;
import astronomy.stellar.Star;
import engine.Namable;
import planetary.Colony;
import utilities.StringFundementals;

public abstract class Planet implements AstroObject, Namable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1687224618339768870L;
	public static final char ALPHA = (char) 945;
	public static final char OMEGA = (char) 969;
	public static final char BE = (char) 1073;
	public static final char YA = (char) 1103;

	protected String myName;

	protected Colony myColony;

	protected Vector<Moon> myMoons = new Vector<Moon>();

	protected Vector<OrbitObject> mySatilights = new Vector<OrbitObject>();

	protected double myAtmosphere;
	protected double[] myTemps;

	protected double myRadius;
	protected double myMass;
	protected double myGravity;
	protected double myVolume;
	protected double myDensity;

	protected double myEccentricity;
	protected double myOrbit;
	protected double myInnerOrbit;
	protected double myOuterOrbit;

	protected double myDay;
	protected double myYear;

	public Planet() {

	};

	public Planet(Vector<Moon> myMoons, double myAtmosphere, double myRadius, double myMass, double myEccentricity,
			double myOrbit, double myDay, Star star) {
		super();
		this.myMoons = myMoons;
		this.myAtmosphere = myAtmosphere;
		this.myRadius = myRadius;
		this.myMass = myMass;
		this.myEccentricity = myEccentricity;
		this.myOrbit = myOrbit;
		this.myDay = myDay;
		calculate(star);
	}

	public Planet(Vector<Moon> myMoons, double myEccentricity, double myOrbit, Star star) {
		this.myMoons = myMoons;
		this.myEccentricity = myEccentricity;
		this.myOrbit = myOrbit;
		myInnerOrbit = myOrbit * (1 - myEccentricity);
		myOuterOrbit = myOrbit * (1 + myEccentricity);
	}

	private void calculate(Star star) {
		myGravity = (AstroObject.G) * (myMass) / Math.pow(myRadius, 2);
		myVolume = Math.pow(myRadius, 3) * (0.75 * Math.PI) * 1000;
		myDensity = myMass / myVolume;
		myInnerOrbit = myOrbit * (1 - myEccentricity);
		myOuterOrbit = myOrbit * (1 + myEccentricity);
		myYear = yearcalculate(star, myMass, myOrbit);

	}

	public static double yearcalculate(Star myStar, double myMass, double myOrbit) {
		double massfactor = myStar.getMyMass() + myMass;
		double distancefactor = Math.pow(myOrbit, 3);
		double pifactor = Math.pow(Math.PI, 2) * 4;
		double completefactor = Math.sqrt((pifactor * distancefactor) / (AstroObject.G * massfactor));
		return completefactor;
	}

	public Colony getMyColony() {
		return myColony;
	}

	public void setMyColony(Colony myColony) {
		this.myColony = myColony;
	}

	public Vector<Moon> getMyMoons() {
		return myMoons;
	}

	public Vector<OrbitObject> getMySatilights() {
		return mySatilights;
	}

	public void setMySatilights(Vector<OrbitObject> mySatilights) {
		this.mySatilights = mySatilights;
	}

	public void setMyMoons(Vector<Moon> moons) {
		this.myMoons = moons;
	}

	public double getMyAtmosphere() {
		return myAtmosphere;
	}

	public void setMyAtmosphere(double myAtmosphere) {
		this.myAtmosphere = myAtmosphere;
	}

	public double[] getMyTemps() {
		return myTemps;
	}

	public void setMyTemps(double[] myTemps) {
		this.myTemps = myTemps;
	}

	public double getMyRadius() {
		return myRadius;
	}

	public void setMyRadius(double myRadius) {
		this.myRadius = myRadius;
	}

	public double getMyMass() {
		return myMass;
	}

	public void setMyMass(double myMass) {
		this.myMass = myMass;
	}

	public double getMyGravity() {
		return myGravity;
	}

	public void setMyGravity(double myGravity) {
		this.myGravity = myGravity;
	}

	public double getMyVolume() {
		return myVolume;
	}

	public void setMyVolume(double myVolume) {
		this.myVolume = myVolume;
	}

	public double getMyDensity() {
		return myDensity;
	}

	public void setMyDensity(double myDensity) {
		this.myDensity = myDensity;
	}

	public double getMyEccentricity() {
		return myEccentricity;
	}

	public void setMyEccentricity(double myEccentricity) {
		this.myEccentricity = myEccentricity;
	}

	public double getMyOrbit() {
		return myOrbit;
	}

	public void setMyOrbit(double myOrbit) {
		this.myOrbit = myOrbit;
	}

	public double getMyInnerOrbit() {
		return myInnerOrbit;
	}

	public void setMyInnerOrbit(double myInnerOrbit) {
		this.myInnerOrbit = myInnerOrbit;
	}

	public double getMyOuterOrbit() {
		return myOuterOrbit;
	}

	public void setMyOuterOrbit(double myOuterOrbit) {
		this.myOuterOrbit = myOuterOrbit;
	}

	public double getMyDay() {
		return myDay;
	}

	public void setMyDay(double myDay) {
		this.myDay = myDay;
	}

	public double getMyYear() {
		return myYear;
	}

	public void setMyYear(double myYear) {
		this.myYear = myYear;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public static String nameMoon(int i) {
		String out = "";

		if (ALPHA + i <= OMEGA) {
			out += (char) (ALPHA + i);
		} else {
			i = (i - OMEGA + ALPHA);
			if (BE + i <= YA) {
				out += (char) (BE + i);
			}
		}

		return out;
	}

	public String toString() {
		return this.myName;
	}

	public abstract ImageIcon getIcon();

	public abstract String string();

	public static Planet parseLoad(String string) {
		Planet obj = null;
		String[] box = StringFundementals.breakByLine(string);
		switch (Integer.parseInt(box[1])) {
		case Terrestrial.CLASSINDEX:
			obj = new Terrestrial(string);
			break;
		case Moon.CLASSINDEX:
			obj = new Moon(string);
			break;
		case Jovian.CLASSINDEX:
			obj = new Jovian(string);
			break;
		case HabitableMoon.CLASSINDEX:
			obj = new HabitableMoon(string);
			break;
		case Habitable.CLASSINDEX:
			obj = new Habitable(string);
			break;
		case Belt.CLASSINDEX:
			obj = new Belt(string);
			break;
		case Asteroid.CLASSINDEX:
			obj = new Asteroid(string);
			break;
		}
		return obj;
	}

}
