package relay;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.SolSystem;
import astronomy.planetary.Planet;
import map.Sprite;

public class PrimaryRelay implements Relay {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1650990345049687346L;
	
	PrimaryRelay myPartner;
	RelayNetwork myNetwork;
	private SolSystem mySystem;
	private Planet myWorld;
	
	
	public SolSystem getMySystem() {
		return mySystem;
	}

	public void setMySystem(SolSystem mySystem) {
		this.mySystem = mySystem;
	}

	public Planet getMyWorld() {
		return myWorld;
	}

	public void setMyWorld(Planet myWorld) {
		this.myWorld = myWorld;
	}

	public PrimaryRelay getMyPartner() {
		return myPartner;
	}

	public void setMyPartner(PrimaryRelay myPartner) {
		this.myPartner = myPartner;
	}

	public RelayNetwork getMyNetwork() {
		return myNetwork;
	}

	public void setMyNetwork(RelayNetwork myNetwork) {
		this.myNetwork = myNetwork;
	}

	@Override
	public String string() {
		return getMyName();
	}

	@Override
	public String getMyName() {
		return mySystem.getMyZone().getMyRegion().getName() + " of " + mySystem.getMyZone().getMyRegion().getMySector().getName() +
				" to " +
				myPartner.getMySystem().getMyZone().getMyRegion().getName() + " of " + myPartner.getMySystem().getMyZone().getMyRegion().getMySector().getName() +
				"Relay";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.RELAY+"PRIMARYRELAY.png"));
	}

	@Override
	public void loadString(String load) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClassIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
