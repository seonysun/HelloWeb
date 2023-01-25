<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	const names=["홍길동",20,'A',30.5,"영업",3500] //배열 내 데이터형 혼용 가능
	document.write("<h1>===일반 for===</h1>")
	for(let i=0;i<names.length;i++){
		document.write(names[i]+"&nbsp;")
	}
	
	document.write("<h1>===for of===</h1>")
	for(let f of names){ //for(String s:names)
		document.write(f+"&nbsp;")
	}
	
	document.write("<h1>===for in===</h1>")
	for(let n in names){ //for(String s:names)
		document.write(names[n]+"&nbsp;")
	}
	
	document.write("<h1>===map 1===</h1>")
	names.map(function(f){
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===map 2===</h1>")
	names.map((f)=>{
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===forEach 1===</h1>")
	names.forEach(function(f){
		document.write(f+"&nbsp;")
	})
	
	document.write("<h1>===forEach 2===</h1>")
	names.forEach((f)=>{
		document.write(f+"&nbsp;")
	})
}
</script>
</head>
<body>

</body>
</html>