<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let i=1;
	document.write("<h1>do~while</h1>")
	do{
		document.write("i="+i+"<br>")
		i++;
	} while(i<=10)
	
	document.write("<h1>while</h1>")
	while(i<=10){
		document.write("i="+i+"<br>")
		i++;
	}
	
	document.write("<h1>for</h1>")
	for(i=1;i<=10;i++){
		document.write("i="+i+"<br>")
	}
	
	document.write("<h1>for=break</h1>")
	for(i=1;i<=10;i++){
		if(i==5) break; //반복문 종료
		document.write("i="+i+"<br>")
	}
	
	document.write("<h1>for=continue</h1>")
	for(i=1;i<=10;i++){
		if(i==5) continue; //해당 조건 제외하고 계속 진행
		document.write("i="+i+"<br>")
	}
}
</script>
</head>
<body>

</body>
</html>