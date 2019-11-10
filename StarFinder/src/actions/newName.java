package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import engine.Namable;

public class newName implements ActionListener {

	private JTextArea Name;
	private Namable Rename;
	
	public newName(Namable myO, JTextArea name) {
		Rename = myO;
		Name = name;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Rename.setMyName(Name.getText());
	}

}
