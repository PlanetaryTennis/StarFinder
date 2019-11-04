package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.stellar.Star;
import map.MapView;

public class StarView implements ActionListener {

	MapView myView;
	Star myStar;

	public StarView(Star s, MapView v) {
		myStar = s;
		myView = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.viewStar(myStar);
	}

}
