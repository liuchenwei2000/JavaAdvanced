/**
 * 
 */
package swing;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * UI风格工具类
 * 
 * @author 刘晨伟
 *
 * 创建日期：2008-5-12
 */
public class Style {

	/**
	 * 统一设置系统字体
	 */
	public static void setDefaultUIFont() {
		setUIFont(new Font("宋体", Font.PLAIN, 12));
	}

	/**
	 * 统一设置系统字体
	 */
	public static void setUIFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}
}