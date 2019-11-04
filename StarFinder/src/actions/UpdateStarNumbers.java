package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import engine.SettingLauncher;

public class UpdateStarNumbers implements ActionListener {

	private SettingLauncher SL;
	private JFrame myFrame;

	public UpdateStarNumbers(SettingLauncher settingLauncher, JFrame frame) {
		SL = settingLauncher;
		myFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SL.setSuns();
		myFrame.dispose();
	}

}
