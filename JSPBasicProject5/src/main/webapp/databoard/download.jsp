<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*"%>
<%
	String fn=request.getParameter("fn");
	String path="c:\\download";
	//다운로드는 라이브러리가 없으므로 직접 제작해야 함(업로드는 라이브러리 존재)
	try{
		//파일 정보 설정
		File file=new File(path+"\\"+fn);
		response.setContentLengthLong(file.length());
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
		//서버에서 파일 읽기
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		//파일 출력 위치 설정, 클라이언트 쪽에 파일 복사
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		int i=0;
		byte[] buffer=new byte[1024];
					//한번에 1024byte씩 전송
		while((i=bis.read(buffer, 0, 1024))!=-1){
				//읽은 바이트 수
										//파일 끝(EOF)까지 읽기
			bos.write(buffer, 0, i);
		}
		out.clear(); //사용한 out 객체 지우기
		out=pageContext.pushBody();	//새로운 객체 생성
		bis.close();
		bos.close();
	}catch(Exception ex){}
%>