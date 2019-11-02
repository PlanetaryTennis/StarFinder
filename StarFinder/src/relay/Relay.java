package relay;

import javax.swing.ImageIcon;

import astronomy.OrbitObject;
import engine.Savable;

public interface Relay extends Savable {

	String getMyName();

	ImageIcon getIcon();

	String string();

}
