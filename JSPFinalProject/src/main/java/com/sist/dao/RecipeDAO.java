package com.sist.dao;
import java.sql.*;
import java.util.*;
import com.sist.vo.*;

public class RecipeDAO {
	private Connection conn;
	private PreparedStatement ps;
	public List<RecipeVO> recipeListData(int page){
		List<RecipeVO> list=new ArrayList<RecipeVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT rno,title,poster,chef,num "
					+ "FROM (SELECT rno,title,poster,chef,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(project_recipe prcp_rno_pk)*/rno,title,poster,chef "
					+ "FROM project_recipe)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				RecipeVO vo=new RecipeVO();
				vo.setRno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setChef(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
	public int recipeRowCount() {
		int count=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT COUNT(*) FROM project_recipe";
			ps=conn.prepareStatement(sql);
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
