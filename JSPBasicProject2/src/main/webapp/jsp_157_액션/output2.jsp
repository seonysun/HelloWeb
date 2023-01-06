<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*"%>
  <%
    request.setCharacterEncoding("UTF-8");
  %>
<jsp:useBean id="bean" class="com.sist.vo.Mybean">
	<jsp:setProperty name="bean" property="*"/>
</jsp:useBean>
<%--
<jsp:useBean id="bean" class="com.sist.vo.Mybean">
	Mybean bean=new Mybean();
	
<jsp:setProperty name="bean" property="*"/>
	String name=request.getParameter("name");
    String sex=request.getParameter("sex");
    String loc=request.getParameter("loc");
    String content=request.getParameter("content");
    
    bean.setName(name);
    bean.setSex(sex);
    bean.setLoc(loc);
    bean.setContent(content);
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>정보 출력</h1>
	이름: <jsp:getProperty name="bean" property="name"/><br>
	성별: <jsp:getProperty name="bean" property="sex"/><br>
	지역: <jsp:getProperty name="bean" property="loc"/><br>
	소개: <jsp:getProperty name="bean" property="content"/><br>
</body>
</html>