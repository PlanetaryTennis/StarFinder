package gate;

import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import utilities.StringFundementals;

public class ImageGate implements OrbitObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4109516429752633935L;
	Gate myGate;

	public ImageGate(Gate Gate) {
		myGate = Gate;
		myID = UUID.randomUUID().toString() + ".image";
	}

	public ImageGate(String load) {
		this.loadString(load);
	}

	@Override
	public int loadString(String load) {
		Vector<String> in = StringFundementals.breakByLine(load);
		myID = in.get(0);
		GateID = in.get(2);
		return 3;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX + "\n";
		if (myGate == null) {
			out += getGateID() + "\n";
		} else {
			out += myGate.getID() + "\n";
		}
		return out;
	}

	String GateID;

	public static final int CLASSINDEX = 678809;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	@Override
	public String string() {
		return myGate.string();
	}

	@Override
	public String getMyName() {
		return myGate.getMyName();
	}

	@Override
	public ImageIcon getIcon() {
		return myGate.getIcon();
	}

	public Gate getMyGate() {
		return myGate;
	}

	public void setMyGate(Gate myGate) {
		this.myGate = myGate;
	}

	public String getGateID() {
		return GateID;
	}

	public void setGateID(String GateID) {
		this.GateID = GateID;
	}

}
