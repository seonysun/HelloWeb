<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
  <%
  	//1. 사용자가 보내준 데이터 받기(데이터형 String)
  	String strPage=request.getParameter("page");
	  	/* null값 처리 필수
	  	list.jsp -> null
	  		- if(strPage==null)
	  	list.jsp?page= -> ""
	  		- if(strPage.equals(""))
	  	*/
  	if(strPage==null) strPage="1";
  	int curpage=Integer.parseInt(strPage);
  	//2. 오라클 연결(메소드)
	BoardDAO dao=new BoardDAO();
	ArrayList<BoardVO> list=dao.boardListData(curpage);
	int totalpage=dao.boardTotalPage();
  	//3. 처리 결과값 읽기 & 4. HTML에 처리 결과 출력 -> body에서 처리
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>자유게시판</h1>
		<div class="row"><%-- 게시물 목록 출력 --%>
			<table class="table">
				<tr>
				  <td>
				  	<a href="insert.jsp" class="btn btn-sm btn-warning">새글</a>
				  </td>
				</tr>
			</table>
			<table class="table">
				<tr class="success">
				  <th width=10% class="text-center">번호</th>
			 	  <th width=45% class="text-center">제목</th>
				  <th width=15% class="text-center">이름</th>
				  <th width=20% class="text-center">작성일</th>
				  <th width=10% class="text-center">조회수</th>
				</tr>
				<%
					for(BoardVO vo:list){
				%>
				<tr>
				  <td width=10% class="text-center"><%=vo.getNo() %></td>
			 	  <td width=45%><a href="detail.jsp?no=<%=vo.getNo() %>"><%=vo.getSubject() %></a>&nbsp;
			 	  						<!-- 상세페이지는 값 입력 필요 -> a 태그 이므로 GET 방식 only -->
			 	  	<!-- 제목은 글자수가 다르므로 왼쪽 정렬 -->
			 	  	<%
			 	  		String today=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date());
			 	  		if(today.equals(vo.getDbday())){
			 	  	%>
			 	  		<sup style="color: red">new</sup>
			 	  	<%
			 	  		}
			 	  	%>
			 	  </td>
				  <td width=15% class="text-center"><%=vo.getName() %></td>
				  <td width=20% class="text-center"><%=vo.getDbday() %></td>
				  <td width=10% class="text-center"><%=vo.getHit() %></td>
				</tr>
				<%
					}
				%>
			</table>
			<table class="table">
				<tr style="text-align: right">
				  <td>
				  	<input type="checkbox" value="name">이름
				  	<input type="checkbox" value="subject">제목
				  	<input type="checkbox" value="content">내용
				  	&nbsp;
				  	<input type="text" name=ss size=15 class="input-sm" autocomplete="off">
				  														 <!-- 자동입력(이전검색어) 끄기 -->
				  	<input type="submit" value="검색" class="btn btn-sm btn-danger">
				  </td>
				</tr>
			</table>
		</div>
		<div style="height: 20px"></div><%-- 간격 설정 --%>
		<div class="row"><%-- 페이지 위치 출력 --%>
			<div class="text-center">
				<a href="list.jsp?page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-primary">이전</a>
					<%=curpage %> page / <%=totalpage %> pages
				<a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>
			</div>
		</div>
	</div>
</body>
</html>