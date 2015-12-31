/**
 * 
 */
package annotation;

import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 标准注解示例
 * <p>
 * JDK提供了一些标准注解供使用。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014-7-8
 */
public class StandardAnnotations {

	/**
	 * @deprecated
	 */
	// @Deprecated注解可以被添加到任何不再鼓励使用的项上，与上面的文档标签@deprecated具有同等功效。
	@Deprecated
	public void deprecatedMethod() {
		// do something
		
		// @SuppressWarnings 注解会告知编译器阻止特殊类型的警告信息。
		@SuppressWarnings("unused")
		int a = 0;
	}
	
	// @Override注解只能应用到方法上，如果具有这种注解的方法并不是覆盖一个超类的方法，则会有编译错误。
	@Override
	public String toString() {
		return "";
	}

	// @Generated注解的目的是供代码生成工具来使用，任何生成的源代码都可以被注解，从而与程序员提供的代码区分开。
	@Generated(value = "com.google.code", date = "2014-07-08")
	public int hashCode() {
		return 0;
	}
	
	/**
	 * @PostConstruct 和 @PreDestroy 注解用于控制对象生命周期的环境中，例如Web容器和应用服务器。
	 */
	// @PostConstruct注解的方法应该在对象被构建之后调用
	@PostConstruct
	private void postInit() {
		// so something
	}

	// @PreDestroy注解的方法应该在对象被构建之后调用
	@PreDestroy
	private void preClose() {
		// so something
	}
}
