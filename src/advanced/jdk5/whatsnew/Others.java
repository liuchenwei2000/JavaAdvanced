/**
 * 
 */
package jdk5.whatsnew;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import static java.lang.Math.*;

/**
 * JDK5其他新特性
 * 
 * @author 刘晨伟
 *
 * 创建日期：2007-9-11
 */
public class Others {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 泛型
		 * 
		 * 允许指定集合里元素的类型，可以使得在编译时刻进行类型检查。
		 */
		Collection<String> c = new ArrayList<String>();
		c.add("string");
		c.add("collection");
		
		/*
		 * for-each循环
		 * 
		 * 简化了数组和集合的遍历 ，并且避免了强制类型转换。
		 */
		// for-each输出数组元素
		int a[] = { 1, 2, 3, 4, 5 };
		for (int item : a)
			System.out.print(item + " ");
		System.out.println();

		// 传统输出数组元素
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();

		// for-each输出集合元素
		for (String item : c)
			System.out.print(item + " ");
		System.out.println();

		// 传统输出集合元素
		Iterator<String> it = c.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println();

		/*
		 * 自动装包/拆包
		 * 
		 * 使得基本类型数据和它们包装类能够自动转换，解决了集合类不能存储基本类型的问题。
		 * 但是自动包装机制不能应用于数组。
		 */
		int b = 2;
		Collection<Integer> c2 = new ArrayList<Integer>();
		c2.add(b + 2);// 先执行加法然后转换成Integer类型
		
		/*
		 * 静态导入包
		 * 
		 * import static java.lang.Math.*;
		 * 
		 * 使得不用再以Math.sin();的方式调用它的静态方法，而直接可以sin();的方式调用Math包中静态方法。
		 * 但是同时也降低了程序的可读性。
		 */
		double d = sin(0.5);
		System.out.println("sin(0) = " + d);
	}
}