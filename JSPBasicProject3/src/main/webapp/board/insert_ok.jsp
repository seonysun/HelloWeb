<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%-- ok 파일 : 전송된 데이터 받아서 요청 처리만 하는 역할 --%>
<%
	request.setCharacterEncoding("UTF-8");
	//1. 사용자 요청 데이터 받음
%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/>
	<!-- BoardDAO dao=new BoardDAO(); -->
<jsp:useBean id="vo" class="com.sist.dao.BoardVO">
	<!-- BoardVO vo=new BoardVO(); -->
	<jsp:setProperty name="vo" property="*"/>
	<!-- 
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
	 -->
</jsp:useBean>
<%
	//2. 데이터 오라클로 전송
	dao.boardInsert(vo);
	//3. 화면 이동
	response.sendRedirect("list.jsp");
%>