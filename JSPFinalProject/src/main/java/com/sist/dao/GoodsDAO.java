package com.sist.dao;
import java.util.*;
import java.sql.*;
import com.sist.vo.*;

public class GoodsDAO {
	private Connection conn;
	private PreparedStatement ps;
	//전체 목록 출력
	public ArrayList<GoodsVO> goodsAllListData(int page){
		ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(goods_all ga_no_pk)*/no,goods_name,goods_poster "
					+ "FROM goods_all)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
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
	//페이징 전체페이지
	public int goodsAllTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_all";
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
	//특가
	public ArrayList<GoodsVO> goodsSpListData(int page){
		ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(goods_special gs_no_pk)*/no,goods_name,goods_poster "
					+ "FROM goods_special)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
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
	public int goodsSpTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_special";
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
	//베스트
	public ArrayList<GoodsVO> goodsBestListData(int page){
		ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(goods_best gb_no_pk)*/no,goods_name,goods_poster "
					+ "FROM goods_best)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
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
	public int goodsBestTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_best";
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
	//신상품
	public ArrayList<GoodsVO> goodsNewListData(int page){
		ArrayList<GoodsVO> list=new ArrayList<GoodsVO>();
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT no,goods_name,goods_poster,num "
					+ "FROM (SELECT no,goods_name,goods_poster,rownum as num "
					+ "FROM (SELECT /*+ INDEX_ASC(goods_new gn_no_pk)*/no,goods_name,goods_poster "
					+ "FROM goods_new)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1;
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setNo(rs.getInt(1));
				vo.setGoods_name(rs.getString(2));
				vo.setGoods_poster(rs.getString(3));
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
	public int goodsNewTotalPage() {
		int total=0;
		try {
			conn=CreateConnection.getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) FROM goods_new";
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
}
