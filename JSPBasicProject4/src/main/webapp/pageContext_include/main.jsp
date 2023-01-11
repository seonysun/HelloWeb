<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
  	String log_jsp="";
  	String id=(String)session.getAttribute("id");
  	if(id==null) {
  		log_jsp="login.jsp";
  	} else {
  		log_jsp="logout.jsp";
  	}
  	
  	String cate_jsp="";
  	String mode=request.getParameter("mode");
  	if(mode==null) mode="0";
  	int index=Integer.parseInt(mode);
  	switch(index){
  	case 0:
  		cate_jsp="../food/category.jsp";
  				//다른 폴더 소속일 때 경로명 주의 -> 404에러
  		break;
  	case 1:
  		cate_jsp="../food/food_list.jsp";
  		break;
  	case 2:
  		cate_jsp="../food/food_detail.jsp";
  		break;
  	}
  	
  	Cookie[] cookies=request.getCookies();
  	ArrayList<FoodVO> cList=new ArrayList<FoodVO>();
  	FoodDAO dao=new FoodDAO();
  	if(cookies!=null){
  		for(int i=cookies.length-1;i>=0;i--){
  			if(cookies[i].getName().startsWith("f")){
  				String fno=cookies[i].getValue();
  				FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
  				String poster=vo.getPoster();
  				poster=poster.substring(0, poster.indexOf("^")).replace("#","&");
  				vo.setPoster(poster);
  				cList.add(vo);
  			}
  		}
  	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.main{
	margin-top: 50px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
h1{
	text-align: center;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<%--
<script type="text/javascript">
$(function(){
	$('.radios').on('click',function(){
		let no=$(this).attr("value");
		$('#cookie_frm'+fno).submit();
	})
})
</script>
 --%>
</head>
<body>
	<%
		pageContext.include("header.jsp");
	%>
	<div class="container main">
		<div class="col-sm-3">
			<%
				pageContext.include(log_jsp);
			%>
		</div>
		<div class="col-sm-9">
			<%
				pageContext.include(cate_jsp);
			%>
		  <div style="height: 20px"></div>
		  	<h3>최근 방문 맛집&nbsp; 더보기&nbsp; <a href="../food/cookie_delete.jsp">기록지우기</a></h3>
		  <hr>
		  <form method=post action="../food/cookie_one_delete.jsp" id="cookie_frm">
			  <button class="btn btn-sm btn-danger">삭제</button>
			  <%
			  	for(int i=0;i<=cList.size();i++){
			  		if(i<5){ //5개만 출력
			  			FoodVO vo=cList.get(i);
			  %>
			  	<input type=radio name=cookie value="<%=vo.getFno() %>" class="radios">
			  	<img src="<%=vo.getPoster() %>" title="<%=vo.getName() %>" style="width: 150px;height: 150px">
			  <%		
			  		}
			  	}
			  %>
		  </form>
		</div>
	</div>
</body>
</html>