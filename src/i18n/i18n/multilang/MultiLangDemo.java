/**
 * 
 */
package i18n.multilang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 实现多语言演示
 * <p>
 * Java既然作为一个跨平台的语言就必然要在各种不同的语言环境中使用。
 * 为了解决这个问题Java提供了一个工具类ResourceBundle，帮助实现Java的国际化，
 * 核心的思想就是：对不同的语言提供一个不同的资源文件。
 * <p>
 * 国际化的步骤
 * <li>1，编写要实现国际化的主程序。
 * <li>2，定义资源文件，注意资源文件的定义要符合一定的规范, 规范如下：</br>
 * 如果将缺省的资源文件的文件名取名为message.properties，
 * 那么相应的其他语言的资源文件名就是message_语言代码_国家代码.properties。</br>
 * 例如: </br>
 * 汉语的语言代码是zh，国家代码是CN，所以简体中文的资源文件名就是: message_zh_CN.properties，
 * 英语的语言代码是en，美国的国家代码是US，所以美国英语的资源文件就是: message_en_US.properties。
 * <li>3，根据软件的使用环境取得相应的资源文件。
 * <li>4，在取得的资源文件中取得Key对应的值。
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-6-23
 */
public class MultiLangDemo {

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获得缺省的locale
		Locale locale = Locale.getDefault();
		showMessage(locale);
		// 设置定制的语言国家locale
		Locale locale2 = new Locale("en_US");
		showMessage(locale2);
	}

	private static void showMessage(Locale locale) {
		// 获得资源文件
		ResourceBundle rb = ResourceBundle.getBundle("resources/message", locale);
		// 使用ResourceBundle根据key返回对应的value值
		System.out.println("name=" + rb.getString("name"));
		System.out.println("sex=" + rb.getString("sex"));
	}
}
