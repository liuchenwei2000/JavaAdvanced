/**
 * 
 */
package i18n.sort;

import java.text.Collator;
import java.util.Locale;

/**
 * Collator演示类
 * <p>
 * 用于执行区分语言环境的String比较。使用此类可为自然语言文本构建搜索和排序例程。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-27
 */
public class CollatorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "我";
		String b = "我们";
		System.out.println(Collator.getInstance().compare(a, b));
		Collator usCollator = Collator.getInstance(Locale.US);
		usCollator.setStrength(Collator.PRIMARY);
		if (usCollator.compare("abc", "ABC") == 0) {
			System.out.println("Strings are equivalent");
		}
	}
}