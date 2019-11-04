package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import planetary.Development;

public class AddDevelopment implements ActionListener {

	AddDev dev;

	public AddDevelopment(AddDev addDev) {
		dev = addDev;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String n = dev.Name.getText();
		int c = dev.Cost.getValue();

		switch (dev.type) {
		case 0:
			dev.h.getMyDevelopments().add(new Development(n, c));
			break;
		case 1:
			dev.d.add(new Development(n, c));
		}
	}

}
