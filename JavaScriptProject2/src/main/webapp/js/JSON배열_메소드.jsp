<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
1. 객체 배열
	1) JSON {} : VO
	2) Array [] : ArrayList
		- push : 배열에 값 추가
		- pop : 배열의 마지막 값 제거
		- slice(start, end)(*) : 원하는 위치까지 잘라서 새로운 배열 생성
			- index번호(0부터 시작) start부터 end 전까지
		- indexof : 데이터 위치 확인
		- find : 검색
		- delete : 배열 완전 삭제
		- length : 저장된 갯수 읽기
 -->
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
	margin-top: 30px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
//전역변수 -> 주로 자바에서 보내주는 값 사용
let sawons=[
	{sabun:1,name:"홍길동",dept:"개발부",job:"대리",pay:4500},
	{sabun:2,name:"심청이",dept:"총무부",job:"사원",pay:3800},
	{sabun:3,name:"박문수",dept:"자재부",job:"부장",pay:6000},
	{sabun:4,name:"이순신",dept:"영업부",job:"과장",pay:5200},
	{sabun:5,name:"강감찬",dept:"기획부",job:"대리",pay:4100}
]
window.onload=function(){
	let names=[
		{name:"홍길동"},
		{name:"심청이"},
		{name:"박문수"},
		{name:"이순신"},
		{name:"강감찬"}
	]
	//갯수 확인	
	console.log("인원수:"+names.length)
	//데이터 입력
	names.push({name:"을지문덕"})
	console.log(names)
	//마지막 데이터 삭제
	names.pop()
	console.log(names)
	//전체 삭제
	names.delete
	
	//function 사용
	sawonList()
	sawonDetail(3)
	sawonInsert()
	sawonDelete()
	sawonSlice()
}
const sawonList=function(){
	console.log(sawons)
}
const sawonDetail=(sabun)=>{
	let sawon=sawons.find(sa=>sa.sabun==sabun)
	console.log(sawon)
}
const sawonInsert=function(){
	sawons.push({sabun:6,name:"을지문덕",dept:"기획부",job:"대리",pay:4100})
	console.log(sawons)
}
const sawonDelete=()=>{
	sawons.pop()
	console.log(sawons)
}
const sawonSlice=()=>{
	let sa=sawons.slice(1,3)
	console.log(sa)
}
</script>
</head>
<body>

</body>
</html>