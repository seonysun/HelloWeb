<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type:'post',
		url:'../reserve/food_list.do',
		data:{"fd":'한식'},
		success:function(response){
			$('#food_list').html(response)
		}
	})
	$('.foods').click(function(){
		let fd=$(this).text()
		$.ajax({
			type:'post',
			url:'../reserve/food_list.do',
			data:{"fd":fd},
			success:function(response){
				$('#food_list').html(response)
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
    <h2 class="sectiontitle">맛집 예약</h2>
  	<div style="height: 5px"></div>
    <table class=table style="width: 100%;height: 700px">
    	<tr>
    	  <td class="success" width="30%" height=500>
    	  	<table class=table>
    	  		<caption><h3>맛집 정보</h3></caption>
    	  		<tr>
    	  		  <td>
    	  		  	<span class="btn btn-xs btn-danger foods">한식</span>
    	  		  	<span class="btn btn-xs btn-info foods">양식</span>
    	  		  	<span class="btn btn-xs btn-primary foods">중식</span>
    	  		  	<span class="btn btn-xs btn-success foods">일식</span>
    	  		  	<span class="btn btn-xs btn-warning foods">기타</span>
    	  		  </td>
    	  		</tr>
    	  		<tr>
    	  		  <td>
    	  		  	<div id="food_list" style="height: 450px;overflow-y: auto">
    	  		  											<!-- 칸을 넘어가면 자동 스크롤바 생성 -->
    	  		  	</div>
    	  		  </td>
    	  		</tr>
    	  	</table>
    	  </td>
    	  <td class="info" width="40%" height=500>
    	  	<table class=table>
    	  		<caption><h3>예약일 정보</h3></caption>
    	  		<tr>
    	  		  <td>
    	  		  	<div id="select_date"></div>
    	  		  </td>
    	  		</tr>
    	  	</table>
    	  </td>
    	  <td class="danger" width="30%" rowspan=2 height=700>
    	  	<table class=table>
    	  		<caption><h3>예약 정보</h3></caption>
    	  		<tr>
    	  		  <td class=text-center colspan=2>
    	  		  	<img style="width: 250px;height: 200px" id="food_img">
    	  		  </td>
    	  		</tr>
    	  		<tr>
    	  		  <td class="text-left" colspan=2><span id="food_name" style="color: black"></span></td>
    	  		</tr>
    	  		<tr>
    	  		  <th width=30%>예약일</th>
    	  		  <td width=70%><span id="r_day"></span></td>
    	  		</tr>
    	  	</table>
    	  </td>
    	</tr>
    	<tr>
    	  <td class="default" width="35%" height=200>
    	  	<table class=table>
    	  		<caption><h3>시간 정보</h3></caption>
    	  	</table>
    	  </td>
    	  <td class="warning" width="35%" height=200>
    	  	<table class=table>
    	  		<caption><h3>인원 정보</h3></caption>
    	  	</table>
    	  </td>
    	</tr>
    </table>
  </main>
</div>
</body>
</html>