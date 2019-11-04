package actions;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import planetary.Development;

/**
 * DevelopmentViewer allows one to create a JFrame to view a Development.
 * @author PlanetaryTennis
 */
public class DevelopmentViewer implements ActionListener {

	Development Dev;

	/**
	 * Class Constructor
	 * @param d Development to be viewed
	 */
	public DevelopmentViewer(Development d) {
		Dev = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame look = new JFrame("Development View");
		look.setLayout(new FlowLayout());
		String display = "";
		display += Dev.getMyName();
		display += Dev.getMyCost();
		JTextArea Read = new JTextArea(display);
		Read.setEditable(false);
		look.add(Read);

		JButton dev;
		Development d;
		for (int k = 0; k < Dev.getMyUpgrades().size(); k++) {
			d = Dev.getMyUpgrades().get(k);
			dev = new JButton(d.getMyName());
			dev.setToolTipText("" + d.getMyCost());
			dev.addActionListener(new UpgadeRemover(d, k));
			look.add(dev);
		}

		dev = new JButton("Add New Upgrade");
		dev.addActionListener(new AddDev(Dev));
		look.add(dev);

		look.setSize(400, 400);
		look.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		look.setVisible(true);
	}

}
