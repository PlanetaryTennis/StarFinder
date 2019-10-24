package astronomy.old;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.Star;
import engine.ObjectFiles;
import map.Sprite;
import map.color;
import planetary.Colony;
import units.sci;
import units.siacceleration;
import units.sibrightness;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitemperature;
import units.sitime;
import units.sivolume;
import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Jovian extends Planet {


	public static int total = 0;
	private static final String LINEBREAK = "<BR/>";
	/**
	 * 
	 */
	private static final long serialVersionUID = 3059962286635742097L;

	public Jovian(Moon[] myMoons, sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit, Star myStar, sitime myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myDay);
		myID = UUID.randomUUID().toString();;
		++total;
	}

	public static final Random random = new Random(System.currentTimeMillis());
	
	public static Planet makeRandom(sidistance orbit,Star star) {
		double m = sci.round(random.nextDouble()*15,4);
		simass mass = AstroObject.JOVIAN.scale(m);
		
		int moonnum = random.nextInt(16)+8;
		Moon[] moons = new Moon[moonnum];
		sidistance radius = AstroObject.JOVIANRADI.scale(Math.pow(m, 0.74));

		double scale = ExtendedMathmatics.log(random.nextInt(499)+1, 1000)/8;
		
		for(int i = 0;i < moonnum;i++) {
			moons[i] = Moon.makeRandomJovian(orbit,yearcalculate(star,mass,orbit), mass, radius, scale, star);
			moons[i].setMyName(nameMoon(i));
		}

		sitime day = DAY.scale(8-ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		sivolume volume = new sivolume(
				Math.pow(sci.convertToDouble(radius.getValue()),3)*
				(0.75*Math.PI)*1000);

		sidensity atmosphere = new sidensity(
				sci.convertToDouble(mass.getValue())/
				sci.convertToDouble(volume.getValue()));
		
		Jovian j = new Jovian(moons, atmosphere, radius, mass, scale, orbit, star, day);
		
		for(int i = 0;i < moonnum;i++) {
			moons[i].setMyPlanet(j);
		}
		
		int y = random.nextInt(25);
		Asteroid a;
		for(int i = 0;i < y;i++) {
			a = (Asteroid) Asteroid.makeRandom(j, scale, orbit, star);
			a.setMyName(nameMoon(moonnum+i));
			j.getMySatilights().add(a);
		}
		
		j.setMyName(SolSystem.randomName());
		j.setMyColony(Colony.randomJovian((Jovian) j));
		sitemperature[] temps = Terrestrial.tempitures(random.nextDouble()*5, random.nextDouble(), orbit, orbit.scale(1+scale), orbit.scale(1-scale), star, atmosphere);
		j.setMyTemps(temps);
		
		return j;
	}

	@Override
	public String string() {
		return 
				"Atmosphere " + this.getMyAtmosphere() + LINEBREAK +
				"Day Length " + this.getMyDay() + LINEBREAK +
				"Gravity " + this.getMyGravity() + LINEBREAK +
				"Density " + this.getMyDensity() + LINEBREAK +
				"Mass " + this.getMyMass() + LINEBREAK +
				"Number of Moons " + this.getMyMoons().length + LINEBREAK +
				"Orbit Average " + this.getMyOrbit() + LINEBREAK +
				"Near Orbit " + this.getMyInnerOrbit() + LINEBREAK +
				"Far Orbit " + this.getMyOuterOrbit() + LINEBREAK +
				"Radius " + this.getMyRadius() + LINEBREAK +
				"Year Length" + this.getMyYear();
	}

	@Override
	public ImageIcon getIcon() {
		ImageIcon Cody = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.GASGIANT+"Gas-Giant-Blue.png"));
		double scale = getMyRadius().compair(JOVIANRADI)/1.5;
		if(getMyMass().greaterOrEqual(AstroObject.JOVIAN.scale(0.5))) {
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.GASGIANT+"Gas-Giant-Yellow.png"));
		}else {
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.GASGIANT+"Gas-Giant-Blue.png"));
		}
	}

	String[] moonSaves;
	String[] satilightSaves;
	String colonySave;
	
	@Override
	public void loadString(String load) {
		String[] loads = StringFundementals.breakByLine(load);
		myID = loads[0];
		myName = loads[1];
		int m = Integer.parseInt(loads[3]);
		myMoons = new Moon[m];
		moonSaves = new String[m];
		for(int i = 0;i < m;i++) {
			moonSaves[i] = loads[3+i];
		}
		int s = Integer.parseInt(loads[3+m]);
		satilightSaves = new String[s];
		for(int i = 0;i < s;i++) {
			satilightSaves[i] = loads[3+m+i];
		}
		int measure = 3+m+s;
		colonySave = loads[measure++];
		myAtmosphere = new sidensity(Double.parseDouble(loads[measure++]));
		myDay = new sitime(Double.parseDouble(loads[measure++]));
		myDensity = new sidensity(Double.parseDouble(loads[measure++]));
		myEccentricity = (Double.parseDouble(loads[7+m+s]));
		myGravity = new siacceleration(Double.parseDouble(loads[measure++]));
		myInnerOrbit = new sidistance(Double.parseDouble(loads[measure++]));
		myMass = new simass(Double.parseDouble(loads[measure++]));
		myOrbit = new sidistance(Double.parseDouble(loads[measure++]));
		myOuterOrbit = new sidistance(Double.parseDouble(loads[measure++]));
		myRadius = new sidistance(Double.parseDouble(loads[measure++]));
		myVolume = new sivolume(Double.parseDouble(loads[measure++]));
		myYear = new sitime(Double.parseDouble(loads[measure++]));
		for(int i = 0;i < 6;i++) {
			myTemps[i] = new sitemperature(Double.parseDouble(loads[measure++]));
		}
	}

	String myID;
	
	@Override
	public String getID() {
		return myID;		
	}
	
	@Override
	public String saveString() {
		String saver = "";
		saver += this.myID + StringFundementals.ENDLINE;
		saver += this.myName + StringFundementals.ENDLINE;
		saver += this.getClassIndex() + StringFundementals.ENDLINE;
		saver += this.myMoons.length +StringFundementals.ENDLINE;
		for(int i = 0;i < this.myMoons.length;i++) {
			saver += myMoons[i].getID() + StringFundementals.ENDLINE;
		}
		saver += this.mySatilights.size() + StringFundementals.ENDLINE;
		for(int i = 0;i < this.mySatilights.size();i++) {
			saver += this.mySatilights.get(i).getID() + StringFundementals.ENDLINE;
		}
		saver += this.myColony.getID() + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myAtmosphere.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myDay.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myDensity.getValue()) + StringFundementals.ENDLINE;
		saver += this.getMyEccentricity() + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myGravity.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myInnerOrbit.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myMass.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myOrbit.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myOuterOrbit.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myRadius.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myVolume.getValue()) + StringFundementals.ENDLINE;
		saver += sci.convertToDouble(this.myYear.getValue()) + StringFundementals.ENDLINE;
		for(int i = 0;i < this.myTemps.length;i++) {
			saver += sci.convertToDouble(this.myTemps[i].getValue()) + StringFundementals.ENDLINE;
		}
		return saver;
	}

	@Override
	public int getClassIndex() {
		return 932813;
	}
}
