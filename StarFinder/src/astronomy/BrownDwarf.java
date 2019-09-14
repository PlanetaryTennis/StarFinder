package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
import units.sci;
import units.siacceleration;
import units.sibrightness;
import units.sidensity;
import units.sivolume;

public class BrownDwarf extends Star {

	public BrownDwarf(double m, SolSystem s) {
		super(m, s);
		myMass = AstroObject.JOVIAN.scale(m*13);
		myLuminosity = new sibrightness(0);
		myRadius = AstroObject.JOVIANRADI.scale(Math.pow(m, 0.74));
		myTemp = AstroObject.SOLTEMP.scale(Math.pow(m, 0.505));
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
		return new BrownDwarf(ran.nextDouble()*5+1,s);
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Brown Dwarf.png"));
	}

}
