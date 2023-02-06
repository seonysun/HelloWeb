package com.sist.model;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.dao.*;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;

@Controller
public class MyPageModel {
	@RequestMapping("mypage/mypage_main.do")
	public String mypage_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("mypage_jsp", "../mypage/mypage_home.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("mypage/jjim_list.do")
	public String mypage_jjim_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		JjimDAO dao=new JjimDAO();
		List<JjimVO> list=dao.jjimListData(id);
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../jjim/jjim_list.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("mypage/jjim_delete.do")
	public String mypage_jjim_delete(HttpServletRequest request, HttpServletResponse response) {
		String jno=request.getParameter("no");
		JjimDAO dao=new JjimDAO();
		dao.jjimDelete(Integer.parseInt(jno));
		return "redirect:../mypage/jjim_list.do";
	}

	@RequestMapping("mypage/reserve.do")
	public String mypage_reserve_list(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		ReserveDAO dao=new ReserveDAO();
		List<ReserveVO> list=dao.reserveMypageData(id);
		request.setAttribute("list", list);
		request.setAttribute("mypage_jsp", "../mypage/reserve.jsp");
		request.setAttribute("main_jsp", "../mypage/mypage_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("mypage/reserve_info.do")
	public String mypage_reserve_info(HttpServletRequest request, HttpServletResponse response) {
		String rno=request.getParameter("rno");
		ReserveDAO dao=new ReserveDAO();
		ReserveVO vo=dao.mypageReserveInfo(Integer.parseInt(rno));
		request.setAttribute("vo", vo);
		return "../mypage/reserve_info.jsp";
	}
}
