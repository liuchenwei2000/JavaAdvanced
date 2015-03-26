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
 * 模拟Web服务器示例
 * <p>
 * Web服务器实际上也是采用了TCP通信模型，本例创建一个简易的Web服务器。
 * <p>
 * HTTP是一个应用层次的协议，是基于TCP协议的，它底层的通信原理依然需要遵循TCP的编程模型。
 * 只不过HTTP还定义了其他一些Web通信的规范。
 * <p>
 * WebServer启动之后，可通过浏览器访问 http://localhost:8080/
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-1-21
 */
public class WebServer {

	/** 线程池 */
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
	 * HTTP请求处理类
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
				// 打印 html
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
