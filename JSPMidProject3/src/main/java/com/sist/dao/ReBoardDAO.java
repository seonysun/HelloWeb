package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;

public class ReBoardDAO {
	private Connection conn;
	private PreparedStatement ps;
	public void getConnection() {
		try {
			Context init=new InitialContext();
			Context c=(Context)init.lookup("java://comp/env");
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		} catch(Exception ex) {}
	}
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	public ArrayList<ReBoardVO> boardListData(int page){
		ArrayList<ReBoardVO> list=new ArrayList<ReBoardVO>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,group_tab,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
					+ "FROM (SELECT no,subject,name,regdate,hit,group_tab "
					+ "FROM jsp_replyBoard ORDER BY group_id DESC, group_step ASC)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ReBoardVO vo=new ReBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setGroup_tab(rs.getInt(6));
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
	public int boardRowCount() {
		int count=0;
		try {
			getConnection();
			String sql="SELECT COUNT(*) FROM jsp_replyBoard";
			ps=conn.prepareStatement(sql);
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
	public ReBoardVO boardDetailData(int no, int type) {
		ReBoardVO vo=new ReBoardVO();
		try {
			getConnection();
			String sql="";
			if(type==1) {
				//게시글 수정 시에는 조회수 증가하지 않도록 설정 -> type 숫자만 다르게 하면 model에서 재사용 가능
				sql="UPDATE jsp_replyBoard "
						+ "SET hit=hit+1 "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit "
					+ "FROM jsp_replyBoard "
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
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return vo;
	}
	public void boardInsert(ReBoardVO vo) {
		try {
			getConnection();
			String sql="INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,group_id) "
					+ "VALUES(jrb_no_seq.nextval,?,?,?,?,(SELECT NVL(MAX(group_id)+1,1) "
																//null 처리 함수
					+ "FROM jsp_replyBoard))";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public void boardReplyInsert(int pno, ReBoardVO vo) {
		try {
			getConnection();
			//1. 기존 게시글(답변 달릴 게시글) group 정보(id,step,tab) 가져오기
			String sql="SELECT group_id,group_step,group_tab "
  				  + "FROM jsp_replyBoard "
  			      + "WHERE no=?";
			ps=conn.prepareStatement(sql);
	  		ps.setInt(1, pno);
	  		ResultSet rs=ps.executeQuery();
	  		rs.next();
	  		ReBoardVO pvo=new ReBoardVO(); //답변 기존 게시글과 group 정보 동일하게 set
	  		pvo.setGroup_id(rs.getInt(1)); 
	  		pvo.setGroup_step(rs.getInt(2));
	  		pvo.setGroup_tab(rs.getInt(3));
	  		rs.close();
			
			//2. group_step 조절
	  		sql="UPDATE jsp_replyBoard "
	     		   + "SET group_step=group_step+1 "
	     		   + "WHERE group_id=? "
	     		   + "AND group_step>?";
	     	ps=conn.prepareStatement(sql);
	     	ps.setInt(1, pvo.getGroup_id()); //그룹 번호 동일(같은 그룹)
	     	ps.setInt(2, pvo.getGroup_step()); //기존 게시글 다음에 출력되므로 step(출력 순서)+1
	     	ps.executeUpdate();
			
			//3. 답변 삽입
	     	sql="INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,regdate,hit,"
	     		   + "group_id,group_step,group_tab,root,depth) "
	     		   + "VALUES(jrb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?,?,?,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, pvo.getGroup_id());
			ps.setInt(6, pvo.getGroup_step()+1); //출력 순서 +1
			ps.setInt(7, pvo.getGroup_tab()+1); //답변 위계 +1
			ps.setInt(8, pno); //답변 소속 = 기존 게시글 번호
			ps.executeUpdate();
			
			//4. 기존 게시글에 depth(포함 답변 수) 증가
			sql="UPDATE jsp_replyBoard "
					+ "SET depth=depth+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, pno);
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public boolean boardUpdate(ReBoardVO vo) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql="SELECT pwd FROM jsp_replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getNo());
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			rs.close();
			
			if(db_pwd.equals(vo.getPwd())) {
				bCheck=true;
				sql="UPDATE jsp_replyBoard "
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
			disConnection();
		}
		return bCheck;
	}
	public boolean boardDelete(int no, String pwd) {
		boolean bCheck=false;
		try {
			getConnection();
			String sql="SELECT pwd,root,depth "
					+ "FROM jsp_replyBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String db_pwd=rs.getString(1);
			int root=rs.getInt(2);
			int depth=rs.getInt(3);
			rs.close();
			
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				if(depth==0){ //답변이 없는 경우
					sql="DELETE FROM jsp_replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				} else { //답변이 있는 경우 -> 게시글 유지, 관리자 멘트 처리
					String msg="삭제된 게시물입니다";
					sql="UPDATE jsp_replyBoard "
							+ "SET subject=?,content=? "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setString(2, msg);
					ps.setInt(3, no);
					ps.executeUpdate();
				}
				//depth 감소
				if(root!=0) { //root : =0(기존 게시글), !=0(답변 게시글일 때 소속 기존 게시글 번호 부여)
					sql="UPDATE jsp_replyBoard "
							+ "SET depth=depth-1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);	//답변 소속 기존 게시글의 depth 감소
					ps.executeUpdate();
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return bCheck;
	}
}
