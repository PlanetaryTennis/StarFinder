package relay;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import astronomy.Galaxy;
import astronomy.OrbitObject;
import astronomy.Region;
import astronomy.planetary.Moon;
import astronomy.planetary.Planet;
import engine.Savable;
import planetary.Colony;
import utilities.StringFundementals;

public class RelayNetwork implements Serializable, Savable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106582796115738519L;

	private Galaxy myGalaxy;

	private Vector<PrimaryRelay> myPrimes = new Vector<PrimaryRelay>();
	private Vector<Vector<SecondaryRelay>> myPods = new Vector<Vector<SecondaryRelay>>();

	public RelayNetwork(Galaxy g, int i) {
		myGalaxy = g;
		myID = UUID.randomUUID().toString()+".network";
	}
	
	Random random = new Random(System.currentTimeMillis());

	private String myID;
	
	public void GeneratePrimeNetwork() {
		int k = 0;
		for(int i = 0;i < myGalaxy.getMySectors().size();i++) {
			myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i).getRegions().get(0)));
			if(i < myGalaxy.getMySectors().size()-1) {
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i+1).getRegions().get(random.nextInt(myGalaxy.getMySectors().get(i).getRegions().size()))));
			}else {
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(0).getRegions().get(random.nextInt(myGalaxy.getMySectors().get(i).getRegions().size()))));
			}
			myPrimes.get(2*i).setMyPartner(myPrimes.get(2*i+1));
			myPrimes.get(2*i+1).setMyPartner(myPrimes.get(2*i));
			k += 2;
		}
		
		for(int i = 0;i < myGalaxy.getMySectors().size();i++)
			for(int j = 0;j < myGalaxy.getMySectors().get(i).getRegions().size();j++) {
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i).getRegions().get(j)));
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i).getRegions().get(random.nextInt(myGalaxy.getMySectors().get(i).getRegions().size()))));
				myPrimes.get(k).setMyPartner(myPrimes.get(k+1));
				k++;
				myPrimes.get(k).setMyPartner(myPrimes.get(k-1));
			}
	}

	public void GenerateSecondNetwork() {
		for(int i = 0;i < myGalaxy.getMySectors().size();i++)
			for(int j = 0;j < myGalaxy.getMySectors().get(i).getRegions().size();j++)
				myPods.add(randomPod(myGalaxy.getMySectors().get(i).getRegions().get(i)));
	}
	
	private Vector<SecondaryRelay> randomPod(Region region) {
		Vector<SecondaryRelay> out = new Vector<SecondaryRelay>();
		for(int i = 0;i < region.getMyZones().size();i++) {
			out.add(SecondaryRelay.randomSecond(region.getMyZones().get(i)));
			out.get(i).setMyPod(out);
		}
		return out;
	}

	@Override
	public int loadString(String load) {
		Vector<String> object = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(object.get(0));
		myID = in[0];
		int i = 2;
		int j = 1;
		PrimeNumber = Integer.parseInt(in[i++]);
		for(int k = 0;k < PrimeNumber/2;k++) {
			myPrimes.add(new PrimaryRelay(object.get(j++)));
			myPrimes.add(new PrimaryRelay(object.get(j++)));
			myPrimes.get(2*k).setMyPartner(myPrimes.get(2*k+1));
			myPrimes.get(2*k+1).setMyPartner(myPrimes.get(2*k));
		}
		PodNumber = Integer.parseInt(in[i++]);
		Vector<SecondaryRelay> temp;
		for(int k = 0;k < PodNumber;k++) {
			PodSize.add(Integer.parseInt(in[i++]));
			temp = new Vector<SecondaryRelay>();
			for(int l = 0;l < PodSize.get(k);l++) {
				temp.add(new SecondaryRelay(object.get(j++)));
				temp.get(l).setMyPod(temp);
			}
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
		for(int i = 0;i < myPrimes.size();i++) {
			out += "{\n";
			out += myPrimes.get(i).saveString() + "\n";
			out += "}\n";
		}
		out += myPods.size() + "\n";
		for(int i =0;i < myPods.size();i++) {
			out += myPods.get(i).size() + "\n";
			for(int k = 0;k < myPods.get(i).size();k++) {
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
	
}