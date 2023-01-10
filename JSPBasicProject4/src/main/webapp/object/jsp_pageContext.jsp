<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>pageContext 메소드</h1>
	<%
		String serverName=request.getServerName();
		String serverName2=pageContext.getRequest().getServerName();
	%>
	서버이름1: <%=serverName %><br>
	서버이름2: <%=serverName2 %><br>
</body>
</html>