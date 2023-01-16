<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
1. EL : Spring 화면에 데이터 출력(<%= %> 대체)
	- ${ 소스 }
		- 소스는 일반변수가 들어갈 수 없음 -> 일반변수는 <%= %>에서만
		- <% request.setAttribute(키, 값) %>
		  ${ requestScope.키 }
		
2. 내장객체
	- requestScope -> request.setAttribute으로 설정된 값 반환
		- 생략 가능
	- sessionScope -> session.setAttribute으로 설정된 값 반환
		- 생략 가능, 우선 순위 request(이름 중복 시 request값 반환)
	- param
	
3. 연산자
	1) 산술연산자
		+, -, * : 숫자 연산만 가능(문자열 결합 불가 -> +=) 
				  문자열 입력 "" 
				  "" 안에 숫자 입력 시 자동 변환(공백 입력 시 오류)
				  null 값은 0으로 인식
		/, div : 정수/정수=실수
		%, mod : 나머지
	2) 비교연산자
		- 결과값 true/false -> 조건문에서 주로 사용
		- eq(==), ne(!=), lt(<), gt(>), le(<=), ge(>=)
		- 문자열, 날짜, 숫자 비교 모두 동일하게 사용
	3) 논리연산자
		- and(&&), or(||), and not
	4) 삼항연산자
		- (조건?값1:값2) : if~else
	5) Empty : 키값의 공백 여부를 물어보는 연산자
		- 주로 비교연산자로 처리, 사용 빈도 적음
 --%>
<%
	String name="홍길동";
	request.setAttribute("name", "심청이");
	session.setAttribute("name", "박문수");
	
	request.setAttribute("empty","");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	<h1>일반 변수</h1>
	이름: <%=name %>
	
	<h1>EL</h1>
	${requestScope.name }<br>
	${name }<br>
	${sessionScope.name }<br>
	${name }<br>
	
	<h1>산술연산자</h1>
	&#36;{10+10}=${10+10 }<br>
	&#36;{"10"+10}=${"10"+10 }<br>
	&#36;{null+10}=${null+10}<br>
	&#36;{"Hello"+=" EL"}=${"Hello"+=" EL" }<br>
	&#36;{10 div 3}=${10 div 3 }<br>
	&#36;{10 mod 3}=${10 mod 3 }<br>
	
	<h1>비교연산자</h1>
	&#36;{10 lt 3}=${10 lt 3 }<br>
	
	<h1>논리연산자</h1>
	&#36;{(10==10)&&(10!=10)}=${(10==10) and (10!=10) }<br>
	&#36;{(10==10)||(10!=10)}=${(10==10) or (10!=10) }<br>
	&#36;{(10==10)&& not(10!=10)}=${(10==10) and not (10!=10) }<br>
	
	<h1>Empty 연산자</h1>
	&#36;{empty "empty")=${empty "empty" }
 --%>
</body>
</html>