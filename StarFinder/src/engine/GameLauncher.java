package engine;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import actions.LauncherButton;
import astronomy.Galaxy;
import map.MapView;
import map.Sprite;

public class GameLauncher {
	JFrame Launcher;

	GameLauncher() {
		Launcher = new JFrame("Star Finder");
		Launcher.setLayout(new FlowLayout());

		JTextArea name = new JTextArea("Star Finder");
		name.setEditable(false);
		Launcher.add(name);

		JButton NewEdit = new JButton("New Galaxy Painter");
		NewEdit.addActionListener(new LauncherButton(2, Launcher));
		Launcher.add(NewEdit);

		JButton LoadEdit = new JButton("Load Galaxy Painter");
		LoadEdit.addActionListener(new LauncherButton(3, Launcher));
		Launcher.add(LoadEdit);

		Launcher.setIconImage(Toolkit.getDefaultToolkit().getImage(Sprite.STARS + "Black Hole.png"));
		Launcher.setSize(150, 200);
		Launcher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Launcher.setVisible(true);
	}

	public static void load(String name, Galaxy galaxy) {
		MapView v = new MapView(name, galaxy);
		v.getClass();
	}
}
