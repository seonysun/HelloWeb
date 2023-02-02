<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		let id=$('#log_id').val()
		if(id.trim()===""){
			$('#log_id').focus()
			return
		}
		let pwd=$('#log_pwd').val()
		if(pwd.trim()===""){
			$('#log_pwd').focus()
			return
		}
		$.ajax({
			type:'post',
			url:'../member/login.do',
			data:{"id":id,"pwd":pwd},
			success:function(result){
				let res=result.trim()
				if(res==='NOID'){
					alert("존재하지 않는 ID입니다!")
					$('#log_id').val("")
					$('#log_pwd').val("")
					$('#log_id').focus()
				}else if(res==='NOPWD'){
					alert("비밀번호를 확인해주세요!")
					$('#log_pwd').val("")
					$('#log_pwd').focus()
				}else{
					location.href="../main/main.do"
				}
			}
		})
	})
	$('#logoutBtn').click(function(){
		$.ajax({
			type:'post',
			url:'../member/logout.do',
			success:function(result){
				location.href="../main/main.do"
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">서울 맛집 & 서울 여행</a></h1>
      	<!-- ".do" : 데이터 처리할 수 있는 Model에 연결 -> request로 받은 jsp 파일을 보여줌
      		 ".jsp" : 데이터 없는 jsp 파일 보여줌 -->
    </div>
    <div class="fl_right">
     <c:if test="${sessionScope.id==null }">
      <ul class="inline">
        <li>ID&nbsp;<input type="text" name=id size=10 class="input-sm" placeholder="ID" id=log_id></li>
        <li>PW&nbsp;<input type="password" name=pwd size=10 class="input-sm" placeholder="Password" id=log_pwd></li>
        <li><input type="button" class="btn btn-lg btn-danger" value="로그인" id=logBtn></li>
      </ul>
     </c:if>
     <c:if test="${sessionScope.id!=null }">
      <ul class="inline">
      	<li>${sessionScope.id }(${sessionScope.admin=='y'?"관리자":"일반사용자" })님 로그인중입니다</li>
        <li><input type="button" class="btn btn-lg btn-danger" value="로그아웃" id=logoutBtn></li>
      </ul>
     </c:if>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
       <c:if test="${sessionScope.id==null }">
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="../member/idfind.do">아이디찾기</a></li>
          <li><a href="../member/pwdfind.do">비밀번호찾기</a></li>
        </ul>
       </c:if>
       <c:if test="${sessionScope.id!=null }">
        <ul>
          <li><a href="../member/join_update.do">회원수정</a></li>
          <li><a href="../member/join_delete.do">회원탈퇴</a></li>
        </ul>
       </c:if>
      </li>
      <li><a class="drop" href="../food/food_location.do">맛집</a>
        <ul>
          <li><a href="../food/food_location.do">지역별 맛집 검색</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/full-width.html">맛집 추천</a></li>
          <li><a href="pages/sidebar-left.html">맛집 예약</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="drop" href="../seoul/seoul_list.do?type=1">여행</a>
        <ul>
          <li><a href="../seoul/seoul_list.do?type=1">명소</a></li>
          									<!-- type값은 반드시 넘어가므로 default 설정 필요 없음 -->
          <li><a href="../seoul/seoul_list.do?type=2">자연&관광</a></li>
          <li><a href="../seoul/seoul_list.do?type=3">쇼핑</a></li>
          <li><a href="../seoul/seoul_list.do?type=4">호텔</a></li>
          <li><a href="../seoul/seoul_weather.do">오늘의 날씨</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/full-width.html">여행 코스</a></li>
         </c:if>
        </ul>
      </li>
      <li><a class="../recipe/list.do" href="#">레시피</a>
        <ul>
          <li><a href="../recipe/list.do">레시피</a></li>
          <li><a href="pages/full-width.html">쉐프</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../goods/goods_all.do">스토어</a>
        <ul>
          <li><a href="../goods/goods_all.do">전체</a></li>
          <li><a href="../goods/goods_best.do">베스트</a></li>
          <li><a href="../goods/goods_new.do">신상품</a></li>
          <li><a href="../goods/goods_sp.do">특가</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../freeboard/list.do">커뮤니티</a>
        <ul>
          <li><a href="../notice/list.do">공지사항</a></li>
          <li><a href="../freeboard/list.do">자유게시판</a></li>
         <c:if test="${sessionScope.id!=null }">
          <li><a href="pages/sidebar-left.html">묻고답하기</a></li>
         </c:if>
        </ul>
      </li>
       <c:if test="${sessionScope.id!=null }">
         <c:if test="${sessionScope.admin=='n' }">
      <li><a href="../mypage/mypage_main.do">마이페이지</a></li>
         </c:if>
         <c:if test="${sessionScope.admin=='y' }">
      <li><a href="../adminpage/admin_main.do">관리자페이지</a></li>
         </c:if>
       </c:if>
    </ul>
  </nav>
</div>
</body>
</html>