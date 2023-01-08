<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   //html 파일에서 보낸 값 받기
   request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지
   String name=request.getParameter("name");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b><%=name %></b>
</body>
</html>