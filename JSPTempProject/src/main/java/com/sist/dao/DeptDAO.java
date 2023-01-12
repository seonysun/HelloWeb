package com.sist.dao;
import java.util.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeptDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public DeptDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(Exception ex) {}
	}
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	public ArrayList<DeptVO> deptListData(){
		ArrayList<DeptVO> list=new ArrayList<DeptVO>();
		try {
			getConnection();
			String sql="SELECT deptno,dname,loc "
					+ "FROM dept";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				DeptVO vo=new DeptVO();
				vo.setDeptno(rs.getInt(1));	
				vo.setDname(rs.getString(2));
				vo.setLoc(rs.getString(3));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
}