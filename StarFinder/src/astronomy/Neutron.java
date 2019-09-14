package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
import units.SI;
import units.sci;
import units.sidensity;
import units.sidistance;
import units.sitemperature;
import units.sivolume;

public class Neutron extends Star {
	
	public Neutron(double m, SolSystem s) {
		super(m,s);
		myRadius = new sidistance(20,SI.KILO).scale(Math.pow(m,1));
		myTemp = new sitemperature(600,SI.KILO);
		myVolume = new sivolume(
				Math.pow(sci.convertToDouble(myRadius.getValue()),3)*
				(0.75*Math.PI)*1000);
		myDensity = new sidensity(
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myVolume.getValue()));
		myColor = color.WHITE;
	}
	
	static Random ran = new Random(System.currentTimeMillis());
	
	public static Star randomStar(SolSystem s) {
		return new Neutron(ran.nextDouble()*50+1.4,s);
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Neutron Star.png"));
	}
}
