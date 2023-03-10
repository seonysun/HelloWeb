<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. JQuery : 자바스크립트 라이브러리
	- 특징
		- 대부분의 브라우저에서 사용 가능
		- HTML, CSS 간단하게 조작 가능
		- 오픈 소스 
			-> 자바스크립트 표준화(형식 동일)
		- 라이브러리가 많아서 무거움, 속도 느림
		
	1) selector : 태그 선택
		$('선택자') => document.querySelector("선택자")
		
	2) 메인 함수
		$(function(){}) => window.onload=function(){}
		
	3) 태그 제어 함수
		- val() : 입력창(input, select, textarea) 입력값 선택 => value
			val() => getter
		  	val("값") => setter
		- attr() : 속성 선택 => getAttribute
			attr("속성")
			attr("속성","값")
		- text() : 태그 사이 값 선택 => innerText
			text() 
		  	text("값")
		- html() : html(태그 포함 값) 선택 => innerHTML
			html()
			html("값") 
				- JSON 배열과 주로 사용 -> 반복문으로 배열의 요소에 맞는 html 생성
				- 태그 삽입 시 html 대신 text로 입력 시 문자 취급 -> 태그 실행 불가
		- append() : 첨부
			- html()과의 비교
				html() : 기존의 내용 초기화 후 새로운 내용 추가
				append() : 기존 내용에 추가(더해짐)
							-> 기존 내용 초기화 시 empty(), remove() 사용
			- 대량 코드 첨부 시 주로 사용
		- remove() : 삭제
		- css() : css 관리
			css("속성","값") : 속성 1개 추가
	 		css({"속성":"값" ..}) : 속성 여러개 추가
		
	4) 상태 함수
		- focus() : 커서 갖다대기
		- hide() : 숨김
		- show() : 더보기 클릭 시 보여주기
			- show(fast) : 속도 이펙트
		- disable() : 비활성화
	
	5) 이벤트
		- 키보드 이벤트
			- keydown : 키를 누를 때
			- keyup : 키를 놓을 때
		
		- 마우스 이벤트
			- click : 마우스 클릭
			 	= on("click",function(){})
			- hover : 마우스 갖다댔을 때
			- mousedown : 마우스 누르고있을 때
		- 기타
			- change : 콤보박스 선택

2. 내장객체
	- window > document, location, history, screen
	1) window : open(팝업), close
	2) document : write, querySelector
	3) location : href(이동)
	4) history : back(이전), forward(다음)
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
	
	$('span').html("<font color=blue>Welcome</font>")
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
	
	<span></span>
</body>
</html>