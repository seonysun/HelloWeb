<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
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
      <ul class="inline">
        <li>ID&nbsp;<input type="text" name=id size=10 class="input-sm" placeholder="ID"></li>
        <li>PW&nbsp;<input type="password" name=pwd size=10 class="input-sm" placeholder="Password"></li>
        <li><input type="button" class="btn btn-lg btn-danger" value="로그인"></li>
      </ul>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="pages/gallery.html">회원가입</a></li>
          <li><a href="pages/full-width.html">아이디찾기</a></li>
          <li><a href="pages/sidebar-left.html">비밀번호찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="../food/food_location.do">맛집</a>
        <ul>
          <li><a href="../food/food_location.do">지역별 맛집 검색</a></li>
          <li><a href="pages/full-width.html">맛집 추천</a></li>
          <li><a href="pages/sidebar-left.html">맛집 예약</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">여행</a>
        <ul>
          <li><a href="pages/gallery.html">명소</a></li>
          <li><a href="pages/full-width.html">자연&관광</a></li>
          <li><a href="pages/full-width.html">쇼핑</a></li>
          <li><a href="pages/full-width.html">호텔</a></li>
          <li><a href="pages/full-width.html">게스트하우스</a></li>
          <li><a href="pages/full-width.html">여행 코스</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="pages/gallery.html">레시피</a></li>
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
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="pages/gallery.html">공지사항</a></li>
          <li><a href="pages/full-width.html">자유게시판</a></li>
          <li><a href="pages/sidebar-left.html">후기게시판</a></li>
        </ul>
      </li>
      <li><a href="#">마이페이지</a></li>
    </ul>
  </nav>
</div>
</body>
</html>