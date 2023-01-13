<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no=request.getParameter("no");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 300px;
}
</style>
</head>
<body>
	<div class="row row1">
		<h1 class="text-center">삭제하기</h1>
	  <form method=post action="../databoard/delete_ok.jsp">
		<table class="table">
		  <tr>
		  	<td width=40% class="text-center">비밀번호</td>
		  	<td width=60%>
		  		<input type=hidden name=no value="<%=no %>">
		  		<input type=password name=pwd size=15 class="input-sm" required>
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan=2 class="text-center">
		  		<input type=submit value="삭제" class="btn btn-sm btn-danger">
		  		<input type=button value="취소" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
		  	</td>
		  </tr>
		</table>
	  </form>
	</div>
</body>
</html>