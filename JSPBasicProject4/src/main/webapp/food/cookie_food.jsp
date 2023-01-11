<%--
1. Cookie -> 최근 방문, 최근검색어, 장바구니, 자동로그인
	- 내장 객체 아님 -> 직접 생성해서 사용 필요
	- 사용 순서
		1) 생성 : Cookie 쿠키명=new Cookie(key,value);
			- key : 중복 저장 불가 -> 같은 클릭에 대한 저장은 1개만 생성
					getName()
			- value : getValue()
		2) 저장 기간 설정 : setMaxAge(초); => 60*60*24
		3) 저장 위치 설정 : setPath("/")
		4) response 이용 -> 클라이언트로 전송(서버 아님, 각각의 컴퓨터에 저장)
			: response.addCookie(쿠키명)
		5) 삭제 : setMaxAge(0);
	- 특징
		- 문자열만 저장 가능
		- 보안 취약
		- response는 한 개의 JSP 파일에서 한번만 전송이 가능하므로 개별 파일로 관리 필요
			-> 모든 JSP는 클라이언트로 HTML 전송하고 있음
			-> HTML 없이 Cookie만 전송할 JSP 파일 필요
	- session과 비교
		- 저장 공간 -> 모든 JSP에서 사용 가능
			- 쿠키 : 클라이언트 브라우저
			- 세션 : 서버 메모리
		- 생성 시 request로 생성
			-> request.getCookie(), request.getSession()
		- 저장 데이터
			- 쿠키 : 문자열
			- 세션 : Object
		- Map 형식으로 저장 -> key가 동일할 시 덮어쓰기(중복 불가)

2. Session -> 로그인, 댓글(ID), 장바구니(ID) 
	- session에 저장된 정보가 많아질수록 브라우저가 늦어짐
  		-> 계속해서 유지할 데이터만 선택적으로 session에 저장, 다른 데이터는 request로 받아서 db에 저장
	  	- request -> 파일(페이지)마다 각각 따로 가지고 있음
	  		- db에 전송 후에는 값이 필요없는 경우
	  		ex. 게시판 글쓰기 데이터
	  	- session -> 서버에 저장되어 모든 파일(페이지)에서 공유 가능
	  		- 브라우저 종료시까지 값이 필요한 경우
	  		ex. ID 정보
	1) 저장(*) : setAttribute(key,value)
		- 키 : 문자열, 값 : Object
	2) 저장 기간 설정 : setMaxInactiveInterval(초); => default 30분
	3) 읽기(*) : getAttribute(키)
	4) 삭제
		- 부분 삭제(*) : removeAttribute(키)
		- 전체 삭제 : invalidate() -> 로그아웃
	5) 확인 -> 값을 읽어서 null
	6) 처음 생성 확인 isNew()
	7) 각 브라우저마다 생성된 sessionId 확인 : getId() 
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//사용자 전송 데이터 -> submit 버튼, a 태그(? 뒤에 입력값)
	String fno=request.getParameter("fno");
	//브라우저에 저장
	Cookie cookie=new Cookie("f"+fno,fno);
	cookie.setMaxAge(60*60*24);
	cookie.setPath("/");
	response.addCookie(cookie);
	//상세보기로 이동
	response.sendRedirect("../pageContext_include/main.jsp?mode=2&fno="+fno);
%>