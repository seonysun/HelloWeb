package com.sist.model;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller 
	//구분자를 올려줘야 Controller가 인식해서 DispatcherServlet이 찾을 수 있음
public class MainModel {
	@RequestMapping("main/main.do")
	public String main_page(HttpServletRequest request, HttpServletResponse response) {
		/*
		HttpSession session=request.getSession();
		session.setAttribute("id", "hong");
		session.setAttribute("admin", "n");
		*/
		FoodDAO dao=new FoodDAO();
		ArrayList<CategoryVO> list=dao.foodCategoryData();
		request.setAttribute("list", list);
		request.setAttribute("main_jsp", "../main/home.jsp"); //include할 파일명 전송
		return "../main/main.jsp";
	}
}
