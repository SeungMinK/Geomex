<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<%@ page import="servlet.DeleteBoardServlet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<title>Insert title here</title>
<style type="text/css">
.tableBox {
	margin-left: 20%;
	margin-right: 20%;
	margin-top: 5%;
	width: 60%;
}
</style>
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

				<li class="nav-item"><a class="nav-link" href="../voute/vote.jsp">Vote</a>
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



	<%
	DAO dao = new DAO();
	dao.connectDb();
	String index = request.getParameter("index");
	dao.selectBoardQuerry(index);
	Object[][] array = dao.getArray();
	
	%>
	<div class="tableBox">
		<table class="table" style="width: 100%">
			<thead style="width: 100%">
				<tr>
					<th scope="col">
						<%
						out.print(array[0][0]);
						%>
					</th>
					<th scope="col">
						<%
						out.print(array[0][1]);
						%>
					</th>
					<th scope="col">
						<%
						out.print(array[0][2]);
						%>
					</th>

				</tr>
			</thead>
			<tbody style="width: 100%">
				<tr>
					<td>
						<%
						out.print(array[0][3]);
						%>
					</td>
				</tr>
			</tbody>
		</table>
		<form action="deleteBoard">
			<input type="hidden" name="index" value=<%out.print(index);%>>
			<input type="hidden" name="id" value=<%out.print(id);%>>
			<input style="float: right; margin-right: 5%" type="submit"
				value="글삭제">

		</form>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>