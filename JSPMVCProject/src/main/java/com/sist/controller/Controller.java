package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
/*
MVC : Model(요청 처리) View(출력) Controller(중간연결)
	- 동작 순서 : C -> M -> V
		- 클라이언트 요청 -> 컨트롤러 
			-> 모델 호출 -> DB 데이터 처리 -> 컨트롤러에 반환
			-> 데이터 뷰로 전송 -> JSP가 출력(응답)
	- Model : VO, DAO, Service, Manager
		- 사용자 요청값을 받아서 처리하고 결과값을 넘겨줌
		- 일반 자바
	- View
		- 전송받은 데이터 출력
		- JSP
	- Controller
		- 요청을 받고 모델을 이용해서 처리, 처리 결과 뷰에 전송
		- JSP / Servlet 중 선택
			- JSP : 화면 출력
			- Servlet : 기능 수행(보안 뛰어남)

*/
@WebServlet("/Controller")
	// /Controller?cmd= list|delete|insert|update
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		
	}
	
	//service = get + post 통합
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청값 받기
		String cmd=request.getParameter("cmd");
		//해당 Model 호출
		if(cmd.equals("list")) {
			ListModel model=new ListModel();
			model.execute(request);
		} else if(cmd.equals("update")) {
			UpdateModel model=new UpdateModel();
			model.execute(request);
		} else if(cmd.equals("delete")) {
			DeleteModel model=new DeleteModel();
			model.execute(request);
		} else if(cmd.equals("insert")) {
			InsertModel model=new InsertModel();
			model.execute(request);
		}
		//해당 JSP로 값 전송(request)
		RequestDispatcher rd=request.getRequestDispatcher("view/"+cmd+".jsp");
		rd.forward(request, response);
	}

}
