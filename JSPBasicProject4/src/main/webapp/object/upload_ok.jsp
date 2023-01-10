<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,com.oreilly.servlet.*,com.oreilly.servlet.multipart.*"%>
<%
	try {
		request.setCharacterEncoding("UTF-8");
		int size=1024*1024*100;
		String enctype="UTF-8";
		String path=application.getRealPath("/object");
		MultipartRequest mr=
				new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
																//동일파일명 존재 시 자동 변경 (1),(2).. 추가
		response.sendRedirect("application.jsp?filename="+mr.getOriginalFileName("upload"));
	} catch(Exception ex) {}
%>