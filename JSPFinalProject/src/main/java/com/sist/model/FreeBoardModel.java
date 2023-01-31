package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

@Controller
public class FreeBoardModel {
	@RequestMapping("freeboard/list.do")
	public String freeboard_list(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		FreeBoardDAO dao=new FreeBoardDAO();
		List<FreeBoardVO> list=dao.boardListData(curpage);
		int totalpage=dao.boardTotalPage();
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		request.setAttribute("main_jsp", "../freeboard/list.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert.do")
	public String freeboard_insert(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("main_jsp", "../freeboard/insert.jsp");
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/insert_ok.do")
	public String freeboard_insert_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=new FreeBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		dao.boardInsert(vo);
		return "redirect:list.do";
	}
	
	@RequestMapping("freeboard/detail.do")
	public String freeboard_detail(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=dao.boardDetailData(Integer.parseInt(no));
		List<BoardReplyVO> list=dao.replyListData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("list", list);
		request.setAttribute("count", list.size());
		request.setAttribute("main_jsp", "../freeboard/detail.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update.do")
	public String freeboard_update(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../freeboard/update.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("freeboard/update_ok.do")
	public String freeboad_update_ok(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		FreeBoardDAO dao=new FreeBoardDAO();
		FreeBoardVO vo=new FreeBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true) {
			request.setAttribute("res", "yes");
		} else {
			request.setAttribute("res", "no");
		}
		return "../freeboard/update_ok.jsp";
	}
	
	@RequestMapping("freeboard/delete.do")
	public String freeboard_delete(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		FreeBoardDAO dao=new FreeBoardDAO();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
		if(bCheck==true) {
			request.setAttribute("res", "yes");
		} else {
			request.setAttribute("res", "no");
		}
		return "../freeboard/update_ok.jsp";
			//동일한 내용의 ok 파일은 하나로 공유해서 사용해도 됨
	}
	
	@RequestMapping("freeboard/reply_insert.do")
	public String freeboard_reply_insert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String bno=request.getParameter("bno");
		String msg=request.getParameter("msg");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String name=(String)session.getAttribute("name");
		FreeBoardDAO dao=new FreeBoardDAO();
		BoardReplyVO vo=new BoardReplyVO();
		vo.setBno(Integer.parseInt(bno));
		vo.setId(id);
		vo.setName(name);
		vo.setMsg(msg);
		dao.replyInsert(vo);
		return "redirect:detail.do?no="+bno;
					//넘어가는 값이 String이므로 숫자 변환형이 아닌 String으로 삽입
	}

}
