<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<title>Insert title here</title>
<style type="text/css">
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
				<li class="nav-item"><a class="nav-link" href="../home/home.jsp">Home </a></li>


				<li class="nav-item"><a class="nav-link" href="../lunch/lunch.jsp">Lunch</a></li>

				<li class="nav-item"><a class="nav-link" href="../vote/vote.jsp">Vote</a>
				</li>


				<li class="nav-item  active"><a class="nav-link">Board <span
						class="sr-only">(current)</span>
				</a></li>




			</ul>

		</div>
	</nav>


	<form name="f" class="tableBox" method="post" action="addBoard">


		<table style="width: 100%;">
			<tr style="width: 100%;">
				<td style="width: 30%;">제목</td>
				<td style="width: 70%;"><input type="text" name="title"
					style="width: 100%;"></td>
			</tr>
			<tr style="width: 100%;">
				<td style="width: 30%;">내용</td>
				<td style="width: 70%;"><textarea rows="10" cols="22"
						name="text" style="width: 100%;"></textarea></td>
			</tr>
		</table>





		<input type="submit" id="noBackButton" value="제출하기"
			style="margin-top: 10px; float: right;">
	</form>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>