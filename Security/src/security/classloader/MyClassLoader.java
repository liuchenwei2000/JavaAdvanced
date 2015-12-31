/**
 * 
 */
package security.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 自定义类加载器
 * <p>
 * 编写自定义的类加载器，只需要继承ClassLoader类，然后覆盖下面的方法：</br><p>
 * findClass(String className)</br><p>
 * ClassLoader超类的loadClass方法用于将类的加载操作委托给其父类加载器去运行。
 * 只有当该类尚未加载并且父类加载器也无法加载该类时，才调用findClass方法。</br>
 * 如果要实现该方法，必须做到以下几点：</br>
 * 1，为来自本地文件系统或者其他来源的类加载其字节码。</br>
 * 2，调用ClassLoader超类的defineClass方法，向虚拟机提供字节码。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-18
 */
public class MyClassLoader extends ClassLoader {

	public static final int KEY = (int) (Math.random()*100);
	
	/**
	 * 查找类的字节码，并通过defineClass方法将字节码传给虚拟机
	 * 
	 * @param name
	 *            类名；使用.作为包名分隔符，并且不使用".class"后缀
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
		/*
		 * defineClass(String name,byte[] data,int offset,int length)
		 * <p>
		 * 将一个新的类添加到虚拟机中
		 * name 类名；使用.作为包名分隔符，并且不使用".class"后缀
		 * data 用于存放该类字节码的数组
		 * offset 数组中字解码的起始位置
		 * length 数组中字解码的长度
		 */
		Class<?> clazz = defineClass(name, classBytes, 0, classBytes.length);
		if (clazz == null)
			throw new ClassNotFoundException(name);
		return clazz;
	}

	/**
	 * 返回加载参数对应class文件的byte数组(即加载字节码)
	 * 
	 * @param name
	 *            class文件名
	 */
	private byte[] loadClassBytes(String name) throws IOException {
		// 对应的加密后的class文件
		String fileName = "files/security.classloader/" + name + ".key.class";
		System.out.println("loading " + fileName);
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int ch;
			while ((ch = in.read()) != -1) {
				// 解密操作
				buffer.write((byte) (ch - KEY));
			}
			return buffer.toByteArray();
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
}
