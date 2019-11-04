package actions;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import planetary.Development;
import planetary.Habitation;

public class ColonyViewer implements ActionListener {

	private Habitation myColony;

	public ColonyViewer(Habitation myColony) {
		this.myColony = myColony;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame look = new JFrame("Colony View");
		look.setLayout(new FlowLayout());
		String display = "";

		int i = 0;

		JButton dev;
		Development d;
		for (int k = 0; k < myColony.getMyDevelopments().size(); k++) {
			d = myColony.getMyDevelopments().get(k);
			dev = new JButton(d.getMyName());
			dev.setToolTipText(d.read());
			i += d.getMyCost();
			dev.addActionListener(new DevelopmentViewer(d));
			look.add(dev);
		}

		dev = new JButton("Add New Development");
		dev.addActionListener(new AddDev(myColony));
		look.add(dev);

		display = i + "/" + myColony.getMaxDev();
		JTextArea Read = new JTextArea(display);
		Read.setEditable(false);
		look.add(Read);
		look.setSize(400, 400);
		look.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		look.setVisible(true);
	}

}
