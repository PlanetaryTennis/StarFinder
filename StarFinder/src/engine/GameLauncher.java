package engine;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import actions.LauncherButton;

public class GameLauncher {
	JFrame Launcher;
	
	GameLauncher(){
		Launcher = new JFrame("Star Finder");
		Launcher.setLayout(new FlowLayout());
		
		JTextArea name = new JTextArea("Star Finder");
		name.setEditable(false);
		Launcher.add(name);
		
		JButton New = new JButton("Generate New");
		New.setEnabled(false);
		Launcher.add(New);
		
		JButton Load = new JButton("Load Galaxy");
		Load.setEnabled(false);
		Launcher.add(Load);
		
		JButton NewEdit = new JButton("New Galaxy Painter");
		NewEdit.addActionListener(new LauncherButton(2,Launcher));
		Launcher.add(NewEdit);
		
		JButton LoadEdit = new JButton("Load Galaxy Painter");
		LoadEdit.setEnabled(false);
		Launcher.add(LoadEdit);
		
		Launcher.setSize(150, 200);
		Launcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Launcher.setVisible(true);
	}
}
