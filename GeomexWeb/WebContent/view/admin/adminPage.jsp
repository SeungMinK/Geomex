<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>관리자 페이지</title>
</head>
<body>
<h1> 관리자 페이지 입니다.</h1>
<%
if((session.getAttribute("loginId")==null)){
	
	response.sendRedirect("login.jsp");
	
}
else if(!session.getAttribute("loginId").toString().equals("admin")){
	response.sendRedirect("../login/login.jsp");
}

%>
<form >

	<button>데이터 테이블 보기</button>
	<br>
	<%
	DAO dao = new DAO();
	dao.connectDb();
	dao.selectDataQuerry();
	Object [][]array = dao.getArray(); 
	int index =0;
	if(array != null){
	%>
	<table border="1px">
		<thead><td> 순번 </td><td> 이름 </td> <td> ID </td> <td> PW </td> </thead>
		<%
		for(int i = 0; i<array.length;i++){
				%>
				<tr>
				<td><% out.print(i+1);%></td>
					<%
					for(int j=0;j<array[0].length;j++){
					%>
					<td><% out.print(array[i][j]);%></td>

					<%
					}
					%>
					<td> <button type="button"  onclick="location.href='../../servlet/deleteServlet.jsp?id=<%out.print(array[i][1]);%>'">삭제</button></td>
					<td> <button type="button"  onclick="location.href='update.jsp?data=<%out.print(array[i][0]+","+array[i][1]+","+array[i][2]);%>'">수정</button></td>
			
			</tr>
		<% 
		
		}}
	else{
		out.print("<h1>데이터 테이블이 비어있습니다.</h1>");
	}
		
		%>
	
	</table>
				
						
	

</form>

<button onclick="location.href='../home/home.jsp'"> index page 가기</button>





</body>
</html>