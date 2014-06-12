/**
 * 
 */
package i18n;

import java.util.Locale;

/**
 * Locale演示类
 * <p>
 * Locale对象表示了特定的地理、政治和文化地区。
 * 需要Locale来执行其任务的操作称为语言环境敏感的操作，它使用Locale为用户量身定制信息。
 * <p>
 * Locale 是一种标识所要获取的对象种类的机制：
 * 将Locale对象传给依赖Locale的那些方法，这些方法将根据不同的地域产生不同形式的文本。
 * 但locale只是一种标识对象的机制，不是对象本身的容器。 
 * 
 * @author 刘晨伟
 *
 * 创建日期：2009-2-24
 */
public class LocaleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 获得此Java虚拟机实例的当前默认语言环境值 
		Locale locale = Locale.getDefault();
		print(locale);
		
		// 根据语言和国家构造一个语言环境
		// 语言参数是一个有效的 ISO 语言代码,这些代码是由 ISO-639 定义的小写两字母代码
		// 国家参数是一个有效的 ISO 国家代码,这些代码是由 ISO-3166 定义的大写两字母代码
		Locale usa = new Locale("en","US");
		// 为此Java虚拟机实例设置默认语言环境,这不会影响主机的语言环境 
		Locale.setDefault(usa);
		print(usa);
		
		// Locale 类提供了一些方便的常量，可用这些常量为常用的语言环境创建 Locale 对象
		Locale uk = Locale.UK;
		Locale.setDefault(uk);
		print(uk);
	}

	private static void print(Locale locale) {
		// 返回适合向用户显示的语言环境名
		System.out.println(locale.getDisplayName());
		// 返回适合向用户显示的语言环境国家名
		System.out.println(locale.getDisplayCountry());
		// 返回适合向用户显示的语言环境语言名
		System.out.println(locale.getDisplayLanguage());
	}
}