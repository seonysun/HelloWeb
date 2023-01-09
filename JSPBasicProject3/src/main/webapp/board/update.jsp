<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String no=request.getParameter("no");
	BoardDAO dao=new BoardDAO();
	BoardVO vo=dao.boardUpdate(Integer.parseInt(no));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 600px;
	margin: 0px auto;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>수정하기</h1>
		<div class="row">
		  <form method="post" action="update_ok.jsp">
		  <!-- post: 데이터 감춰서 전송 / get: 데이터 오픈된 상태로 전송 -->
			<table class="table">
				<tr>
				  <th class="text-right warning" width=20%>이름</th>
				  <td width=80%>
				  	<input type="text" name=name size=15 class="input-sm" value="<%=vo.getName() %>">
				  	<input type=hidden name=no value="<%=vo.getNo() %>">
				  			<!-- hidden : no 데이터가 필요하지만 화면에 출력되지 않게 할 때 -->
				  </td>
				</tr>
				<tr>
				  <th class="text-right warning" width=20%>제목</th>
				  <td width=80%>
				  	<input type="text" name=subject size=50 class="input-sm" value="<%=vo.getSubject() %>">
				  </td>
				</tr>
				<tr>
				  <th class="text-right warning" width=20%>내용</th>
				  <td width=80%>
				  	<textarea rows=10 cols=50 name=content><%=vo.getContent() %></textarea>
				  </td>
				</tr>
				<tr>
				  <th class="text-right warning" width=20%>비밀번호</th>
				  <td width=80%>
				  	<input type="password" name=pwd size=10 class="input-sm">
				  </td>
				</tr>
				<tr>
				  <td colspan=2 class="text-center">
					<input type=submit class="btn btn-sm btn-info" value="수정하기">
					<input type=button class="btn btn-sm btn-warning" onclick="javascript:history.back()" value="취소">
																		<!-- 이전 화면으로 이동 메소드 -->
				  </td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>