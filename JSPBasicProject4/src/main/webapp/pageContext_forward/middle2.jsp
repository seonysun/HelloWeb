<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	System.out.println("middle2:id="+id);
	System.out.println("middle2:pwd="+pwd);
	
	pageContext.forward("output.jsp");
	//middle2에서 output 파일로 넘어가지 않고 화면만 전환 -> 데이터 유지
%>