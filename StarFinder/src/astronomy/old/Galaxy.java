package astronomy.old;

import java.io.Serializable;

import relay.RelayNetwork;

public class Galaxy implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209507012552543563L;
	private Sector[] mySectors;
	private String myName;
	private RelayNetwork myNetwork;
	
	public int Stars;
	public int Terrestrials;
	public int Habitables;
	public int Jovians;
	public int Belts;
	public int Moons;
	public int Asteroids;
	public int HabMoons;
	
	public Galaxy(Sector[] mySectors){
		this.mySectors = mySectors;
		myName = Sector.randomName();
	}
	
	public void count(){
		Stars = 0;
		Terrestrials = 0;
		Habitables = 0;
		Jovians = 0;
		Belts = 0;
		Moons = 0;
		Asteroids = 0;
		HabMoons = 0;
		
		for(int i = 0;i < mySectors.length;i++) {
			mySectors[i].count();
			
			Stars += mySectors[i].Stars;
			
			Terrestrials += mySectors[i].Terrestrials;
			Habitables += mySectors[i].Habitables;
			Jovians += mySectors[i].Jovians;
			Belts += mySectors[i].Belts;

			Moons += mySectors[i].Moons;
			Asteroids += mySectors[i].Asteroids;
			HabMoons += mySectors[i].HabMoons;
		}
	}

	public RelayNetwork getMyNetwork() {
		return myNetwork;
	}

	public void setMyNetwork(RelayNetwork myNetwork) {
		this.myNetwork = myNetwork;
	}

	public int getStars() {
		return Stars;
	}

	public void setStars(int stars) {
		Stars = stars;
	}

	public int getTerrestrials() {
		return Terrestrials;
	}

	public void setTerrestrials(int terrestrials) {
		Terrestrials = terrestrials;
	}

	public int getHabitables() {
		return Habitables;
	}

	public void setHabitables(int habitables) {
		Habitables = habitables;
	}

	public int getJovians() {
		return Jovians;
	}

	public void setJovians(int jovians) {
		Jovians = jovians;
	}

	public int getBelts() {
		return Belts;
	}

	public void setBelts(int belts) {
		Belts = belts;
	}

	public int getMoons() {
		return Moons;
	}

	public void setMoons(int moons) {
		Moons = moons;
	}

	public int getAsteroids() {
		return Asteroids;
	}

	public void setAsteroids(int asteroids) {
		Asteroids = asteroids;
	}

	public int getHabMoons() {
		return HabMoons;
	}

	public void setHabMoons(int habMoons) {
		HabMoons = habMoons;
	}

	public Sector[] getMySectors() {
		return mySectors;
	}

	public void setMySectors(Sector[] mySectors) {
		this.mySectors = mySectors;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}
}
