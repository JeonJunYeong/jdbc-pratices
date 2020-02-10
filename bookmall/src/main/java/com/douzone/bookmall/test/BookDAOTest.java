package com.douzone.bookmall.test;

import java.util.List;

import com.douzone.bookmall.dao.BookDAO;
import com.douzone.bookmall.vo.BookVO;

public class BookDAOTest {

	
	
	public static void main(String[] args) {
		
	
		selectTest();
	}
	
	
	public static void insertTest() {
		
		BookVO vo = new BookVO();
		new BookDAO().insert(vo);
	}
	
	public static void selectTest() {
		
		List<BookVO> list=new BookDAO().findAll();
		
		for(BookVO vo: list) {
			System.out.println(vo.toString());
		}
		
	}
	
}
