package JDBC;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class test
 */
@WebServlet("/signUp")
public class signUp extends HttpServlet {
	
	String id,pw,name;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signUp() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	req.setCharacterEncoding("UTF-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	//���ڵ� �ȵ�..why..?
    	
    	String id = req.getParameter("id");
    	String pw = req.getParameter("pw");
    	String name = req.getParameter("name");
    	System.out.println("[LOG] id : "+id+" \t pw : "+pw+"\t name : "+name);//id �� pw �� �Ѿ� �Դ��� Ȯ��
    	
    	resp.setCharacterEncoding("UTF-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	//���ڵ� �ȵ�..why..?
    	DAO dao = new DAO();
    	dao.connectDb();
    	if(dao.insertDataQuery(id, pw,name)) {//�α��� ����
    		System.out.println("[log]������ �Է� ����");
    		resp.sendRedirect("login.jsp");

    	}
    	else {//�α��� ����
    		System.out.println("[log]������ �Է� ����");
    		resp.sendRedirect("login.jsp");
    	}
    	
    	
    	
    	super.service(req, resp);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}

}
