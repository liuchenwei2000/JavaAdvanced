/**
 * 
 */
package swing.inside;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * ֱ�Ӳ���Model��ʾ
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2011-2-24
 */
public class UsingModelDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * ��ʹ��model��ֱ��ʹ��Component���в���
		 */
		JComboBox box1 = new JComboBox();
		for (int i = 0; i < 10; i++) {
			box1.addItem(i);
		}
		/*
		 * ֱ��ʹ��model
		 * <p>
		 * ���ַ�ʽ����죬ԭ����2��
		 * 1
		 * ��Ϊ�������һ����ӵ�ģ���У�����ζ��ֻ��һ���¼�����������ζ�Ÿ��ٵ��¼����������ٵķ������á�
		 * 2
		 * ��Ϊ��Ҫ֪ͨ�仯�Ķ�����٣��ܵĹ��������ڴ�������������������Ŀ����Ϊģ�����½��ģ�����������Ϊ0��
		 */
		Integer[] datas = new Integer[10];
		for (int i = 0; i < datas.length; i++) {
			datas[i] = i;
		}
		ComboBoxModel model = new DefaultComboBoxModel(datas);
		box1 = new JComboBox(model);
	}
}