/**
 * 
 */
package i18n.sort;

import java.text.Collator;
import java.util.Locale;

/**
 * Collator��ʾ��
 * <p>
 * ����ִ���������Ի�����String�Ƚϡ�ʹ�ô����Ϊ��Ȼ�����ı������������������̡�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-27
 */
public class CollatorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "��";
		String b = "����";
		System.out.println(Collator.getInstance().compare(a, b));
		Collator usCollator = Collator.getInstance(Locale.US);
		usCollator.setStrength(Collator.PRIMARY);
		if (usCollator.compare("abc", "ABC") == 0) {
			System.out.println("Strings are equivalent");
		}
	}
}