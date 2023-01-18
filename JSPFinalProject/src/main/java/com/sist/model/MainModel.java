package com.sist.model;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

import com.sist.dao.*;
import com.sist.vo.*;

@Controller 
	//구분자를 올려줘야 Controller가 인식해서 DispatcherServlet이 찾을 수 있음
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
		FoodDAO dao=new FoodDAO();
		ArrayList<CategoryVO> list=dao.foodCategoryData();
		request.setAttribute("list", list);
		//include할 파일명 전송
		request.setAttribute("main_jsp", "../main/home.jsp");
							//main 안에 실행되는 include 파일 지시자
		return "../main/main.jsp";
	}
}
