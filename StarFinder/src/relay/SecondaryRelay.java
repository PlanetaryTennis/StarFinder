package relay;

import java.awt.Toolkit;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.AstroObject;
import astronomy.Region;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import engine.ObjectFiles;
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
	
	private Vector<SecondaryRelay> myPod;
	private String myID;
	
	public SecondaryRelay() {
		myID = UUID.randomUUID().toString()+".station";
	}
	
	public SecondaryRelay(String load) {
		this.loadString(load);
	}

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

	public SecondaryRelay getPod(int i) {
		return myPod.get(i);
	}

	public Vector<SecondaryRelay> getMyPod() {
		return myPod;
	}

	public void setMyPod(Vector<SecondaryRelay> out) {
		this.myPod = out;
	}

	@Override
	public int loadString(String load) {
		return 0;
	}

	@Override
	public String saveString() {
		// TODO Auto-generated method stub
		return null;
	}

	public static final int CLASSINDEX = 678804;
	
	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	@Override
	public String getID() {
		return myID;
	}

	public static SecondaryRelay randomSecond(Zone zone) {
		SecondaryRelay Relay = new SecondaryRelay();
		Relay.setMyZone(zone);
		SolSystem sol = (SolSystem) ObjectFiles.ReadSaveableFromFile(zone.getMyRegion().getMySector().getMyGalaxy().getMyName()+"/"+zone.getSystemIDs().get(0));
		
		return Relay;
	}

}
