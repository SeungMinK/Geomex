<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="JDBC.DAO"%>
<!DOCTYPE html>
<style type="text/css">

* { margin:0; padding: 0; }

#noBackButton {
    background-color: transparent !important;
    border-color: transparent;
    border: none;
    color: #000000;
}



��ó: https://devnauts.tistory.com/17 [devnauts]

body{

         font-family: "���� ���";

         font-size: 0.75em;

         color: #333;

}
#login-form{

         width:250px;
        
         height:140px;

         margin:100px auto;

         border: 1px solid gray;

         border-radius: 5px;

         padding: 15px;

}

/* inline�̿��� ��ҵ��� block���� �ٲ� */

#login-form input, #login-form label{

         display:block;

}


#login-form label{
         margin-top: 10px;
}

#login-form input{
         margin-top: 5px;
}


#buttonAlign {
    display: inline-block;
    margin-top: 10px;
    margin-right: 20px;

}

</style>

<body>

         <form id="login-form" method="post" action="signIn">

                  <label class="legend">���̵�</label>

                  <input name="id" type="text">



                  <label class="legend">�н�����</label> 

                  <input name="pw" type="password"> 

					
					
				<div id = buttonAlign>
				   <input  type="submit" id="noBackButton"  value="�α���"  >	
				</div>
				
				<div id = buttonAlign>
				  <button type="button" id="noBackButton" onclick="location='signUp.jsp'">ȸ������</button>
            
				</div>
               

         </form>

</body>
</html>