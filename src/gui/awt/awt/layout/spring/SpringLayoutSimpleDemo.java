/**
 * 
 */
package awt.layout.spring;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Spring;
import javax.swing.SpringLayout;

import util.Displayer;

/**
 * ���ɲ�����(SpringLayout)����ʾ
 * <p>
 * ʹ�õ��ɲ��֣����Ը�ÿ�������ӵ���(Spring)��
 * ������һ�ֿ������λ�õ�װ�������ɾ��У���Сֵ����ѡֵ�����ֵ��ʵ��ֵ��
 * �������ڲ��ֽ׶α�ѹ��������չʱ����������ʵ��ֵ��
 * ����ֵ������Сֵ�����ֵ֮�䣬ʵ��ֵ����������������ɵ����λ�á�
 * 
 * @author ����ΰ
 *
 * �������ڣ�2009-7-13
 */
public class SpringLayoutSimpleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("SpringLayout Simple Demo",
				new SpringLayoutSimplePanel());
	}
}

class SpringLayoutSimplePanel extends JPanel {

	private static final long serialVersionUID = -2262833829037414040L;

	private JButton button1 = new JButton("button1");
	private JButton button2 = new JButton("button2");
	private JButton button3 = new JButton("button3");

	public SpringLayoutSimplePanel() {
		setPreferredSize(new Dimension(400, 300));

		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		add(button1);
		add(button2);
		add(button3);
		// ʹ�ø�������Сֵ����ѡֵ�����ֵ����һ�����ɡ�(���ɲ��ǳ����������Ա�ѹ��������չ)
		Spring spring = Spring.constant(0, 10000, 10000);
		// ��Ӹ����ĵ��ɣ�������ɽ���ֹ�ڵ�һ�����(button1������)����ʼ�ڵڶ������(��������)
		// ÿ���߱����������ĳ��ֵ��SpringLayout.NORTH��SOUTH��EAST��WEST
		layout.putConstraint(SpringLayout.WEST, button1, spring,
				SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, this, spring,
				SpringLayout.EAST, button3);
		// ��������������������һ���̶��ľ��룬����ʹ��֧����֧����һ�ֲ��ܱ���չ��ѹ���ĵ��ɡ�
		Spring strut = Spring.constant(10);
		layout.putConstraint(SpringLayout.WEST, button2, strut,
				SpringLayout.EAST, button1);
		layout.putConstraint(SpringLayout.WEST, button3, strut,
				SpringLayout.EAST, button2);
	}
}