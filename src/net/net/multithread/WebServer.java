/**
 * 
 */
package net.multithread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ģ��Web������ʾ��
 * <p>
 * Web������ʵ����Ҳ�ǲ�����TCPͨ��ģ�ͣ���������һ�����׵�Web��������
 * <p>
 * HTTP��һ��Ӧ�ò�ε�Э�飬�ǻ���TCPЭ��ģ����ײ��ͨ��ԭ����Ȼ��Ҫ��ѭTCP�ı��ģ�͡�
 * ֻ����HTTP������������һЩWebͨ�ŵĹ淶��
 * <p>
 * WebServer����֮�󣬿�ͨ����������� http://localhost:8080/
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-1-21
 */
public class WebServer {

	/** �̳߳� */
	private static ExecutorService threadPool = Executors.newFixedThreadPool(5);
	
	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			
			while (true) {
				Socket client = server.accept();
				System.out.println("client ip : " + client.getInetAddress().getHostAddress());
				threadPool.execute(new HttpRequestHandler(client));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HTTP��������
	 */
	private static class HttpRequestHandler implements Runnable {

		private Socket socket;

		public HttpRequestHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				OutputStream out = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(out, true);
				// ��ӡ html
				writer.println("<html><head><title>");
				writer.println("Welcome");
				writer.println("</title></head><body>");
				writer.println("This is a web page.");
				writer.println("</body></html>");
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
