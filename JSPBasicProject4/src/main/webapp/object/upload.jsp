<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<form method=post action="upload_ok.jsp" enctype="multipart/form-data">
												<!-- 업로드 프로토콜 필수 -->
			<input type=file size=20 name=upload>
			<input type=submit value="전송">
		</form>
	</center>
</body>
</html>