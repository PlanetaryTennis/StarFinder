package astronomy;

import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;

import map.Sprite;
import units.sidistance;
import utilities.ExtendedMathmatics;

public class Belt extends Planet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729038741857852169L;

	public Belt(Moon[] myMoons,double myEccentricity, sidistance myOrbit, Star myStar) {
		super(myMoons, myEccentricity, myOrbit, myStar);
	}

	@Override
	public String string() {
		return null;
	}

	public static Random ran = new Random(System.currentTimeMillis());
	
	public static Planet makeRandom(sidistance orbit, Star star) {
		int m = ran.nextInt(30)+25;
		double d = ExtendedMathmatics.log(ran.nextInt(499)+1, 1000)/8;
		Moon[] moons = new Moon[m];
		
		for(int i = 0;i < m;i++) {
			moons[i] = Asteroid.makeRandom(d, orbit, star);
			moons[i].setMyName(nameMoon(i));
		}
		
		Belt b = new Belt(moons, d, orbit, star);
		
		b.setMyName(SolSystem.randomName() + " Belt");
		
		return b;
	}

	@Override
	public ImageIcon getIcon() {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage("data/sprites/systems/Asteroid Belt.png"));
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
