/**
 * 
 */
package annotation;

/**
 * ע��ӿ��﷨ʾ��
 * <p>
 * ���е�ע��ӿڶ�ֱ����չ��java.lang.annotation.Annotation��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-7
 */
public @interface AnnotationSytax {

	enum Color {
		RED, BLUE, YELLOW
	};
	
	/**
	 * ע��ӿ��еķ�������û���κβ�����û���κ�throws��䣬����Ҳ�����Ƿ��͵ġ�
	 * ע��Ԫ�ص����Ϳ����ǣ�
	 * �������͡�Stirng��Class��enum��ע�����͡��������͵�����
	 */
	
	boolean isHere() default false;

	String info() default "";

	/**
	 * һ��ע��Ԫ�ش�����������Ϊnull��������������Ĭ��ֵΪnull�����ǿ���ʹ��""����Void.class
	 */
	Class<?> test() default Void.class;

	Color color() default Color.RED;

	AnnotationTest ref() default @AnnotationTest;
	
	String[] args() default {};
}
