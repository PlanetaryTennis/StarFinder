package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import astronomy.old.Galaxy;
import engine.GameLauncher;
import engine.ObjectFiles;
import engine.SettingLauncher;

/**
 * @author PlanetaryTennis
 */
public class LauncherButton implements ActionListener {

	JFrame my;
	int dothing;
	
	/**
	 * @author PlanetaryTennis
	 * @param index [int] who will be used to determine the function of the button.
	 * @param JFrame the GameLauncher so that it is closed after the function is completed.
	 */
	public LauncherButton(int index,JFrame GameLauncher){
		my = GameLauncher;
		dothing = index;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Cursor save = my.getCursor();
		my.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		if(dothing == 0) {
			return;
		}else if(dothing == 1) {
			return;
		}else if(dothing == 2) {
			SettingLauncher j = new SettingLauncher(true);
			j.getClass();
		}else {
			String filepath;
			JFileChooser fileChooser = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir"));
			fileChooser.setCurrentDirectory(workingDirectory);
		    int result = fileChooser.showOpenDialog(my);
		    if (result == JFileChooser.APPROVE_OPTION) {
		    	File selectedFile = fileChooser.getSelectedFile();
		    	filepath = selectedFile.getName();
		    }else {
				my.setCursor(save);
		      	return;
		    }
		    Galaxy galaxy = (Galaxy)ObjectFiles.ReadObjectFromFile(filepath);
			GameLauncher.load("Galaxy Painter", galaxy);
		}
		my.setCursor(save);
		my.dispose();
	}

}
