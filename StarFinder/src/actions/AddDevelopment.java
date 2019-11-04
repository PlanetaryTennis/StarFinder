package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import planetary.Development;

/**
 * AddDevelopment adds a development to be used by AddDev,
 * 
 * @author PlanetaryTennis
 * @see AddDev
 */
public class AddDevelopment implements ActionListener {

	AddDev dev;

	/**
	 * Class Constructor that creates the development to be added.
	 * 
	 * @param addDev the associated AddDev to get the name, and cost from.
	 */
	public AddDevelopment(AddDev addDev) {
		dev = addDev;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String n = dev.getMyName().getText();
		int c = dev.getMyCost().getValue();

		switch (dev.getMyIndex()) {
		case 0:
			dev.getMyHabitation().getMyDevelopments().add(new Development(n, c));
			break;
		case 1:
			dev.getMyDevelopment().add(new Development(n, c));
			break;
		}
	}

}
