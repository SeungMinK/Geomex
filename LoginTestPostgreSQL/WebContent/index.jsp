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

	<h1>�α��� ���� ! main page</h1>
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

	
</body>
</html>