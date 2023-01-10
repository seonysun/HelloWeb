<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="16kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>out 메소드</h1>
	<%
		int size=out.getBufferSize();
		int re=out.getRemaining();
	%>
	총 버퍼 크기: <%="total="+size %><br>
	남아있는 버퍼: <%="remain="+re %><br>
	사용중인 버퍼: <%=size-re %><br>
</body>
</html>