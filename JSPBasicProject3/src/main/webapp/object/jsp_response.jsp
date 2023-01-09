<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>response 메소드</h1>
	전송 방식:<%=response.getContentType() %><br>
	한글변환 코드:<%=response.getCharacterEncoding() %><br>
		<!-- EUC-KR, UTF-8 -->
</body>
</html>