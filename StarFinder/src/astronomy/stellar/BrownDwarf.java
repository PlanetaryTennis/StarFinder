package astronomy.stellar;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import astronomy.SolSystem;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

public class BrownDwarf extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113436841025380756L;

	public BrownDwarf(double m, SolSystem s) {
		super(m, s);
		myMass = JOVIAN*(m*13);
		myLuminosity = 0;
		myRadius = JOVIANRADI*Math.pow(m, 0.74);
		myTemp = SOLTEMP*Math.pow(m, 0.505);
		myVolume = 	Math.pow(myRadius,3)*(0.75*Math.PI)*1000;
		myDensity = myMass/myVolume;
		myColor = color.ORANGE;
	}
	
	public BrownDwarf(String read) {
		super(read);
		this.loadString(read);
	}

	static Random ran = new Random(System.currentTimeMillis());
	
	public static Star randomStar(SolSystem s) {
		return new BrownDwarf(ran.nextDouble()*5+1,s);
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Brown Dwarf.png"));
	}



	@Override
	public void loadString(String load) {
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
		myColor = color.ORANGE;		
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

	public static final int CLASSINDEX = 934204;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
