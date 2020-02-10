package chat.client.win;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
 

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private PrintWriter pw; 

	public ChatWindow(String name,Socket socket) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.socket=socket;
		
		new ChatClientThread(socket).start();
	
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				sendMessage();
			}
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				char keyCode=e.getKeyChar();
				
				if(keyCode==e.VK_ENTER) {
					sendMessage();
				}
			}
		});
		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
		frame.pack();
	}
	
	private void sendMessage() {
		
		
		try {
			pw= new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			String data;
			if(textField.getText().length()!=0)
				data=textField.getText();
			else
				data="";
			
			
			
			if("quit".equals(data)) {
				
				pw.println("quit:"+frame.getTitle());
				
			}else {
				
				
				String[] arr=data.split(" ");
				
				if("to".equals(arr[0])) {
					if(arr.length>2) {
				
					String id=arr[1];
					for(int i=2;i<arr.length;i++)
					{
						
						data=arr[i]+" ";
					}
					
					pw.println("to:"+id+"/"+data);
					}
					else {
						textArea.append("올바르지 않은 양식입니다\n");
						textField.requestFocus();	
						textField.setText("");
					}
				}else if("ban".equals(arr[0])) {
				
					if(arr.length==2) {
						
						String id=arr[1];
						pw.println("ban:"+id);
						
					}else {
						textArea.append("올바르지 않은 양식입니다\n");
						textField.requestFocus();	
						textField.setText("");
					}
				
				}else if("방장".equals(arr[0])) {
					if(arr.length==2) {
						String id=arr[1];
						pw.println("방장:"+id);
					}else {
						textArea.append("올바르지 않은 양식입니다\n");
						textField.requestFocus();	
						textField.setText("");
					}
				}
				else 
				pw.println("message:"+data);
			}
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

public class ChatClientThread  extends Thread{

	
	private BufferedReader br;
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket=socket;
	}
	
	
	@Override
	public void run() {
		
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			
			while(true) {
				String now=br.readLine();
				
				String[] ack_check=now.split(":");
				if(ack_check.length>1) {
					if("quit".equals(ack_check[1]))
						System.exit(0);
				}
				
					textArea.append(now+"\n");
					textField.requestFocus();	
					textField.setText("");
					
				
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
}
	
}
