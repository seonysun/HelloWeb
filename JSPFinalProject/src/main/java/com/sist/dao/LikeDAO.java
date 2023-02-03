package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.vo.*;

public class LikeDAO {
	private Connection conn;
	private PreparedStatement ps;
	//좋아요
	public void likeInsert(LikeVO vo) {
		try {
			conn=CreateConnection.getConnection();
			conn.setAutoCommit(false);
			
			String sql="INSERT INTO project_all_like(lno,no,id) "
					+ "VALUES((SELECT NVL(MAX(lno)+1,1) FROM project_all_like),?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setString(2, vo.getId());
			ps.executeUpdate();
			
			sql="UPDATE project_food "
					+ "SET like_count=(SELECT NVL(MAX(like_count)+1,1) FROM project_food WHERE fno=?) "
					+ "WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ps.setInt(2, vo.getNo());
			ps.executeUpdate();
			
			conn.commit();
		} catch(Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch(Exception e) {}
		} finally {
			try {
				conn.setAutoCommit(true);
				CreateConnection.disConnection(conn, ps);
			} catch (Exception ex) {}
		}
	}
	//좋아요 확인
	public int likeCount(int no, String id) {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_all_like "
					+ "WHERE no=? AND id=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setString(2, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return count;
	}
}