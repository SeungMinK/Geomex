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
@WebServlet("/main")
public class LunchServlet extends HttpServlet {
	String menu;
	String chkMenu;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LunchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("[log mainServlet]인젝션 공격 시도 했다!!!");
		chkMenu = req.getParameter("chkMenu");
		System.out.println("chkMenu : " + chkMenu);

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script>alert(\"공격 방지\");" + "location.href=\"../home/home.jsp\";" + "</script>");

		writer.flush();
		writer.close();
		// resp.sendRedirect("index.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");// 한글인코딩

		chkMenu = req.getParameter("chkMenu"); // 선택된 메뉴 가져오기

		HttpSession session = req.getSession(); // session 가져오기
		String id = (String) session.getAttribute("loginId"); // session에 저장된 id 가져오기

		System.out.println("chkMenu : " + chkMenu);
		if (chkMenu.equals("1"))
			menu = "돈까스";
		else if (chkMenu.equals("2"))
			menu = "브런치";
		else if (chkMenu.equals("3"))
			menu = "육회덮밥";
		else if (chkMenu.equals("4"))
			menu = "장어덮밥";
		else if (chkMenu.equals("5"))
			menu = "구내식당";
		else {
			menu = "돈까스";
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('이상한짓 하지마세요');</script>");
			writer.flush();
			writer.close();

		}
		// dao 연결
		DAO dao = new DAO();
		dao.connectDb();

		dao.updateMenuQuery(id, menu); // id와 menu를 파라미터로 보내서 db업데이트하기
		System.out.println("[log mainServlete] id : " + id + "\t menu :" + menu);

		resp.sendRedirect("lunch.jsp");// 화면전환

	}

}
