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
import map.SettingList;
import planetary.Colony;
import planetary.Condition;
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
//		GameLauncher l = new GameLauncher();
//		l.getClass();
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			Star s = Star.randomStar(null, null);
//			SolSystem ss = SolSystem.makeRandom(null, new SettingList(0, 0, 0, 0, 0, 0, 0, 4, true, true, true, null));
//			for(int i = 0;i < ss.getMyObjects().length;i++) {
//				if(ss.getMyObjects()[i].getClass()==Habitable.class) {
//					Planet planet = ss.getMyObjects()[i];
//					Colony colony = planet.getMyColony();
//					String display = planet.getMyName() + "\n";
//					display += "Habitable: " + colony.isHab() + "\n";
//					display += "Water Present: " + colony.isHasWater() + "\n";
//					display += "Biosphere Present: " + colony.isHasBio() + "\n";
//					display += "Ezero Present: " + colony.isHasEzo() + "\n";
//					display += "Massive Metal Presence: " + colony.isHasMassiveMetal() + "\n";
//					display += "Massive Gasses Presence: " + colony.isHasMassiveGasses() + "\n";
//					display += "Radiotropics Present: " + colony.isHasRadiotropes() + "\n";
//					display += "Rare Metals Present: " + colony.isHasRareMetals() + "\n";
//					display += "Rare Gasses Present: " + colony.isHasRareGasses() + "\n";
//					display += "\n";
//					display += "Has a colony " + (colony.getSize()>0) + "\n";
//					if(colony.getSize()>0) {
//						display += "Colony Size: " + colony.getSize() + "\n";
//						display += "Colony Growth: " + colony.getScale()  + "\n";
//					}
//					
//					Condition c = null;
//					if(planet.getClass()==Habitable.class) {
//						c = ((Habitable)planet).getMyCondition();
//					}else if(planet.getClass()==HabitableMoon.class) {
//						c = ((HabitableMoon)planet).getMyCondition();
//					}
//					
//					if(c!=null) {
//						display += "------------------------------\n";
//						display += "Gravity Index "+c.getGravityIndex()+"\n";
//						display += "Tempiture Index "+c.getTempitureIndex()+"\n";
//						display += "Tempiture Variations "+c.getVarianceIndex()+"\n";
//						display += "Pressure Index "+c.getAtmosphericIndex()+"\n";
//						display += "Water Index "+c.getWaterIndex()+"\n";
//						display += "Atmosphere Type ";
//						switch(c.getAirIndex()) {
//						case AMMONIA:
//							display += "Ammonia";
//							break;
//						case METHANE:
//							display += "Methane";
//							break;
//						default:
//							display += "Nitrogen";
//							break;
//						}
//						display += "\n";
//						display += c.isDextros() ? "Dextro":"Levo";
//						display += "-amino acid Biology";
//						System.out.print(display);
//						sc.nextLine();
//					}
//				}
//			}
//		}
		String uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
		uniqueID = UUID.randomUUID().toString();
		System.out.println(uniqueID);
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