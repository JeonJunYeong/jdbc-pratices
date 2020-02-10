package com.douzone.bookshop.daotest;

import java.util.ArrayList;
import java.util.List;

import com.douzone.bookshop.dao.MemberDao;
import com.douzone.bookshop.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		
		//insertTest("홍길동","010-1234-5678","abcdefgh@naver.com", "1234");
		//insertTest("김갑동","010-2222-4444","aaabbb@gmail.com","5678");
		
		findAllTest();
	}

	
	public static void insertTest(String name,String phone,String mail,String password) {
		MemberVo vo=new MemberVo();
		
		vo.setName(name);
		vo.setPhone(phone);
		vo.setMail(mail);
		vo.setPassword(password);
		
		
		new MemberDao().insert(vo);

	}

	public static void findAllTest() {
		
		List<MemberVo> list =new MemberDao().findAll();
		
		System.out.println("=====회원정보=====");
		for(MemberVo vo : list) {
			System.out.println("이름 : "+vo.getName()+", 휴대전화 : "+vo.getPhone()+", 이메일 : "+vo.getMail()+", 비밀번호 : "+vo.getPassword());
		}
		
	}
	
	
}
