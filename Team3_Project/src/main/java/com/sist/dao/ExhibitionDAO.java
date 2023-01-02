package com.sist.dao;
import java.util.*;
import java.sql.*;

public class ExhibitionDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String url="jdbc:oracle:thin:@localhost:1521:XE";
   public ExhibitionDAO() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (Exception e) {}
   }
   public void getConnection() {
      try {
         conn=DriverManager.getConnection(url,"hr","happy");
      } catch (Exception e) {}
   }
   public void disConnection() {
      try {
         if(ps!=null) ps.close();
         if(conn!=null) conn.close();
      } catch (Exception e) {}
   }
   public void pictureDetailInsert(PictureVO vo) {
      try {
         getConnection();
         String sql="INSERT INTO project_picture_3(ppno,image,title,name,content) "
               + "VALUES(pp_ppno_seq_3.nextval,?,?,?,?)";
         ps=conn.prepareStatement(sql);
         ps.setString(1, vo.getImage());
         ps.setString(2, vo.getTitle());
         ps.setString(3, vo.getName());
         ps.setString(4, vo.getContent());
         ps.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         disConnection();
      }
   }
   public void ExhibitionDetailInsert(ExhibitionVO vo) {
      try {
         getConnection();
         String sql="INSERT INTO project_exhibition_3(peno,poster,title,title2,kind,period,loc,loc2,area,area2,item,host,url,price,time,hashtag,content) "
               + "VALUES(pe_peno_seq_3.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
         ps=conn.prepareStatement(sql);
         ps.setString(1, vo.getPoster());
         ps.setString(2, vo.getTitle());
         ps.setString(3, vo.getTitle2());
         ps.setString(4, vo.getKind());
         ps.setString(5, vo.getPeriod());
         ps.setString(6, vo.getLoc());
         ps.setString(7, vo.getLoc2());
         ps.setString(8, vo.getArea());
         ps.setString(9, vo.getArea2());
         ps.setString(10, vo.getItem());
         ps.setString(11, vo.getHost());
         ps.setString(12, vo.getUrl());
         ps.setString(13, vo.getPrice());
         ps.setString(14, vo.getTime());
         ps.setString(15, vo.getHashtag());
         ps.setString(16, vo.getContent());
         ps.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         disConnection();
      }
   }
}