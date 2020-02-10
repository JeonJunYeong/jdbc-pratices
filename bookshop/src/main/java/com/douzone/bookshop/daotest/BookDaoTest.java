package com.douzone.bookshop.daotest;

import java.util.List;

import com.douzone.bookshop.dao.BookDao;
import com.douzone.bookshop.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
	//	insertTest(20000,1,"트와일라잇");
	//	insertTest(15000,2,"부의인문학");
	//	insertTest(30000,3,"이것이자바다");
	//	updateTest();
	//	deleteTest();
		findAllTest();
	}
	
	public static void insertTest(long price,long category_no,String name) {
		
		BookVo vo=new BookVo();
		
		vo.setPrice(price);
		vo.setCategoryNo(category_no);
		vo.setName(name);
		
		new BookDao().insert(vo);
		
	}
	
	public static void findAllTest() {
	
		List<BookVo> list = new BookDao().findAll();
		System.out.println("=====책 리스트=====");
		for( BookVo vo : list) {
			System.out.println("책 제목:" +vo.getName()+", 책 가격 : "+vo.getPrice()+", 카테고리 : "+ vo.getCategoryName());
		}
		
		
	}
	public static void updateTest(){

		BookVo vo=new BookVo();
		vo.setName("트와일라잇");
		vo.setNo(1);

		new BookDao().update(vo);
	}
	
	public static void deleteTest() {
		
		new BookDao().delete(1);
	}
}
