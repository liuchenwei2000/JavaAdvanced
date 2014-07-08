/**
 * 
 */
package annotation.custom;

import java.lang.reflect.Method;

/**
 * ע�ⰲװ��ʾ��
 * <p>
 * ע�Ȿ�������κ����飬����ֻ�Ǵ�����Դ�ļ��С��������������������ļ��У�����������Ὣ�������롣
 * ������Ҫһ������ע���Լ���װ��Ϊ�������Ļ��ƣ�����Ǳ����ְ�����ڡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-7
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
	 * ������������е�ע��
	 */
	public static void processAnnotations(Object object) {
		Class<? extends Object> clazz = object.getClass();
		Method[] declaredMethods = clazz.getDeclaredMethods();
		for (Method method : declaredMethods) {
			// ���ָ�����͵�ע�⣬���û���򷵻�null
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
