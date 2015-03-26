/**
 * 
 */
package net.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端使示例
 * <p>
 * 也可以在WINDOWS下使用 "telnet localhost 8189" 命令进行测试。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-1-21
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int number = 5;
		for (int i = 0; i < number; i++) {
			new Thread(new ClientTask()).start();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class ClientTask implements Runnable {

		public void run() {
			Socket socket = null;
			try {
				// 打开一个套接字(Socket)建立和服务器的连接
				socket = new Socket("localhost", 8189);
				// 获取服务器的输入流
				InputStream stream = socket.getInputStream();
				Scanner scanner = new Scanner(stream);
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
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