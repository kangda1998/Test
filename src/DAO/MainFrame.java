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
	 * ������
	 * 
	 * 
	 *
	 */
public class MainFrame extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		public static JPanel GImage = null;
		
		
		// ��ʼ������
		public void initFrame() {
			// ����JPanel��ӱ���ͼƬ

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
			super("�ͻ�����ϵͳ");
			initFrame();
			Image im=(new ImageIcon("2.jpg").getImage());
			this.setIconImage(im);
			JMenuBar menubar = new JMenuBar();
			// �ͻ�����:��ѯ�ͻ� ����ӿͻ��� �ͻ�ά��
			JMenu menu = new JMenu("�ͻ�����");

			JMenuItem item1 = new JMenuItem(" ����ͻ�");
			item1.addActionListener(this);
			JMenuItem item2 = new JMenuItem(" ��ӿͻ�");
			item2.addActionListener(this);
			JMenuItem item4 = new JMenuItem(" �ͻ�ά��");
			item4.addActionListener(this);
			
			JMenuItem item5 = new JMenuItem(" ��ѯ�ͻ�");
			item5.addActionListener(this);
			setJMenuBar(menubar);
			menubar.add(menu);
			menu.add(item1);
			menu.add(item2);
			menu.add(item4);
			menu.add(item5);
			menu.addSeparator();
			JMenuItem item6 = new JMenuItem(" �˳�");
			item6.addActionListener(this);
			menu.add(item6);
			// ���Ź����������������ά��
			JMenu menu2 = new JMenu("����Ա");

			JMenuItem item21 = new JMenuItem("�����˺���Ϣ");
			item21.addActionListener(this);
			JMenuItem item22 = new JMenuItem("�����˺�ά��");
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
			// ���ʶ������ĸ�item��
			System.out.println(e.getActionCommand());
			System.out.println(e.getID());
			JFrame f = null;
			// ��ʾĳ������
			if (e.getActionCommand().contains("��ѯ�ͻ�")) {
				f = new customerSearchFrame();
			}
			if (e.getActionCommand().contains("��ӿͻ�")) {
				f = new customerAddFrame();
			}
			if (e.getActionCommand().contains("�ͻ�ά��")) {
				f = new customerRetentionFrame();
			}
			if (e.getActionCommand().contains("����ͻ�")) {
				f = new customerQueryFrame();}
			if (e.getActionCommand().contains("�˳�")) {
				System.exit(0);
			}
			if (e.getActionCommand().contains("�����˺���Ϣ")) {
				try {
					f = new adminorFrame();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getActionCommand().contains("�����˺�ά��")) {
				f = new keepadminorFrame();
			}
			if (f != null) {
				f.setVisible(true);
			}
		}
	}
	

