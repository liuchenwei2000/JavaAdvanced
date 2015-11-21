/**
 * 
 */
package annotation.custom;

import java.lang.reflect.Method;

/**
 * 注解安装器示例
 * <p>
 * 注解本身不会做任何事情，它们只是存在于源文件中。编译器将它们置于类文件中，并且虚拟机会将它们载入。
 * 现在需要一个分析注解以及安装行为监听器的机制，这就是本类的职责所在。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-7
 */
public class AnnotationInstaller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AnnotationUsed annotationUsed = new AnnotationUsed();
		AnnotationInstaller.processAnnotations(annotationUsed);
	}

	/**
	 * 处理参数对象中的注解
	 */
	public static void processAnnotations(Object object) {
		Class<? extends Object> clazz = object.getClass();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			// 获得指定类型的注解，如果没有则返回null
			AnnotationTest at = method.getAnnotation(AnnotationTest.class);
			if (at != null) {
				try {
					testMethod(object, method);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void testMethod(Object object, Method method)
			throws Exception {
		System.out.println("TEST:enter method " + method.getName());
		method.invoke(object);
		System.out.println("TEST:leave method " + method.getName());
	}
}
