package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import astronomy.SolSystem;
import engine.ObjectFiles;
import map.SystemView;

public class loadSystem implements ActionListener {

	JFrame f;
	
	public loadSystem(JFrame frame) {
		f = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String filepath = "";
		JFileChooser fileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty("user.dir"));
		fileChooser.setCurrentDirectory(workingDirectory);
		SolSystem solsystem = null;
		fileChooser.setFileFilter(new FileFilter() {

			public String getDescription() {
				return "Solar System (*.solarsystem)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String filename = f.getName().toLowerCase();
					return filename.endsWith(".solarsystem");
				}
			}
		});
		int result = fileChooser.showOpenDialog(f);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			FileInputStream fileIn = null;
			try {
				fileIn = new FileInputStream(selectedFile.getCanonicalPath());
				filepath = selectedFile.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String read = null;
			try {
				read = ObjectFiles.getFileContent(fileIn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(read == null)
				return;
			solsystem = new SolSystem(read);
		} else {
			return;
		}
		
		new SystemView("System View", solsystem, filepath);
		if(f.getClass() == SystemView.class) {
			SystemView sv = (SystemView)f;
			SystemView.save(sv);
		}
		
		f.dispose();
	}

}
