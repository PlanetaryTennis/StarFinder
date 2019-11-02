package relay;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;

import javax.swing.ImageIcon;

import astronomy.Region;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import engine.ObjectFiles;
import map.Sprite;
import utilities.StringFundementals;

public class PrimaryRelay implements Relay {

	PrimaryRelay myPartner;
	private Zone myZone;
	private String mySystem;
	private String myPlanet;
	private String myGhost;

	private String myID;
	
	public PrimaryRelay() {
		myID = UUID.randomUUID().toString()+".station";
	}
	
	public PrimaryRelay(String load) {
		this.loadString(load);
	}

	public String getMySystem() {
		return mySystem;
	}

	public void setMySystem(String mySystem) {
		this.mySystem = mySystem;
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
		String[] in = StringFundementals.breakByLine(load);
		myID = in[0];
		int i = 2;
		mySystem = in[i++];
		myPlanet = in[i++];
		myGhost = in[i++];
		ZoneID = in[i++];
		return i;
	}

	public String ZoneID;
	
	@Override
	public String saveString() {
		String out = "";
		out += myID;
		out += getClassIndex();
		out += getMySystem() + "\n";
		out += getMyPlanet() + "\n";
		out += getMyGhost() + "\n";
		out += getMyZone().getID() + "\n";
		return out;
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

	static Random ran = new Random(System.currentTimeMillis());
	
	public static PrimaryRelay randomPrime(Region region) {
		PrimaryRelay out = new PrimaryRelay();
		int z = ran.nextInt(region.getMyZones().size());
		int s = ran.nextInt(region.getMyZones().get(z).getSystemIDs().size());
		SolSystem sol = (SolSystem)ObjectFiles.ReadSaveableFromFile(region.getMySector().getMyGalaxy().getMyName()+"/"+region.getMyZones().get(z).getSystemIDs().get(s));
		int p = ran.nextInt(sol.getMyObjects().size());
		Planet plan = sol.getMyObjects().get(p);
		ImageRelay r = new ImageRelay(out);
		plan.getMySatilights().add(r);
		out.setMyGhost(r.getID());
		out.setMyPlanet(plan.getID());
		out.setMySystem(sol.getID());
		out.setMyZone(region.getMyZones().get(z));
		ObjectFiles.WriteSavabletoFile(sol, region.getMySector().getMyGalaxy().getMyName());
		return out;
	}

	public String getMyGhost() {
		return myGhost;
	}

	public void setMyGhost(String myGhost) {
		this.myGhost = myGhost;
	}

	public String getMyPlanet() {
		return myPlanet;
	}

	public void setMyPlanet(String myPlanet) {
		this.myPlanet = myPlanet;
	}

}
