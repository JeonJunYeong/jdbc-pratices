package test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			
			//2. 연결하기
			String url="jdbc:mysql://127.0.0.1:3306/webdb";
			conn=DriverManager.getConnection(url,"webdb","webdb");
			
			//3. Statement 객체 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql= "select no,name from dept";
			rs = stmt.executeQuery(sql);
			
			//5 결과 가져오기 
			while(rs.next()) {
				long no= rs.getLong(1);
				String name = rs.getString(2);
				
				System.out.println(no+","+name);
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
			//6.자원정리
			try {
				if(rs != null) {
					rs.close();
				}
				
				
				if(stmt !=null) {
					stmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
