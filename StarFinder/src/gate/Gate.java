package gate;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import engine.Savable;

public interface Gate extends Savable {

	String getMyName();

	ImageIcon getIcon();

	String string();

}
