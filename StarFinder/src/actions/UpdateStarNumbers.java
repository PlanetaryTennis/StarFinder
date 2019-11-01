package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.SettingLauncher;

public class UpdateStarNumbers implements ActionListener {

	private SettingLauncher SL;

	public UpdateStarNumbers(SettingLauncher settingLauncher) {
		SL = settingLauncher;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SL.setSuns();
	}

}
