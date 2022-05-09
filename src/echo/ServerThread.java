package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private Socket socket;
	public ServerThread() {
	}
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	public void run() {
		try {
			System.out.println("[클라이언트가 연결 되었습니다]");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			while(true) {
				String msg = br.readLine();
				if ( msg == null) {
					System.out.println("================================");
					System.out.println("<연결종료>");
					break;
				}
				System.out.println("받은메세지: " + msg);
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
			bw.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
