package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;


public class NoticeDAO {
	private Connection conn;
	private PreparedStatement ps;
	//공지사항 top5_푸터
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
	//공지사항 목록 출력
	public List<NoticeVO> noticeListData(int page){
		List<NoticeVO> list=new ArrayList<NoticeVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,type,name,subject,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),hit,num "
					+ "FROM (SELECT no,type,name,subject,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(project_notice pn_no_pk)*/no,type,name,subject,regdate,hit "
					+ "FROM project_notice)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				NoticeVO vo=new NoticeVO();
				vo.setNo(rs.getInt(1));
				vo.setType(rs.getInt(2));
				vo.setName(rs.getString(3));
				vo.setSubject(rs.getString(4));
				vo.setDbday(rs.getString(5));
				vo.setHit(rs.getInt(6));
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
	//공지사항 총페이지
	public int noticeTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) "
					+ "FROM project_notice";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return total;
	}
	//공지사항 등록
	public void noticeInsert(NoticeVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_notice "
					+ "VALUES((SELECT NVL(MAX(no)+1,1) FROM project_notice),?,?,?,?,SYSDATE,0)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getType());
			ps.setString(2, vo.getName());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//공지사항 삭제
	public void noticeDelete(int no) {
		try {
			conn=CreateConnection.getConnection();
			String sql="DELETE FROM project_notice "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//공지사항 상세 출력
	public NoticeVO noticeDetailData(int no, int type) {
		NoticeVO vo=new NoticeVO();
		try {
			conn=CreateConnection.getConnection();
			String sql="";
			if(type==1) {
				sql="UPDATE project_notice "
						+ "SET hit=hit+1 "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			} else {
				sql="SELECT no,name,subject,content,type,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
						+ "FROM project_notice "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ResultSet rs=ps.executeQuery();
				rs.next();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setType(rs.getInt(5));
				vo.setHit(rs.getInt(6));
				vo.setDbday(rs.getString(7));
				rs.close();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	//공지사항 수정
	public void noticeUpdate(NoticeVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="UPDATE project_notice "
					+ "SET type=?,subject=?,content=? "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getType());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setInt(4, vo.getNo());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
}
