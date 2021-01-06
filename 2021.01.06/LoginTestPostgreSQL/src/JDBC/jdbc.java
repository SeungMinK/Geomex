package JDBC;
import java.sql.*;

public class jdbc {
	
	
	
    public void connectDB() {

        Connection conn = null ;
        Statement st = null ;
        ResultSet rs = null ;


        String url = "jdbc:postgresql://localhost:5432/LOGINDATA";
        String user = "postgres";
        String password = "8138";

        try {
            conn = DriverManager.getConnection(url, user, password);
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * from loginDataTable");
            
            int colsize = rs.getMetaData().getColumnCount();
            //int rowsize = rs.getRow();//Ω««‡¿Ã æ»µ  .
           
            if (rs.next()) {
            	for(int i=0; i<rs.getRow();i++) {
            		for(int j=0;j<colsize;j++) {
            			System.out.print(" "+rs.getString(j+1));
            		}
                	rs.next();
                	System.out.println();
                	
            	}
            
            }
                
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                rs.close();
                st.close();
                conn.close();
            } catch (SQLException sqlEX) {
                System.out.println(sqlEX);
            }
        }
    }
}