package actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import engine.Namable;

public class Rename implements ActionListener {

	private Namable myO;
	
	public Rename(Namable in) {
		myO = in;		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFrame view = new JFrame("Rename: " + myO.getClass());
		view.setLayout(new BorderLayout());

		JTextArea Name = new JTextArea(myO.getMyName());
		view.add(Name);
		
		JButton update = new JButton("Change Name");
		update.addActionListener(new newName(myO,Name));
		view.add(update);

		view.setSize(400, 400);
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setVisible(true);
	}

}
