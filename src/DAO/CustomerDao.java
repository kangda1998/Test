package DAO;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;







import Model.customer;
import Util.DBUtil;




public class CustomerDao{
private Connection conn = null;
private PreparedStatement pst = null;
private ResultSet rs = null;

/**
 * ��¼
 * 
 * @param adId
 * @param adPassword
 * @return
 */
public boolean login(String adId, String adPassword) throws ClassNotFoundException {
	try {
		conn = DBUtil.getConnection();
		String sql = "select * from admin where adId=? and adPassword=?";// ?����

		pst = conn.prepareStatement(sql);
		pst.setString(1, adId);
		pst.setString(2, adPassword);
		rs = pst.executeQuery();
		while (rs.next()) {
			return true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closeResultSet(rs);
		DBUtil.closePreparedStatement(pst);
		DBUtil.closeConnection();
	}
	return false;
}

/**
 * ��ӿͻ�
 * 
 */
public int addcustomer(customer cm,Blob photo) throws ClassNotFoundException {
	try {
		conn = DBUtil.getConnection();
		String sql = "insert into customer(cmId,cmName ,Gender,TelPhone,birthday,pic)values(?,?,?,?,?,?);";// ?����

		pst = conn.prepareStatement(sql);
		pst.setString(1, cm.getCmId());
		pst.setString(2, cm.getCmName());
		pst.setString(3, cm.getGender());
		pst.setString(4, cm.getTelPhone());
		pst.setString(5,cm.getBirthday());
		pst.setBlob(6, photo);
		int i = pst.executeUpdate();
		return i;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closePreparedStatement(pst);
		DBUtil.closeConnection();
	}
	return 0;
}

// �õ����пͻ�(String name)
public List<customer> getAllcustomer(String name) throws ClassNotFoundException {
	List<customer> list = new ArrayList<customer>();
	try {
		conn = DBUtil.getConnection();
		String sql = "select * from customer where cmName like '%" + name
				+ "%'";// ?����
		System.out.println(sql);
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		while (rs.next()) {
			customer cm = new customer(rs.getString("cmId"), rs
					.getString("cmName"), rs.getString("Gender"), rs
					.getString("TelPhone"), rs.getString("birthday"),rs.getBlob("pic"));
			list.add(cm);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closeResultSet(rs);
		DBUtil.closePreparedStatement(pst);
		DBUtil.closeConnection();
	}
	return list;
}
//��ѯ
public customer getcustomerById(String cmId) throws ClassNotFoundException {
	
		try {
			conn=	DBUtil.getConnection();
			String sql = "select * from customer where cmId ='" + cmId+"'";// ?����
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				//ÿѭ��һ�εõ� Ա������Ϣ�����뼯�ϣ�
				customer cm=new customer(rs.getString("cmId") ,
						 rs.getString("cmName") ,
						rs.getString("gender") ,rs.getString("TelPhone"),
						rs.getString("birthday"),rs.getBlob("pic"));
				return cm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(pst);
			DBUtil.closeConnection();
		}
		return null;
	}
//ɾ��
public int deletecustomer(String cmId) throws ClassNotFoundException {
	try {
		conn=	DBUtil.getConnection();
		String sql = "delete from customer where cmId=?";		// ?����

		pst = conn.prepareStatement(sql);
		pst.setString(1,cmId);
		int i=pst.executeUpdate();
		return i;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closePreparedStatement(pst);
		DBUtil.closeConnection();
	}
	return 0;}
//�޸�


public int updatecustomer(customer cm, Blob photo) throws ClassNotFoundException {
	try {
		conn=	DBUtil.getConnection();
	String sql = "update customer set cmName =?, gender=?,TelPhone=?,birthday=?,pic=? where cmId=?;";		// ?����

		pst = conn.prepareStatement(sql);

		pst.setString(1,cm.getCmName());
		pst.setString(2,cm.getGender());
		pst.setString(3,cm.getTelPhone());
		pst.setString(4,cm.getBirthday());
		pst.setBlob(5, photo);
		pst.setString(6,cm.getCmId());
		int i=pst.executeUpdate();
		return i;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		DBUtil.closePreparedStatement(pst);
		DBUtil.closeConnection();
	}
	return 0;
}

}
