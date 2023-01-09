<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 한글 변환
	request.setCharacterEncoding("UTF-8");
	//2. 데이터 받기
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String loc=request.getParameter("loc");
	String content=request.getParameter("content");
		//<input type=text|password>, <textarea>, <select> = getParameter
	String[] hobby=request.getParameterValues("hobby");
		//<input type=checkbox> = getParameterValues
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전송받은 값 출력</h1>
	이름:<%=name %><br>
	성별:<%=sex %><br>
	지역:<%=loc %><br>
	소개:<%=content %><br>
	취미:
		<ul>
		<%
		if(hobby!=null) {
			for(String h:hobby) {
		%>
			<li><%=h %></li>
		<%
			}
		} else {
		%>
			<span style="color: red">선택된 취미가 없습니다</span>
		<%
		}
		%>
		</ul>
</body>
</html>