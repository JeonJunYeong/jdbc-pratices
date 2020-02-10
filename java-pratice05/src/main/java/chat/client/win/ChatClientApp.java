package chat.client.win;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {

	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT=5000;
	
	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);
		Socket socket =null;
		BufferedReader br;
		
		
		try {
			socket =new Socket();
			socket.connect(new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
			PrintWriter pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);// true 버퍼가 차지 않아도 즉시 flush 
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			
			while( true ) {
				
				System.out.println("대화명을 입력하세요.");
				System.out.print(">>> ");
				name = scanner.nextLine();
				pw.println("join:"+name);
				
				String now=br.readLine();
				
				String[] arr=now.split(":");
				
				
				if("err".equals(arr[1])) {
					System.out.println("중복된 닉네임입니다. 다시 입력해주세요");
					continue;
				}else if("join".equals(arr[1])) {
					break;
				}
				
				if (name.isEmpty() == false  ) {
					break;
				}
				
				System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
			}
			
			scanner.close();

			
			//pw.println("join:"+name);
			new ChatWindow(name,socket).show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
