<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>    
<%
 	String cno=request.getParameter("cno");
  	CategoryVO vo=dao.categoryInfoData(Integer.parseInt(cno));
  	ArrayList<FoodVO> list=dao.categoryFoodList(Integer.parseInt(cno));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron">
				<!-- 제목 박스 -->
		<h2 class="text-center"><%=vo.getTitle() %></h2>
		<h4 class="text-center"><%=vo.getSubject() %></h4>
	</div>
	<div class="row">
	  <table class="table">
	  	<tr>
		  <td class="text-center">
			<%
				for(FoodVO fvo:list) {
			%>
			<table class="table">
				<tr>
				  <td width=30% class="text-center" rowspan=4>
				    <a href="../food/cookie_food.jsp?fno=<%=fvo.getFno() %>">
				  	  <img src="<%=fvo.getPoster() %>" style="width: 240px;height: 200px" class="img-rounded">
				    </a>
				  </td>
				  <td width=70% class="text-left">
				  	<h3>
				  		<a href="../food/cookie_food.jsp?fno=<%=fvo.getFno() %>"><%=fvo.getName() %></a>&nbsp;<span style="color: orange"><%=fvo.getScore() %></span>
				  	</h3>
				  </td>
				</tr>
				<tr>
				  <td width=70% class="text-left"><%=fvo.getAddress() %></td>
				</tr>
				<tr>
				  <td width=70% class="text-left"><%=fvo.getTel() %></td>
				</tr>
				<tr>
				  <td width=70% class="text-left"><%=fvo.getType() %></td>
				</tr>
			</table>
			<%		
				}
			%>
		  </td>
		</tr>
	  </table>
	</div>
</body>
</html>