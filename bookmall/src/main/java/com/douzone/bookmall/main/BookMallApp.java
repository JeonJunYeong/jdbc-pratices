package com.douzone.bookmall.main;

import java.util.List;
import java.util.Scanner;

import com.douzone.bookmall.dao.BookDAO;
import com.douzone.bookmall.vo.BookVO;

public class BookMallApp {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scan = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long no = scan.nextLong();
		scan.close();
		
		
		rent(no);
		displayBookInfo();
	}
	
	public static void displayBookInfo() {
		System.out.println("**********도서 정보 출력\"**********");
		
		List<BookVO> list= new BookDAO().findAll();
		
		
		for(BookVO vo : list) {
			System.out.println("["+vo.getNo()+"]"+"책 제목 : "+vo.getTitle()+", 작가 : "+vo.getAuthorName()+", 대여 유무 : "+vo.getState());
		}
	}
	
	public static void rent(long no) {
		new BookDAO().update(no,"대여중");
	}
	
}
