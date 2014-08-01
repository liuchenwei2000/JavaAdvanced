/**
 * 
 */
package net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * HttpConnection示例
 * <p>
 * 演示用Java的网络API访问Web站点。
 * URL（Uniform Resource Locator，统一资源定位符），也被称为网页地址，
 * 是因特网上标准的资源地质，它唯一标示了网络上的一个资源。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年8月1日
 */
public class HttpConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// java.net.URL类来支持它，可以通过它来访问网络资源。
			URL url = new URL("http://www.baidu.com/");
			/*
			 * HttpURLConnection是表示HTTP网络连接的类，由下面的方式获得。
			 * <p>
			 * HttpURLConnection可以模拟一个浏览器，在发出请求的时候，设置各种请求头参数，设置请求参数，传输请求数据；
			 * 在收到响应结果的时候，也可以获得响应头数据和相应内容，它也是对TCP传输流的一种包装。
			 */
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();// 连接

			// 打印相应的头部信息
			Map<String, List<String>> headerFields = connection.getHeaderFields();
			for (String key : headerFields.keySet()) {
				System.out.println(key + "=" + headerFields.get(key));
			}
			
			
			// 打印相应内容
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			
			String s = null;
			while (((s = br.readLine()) == null)) {
				System.out.println(s);
			}
			connection.disconnect();// 断开连接
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
