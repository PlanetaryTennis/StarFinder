package relay;

import java.awt.Toolkit;
import java.util.UUID;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.Region;
import astronomy.Sector;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import map.Sprite;

public class PrimaryRelay implements Relay {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1650990345049687346L;
	
	PrimaryRelay myPartner;
	private Zone myZone;
	private SolSystem mySystem;
	private Planet myWorld;

	private String myID;
	
	public PrimaryRelay() {
		myID = UUID.randomUUID().toString()+".station";
	}
	
	public PrimaryRelay(String load) {
		this.loadString(load);
	}

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

	@Override
	public String string() {
		return getMyName();
	}

	@Override
	public String getMyName() {
		return myZone.getMyRegion().getName() + " of " + myZone.getMyRegion().getMySector().getName() +
				" to " +
				myPartner.getMyZone().getMyRegion().getName() + " of " + myPartner.getMyZone().getMyRegion().getMySector().getName() +
				"Relay";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.RELAY+"PRIMARYRELAY.png"));
	}

	@Override
	public int loadString(String load) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String saveString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static final int CLASSINDEX = 678809;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	@Override
	public String getID() {
		return myID;
	}

	public Zone getMyZone() {
		return myZone;
	}
	
	public void setMyZone(Zone zone) {
		myZone = zone;
	}

	public static PrimaryRelay randomPrime(Region region) {
		
		return null;
	}

}
