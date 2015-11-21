/**
 * 
 */
package i18n.format;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DateFormat演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-27
 */
public class DateFormatDemo {

	/** 格式化风格 数组 */
	private static final int[] STYLES = { DateFormat.DEFAULT, DateFormat.FULL,
			DateFormat.LONG, DateFormat.MEDIUM, DateFormat.SHORT };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();
		print(Locale.getDefault(), date);
	}

	private static void print(Locale locale, Date date) {
		System.out.println(locale.getDisplayName());
		for (int style : STYLES) {
			// 根据语言环境和格式化风格返回日期格式化器
			DateFormat df1 = DateFormat.getDateInstance(style, locale);
			// 根据语言环境和格式化风格返回时间格式化器
			DateFormat df2 = DateFormat.getTimeInstance(style, locale);
			System.out.println("date : " + df1.format(date));
			System.out.println("time : " + df2.format(date));
			System.out.println();
		}
	}
}
