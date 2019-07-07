package MapEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import Game.ImgSource;
public class FrameMain extends JFrame{

	private PanelMain panelMain;
	private PanelIcon panelIcon;
	public void initFrame() {
		ImageIcon icon=new ImageIcon("Icon2.jpg");  //xxx代表图片存放路径，2.png图片名称及格式
		this.setIconImage(icon.getImage());
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if(panelMain.getchanged()==true){
					int option = JOptionPane.showConfirmDialog(null, "         要保存吗？","提示", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if (option == JOptionPane.YES_OPTION){
						JFileChooser fileDialog = new JFileChooser();
						int state = fileDialog.showSaveDialog(null);
						if (state == JFileChooser.APPROVE_OPTION) {
							// 取得文件路径显示在tfDir文本框中
							try {
								panelMain.saveMap(fileDialog.getSelectedFile().getAbsoluteFile());
								panelMain.setchanged(false);
							} catch (FileNotFoundException f) {
								// TODO Auto-generated catch block
								f.printStackTrace();
							}
							System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());
			
						}
					}
					panelMain.setchanged(false);
				}
					System.exit(0);
			}
		});
		//新建地图面板
		panelMain = new PanelMain();	
		panelMain.initPanel();
		panelMain.setBorder(new LineBorder(Color.BLUE));
		panelMain.setPreferredSize(new Dimension(800,600));		
		//新建一个滚动面板，地图面板区域放到滚动面板中，垂直滚动条总是显示，水平滚动条只有在需要时显示
		JScrollPane panel = new JScrollPane(panelMain,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		panelIcon = new PanelIcon();	
		panelIcon.initPanel();
		panelMain.setPanelIcon(panelIcon);
		panelIcon.setPanelMain(panelMain);
		
		setLayout(new BorderLayout());
		add(panelIcon,BorderLayout.WEST);
		add(panel);//	将滚动区域添加到窗体
		
		//添加菜单
		JMenuBar menuBar = new JMenuBar();//新建菜单条
		this.setJMenuBar(menuBar);//将菜单条添加到窗体上
		
		JMenu fileMenu = new JMenu("File");//新建菜单"File"
		fileMenu.setMnemonic('F');//设置"File"菜单助记符
		menuBar.add(fileMenu);//将菜单"File"添加到菜单条中		
		
		JMenuItem newItem = new JMenuItem("new");//新建"new"菜单项
		newItem.setAccelerator(KeyStroke.getKeyStroke('N'));//为菜单项添加"N"快捷键
		fileMenu.add(newItem);//将"new"菜单项添加到"File"菜单中
		
		JMenuItem openItem = new JMenuItem("open");//新建"open"菜单项
		//为菜单项添加Ctrl+O快捷键
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		fileMenu.add(openItem);//将"open"菜单项添加到"File"菜单中
		
		JMenuItem saveItem = new JMenuItem("save");//新建"open"菜单项
		//为菜单项添加Ctrl+S快捷键
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		fileMenu.add(saveItem);//将"save"菜单项添加到"File"菜单中
		
		JMenu fileMenu2 = new JMenu("Undo");//新建菜单"File"
		fileMenu2.setMnemonic('U');//设置"File"菜单助记符
		menuBar.add(fileMenu2);//将菜单"File"添加到菜单条中		
		
		JMenuItem undoItem = new JMenuItem("undo");//新建"Undo"菜单项
		undoItem.setAccelerator(KeyStroke.getKeyStroke('U'));//为菜单项添加"U"快捷键
		fileMenu2.add(undoItem);//将"undo"菜单项添加到"File"菜单中

		JMenu fileMenu3 = new JMenu("Redo");//新建菜单"File"
		fileMenu3.setMnemonic('R');//设置"File"菜单助记符
		menuBar.add(fileMenu3);//将菜单"File"添加到菜单条中	

		JMenuItem redoItem = new JMenuItem("redo");//新建"Undo"菜单项
		redoItem.setAccelerator(KeyStroke.getKeyStroke('R'));//为菜单项添加"U"快捷键
		fileMenu3.add(redoItem);//将"undo"菜单项添加到"File"菜单中
		
		JMenu fileMenu4 = new JMenu("Help");//新建菜单"File"
		fileMenu4.setMnemonic('H');//设置"File"菜单助记符
		menuBar.add(fileMenu4);//将菜单"File"添加到菜单条中	

		JMenuItem helpItem = new JMenuItem("help");//新建"Undo"菜单项
		redoItem.setAccelerator(KeyStroke.getKeyStroke('H'));//为菜单项添加"U"快捷键
		fileMenu4.add(helpItem);//将"undo"菜单项添加到"File"菜单中

		newItem.addActionListener(new actNew());//指向open函数
		openItem.addActionListener(new actOpen());//指向open函数
		saveItem.addActionListener(new actSave());//指向sava函数
		undoItem.addActionListener(new actUndo());//指向sava函数
		redoItem.addActionListener(new actRedo());//指向sava函数
		helpItem.addActionListener(new actHelp());//指向sava函数
		
		this.setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);//设置窗体可见
	}
	class actSave implements ActionListener{

		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			
			int state = fileDialog.showSaveDialog(null);
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	// 取得文件路径显示在tfDir文本框中
	        	try {
					panelMain.saveMap(fileDialog.getSelectedFile().getAbsoluteFile());
					panelMain.setchanged(false);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());

	        }
			
		}
		
	}

	class actUndo implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			

	        	try {
					panelMain.Undo();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	        
			
		}
		
	}

	class actRedo implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			

	        	try {
					panelMain.Redo();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


	        
			
		}
		
	}
	class actOpen implements ActionListener{

		JFileChooser fileDialog = new JFileChooser();
		public void actionPerformed(ActionEvent arg0) {
			if(panelMain.getchanged()==true){
				int s = JOptionPane.showConfirmDialog(null, "要保存现有的编辑吗？", "地图编辑器", JOptionPane.YES_NO_OPTION);
				if (s == JOptionPane.YES_OPTION) {
					actSave sa=new actSave();
					sa.actionPerformed(arg0);
				}
			}
			panelMain.removeall();
			panelMain.setchanged(false);
			int state = fileDialog.showOpenDialog(null);
			fileDialog.setCurrentDirectory(new File("."));
	        if (state == JFileChooser.APPROVE_OPTION) {
	        	String name=fileDialog.getSelectedFile().getPath();
				System.out.println(name);
				File file = new File(name);
					// 取得文件路径显示在tfDir文本框中
	        	try {
					//Desktop.getDesktop().open(file);//待会改
					panelMain.openMap(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println(fileDialog.getSelectedFile().getAbsoluteFile());

	        }
			
		}
		
	}

	class actNew implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			if(panelMain.getchanged()==true){
				int s = JOptionPane.showConfirmDialog(null, "要保存现有的编辑吗？", "地图编辑器", JOptionPane.YES_NO_OPTION);
				if (s == JOptionPane.YES_OPTION) {
					actSave sa=new actSave();
					sa.actionPerformed(arg0);
				}
			}
			panelMain.setchanged(false);
			panelMain.removeall();
			panelMain.repaint();
			
		}
	}
	class actHelp implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {

			
			JOptionPane.showMessageDialog(null,"使用鼠标选择需要添加的图标，放置于想要添加的位置，保存后可用于游戏。","帮助",JOptionPane.PLAIN_MESSAGE);
		}
	}
}
