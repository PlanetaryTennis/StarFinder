package astronomy;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import map.Sprite;
import map.color;
import units.sci;
import units.siacceleration;
import units.sidensity;
import units.sidistance;
import units.sivolume;

public class BlackHole extends Star {

	public BlackHole(double m, SolSystem s) {
		super(m, s);
		myLuminosity = AstroObject.SUNLIGHT.scale(0);
		myRadius = AstroObject.SOLMASSBH.scale(m);
		myTemp = AstroObject.SOLTEMP.scale(Math.pow(m, 0.505));
		myGravity = new siacceleration(
				sci.convertToDouble(AstroObject.G)*
				sci.convertToDouble(myMass.getValue())/
				sci.convertToDouble(myRadius.getValue()));
		myColor = color.BLACK;
		++total;
	}

	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.STARS+"Black Hole.png"));
	}
}
