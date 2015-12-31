/**
 * 
 */
package swing.tree;

/**
 * 人类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2007-8-28
 */
public class Person {
	
	private String name;

	public Person() {
		name = "无名氏";
	}

	public Person(String theName) {
		name = theName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return "Person" + this.getName();
	}

	public String toString() {
		return getInfo();
	}
}
