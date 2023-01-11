<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String no=request.getParameter("no");
	String fno=request.getParameter("fno");
	String msg=request.getParameter("msg");
	
	ReplyDAO dao=new ReplyDAO();
	dao.replyUpdate(Integer.parseInt(no), msg);
	
	response.sendRedirect("../pageContext_include/main.jsp?mode=2&fno="+fno);
%>