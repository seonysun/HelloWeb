<%--
0. C/S : Client / Server
	Client : 웹, 앱에서 요청 전송
	Server : 요청 받아서 처리
	- Servlet : Server + Applet, 자바 웹 프로그래밍 기술
		- 순수 자바 -> 변경 시마다 컴파일
		- JSP : 스크립트 형식으로 제작(자바 + HTML)
			- 보안 취약, 재사용성 없음, 반복 코딩 많이 존재
		- MVC : 가장 최근에 주로 사용
			- M : 백엔드 Model
			- V : 프론트엔드 HTML, JSP
			- C : 연결 Controller

1. JSP : Java Server Page : 서버에서 실행되는 자바 파일
	- 자바 + HTML
		- 자바 : 데이터 관리(오라클 연동)
		- HTML : 브라우저에 출력
	cf. JSTL : JSP Standard Tag Library

2. JSP 동작 과정
	- 서버에서 파일 실행 시 url로 경로명 설정 -> 경로명 마지막에는 항상 파일명 첨부
		- HTML, XML, JSON은 자동으로 번역되어 브라우저에서 실행됨
		- JSP는 자동으로 변역되지 않음 
			-> HTML로 변환 후 서버에 전송해야 함(톰캣 역할)
		- 일반 자바 파일은 class 파일만 있어도 가능, JSP 파일은 파일 전체가 필요
	1) 사용자 브라우저 주소창에서 요청 : URL
		http://localhost/JSPBasicProject2/jsp/basic1.jsp
											  ---------- 파일명(파일의 확장자 마음대로 변경 가능)
	2) Web Server에서 파일을 받음
		- .html / .xml인 경우 자동으로 번역되어 실행
		- .html / .xml이 아닌 경우 톰캣으로 전송
	3) 톰캣에서 파일 찾기
		- 파일을 찾은 경우
			3-1) basic_2.jsp -> basic_2_jsp.java : 해당 파일 서블릿 파일(웹에서 실행하는 자바 파일)로 변경
			3-2) basic_2_jsp.java -> basic_2_jsp.class : 컴파일
			3-3) JVM에서 .class 파일 로딩
			3-4) 인터프리터 통해서 한 줄씩 번역
				  -> out.write("<html>") -> <html>
		- 파일이 없는 경우 : 404 에러
	4) 메모리에 저장된 HTML 브라우저가 읽어서 출력

3. JSP 생명 주기
	_jspInit() ===> 코딩은 불가능 (web.xml)
	     |
	_jspService() ===> JSP에서 코딩 내용이 첨부 (포함) ==> 브라우저에 실행되는 화면 UI
	     | *** JSP는 클래스가 아니라 메소드 영역이다
	_jspDestory() ===> 새로고침, 파일이동 ==> 자동으로 메모리에서 해제
	--------------
	   /a.jsp ===> new ==> 메모리 해제
	   ----------------------------- 현재 위치에서 작업 (자바스크립트) => Ajax, Vue, React
		 a.jsp
		   ↓
		 public class a_jsp extends HttpServlet {
		 	public void _jspInit() {}
		 	public void _jspDestory() {}
		 	public void _jspService(HttpServletRequest request, HttpServletResponse response) {
		 		JSP에서 코딩된 내용의 첨부
		 	}
		 	화면이동, 새로고침 ==> request, response => 초기화
		 	모든 JSP는 request를 따로 가지고 있다
		 }
		 
		 예) => a.jsp =========> b.jsp ==========> c.jsp
		 				 id				   |
		 				 			    id = null
		 <html>
		 <body>
		  <% String name="hong"; %>
		 </body>
		 </html> 
		 ------------------------------ _jspService 메소드안에 들어감
		 out.write("<html>")				 			    
		 out.write("<body>")				 			    
		 String name="hong"
		 out.println(name)				 			    
		 out.write("</body>")				 			    
		 out.write("</html>") 				 	
	
4. Java/HTML의 구분 -> 스크립트 요소
	1) Java 영역
		(0) 지시자 : <%@ %>
		(1) 선언문 : <%! %>
			- 일반 자바 코딩 영역 -> 자바 문법 적용
				ex. 자바 주석(//, /* */), 명령문 종료 시 ;
			<%!
				변수 선언(멤버변수)
				메소드 선언
			%>
		(2) 스크립트릿 : <% %>
			- 일반 자바 코딩 영역 -> 자바 문법 적용
			<%
				변수 선언(지역변수)
				제어문
				연산자
				메소드 호출
			%>
		(3) 표현식 : <%= %>
			- 브라우저에 자바 관련 내용 출력 -> 변수값 출력
			<%= 변수%>
		(4) 주석 : <%-- - -%>
		cf. 주의사항
			- 표현식에서는 ; 사용하지 않음
			- 열고 닫기 명확하게(중첩 불가)
			- <%@ page language="java" contentType="text/html; charset=UTF-8"
			      pageEncoding="UTF-8"%>
			   -> "" 필수, 속성 확인 주의
	2) HTML 영역 : <%@ <% <%=를 제외한 나머지 영역
	
5. 내장객체 : 미리 객체 생성 후 사용할 수 있도록 함
	- 사용자 요청값을 받는 경우 클라이언트 정보(*) : request (HTTPServletRequest)
	- 사용자 요청 처리 후 응답, 화면 변경(*) : response (HTTPServletResponse)
	- 서버에 필요한 데이터 저장(*) : session (HTTPSession)
	- 서버 정보 관리(*) : application (ServletContext)
	- 출력 버퍼 관리(메모리 관리) : out (JspWriter)
	- 환경 설정(web.xml) : config (ServletConfig)
	- 예외 처리 : exception (Exception)
	- 내장 객체 관리(*) : pageContext (PageContext)
						-> 화면 변경 : redirect, forward
	- JSP 자신의 객체 : page (Object)

6. JSTL/EL
7. MVC(**)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 선언식 --%>
<%!
	String name="홍길동"; //멤버변수
	public int add(int a,int b){
		return a+b;
	}
	public int minus(int a,int b){
		return a-b;
	}
%>
<%-- 아래 코딩되는 내용은 _jspService() 매소드 안에 들어감 --%>
<!DOCTYPE html><%-- 앞에 out.println 생략 -> Java에서 HTML 문서 형태로 메모리에 출력한 다음 브라우저에서 출력 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container{
	width: 960px;
	margin: 0px auto;
}
h1#gugu{
	text-align: center;
}
</style>
</head>
<body>
<%-- 표현식 --%>
	<h1><%=name %></h1>
		<%-- 자바영역 안에 HTML이 들어오면 안되므로 계속 <%%>로 구분 --%>
	<%
		int a=10;
		int b=20;
		int c=add(a,b);
		int d=minus(a,b);
	%>
	<h3><%=c %></h3>
	<h3><%=d %></h3>
	
	<%
		String name1="홍길동"; /* 지역변수 */
		out.println(name1);
	%>
	<%=name1 %>
	<p>
	
	<%
		for(int i=1;i<=10;i++){
	%>
	<b><%=i %></b><br>
	<%	
		}
	%>
	
	<div class="container">
		<h1 id=gugu>구구단</h1>
		<table width=700 border=1 bordercolor=black>
			<tr bgcolor="orange">
			<%
				for(int i=2;i<=9;i++){
			%>
				<th><%=i+"단" %></th>
			<%
				}
			%>
			</tr>
			<% 
				for(int i=1;i<=9;i++){
			%>
				<tr>
				<%
					for(int j=2;j<=9;j++){
				%>
					<td align=center><%=j+"*"+i+"="+(j*i) %></td>
				<%
					}
				%>
				</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>