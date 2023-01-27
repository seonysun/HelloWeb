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
	$('h1:eq(2)').css("color","red")
			//가상선택자 -> 인덱스번호 0부터 시작
	$('h1:eq(1),h1:eq(3)').css("color","green") //여러개 동시 처리 시 comma로 나열
	$('h1:first').css("color","pink") //첫번째 요소
	$('h1:last').css("color","blue") //마지막 요소
	$('h1:even').css("backgroundColor","yellow") //홀수번째
	$('h1:odd').css("backgroundColor","magenta") //짝수번째
})
</script>
</head>
<body>
	<h1>Hello Jquery!</h1>
	<h1>Hello Jquery!</h1>
	<h1>Hello Jquery!</h1>
	<h1>Hello Jquery!</h1>
	<h1>Hello Jquery!</h1>
</body>
</html>