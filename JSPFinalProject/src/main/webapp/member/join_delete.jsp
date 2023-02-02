<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#delBtn').click(function(){
		let pwd=$('#pwd').val()
		if(pwd.trim()===""){
			$('#pwd').focus()
			return
		}
		$.ajax({
			type:'post',
			url:'../member/join_delete_ok.do',
			data:{"pwd":pwd},
			success:function(response){
				let res=response.trim()
				if(res==='n'){
					alert("비밀번호를 다시 확인해주세요")
					$('#pwd').val("")
					$('#pwd').focus()
				}else{
					alert("회원 탈퇴가 완료되었습니다")
					location.href="../main/main.do"
				}
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li>회원탈퇴</li>
    </ul>
    <!-- ################################################################################################ --> 
   </div>
</div>
<div class="wrapper row3">
   <main class="container clear">
   <h2 class="sectiontitle">회원탈퇴</h2>
   	 <table class=table>
   	   <tr>
   	   	<td class=text-center inline>
   	   		비밀번호: <input type=password size=20 class="input-sm" id=pwd>
   	   	</td>
   	   </tr>
   	   <tr>
   	   	<td>
   	   		<input type=button value="회원탈퇴" class="btn btn-sm btn-danger" id=delBtn>
   	   		<input type=button value="취소" class="btn btn-sm btn-primary" onclick="javascript:history.back()">
   	   	</td>
   	   </tr>
   	 </table>
   </main>
</div>
</body>
</html>