package astronomy;

import javax.swing.ImageIcon;

public interface OrbitObject extends AstroObject {

	String string();

	String getMyName();
	
	public abstract ImageIcon getIcon();
}
