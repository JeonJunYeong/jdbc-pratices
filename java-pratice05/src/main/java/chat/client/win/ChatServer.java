package chat.client.win;

import java.io.IOException;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.client.win.User;

public class ChatServer {

	private static final int PORT= 5000;
	
	public static void main(String[] args) {
	
		ServerSocket serverSocket = null;
		List<User> Users = new ArrayList<User>();
		try {
			//1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			//2. 바인딩
			
			serverSocket.bind(new InetSocketAddress("0.0.0.0",PORT));
			log("연결 기다림"+":"+PORT);
			
			//3. 요청 대기 
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket,Users).start();
			}
			
		} catch (IOException e) {
			log("error");
		}
		
		
		
	}
	
	
	
	public static void log(String log) {
		System.out.println("[server]"+ log);
	}
}
