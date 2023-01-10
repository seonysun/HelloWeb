<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	System.out.println("middle:id="+id);
	System.out.println("middle:pwd="+pwd);
	
	response.sendRedirect("output.jsp");
	//middle에서 output 파일로 이동 -> 데이터 초기화
%>