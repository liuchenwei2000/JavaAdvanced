/**
 * 
 */
package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socket示例
 * <p>
 * Socket类非常简单易用，因为Java技术隐藏了建立网络连接和通过连接发送数据的复杂过程。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-7-7
 */
public class SocketTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "time-A.timefreq.bldrdoc.gov";// 服务器URL
		/*
		 * 在网络术语中，端口指的并不是物理设备，而是为了便于实现服务器与客户端之间通信所使用的抽象概念。
		 */
		int port = 13;// 端口号
		
		Socket socket = null;
		try {
			/*
			 * 打开一个套接字Socket，这是一个抽象概念，负责使该程序内部和外部之间通信。
			 */
			socket = new Socket(url, port);
			// 从服务器返回一个流对象，可以通过它读取服务器信息
			InputStream is = socket.getInputStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = null;
			while ((s = br.readLine()) != null) {
				System.out.println(s);
			}
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