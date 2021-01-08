<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="JDBC.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
DAO dao = new DAO();
dao.connectDb();
String id=request.getParameter("id");
dao.deleteQuery(id);
response.sendRedirect("adminPage.jsp");


%>



</body>
</html>