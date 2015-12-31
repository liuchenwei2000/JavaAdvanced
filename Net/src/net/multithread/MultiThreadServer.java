/**
 * 
 */
package net.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 服务器处理多客户端请求示例
 * <p>
 * 采用多线程编程模型以后，不仅提高了服务器端程序处理并发的能力，而且对于代码的管理也是有帮助的。
 * 主线程只负责监听和接受请求，而业务代码则放在Handler中。
 * <p>
 * 也可以通过WINDOWS的"telnet"命令的"open localhost 8189"进行测试。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-1-21
 */
public class MultiThreadServer {

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8189);
			int i = 1;
			
			/*
			 * 每当程序建立一个新的套接字连接，也就是说当 accept 被成功调用的时候将创建
			 * 一个新的线程来处理服务器和该客户端之间的连接，主程序将立即返回并等待下一个连接。
			 */
			while (true) {
				Socket client = server.accept();
				System.out.println("client thread number : " + i);
				System.out.println("client ip : " + client.getInetAddress().getHostAddress());
				/*
				 * 每一个连接都会启动一个新的线程，因而多个客户端就可以同时连接到服务器了。
				 * 但这种每个连接都会生成一个独立线程的方式并不能满足高性能服务器的要求。
				 */
				new Thread(new Handler(i++, client)).start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 客户端请求处理类
	 */
	private static class Handler implements Runnable {

		private int number;
		private Socket socket;

		public Handler(int number, Socket socket) {
			this.number = number;
			this.socket = socket;
		}

		public void run() {
			try {
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();

				Scanner scanner = new Scanner(in);
				PrintWriter writer = new PrintWriter(out, true);

				writer.println("Hello,this is server.");
				writer.println("Thread " + number + " will work for you.");
				writer.println("Enter 8 to exit.");

				boolean done = false;

				while ((!done) && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					writer.println("This is server echo: " + line);
					if (line.trim().equals("8")) {
						done = true;
					}
				}
				
				writer.close();
				scanner.close();
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
