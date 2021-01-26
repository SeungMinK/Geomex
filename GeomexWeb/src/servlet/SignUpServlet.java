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
@WebServlet("/signUp")//signUp.jsp 연결
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
    	HttpSession session = req.getSession(); // session 가져오기
    	String id = (String) session.getAttribute("loginId"); // session에 저장된 id 가져오기
    	
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
		req.setCharacterEncoding("UTF-8");//한글이 깨지지 않기 위해서 인코딩
    	
    	String id = req.getParameter("id");
    	String pw = req.getParameter("pw");
    	String name = req.getParameter("name");
    	System.out.println("[log signUp] id : "+id+" \t pw : "+pw+"\t name : "+name);//id 및 pw 잘 넘어 왔는지 확인
    	
    	//dao 연결
    	DAO dao = new DAO();
    	dao.connectDb();
    	
    	//회원가입 진행
    	dao.insertDataQuery(id, pw,name);
    	
    	//로그인창으로 화면전환
    	resp.sendRedirect("login.jsp");
	}


}
