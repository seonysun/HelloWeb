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
				//????????? ?????? ????????? ????????? ???????????? ????????? ?????? -> type ????????? ????????? ?????? model?????? ????????? ??????
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
																//null ?????? ??????
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
			//1. ?????? ?????????(?????? ?????? ?????????) group ??????(id,step,tab) ????????????
			String sql="SELECT group_id,group_step,group_tab "
  				  + "FROM jsp_replyBoard "
  			      + "WHERE no=?";
			ps=conn.prepareStatement(sql);
	  		ps.setInt(1, pno);
	  		ResultSet rs=ps.executeQuery();
	  		rs.next();
	  		ReBoardVO pvo=new ReBoardVO(); //?????? ?????? ???????????? group ?????? ???????????? set
	  		pvo.setGroup_id(rs.getInt(1)); 
	  		pvo.setGroup_step(rs.getInt(2));
	  		pvo.setGroup_tab(rs.getInt(3));
	  		rs.close();
			
			//2. group_step ??????
	  		sql="UPDATE jsp_replyBoard "
	     		   + "SET group_step=group_step+1 "
	     		   + "WHERE group_id=? "
	     		   + "AND group_step>?";
	     	ps=conn.prepareStatement(sql);
	     	ps.setInt(1, pvo.getGroup_id()); //?????? ?????? ??????(?????? ??????)
	     	ps.setInt(2, pvo.getGroup_step()); //?????? ????????? ????????? ????????? ???????????? ?????? ?????? step(?????? ??????)+1 -> ????????? ?????????
	     	ps.executeUpdate();
			
			//3. ?????? ??????
	     	sql="INSERT INTO jsp_replyBoard(no,name,subject,content,pwd,regdate,hit,"
	     		   + "group_id,group_step,group_tab,root,depth) "
	     		   + "VALUES(jrb_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?,?,?,0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, pvo.getGroup_id());
			ps.setInt(6, pvo.getGroup_step()+1); //?????? ?????? +1
			ps.setInt(7, pvo.getGroup_tab()+1); //?????? ?????? +1
			ps.setInt(8, pno); //?????? ?????? = ?????? ????????? ??????
			ps.executeUpdate();
			
			//4. ?????? ???????????? depth(?????? ?????? ???) ??????
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
				if(depth==0){ //????????? ?????? ??????
					sql="DELETE FROM jsp_replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
				} else { //????????? ?????? ?????? -> ????????? ??????, ????????? ?????? ??????
					String msg="????????? ??????????????????";
					sql="UPDATE jsp_replyBoard "
							+ "SET subject=?,content=? "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, msg);
					ps.setString(2, msg);
					ps.setInt(3, no);
					ps.executeUpdate();
				}
				//depth ??????
				if(root!=0) { //root : =0(?????? ?????????), !=0(?????? ???????????? ??? ?????? ?????? ????????? ?????? ??????)
					sql="UPDATE jsp_replyBoard "
							+ "SET depth=depth-1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);	//?????? ?????? ?????? ???????????? depth ??????
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
