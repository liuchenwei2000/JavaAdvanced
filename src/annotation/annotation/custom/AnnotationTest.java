/**
 * 
 */
package annotation.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * �Զ��� Annotation ʾ��
 * <p>
 * ÿ��ע�ⶼ����ͨ��һ��ע��ӿڽ��ж��壬�ӿ��еķ�����ע���е�Ԫ�����Ӧ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-7
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 

// @interface ����������һ��ע��ӿڣ�����ע��Ĺ��߽�����ʵ����ע��ӿڵĶ���
public @interface AnnotationTest {

	/**
	 * ����ע���һ��Ԫ�أ����ƽС�name����������String��Ĭ��ֵ�� ��test��
	 */
	String name() default "test";
	
	/**
	 * ����ע�����һ��Ԫ�أ����ƽС�number����������int��Ĭ��ֵ�� 0
	 */
	int number() default 0;
}
