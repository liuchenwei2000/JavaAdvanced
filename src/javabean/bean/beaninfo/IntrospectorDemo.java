/**
 * 
 */
package bean.beaninfo;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 内省器Introspector示例
 * <p>
 * JavaBean模型最关键部分之一，表现在当你从选用区拖动一个Bean(可视化Bean)，然后把它放置到窗体上的时候。
 * 应用程序构建工具必须能够创建这个Bean(需要无参构造器)，然后在不访问Bean源代码的情况下抽取出所有必要信息，以创建属性和事件处理器的列表。
 * <p>
 * Java的反射机制能发现未知类的所有方法。
 * 对于解决JavaBean的这个问题，这是个完美的方案，你不用像其它可视化编程语言那样使用任何语言附加的关键字。
 * 实际上，Java语言里加入反射机制的主要原因之一就是为了支持JavaBean(尽管反射也支持对象序列化和远程方法调用)。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2009-6-17
 */
public class IntrospectorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			introspect(Class.forName("bean.Frog"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用内省器抽取BeanInfo
	 * <p>
	 * Java的设计者希望提供一个标准工具，不仅要使Bean用起来简单，而且对于创建更复杂的Bean，能够提供一个标准方法。
	 * 这个工具就是Introspector(内省器)类，这个类最重要的就是静态的getBeanInfo()方法。
	 * 向这个方法传递一个Class对象引用，它能够完全侦测这个类，然后返回一个BeanInfo对象，可以通过这个对象得到Bean的属性、方法和事件。
	 */
	private static void introspect(Class<?> bean) {
		BeanInfo bi = null;
		try {
			/**
			 * 在给定的"断"点之下，对JavaBean(bean对象)进行内省，了解其所有属性和公开的方法。
			 * <p> 
			 * 第二个参数用来指定断点,因为不关心来自Object的方法，所以在解析来自Object的所有方法之前停止查询。
			 */
			bi = Introspector.getBeanInfo(bean, Object.class);
		} catch (IntrospectionException e) {
			System.out.println("Couldn't introspect " + bean.getName());
			return;
		}
		introspectProperties(bi);
		introspectMethods(bi);
		introspectEventListeners(bi);
	}

	private static void introspectProperties(BeanInfo bi) {
		// 获取Bean的所有属性
		PropertyDescriptor[] properties = bi.getPropertyDescriptors();
		for (int i = 0; i < properties.length; i++) {
			Class<?> p = properties[i].getPropertyType();
			if (p == null)
				continue;
			System.out.println("Property type:\n" + p.getSimpleName());
			// 返回属性的别名(从方法中抽取的)
			System.out.println("Property name:\n" + properties[i].getName());
			// 返回属性的读方法
			Method readMethod = properties[i].getReadMethod();
			if (readMethod != null) {
				System.out.println("Read method:\n" + readMethod);
			}
			// 返回属性的写方法
			Method writeMethod = properties[i].getWriteMethod();
			if (writeMethod != null) {
				System.out.println("Write method:\n" + writeMethod);
			}
			System.out.println("====================");
		}
	}

	private static void introspectMethods(BeanInfo bi) {
		System.out.println("Public methods:");
		// 获取Bean所有的公共方法(包括属性方法)
		MethodDescriptor[] methods = bi.getMethodDescriptors();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getMethod().toString());
		}
		System.out.println("======================");
		System.out.println("Event support:");
	}

	private static void introspectEventListeners(BeanInfo bi) {
		// 获取Bean所支持的事件集合
		EventSetDescriptor[] events = bi.getEventSetDescriptors();
		for (int i = 0; i < events.length; i++) {
			System.out.println("Listener type:\n"
					+ events[i].getListenerType().getSimpleName());
			Method[] lm = events[i].getListenerMethods();
			for (int j = 0; j < lm.length; j++) {
				System.out.println("Listener method:\n" + lm[j].getName());
			}
			MethodDescriptor[] lmd = events[i].getListenerMethodDescriptors();
			for (int j = 0; j < lmd.length; j++) {
				System.out
						.println("Method descriptor:\n" + lmd[j].getMethod());
			}
			// 返回添加此事件监听器的方法
			Method addListener = events[i].getAddListenerMethod();
			System.out.println("Add Listener Method:\n" + addListener);
			// 返回移除此事件监听器的方法
			Method removeListener = events[i].getRemoveListenerMethod();
			System.out.println("Remove Listener Method:\n" + removeListener);
			System.out.println("====================");
		}
	}
}