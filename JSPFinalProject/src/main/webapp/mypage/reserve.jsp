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
  <h2 class="sectiontitle">예약 목록</h2>
  	<table class=table>
  	  <tr>
  	  	<th colspan=2 class=text-center>번호</th>
  	  	<th class=text-center>업체명</th>
  	  	<th class=text-center>예약날짜</th>
  	  	<th class=text-center>예약시간</th>
  	  	<th class=text-center>인원</th>
  	  	<th class=text-center></th>
  	  </tr>
  	  <c:forEach var="vo" items="${list }">
	  	  <tr>
	  	  	<td class=text-center>${vo.rno }</td>
	  	  	<td class=text-center>
	  	  		<img src="${vo.fvo.poster }" style="width: 30px;height: 30px">
	  	  	</td>
	  	  	<td class=text-center>${vo.fvo.name }</td>
	  	  	<td class=text-center>${vo.rdate }</td>
	  	  	<td class=text-center>${vo.rtime }</td>
	  	  	<td class=text-center>${vo.inwon }</td>
	  	  	<td class=text-center>
	  	  	  <c:if test="${vo.ok=='n' }">
	  	  		<span class="btn btn-xs btn-default">예약대기</span>
	  	  	  </c:if>
	  	  	  <c:if test="${vo.ok=='y' }">
	  	  		<span class="btn btn-xs btn-success">예약완료</span>
	  	  	  </c:if>
	  	  		<a href="../reserve/reserve_delete.do?no=${vo.rno }" class="btn btn-xs btn-warning">취소</a>
	  	  	</td>
	  	  </tr>
  	  </c:forEach>
  	</table>
  </main>
</div>
</body>
</html>