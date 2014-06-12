/**
 * 
 */
package i18n.format;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * NumberFormat��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-24
 */
public class NumberFormatDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String value = "123456.78";
		// �����������Ի���������
		for (Locale locale : NumberFormat.getAvailableLocales()) {
			print(locale, value);
		}
	}

	private static void print(Locale locale, String value) {
		System.out.println(locale.getDisplayName());
		// ����ָ�����Ի�����ͨ�����ָ�ʽ
		NumberFormat nf = NumberFormat.getNumberInstance(locale);
		// ����ָ�����Ի����Ļ��Ҹ�ʽ
		NumberFormat cf = NumberFormat.getCurrencyInstance(locale);
		// ����ָ�����Ի����İٷֱȸ�ʽ
		NumberFormat pf = NumberFormat.getPercentInstance(locale);
		try {
			// �Ӹ����ַ����Ŀ�ʼ�����ı����з���������һ������
			double doubleValue = nf.parse(value).doubleValue();
			// �Բ�����ֵ���и�ʽ���������ַ���
			System.out.println("double value = " + nf.format(doubleValue));
			System.out.println("currency value = " + cf.format(doubleValue));
			System.out.println("percent value = " + pf.format(doubleValue));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}