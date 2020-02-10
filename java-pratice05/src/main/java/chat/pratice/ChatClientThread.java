package chat.pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClientThread  extends Thread{

	
	private BufferedReader br;
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket=socket;
	}
	
	
	@Override
	public void run() {
		
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			
			while(true) {
				String now=br.readLine();
				
				
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
