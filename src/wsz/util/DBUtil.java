package wsz.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���ݿ�����
 * 
 * @author wsz
 * @date 2018��3��5��
 */
public class DBUtil {

	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url ="jdbc:mysql://106.14.213.251:3306/shopping";
	private static String username = "wsz";
	private static String password = "wsz";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Connection connection = getConnection();
		System.out.println(connection);
	}
}
