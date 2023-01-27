<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	request.setCharacterEncoding("UTF-8");
	String ss=request.getParameter("ss");
	List<FoodVO> list=dao.foodFindData(ss);
%>
<c:set var="list" value="<%=list %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.image').hover(function(){
		$(this).css("cursor","pointer")
	},function(){
		$(this).css("cursor","none")
	})
	$('.image').click(function(){
		let fno=$(this).attr("data-no")
		$.ajax({
			type:'get',
			url:'food_detail.jsp',
			data:{"fno":fno},
			success:function(result){
				$('.col-sm-4').html(result)
			}
		})
	})
})
</script>
</head>
<body>
	<c:forEach var="vo" items="${list }">
		<div class="col-md-4">
	    <div class="thumbnail">
	      <a href="#">
	        <img src="${vo.poster }" alt="Lights" style="width:250px" data-no="${vo.fno }" class=image>
	        <div class="caption">
	          <p>${vo.name }</p>
	        </div>
	      </a>
	    </div>
	  	</div>
	</c:forEach>
</body>
</html>