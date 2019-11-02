package relay;

import java.util.UUID;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import utilities.StringFundementals;

public class ImageRelay implements OrbitObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4109516429752633935L;
	Relay myRelay;
	
	public ImageRelay(Relay relay) {
		myRelay = relay;
		myID = UUID.randomUUID().toString()+".image";
	}

	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		relayID = in[2];
		return 3;
	}

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX + "\n";
		out += myRelay.getID() + "\n";
		return out;
	}

	String relayID;
	
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
		return myRelay.string();
	}

	@Override
	public String getMyName() {
		return myRelay.getMyName();
	}

	@Override
	public ImageIcon getIcon() {
		return myRelay.getIcon();
	}

	public Relay getMyRelay() {
		return myRelay;
	}

	public void setMyRelay(Relay myRelay) {
		this.myRelay = myRelay;
	}

	public String getRelayID() {
		return relayID;
	}

	public void setRelayID(String relayID) {
		this.relayID = relayID;
	}	
	
}
