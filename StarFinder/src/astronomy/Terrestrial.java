package astronomy;

import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;


import map.Sprite;
import units.SI;
import units.sci;
import units.sidensity;
import units.sidistance;
import units.simass;
import units.sitemperature;
import units.sitime;
import utilities.ExtendedMathmatics;

public class Terrestrial extends Planet {
	
	public static int total = 0;
	private static final String LINEBREAK = "<BR/>";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7913040733716806290L;
	private double myAlbido;
	private double myGreenHouse;
	private double myWater;
	private boolean isFrozen;
	
	public Terrestrial(Moon[] myMoons, sidensity myAtmosphere, sidistance myRadius,
			simass myMass, double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater,sitime myDay) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myDay);
		this.myAlbido = myAlbido;
		this.myGreenHouse = myGreenHouse;
		this.myWater = myWater;
		myTemps = tempitures(myGreenHouse,myAlbido,myOrbit,myInnerOrbit,myOuterOrbit,myStar,myAtmosphere);
		if(myTemps[2].lessOrEqual(freeze)) {
			isFrozen = true;
		}if(myTemps[5].greaterOrEqual(boil)||myAtmosphere.lessOrEqual(BAR.scale(0.25))) {
			this.myWater = 0.0f;
		}
	}
	
	public Terrestrial(Moon[] myMoons, sidensity myAtmosphere, sidistance myRadius,
			simass myMass, double myEccentricity, sidistance myOrbit, Star myStar, double myAlbido, double myGreenHouse,
			double myWater,sitime myDay,boolean unused) {
		super(myMoons, myAtmosphere, myRadius, myMass, myEccentricity, myOrbit, myStar, myDay);
		this.myAlbido = myAlbido;
		this.myGreenHouse = myGreenHouse;
		this.myWater = myWater;
		myTemps = tempitures(myGreenHouse,myAlbido,myOrbit,myInnerOrbit,myOuterOrbit,myStar,myAtmosphere);
		if(myTemps[2].lessOrEqual(freeze)) {
			isFrozen = true;
		}if(myTemps[5].greaterOrEqual(boil)||myAtmosphere.lessOrEqual(new sidensity(250.0f,SI.BASE))) {
			myWater = 0.0f;
		}
		++total;
	}

	public static sitemperature[] tempitures(double GreenHouse, double Albido, sidistance Orbit,
			sidistance InnerOrbit, sidistance OuterOrbit, Star Star, sidensity Atmosphere) {
		double l = Math.pow(sci.convertToDouble(Star.getMyLuminosity().getValue()),1.0f/8.0f);
		double d = sci.convertToDouble(Orbit.getValue())/sci.convertToDouble(AU.getValue());
		double dividan = 16*Math.PI*Math.pow(d, 2)*sci.convertToDouble(O);
		double top = l*(1-Albido);
		double Mid = Math.pow(top/dividan,1.0f/4.0f);
		d = sci.convertToDouble(InnerOrbit.getValue())/sci.convertToDouble(AU.getValue());
		dividan = 16*Math.PI*Math.pow(d, 2)*sci.convertToDouble(O);
		double High = Math.pow(top/dividan,1.0f/4.0f);
		d = sci.convertToDouble(OuterOrbit.getValue())/sci.convertToDouble(AU.getValue());
		dividan = 16*Math.PI*Math.pow(d, 2)*sci.convertToDouble(O);
		double Low = Math.pow(top/dividan,1.0f/4.0f);
		double tempmods = GreenHouse * sci.convertToDouble(Atmosphere.getValue()) / sci.convertToDouble(AstroObject.BAR.getValue());
		double nightmod = tempmods/(tempmods + 0.1);
		
		double myDayNorm = Mid + Mid * Atmosphere.compair(BAR) + tempmods * Mid;
		double myNightNorm = nightmod * Mid + Mid * Atmosphere.compair(BAR) + tempmods * Mid;
		
		double myDayMax = High + High * Atmosphere.compair(BAR) + tempmods * High;
		double myNightMax = nightmod * High + High * Atmosphere.compair(BAR) + tempmods * High;
		
		double myDayMin = Low + Low * Atmosphere.compair(BAR) + tempmods * Low;
		double myNightMin = nightmod * Low + Low * Atmosphere.compair(BAR) + tempmods * Low;
		

		return new sitemperature[] {new sitemperature(myDayNorm),new sitemperature(myNightNorm),new sitemperature(myDayMax),
				new sitemperature(myNightMax),new sitemperature(myDayMin),new sitemperature(myNightMin)};
	}

	public double getMyAlbido() {
		return myAlbido;
	}

	public double getMyGreenHouse() {
		return myGreenHouse;
	}

	public double getMyWater() {
		return myWater;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

//Habitable Stats
	public static final sitemperature low = new sitemperature(270.0f);
	public static final sitemperature high = new sitemperature(330.0f);
	public static final sitemperature freeze = new sitemperature(273.15);
	public static final sitemperature boil = new sitemperature(373.15);
	public static final Random random = new Random(System.nanoTime());
	
	public static Planet makeRandom(sidistance orbit,Star star) {
		int moonnum = random.nextInt(4);
		Moon[] moons = new Moon[moonnum];
		
		double alter = sci.round((random.nextDouble()*15+0.01)/3,4);
		
		simass mass = EARTH.scale(alter);
		sidistance radius = EARTHRADI.scale(alter*(random.nextDouble()/2 + 0.5));
		double scale = ExtendedMathmatics.log(random.nextInt(499)+1, 1000)/16;
		
		for(int i = 0;i < moonnum;i++) {
			moons[i] = Moon.makeRandom(orbit, yearcalculate(star,mass,orbit), mass, radius, scale, star);
			moons[i].setMyName(nameMoon(i));
		}

		
		sidensity atmosphere = BAR.scale(sci.round(random.nextDouble()*5/4,4));
		
		double greenhouse = sci.round(random.nextDouble()*5,4);
		double albido = sci.round(random.nextDouble()*2/3,4);
		
		sitemperature[] temps = tempitures(greenhouse, albido, orbit, orbit.scale(1+scale), orbit.scale(1-scale), star, atmosphere);
		Planet p;
		
		sitime day = DAY.scale(8-ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		double water = sci.round(random.nextDouble(),3);
		
		double test = atmosphere.compair(BAR);
		
		if(temps[2].greaterThan(high)||
				temps[5].lessThan(low)||
				water < 0.1||
				water > 0.98||
				moonnum < 1||
				test < 0.5||
				test > 5||
				mass.lessOrEqual(LUNE)||
				mass.greaterOrEqual(EARTH.scale(5))) {
			p = new Terrestrial(moons, atmosphere, radius, mass, scale, orbit, star, albido, greenhouse, water, day,false);
		}else {
			p = new Habitable(moons, atmosphere, radius, mass, scale, orbit, star, albido, greenhouse, water, day);
		}
		
		for(int i = 0;i < moonnum;i++) {
			moons[i].setMyPlanet(p);
		}
		
		int y = random.nextInt(10);
		Asteroid a;
		for(int i = 0;i < y;i++) {
			a = (Asteroid) Asteroid.makeRandom(p, scale, orbit, star);
			a.setMyName(nameMoon(moonnum+i));
			p.getMySatilights().add(a);
		}
		
		p.setMyName(SolSystem.randomName());
		
		return p;
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
				"Year Length" + this.getMyYear() + LINEBREAK + 
				"Day Norm" + this.getMyTemps()[0] + LINEBREAK + 
				"Night Norm " + this.getMyTemps()[1] + LINEBREAK + 
				"Day Max " + this.getMyTemps()[2] + LINEBREAK + 
				"Night Max " + this.getMyTemps()[3] + LINEBREAK + 
				"Day Min " + this.getMyTemps()[4] + LINEBREAK + 
				"Night Min " + this.getMyTemps()[5] + LINEBREAK + 
				"Water " + this.myWater + LINEBREAK +
				"Green House Effect " + this.myGreenHouse;
	}

	@Override
	public ImageIcon getIcon() {
		ImageIcon Cody = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Lava Planet.png"));
		double scale = getMyRadius().compair(EARTHRADI)+0.1;
		if(this.getClass() == Habitable.class) {
			Habitable h = (Habitable)this;
			if(h.getMyWater() > 0.85) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Ocean Planet.png"));				
			}else if(h.getMyWater() > 0.5) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Earth-Like Planet.png"));
			}else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Desert Planet.png"));
			}
		}else {
			if(this.getMyTemps()[2].compair(boil) > 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Lava Planet.png"));
			}else if(this.getMyTemps()[2].compair(freeze) < 1.0) {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Tundra Planet.png"));
			}else {
				return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.PLANETS+"Rocky Planet.png"));
			}
		}
	}

}
