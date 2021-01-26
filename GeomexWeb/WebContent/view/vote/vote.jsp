<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="/bootstrap/css/bootstrap.css">
<title>Insert title here</title>
<style type="text/css">
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


				<li class="nav-item"><a class="nav-link" href="../lunch/lunch.jsp">Lunch</a></li>

				<li class="nav-item active"><a class="nav-link" href="vote.jsp">Vote
						<span class="sr-only">(current)</span>
				</a></li>


				<li class="nav-item"><a class="nav-link" href="../board/board.jsp">Board</a></li>




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

	<form action="vote" method="post" style="margin: 1%">
		<table style="margin-bottom: 1%">
			<caption style="caption-side: top;">요일</caption>
			<tr>
				<td><input type="radio" name="vote" value="1" checked="checked"></td>
				<td><span class="badge badge-pill badge-secondary">월</span></td>
			</tr>
			<tr>
				<td><input type="radio" name="vote" value="2"></td>
				<td><span class="badge badge-pill badge-secondary">화</span></td>
			</tr>
			<tr>
				<td><input type="radio" name="vote" value="3"></td>
				<td><span class="badge badge-pill badge-secondary">수</span></td>
			</tr>
			<tr>
				<td><input type="radio" name="vote" value="4"></td>
				<td><span class="badge badge-pill badge-secondary">목</span></td>
			</tr>
			<tr>
				<td><input type="radio" name="vote" value="5"></td>
				<td><span class="badge badge-pill badge-secondary">금</span></td>
			</tr>
		</table>

		<button type="submit" class="btn btn-secondary">제출하기</button>
	</form>

	<input type="button" class="btn btn-success" style="margin: 10px;"
		onclick="button2_click();" value="테이블 보기" />
	<input type="button" class="btn btn-success" style="margin: 10px;"
		onclick="button1_click();" value="테이블 닫기" />

	<script>
		function button1_click() {
			var table = document.getElementById("show");

			table.style.display = "none";
		}

		function button2_click() {
			var table = document.getElementById("show");

			table.style.display = "block";
		}
	</script>
	<table id="show" class="table table-hover" border=2px
		style="display: none; width: auto;">
		<%
		DAO dao = new DAO();
		dao.connectDb();
		dao.selectDataQuerry();
		Object[][] array = dao.getArray();
		double m1 = 0, m2 = 0, m3 = 0, m4 = 0, m5 = 0, sum = 0;
		String day;
		if (array != null) {
			out.print("<thead> <td> 이름 </td> <td> 날짜 </td>");
			for (int i = 0; i < array.length; i++) {
				out.print("<tr class=\"table-secondary\">");
				for (int j = 0; j < array[0].length; j++) {

			if (j == 0 || j == 4) {//이름과 날짜만 표출하기
				out.print("<td>");
				out.print(array[i][j]);
			
				if (j == 4) {
					
					day = (String) array[i][j];
				
					if (day.equals("월요일"))
						m1++;
					else if (day.equals("화요일"))
						m2++;
					else if (day.equals("수요일"))
						m3++;
					else if (day.equals("목요일"))
						m4++;
					else if (day.equals("금요일"))
						m5++;

				}
				out.print("</td>");
			}

				}
				out.print("</tr>");
			}
			out.print("</table>");

			sum = m1 + m2 + m3 + m4 + m5;
		}
		%>
	</table>



	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped" role="progressbar"
			style="width: <%out.print((m1 / sum) * 100);%>%;" aria-valuemin="0"
			aria-valuemax="100">
			월요일<%out.print("\t" + (int) m1);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((m2 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			화요일<%out.print("\t" + (int) m2);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-info"
			role="progressbar" style="width: <%out.print((m3 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			수요일<%out.print("\t" + (int) m3);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-warning"
			role="progressbar" style="width: <%out.print((m4 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			목요일<%out.print("\t" + (int) m4);%>
		</div>
	</div>
	<div class="progress" style="margin-bottom: 2%">
		<div class="progress-bar progress-bar-striped bg-success"
			role="progressbar" style="width: <%out.print((m5 / sum) * 100);%>%;"
			aria-valuemin="0" aria-valuemax="100">
			금요일<%out.print("\t" + (int) m5);%>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="/bootstrap/js/bootstrap.js"></script>
</body>
</html>