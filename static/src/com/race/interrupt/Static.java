package com.race.interrupt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author comeon0r
 */
public class Static extends javax.swing.JFrame {
	// ui panels
	private javax.swing.JTabbedPane Tab = new javax.swing.JTabbedPane();
	private javax.swing.JPanel Tab_CFG = new javax.swing.JPanel();
	private javax.swing.JPanel tab1 = new javax.swing.JPanel();
	private javax.swing.JPanel Tab_IICFG = new javax.swing.JPanel();
	private javax.swing.JPanel Tab_Race = new javax.swing.JPanel();
	private javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
	// ui menus
	private javax.swing.JMenuBar Menu = new javax.swing.JMenuBar();
	private javax.swing.JMenu menu_File = new javax.swing.JMenu();
	private JMenuItem File_Clear;
	private JMenuItem File_SRDetect;
	private JMenuItem File_CreateRCFG;
	private JMenuItem File_AnalyzeRCFG;
	// private SampleMenuListener sl = new SampleMenuListener();
	private javax.swing.JMenu menu_Help = new javax.swing.JMenu();
	// ui components
	private javax.swing.JButton importSrc = new javax.swing.JButton();
	private javax.swing.JButton importSettings = new javax.swing.JButton();
	private javax.swing.JScrollPane Files = new javax.swing.JScrollPane();
	private javax.swing.JList FilesList = new javax.swing.JList();
	private javax.swing.JButton Refresh = new javax.swing.JButton();
	private javax.swing.JScrollPane SR = new javax.swing.JScrollPane();
	private javax.swing.JTable SRTable = new javax.swing.JTable();
	private javax.swing.JLabel imageLabel = new javax.swing.JLabel();
	private javax.swing.JButton createCFG = new javax.swing.JButton();
	private javax.swing.JList filesList_CFG = new javax.swing.JList();
	private javax.swing.JScrollPane files_CFG = new javax.swing.JScrollPane();
	private javax.swing.JLabel IICFGLabel = new javax.swing.JLabel();
	// End of variables declaration

	static DefaultListModel listmodel = new DefaultListModel();
	private JSplitPane splitPane;
	DefaultTableModel tablemodel = new DefaultTableModel();

	// variables
	static public File sourceFolder = new File("source");
	static public File settingFolder = new File("setting");
	static public File metaFolder = new File("meta");

