package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;

	public ArrayList<CategoryVO> foodCategoryData(){
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT /*+ INDEX_ASC(project_food_category pfc_cno_pk) */cno,title,subject,poster "
					+ "FROM project_food_category";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo=new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
				list.add(vo);
			}
			rs.close();
		} catch(Exception ex){
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return list;
	}
}
