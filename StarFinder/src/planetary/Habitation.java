package planetary;

import java.util.UUID;
import java.util.Vector;

import engine.Savable;
import utilities.StringFundementals;

public class Habitation implements Savable {

	private Vector<Development> myDevelopments = new Vector<Development>();
	private int maxDev;

	public int getMaxDev() {
		return maxDev;
	}

	public void setMaxDev(int maxDev) {
		this.maxDev = maxDev;
	}

	public static final int CLASSINDEX = 117897;

	public Habitation(int size) {
		maxDev = (int) (3 * (Math.pow(size * 3 + 1, 2) + 1));
		myID = UUID.randomUUID().toString() + ".Surface";
	}

	Habitation(String load) {
		this.loadString(load);
	}

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
	public int loadString(String load) {
		Vector<String> parse = StringFundementals.unnestString('{', '}', load);
		Vector<String> in = StringFundementals.breakByLine(parse.get(0));
		myID = in.get(0);
		int k = 2;
		maxDev = Integer.parseInt(in.get(k++));
		numberDevelopments = Integer.parseInt(in.get(k++));
		for (int i = 0; i < numberDevelopments; i++) {
			myDevelopments.add(new Development(parse.get(1 + i)));
		}
		return k;
	}

	int numberDevelopments;

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX + "\n";
		out += maxDev + "\n";
		out += myDevelopments.size() + "\n";
		for (int i = 0; i < myDevelopments.size(); i++) {
			out += "{\n";
			out += myDevelopments.get(i).saveString();
			out += "}\n";
		}
		return out;
	}

	public Vector<Development> getMyDevelopments() {
		return myDevelopments;
	}

	public void setMyDevelopments(Vector<Development> myDevelopments) {
		this.myDevelopments = myDevelopments;
	}

}
