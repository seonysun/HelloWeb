<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   request.setCharacterEncoding("UTF-8"); //한글 깨짐 방지
   String name=request.getParameter("name"); //html에서 보낸 값 받기
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