package test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	
		public static void main(String[] args) {
		//1. jdbc driver(mysql) loading
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				
				//2. 연결하기
				String url="jdbc:mysql://127.0.0.1:3306/webdb";
				connection=DriverManager.getConnection(url,"webdb","webdb");
				System.out.println("연결 성공!");
				
			
			
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패:"+e);
			}catch (SQLException e) {
				System.out.println("ERROR:"+e);
			}finally {
				try {
					if(connection !=null) {
						connection.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
	
}
