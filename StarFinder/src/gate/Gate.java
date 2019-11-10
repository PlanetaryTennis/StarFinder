package gate;

import javax.swing.ImageIcon;

import engine.Namable;
import engine.Savable;

public interface Gate extends Savable, Namable {

	String getMyName();

	ImageIcon getIcon();

	String string();

}
