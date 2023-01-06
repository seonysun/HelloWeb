<%-- Ch 6
1. 지시자
	1) page : jsp 파일에 대한 정보
		- <%@ page 속성=값 .. %>
		- 지정된 속성만 사용 가능(한번만 사용 가능), 사용자 정의 없음
			- contentType(*) : 브라우저의 파일 형식 정보
				- defalut : contentType="text/html; charset=ISO-8859-1"
							-> 기본 ASC(1byte), 한글 깨지므로 charset 변경 필요
							-> "text/html;charser=UTF-8"
	      			        cf. XML
	      			              "text/xml;charset=UTF-8"
	      			            JSON
	      			              "text/plain;charset=UTF-8"
			- import(*) : 자바 라이브러리 가지고 올 때 사용
				- java.lang, javax.servlet 생략 가능
			- buffer(*) : 출력 버퍼 -> jsp 실행 시 html 출력 -> 브라우저에서 이를 읽음
				- default : 8kb -> 필요 시 용량 증가 필요
			- errorPage(*) : jsp에서 에러 발생 시 이동할 페이지 지정
				- 서버에서 에러 시 코드
					404 : 파일이 없는 경우
					500 : 자바 소스파일 에러
					415 : 지원하지 않는 한글 변환 코드 사용
					400 : bad request -> Spring에서 주로 발생
							- JSP는 개발자 직접 처리, Spring은 Spring에서 해결
					403 : 접근 거부(보안) -> Spring에서 주로 발생
					200 : 정상 수행
			- info : 파일 정보(수정일, 작성자)
			- language : jsp 안에서 다른 언어 사용 가능
				- default : java
			- padeEncoding : 한글 변환 코드
				- default : EUC-KR
				- 리눅스, 깃 : UTF-8
			- autoFlush : 새로고침, 페이지 이동 -> 출력 버퍼 내용 초기화 -> true
			- isErrorPage : 사용(종류별로 에러 처리)
			- session : 서버에 정보 저장 -> true
	2) taglib : 태그 사용(라이브러리 설정) -> 제어문, URL, Format, String 
		- <%@ taglib 속성=값 .. %>
	3) include : JSP 특정 위치에 다른 JSP 포함
		- <%@ include 속성=값 .. %>
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" errorPage="error.jsp"
    pageEncoding="UTF-8" info="2023-01-06 홍길동"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int a=10/0;
	%>
</body>
</html>