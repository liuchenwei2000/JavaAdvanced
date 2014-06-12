/**
 * 
 */
package util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 打印类
 * 
 * 打印出数组元素和集合类中的元素
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-13
 */
public class Printer {

	private Printer() {
		// no instance
	}

	/**
	 * 打印int型数组
	 */
	public static void print(int[] array) {
		for (int item : array) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	/**
	 * 打印double型数组
	 */
	public static void print(double[] array) {
		for (double item : array) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	/**
	 * 打印char型数组
	 */
	public static void print(char[] array) {
		for (char item : array) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	/**
	 * 打印对象数组
	 */
	public static <T> void print(T[] array) {
		for (T item : array) {
			System.out.print(item.toString() + " ");
		}
		System.out.println();
	}

	/**
	 * 打印int型二维数组
	 */
	public static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 打印double型二维数组
	 */
	public static void print(double[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 打印char型二维数组
	 */
	public static void print(char[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 打印对象二维数组
	 */
	public static <T> void print(T[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j].toString() + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Java标准方式打印Collection
	 */
	public static <T> void print(Collection<T> c) {
		System.out.println(c);
	}

	/**
	 * Java标准方式打印Map
	 */
	public static <K, V> void print(Map<K, V> map) {
		System.out.println(map);
	}
	
	/**
	 * 打印实现Iterator接口的类 比如List，Set等
	 */
	public static <T> void print(Iterator<T> it) {
		while (it.hasNext()) {
			System.out.print(it.next().toString() + " ");
		}
		System.out.println();
	}
	
	/**
	 * Printer自定义方式打印Collection
	 */
	public static <T> void printAll(Collection<T> c) {
		for (T t : c) {
			System.out.print(t.toString() + " ");
		}
		System.out.println();
	}
	
	/**
	 * Printer自定义方式打印Map中的元素
	 */
	public static <K, V> void printAll(Map<K, V> map) {
		for (K key : map.keySet()) {
			System.out.print(key + " = ");
			System.out.println(map.get(key));
		}
	}
	
	/**
	 * Map的传统遍历方式打印Map中的元素
	 */
	private static <K, V> void printAll2(Map<K, V> map) {
		// 映射Map的内容通过使用由调用函数entrySet()获得集合视图而显示出来
		Iterator<Entry<K, V>> it = map.entrySet().iterator();// 构造视图的遍历器
		// 显示视图元素
		while (it.hasNext()) {
			// 关键字和值通过调用由Map.Entry的getKey()和getValue()方法返回
			Entry<K, V> me = (Entry<K, V>) it.next();
			System.out.print(me.getKey() + " = ");
			System.out.println(me.getValue());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] values = { "a", "b", "c", "d", "e" };
		print(values);
		Collection<String> c = Arrays.asList(values);
		print(c);
		printAll(c);
		print(new HashSet<String>(c));
		printAll(new HashSet<String>(c));
		print(c.iterator());
		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "1");
		map.put("B", "2");
		map.put("C", "3");
		print(map);
		printAll(map);
		printAll2(map);
	}
}