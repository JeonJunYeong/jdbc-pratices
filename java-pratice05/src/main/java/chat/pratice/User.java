package chat.pratice;

import java.io.PrintWriter;

public class User {

	private String nickname;
	private PrintWriter pw;
	
	public User(String nickname,PrintWriter pw, boolean b) {
		this.nickname= nickname;
		this.pw=pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}
	
}
