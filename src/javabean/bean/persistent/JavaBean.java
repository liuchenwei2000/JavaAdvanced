/**
 * 
 */
package bean.persistent;

/**
 * 一个简单JavaBean
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-7-4
 */
public class JavaBean {

	private int number;
	private String name1;
	private String name2 = "no name2";

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String toString() {
		return number + " " + name1 + " " + name2;
	}
}