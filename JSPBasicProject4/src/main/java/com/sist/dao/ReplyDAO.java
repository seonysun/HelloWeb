/* 메소드 제작 시 매개변수 3개 이상 사용하지 말 것
 * 	-> 배열이나 클래스에 담아서 전송 */
package com.sist.dao;
import java.util.*;
import java.sql.*;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public ReplyDAO() {
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
	//댓글쓰기
	public void replyInsert(ReplyVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO jsp_reply(no,fno,id,name,msg) "
					+ "VALUES(jr_no_seq.nextval,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getFno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	//댓글읽기
	public ArrayList<ReplyVO> replyListData(int fno){
		ArrayList<ReplyVO> list=new ArrayList<ReplyVO>();
		try {
			getConnection();
			String sql="SELECT no,fno,id,name,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS'),msg "
					+ "FROM jsp_reply "
					+ "WHERE fno=? "
					+ "ORDER BY no DESC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ReplyVO vo=new ReplyVO();
				vo.setNo(rs.getInt(1));
				vo.setFno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setDbday(rs.getString(5));
				vo.setMsg(rs.getString(6));
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
	public int replyCount(int fno) {
		int count=0;
		try {
			getConnection();
			String sql="SELECT COUNT(*) FROM jsp_reply "
					+ "WHERE fno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, fno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}
	//댓글수정
	public void replyUpdate(int no, String msg) {
		try {
			getConnection();
			String sql="UPDATE jsp_reply "
					+ "SET msg=? "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, msg);
			ps.setInt(2, no);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	//댓글삭제
	public void replyDelete(int no) {
		try {
			getConnection();
			String sql="DELETE FROM jsp_reply "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	//star
	public int a2(int fno) {
		int count=0;
		try {
			getConnection();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return count;
	}
}