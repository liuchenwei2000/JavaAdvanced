/**
 * 
 */
package i18n.format;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DateFormat��ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-27
 */
public class DateFormatDemo {

	/** ��ʽ����� ���� */
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
			// �������Ի����͸�ʽ����񷵻����ڸ�ʽ����
			DateFormat df1 = DateFormat.getDateInstance(style, locale);
			// �������Ի����͸�ʽ����񷵻�ʱ���ʽ����
			DateFormat df2 = DateFormat.getTimeInstance(style, locale);
			System.out.println("date : " + df1.format(date));
			System.out.println("time : " + df2.format(date));
			System.out.println();
		}
	}
}