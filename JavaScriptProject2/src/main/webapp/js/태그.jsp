<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. 태그 제어
	1) 태그 1개
		- document.getElementById("아이디명")
		cf. Element : 태그, Attribute : 속성
			- 모든 태그명은 클래스로 인식, 속성은 멤버변수로 인식
			ex. <a href=""> a(클래스), href(멤버변수)
				let a=document.getElementById("aaa")
				a.href="" a.target
		- document.querySelector("#아이디명")
	2) 태그 여러개
		- document.getElementsByName("name명")
		- document.getElementsByClassName("클래스명")
		- document.querySelectorAll(선택자)
		
2. 이벤트
	1) select : onchange="처리함수"
	2) 버튼 : onclick="처리함수"
		- <input type=button>
		- <input type=image>
		- <input type=submit>
		- <button>
		- <a>
	3) 입력창 : onkeyup, onkeydown
	4) 모든 태그에 적용 : onmouseover, onmouseout

3. Jquery
	- MVVM 양방향 : vue -> v-model
	- MVC 단방향 : React => $ref
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	document.querySelector("h1").style.color='red' 
	document.querySelector("h1").style.backgroundColor='pink' //$("h1").css("color","red").css("backgroundColor","pink")
	document.querySelector("h2").style.color='blue'
	
	document.querySelector("#id").value="admin"
	document.getElementById("id").value="hello"
}
</script>
</head>
<body>
	<h1>Hello</h1>
	<h2>JavaScript</h2>
	<input type=text id=id size=20>
</body>
</html>