<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<%@ page import="servlet.SignInServlet"%>
<!DOCTYPE html>
<style type="text/css">

* { margin:0; padding: 0; }

#noBackButton {
    background-color: transparent !important;
    border-color: transparent;
    border: none;
    color: #000000;
}





body{

         font-family: "맑은 고딕";

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

/* inline이였던 요소들을 block으로 바꿈 */

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

<%
	//session.invalidate();

	if(session.getAttribute("loginId")!=null){
		response.sendRedirect("../home/home.jsp");
	}
	
%>

         <form id="login-form" method="post" action="../../signIn">

                  <label class="legend">아이디</label>

                  <input name="id" type="text">



                  <label class="legend">패스워드</label> 

                  <input name="pw" type="password"> 

					<%
					
					%>
					
				<div id = buttonAlign>
				   <input  type="submit"  value="로그인"  >	
				</div>
				
				<div id = buttonAlign>
				  <button type="button"  onclick="location='signUp.jsp'">회원가입</button>
            
				</div>
               

         </form>

</body>
</html>