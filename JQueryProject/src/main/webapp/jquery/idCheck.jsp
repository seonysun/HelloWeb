<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
.container{
	margin-top: 30px;
}
.row{
	width: 300px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<div class=container>
		<div class=row>
			<table class=table>
			  <tr>
			  	<td>ID:
			  		<input type=text name=id id=id size=15 class=input-sm>
			  		<input type=button id=checkBtn value="아이디체크" class="btn btn-sm btn-primary">
			  	</td>
			  </tr>
			  <tr>
			  	<td class=text-center>ID를 입력하세요</td>
			  </tr>
			  <tr style="display:none" id=sh>
			  	<td class=text-center><input type=button value="확인" class="btn btn-sm btn-success"></td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>