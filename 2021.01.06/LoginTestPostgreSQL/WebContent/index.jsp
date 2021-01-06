<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta charset="EUC-KR">
<title>JDBC 연결 공부</title>
</head>
<body>

	<h1>JDBC를 활용하여 Postgres SQL 데이터 출력</h1>
	<hr>
	
	<div class = "jumbotron" style = "padding-top : 20px;">
				<form method = "post" action="loginAction.jsp">
					<h3 style = "text-aling : center;"> 로그인 화면(미완성) </h3>
					
					<div class = "form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">
					</div>
					
					<div class = "form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
					</div>
					
						<input type = "submit" class = "btn btn-primary form-control" value = "로그인">
								
				</form>
		
		</div>

<hr>
<p> 데이터베이스 출력  </p>
<hr>
<%
//jsp에서 postgresql 데이터베이스 jdbc 사용하여 연동하기

//close 하기 위해서 null로 선언해줌
Connection conn = null ;//데이터베이스 연결을 관리
Statement st = null ;//sql 구문을 실행하는 역활
ResultSet rs = null ;//sql 구문을 실행했을때 결과

//jdbc 구동시키기  jar 파일이 들어가 있어야 실행 가능함
Class.forName("org.postgresql.Driver");

//jdbc를 사용하여 연결할 링크 , ip, 포트번호, 연동할 데이터베이스 
String url = "jdbc:postgresql://localhost:5432/LOGINDATA";
String user = "postgres";//접속할 아이디
String password = "8138";//접속할 비밀번호

try {
    conn = DriverManager.getConnection(url, user, password); // driverManger를 사용하여 연결 진행
    st = conn.createStatement();
    rs = st.executeQuery("SELECT * from loginDataTable");//sql 보내기
    
    int colsize = rs.getMetaData().getColumnCount();
    //int rowsize = rs.getRow();//실행이 안됨 .
    
    //출력
    if (rs.next()) {
    	for(int i=0; i<rs.getRow();i++) {
    		for(int j=0;j<colsize;j++) {
    			out.print(" "+rs.getString(j+1));
    		}
        	rs.next();
        	out.println("<br>");
        	
    	}
    
    }
        
} catch (SQLException e) {
    System.out.println(e);
} 
//메모리 돌려주기
finally {
    try {
        rs.close();
        st.close();
        conn.close();
    } catch (SQLException sqlEX) {
        System.out.println(sqlEX);
    }
    
}



%>

	
</body>
</html>