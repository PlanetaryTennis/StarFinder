package engine;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import actions.GateGeneration;
import astronomy.Galaxy;
import map.MapView;

public class EditMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7678966553337165564L;
	MapView myMap;
	Galaxy myGalaxy;

	public EditMenu(MapView map) {
		super("Edit");
		myMap = map;
		myGalaxy = map.getGalaxy();
		this.setLayout(new FlowLayout());

		JButton GenerateNetwork = new JButton("Generate Gate Network");
		GenerateNetwork.addActionListener(new GateGeneration(myGalaxy, this));
		this.add(GenerateNetwork);

		this.setSize(400, 100);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
