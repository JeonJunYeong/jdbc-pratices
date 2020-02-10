package com.douzone.bookshop.vo;

public class CartVo {

	private long amount;
	private long price;
	private long memberNo;
	private long bookNo;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}
	public long getBookNo() {
		return bookNo;
	}
	public void setBookNo(long bookNo) {
		this.bookNo = bookNo;
	}
	@Override
	public String toString() {
		return "CartVo [amount=" + amount + ", price=" + price + ", memberNo=" + memberNo + ", bookNo=" + bookNo + "]";
	}
	
	
	
	
	
	
	
	
	
}
