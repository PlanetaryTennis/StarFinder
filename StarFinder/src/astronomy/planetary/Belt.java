package astronomy.planetary;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import astronomy.SolSystem;
import astronomy.stellar.Star;
import map.SettingList;
import utilities.ExtendedMathmatics;
import utilities.StringFundementals;

public class Belt extends Planet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729038741857852169L;

	public Belt(String load) {
		this.loadString(load);
	}
	
	public Belt(Vector<Moon> myMoons,double myEccentricity, double myOrbit, Star star) {
		super(myMoons, myEccentricity, myOrbit, star);
		myID = UUID.randomUUID().toString()+".Planet";
	}

	@Override
	public String string() {
		return null;
	}

	public static Random ran = new Random(System.currentTimeMillis());
	
	public static Planet makeRandom(double orbit, Star star, SettingList sL) {
		int m = ran.nextInt(30)+25;
		double d = ExtendedMathmatics.log(ran.nextInt(499)+1, 1000)/8;
		Vector<Moon> moons = new Vector<Moon>(m);
		
		for(int i = 0;i < m;i++) {
			moons.add(Asteroid.makeRandom(d, orbit, star));
			if(sL.isName()) {
				moons.get(i).setMyName(SolSystem.randomName());
			}else {
				moons.get(i).setMyName(nameMoon(i));				
			}
		}
		
		Belt b = new Belt(moons, d, orbit, star);
		
		b.setMyName(SolSystem.randomName() + " Belt");
		
		return b;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/sprites/systems/Asteroid Belt.png"));
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.get(0));
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myEccentricity = Double.parseDouble(in[i++]);
		myOrbit = Double.parseDouble(in[i++]);
		myInnerOrbit = Double.parseDouble(in[i++]);
		myOuterOrbit = Double.parseDouble(in[i++]);
		setMoonNumber(Integer.parseInt(in[i++]));
		int j = 1;
		for(int k = 0;k < getMoonNumber();k++) {
			getMyMoons().add((Moon) Planet.parseLoad(object.get(j)));
			j++;
		}
		setSatilightNumber(Integer.parseInt(in[i++]));
		for(int k = 0;k < getSatilightNumber();k++) {
			if(j>=object.size()) {
				break;
			}
			getMySatilights().add(OrbitObject.parseLoad(object.get(j)));
			j++;
		}
		return i;
	}
	
	private int MoonNumber;
	private Vector<String> MoonIDs = new Vector<String>();
	private int SatilightNumber;
	private Vector<String> SatilightIDs = new Vector<String>();
	

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += getMyName() + "\n";
		out += myEccentricity + "\n";
		out += myOrbit + "\n";
		out += myInnerOrbit +"\n";
		out += myOuterOrbit + "\n";
		out += getMyMoons().size() + "\n";
		for(int i = 0;i < getMyMoons().size();i++) {
			out += "{\n";
			out += getMyMoons().get(i).saveString() + "\n";
			out += "}\n";
		}
		int g = 0;
		String Append = "";
		for(int i = 0;i < getMySatilights().size();i++) {
			if(getMySatilights().get(i)==null) {
				g++;
				break;
			}
			Append += "{\n";
			Append += getMySatilights().get(i).saveString() + "\n";
			Append += "}\n";			
		}
		out += getMySatilights().size() - g + "\n";
		out += Append;
		return out;
	}
	
	public static final int CLASSINDEX = 934892;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;
	
	@Override
	public String getID() {
		return myID;
	}

	public int getMoonNumber() {
		return MoonNumber;
	}

	public void setMoonNumber(int moonNumber) {
		MoonNumber = moonNumber;
	}

	public Vector<String> getMoonIDs() {
		return MoonIDs;
	}

	public void setMoonIDs(Vector<String> moonIDs) {
		MoonIDs = moonIDs;
	}

	public int getSatilightNumber() {
		return SatilightNumber;
	}

	public void setSatilightNumber(int satilightNumber) {
		SatilightNumber = satilightNumber;
	}

	public Vector<String> getSatilightIDs() {
		return SatilightIDs;
	}

	public void setSatilightIDs(Vector<String> satilightIDs) {
		SatilightIDs = satilightIDs;
	}

}
