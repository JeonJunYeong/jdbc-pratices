package com.douzone.bookshop.daotest;

import java.util.List;

import com.douzone.bookshop.dao.MemberDao;
import com.douzone.bookshop.vo.MemberVo;

public class Maintest {

	public static void main(String[] args) {
	
		List<MemberVo> list =  new MemberDao().findMemberNo();

		new MemberDaoTest().findAllTest();
		System.out.println();
		new CategoryDaoTest().findAllTest();
		System.out.println();
		new CartDaoTest().findAllTest();
		System.out.println();
		new BookDaoTest().findAllTest();
		System.out.println();
		for(MemberVo vo : list) {
			new CartDaoTest().findCartUser(vo.getNo(),vo.getName());
			
		}
		System.out.println();
		new OrderDaoTest().findAllTest();
		System.out.println();
	
	
		for(MemberVo vo : list) {
			new BookOrderDaoTest().findOrderUserTest(vo.getNo(),vo.getName());
			
		}

		
		

	}

}
