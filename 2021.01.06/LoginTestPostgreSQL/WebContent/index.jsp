<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta charset="EUC-KR">
<title>JDBC ���� ����</title>
</head>
<body>

	<h1>JDBC�� Ȱ���Ͽ� Postgres SQL ������ ���</h1>
	<hr>
	
	<div class = "jumbotron" style = "padding-top : 20px;">
				<form method = "post" action="loginAction.jsp">
					<h3 style = "text-aling : center;"> �α��� ȭ��(�̿ϼ�) </h3>
					
					<div class = "form-group">
						<input type="text" class="form-control" placeholder="���̵�" name="userID" maxlength="20">
					</div>
					
					<div class = "form-group">
						<input type="password" class="form-control" placeholder="��й�ȣ" name="userPassword" maxlength="20">
					</div>
					
						<input type = "submit" class = "btn btn-primary form-control" value = "�α���">
								
				</form>
		
		</div>

<hr>
<p> �����ͺ��̽� ���  </p>
<hr>
<%
//jsp���� postgresql �����ͺ��̽� jdbc ����Ͽ� �����ϱ�

//close �ϱ� ���ؼ� null�� ��������
Connection conn = null ;//�����ͺ��̽� ������ ����
Statement st = null ;//sql ������ �����ϴ� ��Ȱ
ResultSet rs = null ;//sql ������ ���������� ���

//jdbc ������Ű��  jar ������ �� �־�� ���� ������
Class.forName("org.postgresql.Driver");

//jdbc�� ����Ͽ� ������ ��ũ , ip, ��Ʈ��ȣ, ������ �����ͺ��̽� 
String url = "jdbc:postgresql://localhost:5432/LOGINDATA";
String user = "postgres";//������ ���̵�
String password = "8138";//������ ��й�ȣ

try {
    conn = DriverManager.getConnection(url, user, password); // driverManger�� ����Ͽ� ���� ����
    st = conn.createStatement();
    rs = st.executeQuery("SELECT * from loginDataTable");//sql ������
    
    int colsize = rs.getMetaData().getColumnCount();
    //int rowsize = rs.getRow();//������ �ȵ� .
    
    //���
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
//�޸� �����ֱ�
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