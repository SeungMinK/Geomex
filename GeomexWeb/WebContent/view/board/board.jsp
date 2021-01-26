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

iframe {
	width: 60%;
	height: 60%;
	border: 1;
	margin-left: 20%;
	margin-right: 20%;
	margin-top: 5%;
	margin-bottom: 1%;
	overflow: scroll;
}

.tableBox {
	width: 60%;
	height: 60%;
	border: 1;
	margin-left: 20%;
	margin-right: 20%;
	margin-top: 5%;
	margin-bottom: 1%;
}
</style>
<script>
	function show() {
		document.getElementById("show").show();

	}

	function close() {
		document.getElementById("show").style.display("none");

	}
</script>


</head>
<body style="background-color: #f8f8ff;">



	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="../home/home.jsp">Geomex</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor02" aria-controls="navbarColor02"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarColor02">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="../home/home.jsp">Home
				</a></li>


				<li class="nav-item"><a class="nav-link" href="../lunch/lunch.jsp">Lunch</a>
				</li>

				<li class="nav-item"><a class="nav-link" href="../vote/vote.jsp">Vote</a>
				</li>

				<li class="nav-item  active"><a class="nav-link">Board <span
						class="sr-only">(current)</span>
				</a></li>




			</ul>
			<form class="form-inline my-2 my-lg-0">
				<span class="nav-item" style="color: gray; font-style: oblique;">
					<%
					String id = (String) session.getAttribute("loginId");
					out.print(id);
					%>
				</span> <a class="nav-link" href="LogoutServlet">Logout</a>
			</form>
		</div>
	</nav>

	<div class="tableBox">


		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>

				</tr>
			</thead>
			<tbody>
				<%
				DAO dao = new DAO();
				dao.connectDb();
				dao.selectBoardQuerry();
				Object[][] array = dao.getArray();
				int index = 0;
				if (array != null) {

					for (int i = 0; i < array.length; i++) {
						index = Integer.parseInt(array[i][3].toString());
						out.print("<tr class=\"table-secondary\" onClick=\"location.href='textView.jsp?index=");
						out.print(index);
						out.print("'\">");
						for (int j = 0; j < array[0].length - 1; j++) {

					out.print("<td>");
					out.print(array[i][j]);
					out.print("</td>");

						}
						out.print("</tr>");
					}
					out.print("</table>");

				}
				%>

			</tbody>
		</table>


		<input style="float: right; margin-right: 5%" type="button"
			onclick="location='addBoard.jsp'" value="글쓰기">

	</div>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>
