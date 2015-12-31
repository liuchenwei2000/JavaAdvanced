/**
 * 
 */
package net.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP接收端示例
 * <p>
 * UDP协议（User Datagram Protocol）与TCP协议同处于TCP/IP的传输层，
 * 它们都是用于数据传输的，只不过它没有提供像TCP那样的可靠机制。
 * 但是，它具有更小的消耗和更快的速度，适用于一些点对点传输的，并且安全性要求不高的网络应用程序。
 * <p>
 * Java的UDP网络应用程序需要分为接收端和发送端两部分。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月31日
 */
public class UDPServer {

	/** 服务端口号 */
	public static final int PORT = 9999;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DatagramSocket server = null;
		try {
			// 1,用指定端口号创建socket
			server = new DatagramSocket(PORT);

			byte[] buffer = new byte[1024];
			// 2,创建数据报供接收数据使用
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			// 3,接收数据
			server.receive(packet);
			// 4,packet.getData()得到数据
			String content = new String(packet.getData(), 0, packet.getLength());
			
			System.out.println(content);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				// 5,释放资源
				server.close();
			}
		}
	}
}
