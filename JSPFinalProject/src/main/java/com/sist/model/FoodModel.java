package com.sist.model;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		CommonsModel.footerData(request);
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
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("food/food_before_detail.do") //쿠키 저장
	public String food_before_detail(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String user=""; //쿠키 아이디별로 구분하기 위해 user 지정
		if(id==null) user="guest";
		else user=id;
		String fno=request.getParameter("fno");
		try {
			Cookie cookie=new Cookie(user+"_food"+fno, fno); //map 형식으로 저장 -> 키, 값
			cookie.setPath("/"); //경로 지정
			cookie.setMaxAge(60*60*24); //1일
			response.addCookie(cookie);
		} catch(Exception ex) {}
		return "redirect:../food/food_detail.do?fno="+fno;
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

		AllReplyDAO rdao=new AllReplyDAO();
		List<AllReplyVO> rList=rdao.allReplyListData(Integer.parseInt(fno), 2);
		request.setAttribute("rList", rList);
		request.setAttribute("count", rList.size());
		
		String type=vo.getType();
		int index=type.indexOf('/');
		if(index>-1) {
			type=type.replace('/', '|');
		}
		List<RecipeVO> rcList=dao.food_recipe_data(type);
		request.setAttribute("rcList", rcList);
		
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		JjimDAO jdao=new JjimDAO();
		int jcount=jdao.jjimCount(Integer.parseInt(fno), id);
		request.setAttribute("jjim_count", jcount);
		
		LikeDAO ldao=new LikeDAO();
		int lcount=ldao.likeCount(Integer.parseInt(fno), id);
		request.setAttribute("like_count", lcount);
		
		request.setAttribute("main_jsp", "../food/food_detail.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
}
