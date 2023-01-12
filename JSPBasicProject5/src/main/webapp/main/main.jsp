<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.change.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	//화면 변경에 대한 요청값 받기
	String mode=request.getParameter("mode");
	//첫번째 실행 default 지정
	if(mode==null) mode="0";
	String jsp=JspChange.change(Integer.parseInt(mode));
		//switch-case문 사용하는 것보다 메소드로 지정해서 사용하기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e7b9d67490320db6911ad3cb1c1e9e18&libraries=services"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 100px;
}
.row{
	width: 960px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<%-- 메뉴 : include(pageContext) --%>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<jsp:include page="<%=jsp %>"></jsp:include>
	</div>
</body>
</html>