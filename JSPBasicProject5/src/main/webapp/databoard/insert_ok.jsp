<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*, com.sist.dao.*, com.sist.vo.*"%>
<%@ page import="com.oreilly.servlet.*" %>
<%@ page import="com.oreilly.servlet.multipart.*" %>
<jsp:useBean id="dao" class="com.sist.dao.DataBoardDAO"/>
<% 
	//한글 변환 -> main.jsp가 아닌 insert_ok.jsp 파일로 직접 들어왔으므로 처리 필수
	request.setCharacterEncoding("UTF-8");
		//GET 방식의 경우 windows10부터 한글 변환 자동처리
		//POST 방식의 경우 한글 변환 처리 필수
	//파일 업로드
	String path="c:\\download"; //업로드된 파일 저장 위치 설정
	int size=1024*1024*100; //업로드할 파일 최대 크기(100MB)
	String enctype="UTF-8"; //파일명 한글 변환
	MultipartRequest mr=new MultipartRequest(request,path,size,enctype,new DefaultFileRenamePolicy());
																		//동일파일명 존재 시 파일명 자동 변경 -> (1),(2).. 추가
	//DB에 저장
	String name=mr.getParameter("name");
	String subject=mr.getParameter("subject");
	String content=mr.getParameter("content");
	String pwd=mr.getParameter("pwd");
	String filename=mr.getOriginalFileName("upload");
	
	DataBoardVO vo=new DataBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	if(filename==null){ //첨부파일이 없는 경우
		vo.setFilename("");
		vo.setFilesize(0);
	} else { //첨부파일이 있는 경우
		File file=new File(path+"\\"+filename);
		vo.setFilename(filename);
		vo.setFilesize((int)file.length());
	}
	
	dao.dataBoardInsert(vo);
	response.sendRedirect("../main/main.jsp?mode=5");
%>
	