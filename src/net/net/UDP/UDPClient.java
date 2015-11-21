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
 * UDP发送端示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月31日
 */
public class UDPClient {
	
	/** 发送端口号 */
	private static final int PORT = 8888;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatagramSocket client = null;
		try {
			// 1,用指定端口号创建socket
			client = new DatagramSocket(PORT);

			String message = "Hello world";
			// 2,创建数据报
			DatagramPacket packet = new DatagramPacket(message.getBytes(), 0,
					message.length(), InetAddress.getLocalHost(),
					UDPServer.PORT);
			// 3,发送数据报
			client.send(packet);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				// 4,释放资源
				client.close();
			}
		}
	}
}
