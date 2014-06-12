/**
 * 
 */
package i18n;

import java.util.Locale;

/**
 * Locale��ʾ��
 * <p>
 * Locale�����ʾ���ض��ĵ������κ��Ļ�������
 * ��ҪLocale��ִ��������Ĳ�����Ϊ���Ի������еĲ�������ʹ��LocaleΪ�û���������Ϣ��
 * <p>
 * Locale ��һ�ֱ�ʶ��Ҫ��ȡ�Ķ�������Ļ��ƣ�
 * ��Locale���󴫸�����Locale����Щ��������Щ���������ݲ�ͬ�ĵ��������ͬ��ʽ���ı���
 * ��localeֻ��һ�ֱ�ʶ����Ļ��ƣ����Ƕ������������ 
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-2-24
 */
public class LocaleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ��ô�Java�����ʵ���ĵ�ǰĬ�����Ի���ֵ 
		Locale locale = Locale.getDefault();
		print(locale);
		
		// �������Ժ͹��ҹ���һ�����Ի���
		// ���Բ�����һ����Ч�� ISO ���Դ���,��Щ�������� ISO-639 �����Сд����ĸ����
		// ���Ҳ�����һ����Ч�� ISO ���Ҵ���,��Щ�������� ISO-3166 ����Ĵ�д����ĸ����
		Locale usa = new Locale("en","US");
		// Ϊ��Java�����ʵ������Ĭ�����Ի���,�ⲻ��Ӱ�����������Ի��� 
		Locale.setDefault(usa);
		print(usa);
		
		// Locale ���ṩ��һЩ����ĳ�����������Щ����Ϊ���õ����Ի������� Locale ����
		Locale uk = Locale.UK;
		Locale.setDefault(uk);
		print(uk);
	}

	private static void print(Locale locale) {
		// �����ʺ����û���ʾ�����Ի�����
		System.out.println(locale.getDisplayName());
		// �����ʺ����û���ʾ�����Ի���������
		System.out.println(locale.getDisplayCountry());
		// �����ʺ����û���ʾ�����Ի���������
		System.out.println(locale.getDisplayLanguage());
	}
}