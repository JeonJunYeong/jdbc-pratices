package hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {
		
		Scanner scan= new Scanner(System.in);
		
		System.out.print("이름>>");
		String name = scan.nextLine();
		
		
		
		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setFirstName(name);
		
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeeVO> list = dao.findByName(employeeVO);
		
		
		for(EmployeeVO vo : list) {
			System.out.println(vo);
		}
		
		
		
		scan.close();
		
		
	}

}
