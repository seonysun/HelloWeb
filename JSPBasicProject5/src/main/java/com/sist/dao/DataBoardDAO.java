package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;

public class DataBoardDAO {
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
	public ArrayList<DataBoardVO> dataBoardListData(int page){
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try {
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(jsp_databoard jd_no_pk)*/ no,subject,name,regdate,hit,filesize "
					+ "FROM jsp_databoard)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=10;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				DataBoardVO vo=new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setFilesize(rs.getInt(6));
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
	public ArrayList<DataBoardVO> dataBoardDetailData(){
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try {
			getConnection();
			String sql="SELECT ";
			ps=conn.prepareStatement(sql);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	public void dataBoardInsert(DataBoardVO vo){
		try {
			getConnection();
			String sql="INSERT INTO jsp_databoard(no,name,subject,content,pwd,filename,filesize) "
					+ "VALUES(jd_no_seq.nextval,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}
	public ArrayList<DataBoardVO> dataBoardUpdate(){
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try {
			getConnection();
			String sql="SELECT ";
			ps=conn.prepareStatement(sql);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
	public ArrayList<DataBoardVO> dataBoardDelete(){
		ArrayList<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try {
			getConnection();
			String sql="SELECT ";
			ps=conn.prepareStatement(sql);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return list;
	}
}
