<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. JQuery : 자바스크립트 라이브러리
	1) selector : 태그 선택
		$("선택자") => document.querySelector("선택자")
	2) 메인 함수
		$(function(){}) => window.onload=function(){}
	3) 태그 제어
		- val() : input, select 태그 값 선택
			val() => getter
		  	val(값) => setter
		- text() : 태그 사이 값 선택 => innerText
			text() 
		  	text(값)
		- attr() : 속성 선택
			attr("src")
			attr("src","값")
		- html() : JSON
		- append() : 태그 첨부할 때
	4) 이벤트 처리
		- click(function(){}) : 마우스 클릭
		- on("click",function(){})
		- change(function(){}) : 콤보박스 선택
		- keyup(function(){}) : 키보드에 입력
		- hover(function(){}) : 마우스 갖다댔을 때
		- mousedown(function(){}) : 마우스 누르고있을 때
	5) 디자인
	 	- css("속성","값")
	 	- css({"속성":"값" ..})
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.a').css("color","green")
	$('#b').css({"color":"red","backgroundColor":"pink"})
})
</script>
</head>
<body>
	<h1>Hello JQuery</h1>
	<h1 class="a">Hello JQuery</h1>
	<h1>Hello JQuery</h1>
	<h1 class="a">Hello JQuery</h1>
	<h1>Hello JQuery</h1>
	<h1 class="a">Hello JQuery</h1>
	<h1 id=b>Hello JQuery</h1>
</body>
</html>