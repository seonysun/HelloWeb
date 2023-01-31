package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class FreeBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	//게시판 목록 출력
	public List<FreeBoardVO> boardListData(int page){
			//ArrayList의 상위 인터페이스 -> vector, linkedList 모두 사용 가능
		List<FreeBoardVO> list=new ArrayList<FreeBoardVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(project_freeBoard pfb_no_pk)*/no,subject,name,regdate,hit "
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
			String sql="INSERT INTO project_freeBoard(no,name,subject,content,pwd,hit) "
					+ "VALUES(pfb_no_seq.nextval,?,?,?,?,0)";
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
	public FreeBoardVO boardDetailData(int no) {
		FreeBoardVO vo=new FreeBoardVO();
		try {
			conn=CreateConnection.getConnection();
			String sql="UPDATE project_freeBoard "
					+ "SET hit=hit+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
					+ "FROM project_freeBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	//게시물 수정
	public FreeBoardVO boardUpdateData(int no) {
		FreeBoardVO vo=new FreeBoardVO();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,name,subject,content "
					+ "FROM project_freeBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return vo;
	}
	public boolean boardUpdate(FreeBoardVO vo) {
		boolean bCheck=false;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT pwd FROM project_freeBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				sql="UPDATE project_freeBoard "
						+ "SET name=?,subject=?,content=? "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setInt(4, vo.getNo());
				ps.executeUpdate();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
	}
	//게시물 삭제
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck=false;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT pwd FROM project_freeBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				sql="DELETE FROM project_freeBoard "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
		return bCheck;
	}
	//댓글 작성
	public void replyInsert(BoardReplyVO vo) {
		try {
			conn=CreateConnection.getConnection();
			String sql="INSERT INTO project_reply(rno,bno,id,name,msg,group_id) "
					+ "VALUES(pr_rno_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) "
					+ "FROM project_reply))";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getBno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			CreateConnection.disConnection(conn, ps);
		}
	}
	//댓글 읽기
	public List<BoardReplyVO> replyListData(int bno) {
		List<BoardReplyVO> list=new ArrayList<BoardReplyVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT rno,bno,id,name,msg,group_tab,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
					+ "FROM project_reply "
					+ "WHERE bno=? "
					+ "ORDER BY group_id DESC, group_step ASC";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BoardReplyVO vo=new BoardReplyVO();
				vo.setRno(rs.getInt(1));
				vo.setBno(rs.getInt(2));
				vo.setId(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setMsg(rs.getString(5));
				vo.setGroup_tab(rs.getInt(6));
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
	//댓글 수정
	public void replyUpdate(int rno, String msg) {
		try {
			conn=CreateConnection.getConnection();
			String sql="UPDATE project_reply "
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
	//댓글 삭제
	public void replyDelete(int rno) {
		try {
			conn=CreateConnection.getConnection();
			//일괄처리 : 트랜젝션
			conn.setAutoCommit(false);
				//sql 문장이 3단계로 들어가고 각각 commit 실행 -> 중간에서 에러 시 commit 적용되는 것 방지
			String sql="SELECT root,depth "
					+ "FROM project_reply "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rno);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int root=rs.getInt(1);
			int depth=rs.getInt(2);
			rs.close();
			
			if(depth==0) {
				sql="DELETE FROM project_reply "
						+ "WHERE rno=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, rno);
				ps.executeUpdate();
			} else {
				String msg="삭제된 게시물입니다";
				sql="UPDATE project_reply "
						+ "SET msg=? "
						+ "WHERE rno=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, msg);
				ps.setInt(2, rno);
				ps.executeUpdate();
			}
			conn.commit(); //정상수행 시 실행될 commit
		} catch(Exception ex) {
			ex.printStackTrace();
			try {
				conn.rollback();
			} catch(Exception e) {}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch(Exception e) {}
			CreateConnection.disConnection(conn, ps);
		}
	}
	//대댓글 작성
	public void replyReplyInsert(int root, BoardReplyVO vo) {
		try {
			conn=CreateConnection.getConnection();
			conn.setAutoCommit(false); 
			String sql="SELECT group_id,group_step,group_tab "
					+ "FROM project_reply "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, root);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			
			sql="UPDATE project_reply "
					+ "SET group_step=group_step+1 "
					+ "WHERE group_id=? "
					+ "AND group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate();
			
			sql="INSERT INTO project_reply(rno,bno,id,name,msg,group_id,group_step,group_tab,root) "
					+ "VALUES(pr_rno_seq.nextval,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getBno());
			ps.setString(2, vo.getId());
			ps.setString(3, vo.getName());
			ps.setString(4, vo.getMsg());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, root);
			ps.executeUpdate();
			
			sql="UPDATE project_reply "
					+ "SET depth=depth+1 "
					+ "WHERE rno=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, root);
			ps.executeUpdate();
			
			conn.commit();
		} catch(Exception ex) {
			try {
				conn.rollback();
			} catch(Exception e) {}
			ex.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch(Exception e) {}
			CreateConnection.disConnection(conn, ps);
		}
	}
}
