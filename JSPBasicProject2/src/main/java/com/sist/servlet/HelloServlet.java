package com.sist.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/* MVC, Spring의 Controller : Servlet
 * 	- 요청 --> 요청 처리(Model) => Java
 * 	- 웅답 --> 화면 출력(View) => JSP
 */
@WebServlet("/hello.do")
			//확장자, 파일명 등은 자유롭게 설정 가능 ex. HelloServlet / a.daum / a.nhn
/* 서블릿 호출
 * 	1) 브라우저에서 요청 
 * 		http://localhost/JSPBasicProject2/hello.do
 *		  -> 사용자가 URL 주소 마지막에 /hello.do 입력 시 톰캣이 HelloServlet 클래스 호출
 * 	2) WAS(톰캣) 서버에서 서블릿 클래스 찾음
 * 		cf. WAS : Web Application Server
 * 	3) init() 메소드 호출
 * 	4) service() 메소드 호출 -> HTML을 브라우저로 전송
 * 		- doGet(), doPost()
 * 	5) destroy() 메소드 호출
 * */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//init, destroy : 시스템(톰캣)에 의해 자동으로 호출 (Callback 함수)
	public void init(ServletConfig config) throws ServletException {
		//생성자 역할 -> 멤버변수 초기화, 자동 로그인, 파일 읽기
		System.out.println("서블릿 초기화 메소드 호출..");
	}
	public void destroy() {
		//자동 메모리 해제 -> 브라우저 종료, 파일 이동, 새로고침
	}

	/* HTML : 데이터 전송 -> 서버가 받아서 처리
	 * 	- GET : 데이터 전송 방식 -> URL 뒤에 데이터 묶어서 전송
	 * 		ex. http://localhost/main/main.jsp?no=10;
	 * 			 => main.jsp로 no 값 전송
	 * 		- 메소드 없으므로 매개변수 사용 불가
	 * 			-> 보안 취약
	 * 			-> 단순한 검색어, 페이지 번호 전송 등에 사용
	 * 		- doGet()
	 * 	- POST : 데이터 전송 방식 -> URL 뒤에 데이터 감춰서 전송
	 * 		- 데이터 숨김
	 * 			-> 보안 good
	 * 			-> ID, Password, 데이터가 많은 경우
	 * 		- doPost()
	 * 	- PUT : update
	 * 	- DELETE : delete
	 * 
	 * GET/POST
	 * 	- default : GET 
	 * 			-> POST가 지정되지 않은 경우 GET 호출
	 * 		- HTML
	 * 			<a>; GET only
	 * 			<form>; GET|POST
	 * 		- JavaScript
	 * 			location.href=""; GET only
	 * 			Ajax(*); type=GET|POST
	 * 			VueJS(*); axios.get()|post()
	 * 			ReactJS(*); axios.get()|post()
	 * 		- Java
	 * 			sendRedirect() GET only
	 * */
	//브라우저 화면을 출력하는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 브라우저에 보내는 형식 설정
		response.setContentType("text/html;charset=uTF-8");
											//한글 포함
								/* text/html : HTML 출력
								 * text/xml : XML 출력
								 * text/plain : JSON 출력
								 * */
		//2. 어떤 브라우저로 전송할 지 설정
		PrintWriter out=response.getWriter();
						//요청한 브라우저 정보(IP)
		System.out.println("<html>");
		System.out.println("<body>");
		System.out.println("<h1>Hello Servlet</h1>");
		System.out.println("</body>");
		System.out.println("</html>");
	}
	//브라우저 화면을 출력하는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
