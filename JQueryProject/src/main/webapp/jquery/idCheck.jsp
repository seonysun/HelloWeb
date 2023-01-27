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
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	//확인 버튼은 일단 감춰놓고 시작
	$('#sh').hide()
	$('#checkBtn').click(function(){
		//입력 유효성체크
		if(id.trim()==""){
			$('#id').focus()
			return
		}
		//입력값 전송
		$.ajax({
			type:'post',
			url:'idCheck_result.jsp',
			data:{"id":id},
			success:function(result){
				let value=result.trim()
				if(value==0){
					$('#result').html('<font color=blue>'+id+'는(은) 사용 가능한 아이디입니다')
						//기존 id=result 값 초기화 후 내용 삽입
					$('#sh').show()
				} 
				else{
					$('#result').html('<font color=red>'+id+'는(은) 이미 사용중인 아이디입니다')
					$('#sh').hide()
				}
			}
		})
	})
	$('#ok').click(function(){
		parent.join_frm.id.value=$('#id').val()
			//ajax_join 파일의 join_frm의 id의 값을 데이터 받아서 채움
		parent.Shadowbox.close();
	})
})
</script>
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
			  	<td class=text-center id=result>ID를 입력하세요</td>
			  </tr>
			  <tr id=sh>
			  	<td class=text-center><input type=button value="확인" class="btn btn-sm btn-success" id=ok></td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>