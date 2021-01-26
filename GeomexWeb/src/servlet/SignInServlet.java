package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import dao.DAO;

/**
 * Servlet implementation class test
 */
@WebServlet("/signIn") //login.jsp�� ����
public class SignInServlet extends HttpServlet {
	
	String id,pw;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	HttpSession session = req.getSession(); // session ��������
    	String id = (String) session.getAttribute("loginId"); // session�� ����� id ��������
     
    
    	super.service(req, resp);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("[signIn]  ���� �õ�");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	req.setCharacterEncoding("UTF-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	resp.setContentType("text/html;charset=UTF-8");
    	
    	//id �� pw ��������
    	 id = req.getParameter("id");
    	 pw = req.getParameter("pw");
    	System.out.println("[log signIn] id : "+id+" \t pw : "+pw);//id �� pw �� �Ѿ� �Դ��� Ȯ��
    	
    
    	//dao ����
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	//session�� �α����� id ����
    	
    	
    	
    	 if(dao.compareQuery(id, pw)) {//�α��� ����
    		HttpSession session = req.getSession();
        	session.setAttribute("loginId", id);
    		
        	if(id.equals("admin")) resp.sendRedirect("../admin/adminPage.jsp");
        	
        	else resp.sendRedirect("../home/home.jsp");
    	}
    	else {//�α��� ���н� �ٽ� ����ȭ������
    		
    		PrintWriter writer =resp.getWriter(); 
    
    		writer.print("<script>"
    				+ "alert('�α��ο� �����Ͽ����ϴ�.');"
    				+ "location.href=\"login.jsp\";"
    				+ "</script>");
    		writer.flush(); 
    		writer.close();
    	
    	}
    	
    	
    	
	}


}
