package astronomy.planetary;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.SolSystem;
import astronomy.stellar.Star;
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
		myID = UUID.randomUUID().toString();
	}

	@Override
	public String string() {
		return null;
	}

	public static Random ran = new Random(System.currentTimeMillis());
	
	public static Planet makeRandom(double orbit, Star star) {
		int m = ran.nextInt(30)+25;
		double d = ExtendedMathmatics.log(ran.nextInt(499)+1, 1000)/8;
		Vector<Moon> moons = new Vector<Moon>(m);
		
		for(int i = 0;i < m;i++) {
			moons.set(i, Asteroid.makeRandom(d, orbit, star));
			moons.get(i).setMyName(nameMoon(i));
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
	public void loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		myName = in[i++];
		myEccentricity = Double.parseDouble(in[i++]);
		myOrbit = Double.parseDouble(in[i++]);
		myInnerOrbit = Double.parseDouble(in[i++]);
		myOuterOrbit = Double.parseDouble(in[i++]);
		MoonNumber = Integer.parseInt(in[i++]);
		MoonIDs = new Vector<String>();
		for(int k = 0;k < MoonNumber;k++) {
			MoonIDs.add(in[i++]);
		}
	}
	
	protected int MoonNumber;
	protected Vector<String> MoonIDs;

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
			out += getMyMoons().get(i).getID() + "\n";
		}
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

}
