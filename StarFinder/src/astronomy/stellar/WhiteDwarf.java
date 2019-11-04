package astronomy.stellar;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

public class WhiteDwarf extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8293543740786396101L;

	public WhiteDwarf(String load) {
		super(load);
		this.loadString(load);
	}
	
	public WhiteDwarf(double m, SolSystem s) {
		super(m,s);
		myRadius = AstroObject.EARTHRADI*(Math.pow(m,1));
		myTemp = AstroObject.CORETEMP*(Math.pow(m, 1));
		myVolume = Math.pow(myRadius,3)*((0.75*Math.PI)*1000);
		myDensity = myMass/myVolume;
		myColor = color.WHITE;
	}
	
	static Random ran = new Random(System.currentTimeMillis());
	
	public static Star randomStar(SolSystem s) {
		return new WhiteDwarf(ran.nextDouble()*8+0.5,s);
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"White Dwarf.png"));
	}
	


	@Override
	public int loadString(String load) {
		String [] in = StringFundementals.breakByLine(load);
		int i = 2;
		this.myID = in[0];
		this.myName = in[i++];
		this.myDensity = Double.parseDouble(in[i++]);
		this.myGravity = Double.parseDouble(in[i++]);
		this.myLuminosity = Double.parseDouble(in[i++]);
		this.myMass = Double.parseDouble(in[i++]);
		this.myRadius = Double.parseDouble(in[i++]);
		this.myTemp = Double.parseDouble(in[i++]);
		this.myVolume = Double.parseDouble(in[i++]);
		myColor = color.WHITE;
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

	public static final int CLASSINDEX = 934202;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
