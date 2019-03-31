package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

//数据库连接公共类
public class DBUtil {
	private static Properties prop = new Properties();
	private static Connection conn = null;
	static
	{

		try {
			prop.load(DBUtil.class.getResourceAsStream("db.properties"));
			Class.forName(prop.getProperty("driver"));
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	
	
	}

	public static Connection getConnection() throws SQLException  {
		// 1加载类库
	
	
			
		conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));			
		 
		return conn;
	}

	public static void closeConnection() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closePreparedStatement(PreparedStatement pst) {
		try {
			if (pst != null)
				pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
