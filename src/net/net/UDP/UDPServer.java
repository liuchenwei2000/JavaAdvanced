/**
 * 
 */
package net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP���ն�ʾ��
 * <p>
 * Java��UDP����Ӧ�ó�����Ҫ��Ϊ���ն˺ͷ��Ͷ������֡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��31��
 */
public class UDPServer {

	/** ����˿ں� */
	public static final int PORT = 9999;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatagramSocket server = null;
		try {
			// 1,��ָ���˿ںŴ���socket
			server = new DatagramSocket(PORT);

			byte[] buffer = new byte[1024];
			// 2,�������ݱ�����������ʹ��
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			// 3,��������
			server.receive(packet);
			// 4,packet.getData()�õ�����
			String content = new String(packet.getData(), 0, packet.getLength());
			
			System.out.println(content);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				// 5,�ͷ���Դ
				server.close();
			}
		}
	}
}
