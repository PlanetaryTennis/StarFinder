package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import astronomy.SolSystem;
import engine.ObjectFiles;
import map.SystemView;
import utilities.StringFundementals;

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
		String solsystem = "";
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
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String read = null;
			try {
				read = ObjectFiles.getFileContent(fileIn);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Vector<String> box = StringFundementals.breakByLine(read);
			solsystem = box.get(2);
			filepath = selectedFile.getName();
		} else {
			return;
		}
		SolSystem System = (SolSystem) ObjectFiles.ReadSaveableFromFile(solsystem + "/" + filepath);
		
		new SystemView("System View", System);
		if(f.getClass() == SystemView.class) {
			SystemView sv = (SystemView)f;
			SystemView.save(sv);
		}
		
		f.dispose();
	}

}
