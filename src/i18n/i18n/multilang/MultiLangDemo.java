/**
 * 
 */
package i18n.multilang;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ʵ�ֶ�������ʾ
 * <p>
 * Java��Ȼ��Ϊһ����ƽ̨�����Ծͱ�ȻҪ�ڸ��ֲ�ͬ�����Ի�����ʹ�á�
 * Ϊ�˽���������Java�ṩ��һ��������ResourceBundle������ʵ��Java�Ĺ��ʻ���
 * ���ĵ�˼����ǣ��Բ�ͬ�������ṩһ����ͬ����Դ�ļ���
 * <p>
 * ���ʻ��Ĳ���
 * <li>1����дҪʵ�ֹ��ʻ���������
 * <li>2��������Դ�ļ���ע����Դ�ļ��Ķ���Ҫ����һ���Ĺ淶, �淶���£�</br>
 * �����ȱʡ����Դ�ļ����ļ���ȡ��Ϊmessage.properties��
 * ��ô��Ӧ���������Ե���Դ�ļ�������message_���Դ���_���Ҵ���.properties��</br>
 * ����: </br>
 * ��������Դ�����zh�����Ҵ�����CN�����Լ������ĵ���Դ�ļ�������: message_zh_CN.properties��
 * Ӣ������Դ�����en�������Ĺ��Ҵ�����US����������Ӣ�����Դ�ļ�����: message_en_US.properties��
 * <li>3�����������ʹ�û���ȡ����Ӧ����Դ�ļ���
 * <li>4����ȡ�õ���Դ�ļ���ȡ��Key��Ӧ��ֵ��
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-6-23
 */
public class MultiLangDemo {

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// ���ȱʡ��locale
		Locale locale = Locale.getDefault();
		showMessage(locale);
		// ���ö��Ƶ����Թ���locale
		Locale locale2 = new Locale("en_US");
		showMessage(locale2);
	}

	private static void showMessage(Locale locale) {
		// �����Դ�ļ�
		ResourceBundle rb = ResourceBundle.getBundle("resources/message", locale);
		// ʹ��ResourceBundle����key���ض�Ӧ��valueֵ
		System.out.println("name=" + rb.getString("name"));
		System.out.println("sex=" + rb.getString("sex"));
	}
}