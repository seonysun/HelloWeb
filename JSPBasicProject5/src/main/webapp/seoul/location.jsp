<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.vo.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.SeoulDAO"/>
<%
	String strPage=request.getParameter("page");
	if(strPage==null) strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	ArrayList<SeoulVO> list=dao.seoulListData(curpage, "location");
	
	//페이지 나누기 블록 설정(5페이지씩 나누기)
	final int BLOCK=5;
	int totalpage=dao.seoulTotalPage("location");
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		// curpage=1~5 -> startPage=1
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		// curpage=1~5 -> endPage=5
	if(endPage>totalpage) endPage=totalpage;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
	<%
		for(SeoulVO vo:list){
		%>
			<div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="<%=vo.getPoster() %>" style="width:260px;height:200px">
			        <div class="caption">
			          <p style="font-size:10px;font-weight:bold"><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
  			</div>
		<%
		}
	%>
	</div>
	<%-- 블록별 페이지 나누기(<< < 1~10 > >>) --%>
	<div class="row">
		<div class="text-center">
		<ul class="pagination">
		<%
			if(startPage>1){
			%>
		 		<li><a href="../main/main.jsp?mode=1&page=<%=startPage-1 %>">&lt;</a></li>
			<%	
			}
			for(int i=startPage;i<=endPage;i++){
		 	%>
		 		<li <%= i==curpage?"class=active":"" %>><a href="../main/main.jsp?mode=1&page=<%=i %>"><%=i %></a></li>
		 				<!-- 현재페이지만 활성화 -->
		  	<%
		  	}
			if(endPage<totalpage){
			%>
		 		<li><a href="../main/main.jsp?mode=1&page=<%=endPage+1 %>">&gt;</a></li>
			<%	
			}
		%>
		</ul>
		</div>
	</div>
</body>
</html>