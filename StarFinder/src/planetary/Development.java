package planetary;

import java.util.UUID;
import java.util.Vector;

import engine.Savable;
import utilities.StringFundementals;

public class Development implements Savable {

	private String myName;
	private int myCost;
	private Vector<Development> myUpgrades = new Vector<Development>();

	public Development(String load) {
		this.loadString(load);
	}

	public Development(String name, int cost) {
		myName = name;
		myCost = cost;
		myID = UUID.randomUUID().toString() + ".Surface";
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public int getMyCost() {
		return myCost;
	}

	public void setMyCost(int myCost) {
		this.myCost = myCost;
	}

	public Vector<Development> getMyUpgrades() {
		return myUpgrades;
	}

	public void setMyUpgrades(Vector<Development> myUpgrades) {
		this.myUpgrades = myUpgrades;
	}

	public void add(Development dev) {
		this.myUpgrades.add(dev);
		myCost = dev.getMyCost();
	}

	public boolean remove(int i) {
		return myUpgrades.remove(i) != null;
	}

	public static final int CLASSINDEX = 119417;

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
		String[] in = StringFundementals.breakByLine(parse.get(0));
		myID = in[0];
		int k = 2;
		myName = in[k++];
		myCost = Integer.parseInt(in[k++]);
		numberUpgrades = Integer.parseInt(in[k++]);
		for (int i = 0; i < numberUpgrades; i++) {
			add(new Development(parse.get(1 + i)));
		}
		return k;
	}

	int numberUpgrades;

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += CLASSINDEX + "\n";
		out += myName + "\n";
		out += myCost + "\n";
		out += myUpgrades.size() + "\n";
		for (int i = 0; i < myUpgrades.size(); i++) {
			out += "{\n";
			out += myUpgrades.get(i).saveString();
			out += "}\n";
		}
		return out;
	}

	public String read() {
		String out = "";
		out += "[";
		for (int i = 0; i < myUpgrades.size(); i++) {
			out += myUpgrades.get(i).getMyName();
			if (i + 1 != myUpgrades.size())
				out += ", ";
		}
		out += "]\n";
		return out;
	}
}
