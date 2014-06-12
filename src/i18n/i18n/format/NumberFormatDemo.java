/**
 * 
 */
package i18n.format;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * NumberFormat演示类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-24
 */
public class NumberFormatDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = "123456.78";
		// 返回所有语言环境的数组
		for (Locale locale : NumberFormat.getAvailableLocales()) {
			print(locale, value);
		}
	}

	private static void print(Locale locale, String value) {
		System.out.println(locale.getDisplayName());
		// 返回指定语言环境的通用数字格式
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		// 返回指定语言环境的货币格式
		NumberFormat cf = NumberFormat.getCurrencyInstance(locale);
		// 返回指定语言环境的百分比格式
		NumberFormat pf = NumberFormat.getPercentInstance(locale);
		try {
			// 从给定字符串的开始处的文本进行分析，生成一个数字
			double doubleValue = nf.parse(value).doubleValue();
			// 对参数数值进行格式化并返回字符串
			System.out.println("double value = " + nf.format(doubleValue));
			System.out.println("currency value = " + cf.format(doubleValue));
			System.out.println("percent value = " + pf.format(doubleValue));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}