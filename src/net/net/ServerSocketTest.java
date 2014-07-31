/**
 * 
 */
package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 服务器端示例
 * <p>
 * JDK提供了相应的开发接口————java.net包就是为此准备的，常用的接口和类包括：ServerSocket、Socket、URL等。
 * TCP一般用于C/S模式的应用程序，它们都会存在客户端和服务器端两部分。
 * <p>
 * 通过ServerSocket建立一个能接受客户端请求的服务器。<p>
 * 每一个服务器程序，比如一个HTTP Web服务器，都不间断地执行下面的循环操作：</br>
 * <li>1，通过输入数据流从客户端接收到一个命令
 * <li>2，通过某种方式获取信息
 * <li>3，通过输出数据流给客户端发送信息
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-1-21
 */
public class ServerSocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// 创建一个监听 8189 端口的服务器套接字
			ServerSocket server = new ServerSocket(8189);
			
			/**
			 * 因为是个示例程序，这里没有使用循环监听，所以当一个客户端终止连接时，服务器就会自动停止。
			 */
			
			// 等待连接。该方法阻塞当前线程直到建立连接为止，返回一个表示连接已经建立的Socket对象
			Socket client = server.accept();
			try {
				/**
				 * 服务器和客户端通过流进行通信：
				 * 服务器输出流的所有信息都会成为客户端程序的输入
				 * 同时来自客户端程序的所有输出都会被包含在服务器输入流中
				 */
				OutputStream out = client.getOutputStream();
				InputStream in = client.getInputStream();
				
				Scanner scanner = new Scanner(in);
				PrintWriter writer = new PrintWriter(out, true);
				writer.println("Hello,this is server.\nEnter 8 to exit.");

				boolean done = false;

				while ((!done) && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					// 服务器对客户端的输入进行回显
					writer.println("This is server echo: " + line);
					if (line.trim().equals("8")) {
						done = true;
					}
				}
			} finally {
				client.close();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}