package DAO;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登录界面
 * 
 * 
 * 
 *
 */
public class LoginFrame<CustomerDAO> {
	private JFrame f;
	private Container c;

	private JLabel lblTitle;
	private JLabel lblName;
	private JTextField fldName;
	private JLabel lblPwd;
	private JPasswordField fldPwd;
	private JButton btnLogin;
	private JLabel label;
	private ImageIcon icon;


	
	public LoginFrame () {
	
		f = new JFrame();
		// 设置大小
		f.setSize(300, 300);
		// 设置标题
		lblTitle = new JLabel("客户管理系统");
		lblName = new JLabel("账号：");
		fldName = new JTextField();
		lblPwd = new JLabel("密码：");
		fldPwd = new JPasswordField();
		btnLogin = new JButton("登录");
		// 设置布局为空，使用坐标控制控件位置的时候，一定要设置布局为空
		f.setLayout(null);
		// 设置位置与大小
		lblTitle.setBounds(100, 50, 150, 30);
		// lblTitle.setSize();
		lblName.setBounds(50, 100, 50, 30);
		fldName.setBounds(100, 100, 150, 30);
		lblPwd.setBounds(50, 150, 50, 30);
		fldPwd.setBounds(100, 150, 150, 30);
		btnLogin.setBounds(100, 210, 100, 40);
		// 添加控件
		c = f.getContentPane();
		c.add(lblTitle);
		c.add(lblName);
		c.add(fldName);
		c.add(lblPwd);
		c.add(fldPwd);
		c.add(btnLogin);
		// 设置f的相对位置，参数为null，即显示在屏幕中间
		f.setLocationRelativeTo(null);
		// 设置当用户在此对话框上启动 "close" 时默认执行的操作
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 设置是否显示
		f.setIconImage(new ImageIcon("a.jpg").getImage());
		
		 icon = new ImageIcon("2.jpg");
			label = new JLabel(icon);
			label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
			f.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

			// 获取frame的顶层容器,并设置为透明
			JPanel j = (JPanel) f.getContentPane();
			j.setOpaque(false);
		
        //标题
        f.setTitle("客户管理系统");
		f.setVisible(true);
		f.setResizable(false);
		// P53
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Button’s label is:"+
				// e.getActionCommand());
				
				String name = fldName.getText();
				String pwd = fldPwd.getText();
				if (name.trim().equals("") || pwd.trim().equals("")) {
					JOptionPane.showMessageDialog(f, "帐号或密码必填！", "客户管理系统", 0);
					return;
				}
				System.out.println(name);
				System.out.println(pwd);
				
				// ok
				CustomerDao dao = new CustomerDao();
				try {
					if (dao.login(name, pwd)) {
						// JOptionPane.showMessageDialog(f, "登录成功!");
						MainFrame f2 = new MainFrame();
						f2.setVisible(true);
						f.setVisible(false);
					} else
						JOptionPane.showMessageDialog(f, "登录失败！", "客户管理系统", 0);
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});



	}







	public static void main(String[] args) {
		new LoginFrame();
	}
}

