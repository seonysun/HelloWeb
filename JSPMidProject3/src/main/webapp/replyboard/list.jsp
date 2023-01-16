<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="model" class="com.sist.model.ReBoardModel"></jsp:useBean>
<%
	model.boardListData(request, response);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 10px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div class="container">
	  <div class="row" style="text-align:center">
	  	<img src="qna1.png" style="width:300px;height:200px">
	  </div>
	  <div style="height: 5px"></div>
	  <div class="row">
	  	<table class="table">
	  	  <tr>
	  	  	<td>
	  	  		<a href="#" class="btn btn-sm btn-danger">새글</a>
	  	  	</td>
	  	  </tr>
	  	</table>
	  	<table class="table">
	  	  <tr class="warning">
	  	  	<th width=10% class="text-center">번호</th>
	  	  	<th width=45% class="text-center">제목</th>
	  	  	<th width=15% class="text-center">이름</th>
	  	  	<th width=20% class="text-center">작성일</th>
	  	  	<th width=10% class="text-center">조회수</th>
	  	  </tr>
	  	  <c:forEach var="vo" items="${list }">
		  	  <tr>
		  	  	<td width=10% class="text-center">
		  	  	
		  	  	</td>
		  	  	<td width=45%>
		  	  		<c:if test="${vo.group_tab>0 }"> <!-- group_tab : =0(기본댓글), >0(대댓글 이상) -->
		  	  			<c:forEach var="i" begin="0" end="${vo.group_tab }">
		  	  				&nbsp;&nbsp; <!-- 대댓글, 대대댓글 tab에 맞게 간격 생성 -->
		  	  			</c:forEach>
		  	  			<img src="re_icon.png">
		  	  		</c:if>
			  	  	${vo.subject }&nbsp;
		  	  		<c:if test="${today==vo.dbday }">
		  	  			<sup><img src="new.gif"></sup>
		  	  		</c:if>
		  	  	</td>
		  	  	<td width=15% class="text-center">${vo.name }</td>
		  	  	<td width=20% class="text-center">${vo.dbday }</td>
		  	  	<td width=10% class="text-center">${vo.hit }</td>
		  	  </tr>
	  	  </c:forEach>
	  	</table>
	  </div>
	</div>
</body>
</html>