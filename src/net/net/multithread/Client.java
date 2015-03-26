/**
 * 
 */
package net.multithread;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * �ͻ���ʹʾ��
 * <p>
 * Ҳ������WINDOWS��ʹ�� "telnet localhost 8189" ������в��ԡ�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-1-21
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
				// ��һ���׽���(Socket)�����ͷ�����������
				socket = new Socket("localhost", 8189);
				// ��ȡ��������������
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