/**
 * 
 */
package security.classloader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * MyClassLoader������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-18
 */
public class MyClassLoaderDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/** ���ڽ�class�ļ����м��ܴ��������µ����ļ� */
			
			String filePath = "files/security.classloader/HelloWorld.class";// ԭʼclass�ļ�
			FileInputStream in = new FileInputStream(filePath);
			// ���ܺ��class�ļ�
			FileOutputStream out = new FileOutputStream(
					"files/security.classloader/HelloWorld.key.class");
			int ch;
			while ((ch = in.read()) != -1) {
				// ���ܴ���
				byte c = (byte) (ch + MyClassLoader.KEY);
				out.write(c);
			}
			in.close();
			out.close();
			
			/** ���ܼ������ļ� */
			
			MyClassLoader loader = new MyClassLoader();
			// ��������ಢ�ҵ�����main����
			Class<?> clazz = loader.loadClass("HelloWorld");
			Method m = clazz.getMethod("main", args.getClass());
			m.invoke(null, (Object) new String[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}