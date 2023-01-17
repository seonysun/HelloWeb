package com.sist.model;
import java.text.SimpleDateFormat;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReBoardModel {
	public void boardListData(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		ReBoardDAO dao=new ReBoardDAO();
		ArrayList<ReBoardVO> list=dao.boardListData(curpage);
		request.setAttribute("list", list);
		request.setAttribute("today", new SimpleDateFormat("YYYY-MM-DD").format(new Date()));
		request.setAttribute("msg", "삭제된 게시물입니다");
	}
	public void boardDetailData(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		ReBoardDAO dao=new ReBoardDAO();
		ReBoardVO vo=dao.boardDetailData(Integer.parseInt(no), 1);
		request.setAttribute("vo", vo);
	}
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try { 
			request.setCharacterEncoding("UTF-8"); //한글 입력 시 변환 처리 필수
		} catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		ReBoardDAO dao=new ReBoardDAO();
		ReBoardVO vo=new ReBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		dao.boardInsert(vo);
	}
	public void boardReplyInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String pno=request.getParameter("pno");
		ReBoardDAO dao=new ReBoardDAO();
		ReBoardVO vo=new ReBoardVO();
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		dao.boardReplyInsert(Integer.parseInt(pno), vo);
	}
	public void boardUpdateData(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		ReBoardDAO dao=new ReBoardDAO();
		ReBoardVO vo=dao.boardDetailData(Integer.parseInt(no), 2);
		request.setAttribute("vo", vo);
	}
	public void boardUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String no=request.getParameter("no");
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		ReBoardDAO dao=new ReBoardDAO();
		ReBoardVO vo=new ReBoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		boolean bCheck=dao.boardUpdate(vo);
		String msg="";
		if(bCheck==true) {
			msg="yes";
		} else {
			msg="no";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("no", no);
	}
	public void boardDelete(HttpServletRequest request, HttpServletResponse response) {
		String no=request.getParameter("no");
		String pwd=request.getParameter("pwd");
		ReBoardDAO dao=new ReBoardDAO();
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
		request.setAttribute("bCheck", bCheck);
	}
}
