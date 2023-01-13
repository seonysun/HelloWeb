<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*, com.sist.vo.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<%
	String no=request.getParameter("no");
	DataBoardVO vo=dao.dataBoardDetailData(Integer.parseInt(no));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 800px;
}
</style>
</head>
<body>
	<div class="row row1">
		<h1 class="text-center">내용 보기</h1>
		<table class="table">
		  <tr>
		  	<th width=20% class="text-center success">번호</th>
		  	<td width=30% class="text-center"><%=vo.getNo() %></td>
		  	<th width=20% class="text-center success">작성일</th>
		  	<td width=30% class="text-center"><%=vo.getDbday() %></td>
		  </tr>
		  <tr>
		  	<th width=20% class="text-center success">작성자</th>
		  	<td width=30% class="text-center"><%=vo.getName() %></td>
		  	<th width=20% class="text-center success">조회수</th>
		  	<td width=30% class="text-center"><%=vo.getHit() %></td>
		  </tr>
		  <tr>
		  	<th width=20% class="text-center success">제목</th>
		  	<td colspan=3><%=vo.getSubject() %></td>
		  </tr>
		  <%
		  	if(vo.getFilesize()>0){
		  	%>
			<tr>
				<th width=20% class="text-center success">첨부파일</th>
			  	<td colspan=3>
			  		<a href="../databoard/download.jsp?fn=<%=vo.getFilename() %>"><%=vo.getFilename() %></a>(<%=vo.getFilesize() %>Bytes)
			  	</td>
			</tr>
		  	<%
		  	}
		  %>
		  <tr>
		  	<td colspan=4 class="text-left" valign=top height=200>
		  		<pre style="white-space: pre-wrap;border: none;background-color: white;"><%=vo.getContent() %></pre>
		  		<!-- pre는 여는 태그와 닫는 태그 한줄에 붙여서 쓰기 -->
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan=4 class="text-right">
		  		<a href="../main/main.jsp?mode=10&no=<%=vo.getNo() %>" class="btn btn-sm btn-success">수정</a>
		  		<a href="../main/main.jsp?mode=9&no=<%=vo.getNo() %>" class="btn btn-sm btn-danger">삭제</a>
		  		<a href="../main/main.jsp?mode=5" class="btn btn-sm btn-info">목록</a>
		  	</td>
		  </tr>
		</table>
	</div>
</body>
</html>