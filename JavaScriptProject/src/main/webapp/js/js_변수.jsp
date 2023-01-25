<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
1. 자바스크립트
	1) 역할 : 브라우저에서 이벤트 기능 처리 (정적 -> 동적 화면)
		- 브라우저에서 처리 : 자바	스크립트
		- 브라우저에서 출력 : HTML/CSS
		- 데이터 관리 : 자바
	- 브라우저(HTML, JavaScript) <===> 프로그램 언어(자바, 파이썬)
		-> 자바에서 전송한 데이터를 브라우저에서 받아서 동적으로 변환
			(자바스크립트는 오라클 연결 불가)
	2) 사용법
		- 내부 스크립트 : 한개의 파일에서만 사용 가능
			<head>
				<script>
					처리
				</script>
			</head>
		- 외부 스크립트 : 여러개 파일에서 사용 가능 -> 라이브러리
			.js
			원격 <script src="URL"> => import
			내부 <script src="경로명">
		- 인라인 스크립트 : 한개의 태그에서만 제어
			<a href="javascript:함수호출">
	3) 버전
		- ES5
			- var : 자동 지정 변수, 사용 범위가 명확하지 않음
		- ES6
			- let : 지역변수, 블록이 종료되면 사라짐
			- const : 상수형변수, final
			- 람다식 : 화살표 함수 -> function, return 제거
				function display(){}
				let display=function(){}
				let display=()=>{}
			- 같다(===) 같지 않다(!==)
			- ; 생략 가능(선택)
	4) 출력
		- alert() : Modal
		- console.log() : 디버그
		- document.write() : 브라우저 화면에 출력할 때
		- 태그.innerHTML="" : Ajax, VueJS, ReactJS

2. 변수
	1) 변수 선언
		- 변수의 데이터형이 없다 
			-> let/var로 통일, 값에 따라서 데이터형 자동으로 변경
		- 식별자 : 규칙 자바와 동일
			- 키워드 사용 불가
			- 특수문자 사용 가능(_, $)
			- 숫자로 시작할 수 없음
			- 공백 포함 불가
			- 대소문자 구별
				- 생성자는 대문자 사용
				- 변수, 메소드, 함수는 소문자 사용
				cf. 함수 : 단독으로 사용, 메소드 : 다른 식별자와 함께 사용

	2) 데이터형
		(1) number : 정수, 실수
			- let i=10 : int
			- let i=10.0 : double
		(2) string : 문자, 문자열
			- let i='ABC' : char	
			- let i="ABC" : string
		(3) Object : 배열[], 객체{}
			- let i={} : object
				- let i={멤버변수:값, 멤버변수:값}
				ex. let i={name:'홍길동',age:30} -> i.name, i.age
			- let i=[] : Array
				- 배열은 Object형으로 들어가므로 서로 다른 데이터형 혼합해서 사용 가능
			- let i=function(){} : function
		(4) boolean
			- let i=true :boolean
				Boolean(1)=true
				Boolean(0)=false
					cf. 0, 0.0이 아닌 수는 true
		(5) null : 값이 없는 경우
		(6) undefined : 선언이 안 된 경우
		- 데이터형 확인 -> typeof

	3) main 함수 : 시작과 동시에 자동 호출되는 함수
		- 일반 자바스크립트
			window.onload=function(){}
		- JQuery
			$(function(){})
		- VueJS
			mounted(){}
		- ReactJS
			componentDidMount(){}
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	//변수 선언
	let a=10;
	let b=10.5;
	let c='ABC';
	let d="ABC";
	let e=[1,2,3,4,5,"홍길동"];
	let f={name:"홍길동",sex:"남자",age:20};
	
	//출력 - 콘솔창
	console.log("a="+a); //System.out.println();
	console.log("b="+b);
	console.log("c="+c);
	console.log("d="+d);
	console.log("e="+e);
	console.log("f="+f.name+","+f.sex+","+f.age);
	
	//출력 - 브라우저
	document.write("a="+a+"<br>");
	document.write("b="+b+"<br>");
	document.write("c="+c+"<br>");
	document.write("d="+d+"<br>");
	document.write("e="+e+"<br>");
	document.write("f="+f+"<br>");
	document.write("<hr>");
	
	document.write("a="+typeof a+"<br>");
	document.write("b="+typeof b+"<br>");
	document.write("c="+typeof c+"<br>");
	document.write("d="+typeof d+"<br>");
	document.write("e="+typeof e+"<br>");
	document.write("f="+typeof f+"<br>");
	
	let i
	document.write("i="+typeof i+"<br>"); //undefined : 초기화가 안됨
	document.write("j="+typeof j+"<br>"); //undefined : 선언되지 않은 변수 사용
	let k='';
	document.write("k="+typeof k+"<br>"); //string
	
	let o=10
	document.write("o="+typeof o+"<br>");
	o='ABC'
	document.write("o="+typeof o+"<br>");
}
</script>
</head>
<body>

</body>
</html>