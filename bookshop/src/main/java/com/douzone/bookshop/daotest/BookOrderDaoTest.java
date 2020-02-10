package com.douzone.bookshop.daotest;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.dao.BookOrderDao;
import com.douzone.bookshop.vo.BookOrderVo;

public class BookOrderDaoTest {

	public static void main(String[] args) {
		//insertTest(2,1,7);
		//insertTest(2,3,7);
		insertTest(2,1,8);
	}
	
	public static void insertTest(long amount,long book_no,long order_no) {
		
		BookOrderVo vo = new BookOrderVo();
		
		vo.setAmount(amount);
		vo.setBookNo(book_no);
		vo.setOrderNo(order_no);
		
		new BookOrderDao().insert(vo);
	}
	
	public static void findAllTest() {
		
		List<BookOrderVo> list = new ArrayList<BookOrderVo>();
		
		list=new BookOrderDao().findAll();
		
		System.out.println("======전체 주문 책 리스트입니다=====");
		for(BookOrderVo vo : list) {
			System.out.println("주문번호 : "+vo.getOrderNo()+", 도서번호 : "+vo.getBookNo()+", 도서제목 :"+vo.getBookName()+", 수량 :"+vo.getAmount());
		}
		
	}
	public static void findOrderUserTest(long memberNo,String userName) {
		
		List<BookOrderVo> list = new ArrayList<BookOrderVo>();
		
		list=new BookOrderDao().findOrderUser(memberNo);
		if(list.size()!=0) {
			System.out.println(userName+"님의 도서 주문내역입니다");
			for(BookOrderVo vo : list) {
				System.out.println(" 도서번호 : "+vo.getBookNo()+", 도서제목 : "+vo.getBookName()+", 수량 : "+vo.getAmount());
			}
		}
	}
}
