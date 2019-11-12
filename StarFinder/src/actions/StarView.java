package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import astronomy.stellar.Star;
import map.StarViewable;

public class StarView implements ActionListener {

	StarViewable myView;
	Star myStar;

	public StarView(Star s, StarViewable  v) {
		myStar = s;
		myView = v;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		myView.viewStar(myStar);
	}

}
