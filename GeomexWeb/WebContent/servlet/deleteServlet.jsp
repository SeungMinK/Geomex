<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
//dao 생성 및 db연결
DAO dao = new DAO();
dao.connectDb();
//form으로 전달받은 파라미터 가져오기
String id=request.getParameter("id");

if(!(id.equals("admin"))) {//admin은 삭제 불가
	dao.deleteQuery(id); // id는 primary key 여서 중복이 불가능 함으로 id를 key로 data 삭제
	
}

//화면전환
response.sendRedirect("../view/admin/adminPage.jsp");


%>



</body>
</html>