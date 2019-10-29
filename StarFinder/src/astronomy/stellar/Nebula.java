package astronomy.stellar;

import java.util.Random;

import astronomy.SolSystem;
import map.color;
import utilities.StringFundementals;

public class Nebula extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = -586368306431126608L;

	public Nebula(SolSystem s) {
		super(0, s);
		myColor = randomColor();
	}
	
	private color randomColor() {
		color c;
		switch(ran.nextInt(5)) {
		case 0:
			c = color.BLUE;
			break;
		case 1:
			c = color.CYAN;
			break;
		case 2:
			c = color.ORANGE;
			break;
		case 3:
			c = color.RED;
			break;
		default:
			c = color.WHITE;
			break;
		}
		return c;
	}

	private Random ran = new Random(System.currentTimeMillis());
	
	public static Star randomStar(SolSystem s) {
		return new Nebula(s);
	}

	@Override
	public int loadString(String load) {
		String [] in = StringFundementals.breakByLine(load);
		int i = 2;
		this.myID = in[0];
		this.myName = in[i++];
		myColor = color.valueOf(in[i++]);
		return i;		
	}

	@Override
	public String saveString() {
		String out = "";
		out += this.myID + "\n";
		out += this.myName + "\n";
		out += this.getClassIndex() + "\n";
		out += this.getMyColor() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 934275;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}
}
