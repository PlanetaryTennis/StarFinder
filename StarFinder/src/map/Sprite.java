package map;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Sprite extends ImageIcon {

	/**
	 * 
	 */
	public static final long serialVersionUID = 5170571467610039551L;
	public static final String STARS = "data/sprites/systems/stars/";
	public static final String PLANETS = "data/sprites/systems/planets/";
	public static final String MOONS = "data/sprites/systems/moons/";
	public static final String GASGIANT = "data/sprites/systems/gasgiant/";
	public static final String Gate = "data/sprites/gate/";

	public static Image starField() {
		return Toolkit.getDefaultToolkit().getImage("data/sprites/background/Starfield.png");
	}
}
