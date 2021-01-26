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
		writer.println("<script>alert(\"���� ����\");" + "location.href=\"../home/home.jsp\";" + "</script>");

		writer.flush();
		writer.close();


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("UTF-8");// �ѱ����ڵ�

		vote = req.getParameter("vote"); // ���õ� �޴� ��������

		HttpSession session = req.getSession(); // session ��������
		String id = (String) session.getAttribute("loginId"); // session�� ����� id ��������

		System.out.println("chkMenu : " + vote);
		if (vote.equals("1"))
			days = "������";
		else if (vote.equals("2"))
			days = "ȭ����";
		else if (vote.equals("3"))
			days = "������";
		else if (vote.equals("4"))
			days = "�����";
		else if (vote.equals("5"))
			days = "�ݿ���";
		else {
			days = "������";
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			writer.println("<script>alert('�̻����� ����������');</script>");
			writer.flush();
			writer.close();

		}
		// dao ����
		DAO dao = new DAO();
		dao.connectDb();

		dao.updateDaysQuery(id, days); // id�� menu�� �Ķ���ͷ� ������ db������Ʈ�ϱ�

		resp.sendRedirect("vote.jsp");// ȭ����ȯ

	}

}
