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
.container-fluid{
	margin-top: 30px;
}
.row{
	width: 100%;
	margin: 0px auto;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').click(function(){
		let ss=$('#ss').val()
		if(ss.trim()==""){
			$('#ss').focus()
			return
		}
		$.ajax({
			type:'post',
			url:'food_find.jsp',
			data:{"ss":ss},
			success:function(result){
				$('.col-md-8').html(result)
			}
		})
	})
})
</script>
</head>
<body>
	<div class=container-fluid>
		<div class=row>
			<table class=table>
			  <tr>
			  	<td>
					<input type=text id=ss class=input-sm size=30>
					<input type=button id=findBtn class="btn btn-sm btn-danger" value="검색">
			  	</td>
			  </tr>
			</table>
		</div>
		<div style="height:10px"></div>
		<div class="row">
			<div class="col-md-8">
			
			</div>
			<div class="col-md-4">
			
			</div>
		</div>
	</div>
</body>
</html>