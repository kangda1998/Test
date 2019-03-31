package Ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



import DAO.CustomerDao;
import Model.customer;


public class customerRetentionFrame extends JFrame {

	private JTextField fldId;
	private JTextField flTelPhone;
	private JTextField fldName;
	private JRadioButton rbGender;
	private JTextField fldBirthDate;
	private JLabel lblPic;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JRadioButton jrb1 = new JRadioButton("��");
	private JRadioButton jrb2 = new JRadioButton("Ů");
	private String fileName = "default.jpg";
	CustomerDao dao=new CustomerDao();
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public customerRetentionFrame() {
		super("�ͻ���Ϣά��");
		this.setSize(650, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container c = this.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		Image im=(new ImageIcon("2.jpg").getImage());
		this.setIconImage(im);
		// ��ӽ������Ԫ��
		c.add(getTitleGUI());
		c.add(getIdGUI());
		c.add( getTelPhoneGUI());
		c.add(getNameGUI());
		c.add(getGenderGUI());

		c.add(getBirthDateGUI());
		c.add(getPicGUI());
		c.add(getDeptGUI());

		c.add(getAddGUI());

		this.setVisible(true);
		this.setResizable(false);
		
		this.btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmId = fldId.getText();
				customer cm = null;
				try {
					cm = new CustomerDao().getcustomerById(cmId);
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (cm == null) {
					JOptionPane.showMessageDialog(null, "�޴˿ͻ���");
					fldName.setText("");
				} else {	
					try {
					fldName.setText(cm.getCmName());
					Blob photo = cm.getPic();
					fldBirthDate.setText(cm.getBirthday());
     	     	    flTelPhone.setText(cm.getTelPhone());
					if (cm.getGender().equals("Ů")) 
					{
						jrb1.setSelected(false);
						jrb2.setSelected(true);
					}
					else {
						jrb1.setSelected(true);
						jrb2.setSelected(false);
					}

					
					
					byte[] imageData = photo.getBytes(1, photo.getBinaryStream().available());

					ImageIcon imageIcon = new ImageIcon(imageData);
					//�����ǿ�����ŵ������(Label)��С��ͬ
					imageIcon =new ImageIcon(imageIcon.getImage().getScaledInstance(130, 150, Image.SCALE_DEFAULT));
		              
					lblPic.setIcon(imageIcon);
					lblPic.setHorizontalAlignment(SwingConstants.CENTER);

				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	});
					
					
					
	
		
		this.btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ���޸���?", "dbc", JOptionPane.YES_NO_OPTION);
				System.out.println(n);
				String cmId = fldId.getText();
				String TelPhone = flTelPhone.getText();
				String cmName = fldName.getText();
				String Birthday = fldBirthDate.getText();
				String gender = jrb1.isSelected() ? "��" : "Ů";
				
				customer cm=new customer( cmId, cmName,gender, TelPhone,Birthday) ;
				
				CustomerDao dao=new CustomerDao();
				File headerFile = new File(fileName);
				FileInputStream inputStream;
				try {
					inputStream = new FileInputStream(headerFile);
					byte[] imageData = new byte[inputStream.available()];
					inputStream.read(imageData);
					// ���浽���ݿ�
					Blob photo = new SerialBlob(imageData);
					int i = dao.updatecustomer(cm, photo);
				
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "��ӳɹ�!");
					} else
						JOptionPane.showMessageDialog(null, "���ʧ�ܣ�", "�ͻ�����ϵͳ", 0);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (SerialException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	 
	
		this.btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?", "����", JOptionPane.YES_NO_OPTION);
				System.out.println(n);
				String cmId = fldId.getText();
				if(n==0){
					int i = 0;
					try {
						i = dao.deletecustomer(cmId);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(i>0)
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
					else
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��!");
				}
			}
		});
	}

	public JPanel getTitleGUI() {
		JPanel jp = new JPanel();
		jp.setSize(400, 50);

		JLabel lbl = new JLabel("�ͻ�ά��", JLabel.LEFT);
		Font f = new Font("����", Font.CENTER_BASELINE, 40);
		lbl.setFont(f);
		jp.add(lbl);
		jp.setBackground(Color.BLUE);
		return jp;
	}

	// 1
	public JPanel getIdGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		jp.add(new JLabel("�ͻ����:", JLabel.LEFT));
		fldId = new JTextField("������ͻ����", 15);
		jp.add(fldId);
		btnSearch = new JButton("��ѯ");
		jp.add(btnSearch);
		return jp;
	}

	// 2
	public JPanel getTelPhoneGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		JLabel lbl = new JLabel("�绰����:", JLabel.LEFT);
		lbl.setSize(55, 30);
		jp.add(lbl);
		 flTelPhone =new JTextField("��������룺", 15);
		jp.add(flTelPhone);
		// jp.setBackground(Color.orange);
		return jp;
	}
	// 3
	public JPanel getNameGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		jp.add(new JLabel("��        ��:", JLabel.LEFT));
		fldName = new JTextField("����������", 15);
		jp.add(fldName);
		// jp.setBackground(Color.orange);
		return jp;
	}

	// 4
	public JPanel getGenderGUI() {
		JPanel jp = new JPanel();
		jp.setBorder(BorderFactory.createTitledBorder("��        ��:"));
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		JLabel lbl = new JLabel("��        ��:", JLabel.LEFT);
		jp.add(lbl);
		JRadioButton[] r = { jrb1, jrb2 };
		ButtonGroup rg = new ButtonGroup();
		for (int i = 0; i < r.length; i++) {
			jp.add(r[i]);
			rg.add(r[i]);
		}
//		r[0].setSelected(true);
		
		return jp;}

	// 5
	public JPanel getBirthDateGUI() {
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(5, 210, 0, 5));
		jp.setSize(400, 50);
		jp.add(new JLabel("��������:", JLabel.LEFT));
		fldBirthDate = new JTextField("", 15);
		jp.add(fldBirthDate);
		// jp.setBackground(Color.orange);
		return jp;
	}

	// 6 Pic
	public JPanel getPicGUI() {
		final JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp.setBorder(new EmptyBorder(3, 210, 0, 5));
		jp.setSize(400, 30);
		jp.add(new JLabel("ͷ        ��:", JLabel.LEFT));
		ImageIcon ic1 = new ImageIcon(fileName);
		lblPic = new JLabel("", ic1, JLabel.LEFT);
		jp.add(lblPic);
		JButton btnBrowse = new JButton("��� ");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(jp);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					fileName = chooser.getSelectedFile().getPath();
					System.out.println(fileName);
					ImageIcon imageIcon = new ImageIcon(fileName);
					imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(130, 150, Image.SCALE_DEFAULT));
					lblPic.setIcon(imageIcon);
					lblPic.setHorizontalAlignment(SwingConstants.CENTER);
				}

			}
		});
		jp.add(btnBrowse);
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
		btnUpdate = new JButton("�� ��");
		btnUpdate.setBackground(Color.pink);
		jp.add(btnUpdate);
		btnDelete = new JButton("ɾ ��");
		btnDelete.setBackground(Color.pink);
		jp.add(btnDelete);
		return jp;
	}

	public static void main(String[] args) {
		new customerRetentionFrame();
	}

	
	}
