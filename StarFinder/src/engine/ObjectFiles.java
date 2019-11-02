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

import astronomy.AstroObject;
import astronomy.Galaxy;
import astronomy.LifeBearing;
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
import map.SettingList;
import planetary.Colony;
import planetary.Condition;
import relay.RelayNetwork;
import utilities.StringFundementals;

public class ObjectFiles {
	
	public static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		Star s = new Star(1,null);
//		Planet p = Terrestrial.makeRandom(AstroObject.AU, s, new SettingList("TEST", 0, 0, 0, 0, 0, 0, 0, 0, false, false, true, new int[] {75,90,100}));
//		System.out.println(p.getMyColony().calculateMaxSize(p));
		
//		SolSystem s;
//		Habitable h;
//		while(true) {
//			s = SolSystem.makeRandom(null, new SettingList("TEST", 0, 0, 0, 0, 0, 0, 0, 12, false, false, true, new int[] {80,90,100,100}));
//			for(int i = 0;i < s.getMyObjects().size();i++)
//				if(s.getMyObjects().get(i).getClass()==Habitable.class) {
//					h = (Habitable) s.getMyObjects().get(i);
//					System.out.println(h.saveString());
//					in.nextLine();
//				}
//		}
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
	
	public static boolean CheckFile(String filepath) {
		File tempFile = new File(filepath);
		return tempFile.exists();
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
	            case RelayNetwork.CLASSINDEX:
	            	obj = new RelayNetwork(read);
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