<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.vo.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.SeoulDAO"/>
<%
	String strPage=request.getParameter("page");
	if(strPage==null) strPage="1";
	int curpage=Integer.parseInt(strPage);
	
	ArrayList<SeoulVO> list=dao.seoulListData(curpage, "shop");
	
	final int BLOCK=5;
	int totalpage=dao.seoulTotalPage("shop");
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
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
			        <img src="<%=vo.getPoster() %>" style="width:260px;height:260px">
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
	<div class="row">
		<div class="text-center">
		<ul class="pagination">
		<%
			if(startPage>1){
			%>
				<li><a href="../main/main.jsp?mode=3&page=<%=startPage-1 %>">&lt;</a></li>
			<%
			}
			for(int i=startPage;i<=endPage;i++){
			%>
				<li <%= i==curpage?"class=active":"" %>><a href="../main/main.jsp?mode=3page=<%=i %>"><%=i %></a></li>
			<%
			}
			if(endPage<totalpage){
			%>
				<li><a href="../main/main.jsp?mode=3&page=<%=endPage+1 %>">&gt;</a></li>
			<%
			}
		%>
		</ul>
		</div>
	</div>
</body>
</html>