package astronomy;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import map.Sprite;
import units.sci;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitemperature;
import units.sitime;
import utilities.ExtendedMathmatics;

public class Moon extends Terrestrial implements OrbitObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5624598682414279466L;
	
	public  static int total;
	
	private sitime myMonth;
	private sidistance myMoonOrbit;
	private Planet myPlanet;
	
	public Moon(sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater, sitime myDay,sitime myMonth, sidistance myMoonOrbit) {
		super(new Moon[0], myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse, myWater, myDay);
		this.myMoonOrbit = myMoonOrbit;
		this.myMonth = myMonth;
	}

	public Moon(sidensity myAtmosphere, sidistance myRadius, simass myMass,
			double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater, sitime myDay,sitime myMonth, sidistance myMoonOrbit,boolean unused) {
		super(new Moon[0], myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myAlbido, myGreenHouse, myWater, myDay);
		this.myMonth = myMonth;
		this.myMoonOrbit = myMoonOrbit;
		++total;
	}
	
	public sitime getMyMonth() {
		return myMonth;
	}

	public void setMyMonth(sitime myMonth) {
		this.myMonth = myMonth;
	}
	
	public sidistance getMyMoonOrbit() {
		return myMoonOrbit;
	}

	public void setMyMoonOrbit(sidistance myMoonOrbit) {
		this.myMoonOrbit = myMoonOrbit;
	}

	public Planet getMyPlanet() {
		return myPlanet;
	}

	public void setMyPlanet(Planet myPlanet) {
		this.myPlanet = myPlanet;
		this.myYear = myPlanet.getMyYear();
		
	}

	public static Moon makeRandom(sidistance orbit, sitime sitime, simass planetmass, sidistance radius, 
			double scale, Star star) {
		
		simass mass = AstroObject.LUNE.scale(sci.round((random.nextDouble()+0.01),4));
		sidistance moonradius = AstroObject.LUNERADI.scale(sci.round((random.nextDouble()+random.nextDouble()+0.1)/4,4));
		sidistance lesserorbit = radius.scale(4).plus(radius.scale(sci.round(random.nextDouble()*4,4)));
		
		sitime month = monthcalculate(planetmass, mass, lesserorbit);
		
		sidensity atmosphere = BAR.scale(sci.round(random.nextDouble()*15,4));
		
		double greenhouse = sci.round(random.nextDouble()*15+0.01,4);
		double albido = sci.round(random.nextDouble()*2/3,4);
		
		sitime day = DAY.scale(8-ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		double water = sci.round(random.nextDouble(),3);
		
		sitemperature[] temps = tempitures(greenhouse, albido, orbit, orbit.scale(1+scale), orbit.scale(1-scale), star, atmosphere);
		
		Moon p;
		
		if(temps[2].greaterOrEqual(high)||temps[5].lessOrEqual(low)||water < 0.1||water > 0.98||
				atmosphere.lessOrEqual(BAR.scale(0.5))||atmosphere.lessOrEqual(BAR.scale(5))||
				mass.lessOrEqual(LUNE)||mass.greaterOrEqual(EARTH.scale(5))) {
			p = new Moon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month, lesserorbit, false);
		}else {
			p = new HabitableMoon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month, lesserorbit);
		}
		
		return p;
	}
	
	public static Moon makeRandomJovian(sidistance orbit, sitime sitime, simass planetmass, sidistance radius, double scale, Star star) {
				
		simass mass = AstroObject.EARTH.scale(sci.round((random.nextDouble()+0.01)/64,4));
		sidistance moonradius = AstroObject.EARTHRADI.scale(sci.round((random.nextDouble()+random.nextDouble()+0.01)/16,4));
		sidistance lesserorbit = radius.scale(4).plus(radius.scale(sci.round(random.nextDouble()*4+0.1,4)));
		
		sitime month = monthcalculate(planetmass, mass, lesserorbit);
		
		sidensity atmosphere = BAR.scale(sci.round(random.nextDouble()*15+0.1,4));
		
		double greenhouse = sci.round(random.nextDouble()*15+0.01,4);
		double albido = sci.round(random.nextDouble()*2/3,4);
		
		sitime day = DAY.scale(8-ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		double water = sci.round(random.nextDouble(),3);
		
		sitemperature[] temps = tempitures(greenhouse, albido, orbit, orbit.scale(1+scale), orbit.scale(1-scale), star, atmosphere);
		
		Moon p;
		
		double test = atmosphere.compair(BAR);
		
		if(temps[2].greaterThan(high)||
				temps[5].lessThan(low)||
				water < 0.1||
				water > 0.98||
				test < 0.5||
				test > 5||
				mass.lessOrEqual(LUNE)||
				mass.greaterOrEqual(EARTH.scale(5))) {
			p = new Moon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month, lesserorbit, false);
		}else {
			p = new HabitableMoon(atmosphere, moonradius, mass, scale, orbit, star, albido, greenhouse, water, day, month, lesserorbit);
		}
		
		return p;
	}

	protected static sitime monthcalculate(simass planetmass, simass moonmass, sidistance moonorbit) {
		double massfactor = sci.convertToDouble(planetmass.getValue().plus(moonmass.getValue()));
		double distancefactor = Math.pow(sci.convertToDouble(moonorbit.getValue()),3);
		double pifactor = Math.pow(Math.PI, 2)*4;
		double completefactor = Math.sqrt((pifactor*distancefactor)/
				(sci.convertToDouble(AstroObject.G)*massfactor));
		return new sitime(completefactor);
	}

	@Override
	public ImageIcon getIcon() {
		ImageIcon Cody = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Lava Moon.png"));
		double scale = (getMyRadius().compair(LUNERADI)+0.1)*2;
		if(this.getClass() == HabitableMoon.class) {
			HabitableMoon h = (HabitableMoon)this;
			if(h.getMyWater() > 0.85) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Ocean Moon.png"));				
			}else	if(h.getMyWater() > 0.5) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Earth-Like Moon.png"));
			}else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Desert Moon.png"));
			}
		}else {
			if(this.getMyTemps()[2].compair(boil) > 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Lava Moon.png"));
			}else if(this.getMyTemps()[2].compair(freeze) < 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Icy Moon.png"));
			}else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.MOONS+"Rocky Moon.png"));
			}
		}
	}
}
