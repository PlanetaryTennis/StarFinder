package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import astronomy.Galaxy;
import engine.GameLauncher;
import engine.ObjectFiles;
import engine.SettingLauncher;
import utilities.StringFundementals;

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
			String filepath = "";
			JFileChooser fileChooser = new JFileChooser();
			File workingDirectory = new File(System.getProperty("user.dir"));
			fileChooser.setCurrentDirectory(workingDirectory);
			String gal = "";
	    	fileChooser.setFileFilter(new FileFilter() {

	    		   public String getDescription() {
	    		       return "Galaxy (*.galaxy)";
	    		   }

	    		   public boolean accept(File f) {
	    		       if (f.isDirectory()) {
	    		           return true;
	    		       } else {
	    		           String filename = f.getName().toLowerCase();
	    		           return filename.endsWith(".galaxy");
	    		       }
	    		   }
	    		});
		    int result = fileChooser.showOpenDialog(my);
		    if (result == JFileChooser.APPROVE_OPTION) {
		    	File selectedFile = fileChooser.getSelectedFile();
	            FileInputStream fileIn = null;
				try {
					fileIn = new FileInputStream(selectedFile.getCanonicalPath());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	            String read = null;
				try {
					read = ObjectFiles.getFileContent(fileIn);
				} catch (IOException e) {
					e.printStackTrace();
				}
	            String[] box = StringFundementals.breakByLine(read);
	            gal = box[2];
		    	filepath = selectedFile.getName();
		    }else {
				my.setCursor(save);
		      	return;
		    }
		    Galaxy galaxy = (Galaxy)ObjectFiles.ReadSaveableFromFile(gal+"/"+filepath);
			GameLauncher.load("Galaxy Painter", galaxy);
		}
		my.setCursor(save);
		my.dispose();
	}

}
