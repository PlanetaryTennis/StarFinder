package astronomy.stellar;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

public class BlackHole extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4586171846287174531L;

	public BlackHole(String load) {
		super(load);
		this.loadString(load);
	}
	
	public BlackHole(double m, SolSystem s) {
		super(m, s);
		myLuminosity = 0;
		myRadius = SOLMASSBH*m;
		myTemp = SOLTEMP*Math.pow(m, 0.505);
		myGravity = AstroObject.G*(myMass/myRadius);
		myColor = color.BLACK;
		++total;
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Black Hole.png"));
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
		myColor = color.BLACK;
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

	public static final int CLASSINDEX = 934205;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
