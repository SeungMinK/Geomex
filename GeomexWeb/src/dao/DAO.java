package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.VO;

public  class  DAO {

	private String url;
	private String userId;
	private String userPw;
	private String sql;

	Object[][] array; // 데이터베이스 자료 저장
	//VO vo = new VO();
	static Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	// String menu = request.getParameter("chkMenu");
	public void connectDb() { // db 연결하기
		
		
			
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setInfo(); // url , id , pw DB연결에 필요한 파라미터 값 설정하기

			try { // DB연결

				conn = DriverManager.getConnection(url, userId, userPw);

			} catch (SQLException e) {
				System.out.println(e);
			} finally {
			
			}
		
	
		


	}

	public void updateDataQuery(String name, String id, String pw, String searchId) {
		// data update ( 수정) 하기

		try {
			sql = "update loginDataTable set username = ?,userid = ?, userpw = ? where userid = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, id);
			pst.setString(3, pw);
			pst.setString(4, searchId);
			pst.executeUpdate();
		} catch (Exception e) {

			// TODO: handle exception
		} finally {
		
		}

	}

	public void deleteQuery(String id) {
		// data 삭제하기
		System.out.println(id);

		try {
			sql = "delete from LoginDataTable where userid = ?"; // 데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
		} catch (Exception e) {

			// TODO: handle exception
		} finally {
		
		}

	}

	
	public void deleteBoardQuery(String id,int index) {
		// data 삭제하기
		//System.out.println("[LOG] deleteBoard 들어옴 index : "+index);
		try {
			sql = "delete from board where id = ? and index = (?)"; // 데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			pst.setInt(2, index);
			pst.executeUpdate();
		} catch (Exception e) {

			// TODO: handle exception
		} finally {
		
		}

	}
	
