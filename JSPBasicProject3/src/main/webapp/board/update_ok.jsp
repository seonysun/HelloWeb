<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.BoardDAO"/>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" class="com.sist.dao.BoardVO">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
	boolean bCheck=dao.boardUpdate(vo);
	if(bCheck==true) {
		//비밀번호 동일 -> 수정 적용, 상세보기로 이동
		response.sendRedirect("detail.jsp?no="+vo.getNo());
	} else {
		//비밀번호 오류 -> 수정 화면으로 이동
	%>
		<script>
			alert("비밀번호가 틀립니다!");
			history.back(); //이전페이지로 이동
		</script>
	<%
	}
%>