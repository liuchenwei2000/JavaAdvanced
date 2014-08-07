/**
 * 
 */
package net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddresʾ��
 * <p>
 * �����Ҫ������������������ַ֮�����ת��ʱ������ʹ��InetAddress�ࡣ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��7��
 */
public class InetAddressTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "www.baidu.com";// ������/����
		try {
			// ���ش���ĳ��������InetAddress����
			InetAddress address = InetAddress.getByName(host);
			System.out.println("IP:" + Arrays.asList(address.getHostAddress()));
			
			// ���ش���ĳ������������InetAddress����
			InetAddress[] all = InetAddress.getAllByName(host);
			for (InetAddress inetAddress : all) {
				System.out.println(inetAddress);
			}
			
			// �õ�����������ַ
			System.out.println(InetAddress.getLocalHost());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
