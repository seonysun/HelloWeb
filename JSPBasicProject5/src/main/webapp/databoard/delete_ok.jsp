<%@page import="com.sist.vo.DataBoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*, java.io.*"%>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<%
	String no=request.getParameter("no");
	String pwd=request.getParameter("pwd");
	
	DataBoardVO vo=dao.dataBoardFileData(Integer.parseInt(no));
	boolean bCheck=dao.dataBoardDelete(Integer.parseInt(no), pwd);
	if(bCheck==true){
		//첨부파일이 있는 경우 파일 삭제
		if(vo.getFilesize()>0){
			try {
				File file=new File("c:\\download\\"+vo.getFilename());
				file.delete();
			} catch(Exception ex) {}
		}
		//화면 전환
		response.sendRedirect("../main/main.jsp?mode=5");
	} else {
	%>
		<script>
			alert("비밀번호가 틀립니다!");
			history.back();
		</script>
	<%
	}
%>