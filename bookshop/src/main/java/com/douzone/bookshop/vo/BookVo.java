package com.douzone.bookshop.vo;

public class BookVo {

	private long no;
	private String name;
	private long price;

	private long categoryNo;
	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	


	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(long category_no) {
		this.categoryNo = category_no;
	}
	

	
	
}
