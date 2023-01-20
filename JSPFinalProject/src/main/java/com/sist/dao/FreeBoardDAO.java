package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	public List<FreeBoardVO> boardListData(int page){
			//ArrayList의 상위 인터페이스 -> vector, linkedList 모두 사용 가능
		//게시판 목록 출력
		List<FreeBoardVO> list=new ArrayList<FreeBoardVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(project_freeBoard pfb_no_pk)*/no,subject,name,regdate,hit "
					+ "FROM project_freeBoard)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				FreeBoardVO vo=new FreeBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
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
	//페이징
	public int boardTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM project_freeBoard";
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
	//게시물 추가
	public void boardInsert(FreeBoardVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_freeBoard(no,name,subject,content,pwd) "
					+ "VALUES(pfb_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally{
			CreateConnection.disConnection(conn, ps);
		}
	}
	//게시물 상세보기
	//게시물 수정
	//게시물 삭제
	//게시물 검색
	//댓글 -> 프로시저
}
