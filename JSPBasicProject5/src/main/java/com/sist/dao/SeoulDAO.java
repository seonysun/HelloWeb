package com.sist.dao;
import java.util.*; //ArrayList
import java.sql.*; //Connection,PreparedStatement,ResultSet
import javax.naming.*; //Context
import javax.sql.*; //DataSource
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
	 * 	- 톰켓 셋팅 : server.xml
	 * 		- xml 파일은 대소문자 구분하므로 주의
	 * 		- 속성값 지정 시 ""
	 * 		- 태그 열고 닫기 오류 발생 시 run on server 실행 불가
	 * 		<Resource
	 * 			auth="Container" : 관리자(톰켓)
	 * 			driverClassName="" : 드라이버 등록
	 * 			url=""
	 * 			username="hr"
	 * 			password="happy"
	 * 			name="jdbc/oracle" : Connection 객체 주소값 구분자로 찾기 
	 * 			type="javax.sql.DataSource" : 얻어온 주소값을 받을 클래스 설정
	 * 			maxActive="" : Connection 객체 최대 생성 갯수 설정
	 * 			maxIdle="" : 실제 메모리공간인 POOL에 저장할 갯수 설정
	 * 			maxWait="-1" : Connection 객체 부족 시 반환 대기 시간, -1=무한대
	 * 		/>
	 * */
	private static SeoulDAO dao; //싱글턴 패턴
	//미리 생성된 Connection 객체 얻기
	public void getConnection() {
		try {
			//1. POOL 안에 접근 => 탐색기 열기
			Context init=new InitialContext();
				//Context : 메모리에 저장된 데이터 관리
			//2. 루트 경로 이용 => c 드라이버에 접근
			Context c=(Context)init.lookup("java://comp/env");
									//lookup() : 이름으로 객체 찾는 메소드
			//3. Connection 객체 얻어옴 => 해당 파일 찾기
			DataSource ds=(DataSource)c.lookup("jdbc/oracle");
				//DataSource : DB에 대한 모든 정보 관리
			//4. 연결 주소 가져오기
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
	/* 메소드 생성
	 * 	1. 매개변수 설정 : 사용자로부터 어떤 값을 받을 지
	 * 	2. 리턴형 설정 : 결과값을 어떤 형태로 출력할 지
	 * 		- 목록 -> ArrayList
	 * 		- 상세보기 -> VO
	 * 		- 총 페이지 수, 검색 결과 수, 로그인처리 -> 일반 데이터형
	 * 	3. 메소드 구현
	 * */
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
