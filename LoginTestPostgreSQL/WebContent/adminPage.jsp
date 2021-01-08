<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="JDBC.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>관리자 페이지</title>
</head>
<body>
<h1> 관리자 페이지 입니다.</h1>

<form >

	<button>데이터 테이블 보기</button>
	<br>
	<%
	DAO dao = new DAO();
	dao.connectDb();
	dao.selectDataQuerry();
	Object [][]array = dao.getArray(); 
	int index =0;
	%>
	<table border="1px">
		<thead><td> 순번 </td><td> 이름 </td> <td> ID </td> <td> PW </td> <td> </td></thead>
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
					<td> <button type="button"  onclick="location.href='delete.jsp?id=<%out.print(array[i][1]);%>'">삭제</button></td>
			
			</tr>
		<% 
		
		}
		
		%>
	
	</table>
				
						
	

</form>





</body>
</html>