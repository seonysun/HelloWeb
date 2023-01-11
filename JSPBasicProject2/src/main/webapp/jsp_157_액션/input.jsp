<%--
JSP 액션 태그
	1) <jsp:include> : 다른 페이지를 현재 페이지에 포함
		- <jsp:include page="URL" flush="true">
		cf. <%@ include %> : 정적 -> header, footer
		동적 -> section
	2) <jsp:useBean> : 메모리 할당(객체 생성)
		- <jsp:useBean id="객체명" class="클래스명">
			=> A a=new A();
	3) <jsp:setProperty>
		- <jsp:setProperty name="객체명(id)" property="*">
			-> 전체 객체가 가지고 있는 변수 set 메소드 호출
		- <jsp:setProperty name="객체명" property="변수명" value="값">
			-> 변수에 값 대입 setName()
	4) <jsp:getProperty>
		- <jsp:getProperty name="객체명" property="변수명"/>
			-> 변수에 입력된 값 출력 getName()
	5) <jsp:forward> : 화면 전환(내용만 변경)
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	width: 500px;
	margin: 0px auto;
}
.h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>개인정보</h1>
		<div class="row">
		  <form method=post action="output2.jsp">
		  		<%-- action에 등록된 파일(output2.jsp)로 데이터 전송 --%>
			<table class="table">
				<tr>
					<th width=20% class="text-right">이름</th>
					<td width=80%>
						<input type=text name=name size=15 class="input-sm">
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">성별</th>
					<td width=80%>
						<input type=radio name=sex value="남자" checked>남자
						<input type=radio name=sex value="여자">여자
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">지역</th>
					<td width=80%>
						<select name="loc" class="input-sm">
							<option>서울</option>
							<option>경기</option>
							<option>부산</option>
							<option>대전</option>
							<option>제주</option>
						</select>
					</td>
				</tr>
				<tr>
					<th width=20% class="text-right">소개</th>
					<td width=80%>
						<textarea rows="8" cols="30" name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=submit value="전송" class="btn btn-sm btn-warning">
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>