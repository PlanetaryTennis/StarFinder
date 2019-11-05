package astronomy.stellar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import map.SettingList;
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
	private int NumberStars;

	public MultiStar(SolSystem s, Vector<Star> stars, double Distance) {
		myStars = stars;
		distance = Distance;
		setMySystem(s);
		myID = UUID.randomUUID().toString() + ".Star";
	}

	public MultiStar(String load) {
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
		for (int i = 0; i < myStars.size(); i++) {
			density += myStars.get(i).getMyDensity();
		}
		return density / myStars.size();
	}

	@Override
	public double getMyTemp() {
		double temp = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			temp += myStars.get(i).getMyTemp();
		}
		return temp / myStars.size();
	}

	@Override
	public double getMyVolume() {
		double volume = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			volume += myStars.get(i).getMyVolume();
		}
		return volume;
	}

	@Override
	public double getMyLuminosity() {
		double luminosity = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			luminosity += myStars.get(i).getMyLuminosity();
		}
		return luminosity;
	}

	@Override
	public double getMyGravity() {
		double gravity = 0.0d;
		for (int i = 0; i < myStars.size(); i++) {
			gravity += myStars.get(i).getMyGravity();
		}
		return gravity / myStars.size();
	}

	@Override
	public color getMyColor() {
		return myStars.get(0).getMyColor();
	}

	@Override
	public String printColor() {
		String out = "";
		for (int i = 0; i < myStars.size(); i++) {
			out += myStars.get(i).printColor() + "\n";
		}
		return out;
	}

	public static MultiStar randomMultiStar(SolSystem s, SettingList SL) {
		Random ran = new Random();
		Vector<Star> out = new Vector<Star>();
		double d = AstroObject.AU / 10;
		int num = 2;
		int special;
		Star star = null;
		boolean dont = true;
		for (int i = 0; i < num; i++) {
			dont = true;
			special = ran.nextInt(SL.getSuns()[5] + SL.getSuns()[9]);
			if (SL.getSuns() == null || special <= SL.getSuns()[0] || !SL.isSpecial()) {
				star = Star.randomStar(s, SL.getSuns());
			} else if (special <= SL.getSuns()[1]) {
				star = BrownDwarf.randomStar(s);
			} else if (special <= SL.getSuns()[2]) {
				star = WhiteDwarf.randomStar(s);
			} else if (special <= SL.getSuns()[3]) {
				star = Neutron.randomStar(s);
			} else if (special <= SL.getSuns()[4]) {
				star = new BlackHole(ran.nextInt(100) + 13, s);
			} else if (special <= SL.getSuns()[5]) {
				star = Nebula.randomStar(s);
			} else {
				num++;
				i--;
				dont = false;
			}
			if (dont)
				out.add(star);
			if (i >= 3)
				break;
		}
		return new MultiStar(s, out, d);
	}

	@Override
	public double[] getHabitablezone() {
		double[] out = new double[2];
		double lum = getMyLuminosity();
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		out[0] = innerhab * factor * AstroObject.AU;
		out[1] = outerhab * factor * AstroObject.AU;
		return out;
	}

	@Override
	public double getFrostLine() {
		double lum = getMyLuminosity();
		double solar = AstroObject.SUNLIGHT;
		double factor = Math.sqrt(lum / solar);
		return frost * factor * AstroObject.AU;
	}

	@Override
	public ImageIcon getIcon() {
		BufferedImage out = null;
		try {
			out = ImageIO.read(new File(Sprite.STARS + "MultiStarBox.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon icon;
		BufferedImage bi;
		Graphics gr;
		int disx, disy, r, g, b, a, rgba;
		switch (myStars.size()) {
		case 2:
			icon = myStars.get(0).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2;
					disy = (128 - bi.getHeight()) / 2;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(1).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2 + 128;
					disy = (128 - bi.getHeight()) / 2 + 128;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			break;
//###########################################################################################################################
		case 3:
			icon = myStars.get(0).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2;
					disy = (128 - bi.getHeight()) / 2;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(1).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2 + 128;
					disy = (128 - bi.getHeight()) / 2;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(2).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2 + 64;
					disy = (128 - bi.getHeight()) / 2 + 128;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			break;
//###########################################################################################################################
		case 4:
			icon = myStars.get(0).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2;
					disy = (128 - bi.getHeight()) / 2;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(1).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2 + 128;
					disy = (128 - bi.getHeight()) / 2;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(2).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2;
					disy = (128 - bi.getHeight()) / 2 + 128;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			icon = myStars.get(3).getIcon();
			bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			gr = bi.createGraphics();
			icon.paintIcon(null, gr, 0, 0);
			gr.dispose();
			for (int x = 0; x < bi.getWidth(); x++) {
				for (int y = 0; y < bi.getHeight(); y++) {
					disx = (128 - bi.getWidth()) / 2 + 128;
					disy = (128 - bi.getHeight()) / 2 + 128;
					Color pixelColor = new Color(out.getRGB(x + disx, y + disy), true);
					Color c = new Color(bi.getRGB(x, y), true);
					r = (pixelColor.getRed() + c.getRed());
					g = (pixelColor.getGreen() + c.getGreen());
					b = (pixelColor.getBlue() + c.getBlue());
					a = Math.max(pixelColor.getAlpha(), c.getAlpha());
					rgba = (a << 24) | (r << 16) | (g << 8) | b;
					out.setRGB(x + disx, y + disy, rgba);
				}
			}
			break;
		}
		return new ImageIcon(out);
	}

	@Override
	public int loadString(String load) {
		Vector<String> parse = StringFundementals.unnestString('{', '}', load);
		String[] in = StringFundementals.breakByLine(parse.get(0));
		int i = 1;
		this.myID = in[0];
		this.myName = in[i++];
		i++;
		NumberStars = Integer.parseInt(in[i++]);
		for (int k = 0; k < NumberStars; k++) {
			myStars.add(SolSystem.StarParse(parse.get(1 + k)));
		}
		return i;
	}

	@Override
	public String saveString() {
		String out = "";
		out += this.myID + "\n";
		out += this.myName + "\n";
		out += this.getClassIndex() + "\n";
		out += this.getMyStars().size() + "\n";
		for (int i = 0; i < myStars.size(); i++) {
			out += "{\n";
			out += myStars.get(i).saveString() + "\n";
			out += "}\n";
		}
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
		for (int i = 0; i < myStars.size(); i++) {
			myStars.get(i).setMyName(name + "-" + (char) ('A' + i));
		}
		myName = name;
	}

	public Vector<Star> getMyStars() {
		return myStars;
	}
}
