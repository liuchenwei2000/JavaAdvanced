/**
 * 
 */
package jdk5.whatsnew;

/**
 * 方法的可变参数列使得它可以接受可变数目的参数
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-9-11
 */
public class Varargs<T> {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Varargs<Object> v = new Varargs<Object>();
		v.print("a", "b", "c", "d");
		Object[] s = { "e", "f", "g", "h" };
		v.print(s);
	}
	
	/**
	 * 格式：method(type... param)
	 * 
	 * 可变参数必须是函数声明中的最后一个参数，该方法可以接受任意数目(含0)的type类型参数，
	 * 并且也可以接受type类型的数组作为参数。
	 */
	private void print(T... objects) {
		// 实际上形参objects代表的是一个数组
		for (int i = 0; i < objects.length; i++) {
			System.out.print(objects[i] + " ");
		}
		System.out.println();
	}
	
	// 在方法重载过程中，编译器对待参数列的T...和T[]是一样的
//	private void print(T[] objects) {
//		// ...
//	}
}