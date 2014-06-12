/**
 * 
 */
package awt.layout;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import util.Displayer;

/**
 * �����ֹ�����
 * <p>
 * �������(JPanel)��Ĭ�ϲ��ֹ�������FlowLayout��<p>
 * �ص㣺</br>
 * ��һ����ˮƽ���������ֱ��û���㹻�Ŀռ�Ϊֹ����ʱ�ſ�ʼ�µ�һ�С�
 * ���û���������ʱ�����ֹ������Զ��ص��������λ��ʹ�������õĿռ䡣
 * ������ѡ����ÿһ������������ķ�����Ĭ�Ϸ�ʽ�Ǿ�����ʾ(FlowLayout.CENTER)��
 * ���⻹�������(FlowLayout.LEFT)���Ҷ���(FlowLayout.RIGHT)��
 * ��Ҫ��FlowLayout����Ĺ�������ָ�����뷽ʽ��</br>
 * new FlowLayout(FlowLayout.LEFT);
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2007-8-21
 */
public class FlowLayoutDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("FlowLayout Demo", new FlowLayoutPanel());
	}
}

class FlowLayoutPanel extends JPanel {
	
	private static final long serialVersionUID = -4692931166665337007L;

	public FlowLayoutPanel() {
		super();
		FlowLayout flow = new FlowLayout();
		setLayout(flow);
		flow.setHgap(10);// �����о�
		flow.setVgap(10);// �����о�
		add(new JButton("one"));
		add(new JButton("two"));
		add(new JButton("three"));
		add(new JButton("four"));
		add(new JButton("five"));
	}
}