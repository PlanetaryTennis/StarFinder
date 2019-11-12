package map;

import astronomy.planetary.Planet;
import gate.PrimaryGate;
import gate.SecondaryGate;
import planetary.Colony;
import planetary.Condition;

public interface PlanetViewer {
	
	public void viewPlanet(Planet p);

	public void viewWorldData(Planet p);

	public void viewSurface(Planet myPlanet);

	public void viewBiosphere(Colony col, Condition con);

	public void viewPrimaryGate(PrimaryGate myGate);

	public void viewSecondaryGate(SecondaryGate myGate);

}
