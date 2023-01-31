<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 & 여행</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#updateBtn').click(function(){
		let name=$('#name').val()
		if(name.trim()===""){
			$('#name').focus()
			return
		}
		let subject=$('#subject').val()
		if(subject.trim()===""){
			$('#subject').focus()
			return
		}
		let content=$('#content').val()
		if(content.trim()===""){
			$('#content').focus()
			return
		}
		let pwd=$('#pwd').val()
		if(pwd.trim()===""){
			$('#pwd').focus()
			return
		}
		
		let no=$('#no').val()
		let queryString=$('#update_frm').serialize() 
						//form 태그 내의 모든 데이터 모아줌
		$.ajax({
			type:'post',
			url:'../freeboard/update_ok.do',
			data:queryString,
			success:function(result){
				let res=result.trim()
				if(res=='no'){
					alert("비밀번호가 틀립니다!\n다시 입력하세요")
					$('#pwd').val("")
					$('#pwd').focus()
				}else{
					location.href="../freeboard/detail.do?no="+no
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
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
    </ul>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
  <h2 class="sectiontitle">수정하기</h2>
  	<div style="height: 5px"></div>
   <form id="update_frm">
	<table class="table">
	  <tr>
	  	<th width=15% class="text-right">이름</th>
	  	<td width=80%>
	  		<input type=text name=name size=20 class="input-sm" value="${vo.name }" id=name>
	  		<input type=hidden name=no value="${vo.no }" id=no>
	  	</td>
	  </tr>
	  <tr>
	  	<th width=15% class="text-right">제목</th>
	  	<td width=80%>
	  		<input type=text name=subject size=60 class="input-sm" value="${vo.subject }" id=subject>
	  	</td>
	  </tr>
	  <tr>
	  	<th width=15% class="text-right">내용</th>
	  	<td width=80%>
	  		<textarea rows=10 cols=60 name=content id=content>${vo.content }</textarea>
	  	</td>
	  </tr>
	  <tr>
	  	<th width=15% class="text-right">비밀번호</th>
	  	<td width=80%>
	  		<input type=password name=pwd size=15 class="input-sm" id=pwd>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan=2 class="text-center">
	  		<input type=submit value="수정하기" class="btn btn-sm btn-danger" id=updateBtn>
	  		<input type=button value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
	  	</td>
	  </tr>
	</table>
   </form>
  </main>
</div>
</body>
</html>