package Ui;

import javax.swing.JFrame;
//浏览

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.AdminDao;
import DAO.CustomerDao;
import Model.admin;
import Model.customer;


@SuppressWarnings("serial")
public class adminorFrame extends JFrame implements ActionListener {
	Container c = null;
	
	JTable t;
	private Object[][] data;
	private String[] head = new String[] { "管理账号", "密码"};

	public adminorFrame() throws Exception {
		
		super("登陆账号管理");
		this.setSize(650, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Image im=(new ImageIcon("2.jpg").getImage());
		this.setIconImage(im);
		c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

		c.add(getTitleGUI());
		c.add(getSearchNameGUI());
		c.add(getTablePane(""));
		
		this.setVisible(true);
		this.setResizable(false);
	}

	public JPanel getTitleGUI() {
		JPanel jp = new JPanel();
		jp.setSize(400, 30);

		JLabel lbl = new JLabel("账号管理", JLabel.LEFT);
		Font f = new Font("楷体", Font.PLAIN, 40);
		lbl.setFont(f);
		jp.add(lbl);
		jp.setBackground(Color.blue);
		return jp;
	}

      public  JPanel getSearchNameGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
	
		return jp;
	}

	private JScrollPane getTablePane(String name) throws ClassNotFoundException {
		
		t = new JTable();
		this.data = queryData(name);
		
		DefaultTableModel tableModel = new DefaultTableModel(data, head) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//设置单元格不可编辑
		t.setModel(tableModel);

		t.setRowHeight(22);
		JScrollPane sp = new JScrollPane();
		sp.getViewport().add(t);
		return sp;
	}

	// 生成表格数据
	/**
	 * @return
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public Object[][] queryData(String adId) throws ClassNotFoundException {
		List<admin> list = new AdminDao(). getAlladmin(adId);
		data = new Object[list.size()][head.length];

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < head.length; j++) {
				data[i][0] = list.get(i).getAdId();
				data[i][1] = list.get(i).getAdPassWord();
	
				
			}
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		
		DefaultTableModel tableModel = new DefaultTableModel(data, head) {
		

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		t.setModel(tableModel);
	}}

