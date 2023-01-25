<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	send(callback)
}
function send(callback){
	for(let i=1;i<10;i++){
		callback()
	}
}
function callback(){
	document.write("callback Call..")
}
</script>
</head>
<body>

</body>
</html>