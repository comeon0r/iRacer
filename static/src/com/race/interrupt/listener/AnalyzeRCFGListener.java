package com.race.interrupt.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import com.race.interrupt.StaticDetector;
import com.race.interrupt.modules.BlockNodeInCFG;
import com.race.interrupt.modules.SRElement;


public class AnalyzeRCFGListener implements ActionListener, ItemListener {

	// "String->BlockNodeInCFG" means a node's id to itself. 
	public HashMap<String, BlockNodeInCFG> id_Node_mapping = new HashMap<String, BlockNodeInCFG>();
	// mark the first (entry) node.
	public BlockNodeInCFG firstEntryNode = new BlockNodeInCFG();	
	// file content
	static public HashMap<Integer, String> IntProcessFileContent = new HashMap<Integer, String>();

	private BufferedReader rd;

	@Override
	public void itemStateChanged(ItemEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			SRElement sr = new SRElement();
			sr.realName = "tmpAddr";
			sr.fakeName = "tmpAddr";
			StaticDetector.SRs.add(sr);
			sr = new SRElement();
			sr.realName = "mInput";
			sr.fakeName = "mInput";
			StaticDetector.SRs.add(sr);
			
			StaticDetector.Function_LineNumber_Mapping.put("ReadASIC", 31);
			StaticDetector.Function_LineNumber_Mapping.put("WriteASIC", 71);
			StaticDetector.Function_LineNumber_Mapping.put("main", 99);
			
			initialFileContent();
						
			analyzeDotFile();
			
			DFSAnalyzeCFG();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initialFileContent() throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(new File("IntProcess.c")));
		String s;
		int cnt = 0;
		while(in.ready()) {
			s = in.readLine();
			++cnt;
			IntProcessFileContent.put(cnt, s);
		}
	}
	
	public void printMap(Map mp) {
	    Iterator it = mp.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue());
	        String id = (String) pairs.getKey();
	        System.out.println(mp.get(id));
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	}
	
	private void copySequence(ArrayList<String> a1, ArrayList<String> a2) {
		a2.clear();
		for(int i = 0; i < a1.size(); ++i) {
			a2.add(a1.get(i));
		}
	}
	
	private ArrayList<SRElement> copySRs(ArrayList<SRElement> SRs) {
		ArrayList<SRElement> curSRs = new ArrayList<SRElement>();
		for(int i = 0; i < SRs.size(); ++i) {
			SRElement sr = new SRElement();
			sr.inFunction = SRs.get(i).inFunction;
			sr.realName = SRs.get(i).realName;
			sr.fakeName = SRs.get(i).fakeName;
			// add it
			curSRs.add(sr);
		}
		return curSRs;
	}
	
	private ArrayList<SRElement> updateSRsInBlock(String id, ArrayList<SRElement> curSRs) {
		BlockNodeInCFG block = id_Node_mapping.get(id);
		String name = block.name;
		String function = name.substring(0, name.indexOf("."));
		String label = block.label;
		String[] arr = label.split("_");
		for(int i = 0; i < arr.length; ++i) {
			String code = IntProcessFileContent.get(Integer.parseInt(arr[i]));
			if(code.matches(".*\\s=\\s.*")) {
				for(int j = 0; j < curSRs.size(); ++j) {
					String fakeName = curSRs.get(j).fakeName;
					if(code.matches(".*=.*" + fakeName + ".*")) {
						code = code.substring(0, code.indexOf(" = "));
						code = code.replaceAll("\\t", "");
						if(code.matches(".*\\s.*")) {
							code = code.substring(code.indexOf(" "), code.length());
						}
						if(code.matches(".*\\[.*")) {
							code = code.substring(0, code.indexOf("["));
						}
						SRElement sr = new SRElement();
						sr.inFunction = function;
						sr.realName = curSRs.get(j).realName;
						sr.fakeName = code;
						// add it to curSRs
						curSRs.add(sr);
					}
				}
			}
		}
		return curSRs;
	}
	
	private void analyzeSRAccessInCurrentNodeSequence(String id, HashMap<String, ArrayList<String>> srStatus) {
		// current SRs
		ArrayList<SRElement> curSRs = copySRs(StaticDetector.SRs);
		// current access sequence
		ArrayList<String> sq = id_Node_mapping.get(id).sequence;
		for(int i = 1; i < sq.size(); ++i) {
			// update SRs in Block
			curSRs = updateSRsInBlock(sq.get(i - 1), curSRs);
			// function calling analysis
			String name1 = id_Node_mapping.get(sq.get(i - 1)).name;
			String name2 = id_Node_mapping.get(sq.get(i)).name;
			String f1 = name1.substring(0, name1.indexOf("."));
			String f2 = name2.substring(0, name2.indexOf("."));
			//System.out.println(f1 + " " + f2);
			if(!f1.equals(f2)) {
				String caller = "", callee = "";
				// caller
				String label1 = id_Node_mapping.get(sq.get(i - 1)).label;
				String[] arr = label1.split("_");
				for(int j = 0; j < arr.length; ++j) {
					String code = IntProcessFileContent.get(Integer.parseInt(arr[j]));
					if(code.matches(".*" + f2 + ".*")) {
						caller = code.substring(code.indexOf("(") + 1, code.lastIndexOf(")"));
						break;
					}
				}
				// callee
				String label2 = id_Node_mapping.get(sq.get(i)).label;
				arr = label2.split("_");
				String code = IntProcessFileContent.get(Integer.parseInt(arr[0]));
				callee = code.substring(code.indexOf("(") + 1, code.lastIndexOf(")"));
				
				caller = caller.trim().replaceAll(" ", "");
				String[] callerVars = caller.split(",");
				String[] calleeVars = callee.split(",");
				for(int j = 0; j < callerVars.length; ++j) {
					SRElement sr = inSR(callerVars[j], curSRs);
					if(sr != null && calleeVars[j].matches(".*[\\*\\&].*")) {
						String fakeName = calleeVars[j].substring(calleeVars[j].lastIndexOf(" ") + 1, calleeVars[j].length());
						fakeName = fakeName.replaceAll("\\*", "");
						SRElement newSR = new SRElement();
						newSR.inFunction = f2;
						newSR.realName = sr.realName;
						newSR.fakeName = fakeName;
						curSRs.add(newSR);
					}
				}				
				// at the same time of function call, if the precious function is ending, delete its SRs!
				if(name1.matches(".*end.*")) {
					deleteSRs(f1, curSRs);
				}
			}
		}
		// after updating SRs, then check the latest block's SR access.
		BlockNodeInCFG lastNode = id_Node_mapping.get(sq.get(sq.size() - 1));
		String label = lastNode.label;
		String[] arr = label.split("_");		
		// if it is a condition, then may be READ access
		if(lastNode.name.matches(".*cond.*")) {
			for(int i = 0; i < arr.length; ++i) {
				String code = IntProcessFileContent.get(Integer.parseInt(arr[i]));
				for(int j = 0; j < curSRs.size(); ++j) {
					String fakeName = curSRs.get(j).fakeName;
					if(code.matches(".*" + fakeName + ".*")) {
						System.out.println("===COND READ===" + curSRs.get(j).realName);
					}
				}
			}
		}
		// if it is an operation.
		for(int i = 0; i < arr.length; ++i) {
			String code = IntProcessFileContent.get(Integer.parseInt(arr[i]));
			for(int j = 0; j < curSRs.size(); ++j) {
				String fakeName = curSRs.get(j).fakeName;
				// if is ++ or -- operations, then it can be READ and WRITE access
				if(code.matches(".*\\+\\+" + fakeName + ".*") 
						|| code.matches(".*" + fakeName + "\\+\\+.*")
						|| code.matches(".*--" + fakeName + ".*")
						|| code.matches(".*" + fakeName + "--.*")) {
					System.out.println("===READ===" + curSRs.get(j).realName);
					System.out.println("===WRITE===" + curSRs.get(j).realName);
				}
				if(code.matches(".*=.*" + fakeName + ".*")) {
					System.out.println("===READ===" + curSRs.get(j).realName);
				}
				if(code.matches(".*" + fakeName + ".*=.*")) {
					System.out.println("===WRITE===" + curSRs.get(j).realName);
				}
			}
		}
	}
	
	public void deleteSRs(String function, ArrayList<SRElement> curSRs) {
		for(int i = 0; i < curSRs.size(); ) {
			if(curSRs.get(i).inFunction.equals(function)) {
				curSRs.remove(i);
			}
			else {
				++i;
			}
		}
	}
	

	public SRElement inSR(String str, ArrayList<SRElement> curSRs) {
		for(int i = 0; i < curSRs.size(); ++i) {
			if(str.matches(".*" + curSRs.get(i).fakeName + ".*")) {
				return curSRs.get(i);
			}
		}
		return null;
	}
	
	
	private void DFSAnalyzeCFG() {
		Stack<BlockNodeInCFG> stk = new Stack<BlockNodeInCFG>();
		stk.push(firstEntryNode);
		while(!stk.isEmpty()) {
			BlockNodeInCFG cur = stk.peek();
			// analyze current node
			analyzeSRAccessInCurrentNodeSequence(cur.id, cur.srStatus);
			// pop current node
			cur.isVisiting = false;
			stk.pop();
			// push next nodes
			for(int i = cur.nexts.size() - 1; i >= 0 && cur.nexts.get(i).isVisiting == false; --i) {
				BlockNodeInCFG n = cur.nexts.get(i);
				copySequence(cur.sequence, n.sequence);
				n.sequence.add(n.id);
				n.isVisiting = true;
				stk.push(n);
			}
		}
	}

	private void analyzeDotFile() throws IOException {
		rd = new BufferedReader(new FileReader(new File("IntProcess.dot")));
		String s;
		while(rd.ready()) {
			s = rd.readLine();
			if(s.matches(".*\\[.*")) {
				// extract node
				s = s.replaceAll(" ", "");
				s = s.replaceAll("\\t", "");
				String id = s.substring(0, s.indexOf("["));
				String name = s.substring(s.indexOf("{") + 1, s.indexOf("\\"));
				String label = s.substring(s.indexOf("_") + 1, s.lastIndexOf("_"));
				BlockNodeInCFG n = new BlockNodeInCFG(id, name, label);	
				// put node
				if(id_Node_mapping.containsKey(id)) {
					BlockNodeInCFG tmp = id_Node_mapping.get(id);
					tmp.id = id;
					tmp.name = name;
					tmp.label = label;
				}
				else {
					id_Node_mapping.put(id, n);
				}
				// initial the first node
				if(n.name.matches(".*main\\.entry.*")) {
					// initial
					ArrayList<String> tmp = new ArrayList<String>();
					tmp.add("tmpAddr");
					n.srStatus.put("tmpAddr", tmp);
					tmp = new ArrayList<String>();
					tmp.add("mInput");
					n.srStatus.put("mInput", tmp);
					n.sequence.add(n.id);
					// mark
					firstEntryNode = n;
				}
			}
			else if(s.matches(".*->.*")) {
				// precious node
				s = s.replaceAll(" ", "");
				s = s.replaceAll("\t", "");
				s = s.replaceAll(";", "");
				String[] arr = s.split("->");
				BlockNodeInCFG pre = id_Node_mapping.get(arr[0]);
				// if the precious node has back-edge, then ignore it.
				if(pre.name.matches(".*for\\.inc.*") || pre.name.matches(".*while\\.body.*")) {
					continue;
				}
				// next node
				if(!id_Node_mapping.containsKey(arr[1])) {
					BlockNodeInCFG nxt = new BlockNodeInCFG();
					id_Node_mapping.put(arr[1], nxt);
				}
				// add next node
				pre.nexts.add(id_Node_mapping.get(arr[1]));
			}
		}
	}	
}