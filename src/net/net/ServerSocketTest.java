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
 * ��������ʾ��
 * <p>
 * JDK�ṩ����Ӧ�Ŀ����ӿڡ�������java.net������Ϊ��׼���ģ����õĽӿں��������ServerSocket��Socket��URL�ȡ�
 * TCPһ������C/Sģʽ��Ӧ�ó������Ƕ�����ڿͻ��˺ͷ������������֡�
 * <p>
 * ͨ��ServerSocket����һ���ܽ��ܿͻ�������ķ�������<p>
 * ÿһ�����������򣬱���һ��HTTP Web��������������ϵ�ִ�������ѭ��������</br>
 * <li>1��ͨ�������������ӿͻ��˽��յ�һ������
 * <li>2��ͨ��ĳ�ַ�ʽ��ȡ��Ϣ
 * <li>3��ͨ��������������ͻ��˷�����Ϣ
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-1-21
 */
public class ServerSocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ����һ������ 8189 �˿ڵķ������׽���
			ServerSocket server = new ServerSocket(8189);
			
			/**
			 * ��Ϊ�Ǹ�ʾ����������û��ʹ��ѭ�����������Ե�һ���ͻ�����ֹ����ʱ���������ͻ��Զ�ֹͣ��
			 */
			
			// �ȴ����ӡ��÷���������ǰ�߳�ֱ����������Ϊֹ������һ����ʾ�����Ѿ�������Socket����
			Socket client = server.accept();
			try {
				/**
				 * �������Ϳͻ���ͨ��������ͨ�ţ�
				 * �������������������Ϣ�����Ϊ�ͻ��˳��������
				 * ͬʱ���Կͻ��˳��������������ᱻ�����ڷ�������������
				 */
				OutputStream out = client.getOutputStream();
				InputStream in = client.getInputStream();
				
				Scanner scanner = new Scanner(in);
				PrintWriter writer = new PrintWriter(out, true);
				writer.println("Hello,this is server.\nEnter 8 to exit.");

				boolean done = false;

				while ((!done) && scanner.hasNextLine()) {
					String line = scanner.nextLine();
					// �������Կͻ��˵�������л���
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