package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.net.*;
import com.sist.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@WebServlet("*.do")
/* 1. MVC 동작 과정
 * 	1) 사용자 요청 : URL 주소(~.do)
 * 	2) 컨트롤러가 요청 받음 : DispatcherServlet 호출
 * 	3) 서블릿 메소드 service() : 해당 RequestMapping 찾기, Model로 요청 전송(request)
 * 	4) Model : 요청에 대한 처리(RequestMapping 소속 메소드 호출)
 * 		- 데이터베이스 처리
 * 		- 크롤링
 * 		- 결과값 저장 -> request.setAttribute()
 * 	5) 컨트롤러가 request 데이터 View로 전송(forward)
 * 	6) View : request에 담긴 데이터 출력(JSP)
 * 
 *	- DispatcherServlet은 고정
 *	- Model이 가지고 있는 모든 메소드 자동 호출 => 어노테이션 invoke()
 *	- 어노테이션은 바로 아래 작성된 내용을 제어
 *		-> 클래스 구분자, 멤버변수 구분자, 메소드 구분자
 *		-> 같은 이름을 가진 어노테이션이 있는 경우 에러 발생 주의
 * 
 * 2. 사용 목적
 * 	- 보안 강화
 * 	- Front/Back 분리 -> 역할분담 가능(여러명 동시 작업, 작업 속도 향상)
 * 	- 확장성, 재사용, 변경 쉬움
 *
 * 3. Controller 역할
 *	- 사용자 요청에 맞는 Model 클래스 호출
 *		- Model 클래스 호출 방식
 *			- Controller 파일에 직접 등록 => init
 *			- XML에 등록 -> Controller 파일 수정하지 않고 가능, Spring
 *	- 요청 처리 위한 Model 메소드 호출
 *	- Model로부터 request/session 결과값 받음
 *	- JSP 찾아서 결과값 전송 => forward
 * */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//application.xml에 등록된 Model 클래스 모아둘 컬렉션
	ArrayList<String> clsList=new ArrayList<String>();
	
	/* 1. init() : 초기화 
	 * 	- 사용자에게 서비스 : JSP로 request 보내서 화면에 출력
	 * 		-> 서비스 전에 Model 클래스 가지고 있어야 함
	 * 	- Model 클래스 호출	
	 * 	- 자동 호출 메소드
	 * 
	 * 2. service() : 사용자 요청시 처리, 데이터 전송 위치 설정
	 * 	- Model이 가지고 있는 메소드 호출
	 * 	- 사용자가 구분자 보냈을 때만 호출
	 * */
	public void init(ServletConfig config) throws ServletException {
		try {
			//1. Model 클래스 등록된 XML 파일 가져오기
			URL url=this.getClass().getClassLoader().getResource(".");
				//xml의 경로명 읽을때 사용 => Mac/Window의 경로 호환
						//저장된 폴더에서 가져오면 경로명이 계속해서 달라지므로(pc에 따라) 실제 톰켓이 인식하는 폴더에서 읽어와야 함 -> "." : 현재 폴더
			File file=new File(url.toURI());
			String path=file.getPath();
			//C:\javaDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPFinalProject\WEB-INF\classes
			path=path.replace("\\", File.separator);
									//파일 구분자 자동 인식(호환) : window -> \\, mac -> /
			path=path.substring(0, path.lastIndexOf(File.separator));
			path=path+File.separator+"application.xml";
			
			//2. XML 파일에 등록된 Model 클래스 읽기 : XML 파싱
				/* XML 파싱
				 * - JAXP(DOM, SAX), JAXB(1.8)
				 * - DOM : 문서 전체를 트리 형태로 파싱하여 메모리에 올려두고 데이터 추출
				 * 		- pull 방식
				 * 		- 추가, 수정, 삭제 가능
				 * - SAX : 태그를 하나씩 읽어서 데이터만 추출
				 * 		- push 방식
				 * 		- 추가, 수정, 삭제 불가능
				 * 		- Spring, MyBatis
				 * */
			//파싱 클래스 생성
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
				//Factory 패턴 : 클래스 객체 여러개 모두 올려놓고 요청하는 객체 찾아서 적용 -> Spring
			//파싱기 생성
			DocumentBuilder db=dbf.newDocumentBuilder();
				//Jsoup(HTML Parser), DocumentBuilder(XML Parser) => 실무에서 사용하지 않는다
			//XML 파싱 -> 매모리에 트리 형태로 저장
			Document doc=db.parse(new File(path));
//			System.out.println(doc.toString());
			//최상위 태그(테이블명) 읽기 -> <beans>
			Element root=doc.getDocumentElement();
//			System.out.println(root.getTagName());
			//특정 태그(클래스 저장된 태그) 데이터 읽기
			NodeList node=root.getElementsByTagName("bean");
//			System.out.println(node.toString());
			
			//3. ArrayList에 저장
			for(int i=0;i<node.getLength();i++) {
				Element bean=(Element)node.item(i);
				String cls=bean.getAttribute("class");
				System.out.println(cls);
				clsList.add(cls);
			}
		} catch(Exception ex) {}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1. 사용자 요청 주소 확인
			String uri=request.getRequestURI();
				/*
				  /JSPFinalProject/main.do
				   ----------------------- uri
				   --------------- ------- 
				     contextPath    구분자
				*/
			
			//2. 요청 처리에 맞는 메소드 호출
			//구분자 잘라내기(요청 파악)
			uri=uri.substring(request.getContextPath().length()+1); 
			for(String cls:clsList) {
				Class clsName=Class.forName(cls); 
									//클래스 내부 메소드 이름 모르므로 forName() 이용
				//클래스 위에 @Controller가 없는 경우 제외 => Spring
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				//클래스 메모리 할당(객체 생성)
				Object obj=clsName.getDeclaredConstructor().newInstance();
				//클래스에 선언된 모든 메소드 호출
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods) {
					//어노테이션 이용해서 특정 메소드 찾기
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(uri)) { //어노테이션이 요청에 일치하는 경우
						//메소드 찾기
						String jsp=(String)m.invoke(obj, request,response);
						//JSP 전송 -> forward로 request 포함 | sendRedirect로 request 초기화
						if(jsp.startsWith("redirect:")) { //sendRedirect -> ok_jsp : Controller가 직접 처리
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						} else { //forward
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
			}
		} catch(Exception ex) {}
	}

}