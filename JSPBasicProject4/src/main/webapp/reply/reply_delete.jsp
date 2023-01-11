<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	String no=request.getParameter("no");
	String fno=request.getParameter("fno");
	
	ReplyDAO dao=new ReplyDAO();
	dao.replyDelete(Integer.parseInt(no));
	
	response.sendRedirect("../pageContext_include/main.jsp?mode=2&fno="+fno);
%>