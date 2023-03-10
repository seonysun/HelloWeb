<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*, com.sist.vo.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<%
	String strPage=request.getParameter("page");
	if(strPage==null) strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	ArrayList<DataBoardVO> list=dao.dataBoardListData(curpage);
	int totalpage=(int)(Math.ceil(dao.dataBoardRowCount()/10.0));
	int count=dao.dataBoardRowCount();
	count=count-((curpage-1)*10);
			//10개씩 출력되므로 페이지 넘어갈 때마다 게시물 번호 10씩 감소
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
	    <h1 class="text-center">자료실</h1>
		<table class="table">
		  <tr>
		  	<td>
		  		<a href="../main/main.jsp?mode=7" class="btn btn-sm btn-danger">새글</a>
		  	</td>
		  </tr>
		</table>
		<table class="table">
		  <tr class="danger">
		  	<th width=10% class="text-center">번호</th>
		  	<th width=40% class="text-center">제목</th>
		  	<th width=10% class="text-center">작성자</th>
		  	<th width=20% class="text-center">작성일</th>
		  	<th width=10% class="text-center">조회수</th>
		  	<th width=10% class="text-center">첨부파일</th>
		  </tr>
		  <%
		  	for(DataBoardVO vo:list){
		  	%>
			  <tr>
			  	<td width=10% class="text-center"><%=count-- %></td>
			  									<!-- vo.getNo()로 가져오면 게시물 삭제 시 비는 번호 생김 -> 전체 갯수부터 1씩 줄여가며 출력 -->
			  	<td width=40%><a href="../main/main.jsp?mode=8&no=<%=vo.getNo() %>"><%=vo.getSubject() %></a></td>
			  	<td width=10% class="text-center"><%=vo.getName() %></td>
			  	<td width=20% class="text-center"><%=vo.getDbday() %></td>
			  	<td width=10% class="text-center"><%=vo.getHit() %></td>
			  	<td width=10% class="text-center">
			  	<%
		        	if(vo.getFilesize()>0) {
		       		%>
		       		  <a href="../databoard/download.jsp?fn=<%=vo.getFilename() %>">
		            	<img src="../images/file_icon.jpg" style="width: 20px;height: 20px" class="img-circle">
		       		  </a>
		           	<%
		        	}
		        %>
			  	</td>
			  </tr>
		  	<%
		  	}
		  %>
		</table>
		<table class="table">
		  <tr>
		  	<td class="text-right">
		  		<a href="../main/main.jsp?mode=5&page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-info">이전</a>
		  		<%=curpage %> page / <%=totalpage %> pages
		  		<a href="../main/main.jsp?mode=5&page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-warning">다음</a>
		  	</td>
		  </tr>
		</table>
	</div>
</body>
</html>