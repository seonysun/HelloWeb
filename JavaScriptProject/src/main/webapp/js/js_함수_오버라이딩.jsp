<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	func()
	func2()
}
function func(){
	document.write("func Call..(1)<br>")
}
function func(){
	document.write("func Call..(2)<br>")
}
let func2=function(){
	document.write("func2 Call..(1)<br>")
}
function func2(){
	document.write("func2 Call..(2)<br>")
}
</script>
</head>
<body>

</body>
</html>