package com.douzone.bookshop.vo;

public class OrderVo {

	private String name;
	private long no;
	private long price;
	private String addr;
	private String orderNo;
	
	private long memberNo;

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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(long memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", price=" + price + ", addr=" + addr + ", orderNo=" + orderNo + ", memberNo="
				+ memberNo + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
