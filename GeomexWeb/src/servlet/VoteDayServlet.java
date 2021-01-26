package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import dao.DAO;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/vote")
public class VoteDayServlet extends HttpServlet {
	String days;
	String vote;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VoteDayServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		vote = req.getParameter("vote");
		System.out.println("vote : " + vote);

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script>alert(\"공격 방지\");" + "location.href=\"../home/home.jsp\";" + "</script>");

		writer.flush();
		writer.close();


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");// 한글인코딩

		vote = req.getParameter("vote"); // 선택된 메뉴 가져오기

		HttpSession session = req.getSession(); // session 가져오기
		String id = (String) session.getAttribute("loginId"); // session에 저장된 id 가져오기

		System.out.println("chkMenu : " + vote);
		if (vote.equals("1"))
			days = "월요일";
		else if (vote.equals("2"))
			days = "화요일";
		else if (vote.equals("3"))
			days = "수요일";
		else if (vote.equals("4"))
			days = "목요일";
		else if (vote.equals("5"))
			days = "금요일";
		else {
			days = "월요일";
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('이상한짓 하지마세요');</script>");
			writer.flush();
			writer.close();

		}
		// dao 연결
		DAO dao = new DAO();
		dao.connectDb();

		dao.updateDaysQuery(id, days); // id와 menu를 파라미터로 보내서 db업데이트하기

		resp.sendRedirect("vote.jsp");// 화면전환

	}

}
