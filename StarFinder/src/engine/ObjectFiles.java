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
//		String HOPE = "A. This is a test\n{\nsub block\n}\nShouldn't be sub.\n{\nAnother Sub\n{\nDeeper sub should be seperated.\n}\n}\nEnd of this nonsense.\n";
//		System.out.println(HOPE);
//		Vector<String> test = StringFundementals.unnestString('{', '}', HOPE);
//		System.out.println(test.toString());
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
        }     
		return s;
	}
	
	public static void MatrioskaSave(String galaxy,Savable save) {
		WriteSavabletoFile(save, galaxy);
		switch(save.getClassIndex()) {
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