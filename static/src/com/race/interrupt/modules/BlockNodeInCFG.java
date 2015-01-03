package com.race.interrupt.modules;

import java.util.ArrayList;
import java.util.HashMap;

public class BlockNodeInCFG {
	public String id;
	public String name;
	public String label;
	public ArrayList<BlockNodeInCFG> nexts; // next nodes
	public boolean isVisiting;
	public ArrayList<String> sequence; // sequence: precious name + label
	public HashMap<String, ArrayList<String>> srStatus;
	
	public BlockNodeInCFG() {
		id = "";
		name = "";
		label = "";
		nexts = new ArrayList<BlockNodeInCFG>();
		isVisiting = false;
		sequence = new ArrayList<String>();
		srStatus = new HashMap<String, ArrayList<String>>();
	}
	
	public BlockNodeInCFG(String id, String name, String label) {
		this.id = id;
		this.name = name;
		this.label = label;
		nexts = new ArrayList<BlockNodeInCFG>();
		isVisiting = false;
		sequence = new ArrayList<String>();
		srStatus = new HashMap<String, ArrayList<String>>();
	}
}
