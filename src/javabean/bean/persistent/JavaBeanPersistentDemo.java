/**
 * 
 */
package bean.persistent;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * JavaBean持久化示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-7-4
 */
public class JavaBeanPersistentDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/bean.persistent/bean.xml";

		JavaBean bean = new JavaBean();
		bean.setNumber(10);
		bean.setName1("Tom");
		System.out.println("bean=" + bean);
		
		writeJavaBean(filePath, bean);
		
		System.out.println("newBean=" + readJavaBean(filePath));
	}

	/**
	 * 持久化一个JavaBean对象到XML文件
	 */
	private static void writeJavaBean(String filePath, JavaBean bean) {
		try {
			/**
			 * 只有那些与默认值不同的属性会被保存下来。
			 * XMLEncoder会生成一个默认的JavaBean对象，并将它的属性与被处理的JavaBean对象作比较。
			 * 只有那些与默认值不同的属性，才会生成属性设置语句，这个过程叫做消除冗余。
			 * 结果是，存储下来的东西通常比序列化的结果要小。
			 */
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(new File(
					filePath)));
			encoder.writeObject(bean);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 从XML文件中读取一个JavaBean对象
	 */
	private static JavaBean readJavaBean(String filePath) {
		JavaBean newBean = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(new File(
					filePath)));
			newBean = (JavaBean) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return newBean;
	}
}