<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		</div>
	</div>
</body>
</html>