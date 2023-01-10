<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	System.out.println("redirect.jsp:id=>"+id);
	response.sendRedirect("send2");
%>