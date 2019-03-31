package DAO;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Ui.adminorFrame;
import Ui.customerAddFrame;


import Ui.customerQueryFrame;
import Ui.customerRetentionFrame;
import Ui.customerSearchFrame;
import Ui.keepadminorFrame;

	/**
	 * 主界面
	 * 
	 * 
	 *
	 */
public class MainFrame extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		public static JPanel GImage = null;
		
		
		// 初始化窗口
		public void initFrame() {
			// 利用JPanel添加背景图片

			GImage = new JPanel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
					ImageIcon icon = new ImageIcon("a.jpg");
					Image img = icon.getImage();
					g.drawImage(img, 0, 0, icon.getIconWidth(), icon
							.getIconHeight(), icon.getImageObserver());
					super.setSize(icon.getIconWidth(), icon.getIconHeight());
				}
			};

			super.add(GImage);

		}

		public MainFrame() {
			super("客户管理系统");
			initFrame();
			Image im=(new ImageIcon("2.jpg").getImage());
			this.setIconImage(im);
			JMenuBar menubar = new JMenuBar();
			// 客户管理:查询客户 ，添加客户， 客户维护
			JMenu menu = new JMenu("客户管理");

			JMenuItem item1 = new JMenuItem(" 浏览客户");
			item1.addActionListener(this);
			JMenuItem item2 = new JMenuItem(" 添加客户");
			item2.addActionListener(this);
			JMenuItem item4 = new JMenuItem(" 客户维护");
			item4.addActionListener(this);
			
			JMenuItem item5 = new JMenuItem(" 查询客户");
			item5.addActionListener(this);
			setJMenuBar(menubar);
			menubar.add(menu);
			menu.add(item1);
			menu.add(item2);
			menu.add(item4);
			menu.add(item5);
			menu.addSeparator();
			JMenuItem item6 = new JMenuItem(" 退出");
			item6.addActionListener(this);
			menu.add(item6);
			// 部门管理：部门浏览，部门维护
			JMenu menu2 = new JMenu("管理员");

			JMenuItem item21 = new JMenuItem("管理账号信息");
			item21.addActionListener(this);
			JMenuItem item22 = new JMenuItem("管理账号维护");
			item22.addActionListener(this);
			menubar.add(menu2);
			menu2.add(item21);
			menu2.add(item22);

			this.setSize(650,500);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}

		public static void main(String[] args) {
			new MainFrame();
		}

		public void actionPerformed(ActionEvent e) {
			// 如何识别点了哪个item？
			System.out.println(e.getActionCommand());
			System.out.println(e.getID());
			JFrame f = null;
			// 显示某个界面
			if (e.getActionCommand().contains("查询客户")) {
				f = new customerSearchFrame();
			}
			if (e.getActionCommand().contains("添加客户")) {
				f = new customerAddFrame();
			}
			if (e.getActionCommand().contains("客户维护")) {
				f = new customerRetentionFrame();
			}
			if (e.getActionCommand().contains("浏览客户")) {
				f = new customerQueryFrame();}
			if (e.getActionCommand().contains("退出")) {
				System.exit(0);
			}
			if (e.getActionCommand().contains("管理账号信息")) {
				try {
					f = new adminorFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().contains("管理账号维护")) {
				f = new keepadminorFrame();
			}
			if (f != null) {
				f.setVisible(true);
			}
		}
	}
	

