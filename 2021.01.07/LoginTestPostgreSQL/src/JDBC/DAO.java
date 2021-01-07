package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DAO {

     private String url;
     private String userId;
     private String userPw;
     Object []array;
 	 VO vo = new VO();
 	 Connection conn=null;
     PreparedStatement pst=null;
     ResultSet rs=null;
     
     //String menu = request.getParameter("chkMenu");


    
     
     
    public void connectDb() {
    	  	
    		try {
			Class.forName("org.postgresql.Driver");
    		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    		}
    		
    	  
    	  setInfo(); // url , id , pw  DB연결에 필요한 파라미터 값 설정하기
    	  
          try { //DB연결
        	
              conn = DriverManager.getConnection(url, userId, userPw);
              selectQuery();   // select Query 날리기
              
                  
          } catch (SQLException e) {
              System.out.println(e);
          } finally {
			//close();//DB연결에 사용한 메모리 닫기
		}
     	
    }

    
    public void selectQuery() { // select qurrery 날리기
    	try {
    		 String sql= "SELECT * from loginDataTable Where username=?";	
    		 String name = "준혁";
			 pst = conn.prepareStatement(sql);
			 pst.setString(1, name);
		     rs = pst.executeQuery();
					if (rs.next()) {//결과 저장하기
							int colsize = rs.getMetaData().getColumnCount();
							
							array = new Object[colsize]; //col size 받기
					 		
							//결과 저장하기
							vo.setId(rs.getString(1)); 
					 		array[0]=vo.getId();
					 		vo.setPw(rs.getString(2)); 
					 		array[1]=vo.getPw();
					 		vo.setName(rs.getString(3));
					 		array[2]=vo.getName();
					 		rs.next();
					 	
					
					 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    }
  
     
	public void close() { // jdbc에서 사용한 메모리 반환 시키기
		
		 try {
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        }
	}
    
    
	
	
	public void setInfo() { //DB 연결에 필요한 url id pw 설정하기
		setUrl();
		setUserId();
		setUserPw();
	}
	
	public void setUrl() { // url 설정
		   url = "jdbc:postgresql://localhost:5432/LOGINDATA"; //jdbc 이름 : // 주소  : 포트번호 / 사용데이터베이스
	}
	
	public void setUserId() { //Id 설정
		  userId = "postgres";
	}
	
	public void setUserPw() { //Pw 설정
		  userPw = "8138";
	}
	
	public Object[] getObject() {//결과 반환
		
		return array;
	}
	
	
	
   
   /* jsp 에서 	데이터 받기
    public  formTag(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String title = request.getParameter("title");
        String content  = request.getParameter("content");    
        
        logger.info("title은? {}", title);
        logger.info("content은? {}", content);
        
        return mv;
    }
    */
	
    
}