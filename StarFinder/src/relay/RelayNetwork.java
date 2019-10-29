package relay;

import java.io.Serializable;
import java.util.Random;
import java.util.Vector;

import astronomy.Galaxy;
import engine.Savable;
import utilities.ExtendedMathmatics;

public class RelayNetwork implements Serializable, Savable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106582796115738519L;

	private Galaxy myGalaxy;

	private Vector<PrimaryRelay> myPrimes = new Vector<PrimaryRelay>();

	public RelayNetwork(Galaxy g, int i) {
		// TODO Auto-generated constructor stub
	}
	
	public void GeneratePrimeNetwork() {
		for(int i = 0;i < myGalaxy.getMySectors().size();i++) {
			myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i)));
			if(i < myGalaxy.getMySectors().size()-1) {
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(i+1)));
			}else {
				myPrimes.add(PrimaryRelay.randomPrime(myGalaxy.getMySectors().get(0)));
			}
			
			myPrimes.get(2*i).setMyPartner(myPrimes.get(2*i+1));
			myPrimes.get(2*i+1).setMyPartner(myPrimes.get(2*i));
		}
		
		for(int i = 0;i < myGalaxy.getMySectors().size();i++)
			for(int j = 0;j < myGalaxy.getMySectors().get(i).getRegions().size();j++) {
				
			}
	}

	@Override
	public int loadString(String load) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String saveString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClassIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
	
}