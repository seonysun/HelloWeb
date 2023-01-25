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
	margin-top: 100px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
window.onload=function(){
	const sawons=[ //ArrayList
		{sabun:1,name:"홍길동",dept:"개발부",loc:"서울",job:"대리"}, //<SawonVO>
		{sabun:2,name:"심청이",dept:"총무부",loc:"서울",job:"사원"},
		{sabun:3,name:"박문수",dept:"자재부",loc:"서울",job:"부장"},
		{sabun:4,name:"이순신",dept:"영업부",loc:"서울",job:"과장"},
		{sabun:5,name:"강감찬",dept:"기획부",loc:"서울",job:"대리"}
	]
	let html=' ';
	for(let sa of sawons){
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	}
	/* 동일 코딩(for of)
	sawons.forEach((sa)=>{
		html+='<tr>'
			+'<td class="text-center">'+sa.sabun+'</td>'
			+'<td class="text-center">'+sa.name+'</td>'
			+'<td class="text-center">'+sa.dept+'</td>'
			+'<td class="text-center">'+sa.loc+'</td>'
			+'<td class="text-center">'+sa.job+'</td>'
			+'</tr>'
	})
	*/
	document.querySelector('tbody').innerHTML=html;
		//tbody 태그 안에 html이라는 이름의 HTML을 대입
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
			  <thead>
				  <tr class="danger">
				  	<th class="text-center">사번</th>
				  	<th class="text-center">이름</th>
				  	<th class="text-center">부서</th>
				  	<th class="text-center">근무지</th>
				  	<th class="text-center">직위</th>
				  </tr>	
			  </thead>
			  <tbody>
			  
			  </tbody>
			</table>
		</div>
	</div>
</body>
</html>