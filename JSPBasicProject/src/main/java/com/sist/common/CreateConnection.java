package com.sist.common;
import java.sql.*;

public class CreateConnection {
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public CreateConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {}
	}
	public Connection getConnection() {
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception e) {}
		return conn;
	}
	public void disConnection(PreparedStatement ps, Connection conn) {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {}
	}
}
