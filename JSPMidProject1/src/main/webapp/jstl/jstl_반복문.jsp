<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%--
1. JSTL : JSP Standard tag lib, 태그형 라이브러리
	- XML 형식으로 제작
		- 대소문자 구분
		- 여는 태그와 닫는 태그 주의
		- 속성값에 "" 필수
	1) core : 제어문
		<c:forEach> : 반복문
			- <c:forEach var="변수명" begin="시작" end="끝" step="간격">
			- step=1인 경우 생략 가능
		<c:forTokens>
			- <c:forTokens var="변수명" items="값" delims="구분자">
			- 값 하나의 키로 묶어서 설정하면 나열하지 않고 사용 가능 
		<c:if> : 조건문
			- <c: if test="조건문">
			- else 문장 없음
		<c:choose> : 다중 조건문
		<c:set> : 변수 설정 -> 사용 빈도 적음(주로 request로 set)
			- <c:set var="키" value="값">
				= request.setAttribute("키","값")
		<c:out> : 자바스크립트에서 출력
		<c:redirect url=""> : response.sendRedirect("url")
	2) fmt : 날짜 숫자 변환
	3) fn : Spring 메소드
	4) sql : DAO
	5) xml : OXM
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>일반 for문</h1>
	<%
		for(int i=1;i<=10;i++){
	%>
		<%=i %>&nbsp;
	<%
		}
	%>
	<p>
	<c:forEach var="i" begin="1" end="10" step="1">
		${i }&nbsp;
	</c:forEach>
	
	<h1>for-each</h1>
<%
	ArrayList<String> list=new ArrayList<String>();
	list.add("홍길동");
	list.add("심청이");
	list.add("박문수");
	list.add("강감찬");
	list.add("이순신");
	
	request.setAttribute("list", list);
%>
	<ul>
	<%
		for(String name:list){
	%>
		<li><%=name %></li>
	<%
		}
	%>
	<p>
	<c:forEach var="name" items="${list }">
		<li>${name }</li>
	</c:forEach>
	</ul>
	
	<h1>forTokens</h1>
<%
	String color="red,blue,green,yellow,black";
%>
	<%
		StringTokenizer st=new StringTokenizer(color,",");
		while(st.hasMoreTokens()){
	%>
		<%=st.nextToken() %><br>	
	<%
		}
	%>
	<p>
	<c:forTokens var="color" items="red,blue,green,yellow,black" delims=",">
		${color }<br>
	</c:forTokens>
	
	<h1>&lt;set var&gt;</h1>
<%
	String name="홍길동";
%>
	<c:set var="name" value="<%=name %>"/>
	${name }<br>
	<c:out value="${name }"/>
</body>
</html>