package actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import engine.SettingLauncher;

public class LauncherButton implements ActionListener {

	JFrame my;
	int dothing;
	
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
		}else {
			return;
		}
		my.setCursor(save);
		my.dispose();
	}

}
