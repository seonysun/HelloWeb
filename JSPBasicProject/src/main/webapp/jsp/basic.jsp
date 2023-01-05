<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- "text/html" : text를 html로 번역 --%>
<%-- 주석
1. JSP : Java Server Page : 서버에서 실행되는 자바 파일
	- HTML + Java
	cf. 서버에서 파일 실행 시 url로 경로명 설정 -> 경로명 마지막에는 항상 파일명 첨부
		- HTML, XML, JSON은 자동으로 번역되어 브라우저에서 실행됨
		- JSP는 자동으로 변역되지 않음 -> HTML로 변환 후 서버에 전송해야 함(톰캣의 역할)
	1) 실행 과정
		(1) 사용자 요청 : URL
			http://localhost:8080/JSPBasicProject/jsp/basic1.jsp
													  ---------- 파일명 -> 파일의 확장자 마음대로 변경 가능
		(2) 톰캣이 요청을 받음
		(3) 파일 찾기
			- 파일을 찾은 경우
				3-1) jsp 파일을 자바 파일로 변경 -> basic_1_jsp.java
				3-2) 변경된 파일을 컴파일 -> basic_1.jsp.class
				3-3) JVM에서 .class 파일 로딩
				3-4) 한줄씩 번역(인터프리터)
				3-5) 메모리에 HTML만 출력
				3-6) 출력한 HTML 브라우저에서 출력 
			- 파일이 없는 경우 : 404 에러 출력
	2) 소스 코딩
		(1) Java 영역
			- <%@ page import=""%>
			- 일반 자바 코딩 영역(자바 문법 적용)
				<%
					변수 선언
					제어문
					메소드 호출
				%>
			- 변수값 출력
				<%= 변수%>
		(2) HTML 영역 : <%@ <% <%=를 제외한 나머지 영역

2. 소스
	1) 지시자
		- <%@ 속성=값 .. %>
		- page : 파일에 대한 정보
			<%@ page 속성=값 .. %>
		- taglib : 태그로 제어문, URL, Format, String 
			<%@ taglib 속성=값 .. %>
		- include : JSP 특정 위치에 다른 JSP 포함
			<%@ include 속성=값 .. %>
	2) 스크립트
		- 자바/HTML 구분
		- 자바 코딩
			- 일반 자바 : 제어문, 메소드 호출, 변수 선언(지역변수) <% %>
			- 선언문 : 메소드, 변수 선언(전역변수) <%! %>
			- 표현식 : 출력 <%= %>
	3) 내장객체 : 미리 객체 생성 후 사용할 수 있도록 함
		- 사용자 요청값을 받는 경우 클라이언트 정보(*) : request (HTTPServlerRequest)
		- 사용자 요청 처리 후 응답, 화면 변경(*) : response (HTTPServlerResponse)
		- 서버에 필요한 데이터 저장(*) : session (HTTPSession)
		- 서버 정보 관리(*) : application (ServletContext)
		- 출력 버퍼 관리(메모리 관리) : out (JspWriter)
		- 환경 설정(web.xml) : config (ServletConfig)
		- 예외 처리 : exception (Exception)
		- 내장 객체 관리(*) : pageContext (PageContext)
							-> 화면 변경 : redirect, forward
		- JSP 자신의 객체 : page (Object)
	4) JSTL/EL
	5) MVC(**)
 --%>
 <%-- 아래 코딩되는 내용은 _jspService() 매소드 안에 들어감 --%>
<!DOCTYPE html><!-- 앞에 out.println 생략 -> Java에서 HTML 문서 형태로 메모리에 출력한 다음 브라우저에서 출력 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	width: 960px;
	margin: 0px auto;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<%
	String name="홍길동";
	out.println(name);
	%>
	<%=name %>
	<p>
	<%
	for(int i=1;i<=10;i++){
	%>
	<!-- 자바영역 안에 HTML이 들어오면 안되므로 계속 <%%>로 구분 -->
	<b><%=i %></b><br>
	<%	
	}
	%>
	
	<div class="container">
		<h1>구구단</h1>
		<table width=700 border=1 bordercolor=black>
			<tr bgcolor="orange">
			<%
				for(int i=2;i<=9;i++){
			%>
				<th><%=i+"단" %></th>
			<%
				}
			%>
			</tr>
			<% 
				for(int i=1;i<=9;i++){
			%>
				<tr>
				<%
					for(int j=2;j<=9;j++){
				%>
					<td align=center><%=j+"*"+i+"="+(j*i) %></td>
				<%
					}
				%>
				</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>