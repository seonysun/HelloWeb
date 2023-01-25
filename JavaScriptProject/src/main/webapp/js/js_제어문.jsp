<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
4. 제어문
	1) 조건문
		- 단일조건문 => 삼항연산자
			if(조건문){
				조건 true일 때 처리
			} else {
				조건 false일 때 처리
			}
		- 다중조건문
			if(조건문1){
			} else if(조건문2) {
			} else {}
	2) 선택문
		- switch~ case
	3) 반복문
		(1) for : 출력용(반복 횟수가 정해져있을 때), 주로 사용
			- 일반 for문
				for(초기갑;조건식;증가식){
					반복수행문장
				}
			- for of : 배열에 실제 값을 가지고 옴
				for(let 변수명 of 배열명){
				}
			- for in : 인덱스 번호를 가지고 옴
				for(let 변수명 in 배열명){
				}
			- forEach(*)
				배열명.forEach(function(변수명){
				})
			- map(*)
				배열명.map(function(변수명)){
				}
				배열명.map((변수명)=>{
				})
		(2) while : 선조건(수행 안함도 가능), 무한 루프(반복 횟수가 없을 때) 
			초기값
			while(조건문){
				반복수행문장
				증가식
			}
		(3) do~ while : 후조건(무조건 1번 수행), 사용 빈도 거의 없음
			do{
				반복수행문장
				증가식
			} while(조건문)
	4) 반복제어문
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=function(){
	let a=11
	/*
	if(a===10){
		alert("a는 10입니다.")
	}
	alert("a는 10이 아닙니다.")
	
	if(a%2==0)
		alert("a는 짝수입니다.")
	else alert("a는 홀수입니다.")
	*/
	
	if(a%2==0)
		alert("a는 짝수입니다.")
	else if(a%2!==0)
		alert("a는 홀수입니다.")
	else alert("a는 바보")
}
</script>
</head>
<body>

</body>
</html>