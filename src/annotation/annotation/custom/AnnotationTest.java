/**
 * 
 */
package annotation.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 Annotation 示例
 * <p>
 * 每个注解都必须通过一个注解接口进行定义，接口中的方法与注解中的元素相对应。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-7
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 

// @interface 声明创建了一个注解接口，处理注解的工具将接收实现了注解接口的对象
public @interface AnnotationTest {

	/**
	 * 这是注解的一个元素，名称叫“name”，类型是String，默认值是 “test”
	 */
	String name() default "test";
	
	/**
	 * 这是注解的另一个元素，名称叫“number”，类型是int，默认值是 0
	 */
	int number() default 0;
}
