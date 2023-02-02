package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import java.io.PrintWriter;
import java.util.*;

@Controller
public class MemberModel {
	@RequestMapping("member/join.do")
	public String member_join(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/join.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/join_ok.do")
	public String member_insert(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String content=request.getParameter("content");
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirth(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(tel1+"-"+tel2);
		vo.setContent(content);
		dao.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	
	@RequestMapping("member/idcheck.do")
	public String member_idcheck(HttpServletRequest request,HttpServletResponse response) {
		return "../member/idcheck.jsp";
	}
	
	@RequestMapping("member/idcheck_result.do")
	public String member_idcheck_result(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberIdCheck(id);
		request.setAttribute("count", count);
		return "../member/idcheck_result.jsp";
	}
	
	@RequestMapping("member/email_check.do")
	public String member_email_check(HttpServletRequest request,HttpServletResponse response) {
		String email=request.getParameter("email");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberEmailCheck(email);
		request.setAttribute("count", count);
		return "../member/email_check.jsp";
	}
	
	@RequestMapping("member/tel_check.do")
	public String member_tel_check(HttpServletRequest request,HttpServletResponse response) {
		String phone=request.getParameter("phone");
		MemberDAO dao=new MemberDAO();
		int count=dao.memberPhoneCheck(phone);
		request.setAttribute("count", count);
		return "../member/tel_check.jsp";
	}
	
	@RequestMapping("member/postfind.do")
	public String member_postfind(HttpServletRequest request,HttpServletResponse response) {
		return "../member/postfind.jsp";
	}
	
	@RequestMapping("member/postfind_result.do")
	public String member_postfind_result(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String dong=request.getParameter("dong");
		MemberDAO dao=new MemberDAO();
		List<ZipcodeVO> list=dao.memberPostFind(dong);
		int count=dao.memberPostCount(dong);
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		return "../member/postfind_result.jsp";
	}
	
	@RequestMapping("member/login.do")
	public String member_login(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		MemberDAO dao=new MemberDAO();
		MemberVO vo=dao.memberLogin(id, pwd);
		if(vo.getMsg().equals("OK")) {
			//로그인 성공 시 session에 저장 -> 모든 jsp 파일에서 사용 가능(전역변수)
			HttpSession session=request.getSession();
			//session, cookie 생성 -> request 이용
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("admin", vo.getAdmin());
		}
		request.setAttribute("result", vo.getMsg());
		return "../member/login.jsp";
	}
	
	@RequestMapping("member/logout.do")
	public String member_logout(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:../main/main.do";
	}
	
	@RequestMapping("member/idfind.do")
	public String member_idfind(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/idfind.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/idfind_ok.do")
	public void member_idfind_ok(HttpServletRequest request,HttpServletResponse response) {
		String tel=request.getParameter("tel");
		MemberDAO dao=new MemberDAO();
		String res=dao.memberIdfind(tel);
		try {
			PrintWriter out=response.getWriter(); //데이터를 받아서
			out.println(res); //화면에 출력(데이터 전송)
		} catch(Exception ex) {}
	}
	
	@RequestMapping("member/idfind2_ok.do")
	public void member_idfind2_ok(HttpServletRequest request,HttpServletResponse response) {
		String email=request.getParameter("email");
		MemberDAO dao=new MemberDAO();
		String res=dao.memberIdfind2(email);
		try {
			PrintWriter out=response.getWriter();
			out.println(res);
		} catch(Exception ex) {}
	}
	
	@RequestMapping("member/join_update.do")
		//회원 정보 수정, 탈퇴 -> session 새로 저장
	public String member_join_update(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		MemberDAO dao=new MemberDAO();
		MemberVO vo=dao.memberJoinUpdateData(id);
		String phone=vo.getPhone();
		phone=phone.substring(phone.indexOf("-")+1);
		vo.setPhone(phone);
		request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../member/join_update.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/join_update_ok.do")
	public String member_join_update_ok(HttpServletRequest request,HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch(Exception ex) {}
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String post=request.getParameter("post");
		String addr1=request.getParameter("addr1");
		String addr2=request.getParameter("addr2");
		String tel1=request.getParameter("tel1");
		String tel2=request.getParameter("tel2");
		String content=request.getParameter("content");
		MemberDAO dao=new MemberDAO();
		MemberVO vo=new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setName(name);
		vo.setSex(sex);
		vo.setBirth(birthday);
		vo.setEmail(email);
		vo.setPost(post);
		vo.setAddr1(addr1);
		vo.setAddr2(addr2);
		vo.setPhone(tel1+"-"+tel2);
		vo.setContent(content);
		dao.memberInsert(vo);
		return "redirect:../main/main.do";
	}
	
	@RequestMapping("member/join_delete.do")
	public String member_join_delete(HttpServletRequest request,HttpServletResponse response) {
		request.setAttribute("main_jsp", "../member/join_delete.jsp");
		CommonsModel.footerData(request);
		return "../main/main.jsp";
	}
	
	@RequestMapping("member/join_delete_ok.do")
	public void member_join_delete_ok(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String pwd=request.getParameter("pwd");
		MemberDAO dao=new MemberDAO();
		boolean bCheck=dao.memberJoinDelete(id, pwd);
		try {
			PrintWriter out=response.getWriter();
			if(bCheck==true) {
				out.println("n");
			}
		} catch(Exception ex) {}
		
	}
}