<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="JDBC.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>������ ������</title>
</head>
<body>
<h1> ������ ������ �Դϴ�.</h1>

<form >

	<button>������ ���̺� ����</button>
	<br>
	<%
	DAO dao = new DAO();
	dao.connectDb();
	dao.selectDataQuerry();
	Object [][]array = dao.getArray(); 
	int index =0;
	%>
	<table border="1px">
		<thead><td> ���� </td><td> �̸� </td> <td> ID </td> <td> PW </td> <td> </td></thead>
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
					<td> <button type="button"  onclick="location.href='delete.jsp?id=<%out.print(array[i][1]);%>'">����</button></td>
			
			</tr>
		<% 
		
		}
		
		%>
	
	</table>
				
						
	

</form>





</body>
</html>