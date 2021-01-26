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
		System.out.println("[log mainServlet]������ ���� �õ� �ߴ�!!!");
		chkMenu = req.getParameter("chkMenu");
		System.out.println("chkMenu : " + chkMenu);

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.println("<script>alert(\"���� ����\");" + "location.href=\"../home/home.jsp\";" + "</script>");

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

		req.setCharacterEncoding("UTF-8");// �ѱ����ڵ�

		chkMenu = req.getParameter("chkMenu"); // ���õ� �޴� ��������

		HttpSession session = req.getSession(); // session ��������
		String id = (String) session.getAttribute("loginId"); // session�� ����� id ��������

		System.out.println("chkMenu : " + chkMenu);
		if (chkMenu.equals("1"))
			menu = "���";
		else if (chkMenu.equals("2"))
			menu = "�귱ġ";
		else if (chkMenu.equals("3"))
			menu = "��ȸ����";
		else if (chkMenu.equals("4"))
			menu = "����";
		else if (chkMenu.equals("5"))
			menu = "�����Ĵ�";
		else {
			menu = "���";
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('�̻����� ����������');</script>");
			writer.flush();
			writer.close();

		}
		// dao ����
		DAO dao = new DAO();
		dao.connectDb();

		dao.updateMenuQuery(id, menu); // id�� menu�� �Ķ���ͷ� ������ db������Ʈ�ϱ�
		System.out.println("[log mainServlete] id : " + id + "\t menu :" + menu);

		resp.sendRedirect("lunch.jsp");// ȭ����ȯ

	}

}
