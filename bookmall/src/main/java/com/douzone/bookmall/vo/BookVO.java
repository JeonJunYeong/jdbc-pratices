package com.douzone.bookmall.vo;
import com.douzone.bookmall.dao.BookDAO;

public class BookVO {

	private Long no;
	private String title;
	private String state;
	
	
	private Long authorNO;
	private String authorName;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getAuthorNO() {
		return authorNO;
	}
	public void setAuthorNO(Long no) {
		this.authorNO = no;
	}
	
	public void setAuthorNO(String name) {
		this.authorNO = new BookDAO().findByName(name);
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
	@Override
	public String toString() {
		
		return "책 제목:"+title+", 작가:"+authorName+","+ " 대여 유무:"+state;
	}


	
	
}
