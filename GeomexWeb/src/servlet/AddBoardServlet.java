package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

/**
 * Servlet implementation class addBoard
 */
@WebServlet("/addBoard")
public class AddBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");// �ѱ����ڵ�
		String title,text,id,date;
		int count;
		title = req.getParameter("title"); // Ÿ��Ʋ ��������
		text = req.getParameter("text"); // �ؽ�Ʈ ��������
		LocalDate now = LocalDate.now();
		date = now.toString();
		
		
		HttpSession session = req.getSession(); // session ��������
		id = (String) session.getAttribute("loginId"); // session�� ����� id ��������

		
		// dao ����
		DAO dao = new DAO();
		dao.connectDb();
		dao.insertBoardQuery(title, text, id, date);

	

		resp.sendRedirect("board.jsp");// ȭ����ȯ
	}

}
