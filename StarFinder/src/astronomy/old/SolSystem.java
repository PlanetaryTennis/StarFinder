package astronomy.old;

import java.io.Serializable;
import java.util.Random;

import astronomy.AstroObject;
import astronomy.Star;
import map.SettingList;
import units.sidistance;
import utilities.ARRAY;
import utilities.RandomList;

public class SolSystem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6219707992537343564L;
	private Star myStar;
	private Planet[] myObjects;
	private String myName;
	private Zone myZone;

	public int Stars;
	public int Terrestrials;
	public int Habitables;
	public int Jovians;
	public int Belts;

	public int Moons;
	public int Asteroids;
	public int HabMoons;

	public void count() {
		Stars = 1;
		Terrestrials = 0;
		Habitables = 0;
		Jovians = 0;
		Belts = 0;
		Moons = 0;
		Asteroids = 0;
		HabMoons = 0;

		for(int i = 0;i < myObjects.length;i++) {
			if(myObjects[i].getClass()==Jovian.class) {
				Jovians++;
			}else if(myObjects[i].getClass()==Habitable.class) {
				Habitables++;
			}else if(myObjects[i].getClass()==Belt.class) {
				Belts++;
			}else if(myObjects[i].getClass()==Terrestrial.class) {
				Terrestrials++;
			}

			myObjects[i].count();
			Moons += myObjects[i].Moons;
			Asteroids += myObjects[i].Asteroids;
			HabMoons += myObjects[i].HabMoons;
		}
	}

	public SolSystem(Star star,String name, Zone s){
		myStar = star;
		myName = name;
		myZone = s;
	}

	public void Add(Planet planet) {
		if(myObjects == null) {
			myObjects = new Planet[1];
			myObjects[0] = planet;
			return;
		}
		Planet[] temp = new Planet[myObjects.length+1];
		for(int i = 0;i < myObjects.length;i++) {
			temp[i] = myObjects[i];
		}
		temp[myObjects.length] = planet;
		myObjects = temp;
	}

	public Star getMyStar() {
		return myStar;
	}

	public void setMyStar(Star myStar) {
		this.myStar = myStar;
	}

	public Planet[] getMyObjects() {
		return myObjects;
	}

	public void setMyObjects(Planet[] myObjects) {
		this.myObjects = myObjects;
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public Zone getMyZone() {
		return myZone;
	}

	public void setMyZone(Zone myZone) {
		this.myZone = myZone;
	}

	//static values for generation
	public static RandomList orbits;

	public static final int NORMALSTAR = 0;
	public static final int BROWNSTAR = 1;
	public static final int WHITESTAR = 2;
	public static final int NUETRONSTAR = 3;
	public static final int BLACKHOLES = 4;
	public static final int SIZEONESTAR = 5;
	public static final int SIZETWOSTAR = 6;
	public static final int SIZETHREESTAR = 7;
	
	public static SolSystem makeRandom(Zone s, SettingList SL) {

		SolSystem r = new SolSystem(null,SolSystem.randomName(),s);
		Random ran = new Random();
		int special = ran.nextInt(1000);
		if(SL.getSuns()==null||special <= SL.getSuns()[0]||!SL.isSpecial()) {
			r.setMyStar(Star.randomStar(r,SL.getSuns()));
			int k = ran.nextInt(3)+1;
			int total = ran.nextInt(SL.getPlanetmax()+1)+1;
			sidistance[] o = new sidistance[total+k];
			Planet put;
			sidistance[] hab = r.myStar.getHabitablezone();
			sidistance frost = r.myStar.getFrostLine();
			sidistance[] sup;
			int measure = (int)(frost.scale(2.0).compair(AstroObject.AU)*1000);
			if(measure == 0) {
				measure = (int)(frost.scale(2.0).compair(AstroObject.AU)*1000);
			}
			while(r.getMyObjects() == null) {
				for(int i = 0;i < total;i++) {
					o[i] = AstroObject.AU.scale((double)(ran.nextInt(measure)+1)/1000);
				}

				if(k == 3) {
					sup = new sidistance[]{hab[0],hab[1],frost};
				}else if(k == 2){
					sup = new sidistance[]{hab[ran.nextInt(2)],frost};
				}else {
					sup = new sidistance[] {frost};
				}



				for(int i = 0;i < k;i++) {
					o[total+i] = sup[i];
				}
				o = ARRAY.SORT(o);

				k = 0;
				int a = 0;
				int z = 0;
				for(int i = 0;i < o.length;i++) {
					if(o[i].lessOrEqual(r.myStar.getMyRadius().scale(2.0))) {
					}else if(o[i].equalTo(hab[0])) {
						put = Belt.makeRandom(o[i],r.getMyStar());
						if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
						r.Add(put);
					}else if(o[i].lessThan(hab[0])) {
						z++;
						put = Terrestrial.makeRandom(o[i], r.getMyStar());
						if(!SL.isName())put.setMyName(""+(char)('a'+k++));
						r.Add(put);
					}else if(o[i].equalTo(hab[1])) {
						put = Belt.makeRandom(o[i],r.getMyStar());
						if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
						r.Add(put);
					}else if(o[i].lessThan(hab[1])) {
						z++;
						int u = ran.nextInt(100);
						if(u < 75) {
							put = Terrestrial.makeRandom(o[i], r.getMyStar());
						}else {
							put = Jovian.makeRandom(o[i], r.getMyStar());
						}
						if(!SL.isName())put.setMyName(""+(char)('a'+k++));
						r.Add(put);
					}else if(o[i].equalTo(frost)) {
						put = Belt.makeRandom(o[i],r.getMyStar());
						if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
						r.Add(put);
					}else if(o[i].lessThan(frost)) {
						z++;
						int u = ran.nextInt(100);
						if(u < 50) {
							put = Terrestrial.makeRandom(o[i], r.getMyStar());
						}else {
							put = Jovian.makeRandom(o[i], r.getMyStar());
						}
						if(!SL.isName())put.setMyName(""+(char)('a'+k++));
						r.Add(put);
					}else {
						put = Belt.makeRandom(o[i],r.getMyStar());
						if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
						r.Add(put);
					}
				}
			}
		}else if(special <= SL.getSuns()[1]) {
			r.setMyStar(BrownDwarf.randomStar(r));
			int total = ran.nextInt(SL.getPlanetmax()+1)+1;
			sidistance[] o = new sidistance[total];
			
			for(int i = 0;i < total;i++) {
				o[i] = AstroObject.AU.scale((double)(ran.nextInt(20)+1)/1000);
			}
			
			int z = 0;
			int k = 0;
			Planet put;
			for(int i = 0;i < o.length;i++) {
				if(i==0) {
					put = Belt.makeRandom(o[i], r.getMyStar());
					if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
					r.Add(put);
				}else {
					int h = ran.nextInt(3);
					switch(h) {
					case 0:
						put = Belt.makeRandom(o[i], r.getMyStar());
						if(!SL.isName())put.setMyName((char)(Planet.ALPHA+i-z) + " Belt");
						r.Add(put);
						break;
					case 1:
						z++;
						put = Terrestrial.makeRandom(o[i], r.getMyStar());
						if(!SL.isName())put.setMyName(""+(char)('a'+k++));
						r.Add(put);
						break;
					case 2:
						z++;
						put = Jovian.makeRandom(o[i], r.getMyStar());
						if(!SL.isName())put.setMyName(""+(char)('a'+k++));
						r.Add(put);
						break;
					}
				}
			}
		}else if(special <= SL.getSuns()[2]) {
			r.setMyStar(WhiteDwarf.randomStar(r));
			int total = ran.nextInt(SL.getPlanetmax()+1)+1;
			sidistance[] o = new sidistance[total];
			for(int i = 0;i < total;i++) {
				o[i] = AstroObject.AU.scale((double)(ran.nextInt(100)+1)/1000);
			}			
			Planet put;
			for(int i = 0;i < o.length;i++) {
				put = Belt.makeRandom(o[i],r.getMyStar());
				r.Add(put);
				if(!SL.isName()) put.setMyName((char)(Planet.ALPHA+i)+" Belt");
			}
		}else if(special<=SL.getSuns()[3]){
			r.setMyStar(Neutron.randomStar(r));
			int total = ran.nextInt(SL.getPlanetmax()+1)+1;
			sidistance[] o = new sidistance[total];
			for(int i = 0;i < total;i++) {
				o[i] = AstroObject.AU.scale((double)(ran.nextInt(500)+1)/1000);
			}
			Planet put;
			for(int i = 0;i < o.length;i++) {
				put = Belt.makeRandom(o[i],r.getMyStar());
				r.Add(put);
				if(!SL.isName()) put.setMyName((char)(Planet.ALPHA+i)+" Belt");
			}			
		}else{
			r.setMyName(r.getMyName()+" Singularity");
			System.out.println("Black Hole");
			r.setMyStar(new BlackHole(ran.nextInt(100)+13, r));
			int total = ran.nextInt(SL.getPlanetmax()+1)+1;
			sidistance[] o = new sidistance[total];
			for(int i = 0;i < total;i++) {
				o[i] = AstroObject.AU.scale((double)(ran.nextInt(500)+1)/1000);
			}
			Planet put;
			for(int i = 0;i < o.length;i++) {
					put = Belt.makeRandom(o[i],r.getMyStar());
					r.Add(put);
					if(!SL.isName()) put.setMyName((Planet.ALPHA+i)+" Belt");
				}
		}
		return r;
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
}
