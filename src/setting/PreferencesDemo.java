/**
 * 
 */
package setting;

import java.util.Arrays;
import java.util.Iterator;
import java.util.prefs.Preferences;

/**
 * Preferences��ʾ��
 * <p>
 * ����usageCount����ÿ����һ�ζ���+1�������Ϣ�ᱻ��Ϊ�û���ƫ�ñ�����ϵͳ�С� 
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-13
 */
public class PreferencesDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * �����õ��� userNodeForPackage()����ý�"user"���ڸ����û���ƫ�ã�
		 * �� systemNodeForPackage() ����ͨ�õİ�װ���á�
		 * ��Ϊmain������static�ģ�����PreferencesDemo.class����������ʶ�ڵ㡣
		 * �����ڷǾ�̬�����ڲ���ͨ��ʹ��getClass()�����ܲ���һ��Ҫ�ѵ�ǰ����Ϊ�ڵ��ʶ����
		 * һ�������˽ڵ㣬�Ϳ������������ػ��߶�ȡ�����ˡ�
		 */
		Preferences p = Preferences.userNodeForPackage(PreferencesDemo.class);
		p.put("Location", "Oz");
		p.putInt("Companions", 4);
		p.putBoolean("Are there witches?", true);
		// ����Ϊkey�ṩĬ��ֵ(���� UsageCount Ĭ��ֵ�� 0)
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