package com.sist.model;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
@Controller
public class AdminModel {
	@RequestMapping("adminpage/admin_main.do")
	public String admin_main(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../adminpage/admin_home.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	private String[] prefix= {"","일반공지","이벤트공지","맛집공지","여행공지","상품공지"};
	@RequestMapping("adminpage/notice_list.do")
	public String admin_notice_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		NoticeDAO dao=new NoticeDAO();
		List<NoticeVO> list=dao.noticeListData(curpage);
		for(NoticeVO vo:list) {
			vo.setPrefix("["+prefix[vo.getType()]+"]");
		}
		int totalpage=dao.noticeTotalPage();
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("admin_jsp", "../adminpage/notice_list.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("adminpage/notice_insert.do")
	public String admin_notice_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("admin_jsp", "../adminpage/notice_insert.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("adminpage/notice_insert_ok.do")
	public String admin_notice_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		NoticeVO vo=new NoticeVO();
		vo.setType(Integer.parseInt(type));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		NoticeDAO dao=new NoticeDAO();
		dao.noticeInsert(vo);
		return "redirect:notice_list.do";
	}
	
	@RequestMapping("adminpage/notice_delete.do")
	public String admin_notice_delete(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		NoticeDAO dao=new NoticeDAO();
		dao.noticeDelete(Integer.parseInt(no));
		return "redirect:notice_list.do";
	}
	
	@RequestMapping("adminpage/notice_update.do")
	public String admin_notice_update(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		NoticeDAO dao=new NoticeDAO();
		NoticeVO vo=dao.noticeDetailData(Integer.parseInt(no),2);
		request.setAttribute("vo", vo);
		request.setAttribute("admin_jsp", "../adminpage/notice_update.jsp");
		request.setAttribute("main_jsp", "../adminpage/admin_main.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("adminpage/notice_update_ok.do")
	public String admin_notice_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String no=request.getParameter("no");
		String type=request.getParameter("type");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		NoticeDAO dao=new NoticeDAO();
		NoticeVO vo=new NoticeVO();
		vo.setNo(Integer.parseInt(no));
		vo.setType(Integer.parseInt(type));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		dao.noticeUpdate(vo);
		return "redirect:notice_list.do";
	}
}
