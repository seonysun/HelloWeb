package com.sist.dao;
import java.sql.*;
import java.util.*;

import com.sist.vo.AllReplyVO;

public class AllReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	//댓글작성
	public void allReplyInsert(AllReplyVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_all_reply "
					+ "VALUES(par_rno_seq.nextval,?,?,?,?,?,SYSDATE)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getCate_no());
			ps.setInt(2, vo.getNo());
			ps.setString(3, vo.getId());
			ps.setString(4, vo.getName());
			ps.setString(5, vo.getMsg());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//댓글목록
	public List<AllReplyVO> allReplyListData(int no, int cate_no) {
		List<AllReplyVO> list=new ArrayList<AllReplyVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT rno,cate_no,no,id,name,msg,TO_CHAR(regdate,'YYYY_MM_DD HH24:MI:SS') "
					+ "FROM project_all_reply "
					+ "WHERE no=? AND cate_no=? "
					+ "ORDER BY rno DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.setInt(2, cate_no);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				AllReplyVO vo=new AllReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setCate_no(rs.getInt(2));
				vo.setNo(rs.getInt(3));
				vo.setId(rs.getString(4));
				vo.setName(rs.getString(5));
				vo.setMsg(rs.getString(6));
				vo.setDbday(rs.getString(7));
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
	//댓글수정
	public void allReplyUpdate(int rno, String msg) {
		try {
			conn=CreateConnection.getConnection();
			String sql="UPDATE project_all_reply "
					+ "SET msg=? "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, rno);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//댓글삭제
	public void allReplyDelete(int rno) {
		try {
			conn=CreateConnection.getConnection();
			String sql="DELETE FROM project_all_reply "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rno);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
}
