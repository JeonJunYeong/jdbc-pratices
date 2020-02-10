package com.douzone.bookshop.vo;

public class MemberVo {

	private long no;
	private String name;
	private String phone;
	private String mail;
	private String password;
	
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MemberVO [no=" + no + ", name=" + name + ", phone=" + phone + ", mail=" + mail + ", password="
				+ password + "]";
	}
	
	
	
	
	
	
}
