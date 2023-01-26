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
	width: 500px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
function calc(){
	let kor=document.querySelector("#kor").value
	if(kor.trim()=="") { //유효성검사(입력여부 확인 -> 공백일 경우, trim 필수)
		document.querySelector("#kor").focus();
		return
	}
	let eng=document.querySelector("#eng").value
	if(eng.trim()=="") {
		document.querySelector("#eng").focus();
		return
	}
	let math=document.querySelector("#math").value
	if(math.trim()=="") {
		document.querySelector("#math").focus();
		return
	}
	
	let total=Number(kor)+Number(eng)+Number(math)
			//입력값은 모두 string으로 들어오므로 형변환 필수
	document.querySelector("#total").value=total
	let avg=total/3
	document.querySelector("#avg").value=Math.round(avg,2)
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="text-center">성적 계산</h1>
			<table class="table">
			  <tr>
			  	<td class="text-center" width=30%>국어</td>
			  	<td width=70%>
			  		<input type=text id=kor size=15 class="input-sm">
			  	</td>
			  </tr>
			  <tr>
			  	<td class="text-center" width=30%>영어</td>
			  	<td width=70%>
			  		<input type=text id=eng size=15 class="input-sm">
			  	</td>
			  </tr>
			  <tr>
			  	<td class="text-center" width=30%>수학</td>
			  	<td width=70%>
			  		<input type=text id=math size=15 class="input-sm">
			  	</td>
			  </tr>
			  <tr>
			  	<td class="text-center" width=30%>총점</td>
			  	<td width=70%>
			  		<input type=text id=total size=15 class="input-sm" readonly>
			  	</td>
			  </tr>
			  <tr>
			  	<td class="text-center" width=30%>평균</td>
			  	<td width=70%>
			  		<input type=text id=avg size=15 class="input-sm" readonly>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2 class="text-center">
			  		<input type=button value="계산" class="btn btn-sm btn-primary" onclick="calc()">
			  	</td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>