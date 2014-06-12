/**
 * 
 */
package net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socketʾ��
 * <p>
 * Socket��ǳ������ã���ΪJava���������˽����������Ӻ�ͨ�����ӷ������ݵĸ��ӹ��̡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-7-7
 */
public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "time-A.timefreq.bldrdoc.gov";// ������URL
		/**
		 * �����������У��˿�ָ�Ĳ����������豸������Ϊ�˱���ʵ�ַ�������ͻ���֮��ͨ����ʹ�õĳ�����
		 */
		int port = 13;// �˿ں�
		try {
			/**
			 * ��һ���׽���Socket
			 * <p>
			 * ����һ������������ʹ�ó����ڲ����ⲿ֮��ͨ�š�
			 */
			Socket socket = new Socket(url, port);
			try {
				// �ӷ���������һ�������󣬿���ͨ������ȡ��������Ϣ
				InputStream is = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				String s = null;
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}