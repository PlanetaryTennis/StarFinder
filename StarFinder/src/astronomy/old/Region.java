package astronomy.old;

import java.io.Serializable;
import java.util.Random;

import map.SettingList;
import utilities.RandomList;

public class Region implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Zone [] myZones;
	private Sector mySector;
	private String Name;

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
		
		for(int i = 0;i < myZones.length;i++) {
			myZones[i].count();
			
			Stars += myZones[i].Stars;
			
			Terrestrials += myZones[i].Terrestrials;
			Habitables += myZones[i].Habitables;
			Jovians += myZones[i].Jovians;
			Belts += myZones[i].Belts;

			Moons += myZones[i].Moons;
			Asteroids += myZones[i].Asteroids;
			HabMoons += myZones[i].HabMoons;
		}
	}
	
	public Region(String Name,Sector sector){
		this.Name = Name;
		this.mySector = sector;
	}
	
	public void Add(Zone zone) {
		if(myZones == null) {
			myZones = new Zone[1];
			myZones[0] = zone;
			return;
		}
		Zone[] temp = new Zone[myZones.length+1];
		for(int i = 0;i < myZones.length;i++) {
			temp[i] = myZones[i];
		}
		temp[myZones.length] = zone;
		zone.setMyRegion(this);
		myZones = temp;
	}

	public Sector getMySector() {
		return mySector;
	}

	public void setMySector(Sector mySector) {
		this.mySector = mySector;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Zone[] getMyZones() {
		return myZones;
	}

	public static RandomList consonants;
	public static RandomList vowels;
	public static void gen() {
		consonants = new RandomList();
		vowels = new RandomList();
		vowels.add(10,"a");
		consonants.add(8,"b");
		consonants.add(9,"c");
		consonants.add(8,"d");
		vowels.add(7,"e");
		consonants.add(5,"f");
		consonants.add(6,"g");
		consonants.add(4,"h");
		vowels.add(10,"i");
		consonants.add(3,"j");
		consonants.add(5,"k");
		consonants.add(7,"l");
		consonants.add(9,"m");
		consonants.add(10,"n");
		vowels.add(6,"o");
		consonants.add(2,"p");
		consonants.add(1,"q");
		consonants.add(8,"r");
		consonants.add(10,"s");
		consonants.add(10,"t");
		vowels.add(5,"u");
		consonants.add(2,"v");
		consonants.add(4,"w");
		consonants.add(1,"x");
		consonants.add(1,"y");
		consonants.add(1,"z");
		vowels.add(1,"-");
		consonants.add(3,"ll");
		consonants.add(4,"sh");
		consonants.add(6,"th");
		consonants.add(1,"wh");
		vowels.add(3,"oo");
		vowels.add(4,"ie");
		vowels.add(2,"ae");
		vowels.add(3,"ee");
		consonants.add(4,"tr");
		consonants.add(3,"cr");
		consonants.add(2,"ch");
		vowels.add(1,"y");
		vowels.add(1,"'");
	}
	
	public static Region makeRandom(Sector s, SettingList SL) {
		Region r = new Region(Region.randomName(),s);
		Random ran = new Random();
		int total = ran.nextInt(SL.getZonemax()+1)+SL.getSysmin();
		for(int i = 0;i < total;i++) {
			Zone z = Zone.makeRandom(r,SL);
			if(!SL.isName())z.setMyName(""+i);
			r.Add(z);
		}
		return r;
	}

	public static String randomName() {
		if(consonants==null) gen();
		String s = "";
		Random ran = new Random();
		int i = ran.nextInt(4)+1;
		for(;i > 0;i--) {
			s+=consonants.get();
			s+=vowels.get();
		}
		return s;
	}
}