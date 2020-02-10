package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.AuthorDAO;
import com.douzone.bookmall.vo.AuthorVO;

public class AuthorDAOTest {

	public static void main(String[] args) {
		//insertTest("김난도");
		//insertTest("천상병");
		//insertTest("조정래");
		//insertTest("원수연");
		selectTest();
	}

	public static void insertTest(String name) {
		
		AuthorVO vo=new AuthorVO();
		vo.setName(name);
		
		
		new AuthorDAO().insert(vo);
		
	}
	
	
	public static void selectTest() {
		
		List<AuthorVO> list =new AuthorDAO().findAll();
		
		for(AuthorVO vo : list) {
			System.out.println(vo.toString());
		}
	}
	
	
}
