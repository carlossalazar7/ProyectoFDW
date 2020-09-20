package sv.edu.udb.www.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

	protected PreparedStatement st;
	protected CallableStatement cs;
	protected ResultSet rs;

	public DBConnection() {

		this.st = null;
		this.rs = null;
	}

	public static Connection createConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/music";
		String username = "root";
		String password = "";

		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Post establishing a DB connection - " + con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void desconectar() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}

	}
}