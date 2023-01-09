<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
  <%
  	try {
  		Class.forName("oracle.jdbc.driver.OracleDriver");
  		final String URL="jdbc:oracle:thin:@localhost:1521:XE";
  		Connection conn=DriverManager.getConnection(URL,"hr","happy");
  		String sql="SELECT hakbun,name,kor,eng,math,(kor+eng+math) total,ROUND((kor+eng+math)/3,2) avg "
  				+"FROM student "
  				+"ORDER BY hakbun";
  		PreparedStatement ps=conn.prepareStatement(sql);
  		ResultSet rs=ps.executeQuery();
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table width=700 border=1>
			<tr>
				<th>학번</th>
				<th>이름</th>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>총점</th>
				<th>평균</th>
			</tr>
			<%
			while(rs.next()){
				int h=rs.getInt(1);
				if(h==3){
			%>
				<tr>
					<td><%=rs.getInt(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getInt(3) %></td>
					<td><%=rs.getInt(4) %></td>
					<td><%=rs.getInt(5) %></td>
					<td><%=rs.getInt(6) %></td>
					<td><%=rs.getDouble(7) %></td>
				</tr>
			<%
				} else if(h==4){
			%>
				<tr bgcolor="pink">
					<td><%=rs.getInt(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getInt(3) %></td>
					<td><%=rs.getInt(4) %></td>
					<td><%=rs.getInt(5) %></td>
					<td><%=rs.getInt(6) %></td>
					<td><%=rs.getDouble(7) %></td>
				</tr>
			<%		
				} else if(h==5){
			%>
				<tr bgcolor="green">
					<td><%=rs.getInt(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getInt(3) %></td>
					<td><%=rs.getInt(4) %></td>
					<td><%=rs.getInt(5) %></td>
					<td><%=rs.getInt(6) %></td>
					<td><%=rs.getDouble(7) %></td>
				</tr>
			<%		
				}
			}
				rs.close();
				ps.close();
				conn.close();
			%>
		</table>
	</center>
</body>
</html>
  <%
  	} catch(Exception ex){}
  %>