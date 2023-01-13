<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.row1{
	width: 800px;
}
</style>
</head>
<body>
	<div class="row row1">
		<h1 class="text-center">글쓰기</h1>
	  <form method=post action="../databoard/insert_ok.jsp" enctype="multipart/form-data">
	  														<!-- post 방식으로 파일 업로드 시 프로토콜 -->
		<table class="table">
		  <tr>
		  	<th width=15% class="text-right warning">이름</th>
		  	<td width=85%>
		  		<input type=text name=name size=15 class="input-sm" required>
		  	</td>
		  </tr>
		  <tr>
		  	<th width=15% class="text-right warning">제목</th>
		  	<td width=85%>
		  		<input type=text name=subject size=45 class="input-sm" required>
		  	</td>
		  </tr>
		  <tr>
		  	<th width=15% class="text-right warning">내용</th>
		  	<td width=85%>
		  		<textarea rows=10 cols=50 name=content required></textarea>
		  	</td>
		  </tr>
		  <tr>
		  	<th width=15% class="text-right warning">첨부파일</th>
		  	<td width=85%>
		  		<input type=file name=upload size=20 class="input-sm">
		  	</td>
		  </tr>
		  <tr>
		  	<th width=15% class="text-right warning">비밀번호</th>
		  	<td width=85%>
		  		<input type=password name=pwd size=10 class="input-sm" required>
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan=2 class="text-center">
		  		<input type=submit value="글쓰기" class="btn btn-sm btn-primary">
		  		<input type=button value="취소" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
		  	</td>
		  </tr>
		</table>
	  </form>
	</div>
</body>
</html>