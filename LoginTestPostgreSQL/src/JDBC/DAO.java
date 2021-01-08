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
     private String sql;
    
     Object [][]array; //데이터베이스 자료 저장
 	 VO vo = new VO();
 	 Connection conn=null;
     PreparedStatement pst=null;
     ResultSet rs=null;
     
     //String menu = request.getParameter("chkMenu");


    
     
     
    public void connectDb() {
    		
    		 	System.out.println("[log] connectDb processing");
        		try {
    			Class.forName("org.postgresql.Driver");
        		} catch (ClassNotFoundException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
        		}
        		
        	  
        	  setInfo(); // url , id , pw  DB연결에 필요한 파라미터 값 설정하기
        	  
              try { //DB연결
            	
                  conn = DriverManager.getConnection(url, userId, userPw);
                  
                  
                      
              } catch (SQLException e) {
                  System.out.println(e);
              } finally {
            	
    		
              }
    		
    	
     	
    }
    
    public void deleteQuery(String id) {
    	System.out.println(id);
    	
    	try {
    		sql= "delete from LoginDataTable where userid = ?"; //데이터 테이블의 모든 내용 가져오기
    		pst = conn.prepareStatement(sql);
    		pst.setString(1, id);
    		pst.executeUpdate();
		} catch (Exception e) {
			
			// TODO: handle exception
		}
    	finally {
			close();
		}
    	
    }
    
    public void selectDataQuerry() {
    		
    	  try {
    		sql= "SELECT username,userid,userpw from loginDataTable"; //데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			/*
			 *  pstmt 생성을 할때 (SQL문, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE); 로
			 *  Resultset is forward_only 에러 해결!!
			 *  참고 https://dodo1054.tistory.com/127
			 */
			rs=pst.executeQuery();
			if(rs.next()) {
				int colsize = rs.getMetaData().getColumnCount();
			
				rs.last();//rs의 커서를 맨 뒤까지 이동시킨 후
			    int rowcount = rs.getRow(); // row의 갯수를 구하고
			    rs.first();//다시 rs를 처음으로 이동시킴
			
				array = new Object[rowcount][colsize]; //데이터베이스크기만큼 array 저장
				
				
				 
				
				for(int i=0; i<rowcount;i++) {
					for(int j=0;j<colsize;j++) {
							array[i][j]=rs.getString(j+1); 
							/*
							 * j == 1 .. id
							 * j == 2 .. pw
							 * j == 3 .. name 
							 * 저장
							 */
					}	
					rs.next();//다음 행으로 이동 시키기
				}
				
			
				
			
    	  
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			close();
		}
    }
    
  
    
    public boolean compareQuery(String id,String pw) { // id pw 조회하는 쿼리 날리기
    	
    	try {
    		  sql= "SELECT userpw from loginDataTable Where userid=?";	
    		 
			 pst = conn.prepareStatement(sql);
			 pst.setString(1, id);
		     rs = pst.executeQuery();
		     if (rs.next()) {//결과 저장하기
		    	 if(rs.getString(1).equals(pw)) {
		    		 System.out.println("[log]로그인 성공");
		    		 return true;
		    	 }
		    	 System.out.println("[log]잘못된 비밀번호");
		    	 return false;
		    }
		     System.out.println("[log]아이디가 없습니다.");
					return false;
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
    	
			e.printStackTrace();
			return false;
		}
   
    }
    
    public boolean insertDataQuery(String id, String pw, String name) {//사용자 아이디,비밀번호,이름 넣기
		// TODO Auto-generated method stub
    	 boolean result=false;
		 System.out.println("[log]insertDataQuery 진입");
		 try {
			 sql = "insert into LOGINDATATABLE values(?,?,?)"; // db에 데이터 넣기 쿼리
   		 
			 pst = conn.prepareStatement(sql);
			 //파라미터 설정
			 pst.setString(1, id);
			 pst.setString(2, pw);
			 
			 pst.setString(3, name);
			 result = pst.execute();
		     
		     
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("[log]catch 진입?");
			e.printStackTrace();
		
		}finally {
			close();
		}
		
		return result;
	}
  
     
	public void close() { // jdbc에서 사용한 메모리 반환 시키기
		
		 try {
			 //close는 꼭 생성한 순서의 반대로 닫아줘야함
			 /*
			 생성순서 
			 1. conn으로 연결 확인
			 2. pst로 쿼리 보내기
			 3. rs에 결과저장
			 */
            
			if(rs!=null) rs.close();
            if(pst!=null) pst.close();
            if(conn!=null) conn.close();
           
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
	
	public Object[][] getArray() {//저장된 2차원 배열 반환
	    	
	    	return array;
	    }
	
    
}