<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
3. 연산자
	1) 단항연산자 : ++, --, !
	2) 형변환연산자 
		- 자바에서 전송된 모든 값은 string으로 들어옴 -> 필요시 형변환하여 사용
		- Number(), parseInt(), String(), Boolean()
	3) 이항연산자
		- 산술연산자 : +, -, *, /, %
			- 정수/정수=실수
			- 숫자로 구성된 문자열일 경우 
				+ : 문자열결합, 나머지 : 자동으로 숫자 변환 후 연산 처리
			- 문자열+숫자=문자열
		- 비교연산자 : ===(권장), ==, !==(권장), !=, <, >, <=, >=, 결과값 boolean
			- 문자열도 사용 가능 -> 알파벳 순서에 따라 대소비교
			- 제어문, 함수, 태그 제어(이벤트 처리), 내장객체(window, document, form, location, history)
		- 논리연산자 : &&, ||, 결과값 boolean
			- && : 직렬연산자(조건이 모두 true일 때 true)
				   범위에 포함 -> 예약일, 체크인, 체크아웃
			- || : 병렬연산자(조건 중 한 개 이상이 true일 때 true)
		- 대입연산자 : =, +=, -=
		- 삼항연산자
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	//단항연산자
	let a=10
	let b=a++
	console.log("a="+a+",b="+b)
	
	a=10
	b=++a
	console.log("a="+a+",b="+b)
	
	let c=false
	c=!c
	console.log("c="+c)
	
	let d=1
	d=!d
	console.log("d="+d) //false -> 정수로도 boolean 처리 가능(모든 숫자 가능)
	
	//형변환연산자
	let e="10"
	console.log("e="+typeof e)
	console.log("e="+typeof Number(e))
	console.log("e="+typeof parseInt(e))
	console.log("e="+typeof Boolean(e)+","+Boolean(e))
	console.log("e="+typeof String(e))
	
	//산술연산자
	a=5
	b=2
	console.log("a+b="+(a+b))
	console.log("a-b="+(a-b))
	console.log("a*b="+(a*b))
	console.log("a/b="+(a/b))
	console.log("a%b="+(a%b))

	c="10"
	d='20'
	console.log("c+d="+(c+d))
	console.log("c-d="+(c-d))
	console.log("c*d="+(c*d))
	console.log("c/d="+(c/d))
	console.log("c%d="+(c%d))
	
	e="10"
	let f=20
	console.log("e+f="+(e+f))
	console.log("e-f="+(e-f))
	console.log("e*f="+(e*f))
	console.log("e/f="+(e/f))
	console.log("e%f="+(e%f))
	
	let m='A'
	let n=10
	console.log("m+n="+(m+n)) //A10
	console.log("m-n="+(m-n)) //NaN : 연산 처리 불가(처리값 없음)
	
	let k=10
	let p=0
	console.log("k/p="+(k/p)) //Infinity : 0으로 나눌 수 없음
	
	//비교연산자
	a=10
	b=5
	console.log("a===b:"+(a===b))
	console.log("a!==b:"+(a!==b))
	console.log("a>=b:"+(a>=b))
	console.log("a<=b:"+(a<=b))
	console.log("a>b:"+(a>b))
	console.log("a<b:"+(a<b))
	
	c="Hello"
	d="JavaScript"
	console.log("c===d:"+(c===d))
	console.log("c!==d:"+(c!==d))
	console.log("c>=d:"+(c>=d))
	console.log("c<=d:"+(c<=d))
	console.log("c>d:"+(c>d))
	console.log("c<d:"+(c<d))
	
	//논리연산자
	let i=(6<7) && (6===7)
	console.log("i:"+i)
	i=(6<7) || (6===7)
	console.log("i:"+i)
	
	//대입연산자
	i=10
	console.log("i:"+i)
	i+=20
	console.log("i:"+i)
	i-=20
	console.log("i:"+i)
}
</script>
</head>
<body>

</body>
</html>