/**
 * 
 */
package annotation;

/**
 * 注解接口语法示例
 * <p>
 * 所有的注解接口都直接扩展自java.lang.annotation.Annotation。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-7
 */
public @interface AnnotationSytax {

	enum Color {
		RED, BLUE, YELLOW
	};
	
	/**
	 * 注解接口中的方法可以没有任何参数，没有任何throws语句，并且也不能是泛型的。
	 * 注解元素的类型可以是：
	 * 基本类型、Stirng、Class、enum、注解类型、上述类型的数组
	 */
	
	boolean isHere() default false;

	String info() default "";

	/**
	 * 一个注解元素从来不能设置为null，甚至不允许其默认值为null，但是可以使用""或者Void.class
	 */
	Class<?> test() default Void.class;

	Color color() default Color.RED;

	AnnotationTest ref() default @AnnotationTest;
	
	String[] args() default {};
}
