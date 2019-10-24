package engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;

import astronomy.AstroObject;
import astronomy.Star;
import astronomy.old.BlackHole;
import astronomy.old.Habitable;
import astronomy.old.HabitableMoon;
import astronomy.old.Jovian;
import astronomy.old.Moon;
import astronomy.old.Planet;
import astronomy.old.Sector;
import astronomy.old.SolSystem;
import astronomy.old.Terrestrial;
import map.MapView;
import map.SettingList;
import planetary.Colony;
import planetary.Condition;
import relay.RelayNetwork;
import utilities.ARRAY;
import utilities.ExtendedMathmatics;

public class ObjectFiles {
	
	public static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Star s = new Star(10,null);
		System.out.print(s.saveString());
		s.loadString("d70a7ea2-40c6-4c03-9bde-0ad7f917e167\n"+
"934201\n"+
"2.509182465996696E9\n"+
"1.9086964485981308E15\n"+
"2.25E21\n"+
"1.989E30\n"+
"695500.0\n"+
"5778.0\n"+
"7.926884660458244E20");
		System.out.println();
		System.out.print(s.saveString());
//		GameLauncher l = new GameLauncher();
//		l.getClass();
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