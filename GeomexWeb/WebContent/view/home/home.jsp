<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="dao.DAO"%>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<title>Geomex</title>
<style type="text/css">


#noBackButton {
    background-color: transparent !important;
    border-color: transparent;
    border: none;
    color: #000000;
}



</style>
<script>

function show() {
	document.getElementById("show").show();

}

function close(){
	document.getElementById("show").style.display("none");

}
</script>


</head>
<body style="background-color: #f8f8ff;">

		<%
		DAO dao = new DAO();
		dao.connectDb();
		dao.selectDataQuerry();
		Object[][] array = dao.getArray();
		double m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, sum = 0;
		double d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, sum2 = 0;
		String day;
		String menu;
		if (array != null) {

			for (int i = 0; i < array.length; i++) {
			
				for (int j = 0; j < array[0].length; j++) {

			if (j == 3||j == 4) {//이름과 날짜만 표출하기
			
				
				
				
				if (j == 3) {

					menu = (String) array[i][j];
					if (menu.equals("돈까스"))
						m1++;
					else if (menu.equals("브런치"))
						m2++;
					else if (menu.equals("육회덮밥"))
						m3++;
					else if (menu.equals("장어덮밥"))
						m4++;
					else if (menu.equals("구내식당"))
						m5++;

				}
				
				
				
				if (j == 4) {
					
					day = (String) array[i][j];
				
					if (day.equals("월요일"))
						d1++;
					else if (day.equals("화요일"))
						d2++;
					else if (day.equals("수요일"))
						d3++;
					else if (day.equals("목요일"))
						d4++;
					else if (day.equals("금요일"))
						d5++;

				}
			
			}

				}
			
			}
		
			sum= m1+m2+m3+m4+m5;
			sum2 = d1 + d2 + d3 + d4 + d5;
		}
	%>


	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="home.jsp">Geomex</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" >Home
          <span class="sr-only">(current)</span>
        </a>
      </li>
  	
  	
  		<li class="nav-item">
        <a class="nav-link" href="../lunch/lunch.jsp">Lunch</a>
      </li>	
      
      	<li class="nav-item">
        <a class="nav-link" href="../vote/vote.jsp">Vote</a>
      </li>	
  		
  	  <li class="nav-item">
        <a class="nav-link" href="../board/board.jsp">Board</a>
      </li>
  
  		
  
  
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <span class="nav-item" style="color: gray; font-style: oblique;" >
      <%
      String id = (String)session.getAttribute("loginId");
      out.print(id);
      %>
      </span>
      <a class="nav-link" href="LogoutServlet">Logout</a>
    </form>
  </div>
</nav>


<div class="jumbotron" >
  <h1 class="display-3">Geomex</h1>
  <p class="lead">사내 도시락 수요 설문 조사 홈페이지입니다.</p>
  <hr class="my-4">
  

  
  
  
  <table style="width: 100%">
<thead>
<tr>
<td style="text-align: center; margin-bottom: 1%;"></td>
<td style="text-align: center; margin-bottom: 1%;"></td>
</tr>
</thead>
<tr style="width: 100%">
<td style="width: 50%">
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped" role="progressbar"
			style="width: <%out.print((m1 / sum) * 100);%>%;" aria-valuemin="0"
			aria-valuemax="100">
			돈까스<%out.print("\t" + (int) m1);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((m2 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			브런치<%out.print("\t" + (int) m2);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-info"
			role="progressbar" style="width: <%out.print((m3 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			육회<%out.print("\t" + (int) m3);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-warning"
			role="progressbar" style="width: <%out.print((m4 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			장어<%out.print("\t" + (int) m4);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((m5 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			식당<%out.print("\t" + (int) m5);%>
		</div>
	</div>
</td>
<td style="width: 50%">	
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped" role="progressbar"
			style="width: <%out.print((d1 / sum2) * 100);%>%;" aria-valuemin="0"
			aria-valuemax="100">
			월요일<%out.print("\t" + (int) d1);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((d2 / sum2) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			화요일<%out.print("\t" + (int) d2);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-info"
			role="progressbar" style="width: <%out.print((d3 / sum2) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			수요일<%out.print("\t" + (int) d3);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-warning"
			role="progressbar" style="width: <%out.print((d4 / sum2) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			목요일<%out.print("\t" + (int) d4);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((d5 / sum2) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			금요일<%out.print("\t" + (int) d5);%>
		</div>
	</div>
	</td>
</tr>
</table>
  
  
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>
