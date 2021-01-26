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
@WebServlet("/signIn") //login.jsp와 연결
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
    	HttpSession session = req.getSession(); // session 가져오기
    	String id = (String) session.getAttribute("loginId"); // session에 저장된 id 가져오기
     
    
    	super.service(req, resp);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("[signIn]  공격 시도");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	req.setCharacterEncoding("UTF-8");//한글이 깨지지 않기 위해서 인코딩
    	resp.setContentType("text/html;charset=UTF-8");
    	
    	//id 및 pw 가져오기
    	 id = req.getParameter("id");
    	 pw = req.getParameter("pw");
    	System.out.println("[log signIn] id : "+id+" \t pw : "+pw);//id 및 pw 잘 넘어 왔는지 확인
    	
    
    	//dao 연결
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	//session에 로그인한 id 저장
    	
    	
    	
    	 if(dao.compareQuery(id, pw)) {//로그인 성공
    		HttpSession session = req.getSession();
        	session.setAttribute("loginId", id);
    		
        	if(id.equals("admin")) resp.sendRedirect("../admin/adminPage.jsp");
        	
        	else resp.sendRedirect("../home/home.jsp");
    	}
    	else {//로그인 실패시 다시 원래화면으로
    		
    		PrintWriter writer =resp.getWriter(); 
    
    		writer.print("<script>"
    				+ "alert('로그인에 실패하였습니다.');"
    				+ "location.href=\"login.jsp\";"
    				+ "</script>");
    		writer.flush(); 
    		writer.close();
    	
    	}
    	
    	
    	
	}


}
