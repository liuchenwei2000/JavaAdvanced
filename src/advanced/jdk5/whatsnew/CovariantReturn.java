/**
 * 
 */
package jdk5.whatsnew;

/**
 * 返回协变类型
 * <p>
 * 表示在子类中的被覆盖方法可以返回基类方法的返回类型的子类型。
 *
 * @author 刘晨伟
 *
 * 创建日期：2008-4-10
 */
public class CovariantReturn {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReturnFather rf = new ReturnFather();
		Father f = rf.get();
		System.out.println(f);
		rf = new ReturnSon();
		f = rf.get();
		System.out.println(f);
	}
}

class Father {
	public String toString() {
		return "Father";
	}
}

class Son extends Father {
	public String toString() {
		return "Son";
	}
}

class ReturnFather {
	public Father get() {
		return new Father();
	}
}

class ReturnSon extends ReturnFather {
	public Son get() {
		return new Son();
	}
}