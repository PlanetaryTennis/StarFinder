package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.SettingLauncher;

public class StarSetter implements ActionListener {

	SettingLauncher SL;

	public StarSetter(SettingLauncher settingLauncher) {
		SL = settingLauncher;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SL.SunCalculat();
	}

}
