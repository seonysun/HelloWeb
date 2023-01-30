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
	margin-top: 30px;
}
.row{
	width: 550px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#postfindBtn').click(function(){
		let dong=$('#dong').val()
		if(dong.trim()===""){
			$('#dong').focus()
			return
		}
		$.ajax({
			type:'post', //결과값 전송 방식 선택
			url:'../member/postfind_result.do', //결과값 실행 위치 설정
			data:{"dong":dong}, //실행에 필요한 데이터 전송
			success:function(result){ //실행 결과 읽어옴
				$('#print').html(result) //읽어온 데이터 출력 위치 설정
			}
		})
	})
})
</script>
</head>
<body>
	<div class=container>
		<div class=row>
			<table class=table>
			  <tr>
			  	<td class=text-center>
			  		입력: <input type=text name=dong id=dong size=20 class=input-sm>
			  		<input type=button value="우편번호 검색" class="btn btn-sm btn-danger" id=postfindBtn>
			  	</td>
			  </tr>
			</table>
			<span id=print>
			
			</span>
		</div>
	</div>
</body>
</html>