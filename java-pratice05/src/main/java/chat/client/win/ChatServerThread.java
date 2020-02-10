package chat.client.win;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import chat.client.win.User;

public class ChatServerThread extends Thread{

	private List<User> users;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	private User temp;
	
	public ChatServerThread(Socket socket) {
		this.socket=socket;
	}
	
	public ChatServerThread(Socket socket,List<User> users) {
		this.socket=socket;
		this.users=users;
	}

	@Override
	public void run() {
		
		try {
			//IOStream
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);// true 버퍼가 차지 않아도 즉시 flush 
			

			while(true) {
				// 데이터 읽기 
				String data = br.readLine();
				if(data == null) {
					log("클라이언트로 부터 연결 끊김");
					doQuit(this.temp.getNickname());
					break;
				}
					
				String[] token = data.split(":");
					if("join".equals(token[0])) {
						if(!Check(token[1])) {
							doJoin(token[1], pw);
						}else {
							pw.println("ack:err");
						}
					}else if("message".equals(token[0])) {
						doMsg(token[1]);
					}else if("quit".equals(token[0])) {
						doQuit(token[1]);	
					}else if("to".equals(token[0])) {
						
						String[] now=token[1].split("/");
						
						if(Check(now[0])&&!now[0].equals(this.temp.getNickname()))
						whisper(now[0],now[1]);
						else if(now[0].equals(this.temp.getNickname())){
							this.temp.getPw().println("자신에게는 보낼 수 없습니다");
						}
						else {
							this.temp.getPw().println("존재하지 않는 사용자입니다");
						}
					}else if("ban".equals(token[0])) {
						
						String id=token[1];
						
						if(this.temp.isLeader()) {
							if(this.temp.getNickname().equals(id))
								this.temp.getPw().println("자신을 추방 할 수는 없습니다");
							else
								ban(id);
						}else {
							this.temp.getPw().println("권한이 없습니다.");
						}
						
						
					}else if("방장".equals(token[0])) {
						String id=token[1];
						
						if(this.temp.isLeader()) {
							if(this.temp.getNickname().equals(id))
								this.temp.getPw().println("당신이 방장입니다.");
							else
								dele(id);
						}else
							this.temp.getPw().println("권한이 없습니다");
						
					}
					
				
				
				
				log("received:"+data);
			
			}	
		
		
		}catch(SocketException e) {
			log("Sudden Close");
			if(users.size()==1)
				removeUser(this.temp.getNickname());
			else
			doQuit(this.temp.getNickname());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(socket!=null && socket.isClosed()) {
					socket.close();
				}
			}catch(IOException e) {
				//log()
			}
		}
		
		
		
	}
	// 방장 위임
	private void dele(String nickname) {
		if(Check(nickname)) {
			for(User user : users) {
				if(nickname.equals(user.getNickname())){
					user.setLeader(true);
					this.temp.setLeader(false);
					broadcast(nickname+"님이 새롭게 방장이 되었습니다.");
					break;
				}	
			}
		}else {
			this.temp.getPw().println("존재하지 않는 사용자입니다");
		}
		
		
	}
	
	
	//강퇴기능 방장만 가능함 첫번째 방장은 입장순 이후 랜덤으로 방장 지정 ban id 로 강퇴 가능 
	private void ban(String nickname) {
	
		if(Check(nickname)) {
			
			for(User user : users) {
				if(nickname.equals(user.getNickname())) {
					user.getPw().println("ack:quit");
					break;
				}
			}
			removeUser(nickname);
			broadcast(this.temp.getNickname()+"님이"+nickname+"님을 추방하였습니다");		
		}else {
			this.temp.getPw().println("존재하지 않는 사용자입니다");
		}
		
	}
	
	private boolean Check(String nickname) {
		
		for(User user: users) {
			if(nickname.equals(user.getNickname())) {
				return true;
			}
		}
		return false;
	}
	
	// 채팅참여 
	private void doJoin(String nickname,PrintWriter pw) {
		

		pw.println("ack:join");
		pw.flush();
		
		if(users.size()==0)
		temp = new User(nickname, pw,true);
		else
		temp = new User(nickname,pw, false);
		String data = temp.getNickname()+"님이 참여 하였습니다.";
		
		addUser(temp);
		broadcast(data);
	}
	//리스트에 유저 추가 
	private void addUser(User user) {
		synchronized (users) {
			users.add(user);
		}
		
	}
	//브로드캐스팅 
	private void broadcast(String data) {
		
		synchronized (users) {
			for(User user : users) {
			PrintWriter pw = (PrintWriter)user.getPw();
				pw.println(data);
				pw.flush();
			}
		}
		
	}
	//메시지 기능
	private void doMsg(String msg) {
		
		broadcast(temp.getNickname()+":"+msg);
		
	}
	//quit 입력시 두번 퇴장되는 경우가 발생 체크를 통해 리스트에 존재할경우 나가도록함 
	private void doQuit(String nickname) {
		
		pw.println("ack:quit");	
		pw.flush();
		
		int index=-1;
		if(Check(nickname)) {
			
			for(int i=0;i<users.size();i++) {
				if(nickname.equals(users.get(i).getNickname())) {
					index=i;
					break;
				}
			}
			if(index!=-1) {
				if(users.get(index).isLeader()) {
					int ran=0;
					if(users.size()==1) {
						ran=0;
					}else {
						while(true) {
							ran=(int)Math.random()*users.size()+1;
							System.out.println("RANDOM:"+ran);
							if(!users.get(index).getNickname().equals(users.get(ran).getNickname())) {
								break;
							}
						}
					}
					
					users.get(ran).setLeader(true);
					broadcast("방장인"+nickname+"님이 퇴장하였습니다. "+users.get(ran).getNickname()+"님이 새롭게 방장이되었습니다." );
					
				}else {
					broadcast(nickname+"님이 퇴장하셨습니다");
				}
				removeUser(nickname);
			}
			
		}
		
	}
	//리스트에서 유저삭제 해당 유저가 방장일시 새로운 방장을 다른유저에게 랜덤으로 부여하도록함 
	private void removeUser(String nickname) {
	
		int index=-1;
		for(int i=0;i<users.size();i++) {
			if(users.get(i).getNickname().equals(nickname)) {
				index=i;
				break;
			}
		}
		if(index!=-1) {
			synchronized (users) {
				
				users.remove(index);	
			}
		}
		
	}
	//귓속말 to id 할말 으로 시작하며 보내는이 받는이에게만 출력하도록 함 
	private void whisper(String name,String text) {
	
		for(User user: users) {
			if(name.equals(user.getNickname())) {
				user.getPw().println(this.temp.getNickname()+"님의 귓속말:"+text);
				this.temp.getPw().println(user.getNickname()+"님 에게:"+text);
				break;
				}
			}
		}
		
		
		
	
	public static void log(String log) {
		System.out.println("[server#"+Thread.currentThread().getId()+"]"+ log);
	}
}
