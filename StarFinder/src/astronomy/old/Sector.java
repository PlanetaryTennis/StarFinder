package astronomy.old;

import java.io.Serializable;
import java.util.Random;

import map.SettingList;
import utilities.RandomList;

public class Sector implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7823345276729076886L;
	private Region [] myRegions;
	private Galaxy myGalaxy;
	private String myName;
	
	public int Stars;
	public int Terrestrials;
	public int Habitables;
	public int Jovians;
	public int Belts;
	public int Moons;
	public int Asteroids;
	public int HabMoons;
	
	public void count() {
		Stars = 0;
		Terrestrials = 0;
		Habitables = 0;
		Jovians = 0;
		Belts = 0;
		Moons = 0;
		Asteroids = 0;
		HabMoons = 0;
		
		for(int i = 0;i < myRegions.length;i++) {
			myRegions[i].count();
			
			Stars += myRegions[i].Stars;
			
			Terrestrials += myRegions[i].Terrestrials;
			Habitables += myRegions[i].Habitables;
			Jovians += myRegions[i].Jovians;
			Belts += myRegions[i].Belts;

			Moons += myRegions[i].Moons;
			Asteroids += myRegions[i].Asteroids;
			HabMoons += myRegions[i].HabMoons;
		}
	}
	
	public Galaxy getMyGalaxy() {
		return myGalaxy;
	}

	public void setMyGalaxy(Galaxy myGalaxy) {
		this.myGalaxy = myGalaxy;
	}

	public Sector(String name){
		myName = name;
	}
	
	public void Add(Region region) {
		if(myRegions == null) {
			myRegions = new Region[1];
			myRegions[0] = region;
			return;
		}
		Region[] temp = new Region[myRegions.length+1];
		for(int i = 0;i < myRegions.length;i++) {
			temp[i] = myRegions[i];
		}
		temp[myRegions.length] = region;
		region.setMySector(this);
		myRegions = temp;
	}
	
	public Region[] getRegions() {
		return myRegions;
	}
	
	public String getName() {
		return myName;
	}
	
	public static RandomList consonants = RandomList.zif(new String[] {"t","n","s","r","h","th","sh","tt","l","d","c","ch","m","nd","f","p","ph","g","ng","ch","w","y","wh","b","v","k","x","j","q","z"});
	public static RandomList vowels = RandomList.zif(new String[]{"e","a","o","i","u","ee","oo","ie","ae","y","e","a","o","i","u","ee","oo","ie","ae","y","-","'"});
	
	public static Sector makeRandom(SettingList SL) {
		Sector r = new Sector(Sector.randomName());
		Random ran = new Random();
		int total = ran.nextInt(SL.getRegionsmax()+1)+SL.getRegionsmin();
		for(int i = 0;i < total;i++) {
			Region z = Region.makeRandom(r,SL);
			if(!SL.isName())z.setName(""+i);
			r.Add(z);
		}
		return r;
	}

	public static String randomName() {
		String s = "";
		Random ran = new Random();
		int i = ran.nextInt(4)+1;
		for(;i > 0;i--) {
			s+=consonants.get();
			s+=vowels.get();
		}
		return s;
	}

	public void setName(String text) {
		myName = text;
	}
}
