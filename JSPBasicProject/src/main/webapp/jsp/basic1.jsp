<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- "text/html" : text를 html로 번역 --%>
<%-- 주석
1. JSP : Java Server Page : 서버에서 실행되는 자바 파일
	- HTML + Java
	cf. 서버에서 파일 실행 시 url로 경로명 설정 -> 경로명 마지막에는 항상 파일명 첨부
		- HTML, XML, JSON은 자동으로 번역되어 브라우저에서 실행됨
		- JSP는 자동으로 변역되지 않음 -> HTML로 변환 후 서버에 전송해야 함(톰캣의 역할)
	- 실행 과정
		1) 사용자 요청 : URL
			http://localhost:8080/JSPBasicProject/jsp/basic1.jsp
													  ---------- 파일명 -> 파일의 확장자 마음대로 변경 가능
		2) 톰캣이 요청을 받음
		3) 파일 찾기
			- 파일을 찾은 경우
				3-1) jsp 파일을 자바 파일로 변경 -> basic_1_jsp.java
				3-2) 변경된 파일을 컴파일 -> basic_1.jsp.class
				3-3) JVM에서 .class 파일 로딩
				3-4) 한줄씩 번역(인터프리터)
				3-5) 메모리에 HTML만 출력
				3-6) 출력한 HTML 브라우저에서 출력 
			- 파일이 없는 경우 : 404 에러 출력
	- 소스 코딩
		- Java 영역
			1) <%@ page import=""%>
			2) 일반 자바 코딩 영역(자바 문법 적용)
				<%
					변수 선언
					제어문
					메소드 호출
				%>
			3) 변수값 출력
			<%= 변수%>
		- HTML 영역 : <%@ <% <%=를 제외한 나머지 영역
 --%>
<!DOCTYPE html><!-- 앞에 out.println 생략 -> Java에서 HTML 문서 형태로 메모리에 출력한 다음 브라우저에서 출력 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String name="홍길동";
	out.println(name);
	%>
	<p>
	<%
	for(int i=1;i<=10;i++){
	%>
	<!-- 자바영역 안에 HTML이 들어오면 안되므로 계속 <%%>로 구분 -->
	<b><%=i %></b><br>
	<%	
	}
	%>
</body>
</html>