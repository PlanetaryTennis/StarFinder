package engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import astronomy.Galaxy;
import astronomy.OrbitObject;
import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Asteroid;
import astronomy.planetary.Belt;
import astronomy.planetary.Habitable;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Jovian;
import astronomy.planetary.Moon;
import astronomy.planetary.Planet;
import astronomy.planetary.Terrestrial;
import astronomy.stellar.BlackHole;
import astronomy.stellar.BrownDwarf;
import astronomy.stellar.Neutron;
import astronomy.stellar.Star;
import astronomy.stellar.WhiteDwarf;
import planetary.Colony;
import planetary.Condition;
import utilities.StringFundementals;

public class ObjectFiles {
	
	public static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		Star s = new Star(2,null);
//		Planet p = Terrestrial.makeRandom(s.getFrostLine(), s);
//		WriteSavabletoFile(p, "test");
//		System.out.println();
//		System.out.print(s.saveString());
		GameLauncher l = new GameLauncher();
		l.getClass();
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			Star s = Star.randomStar(null, null);
//			SolSystem ss = SolSystem.makeRandom(null, new SettingList(0, 0, 0, 0, 0, 0, 0, 4, true, true, true, null));
//			for(int i = 0;i < ss.getMyObjects().length;i++) {
//				if(ss.getMyObjects()[i].getClass()==Jovian.class) {
//						String display = ss.getMyObjects()[i].saveString();
//						System.out.print(display);
//						sc.nextLine();
//				}
//			}
//		}
	}
	
	public static Savable MatrioskaLoad(String galaxy,String filepath){
		Savable s = ReadSaveableFromFile(galaxy+"/"+filepath);
		switch(s.getClassIndex()) {
        case Star.CLASSINDEX:
        	break;
        case WhiteDwarf.CLASSINDEX:
        	break;
        case Neutron.CLASSINDEX:
        	break;
        case BrownDwarf.CLASSINDEX:
        	break;
        case BlackHole.CLASSINDEX:
        	break;
        case Terrestrial.CLASSINDEX:
        	Terrestrial obj1 = (Terrestrial)s;
        	obj1.setMyColony((Colony)MatrioskaLoad(galaxy,obj1.getColonyID()));
        	int m1 = obj1.getMoonNumber();
        	Vector<Moon> moons1 = new Vector<Moon>();
        	for(int i = 0;i < m1;i++) {
        		moons1.add((Moon)MatrioskaLoad(galaxy,obj1.getMoonIDs().get(i)));
        	}
        	obj1.setMyMoons(moons1);
        	int a1 = obj1.getSatilightNumber();
        	Vector<OrbitObject> sats1 = new Vector<OrbitObject>();
        	for(int i = 0;i < a1;i++) {
        		sats1.add((OrbitObject)MatrioskaLoad(galaxy, obj1.getSatilightIDs().get(i)));
        	}
        	obj1.setMySatilights(sats1);
        	break;
        case Moon.CLASSINDEX:
        	Moon obj2 = (Moon)s;
        	obj2.setMyColony((Colony)MatrioskaLoad(galaxy,obj2.getColonyID()));
        	int m2 = obj2.getMoonNumber();
        	Vector<Moon> moons2 = new Vector<Moon>();
        	for(int i = 0;i < m2;i++) {
        		moons2.add((Moon)MatrioskaLoad(galaxy,obj2.getMoonIDs().get(i)));
        	}
        	obj2.setMyMoons(moons2);
        	int a2 = obj2.getSatilightNumber();  
        	Vector<OrbitObject> sats2 = new Vector<OrbitObject>();
        	for(int i = 0;i < a2;i++) {
        		sats2.add((OrbitObject)MatrioskaLoad(galaxy, obj2.getSatilightIDs().get(i)));
        	}
        	obj2.setMySatilights(sats2);
        	break;
        case Jovian.CLASSINDEX:
        	Jovian obj3 = (Jovian)s;
        	obj3.setMyColony((Colony)MatrioskaLoad(galaxy,obj3.getColonyID()));
        	int m3 = obj3.getMoonNumber();
        	Vector<Moon> moons3 = new Vector<Moon>();
        	for(int i = 0;i < m3;i++) {
        		moons3.add((Moon)MatrioskaLoad(galaxy,obj3.getMoonIDs().get(i)));
        	}
        	obj3.setMyMoons(moons3);
        	int a3 = obj3.getSatilightNumber();
        	Vector<OrbitObject> sats3 = new Vector<OrbitObject>();
        	for(int i = 0;i < a3;i++) {
        		sats3.add((OrbitObject)MatrioskaLoad(galaxy, obj3.getSatilightIDs().get(i)));
        	}
        	obj3.setMySatilights(sats3);
        	break;
        case HabitableMoon.CLASSINDEX:
        	HabitableMoon obj4 = (HabitableMoon)s;
        	obj4.setMyColony((Colony)MatrioskaLoad(galaxy,obj4.getColonyID()));
        	int m4 = obj4.getMoonNumber();
        	Vector<Moon> moons4 = new Vector<Moon>();
        	for(int i = 0;i < m4;i++) {
        		moons4.add((Moon)MatrioskaLoad(galaxy,obj4.getMoonIDs().get(i)));
        	}
        	obj4.setMyMoons(moons4);
        	int a4 = obj4.getSatilightNumber();
        	Vector<OrbitObject> sats = new Vector<OrbitObject>();
        	for(int i = 0;i < a4;i++) {
        		sats.add((OrbitObject)MatrioskaLoad(galaxy, obj4.getSatilightIDs().get(i)));
        	}
        	obj4.setMySatilights(sats);
        	obj4.setMyCondition((Condition)MatrioskaLoad(galaxy, obj4.getConditionID()));
        	break;
        case Habitable.CLASSINDEX:
        	Habitable obj5 = (Habitable)s;
        	obj5.setMyColony((Colony)MatrioskaLoad(galaxy,obj5.getColonyID()));
        	int m5 = obj5.getMoonNumber();
        	Vector<Moon> moons5 = new Vector<Moon>();
        	for(int i = 0;i < m5;i++) {
        		moons5.add((Moon)MatrioskaLoad(galaxy,obj5.getMoonIDs().get(i)));
        	}
        	obj5.setMyMoons(moons5);
        	int a5 = obj5.getSatilightNumber();
        	Vector<OrbitObject> sats5 = new Vector<OrbitObject>();
        	for(int i = 0;i < a5;i++) {
        		sats5.add((OrbitObject)MatrioskaLoad(galaxy, obj5.getSatilightIDs().get(i)));
        	}
        	obj5.setMySatilights(sats5);
        	obj5.setMyCondition((Condition)MatrioskaLoad(galaxy, obj5.getConditionID()));
        	break;
        case Belt.CLASSINDEX:
        	Belt obj6 = (Belt)s;
        	int m6 = obj6.getMoonNumber();
        	Vector<Moon> moons6 = new Vector<Moon>();
        	for(int i = 0;i < m6;i++) {
        		moons6.add((Moon)MatrioskaLoad(galaxy,obj6.getMoonIDs().get(i)));
        	}
        	obj6.setMyMoons(moons6);
        	int a6 = obj6.getSatilightNumber();
        	Vector<OrbitObject> sats6 = new Vector<OrbitObject>();
        	for(int i = 0;i < a6;i++) {
        		sats6.add((OrbitObject)MatrioskaLoad(galaxy, obj6.getSatilightIDs().get(i)));
        	}
        	obj6.setMySatilights(sats6);
        	break;
        case Asteroid.CLASSINDEX:
        	Asteroid obj7 = (Asteroid)s;
        	obj7.setMyColony((Colony)MatrioskaLoad(galaxy,obj7.getColonyID()));
        	int m7 = obj7.getMoonNumber();
        	Vector<Moon> moons7 = new Vector<Moon>();
        	for(int i = 0;i < m7;i++) {
        		moons7.add((Moon)MatrioskaLoad(galaxy,obj7.getMoonIDs().get(i)));
        	}
        	obj7.setMyMoons(moons7);
        	int a7 = obj7.getSatilightNumber();  
        	Vector<OrbitObject> sats7 = new Vector<OrbitObject>();
        	for(int i = 0;i < a7;i++) {
        		sats7.add((OrbitObject)MatrioskaLoad(galaxy, obj7.getSatilightIDs().get(i)));
        	}
        	obj7.setMySatilights(sats7);
        	break;
        case SolSystem.CLASSINDEX:
        	SolSystem obj8 = (SolSystem)s;
        	obj8.setMyStar((Star)MatrioskaLoad(galaxy, obj8.getStarID()));
        	obj8.getMyStar().setMySystem(obj8);
        	int p1 = obj8.getPlanetNumber();
        	for(int i = 0;i < p1;i++) {
        		obj8.Add((Planet)MatrioskaLoad(galaxy, obj8.getPlanetIDs().get(i)));
        	}
        	break;
        case Zone.CLASSINDEX:
        	Zone obj9 = (Zone)s;
        	int s1 = obj9.getSystemNumber();
        	for(int i = 0;i < s1;i++) {
        		obj9.Add((SolSystem)MatrioskaLoad(galaxy, obj9.getSystemIDs().get(i)));
        		obj9.getMySystems().get(i).setMyZone(obj9);
        	}
        	break;
        case Region.CLASSINDEX:
        	Region obj10 = (Region)s;
        	int z1 = obj10.getZoneNumber();
        	for(int i = 0;i < z1;i++) {
        		obj10.Add((Zone)MatrioskaLoad(galaxy, obj10.getZoneIDs().get(i)));
        		obj10.getMyZones().get(i).setMyRegion(obj10);
        	}
        	break;
        case Sector.CLASSINDEX:
        	Sector obj11 = (Sector)s;
        	int r1 = obj11.getRegionNumber();
        	for(int i = 0;i < r1;i++) {
        		obj11.Add((Region)MatrioskaLoad(galaxy, obj11.getRegionIDs().get(i)));
        		obj11.getRegions().get(i).setMySector(obj11);
        	}
        	break;
        case Galaxy.CLASSINDEX:
        	Galaxy obj12 = (Galaxy)s;
        	int s2 = obj12.getSectorNumber();
        	Vector<Sector> sects = new Vector<Sector>();
        	for(int i = 0;i < s2;i++) {
        		sects.add((Sector)MatrioskaLoad(galaxy, obj12.getSectorIDs().get(i)));
        		sects.get(i).setMyGalaxy(obj12);
        	}
        	obj12.setMySectors(sects);
        	break;
        case Colony.CLASSINDEX:
        	Colony obj13 = (Colony)s;
//        	obj13.setMyEcosystem((Ecosystem)MatrioskaLoad(galaxy, obj13.getEcosystemID()));
        	break;
        case Condition.CLASSINDEX:
        	break;
        }     
		return s;
	}
	
	public static void MatrioskaSave(String galaxy,Savable save) {
		WriteSavabletoFile(save, galaxy);
		switch(save.getClassIndex()) {
        case Terrestrial.CLASSINDEX:
        	Terrestrial Ter = (Terrestrial)save;
        	for(int i = 0;i < Ter.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Ter.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Ter.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Ter.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, Ter.getMyColony());
        	break;
        case Moon.CLASSINDEX:
        	Moon Moo = (Moon)save;
        	for(int i = 0;i < Moo.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Moo.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Moo.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Moo.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, Moo.getMyColony());
        	break;
        case Jovian.CLASSINDEX:
        	Jovian Jov = (Jovian)save;
        	for(int i = 0;i < Jov.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Jov.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Jov.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Jov.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, Jov.getMyColony());
        	break;
        case HabitableMoon.CLASSINDEX:
        	HabitableMoon HabMoo = (HabitableMoon)save;
        	for(int i = 0;i < HabMoo.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,HabMoo.getMyMoons().get(i));
        	}
        	for(int i = 0;i < HabMoo.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, HabMoo.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, HabMoo.getMyColony());
        	MatrioskaSave(galaxy, HabMoo.getMyCondition());
        	break;
        case Habitable.CLASSINDEX:
        	Habitable Hab = (Habitable)save;
        	for(int i = 0;i < Hab.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Hab.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Hab.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Hab.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, Hab.getMyColony());
        	MatrioskaSave(galaxy, Hab.getMyCondition());
        	break;
        case Belt.CLASSINDEX:
        	Belt Bel = (Belt)save;
        	for(int i = 0;i < Bel.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Bel.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Bel.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Bel.getMySatilights().get(i));
        	}
        	break;
        case Asteroid.CLASSINDEX:
        	Asteroid Ast = (Asteroid)save;
        	for(int i = 0;i < Ast.getMyMoons().size();i++) {
        		MatrioskaSave(galaxy,Ast.getMyMoons().get(i));
        	}
        	for(int i = 0;i < Ast.getMySatilights().size();i++) {
        		MatrioskaSave(galaxy, Ast.getMySatilights().get(i));
        	}
        	MatrioskaSave(galaxy, Ast.getMyColony());
        	break;
        case SolSystem.CLASSINDEX:
        	SolSystem Sol = (SolSystem)save;
        	MatrioskaSave(galaxy, Sol.getMyStar());
        	for(int i = 0;i < Sol.getMyObjects().size();i++) {
        		MatrioskaSave(galaxy, Sol.getMyObjects().get(i));
        	}
        	break;
        case Zone.CLASSINDEX:
        	Zone Zon = (Zone)save;
        	for(int i = 0;i < Zon.getMySystems().size();i++) {
        		MatrioskaSave(galaxy, Zon.getMySystems().get(i));
        	}
        	break;
        case Region.CLASSINDEX:
        	Region Reg = (Region)save;
        	for(int i = 0;i < Reg.getMyZones().size();i++) {
        		MatrioskaSave(galaxy, Reg.getMyZones().get(i));
        	}
        	break;
        case Sector.CLASSINDEX:
        	Sector Sec = (Sector)save;
        	for(int i = 0;i < Sec.getRegions().size();i++) {
        		MatrioskaSave(galaxy, Sec.getRegions().get(i));
        	}
        	break;
        case Galaxy.CLASSINDEX:
        	Galaxy Gal = (Galaxy)save;
        	for(int i = 0;i < Gal.getMySectors().size();i++) {
        		MatrioskaSave(galaxy, Gal.getMySectors().get(i));
        	}
        	break;
        case Colony.CLASSINDEX:
        	break;
        }        
	}
	
	public static void WriteSavabletoFile(Savable object,String filepath) {

		FileOutputStream fout = null;

		try {
			File tmpDir = new File("data/saves/"+filepath);
			boolean exists = tmpDir.exists();
			if(!exists||!tmpDir.isDirectory()) tmpDir.mkdir();
			fout = new FileOutputStream("data/saves/"+filepath+"/"+object.getID());
			byte[] bs = object.saveString().getBytes();
			fout.write(bs);
			fout.flush();
			fout.close();

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Savable ReadSaveableFromFile(String filepath) {
		 try {
	            FileInputStream fileIn = new FileInputStream("data/saves/"+filepath);
	            Savable obj = null;
	            String read = getFileContent(fileIn);
	            String[] box = StringFundementals.breakByLine(read);
	            switch(Integer.parseInt(box[1])) {
	            case Star.CLASSINDEX:
	            	obj = new Star(read);
	            	break;
	            case WhiteDwarf.CLASSINDEX:
	            	obj = new WhiteDwarf(read);
	            	break;
	            case Neutron.CLASSINDEX:
	            	obj = new Neutron(read);
	            	break;
	            case BrownDwarf.CLASSINDEX:
	            	obj = new BrownDwarf(read);
	            	break;
	            case BlackHole.CLASSINDEX:
	            	obj = new BlackHole(read);
	            	break;
	            case Terrestrial.CLASSINDEX:
	            	obj = new Terrestrial(read);
	            	break;
	            case Moon.CLASSINDEX:
	            	obj = new Moon(read);
	            	break;
	            case Jovian.CLASSINDEX:
	            	obj = new Jovian(read);
	            	break;
	            case HabitableMoon.CLASSINDEX:
	            	obj = new HabitableMoon(read);
	            	break;
	            case Habitable.CLASSINDEX:
	            	obj = new Habitable(read);
	            	break;
	            case Belt.CLASSINDEX:
	            	obj = new Belt(read);
	            	break;
	            case Asteroid.CLASSINDEX:
	            	obj = new Asteroid(read);
	            	break;
	            case SolSystem.CLASSINDEX:
	            	obj = new SolSystem(read);
	            	break;
	            case Zone.CLASSINDEX:
	            	obj = new Zone(read);
	            	break;
	            case Region.CLASSINDEX:
	            	obj = new Region(read);
	            	break;
	            case Sector.CLASSINDEX:
	            	obj = new Sector(read,0.0d);
	            	break;
	            case Galaxy.CLASSINDEX:
	            	obj = new Galaxy(read);
	            	break;
	            case Colony.CLASSINDEX:
	            	obj = new Colony(read);
	            	break;
	            case Condition.CLASSINDEX:
	            	obj = new Condition(read);
	            	break;
	            }        
	            return obj;
	 
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	}
	
	public static String getFileContent(FileInputStream fis) throws IOException {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while(( line = br.readLine()) != null ) {
				sb.append( line );
				sb.append( '\n' );
			}
			return sb.toString();
		}
	}
	
    public static Object ReadObjectFromFile(String filepath) {
 
        try {
            FileInputStream fileIn = new FileInputStream("data/"+filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
 
            Object obj = objectIn.readObject();
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	public static void WriteObjecttoFile(Object object,String filepath) {

		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try {

			fout = new FileOutputStream("data/"+filepath+"."+object.getClass().getName());
			oos = new ObjectOutputStream(fout);
			oos.writeObject(object);

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}