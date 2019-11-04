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

public class AddDev implements ActionListener {

	Habitation h;
	Development d;
	int type;
	
	JTextArea Name;
	JSlider Cost;
	
	public AddDev(Habitation myColony) {
		h = myColony;
		type = 0;
	}

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

}
