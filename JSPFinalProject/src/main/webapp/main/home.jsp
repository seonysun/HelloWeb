<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 & 여행</title>
</head>
<body>
<div class="wrapper row3">
  <div id="slider" class="clear"> 
    <div class="flexslider basicslider">
      <ul class="slides">
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back1.jpg" style="width:978px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back2.jpg" style="width:978px;height:400px"></a></li>
        <li><a href="#"><img class="radius-10" src="../images/demo/slides/back3.jpg" style="width:978px;height:400px"></a></li>
      </ul>
    </div>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body -->
    <ul class="nospace group btmspace-80">
      <li class="one_third first">
        <article class="service"><i class="icon fa fa-ambulance"></i>
          <h6 class="heading"><a href="#">맛집</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-h-square"></i>
          <h6 class="heading"><a href="#">여행</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
      <li class="one_third">
        <article class="service"><i class="icon fa fa-hospital-o"></i>
          <h6 class="heading"><a href="#">레시피</a></h6>
          <p>Aenean semper elementum tellus, ut placerat leo. Quisque vehicula, urna sit amet.</p>
          <footer><a href="#">Read More &raquo;</a></footer>
        </article>
      </li>
    </ul>
    <h2 class="sectiontitle">믿고 보는 맛집리스트</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
       <c:forEach var="vo" items="${list }" varStatus="s">
        <c:if test="${s.index>=0 && s.index<12 }">
	        <li>
	         <a href="../food/food_list.do?cno=${vo.cno }">
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption>${vo.title }</figcaption>
	          </figure>
	         </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">지역별 인기 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s">
        <c:if test="${s.index>=12 && s.index<18 }">
	        <li>
	         <a href="../food/food_list.do?cno=${vo.cno }">
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption>${vo.title }</figcaption>
	          </figure>
	         </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">메뉴별 인기 맛집</h2>
    <div class="flexslider carousel basiccarousel btmspace-80">
      <ul class="slides">
        <c:forEach var="vo" items="${list }" varStatus="s">
        <c:if test="${s.index>=18 && s.index<30 }">
	        <li>
	         <a href="../food/food_list.do?cno=${vo.cno }">
	          <figure><img class="radius-10 btmspace-10" src="${vo.poster }" title="${vo.subject }">
	            <figcaption>${vo.title }</figcaption>
	          </figure>
	         </a>
	        </li>
        </c:if>
       </c:forEach>
      </ul>
    </div>
    <h2 class="sectiontitle">최근 방문 맛집</h2>
      <div class=inline>
   		<c:forEach var="vo" items="${cList }" varStatus="s">
   			<c:if test="${s.index<9 }">
   				<img src="${vo.poster }" style="width: 100px;height: 100px">
   			</c:if>
   		</c:forEach>
      </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>