/**
 * 
 */
package setting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.prefs.Preferences;

/**
 * Preferences演示类
 * <p>
 * 其中usageCount程序每运行一次都会+1，这个信息会被作为用户的偏好保存在系统中。 
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-5-13
 */
public class PreferencesDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * 这里用的是 userNodeForPackage()，最好将"user"用于个别用户的偏好，
		 * 将 systemNodeForPackage() 用于通用的安装配置。
		 * 因为main方法是static的，所以PreferencesDemo.class可以用来标识节点。
		 * 但是在非静态方法内部，通常使用getClass()，尽管并非一定要把当前类作为节点标识符。
		 * 一旦创建了节点，就可以用它来加载或者读取数据了。
		 */
		Preferences p = Preferences.userNodeForPackage(PreferencesDemo.class);
		p.put("Location", "Oz");
		p.putInt("Companions", 4);
		p.putBoolean("Are there witches?", true);
		// 必须为key提供默认值(这里 UsageCount 默认值是 0)
		int usageCount = p.getInt("UsageCount", 0);
		usageCount++;
		p.putInt("UsageCount", usageCount);
		Iterator<?> it = Arrays.asList(p.keys()).iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			System.out.println(key + " : " + p.get(key, null));
		}
		System.out.println("How many companions ? " + p.getInt("Companions", 0));
	}
}