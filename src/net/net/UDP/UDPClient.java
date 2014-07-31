/**
 * 
 */
package net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * UDP���Ͷ�ʾ��
 * <p>
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��31��
 */
public class UDPClient {
	
	/** ���Ͷ˿ں� */
	private static final int PORT = 8888;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatagramSocket client = null;
		try {
			// 1,��ָ���˿ںŴ���socket
			client = new DatagramSocket(PORT);

			String message = "Hello world";
			// 2,�������ݱ�
			DatagramPacket packet = new DatagramPacket(message.getBytes(), 0,
					message.length(), InetAddress.getLocalHost(),
					UDPServer.PORT);
			// 3,�������ݱ�
			client.send(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				// 4,�ͷ���Դ
				client.close();
			}
		}
	}
}
