<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="JDBC.DAO"%>
<%@ page import="JDBC.VO"%>
<html>
<head>
<meta charset="EUC-KR">
<title>JDBC 연결 공부</title>
</head>
<body>

	<h1>로그인 성공 ! main page</h1>
	<hr>
	<form action="${JDBC.DAO}" method="post">
	<div> 점심 도시락 메뉴 설문 조사(미완성)</div>
	<input type="radio" name="chkMenu" value="menu1">돈까스 <br>
	<input type="radio" name="chkMenu" value="menu2">브런치 <br>
	<input type="radio" name="chkMenu" value="menu3">육회덮밥 <br>
	<input type="radio" name="chkMenu" value="menu4">장어덮밥 <br>
	<button type="submit"> 제출 하기</button>
	</form>
	<hr>

	
</body>
</html>