<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<%@ page import="servlet.UpdateServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1> 데이터 수정 하기 </h1>
<form action="update" method="post">
<button type="submit"> 수정 완료</button>
<%

String[] data=request.getParameter("data").split(",");



String name=data[0];
String id=data[1];
String pw=data[2];


    	out.print("<table border =" + "2"+">");
        out.print("<thead>" + "<td>name</td>" + "<td>ID</td>" + "<td>PW</td>"+"</thead>");
        out.print(
        "<tr>" 
        + "<td><input type = 'text' name = 'inputName' placeHolder="+name+"></input></td>" 
        + "<td><input type = 'text' name = 'inputId' placeHolder="+id+"></input></td>" 
        + "<td><input type = 'text' name = 'inputPw' placeHolder="+pw+"></input></td>"
        + "<td><input type = 'hidden' name = 'searchId' value="+id+"></input></td>"
        +"</tr>");
        out.print("</table>");	
        

%>




</form>



</body>
</html>