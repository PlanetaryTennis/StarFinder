package astronomy;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

import engine.Namable;
import engine.Savable;
import gate.GateNetwork;
import utilities.StringFundementals;

public class Galaxy implements Serializable, Savable, Namable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209507012552543563L;
	private Vector<Sector> mySectors = new Vector<Sector>();
	private String myName;
	private GateNetwork myNetwork;

	public Galaxy(Vector<Sector> sectors) {
		mySectors = sectors;
		myID = UUID.randomUUID().toString() + ".galaxy";
	}

	public Galaxy(String load) {
		this.loadString(load);
	}

	public GateNetwork getMyNetwork() {
		return myNetwork;
	}

	public void setMyNetwork(GateNetwork myNetwork) {
		this.myNetwork = myNetwork;
	}

	public Vector<Sector> getMySectors() {
		return mySectors;
	}

	public void setMySectors(Vector<Sector> mySectors) {
		this.mySectors = mySectors;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		Vector<String> in = StringFundementals.breakByLine(object.get(0));
		myID = in.get(0);
		int k = 2;
		myName = in.get(k++);
		setSectorNumber(Integer.parseInt(in.get(k++)));
		for (int i = 0; i < getSectorNumber(); i++) {
			mySectors.add(new Sector(object.get(i + 1), 0.0d));
			mySectors.get(i).setMyGalaxy(this);
		}
		return k;
	}

	private int SectorNumber;
	private Vector<String> SectorIDs = new Vector<String>();

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += this.getMyName() + "\n";
		out += this.getMySectors().size() + "\n";
		for (int i = 0; i < this.getMySectors().size(); i++) {
			out += "{\n";
			out += mySectors.get(i).saveString() + "\n";
			out += "}\n";
			;
		}
		return out;
	}

	public static final int CLASSINDEX = 937153;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public int getSectorNumber() {
		return SectorNumber;
	}

	public void setSectorNumber(int sectorNumber) {
		SectorNumber = sectorNumber;
	}

	public Vector<String> getSectorIDs() {
		return SectorIDs;
	}

	public void setSectorIDs(Vector<String> sectorIDs) {
		SectorIDs = sectorIDs;
	}
}
