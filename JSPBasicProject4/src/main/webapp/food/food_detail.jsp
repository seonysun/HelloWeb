<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.sist.dao.*"%>
<jsp:useBean id="dao" class="com.sist.dao.FoodDAO"/>
<jsp:useBean id="rdao" class="com.sist.dao.ReplyDAO"/>
<%
	String fno=request.getParameter("fno");
	FoodVO vo=dao.foodDetailData(Integer.parseInt(fno));
	/*
	String address=vo.getAddress();
	String addr1=address.substring(0,address.lastIndexOf("지"));
	addr1=addr1.trim();
	String addr2=address.substring(address.lastIndexOf("지")+3);
	addr2=addr2.trim();
	*/
	ArrayList<ReplyVO> list=rdao.replyListData(Integer.parseInt(fno));
	int count=rdao.replyCount(Integer.parseInt(fno));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['리뷰 현황', 'Hours per Day'],
          ['맛있다',     11],
          ['괜찮다',      2],
          ['별로',  2],
        ]);
        var options = {
          title: '리뷰 현황',
          is3D: true,
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
    <script type="text/javascript">
    //사용자 Front 처리
    	/* jquery : 자바스크립트 라이브러리
    	window.onload=function(){ main } => $(function(){})
    	*/
    let i=0;
    $(function(){
    	$('.ups').click(function(){
    		$('.updates').hide(); //감추기
    		let no=$(this).attr('data-no');
    		if(i==0){
    			$(this).text("취소");
    			$('#r'+no).show();
    			i=1;
    		} else {
    			$(this).text("수정");
    			$('#r'+no).hide();
    			i=0;
    		}
    	})
    })
    </script>
</head>
<body>
	<table class="table">
		<tr>
		<%
			String poster=vo.getPoster();
			poster=poster.replace("#","&");
			StringTokenizer st=new StringTokenizer(poster,"^");
			while(st.hasMoreTokens()){
		%>
		  <td class="text-center">
		  	<img src="<%=st.nextToken() %>" style="width: 100%">
		  </td>
		<%		
			}
		%>
		</tr>
	</table>
	<div style="height: 10px"></div>
	<div class="row">
		<div class="col-sm-8">
			<table class="table">
			  <tr>
			  	<td colspan="2">
			  		<h3><%=vo.getName() %>&nbsp;<span style="color: orange"><%=vo.getScore() %></span></h3>
			  	</td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">주소</span></th>
			    <td width=85%><%=vo.getAddress() %></td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">전화</span></th>
			    <td width=85%><%=vo.getTel() %></td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">음식종류</span></th>
			    <td width=85%><%=vo.getType() %></td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">가격대</span></th>
			    <td width=85%><%=vo.getPrice() %></td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">영업시간</span></th>
			    <td width=85%><%=vo.getTime() %></td>
			  </tr>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">주차</span></th>
			    <td width=85%><%=vo.getParking() %></td>
			  </tr>
			  <%
			  	if(!vo.getMenu().equals("no")){ //메뉴가 없는 경우 no라고 저장함
			  %>
			  <tr>
			    <th class="text-right" width=15%><span style="color: gray">메뉴</span></th>
			    <td width=85%>
			    	<ul>
			    	<%
			    		st=new StringTokenizer(vo.getMenu(),"원");
			    		while(st.hasMoreTokens()) {
			    	%>
			    		<li><%=st.nextToken() %>원</li>
			    	<%
			    		}
			    	%>
			    	</ul>
			    </td>
			  </tr>
			  <%
			  	}
			  %>
			  <tr>
			  	<td colspan=2 class="text-right">
			  		<input type=button class="btn btn-xs btn-success" value="찜하기">
			  		<input type=button class="btn btn-xs btn-info" value="좋아요">
			  		<input type=button class="btn btn-xs btn-warning" value="예약">
			  		<input type=button class="btn btn-xs btn-primary" value="목록" onclick="javascript:history.back()">
			  	</td>
			  </tr>
			</table>
			<div style="height: 10px"></div>
			댓글&nbsp;(<%=count %>)
			<hr>
			<%
				String id=(String)session.getAttribute("id");
				if(count==0) {
			%>
				<span style="color: gray">등록된 댓글이 없습니다</span>
			<%
				} else {
			%>		
				<table class="table">
				  <tr>
				  	<td>
				  	<%
				  		for(ReplyVO rvo:list){
				  	%>	
				  		<table class="table">
				  		  <tr>
				  		  	<td class="text-left">♡&nbsp;<%=rvo.getName() %>&nbsp;(<%=rvo.getDbday() %>)</td>
				  		  	<td class="text-right">
				  		  	<%
				  		  		if(id!=null){
				  		  			if(id.equals(rvo.getId())){
					  		  	%>
					  		  		<span class="btn btn-xs btn-primary ups" data-no="<%=rvo.getNo() %>">수정</span>
					  		  		<a href="../reply/reply_delete.jsp?no=<%=rvo.getNo() %>&fno=<%=fno %>" class="btn btn-xs btn-danger">삭제</a>
					  		  			<!-- 실제로는 화면 변경하지 않고 동일한 화면 내에서 데이터만 변경 -> Ajax(Javascript) -->
					  		  	<%			
				  		  			}
				  		  		}
				  		  	%>
				  		  	</td>
				  		  </tr>
				  		  <tr>
				  		  	<td colspan=2 class="text-left" valign="top"><pre style="white-space: pre-wrap;background-color: white;border: none"><%=rvo.getMsg() %></pre></td>
				  		  </tr>
				  		  <tr style="display: none" id="r<%=rvo.getNo() %>" class="updates">
						  	<td colspan=2>
						  	  <form method=post action="../reply/reply_update.jsp">
						  	  	<input type=hidden name=no value="<%=rvo.getNo() %>">
						  	    <input type=hidden name=fno value="<%=vo.getFno() %>">
						  	    	<!-- 화면 이동(수정 등) 시 필요한 정보, 화면에 출력할 필요는 없음 -> hidden -->
						  		<textarea rows=4 cols=50 name="msg" style="float: left"><%=rvo.getMsg() %></textarea>
						  		<input type=submit value="댓글수정" class="btn btn-sm btn-danger" style="float: left;height: 88px">
						  	  </form>
						  	</td>
			  	  		  </tr>
				  		</table>	
				  	<%
				  		}
				  	%>
				  	</td>
				  </tr>
				</table>
			<%
				}
			%>
			<%
				if(id!=null){ //로그인이 성공하면 댓글 작성 가능
			%>
			<table class="table">
			  <tr>
			  	<td>
			  	  <form method=post action="../reply/reply_insert.jsp">
			  	    <input type=hidden name=fno value="<%=vo.getFno() %>">
			  		<textarea rows=4 cols=55 name="msg" style="float: left"></textarea>
			  		<input type=submit value="댓글쓰기" class="btn btn-sm btn-danger" style="float: left;height: 88px">
			  	  </form>
			  	</td>
			  </tr>
			</table>
			<%
				}
			%>
		</div>
		<div class="col-sm-4">
			
			<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
		</div>
	</div>
</body>
</html>