<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>request 메소드</h1>
	서버 주소: <%=request.getServerName() %><br>
	서버 포트번호: <%=request.getServerPort() %><br>
		<!-- 서버포트번호 80은 생략 가능(http:80) -->
	URL: <%=request.getRequestURL() %><br>
	URI(요청 경로): <%=request.getRequestURI() %><br>
	루트 경로: <%=request.getContextPath() %><br>
	IP: <%=request.getRemoteAddr() %><br>
	요청 문장: <%=request.getQueryString() %><br>
		<!-- URL 뒤에 ? 추가 후 요청값 입력 가능 -->
</body>
</html>