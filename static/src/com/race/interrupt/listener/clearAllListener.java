package com.race.interrupt.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JOptionPane;

import com.race.interrupt.StaticDetector;

public class clearAllListener implements ActionListener, ItemListener {

	public clearAllListener() {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File f = StaticDetector.sourceFolder;
		if (f.exists()) {
			StaticDetector.deleteFolder(f);
		}
		f = StaticDetector.settingFolder;
		if (f.exists()) {
			StaticDetector.deleteFolder(f);
		}
		f = StaticDetector.metaFolder;
		if (f.exists()) {
			StaticDetector.deleteFolder(f);
		}
		JOptionPane.showMessageDialog(null, "Projects Cleared!", "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}