package com.race.interrupt.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.race.interrupt.StaticDetector;


public class SRDetectListener implements ActionListener, ItemListener {

	private BufferedReader rd;

	public SRDetectListener() {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!StaticDetector.metaFolder.exists()) {
			StaticDetector.metaFolder.mkdir();
		}
		File filelist[] = StaticDetector.sourceFolder.listFiles();
		int listlen = filelist.length;
		for (int i = 0; i < listlen; i++) {
			if (!filelist[i].isDirectory()) {
				try {
					detectSR(filelist[i]);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void detectSR(File source) throws IOException {
		rd = new BufferedReader(new FileReader(source));
		BufferedWriter wr = new BufferedWriter(new FileWriter(StaticDetector.metaFolder + "//" + "pre.txt"));
		wr.write("###global variables###\n");
		String s;
		Boolean flag = true;
		// int cnt = 0;
		while (rd.ready()) {
			s = rd.readLine(); // ++cnt;
			/**
			 * Check if it is a global variable
			 */
			if (flag && s.matches(".*((int\\s+)|(double\\s+)).*")
					&& s.matches(".*=.*")) {
				String[] arr = s.split(" ", -1);
				StaticDetector.ignoreWhiteSpace(arr);
				String type = "regular";
				int idx = StaticDetector.findString("=", arr);
				--idx;
				String var = "";
				if(idx >= 0) {
					var = arr[idx];
					if(var.matches(".*\\*.*")) {
						var = var.replaceAll("\\*", "");
						type = "pointer";
					}
					else {
						--idx;
						if(idx >= 0 && arr[idx].matches(".*\\*.*")) {
							type = "pointer";
						}
					}
				}
				System.out.println(var + " " + type);
				wr.write(var + " " + type + "\n");
			}
			/**
			 * Check if it is a function definition
			 */
			if (s.matches(".*(void|int|irqreturn_t)\\s+.*(\\).*)")
					&& !s.matches(".*=.*") && !s.matches(".*\\#define.*") && !s.matches("\\s*\\/\\/.*")) {
				if(flag == true) {
					flag = false;
					wr.write("###function variables###\n");
				}
				//System.out.println(cnt + " " + s);
				//ArrayList<String> initVars = new ArrayList<String>();
				// function name
				String[] tmp = s.split("\\(");
				String fname = tmp[0].substring(tmp[0].lastIndexOf(" ") + 1);
				// reference variables
				s = s.substring(s.indexOf("(") + 1, s.indexOf(")"));
				//System.out.println(s);
				String[] arr = s.split(",\\s");
				for(int i = 0; i < arr.length; ++i) {
					String ss = arr[i];
					//System.out.println(ss);
					if(ss.matches(".*\\*.*")) {
						String var = ss.substring(ss.lastIndexOf(" ")+1);
						var = var.replaceAll("\\*", "");
						//System.out.println(fname + " " + cnt + " " + var);
						System.out.println(fname + " " + var);
						wr.write(fname + " " + var + "\n");
						//initVars.add(var);
					}
				}
					
				
			}
		}
		rd.close();
		wr.close();
	}
	
		
	}
