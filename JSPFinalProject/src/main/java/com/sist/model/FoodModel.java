package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FoodModel {
	@RequestMapping("food/food_location.do")
	public String food_location(HttpServletRequest request, HttpServletResponse response) {
		try { //한글 입력값(ss) 있으므로 한글 변환 처리 필수
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String ss=request.getParameter("ss");
		if(ss==null) ss="역삼";
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		FoodDAO dao=new FoodDAO();
		ArrayList<FoodVO> list=dao.foodLocationFindData(curpage, ss);
		int totalpage=dao.foodLocationTotalPage(ss);
		request.setAttribute("ss", ss);
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("main_jsp", "../food/food_location.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		String cno=request.getParameter("cno");
		FoodDAO dao=new FoodDAO();
		ArrayList<FoodVO> list=dao.foodListData(Integer.parseInt(cno));
		CategoryVO vo=dao.categoryInfoData(Integer.parseInt(cno));
		request.setAttribute("list", list);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../food/food_list.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_detail.do")
	public String food_detail(HttpServletRequest request, HttpServletResponse response) {
		String fno=request.getParameter("fno");
		FoodDAO dao=new FoodDAO();
		FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
		String address=vo.getAddress();
		String addr1=address.substring(0,address.lastIndexOf("지"));
		addr1=addr1.trim();
		String addr2=address.substring(address.lastIndexOf("지")+3);
		request.setAttribute("vo", vo);
		request.setAttribute("addr1", addr1);
		request.setAttribute("addr2", addr2);
		//레시피, 재료, 상품, 인근 명소 정보 추가
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		return "../main/main.jsp";
	}
}
