package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
import units.sci;
import units.sibrightness;
import units.sidensity;
import units.sivolume;

public class WhiteDwarf extends Star {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8293543740786396101L;

	public WhiteDwarf(double m, SolSystem s) {
		super(m,s);
		myRadius = AstroObject.EARTHRADI.scale(Math.pow(m,1));
		myTemp = AstroObject.CORETEMP.scale(Math.pow(m, 1));
		myVolume = new sivolume(
				Math.pow(sci.convertToDouble(myRadius.getValue()),3)*
				(0.75*Math.PI)*1000);
		myDensity = new sidensity(
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myVolume.getValue()));
		myColor = color.ORANGE;
	}
	
	static Random ran = new Random(System.currentTimeMillis());
	
	public static Star randomStar(SolSystem s) {
		return new BrownDwarf(ran.nextDouble()*8+0.5,s);
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"White Dwarf.png"));
	}
}
