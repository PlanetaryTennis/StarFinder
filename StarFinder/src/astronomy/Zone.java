package astronomy;

import java.io.Serializable;
import java.util.Random;

import map.SettingList;
import utilities.RandomList;

public class Zone implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7375343829370278946L;
	private SolSystem [] mySystems;
	private String myName;
	private Region myRegion;
	
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
		
		for(int i = 0;i < mySystems.length;i++) {
			mySystems[i].count();
			Stars += mySystems[i].Stars;
			
			Terrestrials += mySystems[i].Terrestrials;
			Habitables += mySystems[i].Habitables;
			Jovians += mySystems[i].Jovians;
			Belts += mySystems[i].Belts;

			Moons += mySystems[i].Moons;
			Asteroids += mySystems[i].Asteroids;
			HabMoons += mySystems[i].HabMoons;
		}
	}
	
	public Zone(String name,Region r){
		myName = name;
		myRegion = r;
	}
	
	public void Add(SolSystem system) {
		if(mySystems == null) {
			mySystems = new SolSystem[1];
			mySystems[0] = system;
			return;
		}
		SolSystem [] temp = new SolSystem[mySystems.length+1];
		for(int i = 0;i < mySystems.length;i++) {
			temp[i] = mySystems[i];
		}
		temp[mySystems.length] = system;
		mySystems = temp;
	}
	
	public String getMyName() {
		return myName;
	}
	public void setMyName(String myName) {
		this.myName = myName;
	}
	public Region getMyRegion() {
		return myRegion;
	}
	public void setMyRegion(Region myRegion) {
		this.myRegion = myRegion;
	}
	public SolSystem[] getMySystems() {
		return mySystems;
	}
	
	public static RandomList consonants;
	public static RandomList vowels;
	public static void gen() {
		consonants = new RandomList();
		vowels = new RandomList();
		vowels.add(10,"a");
		consonants.add(12,"b");
		consonants.add(20,"c");
		consonants.add(15,"d");
		vowels.add(7,"e");
		consonants.add(10,"f");
		consonants.add(13,"g");
		consonants.add(20,"h");
		vowels.add(10,"i");
		consonants.add(10,"j");
		consonants.add(12,"k");
		consonants.add(16,"l");
		consonants.add(20,"m");
		consonants.add(20,"n");
		vowels.add(6,"o");
		consonants.add(9,"p");
		consonants.add(2,"q");
		consonants.add(13,"r");
		consonants.add(20,"s");
		consonants.add(20,"t");
		vowels.add(5,"u");
		consonants.add(11,"v");
		consonants.add(5,"w");
		consonants.add(1,"x");
		consonants.add(3,"y");
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

	public static Zone makeRandom(Region s, SettingList SL) {
		Zone r = new Zone(Zone.randomName(),s);
		Random ran = new Random();
		int total = ran.nextInt(SL.getSysmax()+1)+SL.getSysmin();
		for(int i = 0;i < total;i++) {
			SolSystem z = SolSystem.makeRandom(r,SL);
			if(!SL.isName())z.setMyName(""+i);
			r.Add(z);
		}
		return r;
	}
}
