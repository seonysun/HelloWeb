<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String fno=request.getParameter("fno");
	String msg=request.getParameter("msg");
	String id=(String)session.getAttribute("id");
	String name=(String)session.getAttribute("name");
	
	ReplyVO vo=new ReplyVO();
	vo.setFno(Integer.parseInt(fno));
	vo.setId(id);
	vo.setName(name);
	vo.setMsg(msg);
	
	ReplyDAO dao=new ReplyDAO();
	dao.replyInsert(vo);
	
	response.sendRedirect("../pageContext_include/main.jsp?mode=2&fno="+fno);
%>