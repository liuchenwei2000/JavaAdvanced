/**
 * 
 */
package net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddres示例
 * <p>
 * 如果需要在主机名和因特网地址之间进行转换时，可以使用InetAddress类。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月7日
 */
public class InetAddressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "www.baidu.com";// 主机名/域名
		try {
			// 返回代表某个主机的InetAddress对象
			InetAddress address = InetAddress.getByName(host);
			System.out.println("IP:" + Arrays.asList(address.getHostAddress()));
			
			// 返回代表某个主机的所有InetAddress对象
			InetAddress[] all = InetAddress.getAllByName(host);
			for (InetAddress inetAddress : all) {
				System.out.println(inetAddress);
			}
			
			// 得到本地主机地址
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
