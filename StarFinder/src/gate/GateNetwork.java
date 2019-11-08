package gate;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import astronomy.Galaxy;
import astronomy.Region;
import astronomy.Zone;
import engine.Savable;
import utilities.StringFundementals;

public class GateNetwork implements Serializable, Savable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106582796115738519L;

	private Galaxy myGalaxy;

	private Vector<PrimaryGate> myPrimes = new Vector<PrimaryGate>();
	private Vector<Vector<SecondaryGate>> myPods = new Vector<Vector<SecondaryGate>>();

	public GateNetwork(Galaxy g, int i) {
		myGalaxy = g;
		myID = "Gate.network";
		GeneratePrimeNetwork();
		GenerateSecondNetwork();
	}

	public GateNetwork(String load) {
		this.loadString(load);
	}

	Random random = new Random(System.currentTimeMillis());

	private String myID;

	public void GeneratePrimeNetwork() {
		int k = 0;
		for (int i = 0; i < myGalaxy.getMySectors().size(); i++) {
			myPrimes.add(PrimaryGate.randomPrime(myGalaxy.getMySectors().get(i).getRegions().get(0)));
			if (i < myGalaxy.getMySectors().size() - 1) {
				myPrimes.add(PrimaryGate.randomPrime(myGalaxy.getMySectors().get(i + 1).getRegions()
						.get(random.nextInt(myGalaxy.getMySectors().get(i + 1).getRegions().size()))));
			} else {
				myPrimes.add(PrimaryGate.randomPrime(myGalaxy.getMySectors().get(0).getRegions()
						.get(random.nextInt(myGalaxy.getMySectors().get(0).getRegions().size()))));
			}
			myPrimes.get(2 * i).setMyPartner(myPrimes.get(2 * i + 1));
			myPrimes.get(2 * i + 1).setMyPartner(myPrimes.get(2 * i));
			k += 2;
		}

		for (int i = 0; i < myGalaxy.getMySectors().size(); i++)
			for (int j = 0; j < myGalaxy.getMySectors().get(i).getRegions().size(); j++) {
				myPrimes.add(PrimaryGate.randomPrime(myGalaxy.getMySectors().get(i).getRegions().get(j)));
				myPrimes.add(PrimaryGate.randomPrime(myGalaxy.getMySectors().get(i).getRegions()
						.get(random.nextInt(myGalaxy.getMySectors().get(i).getRegions().size()))));
				myPrimes.get(k).setMyPartner(myPrimes.get(k + 1));
				k++;
				myPrimes.get(k).setMyPartner(myPrimes.get(k - 1));
			}
	}

	public void GenerateSecondNetwork() {
		for (int i = 0; i < myGalaxy.getMySectors().size(); i++)
			for (int j = 0; j < myGalaxy.getMySectors().get(i).getRegions().size(); j++)
				myPods.add(randomPod(myGalaxy.getMySectors().get(i).getRegions().get(j)));
	}

	private Vector<SecondaryGate> randomPod(Region region) {
		Vector<SecondaryGate> out = new Vector<SecondaryGate>();
		for (int i = 0; i < region.getMyZones().size(); i++) {
			out.add(SecondaryGate.randomSecond(region.getMyZones().get(i)));
			out.get(i).setMyPod(out);
		}
		return out;
	}

	public void LinkUp(Galaxy galaxy) {
		myGalaxy = galaxy;

		PrimaryGate p;
		Zone z = null;
		for (int i = 0; i < myPrimes.size(); i++) {
			p = myPrimes.get(i);
			for (int k = 0; k < galaxy.getMySectors().size(); k++)
				for (int j = 0; j < galaxy.getMySectors().get(k).getRegions().size(); j++)
					for (int l = 0; l < galaxy.getMySectors().get(k).getRegions().get(j).getMyZones().size(); l++)
						if (p.ZoneID
								.equals(galaxy.getMySectors().get(k).getRegions().get(j).getMyZones().get(l).getID()))
							z = galaxy.getMySectors().get(k).getRegions().get(j).getMyZones().get(l);
			p.setMyZone(z);
		}

		SecondaryGate s;
		for (int i = 0; i < myPods.size(); i++)
			for (int k = 0; k < myPods.get(i).size(); k++) {
				s = myPods.get(i).get(k);
				for (int m = 0; m < galaxy.getMySectors().size(); m++)
					for (int j = 0; j < galaxy.getMySectors().get(m).getRegions().size(); j++)
						for (int l = 0; l < galaxy.getMySectors().get(m).getRegions().get(j).getMyZones().size(); l++)
							if (s.ZoneID.equals(
									galaxy.getMySectors().get(m).getRegions().get(j).getMyZones().get(l).getID()))
								z = galaxy.getMySectors().get(m).getRegions().get(j).getMyZones().get(l);
				s.setMyZone(z);
			}

	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		Vector<String> in = StringFundementals.breakByLine(object.get(0));
		myID = in.get(0);
		int i = 2;
		int j = 1;
		PrimeNumber = Integer.parseInt(in.get(i++));
		for (int k = 0; k < PrimeNumber / 2; k++) {
			myPrimes.add(new PrimaryGate(object.get(j++)));
			myPrimes.add(new PrimaryGate(object.get(j++)));
			myPrimes.get(2 * k).setMyPartner(myPrimes.get(2 * k + 1));
			myPrimes.get(2 * k + 1).setMyPartner(myPrimes.get(2 * k));
		}
		PodNumber = Integer.parseInt(in.get(i++));
		Vector<SecondaryGate> temp;
		for (int k = 0; k < PodNumber; k++) {
			PodSize.add(Integer.parseInt(in.get(i++)));
			temp = new Vector<SecondaryGate>();
			for (int l = 0; l < PodSize.get(k); l++) {
				temp.add(new SecondaryGate(object.get(j++)));
				temp.get(l).setMyPod(temp);
			}
			myPods.add(temp);
		}
		return i;
	}

	public int PrimeNumber;
	public int PodNumber;
	public Vector<Integer> PodSize = new Vector<Integer>();

	@Override
	public String saveString() {
		String out = "";
		out += this.getID() + "\n";
		out += this.getClassIndex() + "\n";
		out += this.myPrimes.size() + "\n";
		for (int i = 0; i < myPrimes.size(); i++) {
			out += "{\n";
			out += myPrimes.get(i).saveString() + "\n";
			out += "}\n";
		}
		out += myPods.size() + "\n";
		for (int i = 0; i < myPods.size(); i++) {
			out += myPods.get(i).size() + "\n";
			for (int k = 0; k < myPods.get(i).size(); k++) {
				out += "{\n";
				out += myPods.get(i).get(k).saveString() + "\n";
				out += "}\n";
			}
		}
		return out;
	}

	public static final int CLASSINDEX = 678802;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	@Override
	public String getID() {
		return myID;
	}

	public Galaxy getMyGalaxy() {
		return myGalaxy;
	}

	public void setMyGalaxy(Galaxy myGalaxy) {
		this.myGalaxy = myGalaxy;
	}

	public Vector<PrimaryGate> getMyPrimes() {
		return myPrimes;
	}

	public void setMyPrimes(Vector<PrimaryGate> myPrimes) {
		this.myPrimes = myPrimes;
	}

	public Vector<Vector<SecondaryGate>> getMyPods() {
		return myPods;
	}

	public void setMyPods(Vector<Vector<SecondaryGate>> myPods) {
		this.myPods = myPods;
	}

	public Random getRandom() {
		return random;
	}

	public void setRandom(Random random) {
		this.random = random;
	}

	public int getPrimeNumber() {
		return PrimeNumber;
	}

	public void setPrimeNumber(int primeNumber) {
		PrimeNumber = primeNumber;
	}

	public int getPodNumber() {
		return PodNumber;
	}

	public void setPodNumber(int podNumber) {
		PodNumber = podNumber;
	}

	public Vector<Integer> getPodSize() {
		return PodSize;
	}

	public void setPodSize(Vector<Integer> podSize) {
		PodSize = podSize;
	}

	public Gate find(String GateID) {
		for (int i = 0; i < myPrimes.size(); i++)
			if (myPrimes.get(i).getID().equals(GateID))
				return myPrimes.get(i);

		for (int i = 0; i < myPods.size(); i++)
			for (int k = 0; k < myPods.get(i).size(); k++)
				if (myPods.get(i).get(k).getID().equals(GateID))
					return myPods.get(i).get(k);

		return null;
	}

}