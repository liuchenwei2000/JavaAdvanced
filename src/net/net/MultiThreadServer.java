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
 * �����������ͻ�������ʾ��
 * <p>
 * Ҳ����ͨ��WINDOWS��"telnet"�����"open localhost 8189"���в��ԡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-1-21
 */
public class MultiThreadServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8189);
			int i = 1;
			
			/**
			 * ÿ��������һ���µ��׽������ӣ�Ҳ����˵��accept���ɹ����õ�ʱ�򽫴���
			 * һ���µ��߳�������������͸ÿͻ���֮������ӣ��������������ز��ȴ���һ�����ӡ�
			 */
			while (true) {
				Socket client = server.accept();
				System.out.println("client thread number : " + i);
				System.out.println("client ip : " + client.getInetAddress().getHostAddress());
				/**
				 * ÿһ�����Ӷ�������һ���µ��̣߳��������ͻ��˾Ϳ���ͬʱ���ӵ��������ˡ�
				 * ������ÿ�����Ӷ�������һ�������̵߳ķ�ʽ��������������ܷ�������Ҫ��
				 */
				new Thread(new Handler(i++, client)).start();
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * �ͻ�����������
 */
class Handler implements Runnable {

	private int number;
	private Socket socket;

	public Handler(int number, Socket socket) {
		this.number = number;
		this.socket = socket;
	}

	public void run() {
		try {
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
			} finally {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}