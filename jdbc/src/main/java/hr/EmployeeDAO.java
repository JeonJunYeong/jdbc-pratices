package hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {

	public Boolean insert(EmployeeVO vo) {
		return false;
	}
	
	public Boolean delete(Long no) {
		return false;
	}
	
	public Boolean delete(String firstName) {
		return false;
	}
	
	public Boolean update(EmployeeVO vo) {
		return false;
	}
	
	public List<EmployeeVO> findByName(EmployeeVO vo){
		
		List<EmployeeVO> result =new ArrayList<EmployeeVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			
			//2. 연결하기
			String url="jdbc:mysql://127.0.0.1:3306/eMPLOYEES";
			conn=DriverManager.getConnection(url,"hr","hr");
			
			//3. SQL문 준비(Prepare, 완성된 쿼리 X, 파라미터바인딩)
			String sql= "select emp_no,first_name,last_name,hire_date from employees where first_name like ? order by first_name";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩 작업 
			pstmt.setString(1, "%"+vo.getFirstName()+"%");
			
			
			//5. SQL문 실행 
			rs = pstmt.executeQuery();
			
			//6. 결과 가져오기 
			while(rs.next()) {
				long no= rs.getLong(1);
				String firstName = rs.getString(2);
				String lastName= rs.getString(3);
				String hireDate = rs.getString(4);
				
				
				EmployeeVO employeeVO = new EmployeeVO();
				
				employeeVO.setNo(no);
				employeeVO.setFirstName(firstName);
				employeeVO.setLastName(lastName);
				employeeVO.setHireDate(hireDate);
				
				result.add(employeeVO);
				employeeVO.toString();
				
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
				
				
				if(pstmt !=null) {
					pstmt.close();
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
