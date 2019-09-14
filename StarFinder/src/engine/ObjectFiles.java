package engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;

import astronomy.AstroObject;
import astronomy.BlackHole;
import astronomy.Habitable;
import astronomy.HabitableMoon;
import astronomy.Jovian;
import astronomy.Moon;
import astronomy.Planet;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Star;
import astronomy.Terrestrial;
import map.MapView;
import relay.RelayNetwork;
import units.SI;
import units.sci;
import units.sidensity;
import units.sidistance;
import units.sitemperature;
import units.sitime;
import utilities.ARRAY;
import utilities.ExtendedMathmatics;

public class ObjectFiles {
	
	public static final Random random = new Random(System.currentTimeMillis());

	public static void main(String[] args) {
		GameLauncher l = new GameLauncher();
//		System.out.println(b.getMyRadius());
//		int num = 20;
//		int number = 0;
//		int tempnum = 0;
//		int[][] map;
//			number = (int) ((num*1-1)*2);
//			tempnum = (number/2);
//			if(tempnum > ExtendedMathmatics.choose(num, 2)) tempnum = ExtendedMathmatics.choose(num, 2);
//			number = tempnum * 2;
//			System.out.println(tempnum + ":" + number);
//			map = RelayNetwork.mapOut(tempnum,num);
//		System.out.println(ARRAY.print(map,",","\n"));
//		int[] depth = RelayNetwork.depthcluster(map, num);
//		System.out.println(ARRAY.print(true,depth,"\n"));
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