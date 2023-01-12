<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*, com.sist.vo.*"%>
<%
	String addr=request.getParameter("addr");
	String strPage=request.getParameter("page");
	if(strPage==null) strPage="1";
	int curpage=Integer.parseInt(strPage);
	if(addr==null) addr="";
	
	FoodDAO dao=new FoodDAO();
	ArrayList<FoodVO> list=dao.foodFindData(addr,curpage);
	
	final int BLOCK=5;
	int totalpage=dao.foodFindTotalPage(addr);
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	if(endPage>totalpage) endPage=totalpage;
	
	int count=dao.foodFindCount(addr);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<form method=post action="../main/main.jsp">
			<!-- post 방식이므로 main.jsp 뒤에 ? 주고 mode 설정하면 안됨, hidden으로 감춰서 데이터 넘기기 -->
			<input type=hidden name=mode value=4>
			검색: <input type=text name=addr size=30 class="input-sm" value="<%=addr!=null?addr:"" %>">
																	 <!-- 검색어 입력 전 기본값 설정 -->
			<button class="btn btn-sm btn-danger">검색</button>
		</form>
	</div>
	<div style="height: 10px"></div>
	<div class="row">
		<div class="text-right">
			<b style="color: green;font-size:25px"><%=count %></b>개의 <%=addr %>&nbsp맛집이 검색되었습니다.
		</div>
	</div>
	<div style="height: 5px"></div>
	<div class="row">
	<%
		if(list!=null){
			for(FoodVO vo:list){
			%>
				<div class="col-md-3">
				    <div class="thumbnail">
				      <a href="../main/main.jsp?mode=6&fno=<%=vo.getFno() %>">
				        <img src="<%=vo.getPoster() %>" style="width:260px;height:200px" class="img-rounded">
				        <div class="caption">
				          <p style="font-size:9px;font-weight:bold"><%=vo.getName() %></p>
				        </div>
				      </a>
				    </div>
	  			</div>
			<%
			}
		}
	%>
	</div>
	<div class="row">
		<div class="text-center">
		<ul class="pagination">
		<%
			if(startPage>1){
			%>
		 		<li><a href="../main/main.jsp?mode=4&page=<%=startPage-1 %>&addr=<%=addr %>">&lt;</a></li>
			<%	
			}
			for(int i=startPage;i<=endPage;i++){
		 	%>
		 		<li <%= i==curpage?"class=active":"" %>><a href="../main/main.jsp?mode=4&page=<%=i %>&addr=<%=addr %>"><%=i %></a></li>
		 				<!-- 현재페이지만 활성화 -->
		  	<%
		  	}
			if(endPage<totalpage){
			%>
		 		<li><a href="../main/main.jsp?mode=4&page=<%=endPage+1 %>&addr=<%=addr %>">&gt;</a></li>
			<%	
			}
		%>
		</ul>
		</div>
	</div>
</body>
</html>