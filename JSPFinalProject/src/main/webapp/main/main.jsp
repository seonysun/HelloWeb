<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>맛집 & 여행</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../shadow/css/shadowbox.css">
<script type="text/javascript" src="../shadow/js/shadowbox.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=e7b9d67490320db6911ad3cb1c1e9e18&libraries=services"></script>
<link href="../css/layout.css" rel="stylesheet" type="text/css" media="all">
</head>
<body id="top">
<jsp:include page="header.jsp"></jsp:include>
<!-- ############################################## Header ############################################## -->

<!-- 헤더와 푸터는 고정, 가운데 파일만 계속해서 바꿔서 들여옴 -->
<jsp:include page="${main_jsp }"></jsp:include> 

<!-- ############################################## Home ############################################## -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- ############################################## Footer ############################################## -->

<a id="backtotop" href="#top"><i class="fa fa-chevron-up"></i></a> 
<!-- JAVASCRIPTS --> 
<script src="../js/jquery.min.js"></script> 
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="../js/jquery.backtotop.js"></script> 
<script src="../js/jquery.mobilemenu.js"></script> 
<script src="../js/jquery.flexslider-min.js"></script>
</body>
</html>