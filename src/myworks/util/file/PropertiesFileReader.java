/**
 * 
 */
package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Properties�ļ��Ķ���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-17
 */
public class PropertiesFileReader {

	private Properties properties;// ���Լ�

	/**
	 * Properties�ļ��Ķ���
	 * 
	 * @param fileName
	 *            �����ļ���
	 * @throws Exception
	 */
	public PropertiesFileReader(String fileName) throws Exception {
		File file = new File(fileName);
		this.properties = new Properties();
		this.properties.load(new FileInputStream(file));
	}

	/**
	 * ���ݼ������ض�Ӧ��ֵ
	 * 
	 * @param key
	 *            ����
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PropertiesFileReader reader = new PropertiesFileReader(
					"files/util.file/config.properties");
			System.out.println(reader.getProperty("ses_version"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}