	public Static() {
		initComponents();
		addListeners();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		// split the pane
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, Files, SR);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
		// tab 1
		javax.swing.GroupLayout tab1_layout = new javax.swing.GroupLayout(tab1);
		tab1_layout
				.setHorizontalGroup(tab1_layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tab1_layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												tab1_layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(Files)
														.addGroup(
																tab1_layout
																		.createSequentialGroup()
																		.addComponent(
																				importSrc,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				108,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(9,
																				9,
																				9)
																		.addComponent(
																				importSettings,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				76,
																				Short.MAX_VALUE))
														.addComponent(
																Refresh,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addComponent(
												SR,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												567, Short.MAX_VALUE)
										.addContainerGap()));
		tab1_layout
				.setVerticalGroup(tab1_layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								tab1_layout
										.createSequentialGroup()
										.addGap(23, 23, 23)
										.addGroup(
												tab1_layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																SR,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																0,
																Short.MAX_VALUE)
														.addGroup(
																tab1_layout
																		.createSequentialGroup()
																		.addComponent(
																				Files,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				302,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				tab1_layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								importSrc)
																						.addComponent(
																								importSettings))
																		.addGap(18,
																				18,
																				18)
																		.addComponent(
																				Refresh)
																		.addComponent(
																				importSettings))
														.addGap(18, 18, 18))
										.addContainerGap(28, Short.MAX_VALUE)));
		tab1.setLayout(tab1_layout);
		Tab.addTab("Files Selection", tab1);
		/*
		 * // tab 1 javax.swing.GroupLayout Tab_FilesLayout = new
		 * javax.swing.GroupLayout(tab1); tab1.setLayout(Tab_FilesLayout);
		 * Tab_FilesLayout
		 * .setHorizontalGroup(Tab_FilesLayout.createParallelGroup
		 * (javax.swing.GroupLayout.Alignment.LEADING)
		 * .addGroup(Tab_FilesLayout.createSequentialGroup().addContainerGap()
		 * .addGroup
		 * (Tab_FilesLayout.createParallelGroup(javax.swing.GroupLayout.
		 * Alignment.LEADING).addComponent(Files)
		 * .addGroup(Tab_FilesLayout.createSequentialGroup
		 * ().addComponent(pre_Process,javax.swing.GroupLayout.PREFERRED_SIZE,
		 * 108, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(9,9,9)
		 * .addComponent
		 * (srAnalysisButton,javax.swing.GroupLayout.DEFAULT_SIZE,76
		 * ,Short.MAX_VALUE))
		 * .addComponent(Refresh,javax.swing.GroupLayout.DEFAULT_SIZE
		 * ,javax.swing.GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)).addGap(18,
		 * 18, 18).addComponent(SR,javax.swing.GroupLayout.DEFAULT_SIZE,567,
		 * Short.MAX_VALUE) .addContainerGap()));
		 * Tab_FilesLayout.setVerticalGroup
		 * (Tab_FilesLayout.createParallelGroup(javax
		 * .swing.GroupLayout.Alignment.LEADING)
		 * .addGroup(Tab_FilesLayout.createSequentialGroup().addGap(23, 23, 23)
		 * .
		 * addGroup(Tab_FilesLayout.createParallelGroup(javax.swing.GroupLayout.
		 * Alignment.LEADING,false)
		 * .addComponent(SR,javax.swing.GroupLayout.PREFERRED_SIZE
		 * ,0,Short.MAX_VALUE) .addGroup(Tab_FilesLayout.createSequentialGroup()
		 * .
		 * addComponent(Files,javax.swing.GroupLayout.PREFERRED_SIZE,302,javax.
		 * swing .GroupLayout.PREFERRED_SIZE).addGap(18,18,18)
		 * .addGroup(Tab_FilesLayout
		 * .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		 * .addComponent(pre_Process)
		 * .addComponent(srAnalysisButton)).addGap(18,18,18)
		 * .addComponent(Refresh))) .addContainerGap(28, Short.MAX_VALUE)));
		 * Tab.addTab("Files Selection", tab1);
		 */
		// tab 2
		javax.swing.GroupLayout Tab_CFGLayout = new javax.swing.GroupLayout(
				Tab_CFG);
		Tab_CFG.setLayout(Tab_CFGLayout);
		Tab_CFGLayout
				.setHorizontalGroup(Tab_CFGLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								Tab_CFGLayout
										.createSequentialGroup()
										.addGroup(
												Tab_CFGLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																Tab_CFGLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				files_CFG,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				167,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(76,
																				76,
																				76)
																		.addComponent(
																				imageLabel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				474,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																Tab_CFGLayout
																		.createSequentialGroup()
																		.addGap(51,
																				51,
																				51)
																		.addComponent(
																				createCFG)))
										.addContainerGap(37, Short.MAX_VALUE)));
		Tab_CFGLayout
				.setVerticalGroup(Tab_CFGLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								Tab_CFGLayout
										.createSequentialGroup()
										.addGap(34, 34, 34)
										.addGroup(
												Tab_CFGLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																imageLabel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																files_CFG,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																333,
																Short.MAX_VALUE))
										.addGap(18, 18, 18)
										.addComponent(createCFG)
										.addContainerGap(27, Short.MAX_VALUE)));
		Tab.addTab("CFG", Tab_CFG);
		// tab 3
		javax.swing.GroupLayout Tab_IICFGLayout = new javax.swing.GroupLayout(
				Tab_IICFG);
		Tab_IICFG.setLayout(Tab_IICFGLayout);
		Tab_IICFGLayout.setHorizontalGroup(Tab_IICFGLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				Tab_IICFGLayout
						.createSequentialGroup()
						.addGap(34, 34, 34)
						.addComponent(IICFGLabel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 691,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(39, Short.MAX_VALUE)));
		Tab_IICFGLayout.setVerticalGroup(Tab_IICFGLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				Tab_IICFGLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(IICFGLabel,
								javax.swing.GroupLayout.DEFAULT_SIZE, 413,
								Short.MAX_VALUE).addContainerGap()));
		Tab.addTab("IICFG", Tab_IICFG);
		// tab 4
		javax.swing.GroupLayout Tab_RaceLayout = new javax.swing.GroupLayout(
				Tab_Race);
		Tab_Race.setLayout(Tab_RaceLayout);
		Tab_RaceLayout.setHorizontalGroup(Tab_RaceLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 764,
				Short.MAX_VALUE));
		Tab_RaceLayout.setVerticalGroup(Tab_RaceLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 435,
				Short.MAX_VALUE));
		Tab.addTab("Race Prediction", Tab_Race);
		// tab 5
		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 764,
				Short.MAX_VALUE));
		jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 435,
				Short.MAX_VALUE));
		Tab.addTab("tab5", jPanel6);
		// tab 6
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(Tab,
								javax.swing.GroupLayout.PREFERRED_SIZE, 769,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(22, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap()
						.addComponent(Tab).addContainerGap()));

		// 为了适应窗体内部各组件大小以及布局而调整window自身大小
		pack();

		// menu File
		menu_File.setText("File");
		menu_File.setMnemonic(KeyEvent.VK_A);
		Menu.add(menu_File);

		File_Clear = new JMenuItem("Clear All", KeyEvent.VK_T);
		File_Clear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		File_Clear.getAccessibleContext().setAccessibleDescription("Clear All");
		File_Clear.addActionListener(new clearAllListener());
		menu_File.add(File_Clear);

		File_SRDetect = new JMenuItem("Detect SR");
		File_SRDetect.getAccessibleContext().setAccessibleDescription("Detect SR");
		File_SRDetect.addActionListener(new SRDetectListener());
		menu_File.add(File_SRDetect);
		
		File_AnalyzeRCFG = new JMenuItem("Analyze RCFG");
		File_AnalyzeRCFG.getAccessibleContext().setAccessibleDescription("Analyze RCFG");
		File_AnalyzeRCFG.addActionListener(new analyzeRCFGListener());
		menu_File.add(File_AnalyzeRCFG);

		// menu Help
		menu_Help.setText("Help");
		Menu.add(menu_Help);
		setJMenuBar(Menu);
	}

	private void addListeners() {
		// //////////////////////////////////////// tab 1
		importSrc.setText("Import Srcs");
		importSrc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					File folderName = new File("source");
					importFiles(evt, sourceFolder);
					JOptionPane.showMessageDialog(null, "File Imported!",
							"Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		importSettings.setText("Import Settings");
		importSettings.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					importFiles(evt, settingFolder);
					JOptionPane.showMessageDialog(null, "File Imported!",
							"Message", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Refresh.setText("Open to Check Files");
		Refresh.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					openCurrentFolder(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// 文件列表
		FilesList.setModel(listmodel);
		Files.setViewportView(FilesList);

		//
		FilesList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

			}
		});

		// 文件列表的监听器
		FilesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					/*
					 * 更新共享变量表格
					 */
				}
			}
		});

		filesList_CFG.setModel(listmodel);
		files_CFG.setViewportView(filesList_CFG);

		// 文件列表的监听器
		filesList_CFG.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					/*
					 * 更新CFG图像
					 */
				}
			}
		});

		createCFG.setText("create IICFG");
		createCFG.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});

		imageLabel.setText("CFG Image");
		imageLabel.setSize(new Dimension(40, 40));
		imageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

			}
		});

		IICFGLabel.setText("IICFG Image");
		IICFGLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {

			}
		});

	}

	// import files
	private void importFiles(java.awt.event.ActionEvent evt, File folderName)
			throws IOException {
		final JFileChooser fc = new JFileChooser();
		int v = fc.showOpenDialog(this);
		if (v == JFileChooser.APPROVE_OPTION) {
			if (!folderName.exists()) {
				folderName.mkdir();
			}
			// copy files
			File srcFile = fc.getSelectedFile();
			String fileName = srcFile.getName();
			File dstFile = new File(folderName + "//" + fileName);
			// copy
			copyFileUsingFileStreams(srcFile, dstFile);
		}
	}

	// copy a file
	private static void copyFileUsingFileStreams(File source, File dest)
			throws IOException {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader(source));
			bw = new BufferedWriter(new FileWriter(dest));
			String line;
			while ((line = br.readLine()) != null) {
				bw.write(line + "\n");
			}
		} catch (Exception e) {
			return;
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
			}
			try {
				if (bw != null)
					bw.close();
			} catch (IOException e) {
			}
		}
	}

	// recursively delete a folder and files
	static public void deleteFolder(File dir) {
		File filelist[] = dir.listFiles();
		int listlen = filelist.length;
		for (int i = 0; i < listlen; i++) {
			if (filelist[i].isDirectory()) {
				deleteFolder(filelist[i]);
			} else {
				filelist[i].delete();
			}
		}
		dir.delete();// 删除当前目录
	}

	private void openCurrentFolder(java.awt.event.ActionEvent evt)
			throws IOException {
		String cmd = "xdg-open .";
		Process p = Runtime.getRuntime().exec(cmd);
	}

	/*
	 * // shared resources detection private void srDetection() throws
	 * IOException { File folder = new File("source"); for (final File fileEntry
	 * : folder.listFiles()) { if (fileEntry.isDirectory()) { //
	 * listFilesForFolder(fileEntry); } else {
	 * System.out.println(fileEntry.getName()); BufferedReader br = new
	 * BufferedReader( new FileReader(fileEntry)); String line; while ((line =
	 * br.readLine()) != null) { // bw.write(line + "\n");
	 * System.out.println(line); } } } }
	 */

	// main函数
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Static.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Static.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Static.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Static.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Static().setVisible(true);
			}
		});
	}

	// 将数组的空格去掉
	static String[] ignoreWhiteSpace(String[] array) {
		for (int i = 0; i < array.length; ++i) {
			array[i] = array[i].replaceAll("\\s", "");
		}
		return array;
	}

	// 在字符串数组中找到某个字符串
	static int findString(String str, String[] array) {

		int len = array.length;
		for (int i = 0; i < len; ++i) {
			if (str.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

}

class clearAllListener implements ActionListener, ItemListener {

	public clearAllListener() {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		File f = Static.sourceFolder;
		if (f.exists()) {
			Static.deleteFolder(f);
		}
		f = Static.settingFolder;
		if (f.exists()) {
			Static.deleteFolder(f);
		}
		f = Static.metaFolder;
		if (f.exists()) {
			Static.deleteFolder(f);
		}
		JOptionPane.showMessageDialog(null, "Projects Cleared!", "Message",
				JOptionPane.INFORMATION_MESSAGE);
	}
}

class createRCFG implements ActionListener, ItemListener {

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


class SRDetectListener implements ActionListener, ItemListener {

	private BufferedReader rd;

	public SRDetectListener() {

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!Static.metaFolder.exists()) {
			Static.metaFolder.mkdir();
		}
		File filelist[] = Static.sourceFolder.listFiles();
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
		BufferedWriter wr = new BufferedWriter(new FileWriter(Static.metaFolder + "//" + "pre.txt"));
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
				Static.ignoreWhiteSpace(arr);
				String type = "regular";
				int idx = Static.findString("=", arr);
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
	



class analyzeRCFGListener implements ActionListener, ItemListener {

	private BufferedReader rd;

	public HashMap<String, Node> mp = new HashMap<String, Node>();
	public Node first = new Node();

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			openFileToAnalyze();
			//System.out.println("===" + mp.get("Node0xa65ccd4").isVisited);
			//printMap(mp);
			DFSAnalyze();
			//analyze();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	
	private void analyzeSRAccess(String id, HashMap<String, ArrayList<String>> srStatus) {
		Node node = mp.get(id);
		System.out.println(node.label);
	}
	
	private void DFSAnalyze() {
		Stack<Node> stk = new Stack<Node>();
		stk.push(first);
		while(!stk.isEmpty()) {
			Node cur = stk.peek();
			// visit current node
			System.out.println("cur " + cur.name);
			for(int i = 0; i < cur.sequence.size(); ++i) {
				System.out.print(cur.sequence.get(i) + ",");
			}
			System.out.println();
			analyzeSRAccess(cur.id, cur.srStatus);
			System.out.println("----------------");
			// pop current node
			cur.isVisiting = false;
			stk.pop();
			// push next nodes
			for(int i = cur.nexts.size() - 1; i >= 0 && cur.nexts.get(i).isVisiting == false; --i) {
				//System.out.println("push " + cur.nexts.get(i).name + " " + cur.nexts.size());
				Node n = cur.nexts.get(i);
				copySequence(cur.sequence, n.sequence);
				n.sequence.add(n.name);
				n.isVisiting = true;
				stk.push(n);
			}
		}
	}
	
	/*// BFS
	private void analyze() {
		System.out.println(mp.size());
		Queue<Node> q = new LinkedList<Node>();
		q.add(first);
		while(!q.isEmpty()) {
			Node cur = q.peek();
			cur.isVisited = true;
			q.remove();
			// visit
			System.out.println(cur.name);
			// BFS
			for(int i = 0; i < cur.nexts.size(); ++i) {
				//String nextId = cur.nexts.get(i);
				Node nextNode = cur.nexts.get(i);
				if(nextNode.isVisited == false) {
					q.add(nextNode);
				}
			}
		}
	}*/

	private void openFileToAnalyze() throws IOException {
		rd = new BufferedReader(new FileReader(new File("IntProcess.dot")));
		String s;
		while(rd.ready()) {
			s = rd.readLine();
			if(s.matches(".*\\[.*")) {
				// extract node
				s = s.replaceAll(" ", "");
				s = s.replaceAll("\\t", "");
				//System.out.println(s);
				String id = s.substring(0, s.indexOf("["));
				String name = s.substring(s.indexOf("{") + 1, s.indexOf("\\"));
				//String[] arr = s.split("_");
				String label = s.substring(s.indexOf("_") + 1, s.lastIndexOf("_"));
				//System.out.println(label);
				Node n = new Node(id, name, label);	
				// put node
				if(mp.containsKey(id)) {
					Node tmp = mp.get(id);
					tmp.id = id;
					tmp.name = name;
					tmp.label = label;
				}
				else {
					mp.put(id, n);
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
					n.sequence.add(n.name);
					// mark
					first = n;
				}
			}
			else if(s.matches(".*->.*")) {
				// precious node
				s = s.replaceAll(" ", "");
				s = s.replaceAll("\t", "");
				s = s.replaceAll(";", "");
				String[] arr = s.split("->");
				Node pre = mp.get(arr[0]);
				// if the precious node has back-edge, then ignore it.
				if(pre.name.matches(".*for\\.inc.*") || pre.name.matches(".*while\\.body.*")) {
					continue;
				}
				// next node
				if(!mp.containsKey(arr[1])) {
					Node nxt = new Node();
					mp.put(arr[1], nxt);
				}
				// add next node
				pre.nexts.add(mp.get(arr[1]));
			}
		}
	}	
}


class Node {
	public String id;
	public String name;
	public String label;
	public ArrayList<Node> nexts; // next nodes
	public boolean isVisiting;
	public ArrayList<String> sequence; // sequence: precious name + label
	public HashMap<String, ArrayList<String>> srStatus;
	
	public Node() {
		id = "";
		name = "";
		label = "";
		nexts = new ArrayList<Node>();
		isVisiting = false;
		sequence = new ArrayList<>();
		srStatus = new HashMap<String, ArrayList<String>>();
	}
	
	public Node(String id, String name, String label) {
		this.id = id;
		this.name = name;
		this.label = label;
		nexts = new ArrayList<Node>();
		isVisiting = false;
		sequence = new ArrayList<>();
		srStatus = new HashMap<String, ArrayList<String>>();
	}
}

class NodeGraph {
	int priority;
	Node first;
	
	public NodeGraph(int p, Node n) {
		priority = p;
		first = n;
	}
}