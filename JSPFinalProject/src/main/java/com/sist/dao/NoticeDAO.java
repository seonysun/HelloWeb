package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;


public class NoticeDAO {
	private Connection conn;
	private PreparedStatement ps;
	public List<NoticeVO> noticeTop5(){
		List<NoticeVO> list=new ArrayList<NoticeVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,name,subject,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),rownum "
					+ "FROM (SELECT no,name,subject,regdate "
					+ "FROM project_notice "
					+ "ORDER BY hit DESC) "
					+ "WHERE rownum<=5";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo=new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
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
}
