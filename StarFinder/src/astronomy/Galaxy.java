package astronomy;

import java.io.Serializable;
import java.util.UUID;
import java.util.Vector;

import engine.Savable;
import relay.RelayNetwork;
import utilities.StringFundementals;

public class Galaxy implements Serializable, Savable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209507012552543563L;
	private Vector<Sector> mySectors;
	private String myName;
	private RelayNetwork myNetwork;
	
	public Galaxy(Vector<Sector> sectors) {
		mySectors = sectors;
		myID = UUID.randomUUID().toString();
	}
	
	public Galaxy(String load) {
		this.loadString(load);
	}

	public RelayNetwork getMyNetwork() {
		return myNetwork;
	}

	public void setMyNetwork(RelayNetwork myNetwork) {
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
	public void loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int k = 2;
		myName = in[k++];
		SectorNumber = Integer.parseInt(in[k++]);
		for(int i = 0;i < SectorNumber;i++) {
			SectorIDs.add(in[k++]);
		}
	}
	
	int SectorNumber;
	Vector<String> SectorIDs;

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += this.getMyName() + "\n";
		out += this.getMySectors().size() + "\n";
		for(int i = 0;i < this.getMySectors().size();i++){
			out += this.getMySectors().get(i).getID() + "\n";
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
}
