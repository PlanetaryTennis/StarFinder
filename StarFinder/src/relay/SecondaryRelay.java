package relay;

import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.Region;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import map.Sprite;

public class SecondaryRelay implements Relay {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6115418467272003541L;
	private Zone myZone;
	private SolSystem mySystem;
	private Planet myWorld;
	
	private Region targetRegion;
	
	private RelayNetwork myNetwork;
	private SecondaryRelay[] myPod;
	
	
	
	public Zone getMyZone() {
		return myZone;
	}

	public void setMyZone(Zone myZone) {
		this.myZone = myZone;
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

	public Region getTargetRegion() {
		return targetRegion;
	}

	public void setTargetRegion(Region targetRegion) {
		this.targetRegion = targetRegion;
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
		return myZone.getMyName()+" Relay";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.RELAY+"SECONDARYRELAY.png"));
	}

	public Vector<SecondaryRelay> getPod() {
		return myPod;
	}

	public SecondaryRelay[] getMyPod() {
		return myPod;
	}

	public void setMyPod(SecondaryRelay[] myPod) {
		this.myPod = myPod;
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
