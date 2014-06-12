/**
 * 
 */
package swing.button;

import java.awt.GridLayout;
import java.lang.reflect.Constructor;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import util.Displayer;

/**
 * ��ť����ʾ
 * <p>
 * Ҫ���õ�ѡ��ť���ֳ�ĳ��"����"��Ϊ����������Ǽ��뵽һ��"��ť��"�С�
 * ButtonGroup����Ϊһ�鰴ť����һ�����(multiple-exclusion)������
 * ʹ����ͬ��ButtonGroup���󴴽�һ�鰴ť��ζ��"����"����һ����ťʱ�����ر����е��������а�ť ��
 * �ɽ�ButtonGroup�����κδ�AbstractButton�̳еĶ����顣
 * ͨ������ť�����JRadioButton��JRadioButtonMenuItem��JToggleButton��ʵ����
 * ����JButton��JMenuItem��ʵ�����밴ť���в�û��ʲô���壬��ΪJButton��JMenuItem��ʵ��ѡ��״̬ ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2009-5-16
 */
public class ButtonGroupDemo {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Displayer.createAndShowGUI("ButtonGroup Demo", new ButtonGroupPanel());
	}
}

class ButtonGroupPanel extends JPanel {

	private static final long serialVersionUID = 1243483188884805647L;

	/** ��ť���� */
	private static final String[] names = { "one", "two", "three", "four",
			"five", "six" };

	public ButtonGroupPanel() {
		setLayout(new GridLayout(4, 1));
		add(makeButtonPanel(JButton.class, names));
		add(makeButtonPanel(JToggleButton.class, names));
		add(makeButtonPanel(JCheckBox.class, names));
		add(makeButtonPanel(JRadioButton.class, names));
	}

	private static JPanel makeButtonPanel(Class<?> klass, String[] names) {
		ButtonGroup bg = new ButtonGroup();
		JPanel panel = new JPanel();
		String title = klass.getName();
		title = title.substring(title.lastIndexOf('.') + 1);
		panel.setBorder(new TitledBorder(title));
		// ��Ӱ�ť
		for (int i = 0; i < names.length; i++) {
			AbstractButton button = new JButton("failed");
			try {
				// ʹ���˷������
				Constructor<?> c = klass
						.getConstructor(new Class[] { String.class });
				button = (AbstractButton) c
						.newInstance(new Object[] { names[i] });
			} catch (Exception e) {
				e.printStackTrace();
			}
			bg.add(button);
			panel.add(button);
		}
		return panel;
	}
}