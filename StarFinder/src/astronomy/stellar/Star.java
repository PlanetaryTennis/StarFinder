package astronomy.stellar;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import engine.Namable;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

public class Star implements AstroObject, Namable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3744832937120819479L;

	public static int total = 0;

	protected String myName;
	protected double myMass;
	protected double myRadius;
	protected double myDensity;
	protected double myTemp;
	protected double myVolume;
	protected double myLuminosity;
	protected double myGravity;

	protected color myColor;

	private SolSystem mySystem;

	public Star(String load) {
		this.loadString(load);
	}

	public Star(SolSystem s) {
		mySystem = s;
		myID = UUID.randomUUID().toString() + ".Star";
	}

	public Star() {
		myID = UUID.randomUUID().toString() + ".Star";
	}

	public Star(double m, SolSystem s) {
		mySystem = s;
		myMass = SOL * m;
		myLuminosity = SUNLIGHT * (Math.pow(m, 3));
		myRadius = SOLRADI * (Math.pow(m, 0.74));
		myTemp = SOLTEMP * (Math.pow(m, 0.505));
		myGravity = AstroObject.G * myMass / myRadius;
		myVolume = Math.pow(myRadius, 3) * (0.75 * Math.PI) * 1000;
		myDensity = myMass / myVolume;
		myColor = StellarClass(myTemp);
		myID = UUID.randomUUID().toString() + ".Star";
	}

	private color StellarClass(Double t) {
		if (t < 3500) {
			return color.RED;
		} else if (t < 5000) {
			return color.ORANGE;
		} else if (t < 6000) {
			return color.YELLOW;
		} else if (t < 7500) {
			return color.WHITE;
		} else if (t < 11000) {
			return color.CYAN;
		} else {
			return color.BLUE;
		}
	}

	public double getMyMass() {
		return myMass;
	}

	public double getMyRadius() {
		return myRadius;
	}

	public double getMyDensity() {
		return myDensity;
	}

	public double getMyTemp() {
		return myTemp;
	}

	public double getMyVolume() {
		return myVolume;
	}

	public double getMyLuminosity() {
		return myLuminosity;
	}

	public double getMyGravity() {
		return myGravity;
	}

	public color getMyColor() {
		return myColor;
	}

	public void setMyColor(color myColor) {
		this.myColor = myColor;
	}

	public String printColor() {
		switch (myColor) {
		case BLUE:
			return "Blue";
		case GREEN:
			return "Green";
		case ORANGE:
			return "Orange";
		case WHITE:
			return "White";
		case YELLOW:
			return "Yellow";
		case RED:
			return "Red";
		case CYAN:
			return "Cyan";
		default:
			break;
		}
		return "";
	}

	public SolSystem getMySystem() {
		return mySystem;
	}

	public static Star randomStar(SolSystem s, int[] suns) {
		Random ran = new Random();
		int die = ran.nextInt(suns[8]);
		if (die < suns[6]) {
			return new Star(ran.nextDouble() + 0.01, s);
		} else if (die < suns[7]) {
			return new Star(ran.nextDouble() * 3 + 1, s);
		} else {
			return new Star((ran.nextDouble() * 3 + 1) * 4, s);
		}
	}

	static double innerhab = 0.95;
	static double outerhab = 1.35;
	static double frost = 4.85;

	public double[] getHabitablezone() {
		double[] out = new double[2];
		double lum = myLuminosity;
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		out[0] = innerhab * factor * AstroObject.AU;
		out[1] = outerhab * factor * AstroObject.AU;
		return out;
	}

	public double getFrostLine() {
		double lum = myLuminosity;
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		return frost * factor * AstroObject.AU;
	}

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
		myColor = StellarClass(myTemp);
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

	public static final int CLASSINDEX = 934201;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String myID;

	@Override
	public String getID() {
		return myID;
	}

	public void setMySystem(SolSystem solsystem) {
		mySystem = solsystem;
	}

	public void setMyName(String name) {
		myName = name;
	}

	public String getMyName() {
		return myName;
	}
}
