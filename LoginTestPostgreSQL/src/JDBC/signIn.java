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
    	req.setCharacterEncoding("utf-8");//한글이 깨지지 않기 위해서 인코딩
    	//인코딩 안됨..why..?
    	
    	 id = req.getParameter("id");
    	 pw = req.getParameter("pw");
    	System.out.println("[LOG] id : "+id+" \t pw : "+pw);//id 및 pw 잘 넘어 왔는지 확인
    	
    	resp.setCharacterEncoding("UTF-8");//한글이 깨지지 않기 위해서 인코딩
    	//인코딩 안됨..why..?
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	if(id.equals("admin")&&pw.equals("admin")) {//ammin으로 로그인시
    		resp.sendRedirect("adminPage.jsp");
    	}
    	else if(dao.compareQuery(id, pw)) {//로그인 성공
    		resp.sendRedirect("index.jsp");
    	}
    	else {//로그인 실패
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
