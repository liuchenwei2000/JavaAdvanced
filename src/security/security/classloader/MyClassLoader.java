/**
 * 
 */
package security.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * �Զ����������
 * <p>
 * ��д�Զ�������������ֻ��Ҫ�̳�ClassLoader�࣬Ȼ�󸲸�����ķ�����</br><p>
 * findClass(String className)</br><p>
 * ClassLoader�����loadClass�������ڽ���ļ��ز���ί�и��丸�������ȥ���С�
 * ֻ�е�������δ���ز��Ҹ��������Ҳ�޷����ظ���ʱ���ŵ���findClass������</br>
 * ���Ҫʵ�ָ÷����������������¼��㣺</br>
 * 1��Ϊ���Ա����ļ�ϵͳ����������Դ����������ֽ��롣</br>
 * 2������ClassLoader�����defineClass��������������ṩ�ֽ��롣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-2-18
 */
public class MyClassLoader extends ClassLoader {

	public static final int KEY = (int) (Math.random()*100);
	
	/**
	 * ��������ֽ��룬��ͨ��defineClass�������ֽ��봫�������
	 * 
	 * @param name
	 *            ������ʹ��.��Ϊ�����ָ��������Ҳ�ʹ��".class"��׺
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classBytes = null;
		try {
			classBytes = loadClassBytes(name);
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
		/**
		 * defineClass(String name,byte[] data,int offset,int length)
		 * <p>
		 * ��һ���µ�����ӵ��������
		 * name ������ʹ��.��Ϊ�����ָ��������Ҳ�ʹ��".class"��׺
		 * data ���ڴ�Ÿ����ֽ��������
		 * offset �������ֽ������ʼλ��
		 * length �������ֽ���ĳ���
		 * */
		Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
		if (clazz == null)
			throw new ClassNotFoundException(name);
		return clazz;
	}

	/**
	 * ���ؼ��ز�����Ӧclass�ļ���byte����(�������ֽ���)
	 * 
	 * @param name
	 *            class�ļ���
	 */
	private byte[] loadClassBytes(String name) throws IOException {
		// ��Ӧ�ļ��ܺ��class�ļ�
		String fileName = "files/security.classloader/" + name + ".key.class";
		System.out.println("loading " + fileName);
		FileInputStream in = new FileInputStream(fileName);
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				// ���ܲ���
				buffer.write((byte) (ch - KEY));
			}
			in.close();
			return buffer.toByteArray();
		} finally {
			in.close();
		}
	}
}