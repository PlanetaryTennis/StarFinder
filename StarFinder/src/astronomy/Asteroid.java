package astronomy;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import units.*;
import utilities.ExtendedMathmatics;

/**
 * 
 * @author JamesArmstrong
 *
 * Asteroid is a special type of moon object that is used by the belt object. 
 * They are smaller and less dense than normal moons.
 *
 */
public class Asteroid extends Moon {

	/**
	 * Constructor
	 * 
	 * @param radius
	 * @param mass
	 * @param eccentricity
	 * @param orbit
	 * @param star
	 * @param day
	 */
	Asteroid(sidistance radius, simass mass, double eccentricity, sidistance orbit, Star star, sitime day) {
		super(new sidensity(1, SI.YOCTO), radius, mass, eccentricity, orbit, star, 0.01, 0.01, 0.0, day, day,
				new sidistance(1, SI.YOCTO));
	}

	/**
	 * 
	 * @param eccentricity
	 * @param orbit
	 * @param star
	 * 
	 * @return Newly created Asteroid
	 */
	public static Moon makeRandom(double eccentricity, sidistance orbit, Star star) {
		simass m = LUNE.scale(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		sidensity d = new sidensity(1000.0 + random.nextInt(2000));
		sidistance r = new sidistance(Math.cbrt((3.0/4.0)*(Math.PI)*(sci.convertToDouble(m.getValue())/sci.convertToDouble(d.getValue()))));
		sitime day = DAY.scale(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));

		return new Asteroid(r, m, eccentricity, orbit, star, day);
	}
	
	public static OrbitObject makeRandom(Planet planet,double eccentricity, sidistance orbit, Star star) {

		simass m = LUNE.scale(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		sidensity d = new sidensity(1000.0 + random.nextInt(2000));
		sidistance r = new sidistance(Math.cbrt((3.0/4.0)*(Math.PI)*(sci.convertToDouble(m.getValue())/sci.convertToDouble(d.getValue()))));
		sitime day = DAY.scale(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		Asteroid out = new Asteroid(r, m, eccentricity, orbit, star, day);
		
		sidistance lesserorbit = planet.getMyRadius().scale(4).plus(planet.getMyRadius().scale(sci.round(random.nextDouble()*4+0.1,4)));
		out.setMyMoonOrbit(lesserorbit);

		sitime month = monthcalculate(planet.getMyMass(), m, lesserorbit);
		out.setMyMonth(month);
		
		out.setMyPlanet(planet);
		
		return out;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/sprites/systems/Asteroid Belt.png"));
	}
}
