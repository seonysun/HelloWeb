<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <main class="clear"> 
  <h2 class="sectiontitle">관심 맛집</h2>
  	<table class=table>
  	  <tr>
  	  	<th colspan=2 class=text-center>번호</th>
  	  	<th class=text-center>업체명</th>
  	  	<th class=text-center>주소</th>
  	  	<th class=text-center>전화</th>
  	  	<th class=text-center></th>
  	  </tr>
  	  <c:forEach var="vo" items="${list }">
	  	  <tr>
	  	  	<td class=text-center>${vo.no }</td>
	  	  	<td class=text-center>
	  	  		<img src="${vo.poster }" style="width: 30px;height: 30px">
	  	  	</td>
	  	  	<td>${vo.name }</td>
	  	  	<td>${vo.address }</td>
	  	  	<td class=text-center>${vo.tel }</td>
	  	  	<td class=text-center>
	  	  		<a href="../mypage/jjim_delete.do?no=${vo.jno }" class="btn btn-xs btn-warning">취소</a>
	  	  	</td>
	  	  </tr>
  	  </c:forEach>
  	</table>
  </main>
</div>
</body>
</html>