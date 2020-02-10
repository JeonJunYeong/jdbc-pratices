package chat.client.win;

import java.io.PrintWriter;

public class User {

	private String nickname;
	private PrintWriter pw;
	private boolean leader;
	
	
	public User(String nickname,PrintWriter pw) {
		this.nickname= nickname;
		this.pw=pw;
	}
	
	
	
	public User(String nickname,PrintWriter pw,boolean leader) {
		this.nickname= nickname;
		this.pw=pw;
		this.leader = leader;
	}
	
	
	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
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
