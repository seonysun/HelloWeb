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
	width: 350px;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#okBtn').hide()
	$('#idBtn').on('click',function(){
		let id=$('#id').val()
		if(id.trim()==""){
			$('#id').focus()
			return
		}
		$.ajax({
			type:'post',
			url:'../member/idcheck_result.do',
			data:{"id":id},
			success:function(result){
				let res=Number(result.trim())
				if(res===0){
					$('#result').html('<font color=blue>'+id+"는(은) 사용 가능한 아이디입니다</font>")
					$('#okBtn').show()
				} else{
					$('#result').html('<font color=red>'+id+"는(은) 이미 사용중인 아이디입니다</font>")
					$('#okBtn').hide()
				}
			}
		})
	})
	$('#okBtn').click(function(){
		parent.join_frm.id.value=$('#id').val()
		parent.Shadowbox.close()
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="Row">
			<table class="table">
			  <tr>
			  	<td>
			  		ID: <input type=text name=id id=id size=15 class="input-sm">
			  		<input type=button value="아이디중복체크" class="btn btn-sm btn-primary" id=idBtn>
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		<span id=result></span>
			  	</td>
			  </tr>
			  <tr>
			  	<td class=text-center>
			  		<input type=button value="확인" class="btn btn-sm btn-success" id=okBtn>
			  	</td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>