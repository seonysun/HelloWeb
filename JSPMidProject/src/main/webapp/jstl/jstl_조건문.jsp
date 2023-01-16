<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>if</h1>
<%
	int sex=1;
%>
	<%
		if(sex==1){
	%>
		남자
	<%
		}else{
	%>
		여자	
	<%
		}
	%>
	<p>
<c:set var="sex" value="<%=sex %>"/>
	<c:if test="${sex==1 }">
				<!-- 안의 변수는 키명, 일반 변수 아님 -->
		남자
	</c:if>
	<c:if test="${sex!=1 }">
		여자
	</c:if>
	
	<h1>다중 조건문</h1>
<%
	int star=3;
%>
	<%
		if(star==1){
	%>
		★☆☆☆☆
	<%
		}else if(star==2){
	%>
		★★☆☆☆
	<%
		}else if(star==3){
	%>
		★★★☆☆
	<%
		}else if(star==4){
	%>
		★★★★☆
	<%
		}else if(star==5){
	%>
		★★★★★
	<%
		}else{
	%>
		☆☆☆☆☆
	<%		
		}
	%>
	<p>
<c:set var="star" value="<%=star %>"/>
	<c:choose>
		<c:when test="${star==1 }">★☆☆☆☆</c:when>
		<c:when test="${star==2 }">★★☆☆☆</c:when>
		<c:when test="${star==3 }">★★★☆☆</c:when>
		<c:when test="${star==4 }">★★★★☆</c:when>
		<c:when test="${star==5 }">★★★★★</c:when>
		<c:otherwise>☆☆☆☆☆</c:otherwise>
	</c:choose>
</body>
</html>