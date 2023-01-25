<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	//배열을 객체 형식으로 변경 -> JSON, JSONP
	let sawon={sabun:1,name:"홍길동",age:20,dept:"영업부",pay:3500}
	document.write("<h1>자바스크립트 객체 사용법 1(JSON)</h1>")
	document.write("사번:"+sawon.sabun+"<br>")
	document.write("이름:"+sawon.name+"<br>")
	document.write("나이:"+sawon.age+"<br>")
	document.write("부서:"+sawon.dept+"<br>")
	document.write("연봉:"+sawon.pay+"<br>")
	
	document.write("<h1>자바스크립트 객체 사용법 2(JSON)</h1>")
	document.write("사번:"+sawon['sabun']+"<br>")
	document.write("이름:"+sawon['name']+"<br>")
	document.write("나이:"+sawon['age']+"<br>")
	document.write("부서:"+sawon['dept']+"<br>")
	document.write("연봉:"+sawon['pay']+"<br>")
	
	document.write("<h1>자바스크립트 객체 사용법 3(JSON)</h1>")
	for(let key in sawon){
		document.write(key+":"+sawon[key]+"<br>")
	}
}
</script>
</head>
<body>

</body>
</html>