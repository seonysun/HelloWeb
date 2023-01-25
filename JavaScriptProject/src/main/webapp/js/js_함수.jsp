<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
5. 함수 : 기능처리
	1) 이벤트(사용자의 요청 행위) : 버튼 클릭 onclick, 마우스 오버 onmouseover, 마우스 아웃 onmouseout, 
						    변경 onchange, 키보드 입력 시 onkeydown, 키보드 종료 onkeyup
	2) 함수 생성 방법
		- 익명의 함수 : function(){} -> callback(시스템에 의해 자동으로 호출)
					 map(function(m){})
		- 선언적 함수 : function 함수명(매개변수){}
					 let func=function(매개변수){}
					 let func=(매개변수)=>{} : ES6버전, 권장(람다식, NodeJS, VueJS, ReactJS)
	3) 함수의 속성
		-------------------------------
		리턴값  매개변수
		-------------------------------
		  O 	O    function 함수명(매개변수){
		 				return 값;
		 			 }
		-------------------------------
		  O 	X    function 함수명(){
		 				return 값;
		 			 }
		-------------------------------
		  X 	O    function 함수명(매개변수){  => void
		 			 }
		-------------------------------
		  X		X    function 함수명(){ => void
		 			 }
		-------------------------------
		- 리턴형은 서술하지 않음, 매개변수는 변수명만 서술
		- 매개변수로 함수를 넘길 수도 있음 -> Redux
		- 구성요소 
			function 함수명(){ //선언부
		 		//구현부
		 	}
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	func1()
	func2("홍길동")
	let msg=func3()
	document.write(msg+"<br>")
	let name=func4("이순신")
	document.write(name+"님 로그아웃하셨습니다.")
}
/*
function func1(){
	document.write("func1 Call... <br>")
}
function func2(name){
	document.write(name+"님 환영합니다! <br>")
}
function func3(){
	return "Hello JavaScript"
}
function func4(name){
	return name
}
*/ 
let func1=function(){
	document.write("func1 Call... <br>")
}
let func2=(name)=>{
	document.write(name+"님 환영합니다! <br>")
}
let func3=()=>{
	return "Hello JavaScript"
}
let func4=(name)=>{
	return name
}
</script>
</head>
<body>

</body>
</html>