package com.race.interrupt;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.race.interrupt.listener.SRDetectListener;
import com.race.interrupt.listener.AnalyzeRCFGListener;
import com.race.interrupt.listener.clearAllListener;
import com.race.interrupt.modules.SRElement;


/**
 * 
 * @author comeon0r
 */
public class StaticDetector extends javax.swing.JFrame {
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

	
	// SR
	static public ArrayList<SRElement> SRs = new ArrayList<SRElement>();
	// function number
	static public HashMap<String, Integer> Function_LineNumber_Mapping = new HashMap<String, Integer>();
	// function variables
//	static public HashMap<String, FVPair> FVs = new HashMap<String, FVPair>();
	
	public StaticDetector() {
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
		File_AnalyzeRCFG.addActionListener(new AnalyzeRCFGListener());
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
			java.util.logging.Logger.getLogger(StaticDetector.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(StaticDetector.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(StaticDetector.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(StaticDetector.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StaticDetector().setVisible(true);
			}
		});
	}

	// 将数组的空格去掉
	public static String[] ignoreWhiteSpace(String[] array) {
		for (int i = 0; i < array.length; ++i) {
			array[i] = array[i].replaceAll("\\s", "");
		}
		return array;
	}

	// 在字符串数组中找到某个字符串
	public static int findString(String str, String[] array) {

		int len = array.length;
		for (int i = 0; i < len; ++i) {
			if (str.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

}





	