	public void selectDataQuerry() {
		// 데이터 검색하기 , array 배열에 저장

		try {
			sql = "SELECT username,userid,userpw,menu,days from loginDataTable where userid !=?"; // 데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, "admin");

			/*
			 * pstmt 생성을 할때 (SQL문, ResultSet.TYPE_SCROLL_SENSITIVE,
			 * ResultSet.CONCUR_UPDATABLE); 로 Resultset is forward_only 에러 해결!! 참고
			 * https://dodo1054.tistory.com/127
			 */
			rs = pst.executeQuery();
			if (rs.next()) {
				int colsize = rs.getMetaData().getColumnCount();

				rs.last();// rs의 커서를 맨 뒤까지 이동시킨 후
				int rowcount = rs.getRow(); // row의 갯수를 구하고
				rs.first();// 다시 rs를 처음으로 이동시킴

				array = new Object[rowcount][colsize]; // 데이터베이스크기만큼 array 저장

				for (int i = 0; i < rowcount; i++) {

					for (int j = 0; j < colsize; j++) {

						array[i][j] = rs.getString(j + 1);

						/*
						 * j == 1 .. id j == 2 .. pw j == 3 .. name j == 4 .. menu 저장 j ==5..days 저장 
						 */
					}

					rs.next();// 다음 행으로 이동 시키기
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	
	public void selectBoardQuerry() {
		// 데이터 검색하기 , array 배열에 저장

		try {
			sql = "SELECT title,id,date,index from board"; // 데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			/*
			 * pstmt 생성을 할때 (SQL문, ResultSet.TYPE_SCROLL_SENSITIVE,
			 * ResultSet.CONCUR_UPDATABLE); 로 Resultset is forward_only 에러 해결!! 참고
			 * https://dodo1054.tistory.com/127
			 */
			rs = pst.executeQuery();
			if (rs.next()) {
				int colsize = rs.getMetaData().getColumnCount();

				rs.last();// rs의 커서를 맨 뒤까지 이동시킨 후
				int rowcount = rs.getRow(); // row의 갯수를 구하고
				rs.first();// 다시 rs를 처음으로 이동시킴

				array = new Object[rowcount][colsize]; // 데이터베이스크기만큼 array 저장

				for (int i = 0; i < rowcount; i++) {

					for (int j = 0; j < colsize; j++) {

						array[i][j] = rs.getString(j + 1);

					
					}

					rs.next();// 다음 행으로 이동 시키기
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	
	public void selectBoardQuerry(String index) {
		// 데이터 검색하기 , array 배열에 저장

		try {
			sql = "SELECT title,id,date,text from board where index = (?)"; // 데이터 테이블의 모든 내용 가져오기
			pst = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			int index2 = Integer.parseInt(index);
			pst.setInt(1, index2);
			//System.out.println("[log selectBoard]selectBoardQuerry에서 index를 받음"+index2);
			/*
			 * pstmt 생성을 할때 (SQL문, ResultSet.TYPE_SCROLL_SENSITIVE,
			 * ResultSet.CONCUR_UPDATABLE); 로 Resultset is forward_only 에러 해결!! 참고
			 * https://dodo1054.tistory.com/127
			 */
			rs = pst.executeQuery();
			if (rs.next()) {
				int colsize = rs.getMetaData().getColumnCount();

				rs.last();// rs의 커서를 맨 뒤까지 이동시킨 후
				int rowcount = rs.getRow(); // row의 갯수를 구하고
				rs.first();// 다시 rs를 처음으로 이동시킴

				array = new Object[rowcount][colsize]; // 데이터베이스크기만큼 array 저장

				for (int i = 0; i < rowcount; i++) {

					for (int j = 0; j < colsize; j++) {

						array[i][j] = rs.getString(j + 1);
						//System.out.println("selectBoardQuerry에서 데이터를 받음 "+array[i][j]);

						/*
						 * j == 1 .. id j == 2 .. pw j == 3 .. name j == 4 .. menu 저장
						 */
					}

					rs.next();// 다음 행으로 이동 시키기
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public boolean compareQuery(String id, String pw) { // id pw 조회하는 쿼리 날리기
		// id와 pw 비교하기 , id 및 pw 있으면 true 없으면 false

		try {
			sql = "SELECT userpw from loginDataTable Where userid=?";

			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {// 결과 저장하기
				if (rs.getString(1).equals(pw)) {
					System.out.println("[log]로그인 성공");

					return true;
				} else {
					System.out.println("[log]잘못된 비밀번호");
					return false;
				}

			}
			System.out.println("[log]아이디가 없습니다.");
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

	}

	public void updateMenuQuery(String id, String menu) {
		// 메뉴 선택한 결과 업데이트하기

	
		try {
			sql = " update logindatatable set menu=? where userid = ?"; // db에 데이터 넣기 쿼리

			pst = conn.prepareStatement(sql);
			// 파라미터 설정
			pst.setString(1, menu);
			pst.setString(2, id);

			pst.executeUpdate();// 쿼리 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println("[log]catch 진입?");
			e.printStackTrace();

		} finally {
		
		}

	}
	
	
	
	public void updateDaysQuery(String id, String Days) {
		// 날짜 선택한 결과 업데이트하기

		
		try {
			sql = " update logindatatable set days=? where userid = ?"; // db에 데이터 넣기 쿼리

			pst = conn.prepareStatement(sql);
			// 파라미터 설정
			pst.setString(1, Days);
			pst.setString(2, id);

			pst.executeUpdate();// 쿼리 실행

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();

		} finally {
		
		}

	}
	

	public boolean insertDataQuery(String id, String pw, String name) {
		// db에 사용자 아이디,비밀번호,이름 생성하기
		// TODO Auto-generated method stub
		boolean result = false;
		//System.out.println("[log]insertDataQuery 진입");
		try {
			sql = "insert into LOGINDATATABLE values(?,?,?)"; // db에 데이터 넣기 쿼리

			pst = conn.prepareStatement(sql);
			// 파라미터 설정
			pst.setString(1, id);
			pst.setString(2, pw);

			pst.setString(3, name);
			result = pst.execute();
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println("[log]catch 진입?");
			e.printStackTrace();

		} finally {
			
		}

		return result;
	}

	public void insertBoardQuery(String title, String text, String id, String date) {
		// db에 사용자 아이디,비밀번호,이름 생성하기
		// TODO Auto-generated method stub

		//System.out.println("[log]insertDataQuery 진입");
		try {
			sql = "insert into board values(?,?,?,?)"; // db에 데이터 넣기 쿼리

			pst = conn.prepareStatement(sql);
			// 파라미터 설정
			pst.setString(1, title);
			pst.setString(2, text);
			pst.setString(3, id);
			pst.setString(4, date);
			pst.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println("[log]catch 진입?");
			e.printStackTrace();

		} finally {
	
		}

	}

	public void close() { // jdbc에서 사용한 메모리 반환 시키기

		try {
			// close는 꼭 생성한 순서의 반대로 닫아줘야함
			/*
			 * 생성순서 1. conn으로 연결 확인 2. pst로 쿼리 보내기 3. rs에 결과저장
			 */

			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (conn != null)
				conn.close();

		} catch (SQLException sqlEX) {
			System.out.println(sqlEX);
		}
	}

	public void setInfo() { // DB 연결에 필요한 url id pw 설정하기
		setUrl();
		setUserId();
		setUserPw();
	}

	public void setUrl() { // url 설정
		url = "jdbc:postgresql://localhost:5432/LOGINDATA"; // jdbc 이름 : // 주소 : 포트번호 / 사용데이터베이스
	}

	public void setUserId() { // Id 설정
		userId = "postgres";
	}

	public void setUserPw() { // Pw 설정
		userPw = "8138";
	}

	public Object[][] getArray() {
		// 저장된 2차원 배열(데이터베이스 colum) 반환

		return array;
	}


	

}