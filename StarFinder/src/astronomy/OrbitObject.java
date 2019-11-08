package astronomy;

import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.planetary.Asteroid;
import astronomy.planetary.HabitableMoon;
import astronomy.planetary.Moon;
import gate.ImageGate;
import utilities.StringFundementals;

public interface OrbitObject extends AstroObject {

	String string();

	String getMyName();

	public abstract ImageIcon getIcon();

	public static OrbitObject parseLoad(String string) {
		OrbitObject obj = null;
		Vector<String> box = StringFundementals.breakByLine(string);
		switch (Integer.parseInt(box.get(1))) {
		case Moon.CLASSINDEX:
			obj = new Moon(string);
			break;
		case HabitableMoon.CLASSINDEX:
			obj = new HabitableMoon(string);
			break;
		case Asteroid.CLASSINDEX:
			obj = new Asteroid(string);
			break;
		case ImageGate.CLASSINDEX:
			obj = new ImageGate(string);
			break;
		}
		return obj;
	}
}