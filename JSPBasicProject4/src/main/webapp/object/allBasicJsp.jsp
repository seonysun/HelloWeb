<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
JSP : _jspService()안에 소스 코딩됨 (클래스가 아닌 메소드 영역 내에 코딩)
	1) WebServer가 URL 주소를 받음 -> 주소 마지막 파일명 읽기
		1-1) .xml, .html일 경우 브라우저로 바로 전송
		1-2) .jsp일 경우 WAS(Web Application Server; 톰켓,레진,제우스)로 전송
				=> a.jsp -> a_jsp.java -> a_jsp.class -> 메모리에 HTML만 출력
				- HTML 출력 시 출력 버퍼 이용(관리 클래스 : out)
	2) JSP에 대한 정보 제공 : 지시자
		<%@ page 변환코드 | 에러시 출력할 파일 지정 | 라이브러리 로딩 | 버퍼크기지정> = JSP의 시작점
			- 변환코드 : contentType="text/html;charset=UTF-8", "text/xml;charset=UTF-8", "text/plain;charset=UTF-8"
			- 에러시 : errorPage="파일명"
			- 라이브러리 : import="" -> 여러개일 때 comma(,)로 구분 또는 따로 생성
			- 버퍼 : buffer="8kb" 
		- 지시자 안의 모든 속성은 지정되어있음, 사용자 속성 없음
		- 속성="값" 
			- 따옴표 필수
			- comma(,) 없이 간격 띄우고 나열
			- 속성은 한번만 사용 가능(import 제외)
	3) JSP = 자바 + HTML(CSS,JavaScript)
		- 자바 
			<% 스크립트릿 : 일반자바 -> 지역변수, 메소드 호출, 제어문, 연산자
			<%= 표현식 : 화면에 출력 -> 변수, 메소드 리턴값 -> out.println()으로 대체 가능
			<%! 선언식 : 전역변수, 메소드 선언
		cf. JSP는 초창기 방식, 최근에는 MVC로 사용 
			-> 출력문 : ${}
			   제어문 : 태그로 생성되어있음
			   	ex. <c:forEach> : for()
			   		<c:if test=""> : if()
		- HTML : 나머지 전체
	4) 내장객체
		- request(*) : 클래스 확인 HttpServletRequest
			- 브라우저 정보/서버 정보 관리
			- 사용자 요청 데이터 관리
			- 추가 정보
		- response(*)
		- session(*)
		- out
		- pageContext
		- application
	5) 액션태그
		- <jsp:include> : 페이지의 처리 결과까지 포함(동적)
			cf. include 지시자 : 소스 내용이 텍스트로 포함(정적)
		- <jsp:useBean>
		- <jsp:setProperty>
 --%>