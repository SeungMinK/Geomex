package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

/**
 * Servlet implementation class test
 */
@WebServlet("/signUp")//signUp.jsp ����
public class SignUpServlet extends HttpServlet {
	
	String id,pw,name;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	HttpSession session = req.getSession(); // session ��������
    	String id = (String) session.getAttribute("loginId"); // session�� ����� id ��������
    	
    	System.out.println("[log signUp] id  : "+id);
    	
    	super.service(req, resp);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	
    	String id = req.getParameter("id");
    	String pw = req.getParameter("pw");
    	String name = req.getParameter("name");
    	System.out.println("[log signUp] id : "+id+" \t pw : "+pw+"\t name : "+name);//id �� pw �� �Ѿ� �Դ��� Ȯ��
    	
    	//dao ����
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	//ȸ������ ����
    	dao.insertDataQuery(id, pw,name);
    	
    	//�α���â���� ȭ����ȯ
    	resp.sendRedirect("login.jsp");
	}


}
