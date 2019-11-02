package astronomy.stellar;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import astronomy.SolSystem;
import map.Sprite;
import map.color;
import utilities.StringFundementals;

public class Nebula extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = -586368306431126608L;

	public Nebula(SolSystem s) {
		super(s);
		myColor = randomColor();
	}
	
	public Nebula(String load) {
		this.loadString(load);
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
	
	public ImageIcon getIcon() {
		BufferedImage out = null;
		try {
			out = ImageIO.read(new File(Sprite.STARS+"Nebula.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Color c = this.myColor.getMyColor();
		for (int x = 0; x < out.getWidth(); x++) {
	        for (int y = 0; y < out.getHeight(); y++) {
	            Color pixelColor = new Color(out.getRGB(x, y), true);
	            int r = (pixelColor.getRed() + pixelColor.getRed() + c.getRed()) / 3;
	            int g = (pixelColor.getGreen() + pixelColor.getGreen() + c.getGreen()) / 3;
	            int b = (pixelColor.getBlue() + pixelColor.getBlue() + c.getBlue()) / 3;
	            int a = pixelColor.getAlpha();
	            int rgba = (a << 24) | (r << 16) | (g << 8) | b;
	            out.setRGB(x, y, rgba);
	        }
	    }
		return new ImageIcon(out);
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
