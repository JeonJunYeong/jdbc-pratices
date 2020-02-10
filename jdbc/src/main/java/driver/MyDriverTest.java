package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDriverTest {

	
	public static void main(String[] args) {
		//1.JDBC Driver(MyDriver) 로딩
		try {
			Class.forName("driver.MyDriver");
			
			
			//2. 연결하기
			String url="jdbc:mydb//127.0.0.1:9999/webdb";
			Connection connection=DriverManager.getConnection(url,"webdb","webdb");
			System.out.println(connection);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
