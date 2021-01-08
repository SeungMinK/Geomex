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
@WebServlet("/signIn")
public class signIn extends HttpServlet {
	
	String id,pw;
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signIn() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	req.setCharacterEncoding("utf-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	//���ڵ� �ȵ�..why..?
    	
    	 id = req.getParameter("id");
    	 pw = req.getParameter("pw");
    	System.out.println("[LOG] id : "+id+" \t pw : "+pw);//id �� pw �� �Ѿ� �Դ��� Ȯ��
    	
    	resp.setCharacterEncoding("UTF-8");//�ѱ��� ������ �ʱ� ���ؼ� ���ڵ�
    	//���ڵ� �ȵ�..why..?
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	if(id.equals("admin")&&pw.equals("admin")) {//ammin���� �α��ν�
    		resp.sendRedirect("adminPage.jsp");
    	}
    	else if(dao.compareQuery(id, pw)) {//�α��� ����
    		resp.sendRedirect("index.jsp");
    	}
    	else {//�α��� ����
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
