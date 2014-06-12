/**
 * 
 */
package bean.persistent;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * JavaBean�־û�ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-7-4
 */
public class JavaBeanPersistentDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/bean.persistent/bean.xml";

		JavaBean bean = new JavaBean();
		bean.setNumber(10);
		bean.setName1("Tom");
		System.out.println("bean=" + bean);
		
		writeJavaBean(filePath, bean);
		
		System.out.println("newBean=" + readJavaBean(filePath));
	}

	/**
	 * �־û�һ��JavaBean����XML�ļ�
	 */
	private static void writeJavaBean(String filePath, JavaBean bean) {
		try {
			/**
			 * ֻ����Щ��Ĭ��ֵ��ͬ�����Իᱻ����������
			 * XMLEncoder������һ��Ĭ�ϵ�JavaBean���󣬲������������뱻�����JavaBean�������Ƚϡ�
			 * ֻ����Щ��Ĭ��ֵ��ͬ�����ԣ��Ż���������������䣬������̽����������ࡣ
			 * ����ǣ��洢�����Ķ���ͨ�������л��Ľ��ҪС��
			 */
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File(
					filePath)));
			encoder.writeObject(bean);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��XML�ļ��ж�ȡһ��JavaBean����
	 */
	private static JavaBean readJavaBean(String filePath) {
		JavaBean newBean = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File(
					filePath)));
			newBean = (JavaBean) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return newBean;
	}
}