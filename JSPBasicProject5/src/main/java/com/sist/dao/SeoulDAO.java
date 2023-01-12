package com.sist.dao;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import com.sist.vo.*;

public class SeoulDAO {
	//연결 객체
	private Connection conn;
	//오라클 송수신 객체
	private PreparedStatement ps;
	/* DBCP : 요청 시마다 생성 / 미리 연결된 객체 사용
	 * 	- 단점 : 웹에서만 사용 가능(톰켓 필요하므로) -> 도스창에서는 불가
	 * 	- 연결 실행 과정
	 * 		1. 톰켓에 의해 Connection을 10개(maxIdle="10") 생성해서 POOL(메모리 공간)에 저장
	 * 		2. POOL 안에서 미리 생성된 Connection 객체 얻어옴
	 * 		3. 얻어온 Connection 이용해 오라클 데이터 관리(CURD)
	 * 		4. 사용된 Connection POOL로 반환, 재사용
	 * 			- Connection 생성 제한 -> 메모리 절약, 연결 소요 시간 감소(속도 빠름)
	 * */
	private static SeoulDAO dao; //싱글턴 패턴
	//미리 생성된 Connection 객체 얻기
	public void getConnection() {
		try {
			//POOL 안에 접근 => 탐색기 열기
			Context init=new InitialContext();
			//루트 경로 이용 => c 드라이버에 접근
			Context c=(Context)init.lookup("java://comp/env");
									//lookup : 이름으로 객체 찾는 메소드
			//Connection 객체 얻어옴 => 해당 파일 찾기
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
			//연결 주소 가져오기
			conn=ds.getConnection();
		} catch(Exception ex) {}
	}
	//Connection 객체 사용 후 반환
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch(Exception ex) {}
	}
	//
	public ArrayList<SeoulVO> seoulListData(int page, String tab){
		ArrayList<SeoulVO> list=new ArrayList<SeoulVO>();
		try {
			getConnection(); //미리 생성된 Connection 객체 가져옴
			String sql="SELECT no,title,poster,num "
					+ "FROM (SELECT no,title,poster,rownum as num "
					+ "FROM (SELECT no,title,poster "
					+ "FROM seoul_"+tab+" ORDER BY no)) "
					+ "WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			int rowSize=20;
			int start=rowSize*(page-1)+1; //rownum은 1번부터 시작하므로 +1
			int end=rowSize*page;
			ps.setInt(1, start);
			ps.setInt(2, end);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				SeoulVO vo=new SeoulVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
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
	public int seoulTotalPage(String tab) {
		int total=0;
		try {
			getConnection();
			String sql="SELECT CEIL(COUNT(*)/20.0) "
					+ "FROM seoul_"+tab;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			total=rs.getInt(1);
			rs.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
		return total;
	}
}
