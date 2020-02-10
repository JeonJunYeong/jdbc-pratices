package com.douzone.bookshop.daotest;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.dao.BookOrderDao;
import com.douzone.bookshop.dao.CartDao;
import com.douzone.bookshop.dao.CategoryDao;
import com.douzone.bookshop.vo.BookOrderVo;
import com.douzone.bookshop.vo.CartVo;
import com.douzone.bookshop.vo.CategoryVo;

public class CartDaoTest {

	public static void main(String[] args) {
		//UpdateTest();
		
		
	}
	
	public static void UpdateTest() {
		CartVo vo = new CartVo();
		
		vo.setAmount(2);
		vo.setPrice(30000);
		vo.setMemberNo(2);
		vo.setBookNo(3);
		
		for(int i=0;i<2;i++)
		new CartDao().check(vo);
	}
	
	public static void findAllTest() {
		List<CartVo> list =new CartDao().findAll();
		System.out.println("=====카트 전체 리스트 입니다=====");
		for(CartVo vo : list) {
			System.out.println("책 제목 : "+vo.getName()+", 수량 : "+vo.getAmount()+", 가격  : "+vo.getPrice());
		}
	}
	
	
	public static void findCartUser(long memberNo,String userName) {
		List<CartVo> list = new ArrayList<CartVo>();
		
		list=new CartDao().findUserCart(memberNo);
		if(list.size()!=0) {
			System.out.println(userName+"님의 장바구니입니다");
			for(CartVo vo : list) {
				System.out.println("책 제목 : "+vo.getName()+", 수량 : "+vo.getAmount()+", 가격  : "+vo.getPrice());
			}
		}
	}
	
}
