package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
import units.*;

public class Star implements AstroObject {
	
	public static int total = 0;
	
	protected simass myMass;
	protected sidistance myRadius;
	protected sidensity myDensity;
	protected sitemperature myTemp;
	protected sivolume myVolume;
	protected sibrightness myLuminosity;
	protected siacceleration myGravity;
	
	protected color myColor;
	
	private SolSystem mySystem;
	
	
	public Star(double m,SolSystem s){
		mySystem = s;
		myMass = AstroObject.SOL.scale(m);
		myLuminosity = AstroObject.SUNLIGHT.scale(Math.pow(m,3));
		myRadius = AstroObject.SOLRADI.scale(Math.pow(m, 0.74));
		myTemp = AstroObject.SOLTEMP.scale(Math.pow(m, 0.505));
		myGravity = new siacceleration(
				sci.convertToDouble(AstroObject.G)*
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myRadius.getValue()));
		myVolume = new sivolume(
				Math.pow(sci.convertToDouble(myRadius.getValue()),3)*
				(0.75*Math.PI)*1000);
		myDensity = new sidensity(
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myVolume.getValue()));
		myColor = StellarClass(sci.convertToDouble(myTemp.getValue()));
		++total;
	}

	private color StellarClass(Double t) {
		if(t < 3500) {
			return color.RED;
		}else if(t < 5000) {
			return color.ORANGE;
		}else if(t < 6000) {
			return color.YELLOW;
		}else if(t < 7500) {
			return color.WHITE;
		}else if(t < 11000){
			return color.CYAN;
		}else {
			return color.BLUE;
		}
	}

	public simass getMyMass() {
		return myMass;
	}
	public sidistance getMyRadius() {
		return myRadius;
	}

	public sidensity getMyDensity() {
		return myDensity;
	}

	public sitemperature getMyTemp() {
		return myTemp;
	}

	public sivolume getMyVolume() {
		return myVolume;
	}

	public sibrightness getMyLuminosity() {
		return myLuminosity;
	}
	public siacceleration getMyGravity() {
		return myGravity;
	}

	public color getMyColor() {
		return myColor;
	}

	public void setMyColor(color myColor) {
		this.myColor = myColor;
	}

	public String printColor() {
		switch(myColor) {
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
		int die = ran.nextInt(99)+1;
		if(die < 75) {
			return new Star(sci.round(ran.nextDouble()+0.01, 3),s);
		}else if(die < 90) {
			return new Star(sci.round(ran.nextDouble()*3+1, 3),s);
		}else {
			return new Star(sci.round((ran.nextDouble()*3+1)*4, 3),s);
		}
	}

	static double innerhab = 0.95;
	static double outerhab = 1.35;
	static double frost = 4.85;
	
	public sidistance[] getHabitablezone() {
		sidistance[] out = new sidistance[2];
		double lum = sci.convertToDouble(myLuminosity.getValue());
		double solar = sci.convertToDouble(AstroObject.SUNLIGHT.getValue());
		double factor = Math.sqrt(lum/solar);
		out[0] = new sidistance(innerhab*factor*sci.convertToDouble(AstroObject.AU.getValue()));
		out[1] = new sidistance(outerhab*factor*sci.convertToDouble(AstroObject.AU.getValue()));
		return out;
	}

	public sidistance getFrostLine() {
		double lum = sci.convertToDouble(myLuminosity.getValue());
		double solar = sci.convertToDouble(AstroObject.SUNLIGHT.getValue());
		double factor = Math.sqrt(lum/solar);
		return new sidistance(frost*factor*sci.convertToDouble(AstroObject.AU.getValue()));
	}

	public ImageIcon getIcon() {
		ImageIcon Cody = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Yellow Star.png"));
		double scale = (getMyRadius().compair(SOLRADI)+0.1)*4;
		switch(getMyColor()) {
		case BLUE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Blue Star.png"));
		case ORANGE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Orange Star.png"));
		case WHITE:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"White Star.png"));
		case YELLOW:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Yellow Star.png"));
		case RED:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Red Star.png"));
		default:
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Cyan Star.png"));
		}
	}

	@Override
	public void loadString(String load) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClassIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
}
