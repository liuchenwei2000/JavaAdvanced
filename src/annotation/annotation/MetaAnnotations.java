/**
 * 
 */
package annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ԫע��ʾ��
 * <p>
 * ע������Annotation��Annotation����Ԫע�⡣
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014-7-8
 */
/*
 * @Target Ԫע�����Ӧ����һ��ע�⣬�����Ƹ�ע�����Ӧ�õ���Щ���ϡ�
 * 
 * ��Ԫ���������£�
 * ElementType.ANNOTATION_TYPE - ע����������
 * ElementType.PACKAGE - ��
 * ElementType.TYPE - ��(����enum��interface��@interface)
 * ElementType.METHOD - ����
 * ElementType.CONSTRUCTOR - ������
 * ElementType.FIELD - ��Ա����
 * ElementType.PARAMETER - ��������������
 * ElementType.LOCAL_VARIABLE - ���ر���
 * 
 * û��@Target���Ƶ�ע�����Ӧ�õ��κ����ϡ�
 */
@Target({ ElementType.TYPE, ElementType.METHOD })

/*
 * @Retention Ԫע������ָ��һ��ע��Ӧ�ñ����೤ʱ�䡣
 * 
 * ��Ԫ���������£�
 * RetentionPolicy.SOURCE - �����������ļ��е�ע��
 * RetentionPolicy.CLASS - ���ļ��е�ע�⣬��JVM����Ҫ����������
 * RetentionPolicy.RUNTIME - ���ļ��е�ע�⣬����JVM����
 */
@Retention(RetentionPolicy.RUNTIME)

/*
 * @Documented Ԫע��Ϊ��Javadoc�������ĵ������ṩ��һЩ��ʾ��
 */
@Documented

/*
 * @Inherited Ԫע��ֻ��Ӧ���ڶ����ע�⣬���һ�����ע�˼̳�ע�⣬��ô�����������඼�Զ�����ͬ����ע�⡣
 */
@Inherited
public @interface MetaAnnotations {

}
