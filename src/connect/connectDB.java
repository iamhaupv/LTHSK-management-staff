package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectDB {
	public static Connection con = null;
	public static connectDB instance = new connectDB();
	public static connectDB getInstance() {
		return instance;
	}
	public static void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QLNVIEN";
		String user = "sa";
		String pwd = "123456789";
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void disconect() {
		if(con!=null) {
			try {
				con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return con;
	}
}
