<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="JDBC.DAO"%>
<%@ page import="JDBC.VO"%>
<html>
<head>
<meta charset="EUC-KR">
<title>JDBC ���� ����</title>
</head>
<body>

	<h1>JDBC�� Ȱ���Ͽ� Postgres SQL ������ ���</h1>
	<hr>
	<form action="${JDBC.DAO}" method="post">
	<div> ���� ���ö� �޴� ���� ����(�̿ϼ�)</div>
	<input type="radio" name="chkMenu" value="menu1">��� <br>
	<input type="radio" name="chkMenu" value="menu2">�귱ġ <br>
	<input type="radio" name="chkMenu" value="menu3">��ȸ���� <br>
	<input type="radio" name="chkMenu" value="menu4">���� <br>
	<button type="submit"> ���� �ϱ�</button>
	</form>
	<hr>
<%

	 DAO conDb = new DAO();
	 conDb.connectDb();
	 
	 
	 Object []array =conDb.getObject();
	 
		for(int i =0; i<array.length;i++){
			out.print(array[i]+" ");
		}
		
	 %>
	
</body>
</html>