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
 * HttpConnectionʾ��
 * <p>
 * ��ʾ��Java������API����Webվ�㡣
 * URL��Uniform Resource Locator��ͳһ��Դ��λ������Ҳ����Ϊ��ҳ��ַ��
 * ���������ϱ�׼����Դ���ʣ���Ψһ��ʾ�������ϵ�һ����Դ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��8��1��
 */
public class HttpConnectionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// java.net.URL����֧����������ͨ����������������Դ��
			URL url = new URL("http://www.baidu.com/");
			/*
			 * HttpURLConnection�Ǳ�ʾHTTP�������ӵ��࣬������ķ�ʽ��á�
			 * <p>
			 * HttpURLConnection����ģ��һ����������ڷ��������ʱ�����ø�������ͷ������������������������������ݣ�
			 * ���յ���Ӧ�����ʱ��Ҳ���Ի����Ӧͷ���ݺ���Ӧ���ݣ���Ҳ�Ƕ�TCP��������һ�ְ�װ��
			 */
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();// ����

			// ��ӡ��Ӧ��ͷ����Ϣ
			Map<String, List<String>> headerFields = connection.getHeaderFields();
			for (String key : headerFields.keySet()) {
				System.out.println(key + "=" + headerFields.get(key));
			}
			
			
			// ��ӡ��Ӧ����
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			
			String s = null;
			while (((s = br.readLine()) == null)) {
				System.out.println(s);
			}
			connection.disconnect();// �Ͽ�����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
