package com.sist.db;
import java.util.*;
import java.sql.*;
import com.sist.common.*;

public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private CreateConnection c=new CreateConnection();
	public void getConnection() {
		conn=c.getConnection();
	}
	public void disConnection() {
		c.disConnection(ps,conn);
	}
	public void empData() {
		try {
			getConnection();
			String sql="SELECT ename FROM emp";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public static void main(String[] args) {
		EmpDAO dao=new EmpDAO();
		dao.empData();
	}
}
