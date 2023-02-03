package com.sist.main;
import java.sql.*;

public class Transaction {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			conn=DriverManager.getConnection(url,"hr","happy");
			
			conn.setAutoCommit(false); //auto commit 끄기
			
			String sql="INSERT INTO card VALUES(1,'hong',30000)";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); //commit 호출 안 됨
			
			sql="INSERT INTO point VALUES(1,'',300)";
			ps=conn.prepareStatement(sql);
			ps.executeUpdate(); //commit 호출 안 됨
			
			conn.commit();
		} catch(Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback(); //과정 중 실패하면 rollback(commit 취소)
			} catch(Exception e) {}
		} finally {
			try {
				conn.setAutoCommit(true); //다시 auto commit 켜기 -> conn은 전역변수

				if(conn!=null) conn.close();
				if(ps!=null) ps.close();
			} catch(Exception ex) {}
		}
	}
}
