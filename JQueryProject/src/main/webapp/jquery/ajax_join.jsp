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
	width: 700px;
	margin: 0px auto;
}
</style>
<!-- daum 우편번호 검색 라이브러리 -->
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- shadow 라이브러리(윈도우창 말고 팝업으로 작은 화면 뜨게) -->
<script type="text/javascript" src="shadow/js/shadowbox.js"></script>
<link rel="stylesheet" href="shadow/css/shadowbox.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
Shadowbox.init({
	players:['iframe']
})
$(function(){
	$('#postBtn').click(function(){
		new daum.Postcode({
			oncomplete:function(data){
								//data를 받아서 내용 자동으로 채우기
				$('#post').val(data.zonecode)
				$('#addr').val(data.address)
			}
		}).open()
			//새로운 창으로 뜨게 설정
	})
	$('#idCheckBtn').click(function(){
		//팝업창으로 만들기 : window.open('idCheck.jsp','idCheck','width:330,height:220')
		//shadowbox로 만들기
		Shadowbox.open({
			content:'idCheck.jsp',
			player:'iframe',
			title:'아이디 중복체크',
			width:330,
			height:220
		})
	})
})
</script>
</head>
<body>
	<div class="container">
		<h1 class="text-center">회원가입</h1>
		<div class="row">
		  <form name="join_frm" id="join_frm">
			<table class="table">
			  <tr>
			  	<th class="text-right" width=15%>ID</th>
			  	<td width=85%>
			  		<input type=text id=id size=15 class="input-sm" readonly name=id>
			  		<input type=button value="아이디중복체크" class="btn btn-sm btn-danger" id="idCheckBtn">
			  	</td>
			  </tr>
			  <tr>
			  	<th class="text-right" width=15%>우편번호</th>
			  	<td width=85%>
			  		<input type=text id=post size=15 class="input-sm" readonly>
			  		<input type=button value="우편번호검색" class="btn btn-sm btn-success" id="postBtn">
			  	</td>
			  </tr>
			  <tr>
			  	<th class="text-right" width=15%>주소</th>
			  	<td width=85%>
			  		<input type=text id=addr size=50 class="input-sm" readonly>
			  	</td>
			  </tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>