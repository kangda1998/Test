package Ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DAO.AdminDao;
import DAO.CustomerDao;
import Model.admin;
import Model.customer;

@SuppressWarnings("serial")
public class keepadminorFrame extends JFrame {
	private JTextField fldId;
	private JTextField fldPassWord;
	
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	
	@SuppressWarnings("unchecked")
	AdminDao dao=new AdminDao() ;

	public keepadminorFrame() {
		super("账号信息维护");
		this.setSize(650, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		Image im=(new ImageIcon("2.jpg").getImage());
		this.setIconImage(im);
		c.add(getTitleGUI());
		c.add(getIdGUI());
		c.add( getadPassWord());
		c.add(getNameGUI());
		c.add(getGenderGUI());

		c.add(getBirthDateGUI());
		c.add(getPicGUI());
		c.add(getDeptGUI());

		c.add(getAddGUI());
		this.setVisible(true);
		this.setResizable(false);
		// app.setUndecorated(true); // 去掉窗口的装饰
		// app.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG
		// );//采用指定的窗口装饰风格

		this.btnSearch.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				String adId = fldId.getText();
				admin ad = null;
				try {
					ad = new AdminDao().getadminById(adId);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (ad == null) {
					JOptionPane.showMessageDialog(null, "无此账号！");
					fldId.setText("");
				} else {JOptionPane.showMessageDialog(null, "查询成功!");
					fldId.setText(ad.getAdId());
				}
			}
		});

		this.btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认修改吗?", "dbc", JOptionPane.YES_NO_OPTION);
				System.out.println(n);
				String adId = fldId.getText();
				String  adPassWord =fldPassWord.getText();
			
				if(n==0){
					int i = 0;
					try {
						i = dao.updateadmin(adId,adPassWord);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(i>0)
						JOptionPane.showMessageDialog(null, "修改成功!");
					else
						JOptionPane.showMessageDialog(null, "修改失败!");
				
				
				}
			}
		});
		this.btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "标题", JOptionPane.YES_NO_OPTION);
				System.out.println(n);
				String adId = fldId.getText();
				if(n==0){
					int i = 0;
					try {
						i = dao.deleteadmin(adId);
					} catch (ClassNotFoundException e1) {
						
						e1.printStackTrace();
					}
						if(i>0)
						JOptionPane.showMessageDialog(null, "删除成功!");
					else
						JOptionPane.showMessageDialog(null, "删除失败!");
				}
			}
		});
	}

		
		
		
		

	public JPanel  getTitleGUI() {
		JPanel jp = new JPanel();
		jp.setSize(400, 50);

		JLabel lbl = new JLabel("账号维护", JLabel.LEFT);
		Font f = new Font("楷体", Font.CENTER_BASELINE, 40);
		lbl.setFont(f);
		jp.add(lbl);
		jp.setBackground(Color.BLUE);
		return jp;
	}
	public JPanel getIdGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		jp.add(new JLabel("账号:", JLabel.LEFT));
		fldId = new JTextField("请输入账号", 15);
		jp.add(fldId);
		btnSearch = new JButton("查询");
		jp.add(btnSearch);
		return jp;
	}
	public JPanel  getadPassWord() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		JLabel lbl = new JLabel("密码:", JLabel.LEFT);
		lbl.setSize(55, 30);
		jp.add(lbl);
		fldPassWord =new JPasswordField("请输入密码：", 15);
		jp.add(fldPassWord);
		// jp.setBackground(Color.orange);
		return jp;
	}
	// 3
	public JPanel getNameGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		
		// jp.setBackground(Color.orange);
		return jp;
	}

	// 4
	public JPanel getGenderGUI() {
		JPanel jp = new JPanel();
	
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		
		return jp;}

	// 5
	public JPanel getBirthDateGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		
		return jp;
	}

	// 6 Pic
	public JPanel getPicGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		
		return jp;
	}

	// 7 Dept
	public JPanel getDeptGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
	
		return jp;
	}

	// 8 add
	public JPanel getAddGUI() {
		JPanel jp = new JPanel();
		jp.setSize(400, 50);
		// jp.setBackground(Color.orange);
		btnUpdate = new JButton("修 改");
		btnUpdate.setBackground(Color.pink);
		jp.add(btnUpdate);
		btnDelete = new JButton("删 除");
		btnDelete.setBackground(Color.pink);
		jp.add(btnDelete);
		return jp;
	}
	public static void main(String[] args) {
		new keepadminorFrame();
	}
}
