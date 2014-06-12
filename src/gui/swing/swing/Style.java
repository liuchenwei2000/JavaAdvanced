/**
 * 
 */
package swing;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/**
 * UI��񹤾���
 * 
 * @author ����ΰ
 *
 * �������ڣ�2008-5-12
 */
public class Style {

	/**
	 * ͳһ����ϵͳ����
	 */
	public static void setDefaultUIFont() {
		setUIFont(new Font("����", Font.PLAIN, 12));
	}

	/**
	 * ͳһ����ϵͳ����
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