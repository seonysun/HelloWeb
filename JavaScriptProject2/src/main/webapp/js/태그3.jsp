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
	margin-top: 30px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
</style>
<script type="text/javascript">
let calc=()=>{
	let price=document.querySelector("#price").getAttribute("value") //$("#price").attr("value")
												//속성값 선택
	let count=document.querySelector("#count").value //$("#count").val()
	let total=price*count
	document.querySelector("#total").innerText=total //$("#total").text(total)
									//태그 사이에 값 입력
}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
			  <tr>
			  	<td width=30% class="text-center" rowspan=7>
			  		<img src="https://recipe1.ezmember.co.kr/cache/data/goods/22/03/13/1000026613/1000026613_detail_060.jpg" style="width:400px">
			  	</td>
			  	<td colspan=2>
			  		<h3>[美친특가] 폴란드 명품 수입그릇 아티스티나 그라탕볼 골라담기 (오븐,전자렌지,식기세척기 가능)</h3>
			  		<sub style="color: gray">100% 핸드메이팅 폴란드 아티스티나 패턴그릇!</sub>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2>
			  		<h3><span style="color: magenta">53%</span>&nbsp;35,600원</h3>
			  		<sub style="color: gray">76,000원</sub>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2>
			  		<span style="color: green">첫구매할인가&nbsp;33,820원</span>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2>
			  		배송: 3,000원 (50,000원이상 무료배송)
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2>
			  		적립: 178원 적립 (모든 회원 구매액의 0.5% 적립)
			  	</td>
			  </tr>
			  <tr>
			  	<td width=30%>
			  		<span id=price value="35600">35,600</span>
			  	</td>
			  	<td width=40%>
			  		<select id=count class="input-sm" onchange="calc()">
			  			<option selected value="1">1개</option>
			  			<option value="2">2개</option>
			  			<option value="3">3개</option>
			  			<option value="4">4개</option>
			  			<option value="5">5개</option>
			  		</select>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2 class="text-right">
			  		주문금액&nbsp;<span style="color: green;font-size: 20px;" id="total"></span>원
			  	</td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>