/**
 * 
 */
package security.classloader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * MyClassLoader测试类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-2-18
 */
public class MyClassLoaderDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			/** 用于将class文件进行加密处理生成新的类文件 */
			
			String filePath = "files/security.classloader/HelloWorld.class";// 原始class文件
			FileInputStream in = new FileInputStream(filePath);
			// 加密后的class文件
			FileOutputStream out = new FileOutputStream(
					"files/security.classloader/HelloWorld.key.class");
			int ch;
			while ((ch = in.read()) != -1) {
				// 加密处理
				byte c = (byte) (ch + MyClassLoader.KEY);
				out.write(c);
			}
			in.close();
			out.close();
			
			/** 解密加载类文件 */
			
			MyClassLoader loader = new MyClassLoader();
			// 加载这个类并且调用其main方法
			Class<?> clazz = loader.loadClass("HelloWorld");
			Method m = clazz.getMethod("main", args.getClass());
			m.invoke(null, (Object) new String[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}