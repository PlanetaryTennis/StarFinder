package actions;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import planetary.Development;
import planetary.Habitation;

/**
 * AddDev adds functionality to allow the user to add a custom development to
 * the world.
 * 
 * @author PlanetaryTennis
 */
public class AddDev implements ActionListener {

	private Habitation h;
	private Development d;
	private int type;

	private JTextArea Name;
	private JSlider Cost;

	/**
	 * Class Constructor that gives the colony for the development to be added on.
	 * 
	 * @param myColony A habitation of the planet to be edited.
	 */
	public AddDev(Habitation myColony) {
		h = myColony;
		type = 0;
	}

	/**
	 * Class Constructor that gives the development for the upgrade to be added on.
	 * 
	 * @param dev A Development to be edited.
	 */
	public AddDev(Development dev) {
		d = dev;
		type = 1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame look = new JFrame("Colony View");
		look.setLayout(new FlowLayout());

		Name = new JTextArea("Name Here");
		look.add(Name);

		Cost = new JSlider();
		Cost.setPaintLabels(true);
		Cost.setValue(1);
		Cost.setMinimum(1);
		Cost.setMaximum(5);
		Cost.setMajorTickSpacing(1);
		Cost.setPaintTicks(true);

		JButton Apply = new JButton("Add");
		Apply.addActionListener(new AddDevelopment(this));
		look.add(Apply);

		look.setSize(400, 200);
		look.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		look.setVisible(true);
	}

	/**
	 * Gets the Habitation associated with the AddDev, will return null if the
	 * AddDev was constructed from a Development.
	 * 
	 * @return Habitation
	 */
	public Habitation getMyHabitation() {
		return h;
	}

	/**
	 * Gets the Development associated with the AddDev will return null if the
	 * AddDev was constructed from a Habitation.
	 * 
	 * @return Development
	 */
	public Development getMyDevelopment() {
		return d;
	}

	/**
	 * Gets the index associated with the AddDev, 0 if the AddDev was constructed
	 * with a Habitation, 1 if the AddDev was constructed with a Development.
	 * 
	 * @return 0 or 1
	 */
	public int getMyIndex() {
		return type;
	}

	/**
	 * Gets the slider of the AddDev used to select the Cost associated with the
	 * custom development.
	 * 
	 * @return Cost JSlider
	 */
	public JSlider getMyCost() {
		return Cost;
	}

	/**
	 * Gets the text area of the AddDev used to select the Name associated with the
	 * custom development.
	 * 
	 * @return Name JTextArea
	 */
	public JTextArea getMyName() {
		return Name;
	}
}
