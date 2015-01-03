package com.race.interrupt.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

public class createRCFG implements ActionListener, ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = "datarace";
		//String[] cmd = {"./run.sh " + name, "cp CFG.dot " + name + ".dot", "dot -Tpng " + name + ".dot" + " -o " + name + ".png"};
		//String[] cmd = {"./run.sh " + name};
		//String[] cmd = {"./run.sh datarace"};
//		String cmd = "./run.sh datarace";
		try {
			String cmd = "./run.sh " + name;
			Runtime.getRuntime().exec(cmd);
			cmd = "cp CFG.dot " + name + ".dot";
			Runtime.getRuntime().exec(cmd);
			cmd = "dot -Tpng " + name + ".dot" + " -o " + name + ".png";
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		rename();
	}

	private void rename() {
		String name = "datarace";
		String cmd = "cp CFG.dot datarace.dot";
		//ArrayList<String> cmd = new ArrayList<String>();
		try {
			Process p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
