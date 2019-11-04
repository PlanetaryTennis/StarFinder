package astronomy.stellar;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

/**
 * MultiStar generates several stars.
 * 
 * @author PlanetaryTennis
 */
public class MultiStar extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5289878534657099658L;
	private Vector<Star> myStars = new Vector<Star>();
	private double distance;

	MultiStar(Vector<Star> stars, int Distance) {
		myStars = stars;
	}

	MultiStar(String load) {
		this.loadString(load);
	}

	@Override
	public double getMyMass() {
		double mass = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			mass += myStars.get(i).getMyMass();
		}
		return mass;
	}

	@Override
	public double getMyRadius() {
		double radius = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			if (radius < myStars.get(i).getMyRadius())
				radius = myStars.get(i).getMyRadius();
		}
		return radius + distance;
	}

	@Override
	public double getMyDensity() {
		double density = 0.0d;
		for(int i = 0;i < myStars.size();i++) {
			density += myStars.get(i).getMyDensity();
		}
		return density/myStars.size();
	}

	@Override
	public double getMyTemp() {
		double temp = 0.0d;
		for(int i = 0;i < myStars.size();i++) {
			temp += myStars.get(i).getMyTemp();
		}
		return temp/myStars.size();
	}

	@Override
	public double getMyVolume() {
		double volume = 0.0d;
		for(int i = 0;i < myStars.size();i++) {
			volume += myStars.get(i).getMyVolume();
		}
		return volume;
	}

	@Override
	public double getMyLuminosity() {
		double luminosity = 0.0d;
		for(int i = 0;i < myStars.size();i++) {
			luminosity += myStars.get(i).getMyMass();
		}
		return luminosity;
	}

	@Override
	public double getMyGravity() {
		return myGravity;
	}

	@Override
	public color getMyColor() {
		return myColor;
	}

	@Override
	public void setMyColor(color myColor) {
		this.myColor = myColor;
	}

	@Override
	public String printColor() {
		String out = "";
		return out;
	}

	public static MultiStar randomMultiStar(SolSystem s, int[] suns) {
		Random ran = new Random();
		return null;
	}

	@Override
	public double[] getHabitablezone() {
		double[] out = new double[2];
		double lum = myLuminosity;
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		out[0] = innerhab * factor * AstroObject.AU;
		out[1] = outerhab * factor * AstroObject.AU;
		return out;
	}

	@Override
	public double getFrostLine() {
		double lum = myLuminosity;
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		return frost * factor * AstroObject.AU;
	}

	@Override
	public ImageIcon getIcon() {
		switch (getMyColor()) {
		case BLUE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Blue Star.png"));
		case ORANGE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Orange Star.png"));
		case WHITE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "White Star.png"));
		case YELLOW:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Yellow Star.png"));
		case RED:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Red Star.png"));
		default:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Cyan Star.png"));
		}
	}

	@Override
	public int loadString(String load) {
		String[] in = StringFundementals.breakByLine(load);
		int i = 1;
		this.myID = in[0];
		this.myName = in[i++];
		i++;
		this.myDensity = Double.parseDouble(in[i++]);
		this.myGravity = Double.parseDouble(in[i++]);
		this.myLuminosity = Double.parseDouble(in[i++]);
		this.myMass = Double.parseDouble(in[i++]);
		this.myRadius = Double.parseDouble(in[i++]);
		this.myTemp = Double.parseDouble(in[i++]);
		this.myVolume = Double.parseDouble(in[i++]);
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += this.myID + "\n";
		out += this.myName + "\n";
		out += this.getClassIndex() + "\n";
		out += this.getMyDensity() + "\n";
		out += this.getMyGravity() + "\n";
		out += this.getMyLuminosity() + "\n";
		out += this.getMyMass() + "\n";
		out += this.getMyRadius() + "\n";
		out += this.getMyTemp() + "\n";
		out += this.getMyVolume() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 934261;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	@Override
	public void setMyName(String name) {
		myName = name;
	}
}
