/**
 * 
 */
package swing.inside;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * 直接操作Model演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2011-2-24
 */
public class UsingModelDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 不使用model，直接使用Component进行操作
		 */
		JComboBox box1 = new JComboBox();
		for (int i = 0; i < 10; i++) {
			box1.addItem(i);
		}
		/*
		 * 直接使用model
		 * <p>
		 * 这种方式会更快，原因有2：
		 * 1
		 * 因为所有项都是一次添加到模型中，这意味着只有一个事件发出，这意味着更少的事件触发，更少的方法调用。
		 * 2
		 * 因为需要通知变化的对象更少，总的工作量等于触发次数乘以侦听器数目。因为模型是新建的，它的侦听器为0。
		 */
		Integer[] datas = new Integer[10];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = i;
		}
		ComboBoxModel model = new DefaultComboBoxModel(datas);
		box1 = new JComboBox(model);
	}
}
