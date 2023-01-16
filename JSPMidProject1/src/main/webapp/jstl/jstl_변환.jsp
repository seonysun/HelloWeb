<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, java.text.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>날짜 변환</h1>
<%
	Date date=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String today=sdf.format(date); 
%>
	<%=date %><br>
	<%=today %>
	<p>
<c:set var="today" value="<%=date %>"/>
	<fmt:formatDate value="${today }" pattern="yyyy-MM-dd"/>
	
	<h1>숫자 변환</h1>
<%
	int a=12345678;
	DecimalFormat d=new DecimalFormat("###,###,###");
	String num=d.format(a);
%>
	<%=num %><br>
	<p>
<c:set var="num" value="<%=a %>"/>
	<fmt:formatNumber value="${num }" type="number"/><br>
	<fmt:formatNumber value="${num }" type="currency" currencySymbol="$"/>
</body>
</html>