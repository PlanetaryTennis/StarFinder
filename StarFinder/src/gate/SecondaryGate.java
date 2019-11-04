package gate;

import java.awt.Toolkit;
import java.util.Random;
import java.util.UUID;
import java.util.Vector;

import javax.swing.ImageIcon;

import astronomy.Region;
import astronomy.SolSystem;
import astronomy.Zone;
import astronomy.planetary.Planet;
import engine.ObjectFiles;
import map.Sprite;
import utilities.StringFundementals;

public class SecondaryGate implements Gate {

	private Zone myZone;
	private String mySystem;
	private String myPlanet;
	private String myGhost;

	private Region targetRegion;

	private Vector<SecondaryGate> myPod;
	private String myID;

	public SecondaryGate() {
		myID = UUID.randomUUID().toString() + ".station";
	}

	public SecondaryGate(String load) {
		this.loadString(load);
	}

	public Zone getMyZone() {
		return myZone;
	}

	public void setMyZone(Zone myZone) {
		this.myZone = myZone;
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
		return myZone.getMyName() + " Gate";
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(Sprite.Gate + "SECONDARYGate.png"));
	}

	public SecondaryGate getPod(int i) {
		return myPod.get(i);
	}

	public Vector<SecondaryGate> getMyPod() {
		return myPod;
	}

	public void setMyPod(Vector<SecondaryGate> out) {
		this.myPod = out;
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

	@Override
	public String saveString() {
		String out = "";
		out += myID + "\n";
		out += getClassIndex() + "\n";
		out += getMySystem() + "\n";
		out += getMyPlanet() + "\n";
		out += getMyGhost() + "\n";
		out += getMyZone().getID() + "\n";
		return out;
	}

	public static final int CLASSINDEX = 678804;

	@Override
	public int getClassIndex() {
		return CLASSINDEX;
	}

	String ZoneID;

	@Override
	public String getID() {
		return myID;
	}

	static Random ran = new Random(System.currentTimeMillis());

	public static SecondaryGate randomSecond(Zone zone) {
		SecondaryGate out = new SecondaryGate();
		out.setMyZone(zone);
		SolSystem sol = (SolSystem) ObjectFiles.ReadSaveableFromFile(
				zone.getMyRegion().getMySector().getMyGalaxy().getMyName() + "/" + zone.getSystemIDs().get(0));
		int p = ran.nextInt(sol.getMyObjects().size());
		Planet plan = sol.getMyObjects().get(p);
		ImageGate r = new ImageGate(out);
		plan.getMySatilights().add(r);
		out.setMyGhost(r.getID());
		out.setMyPlanet(plan.getID());
		out.setMySystem(sol.getID());
		ObjectFiles.WriteSavabletoFile(sol, zone.getMyRegion().getMySector().getMyGalaxy().getMyName());
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

	public String getMySystem() {
		return mySystem;
	}

	public void setMySystem(String mySystem) {
		this.mySystem = mySystem;
	}

}
