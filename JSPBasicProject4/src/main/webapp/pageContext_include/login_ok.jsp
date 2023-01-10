<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<%
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	
	MemberVO vo=dao.isLogin(id,pwd);
	
	if(vo.getMsg().equals("NOID")){
		%>
		<script>
			alert("ID가 존재하지 않습니다!");
			history.back();
		</script>
		<%
	} else if(vo.getMsg().equals("NOPWD")) {
		%>
		<script>
			alert("비밀번호가 틀립니다!");
			history.back();
		</script>
		<%
	} else if(vo.getMsg().equals("OK")){
		session.setAttribute("id", id);
		session.setAttribute("name", vo.getName());
		session.setAttribute("sex", vo.getSex());
		
		response.sendRedirect("main.jsp");
	}
%>