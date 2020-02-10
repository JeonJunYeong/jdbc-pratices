package com.douzone.bookshop.daotest;

import java.util.List;

import com.douzone.bookshop.dao.CategoryDao;
import com.douzone.bookshop.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {

//		insertTest("소설");
//		insertTest("경제");
//		insertTest("IT");
	}
	
	public static void insertTest(String type) {
		
		new CategoryDao().insert(type);
		
	}

	public static void findAllTest() {
		
		List<CategoryVo> list =new CategoryDao().findAll();
		System.out.println("=====카테고리=====");
		for(CategoryVo vo : list) {
			System.out.println("종류 : "+vo.getType());
		}
		
	}
	
	
}
