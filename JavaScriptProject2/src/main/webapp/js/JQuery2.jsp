<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//hover 이벤트 처리
	$('img').hover(function(){ //if
		$(this).css('opacity',0.2)
	},function(){ //else
		$(this).css('opacity',1.0)
	})
})
</script>
<style type="text/css">
img{
	width: 150px;
	height: 220px;
}
</style>
</head>
<body>
	<img src="../js/image/1.jpg">
	<img src="../js/image/2.jpg">
	<img src="../js/image/3.jpg">
	<img src="../js/image/4.jpg">
	<img src="../js/image/5.jpg">
	<img src="../js/image/6.jpg">
	<img src="../js/image/7.jpg">
</body>
</html>