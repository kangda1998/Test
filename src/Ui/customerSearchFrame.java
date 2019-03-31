package Ui;


import javax.swing.JFrame;
//���

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

import DAO.CustomerDao;
import Model.customer;


@SuppressWarnings("serial")
public class customerSearchFrame extends JFrame implements ActionListener {
	Container c = null;
	JTextField fldName;
	JButton btnSearch;
	JTable t;
	private Object[][] data;
	private String[] head = new String[] { "�ͻ����", "����", "�Ա�", "�绰����", "����", "ͷ��" };

	public customerSearchFrame() {

		super("�ͻ���ѯ");
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
		btnSearch.addActionListener(this);

		// ��ӽ������Ԫ��
		this.setVisible(true);
		this.setResizable(false);
	}

	public JPanel getTitleGUI() {
		JPanel jp = new JPanel();
		jp.setSize(400, 30);

		JLabel lbl = new JLabel("�ͻ���Ϣ��ѯ", JLabel.LEFT);
		Font f = new Font("����",Font.CENTER_BASELINE, 40);
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
		jp.add(new JLabel("������ؼ���:", JLabel.LEFT));
		fldName = new JTextField("", 15);
		jp.add(fldName);
		btnSearch = new JButton("��ѯ");
		jp.add(btnSearch);
		return jp;
	}

	private JScrollPane getTablePane(String name) {
		// ��ʾ����Ա��
		t = new JTable();
		try {
			this.data = queryData(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, head) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		//���õ�Ԫ�񲻿ɱ༭
		t.setModel(tableModel);

		t.setRowHeight(22);
		JScrollPane sp = new JScrollPane();
		sp.getViewport().add(t);
		return sp;
	}

	// ���ɱ������
	/**
	 * @return
	 * @throws Exception 
	 */
	public Object[][] queryData(String name) throws Exception {
		List<customer> list = new CustomerDao().getAllcustomer(name);
		data = new Object[list.size()][head.length];

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < head.length; j++) {
				data[i][0] = list.get(i).getCmId();
				data[i][1] = list.get(i).getCmName();
				data[i][2] = list.get(i).getGender();
				data[i][3] = list.get(i).getTelPhone();
				data[i][4] = list.get(i).getBirthday();
				data[i][5] = list.get(i).getPic();
			}
		}
		return data;
	}

	public void actionPerformed(ActionEvent e) {
		String name = fldName.getText();
		

		try {
			this.data = queryData(name);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DefaultTableModel tableModel = new DefaultTableModel(data, head) {
		

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		t.setModel(tableModel);
	}}
