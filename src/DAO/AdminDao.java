package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.admin;
import Model.customer;
import Util.DBUtil;




public class AdminDao<Dept> {
	private Connection conn=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	
	public List<admin> getAlladmin(String adId) throws ClassNotFoundException {
		List<admin> list=new ArrayList<admin>();
		try {
			conn=	DBUtil.getConnection();
			String sql = "select * from admin";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				//每循环一次得到 账号的信息，加入集合；
				admin d=new admin(rs.getString("adId") ,
				 rs.getString("adPassWord") );
				list.add(d);
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

	public admin getadminById(String adId) throws ClassNotFoundException {

		try {
			conn=	DBUtil.getConnection();
			String sql = "select * from admin where adId ='" + adId+"'";// ?参数
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				//每循环一次得到的信息，加入集合；
				admin ad=new admin(rs.getString("adId") ,
						 rs.getString("adPassWord"));
				return ad;
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

	

	public int deleteadmin(String adId) throws ClassNotFoundException {
		try {
			conn=	DBUtil.getConnection();
			String sql = "delete from admin where adId=?";		// ?参数

			pst = conn.prepareStatement(sql);
			pst.setString(1,adId);
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

	public int updateadmin(String adId, String adPassWord) throws ClassNotFoundException {
		try {
			conn=	DBUtil.getConnection();
			String sql = "update admin set adPassWord=? where adId=?;";		// ?参数

				pst = conn.prepareStatement(sql); //得到预处理语句对象

				pst.setString(1,adPassWord);
				pst.setString(2,adId);
				
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
