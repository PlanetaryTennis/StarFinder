package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
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

	@Override
	public void loadString(String load) {
		// TODO Auto-generated method stub
	}

	@Override
	public String saveString() {
		String saver = "";
		saver += this.myName + StringFundementals.ENDLINE;
		saver += StringFundementals.MARK + StringFundementals.ENDLINE;
		for(int i = 0;i < this.myMoons.length;i++) {
			saver += myMoons[i].getMyName() + StringFundementals.ENDLINE;
		}
		saver += StringFundementals.MARK + StringFundementals.ENDLINE;
		return saver;
	}

	@Override
	public int getClassIndex() {
		return 932813;
	}
}
