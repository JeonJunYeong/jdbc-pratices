package com.douzone.bookshop.daotest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.douzone.bookshop.dao.OrderDao;
import com.douzone.bookshop.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		insertTest(40000,"서울",new OrderDao().getOrderNum(),1);
		findAllTest();

	}
	
	public static void insertTest(long price, String addr,String orderNo,long memberNo) {
	
		
		OrderVo vo=new OrderVo();
		
		vo.setPrice(price);
		vo.setAddr(addr);
		vo.setOrderNo(orderNo);
		vo.setMemberNo(memberNo);
		
		new OrderDao().insert(vo);
				
	}
	
	public static void findAllTest() {
		
		List<OrderVo> list = new OrderDao().findAll();
		System.out.println("=====주문 내역입니다=====");
		for(OrderVo vo : list) {
			System.out.println("이름/이메일:"+vo.getName()+", 가격 : "+vo.getPrice()+", 주소 : "+vo.getAddr()+", 주문 번호 : "+vo.getOrderNo());
		}
		
	}
	
	
	
	
}
