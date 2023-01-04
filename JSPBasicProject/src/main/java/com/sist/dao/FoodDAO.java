package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class FoodDAO {
	private Connection conn;
	private PreparedStatement ps;
	/* DBCP : 웹에서만 사용 가능(앱 불가)
	Servers -> 톰캣 -> server.xml -> 마지막 context 안쪽에(마지막에) 추가
	 <Resource 
          auth="Container"
          driverClassName="oracle.jdbc.driver.OracleDriver"
          username="hr"
          password="happy"
          url="jdbc:oracle:thin:@localhost:1521:xe"
          name="jdbc/oracle"
          type="javax.sql.DataSource"
          maxActive="10"
          maxIdle="10"
          maxWait="-1"
        />
	  * getConnection 새로 설정할 필요 없음, Connection 톰캣에서 자동 생성 -> 만들어진 Connection 가져와서 쓰면 됨
      * 장점 : 접속 시간 줄일 수 있음(연결 속도 향상), 보안, Connection 갯수 관리 가능(서버 쉽게 무너지지 않음)
      * DB 관리 : JDBC => DBCP => ORM(Mybatis,JPA);실무
      * 	ex. SELECT * FROM project_category WHERE cno=1;
      * 		=> CategoryVO findByCno(int cno);
      * 		INSERT INTO~
      * 		=> void save()
	*/
	public void getConnection() { //미리 만들어진 Connection 개체 가져옴
		try {
			//등록된 위치로 접속
			Context init=new InitialContext(); //JNDI : 폴더 형식 메모리 구조
			Context c=(Context)init.lookup("java://comp/env"); 
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			conn=ds.getConnection();
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void disConnection() { //재사용 위해 반환
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	public ArrayList<CategoryVO> categoryAllData(){
		ArrayList<CategoryVO> list=new ArrayList<CategoryVO>();
		try {
			getConnection();
			String sql="SELECT cno,title,subject,poster "
					+ "FROM project_food_category "
					+ "ORDER BY cno";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CategoryVO vo=new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setPoster(rs.getString(4));
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
}
