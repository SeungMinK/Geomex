<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">

* { margin:0; padding: 0; }
body{

         font-family: "맑은 고딕";

         font-size: 0.75em;

         color: #333;

}

#noBackButton {
    background-color: transparent !important;
    border-color: transparent;
    border: none;
    color: #000000;
    margin-top: 10px;
    margin-right: 20px;
    
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

</head>
<body>
		
         <form name ="f" id="login-form" method="post" action="signUp">
		
         <table>
         <caption> 회원 가입</caption>
    	<tr><td> 이름  </td> <td> <input type="text" name= "name"> </td></tr>
    	<tr><td> 아이디  </td><td><input type="text" name= "id"> </td></tr>
    	<tr><td> 패스워드  </td><td><input type="password" name= "pw"> </td></tr>
    	<tr>
    	<td><input type="submit"  id="noBackButton" value="제출하기" style="margin-top: 10px;" ></td>
    	<td> <button type="button" id="noBackButton" onclick="location='login.jsp'"> 돌아가기</button></td>
    	</tr> 
    	</table>
               

         </form>

</body>

</html>