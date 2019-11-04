package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import planetary.Development;

public class UpgadeRemover implements ActionListener {

	Development dev;
	int index;

	public UpgadeRemover(Development d, int k) {
		dev = d;
		index = k;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dev.remove(index);
	}

}
