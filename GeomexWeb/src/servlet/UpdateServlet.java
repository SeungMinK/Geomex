package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

/**
 * Servlet implementation class updateServlet
 */
@WebServlet("/update") //update.jsp와 연결
public class UpdateServlet extends HttpServlet {
	
	String name,id,pw,searchId;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

 
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
   	 req.setCharacterEncoding("UTF-8"); // 한글 패치
    	 
    	 //form 으로 전달받은 값들 가져오기
    	 name= req.getParameter("inputName");
    	 id = req.getParameter("inputId");
    	 pw = req.getParameter("inputPw");
    	 searchId = req.getParameter("searchId");
    	 
    	 //dao 생성 및 db 연결 
    	 DAO dao = new DAO();
     	 dao.connectDb();
     	 
     	if(!searchId.equals("admin")) {//admin은 수정불가
     	  	dao.updateDataQuery(name, id, pw, searchId);
     	}  
     	
    	System.out.println("[log updateServlet.java] id : "+id+" \t pw : "+pw+"\t name : "+name+ "\t searchId : "+searchId);//id 및 pw 잘 넘어 왔는지 확인
    	//화면 전환
    	resp.sendRedirect("adminPage.jsp");
	}

}
