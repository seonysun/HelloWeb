<!-- 
1. 내장 객체 : 별도 생성 없이 사용 가능한 객체
	- 컨테이너가 JSP -> 서블릿 변환할 때 자동으로 생성
    - 이클립스 : 9개의 내장객체 보유
	   => public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletExceptio{
			  final javax.servlet.jsp.PageContext pageContext;
		      javax.servlet.http.HttpSession session = null;
		      final javax.servlet.ServletContext application;
		      final javax.servlet.ServletConfig config;
		      javax.servlet.jsp.JspWriter out = null;
		      final java.lang.Object page = this;
		      javax.servlet.jsp.JspWriter _jspx_out = null;
		      javax.servlet.jsp.PageContext _jspx_page_context = null;
		    
		      //JSP 코딩 위치
		  }
	cf. 웹의 핵심 : request, response, session, cookie
	1) request(**) : 요청, HttpServletRequest
		- 사용자 브라우저/서버 정보
			URL : http://localhost/JSPBasicProject3/object/basic_input.jsp
				  ---------------- ---------------------------------------
				 	  서버 정보				사용자 요청 경로(URI)
				 	  			   ----------------
				 	  			      ContextPath
			- getRequestURL()(*)
			- getRequestURI()(*)
			- getContextPath()(*)
			- getRemoteAddr()(*) : 사용자 IP
				- 조회수 설정 시 종종 사용(동일 IP는 처리 X)
			- getServerPort() : 서버 포트(80)
				cf. url의 localhost 뒤에 ':80' 생략되어있음
			- getServerInfo() : localhost
		- 요청 데이터 관리
			- getParameter() : 단일 데이터값 수신 -> 데이터형 String, 변환해서 사용
			- getParameterValues() : 다중 데이터값 수신 -> 배열
			- getParameterNames() : key값(=name값=변수명) 찾기
			- request.setCharacterEncoding("UTF-8") : 디코딩(byte[] -> String)
											  		  브라우저에서 값을 받을 때
			- URLEncoder.encoder(데이터,"UTF-8") : 인코딩(String -> byte[])
												 브라우저로 값 전송할 때
								인코딩 디코딩
			 	 cf. 브라우저(1byte) <- -> 자바(2byte)
						byte[]			  String
			cf. 데이터 전송 request
					- URL(~받는파일명)?변수명=값
						-> URL/URI 안에는 ? 포함되지 않음
					- map 형태 : key(변수명) + value(값)
					- 모든 데이터값은 String
					ex. a.jsp?no=10
						=> a.jsp
						   request.getParameter("no"); //10
						a.jsp?id=admin&pw=1234
						   request.getParameter("id"); //admin
						   request.getParameter("pw"); //1234
						a.jsp?hobby=a&hobby=b&hobby=c
						   request.detParameterValues("hobby");
		- 추가 기능 : 사용자가 보내준 데이터 + 필요한 데이터 추가해서 전송(MVC, Spring)
			- setAttribute(키,값) -> Object 첨부
			- getAttribute(키)
	2) response(**) : 응답, HttpServletResponse
	   	- JSP파일 1개에서 response 1번만 가능
	   	- getContentType() : 데이터 형식 확인
	   		- text/html(HTML), text/xml(XML), text/plain(JSON)
	   	- getCharacterEncoding : 한글 변환 코드 확인
	   	- setHeader() : 파일 업로드/다운로드
	   	- sendRedirect()(*) : 서버에서 다른 파일로 이동, GET 방식
	   		- redirect로 jsp가 변경되면 request 데이터 초기화 
	   			-> 사용자가 보내준 값 초기화
	   			-> request 데이터 유지 가능한 pageContext(forward, include) 사용
	3) pageContext(*)
		- jsp에서 사용되는 내부 객체 얻어옴 -> 사용 빈도 거의 없음, 각 내부 객체 직접 사용
			- getRequest()
			- getResponse()
			- getSession()
			- getOut()
			- getSetvletContext() : application
			- getPage()
			- getServletConfig() : config
			- getException()
		- 웹 흐름 제어(데이터 공유) -> 주로 사용
			- include("파일명") : 파일 안에 파일 삽입, 파일 이동
				-> <jsp:include page="파일명">
			- forward : 파일은 동일, 화면 이동
			  	-> <jsp:forward>
	4) session(**) : 서버에 저장, HttpSession
		- 브라우저 종료, 로그아웃 전까지 지속적으로 유지
		- 모든 JSP에서 공유 가능
		- setMaxInactiveInterval(time) : 저장 기간 설정
		- invalidate() : 해제, 로그아웃
		- setAttribute(키,값) : Object(값) 등록
		- Object getAttribute() : 등록된 값 읽기
		- isNew() : 새로 생성된 것인지 확인
	5) application(*) : 서버 관리, 데이터 공유
		- log()(*) : 서블릿 로그 파일에 데이터 기록 -> 서버 관리자만 확인할 수 있도록
		- getInitParameterNames() : web.xml에 등록된 key값 확인
		- getInitParameter("key") : web.xml에 등록된 value값 확인
		- getRealPath()(*) : 실제 경로명
		- getServerInfo() : 서버 정보
		- getMajorVersion(), getMinorVersion() : 버전 정보
	6) config : 서블릿 정보 저장, ServletConfig
		- web.xml에 저장된 초기화 정보 저장
	7) out(*) : 출력 스트림(브라우저에서 읽어가는 메모리) -> JspWriter, ServletContext
		- 출력 메모리 크기 결정 : buffer
							 -> default 8kb, 크기 page 지시자에서 설정 가능
		- getBufferSize() : 메모리 총 크기
   		- gerRemaining() : 사용 후에 남아있는 버퍼 크기
	8) page : JSP 페이지 그 자체의 객체(자바의 this) -> 거의 사용 안함
	9) exception : 예외처리 -> 거의 사용 안함, try catch로 대체
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 전송값 받기(request) -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 500px;
	margin: 0px auto;
}
.h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>개인정보 전송</h1>
		<div class="row">
		  <form method=post action="basic_output.jsp">
		  <!-- method : get/post, action : 받는 파일 지정 -->
			<table class="table">
				<tr>
				  <th class="text-center" width=20%>이름</th>
				  <td width=80%>
				  	<input type="text" name="name" class="input-sm" size=15>
				  </td>
				</tr>
				<tr>
				  <th class="text-center" width=20%>성별</th>
				  <td width=80%>
				  	<input type="radio" name="sex" checked value="남자">남자
				  	<input type="radio" name="sex" value="여자">여자
				  </td>
				</tr>
				<tr>
					<th class="text-center" width=20%>지역</th>
					<td width=80%>
						<select name="loc" class="input-sm">
							<option>서울</option>
							<option>경기</option>
							<option>부산</option>
							<option>대전</option>
							<option>제주</option>
							<!-- 태그 안에 value 속성이 있으면 value 값으로, 없으면 태그 사이 값으로 전송됨 -->
						</select>
					</td>
				</tr>
					<th class="text-center" width=20%>취미</th>
					<td width=80%>
						<input type="checkbox" value="등산" name=hobby>등산
						<input type="checkbox" value="여행" name=hobby>여행
						<input type="checkbox" value="게임" name=hobby>게임
						<input type="checkbox" value="낚시" name=hobby>낚시
						<input type="checkbox" value="독서" name=hobby>독서
					</td>
				<tr>
					<th class="text-center" width=20%>소개</th>
					<td width=80%>
						<textarea rows="8" cols="35" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit value="전송" class="btn btn-sm btn-danger">
						<!-- submit을 줘야 파일 넘길 수 있음 -->
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>