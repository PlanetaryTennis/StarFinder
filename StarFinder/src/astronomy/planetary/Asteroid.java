package astronomy.planetary;

import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import astronomy.stellar.Star;
import planetary.Colony;
import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

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
	 * 
	 */
	private static final long serialVersionUID = 3432449119572055711L;

	public Asteroid(String load){
		super(load);
		this.loadString(load);
	}
	
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
	Asteroid(double radius, double mass, double eccentricity, double orbit, Star star, double day) {
		super(0.000000000000000000000001, radius, mass, eccentricity, orbit, star, 0.01, 0.01, 0.0, day, day, 0.000000000000000000000001);
	}

	/**
	 * 
	 * @param eccentricity
	 * @param orbit
	 * @param star
	 * 
	 * @return Newly created Asteroid
	 */
	public static Moon makeRandom(double eccentricity, double orbit, Star star) {
		double d = 8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2);
		double m = LUNE*(Math.pow(d,3));
		double r = LUNERADI*(d);
		double day = DAY*(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		Moon out = new Asteroid(r, m, eccentricity, orbit, star, day);
		
		out.setMyColony(Colony.randomMoon(out));;

		return out;
	}
	
	public static OrbitObject makeRandom(Planet planet,double eccentricity, double orbit, Star star) {
		double d = 8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2);
		double m = LUNE*(Math.pow(d,3));
		double r = LUNERADI*(d);
		double day = DAY*(8 - ExtendedMathmatics.log(random.nextInt(255) + 1, 2));
		
		Asteroid out = new Asteroid(r, m, eccentricity, orbit, star, day);
		
		double lesserorbit = planet.getMyRadius()*(4)+(planet.getMyRadius()*(random.nextDouble()*4+0.1));
		out.setMyMoonOrbit(lesserorbit);

		double month = monthcalculate(planet.getMyMass(), m, lesserorbit);
		out.setMyMonth(month);
		
		out.setMyYear(planet.myYear);
		
		out.setMyColony(Colony.randomMoon(out));;
		
		return out;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/sprites/systems/Asteroid Belt.png"));
	}
	
	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myAlbido = Double.parseDouble(in[i++]);
		myAtmosphere = Double.parseDouble(in[i++]);
		myDay = Double.parseDouble(in[i++]);
		myDensity = Double.parseDouble(in[i++]);
		myEccentricity = Double.parseDouble(in[i++]);
		myGravity = Double.parseDouble(in[i++]);
		myGreenHouse = Double.parseDouble(in[i++]);
		myOrbit = Double.parseDouble(in[i++]);
		myInnerOrbit = Double.parseDouble(in[i++]);
		myOuterOrbit = Double.parseDouble(in[i++]);
		myMass = Double.parseDouble(in[i++]);
		myRadius = Double.parseDouble(in[i++]);
		myTemps = new double[6];
		myTemps[0] = Double.parseDouble(in[i++]);
		myTemps[1] = Double.parseDouble(in[i++]);
		myTemps[2] = Double.parseDouble(in[i++]);
		myTemps[3] = Double.parseDouble(in[i++]);
		myTemps[4] = Double.parseDouble(in[i++]);
		myTemps[5] = Double.parseDouble(in[i++]);
		myVolume = Double.parseDouble(in[i++]);
		myWater = Double.parseDouble(in[i++]);
		myYear = Double.parseDouble(in[i++]);
		setColonyID(in[i++]);
		setMoonNumber(Integer.parseInt(in[i++]));
		setMoonIDs(new Vector<String>());
		for(int k = 0;k < getMoonNumber();k++) {
			getMoonIDs().add(in[i++]);
		}
		setSatilightNumber(Integer.parseInt(in[i++]));
		setSatilightIDs(new Vector<String>());
		for(int k = 0;k < getMoonNumber();k++) {
			getSatilightIDs().add(in[i++]);
		}
		myMonth = Double.parseDouble(in[i++]);
		myMoonOrbit = Double.parseDouble(in[i++]);
		return i;
	}
	
	
	@Override
	public String saveString() {
		String out = "";
		out += getID() + "\n";
		out += getClassIndex() + "\n";
		out += getMyName()  + "\n";
		out += getMyAlbido() + "\n";
		out += getMyAtmosphere() + "\n";
		out += getMyDay() + "\n";
		out += getMyDensity() + "\n";
		out += getMyEccentricity() + "\n";
		out += getMyGravity() + "\n";
		out += getMyGreenHouse() + "\n";
		out += getMyOrbit() + "\n";
		out += getMyInnerOrbit() + "\n";
		out += getMyOuterOrbit() + "\n";
		out += getMyMass() + "\n";
		out += getMyRadius() + "\n";
		out += getMyTemps()[0] + "\n";
		out += getMyTemps()[1] + "\n";
		out += getMyTemps()[2] + "\n";
		out += getMyTemps()[3] + "\n";
		out += getMyTemps()[4] + "\n";
		out += getMyTemps()[5] + "\n";
		out += getMyVolume() + "\n";
		out += getMyWater() + "\n";
		out += getMyYear() + "\n";
		out += "{\n";
		out += getMyColony().saveString() + "\n";
		out += "}\n";
		out += getMyMoons().size() + "\n";
		for(int i = 0;i < getMyMoons().size();i++) {
			out += "{\n";
			out += getMyMoons().get(i).saveString() + "\n";
			out += "}\n";
		}
		out += getMySatilights().size() + "\n";
		for(int i = 0;i < getMySatilights().size();i++) {
			out += "{\n";
			out += getMySatilights().get(i).saveString() + "\n";
			out += "}\n";			
		}
		out += getMyMonth() + "\n";
		out += getMyMoonOrbit() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 934805;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
