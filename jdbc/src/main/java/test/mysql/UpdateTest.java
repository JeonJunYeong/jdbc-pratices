package test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		DeptVO vo= new DeptVO();
		
		vo.setNo(3L);
		vo.setName("Sales Team");
		
		
		boolean result = update(vo);
		if(result) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}

	
	
	public static boolean update(DeptVO vo) {
	
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			
			//2. 연결하기
			String url="jdbc:mysql://127.0.0.1:3306/webdb";
			conn=DriverManager.getConnection(url,"webdb","webdb");
			
			//3. Statement 객체 생성
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql= "update dept set name = '"+vo.getName()+"' where no="+vo.getNo();
			int count = stmt.executeUpdate(sql);
			//5 성공여부 
			
			result = count ==1;
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:"+e);
		}catch (SQLException e) {
			System.out.println("ERROR:"+e);
		}finally {
			//6.자원정리
			try {
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
		
		return result;
		

		
		
		
	}
}
