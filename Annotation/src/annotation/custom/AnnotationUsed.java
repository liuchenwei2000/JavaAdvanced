/**
 * 
 */
package annotation.custom;

/**
 * 注解使用示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-7
 */
public class AnnotationUsed {

	/**
	 * 注解是当作一个修饰符来使用的，它被置于被注解项之前，中间没有分号。
	 * 每一个注解的名称前面都加上了@符号。
	 * 注解本身并不会做任何事情，它需要工具支持才会有用。
	 * 除了方法外，还可以注解类、成员以及本地变量，这些注解可以存在与任何可以放置
	 * 一个public或static这样的修饰符的地方。
	 */
	// 注解可以定义成包含元素的形式，这些元素可以被阅读这些注解的工具去处理
	@AnnotationTest(name = "operate1")
	public void operate1() {
		System.out.println("in AnnotationUsed.operate()");
	}
	
	/**
	 * 这是没使用注解的普通方法
	 */
	public void operate2() {
		System.out.println("in AnnotationUsed.operate()");
	}
	
	/**
	 * 这是使用另一个注解的方法
	 */
	// 因为注解是由编译器计算而来的，因此所有元素值必须是编译期常量。
	// 如果元素值是一个数组，那么要将它的值用括号括起来。
	@AnnotationTest(name = "operate1")// 一个项可以具有多个注解，只要它们属于不同的类型即可。
	@AnnotationSytax(args = { "var1", "vra2" })
//	@AnnotationSytax(args = { "var1", "vra2" }) // 当注解一个特定项的时候，不能多次使用同一个注解类型。
	public void operate3() {
		System.out.println("in AnnotationUsed.operate()");
	}
}
