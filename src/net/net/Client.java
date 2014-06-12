/**
 * 
 */
package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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
		try {
			// ��һ���׽���(Socket)�����ͷ�����������
			Socket socket = new Socket("localhost", 8189);
			try {
				// ��ȡ��������������
				InputStream stream = socket.getInputStream();
				Scanner scanner = new Scanner(stream);
				while (scanner.hasNextLine()) {
					System.out.println(scanner.nextLine());
				}
			} finally {
				socket.close();// �ر�����
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}