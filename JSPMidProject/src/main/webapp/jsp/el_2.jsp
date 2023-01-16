<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.main.*"%>
<%
	SawonVO vo=new SawonVO();
	vo.setSabun(1);
	vo.setName("이순신");
	vo.setDept("개발부");
	vo.setJob("대리");
	vo.setLoc("서울");
	
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사원 정보 출력</h1>
	사번: ${vo.sabun } / <%=((SawonVO)request.getAttribute("vo")).getSabun() %><br>
	이름: ${vo.getName() }<br>
	부서: ${vo.dept }<br>
	직위: ${vo.job }<br>
	근무지: ${vo.loc }<br>
</body>
</html>