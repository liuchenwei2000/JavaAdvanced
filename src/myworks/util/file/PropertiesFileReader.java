/**
 * 
 */
package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Properties文件阅读器
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-17
 */
public class PropertiesFileReader {

	private Properties properties;// 属性集

	/**
	 * Properties文件阅读器
	 * 
	 * @param fileName
	 *            属性文件名
	 * @throws Exception
	 */
	public PropertiesFileReader(String fileName) throws Exception {
		File file = new File(fileName);
		this.properties = new Properties();
		this.properties.load(new FileInputStream(file));
	}

	/**
	 * 根据键名返回对应的值
	 * 
	 * @param key
	 *            键名
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
